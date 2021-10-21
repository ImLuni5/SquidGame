package main.CMDHandler;

import main.Main;
import main.TimerHandler.Timer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SquidHandler {

    private static final String index = Main.index;
    public static boolean isPvp = true;
    private static final Main pl = Main.getPlugin(Main.class);
    public static int taskId = -1;
    public static List<Player> notSuccess = new ArrayList<>();
    public static boolean isStarted = false;
    public static boolean isStarted2 = false;
    public static boolean isStarted3 = false;
    public static boolean isTwo = false;

    public static void onCommand(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        if (args[0].equals("pvp")) {
            isPvp = !isPvp;
            player.getWorld().setPVP(isPvp);
            if (isPvp) Bukkit.broadcastMessage(index + "PVP가 활성화 되었습니다.");
            else Bukkit.broadcastMessage(index + "PVP가 비활성화 되었습니다.");
        } else if (args[0].equals("시작") && !isStarted) {
            if (args[1].equals("1")) {
                isStarted = true;
                notSuccess.addAll(Bukkit.getOnlinePlayers());
                for (Player player1 : Bukkit.getOnlinePlayers())
                    player1.playSound(player1.getLocation(), "squidgame.mugunghwa.narration_1", 100.0f, 1.0f);
                Bukkit.getScheduler().scheduleSyncDelayedTask(pl, () -> {
                    for (Player player1 : Bukkit.getOnlinePlayers())
                        player1.playSound(player1.getLocation(), "squidgame.mugunghwa.narration_2", 100.0f, 1.0f);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(pl, () -> {
                        isStarted2 = true;
                        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new Timer(), 0L, 20L);
                    }, 160);
                }, 360L);
            } else if (args[1].equals("2") && !isStarted3) {
                isStarted3 = true;
                Random random = new Random();
                isTwo = random.nextBoolean();
                Bukkit.broadcastMessage(index + "/홀짝 홀 혹은 /홀짝 짝 을 입력해주세요. 20초 이내에 입력하지 않으면 자동 탈락이 됩니다.");
                Bukkit.getScheduler().scheduleSyncDelayedTask(pl, () -> {
                    for (Player player1 : Bukkit.getOnlinePlayers())
                        if (!notSuccess.contains(player1)) {
                            player1.kickPlayer("탈락");
                            Bukkit.broadcastMessage(index + player1.getName() + " 탈락!");
                        }
                    if (isTwo) Bukkit.broadcastMessage(index + "짝이였습니다.");
                    else Bukkit.broadcastMessage(index + "홀이였습니다.");
                    isStarted3 = false;
                }, 400L);
            }
        } else if (args[0].equals("종료")) {
            Bukkit.getScheduler().cancelTasks(pl);
            Bukkit.broadcastMessage(index + "관리자가 게임을 중단하였습니다.");
        }
    }
}
