package me.maximelucas.nonbinaires.vanish;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.Logger;
import me.maximelucas.nonbinaires.NBEssentials;
import me.maximelucas.nonbinaires.common.CommonManager;
import me.maximelucas.nonbinaires.vanish.commands.VanishCommand;
import me.maximelucas.nonbinaires.vanish.events.VanishEventsHandler;

public class VanishManager extends CommonManager implements IVanishManager {

	private List<String> vanishedPlayers;

	public VanishManager(NBEssentials main) {
		super(main);
		this.vanishedPlayers = new ArrayList<String>();
	}

	@Override
	public void registerCommands() {
		registerCommand("vanish", new VanishCommand(this));
	}

	@Override
	public void registerEvents() {
		main.getServer().getPluginManager().registerEvents(new VanishEventsHandler(this), main);
	}

	@Override
	public void toggleVanish(Player pPlayer) {
		String playerName = pPlayer.getName();
		if (vanishedPlayers.contains(playerName)) {
			vanishOff(pPlayer);
			Logger.log(pPlayer, "You are now visible to other players");
		} else {
			vanishOn(pPlayer);
			Logger.log(pPlayer, "You are now invisible to other players");
		}
	}

	private void vanishOn(Player pPlayer) {
		for (Player onlinePlayer : main.getServer().getOnlinePlayers()) {
			onlinePlayer.hidePlayer(main, pPlayer);
		}
		vanishedPlayers.add(pPlayer.getName());
	}

	private void vanishOff(Player pPlayer) {
		for (Player onlinePlayer : main.getServer().getOnlinePlayers()) {
			onlinePlayer.showPlayer(main, pPlayer);
		}
		vanishedPlayers.remove(pPlayer.getName());
	}

	@Override
	public void hideVanishedPlayersToSpecificPlayer(Player pPlayer) {
		for (String vanishedPlayerName : vanishedPlayers) {
			Player vanishedPlayer = Bukkit.getPlayer(vanishedPlayerName);
			pPlayer.hidePlayer(main, vanishedPlayer);
		}
	}

	@Override
	public void removePlayerFromVanishedPlayers(Player pPlayer) {
		vanishOff(pPlayer);
	}

}
