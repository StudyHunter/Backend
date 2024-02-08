package inf.questpartner.util.validation.interceptor;

import inf.questpartner.common.annotation.LoginCheck;
import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.service.SessionLoginService;
import inf.questpartner.util.exception.users.NotAuthorizedException;
import inf.questpartner.util.exception.users.UnauthenticatedUserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 예시
 */
@Component
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final SessionLoginService sessionLoginService;

    @Autowired
//    @inject 변경
    private Environment environment;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler)
            throws Exception {

        // test 환경에서는 인터셉트가 동작하지 않도록 설정
        String[] activeProfiles = environment.getActiveProfiles();
        if ("test".equals(activeProfiles[0])) {
            return true;
        }

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);

            if (loginCheck == null) {
                return true;
            }

            if (sessionLoginService.getLoginUser() == null) {
                throw new UnauthenticatedUserException("로그인 후 이용 가능합니다.");
            }

            UserLevel auth = loginCheck.authority();


            switch (auth) {
                case ADMIN:
                    adminUserLevel();
                    break;

                case AUTH:
                    authUserLevel();
                    break;
            }
        }
        return true;
    }

    /**
     * 현재 USER의 권한(UserLevel)이 AUTH인지 확인한다. 해당 리소스는 ADMIN과 AUTH만 접근 가능하다. 따라서 UNAUTH인 경우에만 제한한다.
     */
    private void authUserLevel() {
        UserLevel auth = sessionLoginService.getUserLevel();
        if (auth == UserLevel.UNAUTH) {
            throw new NotAuthorizedException("해당 리소스에 대한 접근 권한이 존재하지 않습니다.");

        }
    }

    /**
     * 현재 USER의 권한(UserLevel)이 ADMIN인지 확인한다. ADMIN이 아니라면 해당 요청을 수행할 수 없다.
     */
    private void adminUserLevel() {
        UserLevel auth = sessionLoginService.getUserLevel();
        if (auth != UserLevel.ADMIN) {
            throw new NotAuthorizedException("해당 리소스에 대한 접근 권한이 존재하지 않습니다.");
        }
    }
}
