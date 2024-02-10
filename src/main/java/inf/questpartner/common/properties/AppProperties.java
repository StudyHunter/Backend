package inf.questpartner.common.properties;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Getter
@Configuration
public class AppProperties {

    private final String emailFromAddress;

//    이메일 주소 단일값만 가져오므로 Value로 수정.
    public AppProperties(@Value("${external-certification.email-from-address}") String emailFromAddress) {
        this.emailFromAddress = emailFromAddress;
    }
}
