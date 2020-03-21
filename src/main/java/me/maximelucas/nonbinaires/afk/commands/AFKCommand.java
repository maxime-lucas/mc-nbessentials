package me.maximelucas.nonbinaires.afk.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.afk.IAFKManager;
import me.maximelucas.nonbinaires.common.Utils;

public class AFKCommand implements CommandExecutor {

	private IAFKManager manager;

	public AFKCommand(IAFKManager manager) {
		this.manager = manager;
	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] arg3) {

		if (sender instanceof Player) {
			String playerName = sender.getName();
			manager.toggleAFK(playerName);
		} else {
			Utils.messagePlayerOnly();
		}

		return true;
	}

}
