package me.senno.mineAnnouncers.Commands;

import com.muhammaddaffa.mdlib.commandapi.CommandAPICommand;
import com.muhammaddaffa.mdlib.utils.Config;
import me.senno.mineAnnouncers.MineAnnouncers;

public class MainCommand {
    private final CommandAPICommand command;
    public MainCommand() {
        this.command = new CommandAPICommand("mineannouncers")
                .withPermission("mineannouncers.admin")
                .executes((sender, args) -> {
                    Config.reload();
                    MineAnnouncers.DEFAULT_CONFIG.sendMessage(sender, "messages.reload");
                });
        this.command.register();
    }
}
