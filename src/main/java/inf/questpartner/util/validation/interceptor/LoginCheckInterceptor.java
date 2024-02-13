package inf.questpartner.util.validation.interceptor;


import inf.questpartner.domain.users.common.UserLevel;
import inf.questpartner.service.SessionLoginService;
import inf.questpartner.util.constant.SessionConst;
import inf.questpartner.util.exception.users.NotAuthorizedException;
import inf.questpartner.util.exception.users.UnauthenticatedUserException;
import inf.questpartner.util.validation.argumentResolver.Login;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 이 인터셉터는 사용자 로그인 여부를 확인하고, 미인증된 사용자의 요청을 처리합니다.
 */
@Slf4j
@RequiredArgsConstructor
public class LoginCheckInterceptor implements HandlerInterceptor {

    private final SessionLoginService sessionLoginService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Login loginCheck = handlerMethod.getMethodAnnotation(Login.class);

            if (loginCheck != null) {
                if (sessionLoginService.retrieveLoginUser() == null) {
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
        }

        String requestURI = request.getRequestURI();
        log.info("인증체크 인터셉터 실행{}", requestURI);

        HttpSession session = request.getSession();

        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자 요청");
            response.sendRedirect("/users/login");
            return false;
        }

        return true;
    }

    private void authUserLevel() {
        UserLevel auth = sessionLoginService.retrieveUserLevel();
        if (auth == UserLevel.UNAUTH) {
            throw new NotAuthorizedException("해당 리소스에 대한 접근 권한이 존재하지 않습니다.");
        }
    }

    /**
     * 현재 회원의 권한이 ADMIN인지 확인한다.
     */
    private void adminUserLevel() {
        UserLevel auth = sessionLoginService.retrieveUserLevel();
        if (auth != UserLevel.ADMIN) {
            throw new NotAuthorizedException("해당 리소스에 대한 접근 권한이 존재하지 않습니다.");
        }
    }
}
