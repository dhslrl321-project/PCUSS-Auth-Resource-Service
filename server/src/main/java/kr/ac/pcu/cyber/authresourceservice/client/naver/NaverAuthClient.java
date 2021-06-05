package kr.ac.pcu.cyber.authresourceservice.client.naver;

import kr.ac.pcu.cyber.authresourceservice.model.dto.naver.NaverTokenIssuanceRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.naver.NaverTokenIssuanceResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(
        name = "naver-auth",
        url = "https://nid.naver.com/oauth2.0"
)
public interface NaverAuthClient {

    @PostMapping(value = "/token", consumes = "application/x-www-form-urlencoded")
    NaverTokenIssuanceResponseData getToken(@RequestBody NaverTokenIssuanceRequestData request);
}
