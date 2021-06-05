package kr.ac.pcu.cyber.authresourceservice.service;

import kr.ac.pcu.cyber.authresourceservice.client.UserServiceClient;
import kr.ac.pcu.cyber.authresourceservice.common.SocialType;
import kr.ac.pcu.cyber.authresourceservice.factory.OAuthServiceFactory;
import kr.ac.pcu.cyber.authresourceservice.model.dto.OAuthRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.OAuthResponseData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.UserRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.UserResponseData;
import kr.ac.pcu.cyber.authresourceservice.model.entity.Token;
import kr.ac.pcu.cyber.authresourceservice.model.vo.TokenData;
import kr.ac.pcu.cyber.authresourceservice.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenService {
    private final OAuthServiceFactory factory;
    private final UserServiceClient userServiceClient;
    private final TokenRepository tokenRepository;

    public TokenService(OAuthServiceFactory factory, UserServiceClient userServiceClient, TokenRepository tokenRepository) {
        this.factory = factory;
        this.userServiceClient = userServiceClient;
        this.tokenRepository = tokenRepository;
    }

    public OAuthResponseData oauth(SocialType type, OAuthRequestData requestData) {
        OAuthService oAuthService = factory.createService(type);

        TokenData tokenData = oAuthService.oauth(requestData);

        UserRequestData userRequestData = UserRequestData.builder()
                .nickname(tokenData.getName())
                .email(tokenData.getEmail())
                .profileImage(tokenData.getEmail())
                .build();

        Optional<Token> token = tokenRepository.findByTokenIdAndType(tokenData.getTokenId(), String.valueOf(type));

        var context = new Object() {
            UserResponseData userResponseData;
        };

        token.ifPresentOrElse(item -> {
            context.userResponseData = userServiceClient.login(item.getUUID());

            item.update(tokenData.getAccessToken(), tokenData.getRefreshToken());

            tokenRepository.save(item);
        }, () -> {
            context.userResponseData = userServiceClient.register(userRequestData);

            Token item = tokenBuilder(tokenData, context.userResponseData, String.valueOf(type));

            tokenRepository.save(item);
        });

        return OAuthResponseData.builder()
                .id(context.userResponseData.getId())
                .jwtAccessToken(context.userResponseData.getAccessToken())
                .jwtRefreshToken(context.userResponseData.getRefreshToken())
                .nickname(context.userResponseData.getNickname())
                .profileImage(context.userResponseData.getProfileImage())
                .build();
    }

    private Token tokenBuilder(TokenData tokenData, UserResponseData userResponseData, String type) {
        return Token.builder()
                .tokenId(tokenData.getTokenId())
                .UUID(userResponseData.getUserId())
                .type(type)
                .accessToken(tokenData.getAccessToken())
                .refreshToken(tokenData.getRefreshToken())
                .build();
    }
}
