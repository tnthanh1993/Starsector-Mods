package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class DowngradedLogisticsSystem extends BaseHullMod {
    private static final float MAINTENANCE_MULT = 0.9f;
    private static final float CAPACITY_PENALTY_MULT = 0.75f;

    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getMinCrewMod().modifyMult(id, MAINTENANCE_MULT);
        stats.getSuppliesPerMonth().modifyMult(id, MAINTENANCE_MULT);
        stats.getFuelUseMod().modifyMult(id, MAINTENANCE_MULT);

        stats.getMaxCrewMod().modifyMult(id, CAPACITY_PENALTY_MULT);
        stats.getFuelMod().modifyMult(id, CAPACITY_PENALTY_MULT);
        stats.getCargoMod().modifyMult(id, CAPACITY_PENALTY_MULT);
    }

    public String getDescriptionParam(int index, HullSize hullSize, ShipAPI ship) {
        if (index == 0) return Math.round((1f - MAINTENANCE_MULT) * 100f) + "%";
        if (index == 1) return Math.round((1f - CAPACITY_PENALTY_MULT) * 100f) + "%";
        return null;
    }

}