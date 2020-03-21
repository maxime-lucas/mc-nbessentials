package me.maximelucas.nonbinaires;

import org.bukkit.plugin.java.JavaPlugin;

import me.maximelucas.nonbinaires.afk.AFKManager;
import me.maximelucas.nonbinaires.afk.IAFKManager;
import me.maximelucas.nonbinaires.trash.ITrashManager;
import me.maximelucas.nonbinaires.trash.TrashManager;

public class NBEssentials extends JavaPlugin {
	@Override
	public void onEnable() {
		super.onEnable();
		this.getLogger().info("NBEssentials is now loaded !");

		// Configuration loading
		getConfig().options().copyDefaults();
		saveDefaultConfig();

		IAFKManager afkManager = new AFKManager(this);
		ITrashManager trashManager = new TrashManager(this);

		afkManager.init();
		trashManager.init();
	}

}
