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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TokenServiceTest {

    private final KakaoOAuthService kakaoOAuthService = mock(KakaoOAuthService.class);
    private final NaverOAuthService naverOAuthService = mock(NaverOAuthService.class);
    private final GoogleOAuthService googleOAuthService = mock(GoogleOAuthService.class);

    private final UserServiceClient userServiceClient = mock(UserServiceClient.class);

    private final TokenRepository tokenRepository = mock(TokenRepository.class);
    private TokenService tokenService;

    private final OAuthRequestData oAuthRequestData = OAuthRequestData.builder()
            .code("authorization_code")
            .build();

    @BeforeEach
    void setUp() {
        OAuthServiceFactory oAuthServiceFactory = new OAuthServiceFactory(kakaoOAuthService, naverOAuthService, googleOAuthService);

        UserResponseData loginResponse = UserResponseData.builder()
                .id(1L)
                .userId("userid")
                .accessToken("access")
                .refreshToken("refresh")
                .nickname("nickname")
                .profileImage("profileurl")
                .build();

        UserResponseData registerResponse = UserResponseData.builder()
                .id(1L)
                .userId("userid")
                .accessToken("access")
                .refreshToken("refresh")
                .nickname("nickname")
                .profileImage("profileurl")
                .build();

        TokenData tokenData = TokenData.builder()
                .name("name")
                .accessToken("accessToken")
                .refreshToken("refreshToken")
                .email("email")
                .profileImage("profileImage")
                .build();

        Token token = Token.builder()
                .id(1L)
                .accessToken("access")
                .refreshToken("refresh")
                .tokenId("tokenId")
                .type("type")
                .UUID("uuid")
                .build();

        given(userServiceClient.login(anyString()))
                .willReturn(loginResponse);

        given(userServiceClient.register(any(UserRequestData.class)))
                .willReturn(registerResponse);

        given(kakaoOAuthService.oauth(any()))
                .willReturn(tokenData);

        given(naverOAuthService.oauth(any()))
                .willReturn(tokenData);

        given(googleOAuthService.oauth(any()))
                .willReturn(tokenData);

        given(tokenRepository.findByTokenIdAndType(anyString(), eq("kakao")))
                .willReturn(Optional.of(token));

        given(tokenRepository.findByTokenIdAndType(anyString(), eq("google")))
                .willReturn(Optional.empty());

        tokenService = new TokenService(oAuthServiceFactory, userServiceClient, tokenRepository);
    }

    @Test
    void login_with_token_service_oauth() {
        OAuthResponseData oAuthResponseData = tokenService.oauth(SocialType.valueOf("KAKAO"), oAuthRequestData);

        assertAll(
                () -> assertNotNull(oAuthResponseData),
                () -> assertNotNull(oAuthResponseData.getId()),
                () -> assertNotNull(oAuthResponseData.getJwtAccessToken()),
                () -> assertNotNull(oAuthResponseData.getJwtRefreshToken()),
                () -> assertNotNull(oAuthResponseData.getNickname()),
                () -> assertNotNull(oAuthResponseData.getProfileImage())
        );
    }

    @Test
    void register_with_token_service_oauth() {
        OAuthResponseData oAuthResponseData = tokenService.oauth(SocialType.valueOf("GOOGLE"), oAuthRequestData);

        assertAll(
                () -> assertNotNull(oAuthResponseData),
                () -> assertNotNull(oAuthResponseData.getId()),
                () -> assertNotNull(oAuthResponseData.getJwtAccessToken()),
                () -> assertNotNull(oAuthResponseData.getJwtRefreshToken()),
                () -> assertNotNull(oAuthResponseData.getNickname()),
                () -> assertNotNull(oAuthResponseData.getProfileImage())
        );
    }
}