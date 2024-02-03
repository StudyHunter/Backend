package inf.questpartner.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

import static inf.questpartner.common.utils.EmailConstants.LIMIT_TIME_EMAIL_VALIDATION;
import static inf.questpartner.common.utils.EmailConstants.PREFIX_VERIFICATION;

@RequiredArgsConstructor
@Repository
public class EmailVerificationDao implements EmailCertificationDao {

    private final StringRedisTemplate redisTemplate;

    @Override
    public void createEmail(String email, String token) {
        redisTemplate.opsForValue()
                .set(PREFIX_VERIFICATION + email, token,
                        Duration.ofSeconds(LIMIT_TIME_EMAIL_VALIDATION));
    }

    @Override
    public String getEmailCertification(String email) {
        return redisTemplate.opsForValue().get(PREFIX_VERIFICATION + email);
    }

    @Override
    public void removeEmailCertification(String email) {
        redisTemplate.delete(PREFIX_VERIFICATION + email);
    }

    @Override
    public boolean hasKey(String email) {
        return redisTemplate.hasKey(PREFIX_VERIFICATION + email);
    }
}
