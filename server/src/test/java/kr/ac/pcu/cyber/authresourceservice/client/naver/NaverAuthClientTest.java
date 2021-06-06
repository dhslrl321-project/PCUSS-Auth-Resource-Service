package kr.ac.pcu.cyber.authresourceservice.client.naver;

import kr.ac.pcu.cyber.authresourceservice.model.dto.naver.NaverTokenIssuanceRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.naver.NaverTokenIssuanceResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class NaverAuthClientTest {

    private final NaverAuthClient naverAuthClient = mock(NaverAuthClient.class);

    NaverTokenIssuanceRequestData naverTokenIssuanceRequestData = NaverTokenIssuanceRequestData.builder()
            .grant_type("grant_type")
            .client_id("client_id")
            .client_secret("client_secret")
            .code("code")
            .state("state")
            .build();

    @BeforeEach
    void setUp() {
        NaverTokenIssuanceResponseData naverTokenIssuanceResponseData = NaverTokenIssuanceResponseData.builder()
                .access_token("access_token")
                .refresh_token("refresh_token")
                .token_type("token_type")
                .expires_in("expires_in")
                .build();

        given(naverAuthClient.getToken(naverTokenIssuanceRequestData))
                .willReturn(naverTokenIssuanceResponseData);
    }

    @Test
    void get_naver_token_response_data() {
        NaverTokenIssuanceResponseData response = naverAuthClient.getToken(naverTokenIssuanceRequestData);

        assertAll(
                () -> assertNotNull(response.getAccess_token()),
                () -> assertNotNull(response.getRefresh_token()),
                () -> assertNotNull(response.getToken_type()),
                () -> assertNotNull(response.getExpires_in())
        );
    }

}