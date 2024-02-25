package inf.questpartner.config.login;

import inf.questpartner.config.login.jwt.JwtAuthenticationEntryPoint;

import inf.questpartner.config.login.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final CorsConfigurationSource corsConfigurationSource;

	private static final String[] AUTH_WHITELIST = {
			"/api/**", "/graphiql", "/graphql", "/swagger/index.html",
			"/swagger-ui/**", "/api-docs", "/swagger-ui-custom.html",
			"/v3/api-docs/**", "/api-docs/**", "/swagger-ui.html"
	};

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.httpBasic(httpBasic -> httpBasic.disable())
				.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configurationSource(corsConfigurationSource))

				.authorizeHttpRequests(authorize
						-> authorize

						.requestMatchers("/chat/**",
								"/rooms/search","/randomQuote",
								"/user/checkId",
								"/user/register",
								"/user/login").permitAll()

						.requestMatchers("/user/**").hasRole("USER")
						.requestMatchers("/rooms/**").hasRole("USER")
						.requestMatchers("/rooms/{roomId}/**").hasRole("USER"))

				// 추가 구성 시작
				.authorizeHttpRequests(
						authorize -> authorize
								.requestMatchers(AUTH_WHITELIST)
								.permitAll()
								.anyRequest()
								.authenticated()
				)

				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.exceptionHandling(excep -> excep.authenticationEntryPoint(jwtAuthenticationEntryPoint))
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
}


