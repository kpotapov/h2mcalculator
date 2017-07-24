package h2mcalc;

import h2mcalc.hrz.Game;

public class GameWrapper {
    Game   game;
    String startFilePath;
    String saveFilePath;

    public GameWrapper(Game game, String startFilePath, String saveFilePath) {
        this.game = game;
        this.startFilePath = startFilePath;
        this.saveFilePath = saveFilePath;
    }
}
