package me.maximelucas.nonbinaires.vanish.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.maximelucas.nonbinaires.vanish.IVanishManager;

public class VanishEventsHandler implements Listener {
	private IVanishManager manager;

	public VanishEventsHandler(IVanishManager manager) {
		this.manager = manager;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		manager.hideVanishedPlayersToSpecificPlayer(player);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		manager.removePlayerFromVanishedPlayers(player);
	}
}
