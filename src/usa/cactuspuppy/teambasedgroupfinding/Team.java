package usa.cactuspuppy.teambasedgroupfinding;

import java.util.HashSet;
import java.util.Set;

public class Team {
    private Set<TPlayer> tplayers;
    private WeightedQuickUnionUF groupTracker;
    private String name;

    public Team(String n) {
        name = n;
        tplayers = new HashSet<>();
        groupTracker = new WeightedQuickUnionUF();
    }
}
