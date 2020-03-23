package me.maximelucas.nonbinaires.home.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.Logger;
import me.maximelucas.nonbinaires.common.Utils;
import me.maximelucas.nonbinaires.exceptions.DatabaseException;
import me.maximelucas.nonbinaires.home.IHomeManager;
import me.maximelucas.nonbinaires.home.exceptions.NoSuchHomeException;

public class DelHomeCommand implements CommandExecutor {

	private IHomeManager manager;

	public DelHomeCommand(IHomeManager manager) {
		this.manager = manager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			Utils.messagePlayerOnly();
			return true;
		}

		if (args.length != 1)
			return false;

		Player player = (Player) sender;
		String playerName = player.getName();
		String homeName = args[0];
		try {
			this.manager.removeHome(playerName, homeName);
			Logger.log(player, "Home '" + homeName + "' successfully removed");
		} catch (NoSuchHomeException e) {
			Logger.log(player, "There is no such home '" + e.getHomeName() + "'");
		} catch (DatabaseException e) {
			Logger.logInternalError(player, e);
		}
		return true;
	}

}
