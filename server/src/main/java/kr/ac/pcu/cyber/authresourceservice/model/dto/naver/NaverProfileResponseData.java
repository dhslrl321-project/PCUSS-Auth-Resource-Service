package kr.ac.pcu.cyber.authresourceservice.model.dto.naver;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NaverProfileResponseData {
    private String resultcode;
    private String message;
    private Map<String, Object> response;
}
