package h2mcalc.hrz;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Hero>   heroes;
    private List<String> gameLog;
    private AttackAlgorithm attackAlgorithm = new AttackAlgorithm();

    public Game(List<Hero> heroes) {
        this.heroes = heroes;
    }

    public List<Hero> getHeroes() {
        return heroes;
    }

    @Override
    public String toString() {
        return "Game{" +
               "heroes=" + heroes +
               '}';
    }

    public String printStr() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson        gson        = gsonBuilder.setPrettyPrinting().create();
        String      result      = gson.toJson(this);
        return result;
    }

    public void attack(String aName, String aArmyId, String bName, String bArmyId) {
        Hero aHero = getHeroByName(aName);
        Hero bHero = getHeroByName(bName);

        if (null == aHero || null == bHero) {
            gameLog("GAME INPUT ERROR: invalid hero name " + "hero a:" + aName + " " + aHero + "hero b:" + bName + " " + bHero);
        } else {
            int aHeroAttack  = aHero.getAttack();
            int aHeroDefence = aHero.getDefence();

            int bHeroAttack  = bHero.getAttack();
            int bHeroDefence = bHero.getDefence();

            Army armyA = aHero.getArmy(aArmyId);
            Army armyB = bHero.getArmy(bArmyId);
            if (null == armyA) {
                String message = "A attacker hero \"%s\" army \"%s\" does not exist";
                gameLog(String.format("ATTACK FAILED ::" + message, aHero.getName(), aArmyId));
            } else if (null == armyB) {
                String message = "A hero \"%s\" with army \"%s\" attacked hero \"%s\" army \"%s\" which was not exist";
                gameLog(String.format("ATTACK FAILED ::" + message, aHero.getName(), aArmyId, bHero.getName(), bArmyId));
            } else {
                gameLog("ATTACK: "
                        + aHero.getName() + ":" + armyA.getId() + " " + armyA.getAmount() + " " + armyA.getAttack() + " " + armyA.getHealthPerUnit()
                        + "-->>>"
                        + bHero.getName() + ":" + armyB.getId() + " " + armyB.getAmount() + " " + armyB.getAttack() + " " + armyB.getHealthPerUnit()
                        + " "
                        + armyA + "\n     attack " + armyB);

                AttackResultMessage attackResultMessage = AttackAlgorithm.attack(armyA, aHeroAttack, aHeroDefence, armyB, bHeroAttack, bHeroDefence);
                gameLog(attackResultMessage.toString());
                if (armyB.getAmount() <= 0) { // the army b was attacked and could be totally destroyed
                    bHero.removeArmy(armyB);
                }
            }
        }
    }

    private void gameLog(String message) {
        if (gameLog == null) {
            gameLog = new ArrayList<>();
        }
        gameLog.add(message);
        System.out.println(message);
    }


    public Hero getHeroByName(String name) {
        Hero hero = heroes.stream()
                          .filter(x -> name.equals(x.getName()))
                          .findAny()
                          .orElse(null);
        return hero;
    }

}
