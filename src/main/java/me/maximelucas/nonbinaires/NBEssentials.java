package me.maximelucas.nonbinaires;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import me.maximelucas.nonbinaires.afk.AFKManager;
import me.maximelucas.nonbinaires.common.ICommonManager;
import me.maximelucas.nonbinaires.common.Properties;
import me.maximelucas.nonbinaires.home.HomeManager;
import me.maximelucas.nonbinaires.trash.TrashManager;

public class NBEssentials extends JavaPlugin {

	private String databaseUrl;
	private ConnectionSource connectionSource;
	private List<ICommonManager> managers;

	@Override
	public void onEnable() {

		super.onEnable();
		this.getLogger().info("NBEssentials is now loaded !");

		// Configuration loading
		getConfig().options().copyDefaults();
		saveDefaultConfig();

		// Database configuration
		this.databaseUrl = getConfig().getString(Properties.DATABASE_URL);
		if (this.databaseUrl == null || this.databaseUrl.isEmpty()) {
			this.getLogger().severe("Missing property '" + Properties.DATABASE_URL + "' in the configuration file");
			throw new IllegalArgumentException();
		}

		// Manager creation and initialization
		managers = new ArrayList<ICommonManager>();
		managers.add(new AFKManager(this));
		managers.add(new TrashManager(this));

		Integer nbMaxHomes = getConfig().getInt(Properties.MAX_HOME);
		try {
			managers.add(new HomeManager(this, nbMaxHomes));
		} catch (Exception e) {
			getLogger().severe("An error occured on Home feature initialization");
			e.printStackTrace();
		}

		for (ICommonManager manager : managers) {
			manager.registerCommands();
			manager.registerEvents();
		}

	}

	public ConnectionSource getDB() {

		if (this.connectionSource == null) {
			try {
				connectionSource = new JdbcConnectionSource(this.databaseUrl);
			} catch (SQLException e) {
				getLogger().severe("[SQLException] " + e.getMessage());
				return null;
			}
		}
		return connectionSource;
	}
}
