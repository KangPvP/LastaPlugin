package fr.kangpvp.lastarria.utils;

import java.util.HashMap;
import java.util.Map;

public class GamePlayer {
	private String name;
	public static boolean isInZone;
	public static Map<String, GamePlayer>gamePlayers = new HashMap();
	
	public GamePlayer(String name) {
		this.name = name;
		this.isInZone = false;
		gamePlayers.put(name, this);
	}
	public String getName() {
		return name;
	}
}