package kr.ac.pcu.cyber.authresourceservice.model.dto.google;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.*;

class GoogleTokenIssuanceRequestDataTest {

    @Value("${oauth.grant_type}") private String GRANT_TYPE;
    @Value("${google.client_id}") private String CLIENT_ID;
    @Value("${google.client_secret}") private String CLIENT_SECRET;
    @Value("${google.redirect_uri}") private String REDIRECT_URI;
    private final String CODE = "authorization_code";

    GoogleTokenIssuanceRequestData data = GoogleTokenIssuanceRequestData.builder()
            .grant_type(GRANT_TYPE)
            .client_id(CLIENT_ID)
            .client_secret(CLIENT_SECRET)
            .redirect_uri(REDIRECT_URI)
            .code(CODE)
            .build();

    @Test
    void valid_value_annotation() {
        assertAll(
                () -> assertEquals(GRANT_TYPE, data.getGrant_type()),
                () -> assertEquals(CLIENT_ID, data.getClient_id()),
                () -> assertEquals(CLIENT_SECRET, data.getClient_secret()),
                () -> assertEquals(REDIRECT_URI, data.getRedirect_uri()),
                () -> assertEquals(CODE, data.getCode())
        );
    }
}