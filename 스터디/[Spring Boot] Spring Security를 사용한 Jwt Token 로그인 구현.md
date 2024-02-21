### 📚 목차
목차는 다음과 같다.

+ JWT(JSON Web Token) 소개
+ 라이브러리 추가
+ spring security, JWT 구현
+ Spring Boot 3.x 이상의 Security Config 코드 변경 사항
+ REST API 구현
+ 브라우저 확인 및 로그 확인
+ ajax를 사용하여 로그인 정보 불러오기
+ 참고자료


# JWT(JSON Web Token) 소개
+ 세션, 쿠키의 로그인 방식과 JWT의 가장 큰 차이점은 중 하나는 <br> JWT 방식에서는 서버는 클라이언트의 상태를 완전히 저장하지 않는 무상태성(Stateless)을 유지할 수 있다는 점이다.
+ 사용자가 로그인 시에 암호화 방식(ex: 공개키, 비밀키 방식인 RSA)으로 클라이언트에게 암호화된 토큰을 전달하게 되는데, <br> 해당 토큰을 다시 서버에 전달해서 토큰을 복호화해서 풀어내는 방식을 사용하기 때문에 <br> 서버가 클라이언트의 상태를 저장하고 있지 않고 단순히 복호화만 하기 때문에 무상태성을 유지할 수 있다.
> 세션을 사용하는 로그인 방식은 서버 메모리에 사용자 세션 값을 들고있으므로 <br> (서버가 아니라 DB에 넣더라도) 무상태성이라고 볼 수 없다.

<br>

우리는 CSR(Client Side Rendering) 방식의 React 사용하여 JWT를 구축할 예정이다. 
#### CSR
>말 그대로 SSR과 달리 렌더링이 클라이언트 쪽에서 일어난다. <br>
>즉, 서버는 요청을 받으면 클라이언트에 HTML과 JS를 보내주고, 클라이언트는 그것을 받아 렌더링을 시작한다. <br>
> 쉽게 말하자면, HTML 파일 안에는 아무런 내용이 없다는 것이다. <br> 그 내용은 JS 파일을 받아 실행을 시켜야 그제서야 만들어진다. 

