package usa.cactuspuppy.teambasedgroupfinding;

import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.Set;

import static java.lang.System.arraycopy;

public class Main extends JavaPlugin implements Listener {
    private WeightedQuickUnionUF playerGroups;

    @Override
    /**
     * This method is called when the server loads the plugin.
     */
    public void onEnable() {
        
    }


    @Override
    /**
     * This method is called when the server disables the plugin.
     */
    public void onDisable() {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

    public boolean onCommand(final CommandSender sender, final Command cmd, final String command, final String[] args) {
        //team chat command
        if (command.equals("t")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Color.RED + "/t should only be used by players!");
                return true;
            }
            sendGroupMessage(sender, args);
            return true;
        }

        //group command
        if (command.equals("group")) {
            //TODO: Union player with person on their team
        }

        //team command
        if (command.equals("team")) {
            String subcommand = args[0];
            String[] newArgs = new String[args.length - 1];
            arraycopy(args, 1, newArgs, 0, args.length - 1);
            if (subcommand.equals("create")) {
                //TODO: Create team subroutine
            } else if (subcommand.equals("delete")) {
                //TODO: Delete team subroutine
            } else if (subcommand.equals("join")) {
                //TODO: Join player subroutine
            } else if (subcommand.equals("leave")) {
                //TODO: Leave player subroutine
            } else {
                return false;
            }
            return true;
        }

        //info command
        if (command.equals("info")) {
            //TODO: info commands
        }

        if (command.equals("reset")) {
            //TODO: reset commands
        }
        return false;
    }

    public void sendGroupMessage(final CommandSender sender, final String[] args) {
        Player senderP = (Player) sender;
    }
}
