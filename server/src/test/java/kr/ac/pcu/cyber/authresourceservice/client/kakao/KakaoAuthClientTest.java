package kr.ac.pcu.cyber.authresourceservice.client.kakao;

import kr.ac.pcu.cyber.authresourceservice.model.dto.kakao.KakaoTokenIssuanceRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.kakao.KakaoTokenIssuanceResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class KakaoAuthClientTest {

    private final KakaoAuthClient kakaoAuthClient = mock(KakaoAuthClient.class);

    KakaoTokenIssuanceRequestData kakaoTokenIssuanceRequestData = KakaoTokenIssuanceRequestData.builder()
            .code("code")
            .client_id("client_id")
            .client_secret("client_secret")
            .redirect_uri("redirect_uri")
            .grant_type("grant_type")
            .build();

    @BeforeEach
    void setUp() {
        KakaoTokenIssuanceResponseData kakaoTokenIssuanceResponseData = KakaoTokenIssuanceResponseData.builder()
                .token_type("token_type")
                .access_token("access_token")
                .expires_in(1)
                .refresh_token("refresh_token")
                .refresh_token_expires_in(1)
                .scope("scope")
                .build();

        given(kakaoAuthClient.getToken(kakaoTokenIssuanceRequestData))
                .willReturn(kakaoTokenIssuanceResponseData);
    }

    @Test
    void get_kakao_token_response_data() {
        KakaoTokenIssuanceResponseData response = kakaoAuthClient.getToken(kakaoTokenIssuanceRequestData);

        assertAll(
                () -> assertNotNull(response.getToken_type()),
                () -> assertNotNull(response.getAccess_token()),
                () -> assertNotNull(response.getExpires_in()),
                () -> assertNotNull(response.getRefresh_token()),
                () -> assertNotNull(response.getRefresh_token_expires_in()),
                () -> assertNotNull(response.getScope())
        );
    }
}