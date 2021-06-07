package kr.ac.pcu.cyber.authresourceservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.pcu.cyber.authresourceservice.common.SocialType;
import kr.ac.pcu.cyber.authresourceservice.model.dto.OAuthRequestData;
import kr.ac.pcu.cyber.authresourceservice.model.dto.OAuthResponseData;
import kr.ac.pcu.cyber.authresourceservice.service.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OAuthController.class)
class OAuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        OAuthResponseData oAuthResponseData = OAuthResponseData.builder()
                .id(1L)
                .nickname("nickname")
                .profileImage("profileImage")
                .build();

        given(tokenService.oauth(any(SocialType.class), any(OAuthRequestData.class), any(HttpServletResponse.class)))
                .willReturn(oAuthResponseData);
    }

    @Test
    void kakao_oauth_request() throws Exception {
        OAuthRequestData requestData = OAuthRequestData.builder()
                .code("testCode")
                .build();

        mockMvc.perform(post("/oauth/{social_type}", SocialType.KAKAO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("nickname").exists())
                .andExpect(jsonPath("profileImage").exists());
    }

    @Test
    void naver_oauth_request() throws Exception {
        OAuthRequestData requestData = OAuthRequestData.builder()
                .code("testCode")
                .build();

        mockMvc.perform(post("/oauth/{social_type}", SocialType.NAVER)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("nickname").exists())
                .andExpect(jsonPath("profileImage").exists());
    }

    @Test
    void google_oauth_request() throws Exception {
        OAuthRequestData requestData = OAuthRequestData.builder()
                .code("testCode")
                .build();

        mockMvc.perform(post("/oauth/{social_type}", SocialType.GOOGLE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestData)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("nickname").exists())
                .andExpect(jsonPath("profileImage").exists());
    }

    @Test
    void wrong_path_oauth_request() throws Exception {
        OAuthRequestData requestData = OAuthRequestData.builder()
                .code("testCode")
                .build();

        mockMvc.perform(post("/oauth/{social_type}", "anyString")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestData)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}