package me.maximelucas.nonbinaires.home.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.Logger;
import me.maximelucas.nonbinaires.common.Utils;
import me.maximelucas.nonbinaires.exceptions.DatabaseException;
import me.maximelucas.nonbinaires.home.IHomeManager;
import me.maximelucas.nonbinaires.home.exceptions.HomeAlreadyExistsException;
import me.maximelucas.nonbinaires.home.exceptions.MaxHomeException;

public class SetHomeCommand implements CommandExecutor {

	private IHomeManager manager;

	public SetHomeCommand(IHomeManager manager) {
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
		Double posX = player.getLocation().getX();
		Double posY = player.getLocation().getY();
		Double posZ = player.getLocation().getZ();
		try {
			this.manager.addHome(playerName, homeName, posX, posY, posZ);
			Logger.log(player, "Home '" + homeName + "' successfully created");
		} catch (HomeAlreadyExistsException e) {
			Logger.log(player, "You already have a home named '" + homeName + "'");
		} catch (MaxHomeException e) {
			Logger.log(player, "You have reached the limit of '" + e.getMaxHome() + "' homes");
		} catch (DatabaseException e) {
			Logger.logInternalError(player, e);
		}
		return true;
	}

}
