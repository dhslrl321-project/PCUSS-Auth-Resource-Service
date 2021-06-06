package kr.ac.pcu.cyber.authresourceservice.config;

import kr.ac.pcu.cyber.authresourceservice.common.StringToEnumConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    
    @Override
    public FormattingConversionService mvcConversionService() {
        FormattingConversionService f = super.mvcConversionService();
        f.addConverter(new StringToEnumConverter());
        return f;
    }
}
