package inf.questpartner.common.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppProperties {

    private final String emailFromAddress;

//    이메일 주소 단일값만 가져오므로 Value로 수정.
    public AppProperties(@Value("${external-certification.email-from-address}") String emailFromAddress) {
        this.emailFromAddress = emailFromAddress;
    }
}
