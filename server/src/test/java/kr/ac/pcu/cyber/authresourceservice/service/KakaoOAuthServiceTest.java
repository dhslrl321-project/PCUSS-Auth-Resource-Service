package kr.ac.pcu.cyber.authresourceservice.service;

import kr.ac.pcu.cyber.authresourceservice.client.kakao.KakaoApiClient;
import kr.ac.pcu.cyber.authresourceservice.client.kakao.KakaoAuthClient;
import kr.ac.pcu.cyber.authresourceservice.model.dto.OAuthRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.kakao.KakaoProfileResponseData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.kakao.KakaoTokenIssuanceRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.kakao.KakaoTokenIssuanceResponseData;
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

class KakaoOAuthServiceTest {

    private final KakaoAuthClient kakaoAuthClient = mock(KakaoAuthClient.class);
    private final KakaoApiClient kakaoApiClient = mock(KakaoApiClient.class);
    private KakaoOAuthService kakaoOAuthService;

    @BeforeEach
    void setUp() {
        KakaoTokenIssuanceResponseData kakaoTokenIssuanceResponseData = KakaoTokenIssuanceResponseData.builder()
                .access_token("access")
                .refresh_token("refresh")
                .expires_in(1)
                .refresh_token_expires_in(2)
                .scope("scope")
                .token_type("token_type")
                .build();
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> kakao_account = new HashMap<>();

        properties.put("nickname", "nickname");
        kakao_account.put("email", "email");

        KakaoProfileResponseData kakaoProfileResponseData = KakaoProfileResponseData.builder()
                .id(1L)
                .connected_at("connected_at")
                .properties(properties)
                .kakao_account(kakao_account)
                .build();

        given(kakaoAuthClient.getToken(any(KakaoTokenIssuanceRequestData.class)))
                .willReturn(kakaoTokenIssuanceResponseData);

        given(kakaoApiClient.getProfile(anyString()))
                .willReturn(kakaoProfileResponseData);

        kakaoOAuthService = new KakaoOAuthService(kakaoAuthClient, kakaoApiClient);
    }

    @Test
    void kakao_oauth_service() {
        OAuthRequestData oAuthRequestData = OAuthRequestData.builder()
                .code("authorization_code")
                .build();

        TokenData tokenData = kakaoOAuthService.oauth(oAuthRequestData);

        assertAll(
                () -> assertNotNull(tokenData),
                () -> assertNotNull(tokenData.getTokenId()),
                () -> assertNotNull(tokenData.getAccessToken()),
                () -> assertNotNull(tokenData.getRefreshToken()),
                () -> assertNotNull(tokenData.getEmail()),
                () -> assertNotNull(tokenData.getName())
        );
    }
}