package me.senno.mineAnnouncers.Commands;

import com.muhammaddaffa.mdlib.commandapi.CommandAPICommand;
import com.muhammaddaffa.mdlib.commandapi.arguments.*;
import com.muhammaddaffa.mdlib.utils.Placeholder;
import me.senno.mineAnnouncers.MineAnnouncers;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.*;

public class WTSCommand {
    FileConfiguration config = MineAnnouncers.DEFAULT_CONFIG.getConfig();
    private List<String> badWords;
    private  final HashMap<UUID, Long> cooldown;
    private final CommandAPICommand command;
    private final HashSet<UUID> Muted;
    private final int cooldownTime = config.getInt("cooldown");
    public WTSCommand() {
        this.Muted = new HashSet<>();
        this.cooldown = new HashMap<>();
        this.badWords = new ArrayList<>();
        filterWord();

        this.command = new CommandAPICommand("wts")
                .withArguments(new IntegerArgument("price"))
                .withArguments(new GreedyStringArgument("item"))

                .executes(((sender, args) -> {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage("§cOnly players can use this command.");
                        return;
                    }

                    Player player = (Player) sender;
                    UUID playerUUID = player.getUniqueId();


                    if (Muted.contains(playerUUID)) {
                        MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender, "messages.muted-message");
                        return;
                    }

                    if (cooldown.containsKey(playerUUID)) {
                        long timeLeft = ((cooldown.get(playerUUID) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
                        if (timeLeft > 0) {
                            MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender, "messages.cooldown-message", new Placeholder()
                                    .add("{timeleft}", timeLeft));
                            //player.sendMessage("You must wait " + timeLeft + " seconds before using this command again!");
                            return;
                        }
                    }

                    String item = (String) args.get("item");
                    int priceItem = (int) args.get("price");

                    if (containsBadWord(item)) {
                        MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender, "messages.badword-messages");
                        return;
                    }

                    MineAnnouncers.DEFAULT_CONFIG.broadcast("messages.wts", new Placeholder()
                            .add("{player}", player.getName())
                            .add("{item}", item)
                            .add("{priceitem}", priceItem));


                    cooldown.put(playerUUID, System.currentTimeMillis());
                }));
        this.command.register();

        CommandAPICommand WTBCommand = new CommandAPICommand("wtb")
                //.withArguments(new IntegerArgument("price"))
                .withArguments(new GreedyStringArgument("item"))

                .executes(((sender, args) -> {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage("§cOnly players can use this command.");
                        return;
                    }

                    Player player = (Player) sender;
                    UUID playerUUID = player.getUniqueId();


                    //String senderName = sender instanceof CommandSender ? sender.getName() : "Console";
                    if (Muted.contains(playerUUID)) {
                        MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender, "messages.muted-message");
                        return;
                    }

                    if (cooldown.containsKey(playerUUID)) {
                        long timeLeft = ((cooldown.get(playerUUID) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
                        if (timeLeft > 0) {
                            MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender, "messages.cooldown-message", new Placeholder()
                                    .add("{timeleft}", timeLeft));
                            return;
                        }
                    }

                    String item = (String) args.get("item");

                    if (containsBadWord(item)) {
                        MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender, "messages.badword-messages");
                        return;
                    }

                    MineAnnouncers.DEFAULT_CONFIG.broadcast("messages.wtb", new Placeholder()
                            .add("{player}", player.getName())
                            .add("{item}", item));


                    cooldown.put(playerUUID, System.currentTimeMillis());
                }));
        WTBCommand.register();

        CommandAPICommand ADSCommand = new CommandAPICommand("ads")
                //.withArguments(new StringArgument("price"))
                .withArguments(new GreedyStringArgument("description"))

                .executes(((sender, args) -> {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage("§cOnly players can use this command.");
                        return;
                    }

                    Player player = (Player) sender;
                    UUID playerUUID = player.getUniqueId();

                    if (Muted.contains(playerUUID)) {
                        MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender, "messages.muted-message");
                        return;
                    }


                    //String senderName = sender instanceof CommandSender ? sender.getName() : "Console";

                    if (cooldown.containsKey(playerUUID)) {
                        long timeLeft = ((cooldown.get(playerUUID) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
                        if (timeLeft > 0) {
                            MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender, "messages.cooldown-message", new Placeholder()
                                    .add("{timeleft}", timeLeft));
                            return;
                        }
                    }

                    String description = (String) args.get("description");

                    if (containsBadWord(description)) {
                        MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender, "messages.badword-messages");
                        return;
                    }

                    MineAnnouncers.DEFAULT_CONFIG.broadcast("messages.ads", new Placeholder()
                            .add("{player}", player.getName())
                            .add("{description}", description));


                    cooldown.put(playerUUID, System.currentTimeMillis());
                }));
        ADSCommand.register();

        CommandAPICommand WORKCommand = new CommandAPICommand("work")
                .withArguments(new IntegerArgument("number people"))
                .withArguments(new IntegerArgument("pay"))
                .withArguments(new GreedyStringArgument("work"))

                .executes(((sender, args) -> {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage("§cOnly players can use this command.");
                        return;
                    }

                    Player player = (Player) sender;
                    UUID playerUUID = player.getUniqueId();

                    if (Muted.contains(playerUUID)) {
                        MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender, "messages.muted-message");
                        return;
                    }


                    //String senderName = sender instanceof CommandSender ? sender.getName() : "Console";

                    if (cooldown.containsKey(playerUUID)) {
                        long timeLeft = ((cooldown.get(playerUUID) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
                        if (timeLeft > 0) {
                            MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender, "messages.cooldown-message", new Placeholder()
                                    .add("{timeleft}", timeLeft));
                            return;
                        }
                    }

                    int people = (int) args.get("number people");
                    int pay = (int) args.get("pay");
                    String work = (String) args.get("work");

                    if (containsBadWord(work)) {
                        MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender, "messages.badword-messages");
                        return;
                    }

                    MineAnnouncers.DEFAULT_CONFIG.broadcast("messages.ads", new Placeholder()
                            .add("{player}", player.getName())
                            .add("{people}", people)
                            .add("{pay}", pay)
                            .add("{work}", work));


                    cooldown.put(playerUUID, System.currentTimeMillis());
                }));
        WORKCommand.register();
        CommandAPICommand MuteCommand = new CommandAPICommand("muteadvertise")
                .withArguments(new PlayerArgument("target"))
                .withPermission("mineannouncers.mute")

                .executes(((sender, args) -> {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage("§cOnly players can use this command.");
                        return;
                    }

                    Player target = (Player) args.get("target");
                    UUID targetUUID = target.getUniqueId();

                    if (target == null) {
                        sender.sendMessage("Offline player");
                        return;
                    }
                    if (Muted.contains(targetUUID)) {
                        MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender, "messages.failed-mute");
                        return;
                    }

                    Muted.add(targetUUID);


                    MineAnnouncers.DEFAULT_CONFIG.sendMessage(target,"messages.mute", new Placeholder()
                            .add("{player}", sender.getName())
                            .add("{target}", target.getName()));
                    MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender,"messages.mute-sender", new Placeholder()
                            .add("{player}", sender.getName())
                            .add("{target}", target.getName()));
                    //cooldown.put(playerUUID, System.currentTimeMillis());
                }));
        MuteCommand.register();
    CommandAPICommand UnMuteCommand = new CommandAPICommand("unmuteadvertise")
            .withArguments(new PlayerArgument("target"))
            .withPermission("mineannouncers.unmute")

            .executes(((sender, args) -> {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§cOnly players can use this command.");
                    return;
                }


                Player target = (Player) args.get("target");
                UUID targetUUID = target.getUniqueId();


                if (target == null) {
                    sender.sendMessage("Offline player");
                    return;
                }
                if (!Muted.contains(targetUUID)) {
                    MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender, "messages.failed-unmute");
                    return;
                }

                Muted.remove(targetUUID);


                MineAnnouncers.DEFAULT_CONFIG.sendMessage(target,"messages.unmute", new Placeholder()
                        .add("{player}", sender.getName())
                        .add("{target}", target.getName()));
                MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender,"messages.unmute-sender", new Placeholder()
                        .add("{player}", sender.getName())
                        .add("{target}", target.getName()));
                //cooldown.put(playerUUID, System.currentTimeMillis());
            }));
        UnMuteCommand.register();
}


    public void filterWord() {
        //badWords = new ArrayList<>();
        FileConfiguration config = MineAnnouncers.DEFAULT_CONFIG.getConfig();
        badWords = config.getStringList("badword-list");
    }

    public boolean containsBadWord(String input) {
        for (String word : badWords) {
            if (input.toLowerCase().contains(word.toLowerCase())) {
                return true; // Found a bad word
            }
        }
        return false; // No bad words found
    }
}
