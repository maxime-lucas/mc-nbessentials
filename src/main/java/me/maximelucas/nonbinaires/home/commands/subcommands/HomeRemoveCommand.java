package me.maximelucas.nonbinaires.home.commands.subcommands;

import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.Logger;
import me.maximelucas.nonbinaires.exceptions.DatabaseException;
import me.maximelucas.nonbinaires.home.IHomeManager;
import me.maximelucas.nonbinaires.home.exceptions.NoSuchHomeException;

public class HomeRemoveCommand extends HomeAbstractCommand {

	public HomeRemoveCommand(IHomeManager manager) {
		super(manager);
	}

	@Override
	public String getName() {
		return "remove";
	}

	@Override
	public String getDescription() {
		return "Remove a home";
	}

	@Override
	public String getSyntax() {
		return "/home remove <name>";
	}

	@Override
	public void perform(Player player, String[] args) {
		if (args.length != 2) {
			sendSyntaxError(player);
		} else {
			String playerName = player.getName();
			String homeName = args[1];

			try {
				this.manager.removeHome(playerName, homeName);
				Logger.log(player, "Home '" + homeName + "' successfully removed");
			} catch (NoSuchHomeException e) {
				Logger.log(player, "There is no such home '" + e.getHomeName() + "'");
			} catch (DatabaseException e) {
				Logger.logInternalError(player, e);
			}

		}
	}

}
