package fun.xiantiao.playerjoinmessage;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * @author xiantiao
 * @date 2024/3/23
 * PlayerJoinMessage
 */
public class PJMCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, String[] args) {
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender; 
        }
        if (sender.isOp() || sender.hasPermission("playerjoinmessage.reload")) {
            if (args.length == 0) {
                sender.sendMessage("PlayerJoinMessage by. xiantiao");
            }

            if (args.length == 1 && "reload".equalsIgnoreCase(args[0])) {
                PlayerJoinMessage.getPlugin().reloadConfig();
                sender.sendMessage("=====first=====OK============");
                for (String message : PlayerJoinMessage.getPlugin().getConfig().getStringList("firstJoinMessage")) {
                    if (player != null) {
                        message = message.replaceAll("\\{player}",player.getName());
                    }
                    message = message.replaceAll("&","§");
                    sender.sendMessage(message);
                }
                sender.sendMessage("=============================");

                PlayerJoinMessage.getPlugin().reloadConfig();
                sender.sendMessage("==============OK=============");
                for (String message : PlayerJoinMessage.getPlugin().getConfig().getStringList("JoinMessage")) {
                    if (player != null) {
                        message = message.replaceAll("\\{player}",player.getName());
                    }
                    message = message.replaceAll("&","§");
                    sender.sendMessage(message);
                }
                sender.sendMessage("=============================");
            }
        }
        return true;
    }
}
