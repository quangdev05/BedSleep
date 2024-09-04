package tencer.quangdev05.bedSleep;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BedSleep extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[BedSleep] Plugin đã được kích hoạt!");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[BedSleep] Plugin đã bị tắt!");
    }

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();

        long sleepingPlayers = world.getPlayers().stream()
                .filter(Player::isSleeping).count();

        int onlinePlayers = world.getPlayers().size();
        double percentage = (double) sleepingPlayers / onlinePlayers;

        if (percentage >= 0.5) {
            Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + "Hơn 50% người chơi đang ngủ! Trời sẽ sáng ngay bây giờ.");
            world.setTime(0);
            world.setStorm(false);
        }
    }
}