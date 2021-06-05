package kr.ac.pcu.cyber.authresourceservice.client;

import kr.ac.pcu.cyber.authresourceservice.model.dto.UserRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.UserResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(
        name = "user-service"
)
public interface UserServiceClient {

    @GetMapping(value = "/login/{uuid}", consumes = "application/x-www-form-urlencoded")
    UserResponseData login(@PathVariable("uuid") String uuid);

    @PostMapping(value = "/register", consumes = "application/x-www-form-urlencoded")
    UserResponseData register(@RequestBody UserRequestData requestData);
}
