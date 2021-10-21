package main;

import main.CMDHandler.SquidHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventHandler implements Listener {

    private static final String index = Main.index;
    public static boolean isLook = false;

    @org.bukkit.event.EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(index + e.getPlayer().getName() + "님이 오징어게임에 참가하셨습니다.");
    }

    @org.bukkit.event.EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(index + e.getPlayer().getName() + "님이 오징어게임에 퇴장하셨습니다.");
    }

    @org.bukkit.event.EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (!e.getPlayer().isOp()) e.setCancelled(true);
    }

    @org.bukkit.event.EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (isLook && SquidHandler.notSuccess.contains(e.getPlayer())) {
            e.getPlayer().kickPlayer("탈락");
            Bukkit.broadcastMessage(index + e.getPlayer().getName() + " 탈락!");
            for (Player player1 : Bukkit.getOnlinePlayers())
                player1.playSound(player1.getLocation(), "squidgame.mugunghwa.gun", 100.0f, 1.0f);
        }
        if (SquidHandler.isStarted && SquidHandler.notSuccess.contains(e.getPlayer())) {
            if (e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WOOL) {
                SquidHandler.notSuccess.remove(e.getPlayer());
                Bukkit.broadcastMessage(index + e.getPlayer().getName() + " 성공!");
            }
        }
        if (!SquidHandler.isStarted2) if (e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.CONCRETE) {
            Location location = e.getPlayer().getLocation();
            location.setX(location.getX() - 1);
            e.getPlayer().teleport(location);
        }
    }
}