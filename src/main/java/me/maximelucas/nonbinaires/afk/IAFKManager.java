package me.maximelucas.nonbinaires.afk;

import me.maximelucas.nonbinaires.common.ICommonManager;

public interface IAFKManager extends ICommonManager {

	public void toggleAFK(String pPlayerName);

	public boolean isAFK(String pPlayerName);
}
