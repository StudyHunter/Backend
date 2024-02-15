package inf.questpartner.config;

import inf.questpartner.util.validation.argumentResolver.LoginUserArgumentResolver;
import inf.questpartner.util.validation.interceptor.LoginCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/*
 예시
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final LoginCheckInterceptor loginCheckInterceptor;
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .excludePathPatterns(
                        "/users/signup/**",
                        "/users/email-check-token",
                        "/resend-email-token",
                        "/users/login",
                        "/users/forget/password",
                        "/users/find",
                        "/favicon.ico",
                        "/error",
                        "/"
                );
    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver);
    }
}
