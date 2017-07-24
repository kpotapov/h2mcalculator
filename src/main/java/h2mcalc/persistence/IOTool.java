package h2mcalc.persistence;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import h2mcalc.hrz.Game;
import h2mcalc.GameWrapper;

import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOTool {

    private static final Logger logger = LoggerFactory.getLogger(IOTool.class);

    /**
     * reads a game from json configuration file
     * @param pathname
     * @return
     * @throws IOException
     */
    public static Game readGameFromFile(String pathname) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String text = Files.toString(new File(pathname), Charsets.UTF_8);
        System.out.println(text);
        Game newGame = gson.fromJson(text, Game.class);
        return newGame;
    }

    /**
     * reads a game from json configuration file
     * @param startgamefile
     * @param savegamefile
     * @return
     */
    public static GameWrapper creategameFromFile(String startgamefile, String savegamefile) {
        Game game = null;
        try {
            game = readGameFromFile(startgamefile);
        } catch (IOException e) {
            logger.debug("An error reading json game configuration from file: '{}'", startgamefile);
            logger.debug(e.getLocalizedMessage());
        }
        GameWrapper gameWrapper = new GameWrapper(game, startgamefile, savegamefile);
        return gameWrapper;
    }
}
