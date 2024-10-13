package wbe.deathoath.commads;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import wbe.deathoath.DeathOath;
import wbe.deathoath.items.Life;
import wbe.deathoath.util.Utilities;

public class CommandListener implements CommandExecutor {

    private DeathOath plugin = DeathOath.getInstance();

    private Utilities utilities;

    public CommandListener() {
        this.utilities = new Utilities();
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("DeathOath")) {
            Player player = null;
            if (sender instanceof Player) {
                player = (Player) sender;
            }

            if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
                if (!sender.hasPermission("deathoath.command.help")) {
                    sender.sendMessage(DeathOath.messages.noPermission);
                    return false;
                }
                for (String x : DeathOath.messages.help) {
                    sender.sendMessage(x.replace("&", "§"));
                }
            } else if (args[0].equalsIgnoreCase("get")) {
                if (!sender.hasPermission("deathoath.command.get")) {
                    sender.sendMessage(DeathOath.messages.noPermission);
                    return false;
                }
                int lifes = 0;
                if (player != null && args.length == 1) {
                    lifes = utilities.getLifes(player.getUniqueId());
                    if (lifes == 1) {
                        sender.sendMessage(DeathOath.messages.lifeMessagePlayer
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", ""));
                    } else {
                        sender.sendMessage(DeathOath.messages.lifeMessagePlayer
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", "s"));
                    }
                } else {
                    Player receiver = Bukkit.getServer().getPlayer(args[1]);
                    lifes = utilities.getLifes(receiver.getUniqueId());
                    if (lifes == 1) {
                        sender.sendMessage(DeathOath.messages.lifeMessage
                                .replace("%player%", receiver.getName())
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", ""));
                    } else {
                        sender.sendMessage(DeathOath.messages.lifeMessage
                                .replace("%player%", receiver.getName())
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", "s"));
                    }
                }

            } else if (args[0].equalsIgnoreCase("remove")) {
                if (!sender.hasPermission("deathoath.command.remove")) {
                    sender.sendMessage(DeathOath.messages.noPermission);
                    return false;
                }
                if (args.length < 3) {
                    sender.sendMessage(DeathOath.messages.notEnoughArgs);
                    sender.sendMessage(DeathOath.messages.lifeRemoveArgs);
                    return false;
                }
                Player receiver = Bukkit.getServer().getPlayer(args[1]);
                int lifes = Integer.parseInt(args[2]);
                if (lifes < 1) {
                    sender.sendMessage(DeathOath.messages.cannotBeNegative);
                    return false;
                }
                boolean ok = utilities.removeLifes(receiver.getUniqueId(), lifes);
                if (ok) {
                    if (lifes == 1) {
                        sender.sendMessage(DeathOath.messages.lifeRemoved
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%player%", receiver.getName())
                                .replace("%s%", ""));
                        receiver.sendMessage(DeathOath.messages.lifeRemovedPlayer
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", ""));
                    } else {
                        sender.sendMessage(DeathOath.messages.lifeRemoved
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%player%", receiver.getName())
                                .replace("%s%", "s"));
                        receiver.sendMessage(DeathOath.messages.lifeRemovedPlayer
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", "s"));
                    }
                    return true;
                }
                return false;
            } else if (args[0].equalsIgnoreCase("add")) {
                if (!sender.hasPermission("deathoath.command.add")) {
                    sender.sendMessage(DeathOath.messages.noPermission);
                    return false;
                }
                if (args.length < 3) {
                    sender.sendMessage(DeathOath.messages.notEnoughArgs);
                    sender.sendMessage(DeathOath.messages.lifeAddArgs);
                    return false;
                }
                Player receiver = Bukkit.getServer().getPlayer(args[1]);
                int lifes = Integer.parseInt(args[2]);
                if (lifes < 1) {
                    sender.sendMessage(DeathOath.messages.cannotBeNegative);
                    return false;
                }
                boolean ok = utilities.addLifes(receiver.getUniqueId(), lifes);
                if (ok) {
                    if (lifes == 1) {
                        sender.sendMessage(DeathOath.messages.lifeAdded
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%player%", receiver.getName())
                                .replace("%s%", ""));
                        receiver.sendMessage(DeathOath.messages.lifeAddedPlayer
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", ""));
                    } else {
                        sender.sendMessage(DeathOath.messages.lifeAdded
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%player%", receiver.getName())
                                .replace("%s%", "s"));
                        receiver.sendMessage(DeathOath.messages.lifeAddedPlayer
                                .replace("%lifes%", String.valueOf(lifes))
                                .replace("%s%", "s"));
                    }
                    return true;
                }
                return false;
            } else if (args[0].equalsIgnoreCase("set")) {
                if (!sender.hasPermission("deathoath.command.set")) {
                    sender.sendMessage(DeathOath.messages.noPermission);
                    return false;
                }

                if (args.length < 3) {
                    sender.sendMessage(DeathOath.messages.notEnoughArgs);
                    sender.sendMessage(DeathOath.messages.lifeSetArgs);
                    return false;
                }
                Player receiver = Bukkit.getServer().getPlayer(args[1]);
                int lifes = Integer.parseInt(args[2]);
                if (lifes < 1) {
                    sender.sendMessage(DeathOath.messages.cannotBeNegative);
                    return false;
                }
                boolean ok = utilities.setLifes(receiver.getUniqueId(), lifes);
                if (ok) {
                    sender.sendMessage(DeathOath.messages.lifeSet
                            .replace("%lifes%", String.valueOf(lifes))
                            .replace("%player%", receiver.getName()));
                    receiver.sendMessage(DeathOath.messages.lifeSetPlayer
                            .replace("%lifes%", String.valueOf(lifes)));
                    return true;
                }
                return false;
            } else if (args[0].equalsIgnoreCase("transfer")) {
                if (!sender.hasPermission("deathoath.command.transfer")) {
                    sender.sendMessage(DeathOath.messages.noPermission);
                    return false;
                }

                if (args.length < 3) {
                    sender.sendMessage(DeathOath.messages.notEnoughArgs);
                    sender.sendMessage(DeathOath.messages.transferArgs);
                    return false;
                }
                Player receiver = Bukkit.getServer().getPlayer(args[1]);
                if (receiver.getUniqueId().equals(player.getUniqueId())) {
                    sender.sendMessage(DeathOath.messages.cannotTransferSelf);
                    return false;
                }
                int lifes = Integer.parseInt(args[2]);
                if (lifes < 1) {
                    sender.sendMessage(DeathOath.messages.cannotBeNegative);
                    return false;
                }

                if (!utilities.hasEnoughLifes(lifes, player.getUniqueId())) {
                    sender.sendMessage(DeathOath.messages.notEnoughLifes);
                    return false;
                }

                boolean ok = utilities.transferLifes(player.getUniqueId(), receiver.getUniqueId(), lifes);
                if (ok) {
                    sender.sendMessage(DeathOath.messages.transferMessage
                            .replace("%lifes%", String.valueOf(lifes))
                            .replace("%player%", receiver.getName())
                            .replace("%s%", lifes > 1 ? "s" : ""));
                    receiver.sendMessage(DeathOath.messages.transferPlayer
                            .replace("%lifes%", String.valueOf(lifes))
                            .replace("%player%", player.getName())
                            .replace("%s%", lifes > 1 ? "s" : ""));
                    return true;
                }
                return false;
            } else if(args[0].equalsIgnoreCase("devilPact")) {
                if(!sender.hasPermission("deathoath.command.devilPact")) {
                    sender.sendMessage(DeathOath.messages.noPermission);
                    return false;
                }

                String type = args[1];
                int amount = Integer.parseInt(args[2]);
                if(type.equalsIgnoreCase("sacrifice")) {
                    utilities.sacrifice(player.getUniqueId(), amount);
                } else if(type.equalsIgnoreCase("regain")) {
                    utilities.regain(player.getUniqueId(), amount);
                }

            } else if(args[0].equalsIgnoreCase("lifeItem")) {
                if(!sender.hasPermission("deathoath.command.lifeItem")) {
                    sender.sendMessage(DeathOath.messages.noPermission);
                    return false;
                }

                int amount = DeathOath.config.baseItemAmount;
                if(args.length > 1) {
                    amount = Integer.parseInt(args[1]);
                }
                Life life = new Life(amount);

                if(args.length > 2) {
                    player = Bukkit.getPlayer(args[2]);
                }

                if(player.getInventory().firstEmpty() == -1) {
                    player.getWorld().dropItem(player.getLocation(), life);
                } else {
                    player.getInventory().addItem(life);
                }
            } else if(args[0].equalsIgnoreCase("reload")) {
                if(!sender.hasPermission("deathoath.command.reload")) {
                    sender.sendMessage(DeathOath.messages.noPermission);
                    return false;
                }

                plugin.reloadConfiguration();
                sender.sendMessage(DeathOath.messages.reload);
            }
        }
        return true;
    }
}
