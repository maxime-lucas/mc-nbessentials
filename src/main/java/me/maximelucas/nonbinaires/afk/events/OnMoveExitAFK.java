package me.maximelucas.nonbinaires.afk.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.maximelucas.nonbinaires.afk.IAFKManager;

public class OnMoveExitAFK implements Listener {

	private IAFKManager manager;

	public OnMoveExitAFK(IAFKManager manager) {
		this.manager = manager;
	}

	@EventHandler
	public void onMoveExitAFK(PlayerMoveEvent event) {
		if (event.getFrom().getBlockX() != event.getTo().getBlockX()
				|| event.getFrom().getBlockY() != event.getTo().getBlockY()
				|| event.getFrom().getBlockZ() != event.getTo().getBlockZ()) {
			String playerName = event.getPlayer().getName();
			if (manager.isAFK(playerName))
				manager.toggleAFK(playerName);
		}
	}
}
