package fun.xiantiao.playerjoinmessage;

import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 40482
 */
public class PlayerJoinMessage extends JavaPlugin implements Listener {
    private static PlayerJoinMessage plugin;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
        reloadConfig();

        getServer().getPluginManager().registerEvents(new Events(),this);

        PluginCommand playerjoinmessage = getCommand("playerjoinmessage");
        if (playerjoinmessage != null) {
            playerjoinmessage.setExecutor(new PJMCommand());
        }

        loadConfig();

        Events.sendFirstJoinMessage = new ArrayList<>();

        if (getConfig().getBoolean("bStats")) {
            new Metrics(this, 21409);
        }
    }

    static void sendAllPlayer(String message,Player player) {
        for (Player onlinePlayer : getPlugin().getServer().getOnlinePlayers()) {
            message = message.replaceAll("&","§");
            if (player != null) {
                message = message.replaceAll("\\{player}",player.getName());
            }
            onlinePlayer.sendMessage(message);
        }
    }

    private void loadConfig() {
        if (!getConfig().isSet("ver")) {
            getConfig().set("notSendJoinMessage",true);
            getConfig().set("ver",1);
            saveConfig();
        }

        if (getConfig().getInt("ver") == 1) {
            List<String> list = new ArrayList<>();
            list.add("[新玩家加入] &b {player}");
            getConfig().set("firstJoinMessage",list);

            getConfig().set("JoinMessage",getConfig().getStringList("message"));
            getConfig().set("message",null);

            getConfig().set("ver",2);
            saveConfig();
        }

        if (getConfig().getInt("ver") == 2) {
            getConfig().set("bStats",true);
            getConfig().set("ver",3);
            saveConfig();
        }
    }

    public static PlayerJoinMessage getPlugin() {
        return plugin;
    }
}
