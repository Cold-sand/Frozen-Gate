package top.coldsand.frozengate;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;
import top.coldsand.frozengate.command.LoginCommand;
import top.coldsand.frozengate.event.LoginListener;
import top.coldsand.frozengate.event.TipListener;


public final class FrozenGate extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic 插件启动
        getLogger().info(ChatColor.YELLOW+"MaterialLogin"+ChatColor.BLUE+"插件已启动！");
        getLogger().info(ChatColor.YELLOW+"作者:"+ChatColor.BLUE+"Cold_sand");

        //Configuration 配置文件
        this.saveDefaultConfig();  //生成默认配置文件
        this.saveDefaultConfig();
        this.saveResource("playerData.yml", false);

        //Listener 监听器
        Bukkit.getPluginManager().registerEvents(new LoginListener(), this); //登录
        Bukkit.getPluginManager().registerEvents(new LoginCommand(), this);//登录指令
        Bukkit.getPluginManager().registerEvents(new TipListener(), this);//登录消息

        //Commands 指令
        CommandExecutor login_command = new LoginCommand();  //登录指令注册
        Bukkit.getPluginCommand("login").setExecutor(login_command); //登录指令
        Bukkit.getPluginCommand("register").setExecutor(login_command); //注册指令
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info(ChatColor.YELLOW+"MaterialLogin"+ChatColor.BLUE+"插件已卸载！");

    }
}
