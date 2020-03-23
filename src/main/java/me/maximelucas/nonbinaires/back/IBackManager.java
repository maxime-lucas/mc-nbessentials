package me.maximelucas.nonbinaires.back;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.common.ICommonManager;

public interface IBackManager extends ICommonManager {
	public void teleportPlayerToPreviousLocation(Player pPlayer);

	public void registerPlayerPreviousLocation(Player pPlayer, Location pLocation);

	public void removePlayerPreviousLocation(Player pPlayer);
}
