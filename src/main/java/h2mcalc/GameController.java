package h2mcalc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calc")

public class GameController {
    private Log log = LogFactory.getLog(GameController.class);

    final Games games;

    @Autowired
    public GameController(Games games) {
        this.games = games;
    }


    @RequestMapping(value = "/print", produces = {"application/json"}, method = RequestMethod.GET)
    @ApiOperation(value = "print current game status ", response = String.class)
    public String print() {
        return games.printCurrentGame();
    }

    @RequestMapping(value = "/attack/{heroa}/{armya}/{herob}/{armyb}", produces = {"application/json"}, method = RequestMethod.POST)
    public String attack(
            @PathVariable(value = "heroa") String heroA,
            @PathVariable(value = "armya") String armyA,
            @PathVariable(value = "herob") String heroB,
            @PathVariable(value = "armyb") String armyB
    ) {
        games.currentGameAttack(heroA, armyA, heroB, armyB);
        return games.printCurrentGame();
    }


    @RequestMapping(value = "/loadgame/{startgamefile}/{savegamefile}",
            produces = {"application/json"}, method = RequestMethod.POST)
    public String loadgame(@PathVariable(value = "startgamefile") String startgamefile,
                           @PathVariable(value = "savegamefile") String savegamefile) {
        games.loadgame(startgamefile, savegamefile);
        return games.printCurrentGame();
    }

}
