package me.maximelucas.nonbinaires.vanish.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.common.Utils;
import me.maximelucas.nonbinaires.vanish.IVanishManager;

public class VanishCommand implements CommandExecutor {
	private IVanishManager manager;

	public VanishCommand(IVanishManager manager) {
		this.manager = manager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			Utils.messagePlayerOnly();
			return true;
		}
		Player player = (Player) sender;
		manager.toggleVanish(player);
		return true;
	}
}
