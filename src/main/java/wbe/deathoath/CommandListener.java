package wbe.deathoath;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandListener implements CommandExecutor {

    private DeathOath plugin;

    private FileConfiguration config;

    public CommandListener(DeathOath plugin) {
        this.plugin = plugin;
        this.config = this.plugin.getConfig();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("DeathOath")) {
            Player player = null;
            if(sender instanceof Player) {
                player = (Player) sender;
            }

            if(args.length == 0 || args[0].equalsIgnoreCase("help")) {
                if(!sender.hasPermission("deathoath.command.help")) {
                    sender.sendMessage(config.getString("Messages.noPermission").replace("&", "§"));
                    return false;
                }
                for(String x : config.getStringList("Messages.help")) {
                    sender.sendMessage(x.replace("&", "§"));
                }
            } else if(args[0].equalsIgnoreCase("get")) {
                if(!sender.hasPermission("deathoath.command.add")) {
                    sender.sendMessage(config.getString("Messages.noPermission").replace("&", "§"));
                    return false;
                }
                int lifes = 0;
                if(player != null && args.length == 1) {
                    lifes = plugin.getLifes(player.getUniqueId());
                    if(lifes == 1) {
                        sender.sendMessage(config.getString("Messages.lifeMessagePlayer")
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", "")
                                .replace("&", "§"));
                    } else {
                        sender.sendMessage(config.getString("Messages.lifeMessagePlayer")
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", "s")
                                .replace("&", "§"));
                    }
                } else {
                    Player receiver = Bukkit.getServer().getPlayer(args[1]);
                    lifes = plugin.getLifes(receiver.getUniqueId());
                    if(lifes == 1) {
                        sender.sendMessage(config.getString("Messages.lifeMessage")
                                .replace("%player%", receiver.getName())
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", "")
                                .replace("&", "§"));
                    } else {
                        sender.sendMessage(config.getString("Messages.lifeMessage")
                                .replace("%player%", receiver.getName())
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", "s")
                                .replace("&", "§"));
                    }
                }

            } else if(args[0].equalsIgnoreCase("remove")) {
                if(!sender.hasPermission("deathoath.command.remove")) {
                    sender.sendMessage(config.getString("Messages.noPermission").replace("&", "§"));
                    return false;
                }
                if(args.length < 3) {
                    sender.sendMessage(config.getString("Messages.notEnoughArgs").replace("&", "§"));
                    return false;
                }
                Player receiver = Bukkit.getServer().getPlayer(args[1]);
                int lifes = Integer.valueOf(args[2]);
                if(lifes < 1) {
                    sender.sendMessage(config.getString("Messages.cannotBeNegative").replace("&", "§"));
                    return false;
                }
                boolean ok = plugin.removeLifes(receiver.getUniqueId(), lifes);
                if(ok) {
                    if(lifes == 1) {
                        sender.sendMessage(config.getString("Messages.lifeRemoved")
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%player%", receiver.getName())
                                .replace("%s%", "")
                                .replace("&", "§"));
                        receiver.sendMessage(config.getString("Messages.lifeRemovedPlayer")
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", "")
                                .replace("&", "§"));
                    } else {
                        sender.sendMessage(config.getString("Messages.lifeRemoved")
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%player%", receiver.getName())
                                .replace("%s%", "s")
                                .replace("&", "§"));
                        receiver.sendMessage(config.getString("Messages.lifeRemovedPlayer")
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", "s")
                                .replace("&", "§"));
                    }
                    return true;
                }
                return false;
            } else if(args[0].equalsIgnoreCase("add")) {
                if(!sender.hasPermission("deathoath.command.add")) {
                    sender.sendMessage(config.getString("Messages.noPermission").replace("&", "§"));
                    return false;
                }
                if(args.length < 3) {
                    sender.sendMessage(config.getString("Messages.notEnoughArgs").replace("&", "§"));
                    return false;
                }
                Player receiver = Bukkit.getServer().getPlayer(args[1]);
                int lifes = Integer.valueOf(args[2]);
                if(lifes < 1) {
                    sender.sendMessage(config.getString("Messages.cannotBeNegative").replace("&", "§"));
                    return false;
                }
                boolean ok = plugin.addLifes(receiver.getUniqueId(), lifes);
                if(ok) {
                    if(lifes == 1) {
                        sender.sendMessage(config.getString("Messages.lifeAdded")
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%player%", receiver.getName())
                                .replace("%s%", "")
                                .replace("&", "§"));
                        receiver.sendMessage(config.getString("Messages.lifeAddedPlayer")
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", "")
                                .replace("&", "§"));
                    } else {
                        sender.sendMessage(config.getString("Messages.lifeAdded")
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%player%", receiver.getName())
                                .replace("%s%", "s")
                                .replace("&", "§"));
                        receiver.sendMessage(config.getString("Messages.lifeAddedPlayer")
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", "s")
                                .replace("&", "§"));
                    }
                    return true;
                }
                return false;
            } else if(args[0].equalsIgnoreCase("set")) {
                if(!sender.hasPermission("deathoath.command.set")) {
                    sender.sendMessage(config.getString("Messages.noPermission").replace("&", "§"));
                    return false;
                }
                if(args.length < 3) {
                    sender.sendMessage(config.getString("Messages.notEnoughArgs").replace("&", "§"));
                    return false;
                }
                Player receiver = Bukkit.getServer().getPlayer(args[1]);
                int lifes = Integer.valueOf(args[2]);
                if(lifes < 1) {
                    sender.sendMessage(config.getString("Messages.cannotBeNegative").replace("&", "§"));
                    return false;
                }
                boolean ok = plugin.setLifes(receiver.getUniqueId(), lifes);
                if(ok) {
                    sender.sendMessage(config.getString("Messages.lifeSet")
                            .replace("%lifes%", String.valueOf(lifes))
                            .replace("%player%", receiver.getName())
                            .replace("&", "§"));
                    receiver.sendMessage(config.getString("Messages.lifeSetPlayer")
                            .replace("%lifes%", String.valueOf(lifes))
                            .replace("&", "§"));
                    return true;
                }
                return false;
            }
        }
        return true;
    }
}
