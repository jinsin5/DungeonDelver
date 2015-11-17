package com.ellipsoft.DungeonDelver.Engine;

import com.ellipsoft.DungeonDelver.Entity.Entity;
import com.ellipsoft.DungeonDelver.UI.CombatLog;

import java.util.concurrent.ThreadLocalRandom;

public class Game {
	private static final int BASE_DAMAGE = 5;

	public static void fight(Entity defender, Entity attacker, CombatLog cLog){
		int hit = randInt(0, attacker.stats.get("Atk") + attacker.stats.get("Luck")/10 +
				attacker.stats.get("Dex"));
		int dodge = randInt(0, 100 + defender.stats.get("Def"));
		if (hit < dodge) {
			cLog.publish(attacker.type + " has missed " + defender.type + "!");
			return; //miss
		}
		int damage = randInt(0, BASE_DAMAGE) + attacker.stats.get("Str") - defender.stats.get("Str")
				- (defender.stats.get("Con")/10);
		if (damage <= 0){
			cLog.publish(attacker.type + "'s hit glances off " + defender.type + "'s armor");
			return; //glancing blow
		}
		int crit = randInt(0, 100) + (defender.stats.get("Luck")/5) - attacker.stats.get("Int")
				- (attacker.stats.get("Atk")/25) - (attacker.stats.get("Wis")/10);
		if (crit <= 0) {
			int multiplier = 1 + (attacker.stats.get("Dex") / 10); // need to make into float
			damage = damage * multiplier;
			cLog.publish(attacker.type + " crits " + defender.type + " for " + damage + " damage!");
		}
		else {
			cLog.publish(attacker.type + " hits " + defender.type + " for " + damage + " damage.");
		}
		defender.hp -= damage;

		if (defender.hp <= 0) {
			cLog.publish(defender.type + " has died.");
			return; //Defend is dead
		}
		if (attacker.hp <= 0) {
			cLog.publish(attacker.type + " has died.");
			return; //attacker is dead
		}
	}

	public static void levelUp(Entity e, CombatLog cLog){
		e.max_hp += e.stats.get("Con")/5;
		for (String key : e.stats.keySet()) {
			int cur = e.stats.get(key);
			e.stats.put(key, cur+1);
		}
		cLog.publish(e.type + " has leveled up!");
	}

	public static int randInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	public static int randInt(int max) {
		return ThreadLocalRandom.current().nextInt(0, max + 1);
	}
}
