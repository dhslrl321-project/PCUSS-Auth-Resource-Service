package kr.ac.pcu.cyber.authresourceservice.client.google;

import kr.ac.pcu.cyber.authresourceservice.model.dto.google.GoogleProfileResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GoogleApiClientTest {

    private final GoogleApiClient googleApiClient = mock(GoogleApiClient.class);

    @BeforeEach
    void setUp() {
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

        given(googleApiClient.getProfile(anyString()))
                .willReturn(googleProfileResponseData);
    }

    @Test
    void get_google_profile_response_data() {
        GoogleProfileResponseData response = googleApiClient.getProfile("Bearer Token");

        assertAll(
                () -> assertNotNull(response.getId()),
                () -> assertNotNull(response.getEmail()),
                () -> assertNotNull(response.getVerified_email()),
                () -> assertNotNull(response.getName()),
                () -> assertNotNull(response.getGiven_name()),
                () -> assertNotNull(response.getFamily_name()),
                () -> assertNotNull(response.getPicture()),
                () -> assertNotNull(response.getLocale())
        );
    }
}