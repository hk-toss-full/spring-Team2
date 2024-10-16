package team2.WebSocket_QuerryDSL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebSocketQuerryDslApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSocketQuerryDslApplication.class, args);
	}

}
