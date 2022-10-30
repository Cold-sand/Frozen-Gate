package top.coldsand.frozengate.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import top.coldsand.frozengate.login.LoginManager;

public class LoginCommand implements Listener, CommandExecutor {
    @EventHandler //拦截非登录指令
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        if (LoginManager.isLogin(e.getPlayer().getName()))
            return;

        e.setCancelled(true);
        if (e.getMessage().split(" ")[0].contains("login")
                || e.getMessage().split(" ")[0].contains("register"))
            e.setCancelled(false);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg) {
        if (!(sender instanceof Player)) //判断是Login还是register操作
            return false;

        Player p = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("login"))
            loginCommand(p, arg);
        else if (cmd.getName().equalsIgnoreCase("register"))
            registerCommand(p, arg);
        return true;
    }

    private void loginCommand(Player p, String[] args) { //login操作
        if (LoginManager.isLogin(p.getName())) {
            p.sendMessage("你已经登录了！");
            return;
        }
        if (LoginManager.isRegister(p.getName())) {
            p.sendMessage(ChatColor.RED + "你还没有注册！");
            return;
        }
        if (args.length != 1) {
            p.sendMessage(ChatColor.RED + "登录指令使用错误！");
            return;
        }
        if (LoginManager.isCorrectPassword(p.getName())) {
            p.sendMessage(ChatColor.GREEN + "登录成功！");
            LoginManager.setPlayerLogin(p.getName(), true);
        }
    }

    private void registerCommand(Player p, String[] args) { //register操作
        if (LoginManager.isLogin(p.getName())) {
            p.sendMessage("你已经登录了！");
            return;
        }
        if (LoginManager.isRegister(p.getName().toLowerCase())) {
            p.sendMessage("你已经注册了！");
            return;
        }
        if (args.length != 1) {
            p.sendMessage(ChatColor.RED + "注册指令使用错误！");
            return;
        }

        String key = args[0].trim();

        LoginManager.register(p.getName().toLowerCase() , key);
        p.sendMessage(ChatColor.GREEN + "注册成功！请登录！");
    }
}
