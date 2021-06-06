package kr.ac.pcu.cyber.authresourceservice.client.kakao;

import kr.ac.pcu.cyber.authresourceservice.model.dto.kakao.KakaoProfileResponseData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class KakaoApiClientTest {

    private final KakaoApiClient kakaoApiClient = mock(KakaoApiClient.class);

    @BeforeEach
    void setUp() {
        Map<String, Object> properties = new HashMap<>();
        Map<String, Object> kakao_account = new HashMap<>();

        KakaoProfileResponseData kakaoProfileResponseData = KakaoProfileResponseData.builder()
                .id(1L)
                .connected_at("connected_at")
                .properties(properties)
                .kakao_account(kakao_account)
                .build();

        given(kakaoApiClient.getProfile(anyString()))
                .willReturn(kakaoProfileResponseData);
    }

    @Test
    void get_kakao_profile_response_data() {
        KakaoProfileResponseData response = kakaoApiClient.getProfile("Bearer Token");

        assertAll(
                () -> assertNotNull(response.getId()),
                () -> assertNotNull(response.getConnected_at()),
                () -> assertNotNull(response.getProperties()),
                () -> assertNotNull(response.getKakao_account())
        );
    }

}