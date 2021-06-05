package kr.ac.pcu.cyber.authresourceservice.service;

import kr.ac.pcu.cyber.authresourceservice.client.naver.NaverApiClient;
import kr.ac.pcu.cyber.authresourceservice.client.naver.NaverAuthClient;
import kr.ac.pcu.cyber.authresourceservice.model.dto.OAuthRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.naver.NaverProfileResponseData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.naver.NaverTokenIssuanceRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.naver.NaverTokenIssuanceResponseData;
import kr.ac.pcu.cyber.authresourceservice.model.vo.TokenData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class NaverOAuthServiceTest {

    private final NaverAuthClient naverAuthClient = mock(NaverAuthClient.class);
    private final NaverApiClient naverApiClient = mock(NaverApiClient.class);
    private NaverOAuthService naverOAuthService;

    @BeforeEach
    void setUp() {
        NaverTokenIssuanceResponseData naverTokenIssuanceResponseData = NaverTokenIssuanceResponseData.builder()
                .access_token("access")
                .refresh_token("refresh")
                .token_type("token_type")
                .expires_in("expires_in")
                .build();

        Map<String, Object> response = new HashMap<>();
        response.put("id", "id");
        response.put("email", "email");
        response.put("name", "name");

        NaverProfileResponseData naverProfileResponseData = NaverProfileResponseData.builder()
                .resultcode("resultcode")
                .message("message")
                .response(response)
                .build();

        given(naverAuthClient.getToken(any(NaverTokenIssuanceRequestData.class)))
                .willReturn(naverTokenIssuanceResponseData);

        given(naverApiClient.getProfile(anyString()))
                .willReturn(naverProfileResponseData);

        naverOAuthService = new NaverOAuthService(naverAuthClient, naverApiClient);
    }

    @Test
    void naver_oauth_service() {
        OAuthRequestData oAuthRequestData = OAuthRequestData.builder()
                .code("authorization_code")
                .build();

        TokenData tokenData = naverOAuthService.oauth(oAuthRequestData);

        assertAll(
                () -> assertNotNull(tokenData),
                () -> assertNotNull(tokenData.getTokenId()),
                () -> assertNotNull(tokenData.getAccessToken()),
                () -> assertNotNull(tokenData.getRefreshToken()),
                () -> assertNotNull(tokenData.getEmail()),
                () -> assertNotNull(tokenData.getName()),
                () -> assertNull(tokenData.getProfileImage())
        );
    }

}