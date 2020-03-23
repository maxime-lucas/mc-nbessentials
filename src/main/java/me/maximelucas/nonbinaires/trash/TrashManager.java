package me.maximelucas.nonbinaires.trash;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import me.maximelucas.nonbinaires.NBEssentials;
import me.maximelucas.nonbinaires.common.CommonManager;
import me.maximelucas.nonbinaires.trash.commands.TrashCommand;
import net.md_5.bungee.api.ChatColor;

public class TrashManager extends CommonManager implements ITrashManager {

	public TrashManager(NBEssentials nbEssentials) {
		super(nbEssentials);
	}
	
	@Override
	public void registerEvents() {}

	@Override
	public void registerCommands() {
		registerCommand("trash", new TrashCommand(this));
	}

	@Override
	public void openTrashInventory(Player pPlayer) {
		Inventory trash = Bukkit.createInventory(pPlayer, 9, ChatColor.translateAlternateColorCodes('&', "&4&lTrash"));
		pPlayer.openInventory(trash);
	}

}
