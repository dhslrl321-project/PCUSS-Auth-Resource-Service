package kr.ac.pcu.cyber.authresourceservice.config;

import feign.codec.Encoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;

import static org.junit.jupiter.api.Assertions.*;

class FeignConfigTest {

    private ObjectFactory<HttpMessageConverters> messageConverters = new ObjectFactory<>() {
        @Override
        public HttpMessageConverters getObject() throws BeansException {
            return new HttpMessageConverters();
        }
    };

    private FeignConfig feignConfig;

    @Test
    void constructor_with_ObjectFactory() {
        try {
            feignConfig = new FeignConfig(messageConverters);
        } catch(Exception e) {
            fail();
        }
    }


}