package kr.ac.pcu.cyber.authresourceservice.service;

import kr.ac.pcu.cyber.authresourceservice.client.google.GoogleApiClient;
import kr.ac.pcu.cyber.authresourceservice.client.google.GoogleAuthClient;
import kr.ac.pcu.cyber.authresourceservice.model.dto.OAuthRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.google.GoogleProfileResponseData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.google.GoogleTokenIssuanceRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.google.GoogleTokenIssuanceResponseData;
import kr.ac.pcu.cyber.authresourceservice.model.vo.TokenData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GoogleOAuthServiceTest {

    private final GoogleAuthClient googleAuthClient = mock(GoogleAuthClient.class);
    private final GoogleApiClient googleApiClient = mock(GoogleApiClient.class);
    private GoogleOAuthService googleOAuthService;

    @BeforeEach
    void setUp() {
        GoogleTokenIssuanceResponseData googleTokenIssuanceResponseData = GoogleTokenIssuanceResponseData.builder()
                .access_token("access")
                .expires_in("expires_in")
                .refresh_token("refresh")
                .scope("scope")
                .id_token("id_token")
                .token_type("token_type")
                .build();

        GoogleProfileResponseData googleProfileResponseData = GoogleProfileResponseData.builder()
                .id("id")
                .email("email")
                .verified_email(true)
                .name("name")
                .given_name("given_name")
                .family_name("family_name")
                .picture("picture")
                .locale("locale")
                .build();

        given(googleAuthClient.getToken(any(GoogleTokenIssuanceRequestData.class)))
                .willReturn(googleTokenIssuanceResponseData);

        given(googleApiClient.getProfile(anyString()))
                .willReturn(googleProfileResponseData);

        googleOAuthService = new GoogleOAuthService(googleAuthClient, googleApiClient);
    }

    @Test
    void google_oauth_service() {
        OAuthRequestData oAuthRequestData = OAuthRequestData.builder()
                .code("authorization_code")
                .build();

        TokenData tokenData = googleOAuthService.oauth(oAuthRequestData);

        assertAll(
                () -> assertNotNull(tokenData),
                () -> assertNotNull(tokenData.getTokenId()),
                () -> assertNotNull(tokenData.getAccessToken()),
                () -> assertNotNull(tokenData.getRefreshToken()),
                () -> assertNotNull(tokenData.getEmail()),
                () -> assertNotNull(tokenData.getName()),
                () -> assertNotNull(tokenData.getProfileImage())
        );
    }

}