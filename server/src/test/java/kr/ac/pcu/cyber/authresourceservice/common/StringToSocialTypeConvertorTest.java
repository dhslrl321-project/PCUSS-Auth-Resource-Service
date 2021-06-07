package kr.ac.pcu.cyber.authresourceservice.common;

import kr.ac.pcu.cyber.authresourceservice.config.StringToSocialTypeConvertor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class StringToSocialTypeConvertorTest {

    StringToSocialTypeConvertor stringToSocialTypeConvertor = new StringToSocialTypeConvertor();

    @Test
    void valid_kakao_social_type_to_string_convertor() {
        SocialType source = stringToSocialTypeConvertor.convert("kakao");

        assertEquals(source, SocialType.KAKAO);
    }

    @Test
    void valid_google_social_type_to_string_convertor() {
        SocialType source = stringToSocialTypeConvertor.convert("google");

        assertEquals(source, SocialType.GOOGLE);
    }

    @Test
    void valid_naver_social_type_to_string_convertor() {
        SocialType source = stringToSocialTypeConvertor.convert("naver");

        assertEquals(source, SocialType.NAVER);
    }

    @Test
    void invalid_social_type_to_string_convertor() {
        try {
            SocialType socialType = stringToSocialTypeConvertor.convert("someString");
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}