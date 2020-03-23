package me.maximelucas.nonbinaires.home;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.table.TableUtils;

import me.maximelucas.nonbinaires.NBEssentials;
import me.maximelucas.nonbinaires.common.CommonManager;
import me.maximelucas.nonbinaires.exceptions.DatabaseException;
import me.maximelucas.nonbinaires.home.commands.DelHomeCommand;
import me.maximelucas.nonbinaires.home.commands.HomeCommandManager;
import me.maximelucas.nonbinaires.home.commands.SetHomeCommand;
import me.maximelucas.nonbinaires.home.entities.Home;
import me.maximelucas.nonbinaires.home.exceptions.HomeAlreadyExistsException;
import me.maximelucas.nonbinaires.home.exceptions.MaxHomeException;
import me.maximelucas.nonbinaires.home.exceptions.NoSuchHomeException;

public class HomeManager extends CommonManager implements IHomeManager {

	Dao<Home, String> homeDAO;
	private final Integer maxNbOfHomes;

	public HomeManager(NBEssentials main, Integer maxNbOfHomes) throws Exception {
		super(main);
		this.maxNbOfHomes = maxNbOfHomes;
		try {
			this.homeDAO = DaoManager.createDao(main.getDB(), Home.class);
			TableUtils.createTableIfNotExists(this.main.getDB(), Home.class);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public void registerEvents() {
	}

	@Override
	public void registerCommands() {
		registerCommand("home", new HomeCommandManager(this));
		registerCommand("sethome", new SetHomeCommand(this));
		registerCommand("delhome", new DelHomeCommand(this));
	}

	@Override
	public List<String> listHomes(String pPlayerName) throws DatabaseException {
		try {
			List<String> result = new ArrayList<String>();
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("player", pPlayerName);
			List<Home> homes = homeDAO.queryForFieldValuesArgs(parameters);
			return homes.stream().map(home -> home.getName()).collect(Collectors.toList());
		} catch (SQLException e) {
			System.err.println("[listHomes] SQLException : " + e.getMessage());
			e.printStackTrace();
			throw new DatabaseException(e);
		}

	}

	@Override
	public void addHome(String pPlayerName, String pHomeName, Double pPosX, Double pPosY, Double pPosZ)
			throws DatabaseException, MaxHomeException, HomeAlreadyExistsException {

		try {
			// Check if max home reached
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("player", pPlayerName);
			List<Home> homes = homeDAO.queryForFieldValuesArgs(parameters);
			if (homes.size() >= maxNbOfHomes)
				throw new MaxHomeException(maxNbOfHomes);

			// Check if home exists with given name
			boolean homeExists = false;
			for (Home home : homes) {
				if (home.getName().equals(pHomeName))
					homeExists = true;
			}
			if (homeExists)
				throw new HomeAlreadyExistsException(pHomeName);

			Home home = new Home();
			home.setName(pHomeName);
			home.setPlayer(pPlayerName);
			home.setPosX(pPosX);
			home.setPosY(pPosY);
			home.setPosZ(pPosZ);
			homeDAO.create(home);

		} catch (SQLException e) {
			System.err.println("[addHome] SQLException : " + e.getMessage());
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public void removeHome(String pPlayerName, String pHomeName) throws NoSuchHomeException, DatabaseException {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("player", pPlayerName);
			parameters.put("name", pHomeName);
			List<Home> homes = homeDAO.queryForFieldValuesArgs(parameters);
			if (homes.isEmpty()) {
				throw new NoSuchHomeException(pHomeName);
			}
			Home home = homes.get(0);
			homeDAO.delete(home);
		} catch (SQLException e) {
			System.err.println("[removeHome] SQLException : " + e.getMessage());
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}

	@Override
	public void teleportPlayerToHome(String pPlayerName, String pHomeName)
			throws DatabaseException, NoSuchHomeException {
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("player", pPlayerName);
			parameters.put("name", pHomeName);
			List<Home> homes = homeDAO.queryForFieldValuesArgs(parameters);
			if (homes.isEmpty()) {
				throw new NoSuchHomeException(pHomeName);
			}
			Home homeToTp = homes.get(0);
			Bukkit.getPlayer(pPlayerName).teleport(new Location(main.getServer().getWorld("world"), homeToTp.getPosX(),
					homeToTp.getPosY(), homeToTp.getPosZ()));

		} catch (SQLException e) {
			System.err.println("[teleportPlayerToHome] SQLException : " + e.getMessage());
			e.printStackTrace();
			throw new DatabaseException(e);
		}

	}

}
