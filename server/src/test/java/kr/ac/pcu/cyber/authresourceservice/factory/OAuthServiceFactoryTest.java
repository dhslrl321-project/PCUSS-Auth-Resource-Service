package kr.ac.pcu.cyber.authresourceservice.factory;

import kr.ac.pcu.cyber.authresourceservice.client.google.GoogleApiClient;
import kr.ac.pcu.cyber.authresourceservice.client.google.GoogleAuthClient;
import kr.ac.pcu.cyber.authresourceservice.client.kakao.KakaoApiClient;
import kr.ac.pcu.cyber.authresourceservice.client.kakao.KakaoAuthClient;
import kr.ac.pcu.cyber.authresourceservice.client.naver.NaverApiClient;
import kr.ac.pcu.cyber.authresourceservice.client.naver.NaverAuthClient;
import kr.ac.pcu.cyber.authresourceservice.common.SocialType;
import kr.ac.pcu.cyber.authresourceservice.service.GoogleOAuthService;
import kr.ac.pcu.cyber.authresourceservice.service.KakaoOAuthService;
import kr.ac.pcu.cyber.authresourceservice.service.NaverOAuthService;
import kr.ac.pcu.cyber.authresourceservice.service.OAuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class OAuthServiceFactoryTest {

    private final KakaoAuthClient kakaoAuthClient = mock(KakaoAuthClient.class);
    private final KakaoApiClient kakaoApiClient = mock(KakaoApiClient.class);

    private final NaverAuthClient naverAuthClient = mock(NaverAuthClient.class);
    private final NaverApiClient naverApiClient = mock(NaverApiClient.class);

    private final GoogleAuthClient googleAuthClient = mock(GoogleAuthClient.class);
    private final GoogleApiClient googleApiClient = mock(GoogleApiClient.class);

    private OAuthServiceFactory factory;

    @BeforeEach
    void setUp() {
        KakaoOAuthService kakaoOAuthService = new KakaoOAuthService(kakaoAuthClient, kakaoApiClient);
        NaverOAuthService naverOAuthService = new NaverOAuthService(naverAuthClient, naverApiClient);
        GoogleOAuthService googleOAuthService = new GoogleOAuthService(googleAuthClient, googleApiClient);

        factory = new OAuthServiceFactory(kakaoOAuthService, naverOAuthService, googleOAuthService);
    }

    @Test
    void create_kakao_service() {
        OAuthService oAuthService = factory.createService(SocialType.KAKAO);

        assertEquals(KakaoOAuthService.class, oAuthService.getClass());
    }

    @Test
    void create_naver_service() {
        OAuthService oAuthService = factory.createService(SocialType.NAVER);

        assertEquals(NaverOAuthService.class, oAuthService.getClass());
    }

    @Test
    void create_google_service() {
        OAuthService oAuthService = factory.createService(SocialType.GOOGLE);

        assertEquals(GoogleOAuthService.class, oAuthService.getClass());
    }
}