package me.senno.mineAnnouncers;

import com.muhammaddaffa.mdlib.MDLib;
import com.muhammaddaffa.mdlib.utils.Config;
import com.muhammaddaffa.mdlib.utils.Logger;
import me.senno.mineAnnouncers.Commands.MainCommand;
import me.senno.mineAnnouncers.Commands.WTSCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineAnnouncers extends JavaPlugin {

    public static Config DEFAULT_CONFIG;

    @Override
    public void onLoad() {
        MDLib.inject(this);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        MDLib.onEnable(this);
        DEFAULT_CONFIG = new Config("config.yml", null, true);
        registerCommand();
        Logger.info("""
                MineAnnouncers plugin by Senn0_ - starting...
                
                
               ███╗░░░███╗██╗███╗░░██╗███████╗        ░█████╗░███╗░░██╗███╗░░██╗░█████╗░██╗░░░██╗███╗░░██╗░█████╗░███████╗██████╗░░██████╗
               ████╗░████║██║████╗░██║██╔════╝        ██╔══██╗████╗░██║████╗░██║██╔══██╗██║░░░██║████╗░██║██╔══██╗██╔════╝██╔══██╗██╔════╝
               ██╔████╔██║██║██╔██╗██║█████╗░░        ███████║██╔██╗██║██╔██╗██║██║░░██║██║░░░██║██╔██╗██║██║░░╚═╝█████╗░░██████╔╝╚█████╗░
               ██║╚██╔╝██║██║██║╚████║██╔══╝░░        ██╔══██║██║╚████║██║╚████║██║░░██║██║░░░██║██║╚████║██║░░██╗██╔══╝░░██╔══██╗░╚═══██╗
               ██║░╚═╝░██║██║██║░╚███║███████╗        ██║░░██║██║░╚███║██║░╚███║╚█████╔╝╚██████╔╝██║░╚███║╚█████╔╝███████╗██║░░██║██████╔╝
               ╚═╝░░░░░╚═╝╚═╝╚═╝░░╚══╝╚══════╝        ╚═╝░░╚═╝╚═╝░░╚══╝╚═╝░░╚══╝░╚════╝░░╚═════╝░╚═╝░░╚══╝░╚════╝░╚══════╝╚═╝░░╚═╝╚═════╝░
                """);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Logger.info("[MineAnnouncers] Successfully disabled the plugins! bye bye~~");
        MDLib.shutdown();
    }
    private void registerCommand() {
        new WTSCommand();
        new MainCommand();
    }
}
