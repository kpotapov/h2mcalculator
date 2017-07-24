package h2mcalc.hrz;

@lombok.Data
@lombok.AllArgsConstructor
public class Army {
    private String id;
    private String name;
    private int attack;
    private int defence;
    private int amount;
    private int healthPerUnit;
    private int lastUnitHealth;
}
