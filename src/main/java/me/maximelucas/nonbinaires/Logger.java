package me.maximelucas.nonbinaires;

import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Logger {
	public static void log(Player pPlayer, String message) {
		pPlayer.sendMessage(ChatColor.GOLD + message);
	}

	public static void logInternalError(Player pPlayer, Exception e) {
		log(pPlayer, "An internal error occured. Please contact your server administrator");
	}
}
