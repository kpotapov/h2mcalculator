package h2mcalc;

import org.junit.Test;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GameControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSimplePath() {
        String print = "/calc/print";
        String load = "/calc/loadgame/game.json/game-save.json";
        String attack = "/calc/attack/First/C/Second/C";
        assertEmptyGameResult(this.restTemplate.getForObject(print, String.class));

        Object request = null;
        assertEmptyGameResult(this.restTemplate.postForObject(attack, request, String.class));

        assertThat(this.restTemplate.getForObject(load, String.class)).isNotEmpty();

        assertThat(this.restTemplate.getForObject(print, String.class)).isNotEmpty();
        assertThat(this.restTemplate.getForObject(attack, String.class)).isNotEmpty();
    }

    private void assertEmptyGameResult(String body) {
        assertThat(body).isEqualTo("{\"status\":\"game is empty\"}");
    }

}