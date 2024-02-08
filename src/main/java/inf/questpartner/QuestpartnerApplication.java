package inf.questpartner;

import inf.questpartner.common.properties.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {AppProperties.class})
public class QuestpartnerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuestpartnerApplication.class, args);
	}

}
