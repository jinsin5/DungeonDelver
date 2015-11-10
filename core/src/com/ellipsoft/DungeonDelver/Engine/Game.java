package com.ellipsoft.DungeonDelver.Engine;

import com.ellipsoft.DungeonDelver.Entity.Entity;

import java.util.concurrent.ThreadLocalRandom;


public class Game {
	private static final int BASE_DAMAGE = 5;

	public static void fight(Entity defender, Entity attacker){
		if (defender.hp <= 0) {
			return; //Defend is dead
		}
		if (attacker.hp <= 0) {
			return; //attacker is dead
		}
		int hit = randInt(0, attacker.stats.get("Atk") + attacker.stats.get("Luck")/10 +
				attacker.stats.get("Dex"));
		int dodge = randInt(0, 100 + defender.stats.get("Def"));
		if (hit < dodge) {
			return; //miss
		}
		int damage = randInt(0, BASE_DAMAGE) + attacker.stats.get("Str") - defender.stats.get("Str")
				- (defender.stats.get("Con")/10);
		if (damage <= 0){
			return;
		}
		int crit = randInt(0, 100) + (defender.stats.get("Luck")/5) - attacker.stats.get("Int")
				- (attacker.stats.get("Atk")/25) - (attacker.stats.get("Wis")/10);
		if (crit <= 0) {
			int multiplier = 1 + (attacker.stats.get("Dex") / 10); // need to make into float
			damage = damage * multiplier;
		}
		defender.hp -= damage;
	}

	public static void levelUp(Entity e){
		e.max_hp += e.stats.get("Con")/5;
		for (String key : e.stats.keySet()) {
			int cur = e.stats.get(key);
			e.stats.put(key, cur+1);
		}
	}

	public static int randInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
}
