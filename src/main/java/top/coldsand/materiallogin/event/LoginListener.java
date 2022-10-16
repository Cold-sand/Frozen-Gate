package top.coldsand.materiallogin.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.*;

import top.coldsand.materiallogin.login.LoginManager;

public class LoginListener implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) { //不让聊天
        if(e.getMessage().substring(0, 1).equals("/")) //不拦截玩家用命令
            return;
        e.setCancelled(needCancelled(e.getPlayer().getName()));
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) { //不让玩家移动
        e.setCancelled(needCancelled(e.getPlayer().getName()));
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) { //不让玩家跟别的东西交互，约等于屏蔽左右键
        e.setCancelled(needCancelled(e.getPlayer().getName()));
    }

    @EventHandler
    public void onPlayerInventory(InventoryOpenEvent e) { //不让玩家打开背包
        e.setCancelled(needCancelled(e.getPlayer().getName()));
    }

    private boolean needCancelled(String playerName) {
        return !LoginManager.isLogin(playerName);
    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent e) { //玩家登录时修改玩家状态
        LoginManager.setPlayerLogin(e.getPlayer().getName(), false);
    }

    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent e) { //玩家登出时修改玩家状态
        LoginManager.setPlayerLogin(e.getPlayer().getName(), false);
    }

}

