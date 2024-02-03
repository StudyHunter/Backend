package inf.questpartner.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @CurrentUser : 현재 로그인된 USER의 email(아이디)를 가져온다.
 */

@Retention(RUNTIME)
@Target(PARAMETER)
public @interface CurrentUser {

}
