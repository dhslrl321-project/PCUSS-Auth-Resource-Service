package kr.ac.pcu.cyber.authresourceservice.factory;

import kr.ac.pcu.cyber.authresourceservice.common.SocialType;
import kr.ac.pcu.cyber.authresourceservice.service.GoogleOAuthService;
import kr.ac.pcu.cyber.authresourceservice.service.KakaoOAuthService;
import kr.ac.pcu.cyber.authresourceservice.service.NaverOAuthService;
import kr.ac.pcu.cyber.authresourceservice.service.OAuthService;
import org.springframework.stereotype.Component;

@Component
public class OAuthServiceFactory {

    private final KakaoOAuthService kakaoOAuthService;
    private final NaverOAuthService naverOAuthService;
    private final GoogleOAuthService googleOAuthService;

    public OAuthServiceFactory(
            KakaoOAuthService kakaoOAuthService,
            NaverOAuthService naverOAuthService,
            GoogleOAuthService googleOAuthService
    ) {
        this.kakaoOAuthService = kakaoOAuthService;
        this.naverOAuthService = naverOAuthService;
        this.googleOAuthService = googleOAuthService;
    }

    public OAuthService createService(SocialType type) {
        OAuthService service = null;

        switch (type) {
            case KAKAO: {
                service = kakaoOAuthService;
                break;
            }
            case NAVER: {
                service = naverOAuthService;
                break;
            }
            case GOOGLE: {
                service = googleOAuthService;
                break;
            }
            default: {
                break;
            }
        }

        return service;
    }
}
