package me.maximelucas.nonbinaires.back.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import me.maximelucas.nonbinaires.back.IBackManager;

public class BackEventHandler implements Listener {

	public IBackManager manager;

	public BackEventHandler(IBackManager manager) {
		this.manager = manager;
	}

	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		Location previousLocation = event.getFrom();
		Player player = event.getPlayer();
		TeleportCause cause = event.getCause();

		if (TeleportCause.COMMAND.equals(cause) || TeleportCause.PLUGIN.equals(cause))
			manager.registerPlayerPreviousLocation(player, previousLocation);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		manager.removePlayerPreviousLocation(player);
	}
}
