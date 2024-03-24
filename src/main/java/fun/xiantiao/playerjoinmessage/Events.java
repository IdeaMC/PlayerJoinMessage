package fun.xiantiao.playerjoinmessage;

import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static fun.xiantiao.playerjoinmessage.PlayerJoinMessage.sendAllPlayer;

/**
 * @author xiantiao
 * @date 2024/3/24
 * PlayerJoinMessage
 */
public class Events implements Listener {
    protected static List<String> sendFirstJoinMessage;

    @EventHandler
    public void playerJoin(PlayerJoinEvent event) {
        if (getConfig().getBoolean("notSendJoinMessage")) {
            event.setJoinMessage(null);
        }

        if (sendFirstJoinMessage.contains(event.getPlayer().getName())) {
            for (String message : getConfig().getStringList("JoinMessage")) {
                sendAllPlayer(message,event.getPlayer());
            }
        } else {
            for (String message : getConfig().getStringList("firstJoinMessage")) {
                sendAllPlayer(message,event.getPlayer());
            }
        }
    }

    @EventHandler
    public void preJoin(AsyncPlayerPreLoginEvent event) {
        if (getServer().getOfflinePlayer(event.getUniqueId()).hasPlayedBefore() && !sendFirstJoinMessage.contains(event.getName())) {
            sendFirstJoinMessage.add(event.getName());
        }
    }

    private @NotNull FileConfiguration getConfig() {return PlayerJoinMessage.getPlugin().getConfig();}
    private @NotNull Server getServer() {return PlayerJoinMessage.getPlugin().getServer();}
}
