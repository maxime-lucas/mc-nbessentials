package me.maximelucas.nonbinaires.home.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.common.Utils;
import me.maximelucas.nonbinaires.home.IHomeManager;
import me.maximelucas.nonbinaires.home.commands.subcommands.HomeAbstractCommand;
import me.maximelucas.nonbinaires.home.commands.subcommands.HomeListCommand;
import me.maximelucas.nonbinaires.home.commands.subcommands.HomeRemoveCommand;
import me.maximelucas.nonbinaires.home.commands.subcommands.HomeSetCommand;
import me.maximelucas.nonbinaires.home.commands.subcommands.HomeTpCommand;

public class HomeCommandManager implements CommandExecutor {

	private Map<String, HomeAbstractCommand> availableSubCommands;
	private HomeAbstractCommand homeTpCommand;

	public HomeCommandManager(IHomeManager manager) {
		this.availableSubCommands = new HashMap<String, HomeAbstractCommand>();

		HomeListCommand listCommand = new HomeListCommand(manager);
		HomeSetCommand setCommand = new HomeSetCommand(manager);
		HomeRemoveCommand removeCommand = new HomeRemoveCommand(manager);
		this.homeTpCommand = new HomeTpCommand(manager);

		this.availableSubCommands.put(listCommand.getName(), listCommand);
		this.availableSubCommands.put(setCommand.getName(), setCommand);
		this.availableSubCommands.put(removeCommand.getName(), removeCommand);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			Utils.messagePlayerOnly();
			return true;
		}

		Player player = (Player) sender;

		if (args.length == 0) {
			return false;
		} else if (args.length == 1) {
			if (args[0].equals("list")) {
				this.availableSubCommands.get("list").perform(player, args);
			} else {
				homeTpCommand.perform(player, args);
			}
		} else {
			String homeCommandName = args[0];
			HomeAbstractCommand homeCommand = this.availableSubCommands.get(homeCommandName);
			if (homeCommand != null) {
				homeCommand.perform(player, args);
			} else {
				sendUsage(player);
			}
		}

		return true;
	}

	public void sendUsage(Player pPlayer) {
		pPlayer.sendMessage("Available commands :");
		pPlayer.sendMessage(homeTpCommand.getSyntax() + " - " + homeTpCommand.getDescription());
		for (Entry<String, HomeAbstractCommand> entry : availableSubCommands.entrySet()) {
			HomeAbstractCommand command = entry.getValue();
			pPlayer.sendMessage(command.getSyntax() + " - " + command.getDescription());
		}
	}

}
