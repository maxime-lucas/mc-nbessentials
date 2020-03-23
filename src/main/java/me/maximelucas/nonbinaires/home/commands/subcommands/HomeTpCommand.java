package me.maximelucas.nonbinaires.home.commands.subcommands;

import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.Logger;
import me.maximelucas.nonbinaires.exceptions.DatabaseException;
import me.maximelucas.nonbinaires.home.IHomeManager;
import me.maximelucas.nonbinaires.home.exceptions.NoSuchHomeException;

public class HomeTpCommand extends HomeAbstractCommand {

	public HomeTpCommand(IHomeManager manager) {
		super(manager);
	}

	@Override
	public String getName() {
		return "tp";
	}

	@Override
	public String getDescription() {
		return "Teleport to a home";
	}

	@Override
	public String getSyntax() {
		return "/home <name>";
	}

	@Override
	public void perform(Player player, String[] args) {
		if (args.length != 1) {
			sendSyntaxError(player);
		} else {
			try {
				String playerName = player.getName();
				String homeName = args[0];
				manager.teleportPlayerToHome(playerName, homeName);
				Logger.log(player, "You have been successfully teleported to '" + homeName + "'");
			} catch (DatabaseException e) {
				Logger.logInternalError(player, e);
			} catch (NoSuchHomeException e) {
				Logger.log(player, "There is no such home '" + e.getHomeName() + "'");
			}
		}
	}

}