# 라이브러리 추가
참고한 자료는 "'org.springframework.boot' version '2.7.4'" 스프링부트 2.7 버전에서 구현했다.  <br> 우리는 최신버전 3.x 버전 이상에 맞추어 수정해야 한다. <br>
![image](https://github.com/StudyHunter/Backend/assets/57389368/5e66b2ce-222d-4257-9810-6b899c7e494f)
> 전체 코드는 [여기](https://github.com/StudyHunter/Backend/blob/master/%EC%8A%A4%ED%84%B0%EB%94%94/%EC%BD%94%EB%93%9C/%5BSpring%20Boot%5D%20Spring%20Security%EB%A5%BC%20%EC%82%AC%EC%9A%A9%ED%95%9C%20Jwt%20Token%20%EB%A1%9C%EA%B7%B8%EC%9D%B8%20%EA%B5%AC%ED%98%84/build.gradle%20%EC%BD%94%EB%93%9C.md)에서 확인가능하다.

# spring security, JWT 구현
다음과 같이 security, jwt 설정을 구현해야 한다. <br>
![image](https://github.com/StudyHunter/Backend/assets/57389368/951768f5-3e2c-4074-b3e8-ce5ae8b2b3cf)

## User의 Role 추가
![image](https://github.com/StudyHunter/Backend/assets/57389368/499b741f-1fbc-40eb-916f-967d4c22fbf2) <br>
이 코드는 역할(role)을 나타내는 문자열을 처리하는 부분이다. <br>
주어진 문자열로부터 역할 목록을 추출하고, 역할 목록을 문자열로 설정하는 데 사용될 수 있다. 이러한 기능으로  사용자 인증 권한으로 사용할 예정이다.  <br>
+ private String roles;: <br> 문자열 형식의 역할을 저장하는 멤버 변수.
+ public List<String> getRoleList() { ... }:  <br> 역할을 구분자(여기서는 쉼표)로 분할하여 역할 목록을 반환하는 메서드이다. <br> 만약 roles 변수가 null이면, 빈 목록(Collections.emptyList())을 반환한다. <br> 그렇지 않으면, roles를 쉼표로 분할하여 배열로 만든 후, 그 배열을 리스트로 변환하여 반환한다.
+ public void settingRoles(String role) { ... }: <br> 역할을 설정하는 메서드로, 주어진 문자열을 roles 변수에 할당한다.




## PrincipalDetails 
spring security에서 사용자 정보를 담은 `UserDetails 객체를 정의`하는데 사용된다. <br> 사용자의 인증 정보 및 권한을 `커스터마이징`할 때 유용하게 사용될 수 있다. <br><br>

### ✅ UserDetails 인터페이스를 구현한다.
이는 spring security에서 사용자 상세 정보를 나타내는데 쓰인다. <br>
```
    isAccountNonExpired() -> "사용자의 계정이 만료되지 않았으며"
    isAccountNonLocked()  -> "잠기지 않았으며"
    isCredentialsNonExpired() -> "자격 증명이 만료되지 않았으며"
    isEnabled() -> " 활성화되어 있는지"
```
위와 같이 정보를 나타내는 boolean 값을 반환한다. <br><br>

### ✅ getAuthorities() : 메서드는 사용자의 권한 목록을 반환한다.
![image](https://github.com/StudyHunter/Backend/assets/57389368/a1882819-6ebe-4e1c-b9b0-cfe222ff1fae) <br>

여기서는 User, 객체 권한 목록을 `GrantedAuthority 객체`로 변환하여 반환한다. <br><br>

+ 먼저, authorities라는 Collection 객체를 생성한다. 이 객체는 반환할 권한 목록을 담을 것이다.
+ 그런 다음, 사용자의 권한 목록을 가져와서 각 권한을 GrantedAuthority 객체로 변환하여 authorities 컬렉션에 추가한다. <br> user.getRoleList()로 사용자의 권한 목록을 가져와서 각 권한을 람다식을 이용하여 GrantedAuthority 객체로 변환한다.
+ 마지막으로, 변환된 권한 목록이 담긴 authorities 컬렉션을 반환한다.

즉, 이 코드는 사용자가 가진 역할 목록을 GrantedAuthority 객체로 변환하여 반환하는 역할을 한다. <br>
이러한 형태의 권한 객체는 Spring Security에서 인증 및 권한 부여에 사용된다. <br>

## PrincipalDetailsService
![image](https://github.com/StudyHunter/Backend/assets/57389368/f33084c2-0200-4a56-982f-7f02cd0213f1)

이 클래스는 사용자의 인증 정보를 로드하기 위해 사용된다. <br>
loadUserByUsername() 메서드는 사용자의 닉네임을 매개변수로 받아와서 해당 닉네임에 해당하는 사용자 정보를 데이터베이스에서 찾는다. 이때 UserRepository를 사용하여 닉네임에 해당하는 사용자를 조회한다. <br><br>
조회된 사용자 정보를 이용하여 PrincipalDetails 객체를 생성하고 반환한다. 이 객체는 Spring Security의 UserDetails 인터페이스를 구현한 것으로, 사용자의 인증 및 권한 정보를 담고 있다

## JwtAuthenticationFilter 
`WT(JSON Web Token)`를 사용하여 사용자의 인증을 처리한다. <br> 이 필터는 사용자가 로그인하면 JWT 토큰을 생성하고, 이를 응답 헤더에 담아 클라이언트에게 반환한다. <br><br>
사용자의 인증이 성공하면 클라이언트는 JWT 토큰을 받게 되고, 이 토큰을 사용하여 인증된 요청을 서버에 보낼 수 있다. <br><br>
### ✅ UsernamePasswordAuthenticationFilter를 상속한다. 
이는 Spring Security에서 제공하는 기본 로그인 인증 필터 중 하나이다.

### ✅ authenticationManager 필드는 Spring Security에서 인증을 관리하는 데 사용되는 AuthenticationManager 객체를 주입받는다.
![jwt filter](https://github.com/StudyHunter/Backend/assets/57389368/5ba1a1b4-138d-4204-8723-3120492b02be) <br>
+ attemptAuthentication() 메서드는 사용자의 로그인 시도를 처리한다. 
+ 클라이언트가 전송한 로그인 요청에서 username과 password를 추출하여 LoginRequestDto 객체로 변환한 후, <br> `UsernamePasswordAuthenticationToken 객체`를 생성하여 인증 매니저를 통해 인증을 시도한다.

### ✅ 성공적으로 인증되면 successfulAuthentication() 메서드가 호출된다. 
![image](https://github.com/StudyHunter/Backend/assets/57389368/377b3e7f-3e79-48c7-8393-6a9878cc9c79) <br>
+ 이 메서드에서는 사용자의 정보를 기반으로 JWT 토큰을 생성하고, 응답 헤더에 추가하여 클라이언트에게 반환한다. 
+ JWT 토큰에는 사용자의 ID와 닉네임이 포함된다. <br> JWT 토큰은 com.auth0.jwt.JWT 클래스를 사용하여 생성된다. 
+ 토큰에는 사용자명(subject), 만료 시간(expiration time), 사용자의 ID 및 닉네임과 같은 클레임(claim)이 추가됩니다.
+ `생성된 JWT 토큰은 응답 헤더에 Authorization 헤더에 추가되어 클라이언트에게 반환된다.`


## JwtAuthorizationFilter 
JWT(JSON Web Token)를 사용하여 사용자의 권한을 확인하는 `Spring Security의 필터`인 JwtAuthorizationFilter 클래스이다. <br><br>
`JWT 토큰을 사용하여 사용자의 인증 및 권한을 검증하고, 검증된 정보를 Spring Security에서 사용할 수 있도록 설정한다.` <br><br>
이 필터는 클라이언트로부터 전송된 JWT 토큰을 확인하여 사용자의 권한을 검증하고, <br> 권한 정보를 Spring Security의 SecurityContextHolder에 저장한다.

### ✅ BasicAuthenticationFilter를 상속한다.
JwtAuthorizationFilter 클래스는 BasicAuthenticationFilter를 상속한다. <br> 
이는 Spring Security에서 제공하는 기본 인증 필터 중 하나이다.

<br>

### ✅ 필터를 생성할 때 AuthenticationManager와 UserRepository를 주입받는다. 
![image](https://github.com/StudyHunter/Backend/assets/57389368/5cf5a7df-60af-429f-a1a3-52df273a98a6) <br>
AuthenticationManager는 인증 매니저를, UserRepository는 사용자 정보를 가져오기 위해 사용된다.

<br>

###  ✅ doFilterInternal() 메서드는 실제 필터링 작업을 수행한다. 
클라이언트의 요청 헤더에서 JWT 토큰을 추출하고, 이를 이용하여 사용자의 닉네임을 확인한다. <br>

#### ✔️ 사용자 정보 조회
![image](https://github.com/StudyHunter/Backend/assets/57389368/e9e8d0f8-44e1-4896-ad7d-0140becafd97) <br>
JWT 토큰에서 추출한 닉네임을 사용하여 데이터베이스에서 해당 사용자를 조회한다. <br> 이를 통해 사용자 정보를 가져온다.

#### ✔️ 사용자 정보 인증 및 권한 설정 프로세스
![image](https://github.com/StudyHunter/Backend/assets/57389368/a208bd40-ad70-4076-8517-ee1f39244678) <br>
+ 가져온 사용자 정보를 PrincipalDetails 객체로 변환하여 UsernamePasswordAuthenticationToken 객체를 생성한다. <br> 여기서는 패스워드를 사용하지 않으므로 null로 처리하고, 권한은 사용자 정보에 포함된 권한을 사용한다.
+ 생성된 Authentication 객체를 SecurityContextHolder에 설정하여 현재 사용자의 인증 정보를 저장한다.
+ 필터 체인을 계속 진행하여 다음 필터 또는 서블릿으로 요청을 전달한다.

## JwtProperties
JWT(JSON Web Token) 관련 속성을 정의하는 JwtProperties 인터페이스이다. 이 인터페이스는 JWT 토큰 생성 및 검증에 필요한 몇 가지 속성을 정의한다. <br><br> 
이러한 속성들은 JWT 토큰의 생성, 검증 및 사용 시에 참조되어야 하는 중요한 정보를 제공한다. <br>  따라서 안전한 값으로 설정되어야 하며, 보안에 주의하여야 한다. <br> 
![image](https://github.com/StudyHunter/Backend/assets/57389368/8c905f08-8c56-4cc2-a976-63c58dd7b7a0) <br><br>

### ✔️  SECRET 
JWT 토큰을 생성할 때 사용되는 비밀 키다. 이 키는 서버만 알고 있어야 한다.  <br>  보안상 중요한 값이므로 보안을 위해 안전한 곳에 보관되어야 한다.

### ✔️ EXPIRATION_TIME
JWT 토큰의 만료 시간을 설정한다. 이 시간이 지나면 해당 토큰은 더 이상 유효하지 않게 된다.

### ✔️ TOKEN_PREFIX 
bearer를 사전에 검사함으로서 JWT 토큰인지 아님을 빠르게 식별할 수 있게된다.
 
### ✔️ HEADER_STRING 
JWT 토큰이 HTTP 요청의 어떤 헤더에 있는지를 나타내는 문자열이다. <br>  일반적으로는 "Authorization" 헤더에 JWT 토큰이 포함되어 있다.

## SecurityConfig
`웹 보안 구성`을 정의하고 `JWT(JSON Web Token) 인증 및 권한 부여`를 구현한다. <br>
SecurityConfig 클래스는 @Configuration 및 @EnableWebSecurity 어노테이션을 사용하여 Spring Security의 설정 클래스임을 나타낸다. <br><br>

### ✔️ passwordEncoder() 메서드
![image](https://github.com/StudyHunter/Backend/assets/57389368/81fd15ca-b40d-4b78-a761-c359e1a4a01a) <br>
passwordEncoder() 메서드는 BCryptPasswordEncoder 빈을 생성하여 암호화에 사용된다. <br>
이 빈은 사용자의 비밀번호를 암호화하고, 인증 시 비밀번호를 검증하는 데 사용된다. <br>

### ✔️ authenticationManager() 메서드
![image](https://github.com/StudyHunter/Backend/assets/57389368/6f7c536f-7a28-4ce5-a145-634572237a6f) <br>
authenticationManager() 메서드는 인증 매니저 빈을 생성한다. <br> 
이 빈은 Spring Security의 인증을 관리하는 데 사용된다.

<br>

### ✔️ filterChain() 메서드
![image](https://github.com/StudyHunter/Backend/assets/57389368/22304229-02a5-4f48-b18c-41e583509a00) <br>

#### REST API 설정은 다음과 같다.
```
.csrf(AbstractHttpConfigurer::disable)
```
REST API에서는 CSRF 방어가 필요가 없고, CSRF 토큰을 주고 받을 필요가 없기 때문에 CSRF 설정을 해제한다. <br>
> CSRF를 켜두면 서버는 클라이언트 영역에 CSRF 토큰을 보낼 수 있다. <br>

<br>

```
.addFilter(corsConfig.corsFilter())
```
REST API에서는 여러 서버를 운영하는 환경이다보니 SOP 뿐만아니라 CORS도 허용을 해야 여러 곳에서 접근이 가능하므로 CORS를 허용해줘야 한다. <br>
filterChain() 메서드는 HTTP 보안 구성을 정의한다. http 객체를 매개변수로 받아 필터 체인을 구성한다. <br>
여기서는 CSRF 보호를 비활성화하고, 세션 관리 정책을 STATELESS로 설정하여 세션을 사용하지 않도록 한다. <br>

#### `JwtAuthenticationFilter와 JwtAuthorizationFilter를 추가하여 JWT 기반의 인증 및 권한 부여를 수행한다.`
+ JwtAuthenticationFilter는 사용자의 로그인 요청을 처리하고 JWT 토큰을 생성한다.
+ JwtAuthorizationFilter는 HTTP 요청의 JWT 토큰을 검증하여 사용자의 권한을 확인하고 인증 정보를 설정한다.

#### `authorizeRequests() 메서드를 사용하여 요청에 대한 접근 권한을 설정한다.`
+ 여기서는 정적 리소스에 대한 접근을 허용하고,
+ "/api/v1/user/" 경로에 대해서는 USER, MANAGER, ADMIN 역할이 있는 사용자만 접근을 허용하며,
+ "/api/v1/admin/" 경로에 대해서는 모든 사용자에게 접근을 허용합니다.

이렇게 함으로써 Spring Security를 사용하여 웹 애플리케이션의 보안을 구성하고, JWT를 활용하여 인증 및 권한 부여를 처리한다.

## CorsConfig
이 코드는 Cross-Origin Resource Sharing (CORS)를 구성하기 위한 CorsConfig 클래스이다.  <br>
이 클래스는 Spring Boot 애플리케이션에 `CORS 필터를 추가하여 다른 출처에서의 HTTP 요청을 처리할 수 있도록` 설정한다. <br>

### ✔️ CorsFilter 등록 
![image](https://github.com/StudyHunter/Backend/assets/57389368/ecc7edae-c428-4387-a85e-e0f3b105851c) <br>
corsFilter() 메서드는 CorsFilter 객체를 생성한다. 이 필터는 CORS를 처리하는 데 사용된다. <br><br>
CorsFilter 객체를 생성하여 이를 반환한다. 이 필터는 Spring의 보안 구성에 등록되어 CORS 요청을 처리한다. <br> 
이렇게 함으로써 Spring Boot 애플리케이션에 CORS를 구성하여 다른 출처에서의 HTTP 요청을 허용한다. <br>

+ UrlBasedCorsConfigurationSource 객체를 생성하여 CORS 구성을 설정합니다.  <br> 이는 URL 기반의 CORS 구성을 제공한다.
+ CorsConfiguration 객체를 생성하여 CORS 설정을 구성한다. <br> setAllowCredentials(true)는 자격 증명을 허용하고, addAllowedOrigin("*")은 모든 출처에서의 요청을 허용하고, addAllowedHeader("*")와 addAllowedMethod("*")는 모든 헤더와 모든 HTTP 메서드를 허용합니다.
+ source.registerCorsConfiguration() 메서드를 사용하여 CORS 구성을 특정 URL 패턴에 등록한다. <br> 여기서는 "/api/**"와 "/login_proc" 경로에 대해 동일한 CORS 구성을 등록한다.
> @Bean 어노테이션은 CorsFilter 인스턴스를 생성하여 Spring의 애플리케이션 컨텍스트에 빈으로 등록합니다.


## Spring Boot 3.x 이상의 Security Config 코드 변경 사항
이전 버전의 코드와 최신 버전의 코드 간에는 주요한 차이가 있습니다. 주요한 변경 사항은 다음과 같다. <br>

### ✔️ 1. CSRF(Cross-Site Request Forgery) 설정
```
   - 이전 버전: `.csrf().disable()`
   - 최신 버전: `.csrf(AbstractHttpConfigurer::disable)`
```

### ✔️ 2. 세션 관리(Session Management) 설정
```
   - 이전 버전: `.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)`
   - 최신 버전: `.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))`
```

### ✔️ 3. 로그인 폼 설정
```
   - 이전 버전: `.formLogin().disable()`
   - 최신 버전: `.formLogin(login -> login.loginPage("/login").permitAll())`
```

### ✔️ 4. HTTP 기본 인증 설정
```
   - 이전 버전: `.httpBasic().disable()`
   - 최신 버전: 해당 설정이 없음
```

### ✔️ 5. `authorizeRequests()` 메서드 사용
```
   - 이전 버전: `authorizeRequests().antMatchers(...)...`
   - 최신 버전: `authorizeRequests(authorize -> authorize.antMatchers(...)...)`
```

이전 버전에서는 각 구성 요소를 따로 설정하고 체이닝하여 보안 필터 체인을 구성했었다 <br>
최신 버전에서는 `authorizeRequests()` 메서드를 사용하여 권한 설정을 직접 지정할 수 있다. 또한 일부 메서드에 대한 사용법이 변경되었으며, 람다 표현식을 사용하여 세부 설정을 더 명확하게 지정할 수 있게 수정했다. <br><br>
> 최신 버전에 맞게 수정했으며, 코드를 리팩토링하여 람다 표현식을 사용했다.

##  REST API 구현
Spring Security와 JWT(JSON Web Token)을 사용하여 보안을 구현하는 REST API 컨트롤러인 RestApiController를 구현했다. <br>
이 컨트롤러는 사용자의 인증 및 권한을 확인하고, 사용자 정보에 접근하여 처리한다.

### ✅ "/login" 매핑이 없는 이유


#### ✔️ userRepository 주입
사용자 정보를 데이터베이스에서 조회하고 저장하기 위한 UserRepository를 주입한다.

#### ✔️ bCryptPasswordEncoder 주입
사용자의 비밀번호를 암호화하기 위한 BCryptPasswordEncoder를 주입한다.

#### ✔️ @GetMapping("/home")
모든 사람이 접근 가능한 홈 페이지에 대한 요청을 처리한다.

#### ✔️ @GetMapping("/logout")
로그아웃 요청을 처리하고, 세션을 무효화한다.

#### ✔️ @GetMapping("/user")
![image](https://github.com/StudyHunter/Backend/assets/57389368/50187100-191a-47e7-a443-62689f7210ea) <br>
인증된 사용자에 대한 정보를 반환한다. 현재 로그인한 사용자의 PrincipalDetails를 가져와 사용자 정보에 접근한다.

#### ✔️ @PostMapping("/join")
![image](https://github.com/StudyHunter/Backend/assets/57389368/9f4088a0-bd7a-46a6-b4ea-bad76db73e6e) <br>
사용자 회원가입 요청을 처리하고, 비밀번호를 암호화하여 저장한다. <br>
이 컨트롤러는 Spring Security와 함께 사용되어 각 엔드포인트에 대한 접근 권한을 부여하고, 인증된 사용자에 대한 정보를 제공한다.
또한 `사용자의 비밀번호를 암호화하여 저장하여 보안을 강화`한다.
> "user.xxx"로 처리하는 부분은 userService에서 다루도록 리팩토링할 예정이다.

<br><br>

## 브라우저 확인 및 로그 확인
###  ✅ "/login_page" Get 요청
"/login_page"를 get 요청하여 로그인 폼 페이지로 이동한다. <br>
![로그인 폼](https://github.com/StudyHunter/Backend/assets/57389368/bde75d23-8393-4dfb-a1ea-f4e42e07d17b)
 
### ✅ GET 요청 성공하면, "/home" 으로 이동된다.
로그인 정상처리가 되면, "/home" 메인 페이지로 이동된다. <br>
![로그인 후 메인페이지 이동](https://github.com/StudyHunter/Backend/assets/57389368/2c8db153-65ff-403d-91ae-d47f26a4cb5b) <br><br>

로그인이 성공처리가 되면 아래와 같이 로그로 확인이 가능하다. <br>
![로그인 로그확인](https://github.com/StudyHunter/Backend/assets/57389368/760998bd-f004-4ed5-b33a-ac1b3c7fd20e) <br> 
> 참고로, 로그는 REST API에서 작성했던 "/user" get 요청으로 확인할 수 있는 것이다. <br>
> ![image](https://github.com/StudyHunter/Backend/assets/57389368/f8b8321b-e633-4d9b-87b8-bd83dbde3690)

<br><br>
 
### ✅ 스터디 방 입장에 대한 요청
![입장](https://github.com/StudyHunter/Backend/assets/57389368/96497bd7-16a1-44f3-82cf-e87b3951b4a8) <br><br>
메인 페이지에 있는 방을 "입장"버튼으로 원하는 스터디 방에 참여할 수 있다. <br>
아래의 버튼이 "입장" 버튼 역할을 한다. <br>
![image](https://github.com/StudyHunter/Backend/assets/57389368/88433403-1f37-4249-9c0d-4275bbb6278f) <br><br>
![방 입장 ajax](https://github.com/StudyHunter/Backend/assets/57389368/33bb0f53-b399-4a6c-b308-91d418e80c7c) <br>

 <br>
 
### ✅ "/rooms/(roomId)" 로 이동된다.
"입장" 버튼을 누르면, 해당 스터디 방에 들어가진다. (이때 방의 List<User>도 카운팅되도록 구현했다.)
![방 입장](https://github.com/StudyHunter/Backend/assets/57389368/2cc31202-f24d-40da-97b3-d17699daf76e)



# 참고자료
+ [SSR-vs-CSR-차이](https://velog.io/@hanei100/TIL-SSR-vs-CSR-%EC%B0%A8%EC%9D%B4)
+ [jwt 로그인 구현](https://stir.tistory.com/275)
  
