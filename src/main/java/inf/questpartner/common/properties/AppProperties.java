package inf.questpartner.common.properties;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@RequiredArgsConstructor
//@Configuration
//@ConstructorBinding
@ConfigurationProperties("external-certification")
public class AppProperties {

    private final String emailFromAddress;
//    private final String coolSmsKey;
//    private final String coolSmsSecret;
//    private final String coolSmsFromPhoneNumber;
}
