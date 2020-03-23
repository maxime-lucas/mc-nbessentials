package me.maximelucas.nonbinaires.afk;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import me.maximelucas.nonbinaires.NBEssentials;
import me.maximelucas.nonbinaires.afk.commands.AFKCommand;
import me.maximelucas.nonbinaires.afk.events.OnMoveExitAFK;
import me.maximelucas.nonbinaires.common.CommonManager;
import me.maximelucas.nonbinaires.common.Properties;

public class AFKManager extends CommonManager implements IAFKManager {

	private List<String> afkPlayers;

	public AFKManager(NBEssentials nbEssentials) {
		super(nbEssentials);
		this.afkPlayers = new ArrayList<String>();
	}
	
	@Override
	public void registerCommands() {
		registerCommand("afk", new AFKCommand(this));
	}

	@Override
	public void registerEvents() {
		this.main.getServer().getPluginManager().registerEvents(new OnMoveExitAFK(this), main);
	}

	@Override
	public void toggleAFK(String pPlayerName) {
		if (afkPlayers.contains(pPlayerName)) {
			this.main.getServer()
					.broadcastMessage(getBroadcastMessageAndParsePlayerName(Properties.AFK_OFF_MESSAGE, pPlayerName));
			this.afkPlayers.remove(pPlayerName);
		} else {
			this.main.getServer()
					.broadcastMessage(getBroadcastMessageAndParsePlayerName(Properties.AFK_ON_MESSAGE, pPlayerName));
			this.afkPlayers.add(pPlayerName);
		}
	}

	@Override
	public boolean isAFK(String pPlayerName) {
		return afkPlayers.contains(pPlayerName);
	}

	private String getBroadcastMessageAndParsePlayerName(String pMessage, String pPlayerName) {
		String message = this.main.getConfig().getString(pMessage);
		String parsedMessage = message.replace(Properties.AFK_PLAYER_TAG_MESSAGE, pPlayerName);
		return ChatColor.translateAlternateColorCodes('&', parsedMessage);
	}

}
