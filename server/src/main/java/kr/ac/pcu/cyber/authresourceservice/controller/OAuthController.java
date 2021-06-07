package kr.ac.pcu.cyber.authresourceservice.controller;

import kr.ac.pcu.cyber.authresourceservice.common.SocialType;
import kr.ac.pcu.cyber.authresourceservice.model.dto.OAuthRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.OAuthResponseData;
import kr.ac.pcu.cyber.authresourceservice.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/oauth")
public class OAuthController {

    private final TokenService tokenService;

    public OAuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping(value = "/{social_type}", produces = "application/json; charset-utf8")
    public ResponseEntity<OAuthResponseData> oauth(
            @PathVariable("social_type") SocialType type,
            @RequestBody OAuthRequestData requestData,
            HttpServletResponse response
    ) {

        return ResponseEntity.ok(tokenService.oauth(type, requestData, response));
    }
}
