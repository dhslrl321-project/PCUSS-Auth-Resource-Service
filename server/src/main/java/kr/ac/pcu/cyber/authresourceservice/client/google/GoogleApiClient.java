package kr.ac.pcu.cyber.authresourceservice.client.google;

import kr.ac.pcu.cyber.authresourceservice.model.dto.google.GoogleProfileResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(
        name = "google-api",
        url = "https://www.googleapis.com"
)
public interface GoogleApiClient {

    @GetMapping(value = "/oauth2/v1/userinfo")
    GoogleProfileResponseData getProfile(@RequestHeader("Authorization") String authorization);

}
