package inf.questpartner.config.jwt;

public interface JwtProperties {
	String SECRET = "loose"; // 서버만 알고 있는 개인키
	int EXPIRATION_TIME = 1000000; // 10초 *100(1/1000초)
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}
