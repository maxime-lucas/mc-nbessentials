package me.maximelucas.nonbinaires;

import java.io.IOException;
import java.sql.SQLException;

import org.bukkit.plugin.java.JavaPlugin;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import me.maximelucas.nonbinaires.afk.AFKManager;
import me.maximelucas.nonbinaires.afk.IAFKManager;
import me.maximelucas.nonbinaires.persistence.entities.HomeEntity;
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

		testDb();
	}

	public void testDb() {
		ConnectionSource connectionSource = null;
		try {
			connectionSource = new JdbcConnectionSource("jdbc:sqlite:nbessentials.db");
		} catch (SQLException e) {
			this.getLogger().severe("SQL Exception when creating JdbcConnectionSource");
			e.printStackTrace();
		}

		Dao<HomeEntity, String> homeDao = null;
		try {
			homeDao = DaoManager.createDao(connectionSource, HomeEntity.class);
		} catch (SQLException e) {
			this.getLogger().severe("SQL Exception when creating Home DAO");
			e.printStackTrace();
		}

		try {
			TableUtils.createTableIfNotExists(connectionSource, HomeEntity.class);
		} catch (SQLException e) {
			this.getLogger().severe("SQL Exception when creating Home table");
			e.printStackTrace();
		}

		// create an instance of Home
		HomeEntity home = new HomeEntity();
		home.setName("My Home");
		home.setPlayer("Player");
		home.setPosX(10.0);
		home.setPosY(11.0);
		home.setPosZ(12.0);

		try {
			homeDao.create(home);
		} catch (SQLException e) {
			this.getLogger().severe("SQL Exception when persisting home object");
			e.printStackTrace();
		}

		HomeEntity homeInDb = null;
		try {
			homeInDb = homeDao.queryForSameId(home);
		} catch (SQLException e) {
			this.getLogger().severe("SQL Exception when querying home");
			e.printStackTrace();
		}
		System.out.println("Home: " + homeInDb.getPlayer());

		// close the connection source
		try {
			connectionSource.close();
		} catch (IOException e) {
			this.getLogger().severe("SQL Exception when closing connection source");
			e.printStackTrace();
		}
	}

}
