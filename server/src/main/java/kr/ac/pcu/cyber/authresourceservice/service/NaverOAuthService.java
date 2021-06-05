package kr.ac.pcu.cyber.authresourceservice.service;

import kr.ac.pcu.cyber.authresourceservice.client.naver.NaverApiClient;
import kr.ac.pcu.cyber.authresourceservice.client.naver.NaverAuthClient;
import kr.ac.pcu.cyber.authresourceservice.model.dto.OAuthRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.naver.NaverProfileResponseData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.naver.NaverTokenIssuanceRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.naver.NaverTokenIssuanceResponseData;
import kr.ac.pcu.cyber.authresourceservice.model.vo.TokenData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NaverOAuthService implements OAuthService {

    @Value("${oauth.grant_type}") private String GRANT_TYPE;
    @Value("${naver.client_id}") private String CLIENT_ID;
    @Value("${naver.client_secret}") private String CLIENT_SECRET;
    @Value("${naver.state}") private String STATE;

    private final NaverAuthClient naverAuthClient;
    private final NaverApiClient naverApiClient;

    public NaverOAuthService(NaverAuthClient naverAuthClient, NaverApiClient naverApiClient) {
        this.naverAuthClient = naverAuthClient;
        this.naverApiClient = naverApiClient;
    }

    @Override
    public TokenData oauth(OAuthRequestData request) {
        String code = request.getCode();

        NaverTokenIssuanceRequestData tokenRequestData = NaverTokenIssuanceRequestData.builder()
                .grant_type(GRANT_TYPE)
                .client_id(CLIENT_ID)
                .client_secret(CLIENT_SECRET)
                .state(STATE)
                .code(code)
                .build();

        NaverTokenIssuanceResponseData tokenResponseData = naverAuthClient.getToken(tokenRequestData);

        System.out.println(tokenResponseData.getAccess_token());

        NaverProfileResponseData profileResponseData = naverApiClient.getProfile("Bearer " + tokenResponseData.getAccess_token());

        return TokenData.builder()
                .tokenId((String) profileResponseData.getResponse().get("id"))
                .accessToken(tokenResponseData.getAccess_token())
                .refreshToken(tokenResponseData.getRefresh_token())
                .email((String) profileResponseData.getResponse().get("email"))
                .name((String) profileResponseData.getResponse().get("name"))
                .build();
    }
}
