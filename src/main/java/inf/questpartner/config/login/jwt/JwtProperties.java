package inf.questpartner.config.login.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.function.Function;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.security.Key;

@Component
public class JwtProperties implements Serializable {
	private static final long serialVersionUID = -2550185165626007488L;

	@Value("${jwt.tokenExpirationTime}") private Integer tokenExpirationTime;   // JWT 토큰 만료 시간
	@Value("${jwt.secret}") private String secret;   // JWT 비밀키

	private final Key key;  // 비밀키를 Key 형태로 변환


	/**
	 * JwtProperties 생성자
 	 */
	public JwtProperties(@Value("${jwt.secret}") String secretKey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}

	// JWT에서 사용자 email 정보 추출한다.

	public String getEmailFromJwt(String jwt) {
		String token = jwt.substring(7);
		String subject = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
		return subject;
	}

	// JWT 토큰에서 사용자 이름을 추출한다.
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	//JWT 토큰에서 만료날짜를 추출한다.
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	// JWT 토큰에서 클레임 추출한다.
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// JWT 토큰에서 모든 클레임 추출한다.
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// JWT가 만료되었는지 확인하는 로직
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// 사용자 정보를 기반으로 JWT 생성하는 로직
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}

	// JWT 토큰 생성
	//while creating the token -
	//1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
	//2. Sign the JWT using the HS512 algorithm and secret key.
	//3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	//   compaction of the JWT to a URL-safe string
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + tokenExpirationTime * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	// 유효한 JWT인지 확인하는 로직 (추가로 사용자 정보도 확인)
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	// 유효한 JWT인지 확인하는 로직
	public Boolean validateToken(String token) {
		return !isTokenExpired(token);
	}
}
