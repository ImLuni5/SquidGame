package main.TimerHandler;

import main.CMDHandler.SquidHandler;
import main.EventHandler;
import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Random;

public class Timer implements Runnable {

    public static int count = 120;
    private static Main pl = Main.getPlugin(Main.class);
    public static boolean canRun = true;

    @Override
    public void run() {
        Random random = new Random();
        if (Timer.count > 0 && canRun) {
            canRun = false;
            long time = 0;
            switch (random.nextInt(4)) {
                case 0:
                    for (Player player1 : Bukkit.getOnlinePlayers())
                        player1.playSound(player1.getLocation(), "squidgame.mugunghwa.say_1", 100.0f, 1.0f);
                    time = 100L;
                    break;
                case 1:
                    for (Player player1 : Bukkit.getOnlinePlayers())
                        player1.playSound(player1.getLocation(), "squidgame.mugunghwa.say_2", 100.0f, 1.0f);
                    time = 80L;
                    break;
                case 2:
                    for (Player player1 : Bukkit.getOnlinePlayers())
                        player1.playSound(player1.getLocation(), "squidgame.mugunghwa.say_3", 100.0f, 1.0f);
                    time = 80L;
                    break;
                case 3:
                    for (Player player1 : Bukkit.getOnlinePlayers())
                        player1.playSound(player1.getLocation(), "squidgame.mugunghwa.say_4", 100.0f, 1.0f);
                    time = 60L;
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(pl, () -> {
                EventHandler.isLook = true;
                Bukkit.getScheduler().scheduleSyncDelayedTask(pl, () -> {
                    EventHandler.isLook = false;
                    canRun = true;
                }, 60L);
            }, time);
        }
        for (Player player : Bukkit.getOnlinePlayers()) player.sendActionBar(String.valueOf(count));
        if (count <= 0) {
            SquidHandler.isStarted = false;
            SquidHandler.isStarted2 = false;
            for (Player player : SquidHandler.notSuccess) if (player.isOnline()) player.kickPlayer("탈락");
            SquidHandler.notSuccess.clear();
            Bukkit.getScheduler().cancelTask(SquidHandler.taskId);
        }
        count--;
    }
}
