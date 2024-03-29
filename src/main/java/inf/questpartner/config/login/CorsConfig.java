package inf.questpartner.config.login;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {

   /* CORS */
   @Bean
   public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.addAllowedOrigin("*");
      configuration.setAllowedMethods(Arrays.asList("GET","POST", "OPTIONS", "PATCH", "DELETE"));
      configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));

      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
   }
}
