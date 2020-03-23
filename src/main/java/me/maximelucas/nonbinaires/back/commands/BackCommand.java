package me.maximelucas.nonbinaires.back.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.back.IBackManager;
import me.maximelucas.nonbinaires.common.Utils;

public class BackCommand implements CommandExecutor {

	private IBackManager manager;

	public BackCommand(IBackManager manager) {
		this.manager = manager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			Utils.messagePlayerOnly();
			return true;
		}
		Player player = (Player) sender;
		manager.teleportPlayerToPreviousLocation(player);
		return true;
	}

}
