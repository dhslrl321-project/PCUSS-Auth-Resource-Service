package kr.ac.pcu.cyber.authresourceservice.config;

import kr.ac.pcu.cyber.authresourceservice.common.SocialType;
import org.springframework.core.convert.converter.Converter;

public class StringToSocialTypeConvertor implements Converter<String, SocialType> {
    @Override
    public SocialType convert(String source) {
        return SocialType.valueOf(source.toUpperCase());
    }
}
