package kr.ac.pcu.cyber.authresourceservice.config;

import kr.ac.pcu.cyber.authresourceservice.common.SocialType;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.FormattingConversionService;


import static org.junit.jupiter.api.Assertions.*;

class WebConfigTest {
    private final WebConfig webConfig = new WebConfig();

    @Test
    void add_custom_convertor_test() {
        FormattingConversionService formattingConversionService = webConfig.mvcConversionService();

        assertTrue(formattingConversionService.canConvert(SocialType.class, String.class));
    }
}