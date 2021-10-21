package main.CMDHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class CMDHandler implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        switch (s) {
            case "오징어게임":
                if (commandSender.isOp()) SquidHandler.onCommand(commandSender, strings);
                break;
            case "홀짝":
                if (SquidHandler.isStarted3) {
                    if (strings.length == 0) commandSender.sendMessage("/홀짝 홀 혹은 /홀짝 짝 을 입력해주세요.");
                    else if (strings[0].equals("홀")) {
                        if (!SquidHandler.isTwo) SquidHandler.notSuccess.add((Player) commandSender);
                        else SquidHandler.notSuccess.remove((Player) commandSender);
                        commandSender.sendMessage("성공적으로 홀에 선택하셨습니다.");
                    } else if (strings[0].equals("짝")) {
                        if (SquidHandler.isTwo) SquidHandler.notSuccess.add((Player) commandSender);
                        else SquidHandler.notSuccess.remove((Player) commandSender);
                        commandSender.sendMessage("성공적으로 짝에 선택하셨습니다.");
                    } else commandSender.sendMessage("/홀짝 홀 혹은 /홀짝 짝 을 입력해주세요.");
                } else commandSender.sendMessage("홀짝 게임이 시작되지 않았습니다.");
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
