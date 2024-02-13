package inf.questpartner.util.validation.argumentResolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;

/**
 * 현재 로그인된 USER의 email 정보 가져오기
 */
@Retention(RUNTIME)
@Target(ElementType.PARAMETER)
public @interface CurrentUser {
}
