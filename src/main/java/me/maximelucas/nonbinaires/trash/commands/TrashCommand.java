package me.maximelucas.nonbinaires.trash.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.common.Utils;
import me.maximelucas.nonbinaires.trash.ITrashManager;

public class TrashCommand implements CommandExecutor {

	private ITrashManager manager;

	public TrashCommand(ITrashManager manager) {
		this.manager = manager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			manager.openTrashInventory(player);
		} else {
			Utils.messagePlayerOnly();
		}
		return true;
	}

}
