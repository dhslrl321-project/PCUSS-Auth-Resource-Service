package kr.ac.pcu.cyber.authresourceservice.service;

import kr.ac.pcu.cyber.authresourceservice.client.UserServiceClient;
import kr.ac.pcu.cyber.authresourceservice.common.SocialType;
import kr.ac.pcu.cyber.authresourceservice.factory.OAuthServiceFactory;
import kr.ac.pcu.cyber.authresourceservice.model.dto.OAuthRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.OAuthResponseData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.UserRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.vo.TokenData;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private final OAuthServiceFactory factory;
    private final UserServiceClient userServiceClient;

    public TokenService(OAuthServiceFactory factory, UserServiceClient userServiceClient) {
        this.factory = factory;
        this.userServiceClient = userServiceClient;
    }

    public OAuthResponseData oauth(SocialType type, OAuthRequestData requestData) {
        OAuthService oAuthService = factory.createService(type);

        TokenData tokenData = oAuthService.oauth(requestData);

        UserRequestData userRequestData = null;

        return null;
    }
}
