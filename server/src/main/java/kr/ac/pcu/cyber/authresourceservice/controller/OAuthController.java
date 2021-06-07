package kr.ac.pcu.cyber.authresourceservice.controller;

import kr.ac.pcu.cyber.authresourceservice.common.SocialType;
import kr.ac.pcu.cyber.authresourceservice.model.dto.OAuthRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.OAuthResponseData;
import kr.ac.pcu.cyber.authresourceservice.service.TokenService;
import org.apache.http.cookie.Cookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    private final TokenService tokenService;

    public OAuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/{social_type}")
    public ResponseEntity<OAuthResponseData> oauth(
            @PathVariable("social_type") SocialType type,
            OAuthRequestData requestData,
            HttpServletResponse response
    ) {

        return ResponseEntity.ok(tokenService.oauth(type, requestData, response));
    }
}
