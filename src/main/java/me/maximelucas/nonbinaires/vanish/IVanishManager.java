package me.maximelucas.nonbinaires.vanish;

import org.bukkit.entity.Player;

import me.maximelucas.nonbinaires.common.ICommonManager;

public interface IVanishManager extends ICommonManager {
	public void toggleVanish(Player pPlayer);

	public void hideVanishedPlayersToSpecificPlayer(Player pPlayer);

	public void removePlayerFromVanishedPlayers(Player pPlayer);
}
