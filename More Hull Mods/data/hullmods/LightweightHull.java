package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class LightweightHull extends BaseHullMod {
    private static final float FUEL_USE = 0.85f;
    private static final int BURN_LEVEL_BONUS = 1;
    private static final float MANEUVER_BONUS = 15f;
    private static final float SPEED_BONUS = 10f;
    private static final float HULL_PENALTY = 30f;
    private static final float ARMOR_PENALTY = 30f;


    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getFuelUseMod().modifyMult(id, FUEL_USE);

        stats.getMaxBurnLevel().modifyFlat(id, BURN_LEVEL_BONUS);

        stats.getAcceleration().modifyPercent(id, MANEUVER_BONUS);
        stats.getDeceleration().modifyPercent(id, MANEUVER_BONUS);
        stats.getTurnAcceleration().modifyPercent(id, MANEUVER_BONUS);
        stats.getMaxTurnRate().modifyPercent(id, MANEUVER_BONUS);

        stats.getMaxSpeed().modifyPercent(id, SPEED_BONUS);

        stats.getHullBonus().modifyPercent(id, -HULL_PENALTY);
        stats.getArmorBonus().modifyPercent(id, -ARMOR_PENALTY);
    }

    public String getDescriptionParam(int index, HullSize hullSize, ShipAPI ship) {
        if (index == 0) return Math.round((1f - FUEL_USE) * 100f) + "%";
        if (index == 1) return String.valueOf(BURN_LEVEL_BONUS);
        if (index == 2) return Math.round(MANEUVER_BONUS) + "%";
        if (index == 3) return Math.round(SPEED_BONUS) + "%";
        if (index == 4) return Math.round(HULL_PENALTY) + "%";
        if (index == 5) return Math.round(ARMOR_PENALTY) + "%";
        return null;
    }

}