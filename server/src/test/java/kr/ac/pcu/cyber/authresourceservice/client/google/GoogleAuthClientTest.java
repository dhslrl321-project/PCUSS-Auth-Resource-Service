package kr.ac.pcu.cyber.authresourceservice.client.google;

import kr.ac.pcu.cyber.authresourceservice.model.dto.google.GoogleTokenIssuanceRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.google.GoogleTokenIssuanceResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GoogleAuthClientTest {

    private final GoogleAuthClient googleAuthClient = mock(GoogleAuthClient.class);

    GoogleTokenIssuanceRequestData googleTokenIssuanceRequestData = GoogleTokenIssuanceRequestData.builder()
            .code("code")
            .grant_type("grant_type")
            .client_id("client_id")
            .client_secret("client_secret")
            .redirect_uri("redirect_uri")
            .build();

    @BeforeEach
    void setUp() {
        GoogleTokenIssuanceResponseData googleTokenIssuanceResponseData = GoogleTokenIssuanceResponseData.builder()
                .access_token("access_token")
                .expires_in("expires_in")
                .refresh_token("refresh_token")
                .scope("scope")
                .token_type("token_type")
                .id_token("id_token")
                .build();

        given(googleAuthClient.getToken(googleTokenIssuanceRequestData))
                .willReturn(googleTokenIssuanceResponseData);
    }

    @Test
    void get_google_token_response_data() {
        GoogleTokenIssuanceResponseData response = googleAuthClient.getToken(googleTokenIssuanceRequestData);

        assertAll(
                () -> assertNotNull(response.getAccess_token()),
                () -> assertNotNull(response.getExpires_in()),
                () -> assertNotNull(response.getRefresh_token()),
                () -> assertNotNull(response.getScope()),
                () -> assertNotNull(response.getToken_type()),
                () -> assertNotNull(response.getId_token())
        );
    }

}