//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.dingding.config;

import com.example.dingding.dto.DingResult;
import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Feign 统一响应解码器。
 * 针对返回结构为 DingResult<T> 的接口自动解包，并在 errcode 非 0 时抛异常。
 */
public class FeignResponseDecoder {
    public FeignResponseDecoder() {
    }

    @Bean
    public Decoder feignDecoder(ObjectProvider<HttpMessageConverters> messageConverters) {
        SpringDecoder decoder = new SpringDecoder(messageConverters);
        return new OptionalDecoder(new ResponseEntityDecoder((response, type) -> {
            Method method = response.request().requestTemplate().methodMetadata().method();
            boolean notTheSame = method.getReturnType() != DingResult.class;

            if (notTheSame) {
                // 非 DingResult 返回值的接口，也按 DingResult<T> 协议先解码后解包。
                Type newType = new ParameterizedType() {
                    public Type[] getActualTypeArguments() {
                        return new Type[]{type};
                    }

                    public Type getRawType() {
                        return DingResult.class;
                    }

                    public Type getOwnerType() {
                        return null;
                    }
                };
                DingResult<?> result = (DingResult) decoder.decode(response, newType);
                if (result.getErrcode() != 0) {
                    // 钉钉业务错误统一转运行时异常，交由上层处理。
                    throw new RuntimeException(String.format("errcode:%s, errmsg:%s", result.getErrcode(), result.getErrmsg()));
                }
                return result.getResult();
            } else {
                return decoder.decode(response, type);
            }
        }));
    }
}
