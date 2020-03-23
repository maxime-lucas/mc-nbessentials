package me.maximelucas.nonbinaires.home;

import java.sql.SQLException;
import java.util.List;

import me.maximelucas.nonbinaires.common.ICommonManager;
import me.maximelucas.nonbinaires.exceptions.DatabaseException;
import me.maximelucas.nonbinaires.home.exceptions.HomeAlreadyExistsException;
import me.maximelucas.nonbinaires.home.exceptions.MaxHomeException;
import me.maximelucas.nonbinaires.home.exceptions.NoSuchHomeException;

public interface IHomeManager extends ICommonManager {
	public List<String> listHomes(String pPlayerName) throws DatabaseException, SQLException;

	public void addHome(String pPlayerName, String pHomeName, Double pPosX, Double pPosY, Double pPosZ)
			throws HomeAlreadyExistsException, MaxHomeException, DatabaseException;

	public void removeHome(String pPlayerName, String pHomeName) throws NoSuchHomeException, DatabaseException;

	public void teleportPlayerToHome(String pPlayerName, String pHomeName)
			throws DatabaseException, NoSuchHomeException;
}
