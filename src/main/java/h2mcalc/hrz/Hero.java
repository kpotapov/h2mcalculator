package h2mcalc.hrz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@lombok.Data
@lombok.AllArgsConstructor
public class Hero {
    private final String name;
    private int attack;
    private int defence;

    List<Army> armies = new ArrayList<>();

    public Army getArmy(String id) {
        Army army = armies.stream()
                .filter(x -> id.equals(x.getId()))
                .findAny()
                .orElse(null);
        return army;
    }

    public void removeArmy(Army army) {
        Iterator<Army> iterator = armies.iterator();
        while (iterator.hasNext()) {
            Army next = iterator.next();
            if (next.getId().equals(army.getId())) {
                iterator.remove();
            }
        }
    }
}
