package h2mcalc.hrz;

import static java.lang.Math.round;

public class AttackAlgorithm {

    /**
     * the army A attacks the army B
     */
    public static AttackResultMessage attack(Army a, int aAttack, int aDefence, Army b, int bAttack, int bDefence) {
        AttackResultMessage battleResult = new AttackResultMessage("Not defined.");

        int    aArmyAttack  = a.getAttack();
        int    bArmyDefence = b.getDefence();
        double attackDefenseModifier;
        if (aArmyAttack >= bArmyDefence) {
            attackDefenseModifier = 1 + (aArmyAttack - bArmyDefence) * 0.05;
        } else {
            attackDefenseModifier = 1 / (1 + (aArmyAttack - bArmyDefence) * 0.05);
        }

        double aAttackHits  = (a.getAttack() + aAttack + attackDefenseModifier) * a.getAmount();
        double bDefenceHits = (b.getDefence() + bDefence) * b.getAmount();
        double diff         = aAttackHits - new Double(bDefenceHits * 0.3).intValue();
        if (diff > 0) {
            battleResult = attacking(b, diff);
        } else {
            int strongAttackPower = a.getAttack() - (b.getDefence() * 2);
            if (strongAttackPower > 0) {
                double diffForStrongAttacker = strongAttackPower * a.getAmount();
                if (diffForStrongAttacker > 0) {
                    battleResult = attacking(b, diffForStrongAttacker);
                } else {
                    //pass
                }
            } else {
                //pass
            }
        }
        return battleResult;
    }

    private static AttackResultMessage attacking(Army b, double diff) {
        AttackResultMessage battleResult;

        int bHealthTotal = (b.getHealthPerUnit() - 1) * b.getAmount() + b.getLastUnitHealth();
        if (bHealthTotal > 0) {
            double newHealthTotal     = bHealthTotal - diff;
            double newAmountDecrement = newHealthTotal / b.getHealthPerUnit();
            double newLastUnitHealth  = newHealthTotal % b.getHealthPerUnit();

            long unitAmountWasLost = b.getAmount() - (int) round(newAmountDecrement);

            b.setAmount(new Double(newAmountDecrement).intValue());
            b.setLastUnitHealth(new Double(newLastUnitHealth).intValue());
            battleResult = new AttackResultMessage(String.format("The army %s %s lost %d units", b.getId(), b.getName(), unitAmountWasLost));
        } else {
            b.setAmount(0);
            b.setLastUnitHealth(0);
            battleResult = new AttackResultMessage(String.format("The army %s was destroyed", b));
        }
        return battleResult;
    }

}
