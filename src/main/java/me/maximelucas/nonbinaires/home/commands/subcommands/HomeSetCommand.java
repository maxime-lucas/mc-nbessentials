package me.maximelucas.nonbinaires.home.commands.subcommands;

import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.Logger;
import me.maximelucas.nonbinaires.exceptions.DatabaseException;
import me.maximelucas.nonbinaires.home.IHomeManager;
import me.maximelucas.nonbinaires.home.exceptions.HomeAlreadyExistsException;
import me.maximelucas.nonbinaires.home.exceptions.MaxHomeException;

public class HomeSetCommand extends HomeAbstractCommand {

	public HomeSetCommand(IHomeManager manager) {
		super(manager);
	}

	@Override
	public String getName() {
		return "set";
	}

	@Override
	public String getDescription() {
		return "Create a new home";
	}

	@Override
	public String getSyntax() {
		return "/home set <name>";
	}

	@Override
	public void perform(Player player, String[] args) {
		if (args.length != 2) {
			sendSyntaxError(player);
		} else {
			String playerName = player.getName();
			String homeName = args[1];
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
		}
	}

}
