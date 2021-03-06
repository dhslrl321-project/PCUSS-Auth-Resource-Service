package kr.ac.pcu.cyber.authresourceservice.client.google;

import kr.ac.pcu.cyber.authresourceservice.model.dto.google.GoogleTokenIssuanceRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.google.GoogleTokenIssuanceResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(
        name = "google-oauth",
        url = "https://oauth2.googleapis.com"
)
public interface GoogleAuthClient {

    @PostMapping(value = "/token", consumes = "application/x-www-form-urlencoded")
    GoogleTokenIssuanceResponseData getToken(@RequestBody GoogleTokenIssuanceRequestData request);

}
