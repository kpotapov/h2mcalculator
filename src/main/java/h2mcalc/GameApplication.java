package h2mcalc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class GameApplication {

	private Log log = LogFactory.getLog(GameApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}


}
