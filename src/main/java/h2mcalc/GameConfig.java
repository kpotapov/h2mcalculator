package h2mcalc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {

    @Bean
    public Games games() {
        Games games = new Games();
        return games;
    }

}
