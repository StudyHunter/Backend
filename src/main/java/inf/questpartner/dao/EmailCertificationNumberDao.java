package inf.questpartner.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

import static inf.questpartner.util.constant.EmailConstants.LIMIT_TIME_CERTIFICATION_NUMBER;
import static inf.questpartner.util.constant.EmailConstants.PREFIX_CERTIFICATION;

@RequiredArgsConstructor
@Repository
public class EmailCertificationNumberDao implements EmailCertificationDao {
    private final StringRedisTemplate redisTemplate;

    @Override
    public void createEmail(String email, String certificationNumber) {
        redisTemplate.opsForValue()
                .set(PREFIX_CERTIFICATION + email, certificationNumber,
                        Duration.ofSeconds(LIMIT_TIME_CERTIFICATION_NUMBER));
    }

    @Override
    public String getEmailCertification(String email) {
        return redisTemplate.opsForValue().get(PREFIX_CERTIFICATION + email);
    }

    @Override
    public void removeEmailCertification(String email) {
        redisTemplate.delete(PREFIX_CERTIFICATION + email);
    }

    @Override
    public boolean hasKey(String email) {
        return redisTemplate.hasKey(PREFIX_CERTIFICATION + email);
    }
}
