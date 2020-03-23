package me.maximelucas.nonbinaires.home.commands.subcommands;

import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.home.IHomeManager;

public abstract class HomeAbstractCommand {

	protected IHomeManager manager;

	public HomeAbstractCommand(IHomeManager manager) {
		this.manager = manager;
	}

	public abstract String getName();

	public abstract String getDescription();

	public abstract String getSyntax();

	public abstract void perform(Player player, String[] args);

	public void sendSyntaxError(Player pPlayer) {
		pPlayer.sendMessage("Syntax Error ! " + getSyntax());
	}
}
