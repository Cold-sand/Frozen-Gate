package top.coldsand.materiallogin.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import top.coldsand.materiallogin.login.LoginManager;


public class TipListener implements Listener {

    //玩家进入服务器后的“请您登录”提示语
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage(
                LoginManager.isRegister(e.getPlayer().getName())?
                        "欢迎回来！请输入/login 密码 登录服务器！":
                        "如果您是第一次来到本服务器！请输入/register 密码 注册账号！"
        );
    }

}
