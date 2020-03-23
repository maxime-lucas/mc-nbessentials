package me.maximelucas.nonbinaires.home.commands.subcommands;

import java.util.List;

import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.Logger;
import me.maximelucas.nonbinaires.home.IHomeManager;

public class HomeListCommand extends HomeAbstractCommand {

	public HomeListCommand(IHomeManager manager) {
		super(manager);
	}

	@Override
	public String getName() {
		return "list";
	}

	@Override
	public String getDescription() {
		return "Get the list of your homes";
	}

	@Override
	public String getSyntax() {
		return "/home list";
	}

	@Override
	public void perform(Player player, String[] args) {
		if (args.length != 1) {
			sendSyntaxError(player);
		} else {
			try {
				List<String> playerHomes = this.manager.listHomes(player.getName());
				if (playerHomes.size() == 0) {
					Logger.log(player, "You don't have any home");
				} else {
					StringBuilder sb = new StringBuilder();
					for (String home : playerHomes) {
						sb.append(home + ", ");
					}
					sb.delete(sb.length() - 2, sb.length() - 1);
					Logger.log(player, "Homes : " + sb.toString());
				}
			} catch (Exception e) {
				Logger.logInternalError(player, e);
			}
		}
	}

}
