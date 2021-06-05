package kr.ac.pcu.cyber.authresourceservice.service;

import kr.ac.pcu.cyber.authresourceservice.client.UserServiceClient;
import kr.ac.pcu.cyber.authresourceservice.client.google.GoogleApiClient;
import kr.ac.pcu.cyber.authresourceservice.client.google.GoogleAuthClient;
import kr.ac.pcu.cyber.authresourceservice.client.kakao.KakaoApiClient;
import kr.ac.pcu.cyber.authresourceservice.client.kakao.KakaoAuthClient;
import kr.ac.pcu.cyber.authresourceservice.client.naver.NaverApiClient;
import kr.ac.pcu.cyber.authresourceservice.client.naver.NaverAuthClient;
import kr.ac.pcu.cyber.authresourceservice.factory.OAuthServiceFactory;
import kr.ac.pcu.cyber.authresourceservice.model.dto.UserRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.UserResponseData;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class TokenServiceTest {

    private final KakaoAuthClient kakaoAuthClient = mock(KakaoAuthClient.class);
    private final KakaoApiClient kakaoApiClient = mock(KakaoApiClient.class);

    private final NaverAuthClient naverAuthClient = mock(NaverAuthClient.class);
    private final NaverApiClient naverApiClient = mock(NaverApiClient.class);

    private final GoogleAuthClient googleAuthClient = mock(GoogleAuthClient.class);
    private final GoogleApiClient googleApiClient = mock(GoogleApiClient.class);

    private final UserServiceClient userServiceClient = mock(UserServiceClient.class);
    private TokenService tokenService;


    @BeforeEach
    void setUp() {
        KakaoOAuthService kakaoOAuthService = new KakaoOAuthService(kakaoAuthClient, kakaoApiClient);
        NaverOAuthService naverOAuthService = new NaverOAuthService(naverAuthClient, naverApiClient);
        GoogleOAuthService googleOAuthService = new GoogleOAuthService(googleAuthClient, googleApiClient);

        OAuthServiceFactory oAuthServiceFactory = new OAuthServiceFactory(kakaoOAuthService, naverOAuthService, googleOAuthService);

        UserResponseData loginResponse = UserResponseData.builder()
                .id(1L)
                .userId("userid")
                .accessToken("access")
                .refreshToken("refresh")
                .nickname("nickname")
                .profileUrl("profileurl")
                .build();

        UserResponseData registerResponse = UserResponseData.builder()
                .id(1L)
                .userId("userid")
                .accessToken("access")
                .refreshToken("refresh")
                .nickname("nickname")
                .profileUrl("profileurl")
                .build();

        given(userServiceClient.login(anyString())).willReturn(loginResponse);

        given(userServiceClient.register(any(UserRequestData.class))).willReturn(registerResponse);

        tokenService = new TokenService(oAuthServiceFactory, userServiceClient);
    }
}