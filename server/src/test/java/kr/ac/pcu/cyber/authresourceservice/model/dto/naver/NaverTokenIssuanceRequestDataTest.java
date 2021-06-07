package kr.ac.pcu.cyber.authresourceservice.model.dto.naver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static org.junit.jupiter.api.Assertions.*;

class NaverTokenIssuanceRequestDataTest {

    @Value("${oauth.grant_type}") private String GRANT_TYPE;
    @Value("${naver.client_id}") private String CLIENT_ID;
    @Value("${naver.client_secret}") private String CLIENT_SECRET;
    @Value("${naver.state}") private String STATE;
    private final String CODE = "authorization_code";
    
    NaverTokenIssuanceRequestData data = NaverTokenIssuanceRequestData.builder()
            .grant_type(GRANT_TYPE)
            .client_id(CLIENT_ID)
            .client_secret(CLIENT_SECRET)
            .state(STATE)
            .code(CODE)
            .build();
    
    @Test
    void valid_value_annotation() {
        assertAll(
                () -> assertEquals(GRANT_TYPE, data.getGrant_type()),
                () -> assertEquals(CLIENT_ID, data.getClient_id()),
                () -> assertEquals(CLIENT_SECRET, data.getClient_secret()),
                () -> assertEquals(STATE, data.getState()),
                () -> assertEquals(CODE, data.getCode())
        );
    }

}