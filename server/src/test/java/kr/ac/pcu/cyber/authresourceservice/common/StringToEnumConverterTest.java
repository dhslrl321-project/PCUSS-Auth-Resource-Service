package kr.ac.pcu.cyber.authresourceservice.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class StringToEnumConverterTest {

    StringToEnumConverter stringToEnumConverter = new StringToEnumConverter();

    @Test
    void valid_kakao_social_type_to_string_convertor() {
        SocialType source = stringToEnumConverter.convert("kakao");

        assertEquals(source, SocialType.KAKAO);
    }

    @Test
    void valid_google_social_type_to_string_convertor() {
        SocialType source = stringToEnumConverter.convert("google");

        assertEquals(source, SocialType.GOOGLE);
    }

    @Test
    void valid_naver_social_type_to_string_convertor() {
        SocialType source = stringToEnumConverter.convert("naver");

        assertEquals(source, SocialType.NAVER);
    }

    @Test
    void invalid_social_type_to_string_convertor() {
        try {
            SocialType socialType = stringToEnumConverter.convert("someString");
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }
}