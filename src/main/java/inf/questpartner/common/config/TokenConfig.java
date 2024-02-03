package inf.questpartner.common.config;

//@Configuration
//public class TokenConfig {
//
//    @Value("${spring.redis.token.host}")
//    private String redisHost;
//
//    @Value("${spring.redis.token.port}")
//    private int redisPort;
//
//    @Bean
//    public RedisConnectionFactory redisTokenConnectionFactory() {
//        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisHost,
//                redisPort);
//        return lettuceConnectionFactory;
//    }
//
//    @Bean(name = "tokenRedisTemplate")
//    public StringRedisTemplate stringRedisTemplate() {
//        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
//        stringRedisTemplate.setConnectionFactory(redisTokenConnectionFactory());
//        return stringRedisTemplate;
//    }
//}
