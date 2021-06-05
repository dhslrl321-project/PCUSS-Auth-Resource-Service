package kr.ac.pcu.cyber.authresourceservice.client.kakao;

import kr.ac.pcu.cyber.authresourceservice.model.dto.kakao.KakaoTokenIssuanceRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.kakao.KakaoTokenIssuanceResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(
        name = "kakao-auth",
        url = "https://kauth.kakao.com"
)
public interface KakaoAuthClient {

    @PostMapping(value = "/oauth/token", consumes = "application/x-www-form-urlencoded")
    KakaoTokenIssuanceResponseData getToken(@RequestBody KakaoTokenIssuanceRequestData requestData);

}
