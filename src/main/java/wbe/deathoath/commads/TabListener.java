package wbe.deathoath.commads;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabListener implements TabCompleter {

    private final List<String> subCommands = Arrays.asList("help", "get", "add", "remove", "set",
            "lifeItem", "transfer", "devilPact", "reload");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList<>();

        if(!command.getName().equalsIgnoreCase("DeathOath")) {
            return completions;
        }

        // Mostrar subcomandos
        if(args.length == 1) {
            StringUtil.copyPartialMatches(args[0], subCommands, completions);
        }

        // Argumento 1
        if(args.length == 2) {
            switch(args[0].toLowerCase()) {
                case "get":
                case "add":
                case "remove":
                case "set":
                case "transfer":
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        if(args[1].isEmpty()) {
                            completions.add(player.getName());
                        } else if(player.getName().startsWith(args[1])) {
                            completions.add(player.getName());
                        }
                    }
                    break;
                case "lifeitem":
                    completions.add("<Cantidad de vidas>");
                    break;
                case "devilpact":
                    completions.add("sacrifice");
                    completions.add("regain");
                    break;
            }
        }

        // Argumento 2
        if(args.length == 3) {
            switch(args[0].toLowerCase()) {
                case "add":
                case "remove":
                case "set":
                case "transfer":
                    completions.add("<Cantidad de vidas>");
                    break;
                case "lifeitem":
                    for(Player player : Bukkit.getOnlinePlayers()) {
                        if(args[2].isEmpty()) {
                            completions.add(player.getName());
                        } else if(player.getName().startsWith(args[2])) {
                            completions.add(player.getName());
                        }
                    }
                    break;
                case "devilpact":
                    if(args[1].equalsIgnoreCase("sacrifice")) {
                        completions.add("<Cantidad de contenedores>");
                    } else if(args[1].equalsIgnoreCase("regain")) {
                        completions.add("<Cantidad de vidas>");
                    }
                    break;
            }
        }

        return completions;
    }
}
