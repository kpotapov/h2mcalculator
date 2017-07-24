package h2mcalc;

import h2mcalc.persistence.IOTool;

public class Games {
    private GameWrapper currentGame;

    public void loadgame(String startgamefile, String savegamefile) {
        GameWrapper game = IOTool.creategameFromFile(startgamefile, savegamefile);
        currentGame=game;
    }

    public void currentGameAttack(String aName, String aArmyId, String bName, String bArmyId) {
        if (null != currentGame && null != currentGame.game) {
            currentGame.game.attack(aName,aArmyId,bName,bArmyId);
        }
    }

    public String printCurrentGame() {
        if (null != currentGame && null != currentGame.game) {
            return currentGame.game.printStr();
        } else {
            return "{\"status\":\"game is empty\"}";
        }
    }
}
