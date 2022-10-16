package top.coldsand.materiallogin.login;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import top.coldsand.materiallogin.MaterialLogin;

public class LoginManager {
    //载入playerData配置文件
    static File playerDataFile = new File(MaterialLogin.getPlugin(MaterialLogin.class).getDataFolder() , "playerData.yml");
    static FileConfiguration playerData = YamlConfiguration.loadConfiguration(playerDataFile);

    private static List<String> unloginList = new ArrayList<>(); //创建未登录玩家列表

    //判断玩家是否登录
    public static boolean isLogin(String playerName) {
        return unloginList.contains(playerName);
    }

   //设置玩家登陆状态
    public static void setPlayerLogin(String playerName, boolean loginFlag) {
        if (loginFlag) {
            unloginList.remove(playerName);
        } else {
            unloginList.add(playerName);
        }
    }

    //判断玩家是否注册
    public static boolean isRegister(String playerName) {
        return playerData.contains("player_data"+playerName);
    }

    //玩家注册
    public static boolean register(String playerName , String password) {
        if (isRegister(playerName))
            return false; //未注册返回False

        List<String> key_salt = SaltedHash.createSaltedHash(password) ; //调用加密方法
        String key = key_salt.get(0);
        String salt = key_salt.get(1);

        //设置YML配置文件
        playerData.set("player_data."+playerName+".password", key);
        playerData.set("player_data."+playerName+".salt", salt);

        try {
            playerData.save("playerData.yml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;

    }

    //判断玩家密码是否正确
    public static boolean isCorrectPassword(String playerName) {
    if(!isRegister(playerName))
        return false; //未注册返回False

    //加密后验证密码
    String key = playerData.getString("player_data."+playerName+".password");
    String salt = playerData.getString("player_data."+playerName+".salt");
    String pass = SaltedHash.getSaltedHash(key, salt);

    return key.equals(pass);
    }

}

