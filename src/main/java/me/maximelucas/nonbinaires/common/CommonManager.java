package me.maximelucas.nonbinaires.common;

import org.bukkit.command.CommandExecutor;

import me.maximelucas.nonbinaires.NBEssentials;

public abstract class CommonManager {
	protected NBEssentials main;

	public CommonManager() {

	}

	public abstract void init();

	public CommonManager(NBEssentials nbEssentials) {
		this.main = nbEssentials;
	}

	public NBEssentials getMain() {
		return main;
	}

	public void setMain(NBEssentials main) {
		this.main = main;
	}

	protected void registerCommand(String pCommandName, CommandExecutor pCommand) {
		this.main.getCommand(pCommandName).setExecutor(pCommand);
	}

}
