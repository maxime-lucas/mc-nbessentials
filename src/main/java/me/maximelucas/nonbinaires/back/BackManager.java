package me.maximelucas.nonbinaires.back;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.Logger;
import me.maximelucas.nonbinaires.NBEssentials;
import me.maximelucas.nonbinaires.back.commands.BackCommand;
import me.maximelucas.nonbinaires.back.events.BackEventHandler;
import me.maximelucas.nonbinaires.common.CommonManager;

public class BackManager extends CommonManager implements IBackManager {

	private Map<String, Location> previousLocations;

	public BackManager(NBEssentials main) {
		super(main);
		this.previousLocations = new HashMap<String, Location>();
	}

	@Override
	public void registerCommands() {
		registerCommand("back", new BackCommand(this));
	}

	@Override
	public void registerEvents() {
		main.getServer().getPluginManager().registerEvents(new BackEventHandler(this), this.main);
	}

	@Override
	public void teleportPlayerToPreviousLocation(Player pPlayer) {
		String playerName = pPlayer.getName();
		Location previousLocation = this.previousLocations.get(playerName);
		if (previousLocation == null) {
			Logger.log(pPlayer, "You have no back available");
		} else {
			pPlayer.teleport(previousLocation);
			Logger.log(pPlayer, "You have been teleported to your previous location");
		}
	}

	@Override
	public void registerPlayerPreviousLocation(Player pPlayer, Location pLocation) {
		String playerName = pPlayer.getName();
		this.previousLocations.put(playerName, pLocation);
	}

	@Override
	public void removePlayerPreviousLocation(Player pPlayer) {
		String playerName = pPlayer.getName();
		this.previousLocations.remove(playerName);
	}
}
