package usa.cactuspuppy.teambasedgroupfinding;

import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class TPlayer {
    private UUID playerUUID;
    private int tbgfID;

    private static int nextTBGF;

    public TPlayer(OfflinePlayer player) {
        playerUUID = player.getUniqueId();
        tbgfID = nextTBGF;
        nextTBGF++;
    }

    public static void settbgfID(int num) {
        nextTBGF = num;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public int getTbgfID() {
        return tbgfID;
    }

    public boolean checkID(int ID) {
        return ID == tbgfID;
    }

    public boolean checkUUID(UUID num) {
        return num.equals(playerUUID);
    }

    public boolean equals(TPlayer other) {
        return this.playerUUID.equals(other.playerUUID);
    }
}
