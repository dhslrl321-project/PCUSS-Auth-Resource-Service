package kr.ac.pcu.cyber.authresourceservice.client.naver;

import kr.ac.pcu.cyber.authresourceservice.model.dto.naver.NaverProfileResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class NaverApiClientTest {

    private final NaverApiClient naverApiClient = mock(NaverApiClient.class);

    @BeforeEach
    void setUp() {
        Map<String, Object> response = new HashMap<>();

        NaverProfileResponseData naverProfileResponseData = NaverProfileResponseData.builder()
                .resultcode("resultcode")
                .message("message")
                .response(response)
                .build();

        given(naverApiClient.getProfile(anyString()))
                .willReturn(naverProfileResponseData);
    }

    @Test
    void get_naver_profile_response_data() {
        NaverProfileResponseData response = naverApiClient.getProfile("Bearer Token");

        assertAll(
                () -> assertNotNull(response.getResultcode()),
                () -> assertNotNull(response.getMessage()),
                () -> assertNotNull(response.getResponse())
        );
    }

}