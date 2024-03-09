package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class DowngradedCombatSystem extends BaseHullMod {
    private static final float MAINTENANCE_MULT = 0.8f;
    private static final float WEAPON_RANGE_PENALTY_MULT = 0.9f;
    private static final float WEAPON_ROTATE_PENALTY_MULT = 0.85f;
    private static final float RECOIL_PENALTY_PERCENT = 15f;
    private static final float FLUX_PENALTY_MULT = 0.75f;

    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getMinCrewMod().modifyMult(id, MAINTENANCE_MULT);
        stats.getSuppliesPerMonth().modifyMult(id, MAINTENANCE_MULT);
        stats.getFuelUseMod().modifyMult(id, MAINTENANCE_MULT);

        stats.getBallisticWeaponRangeBonus().modifyMult(id, WEAPON_RANGE_PENALTY_MULT);
        stats.getEnergyWeaponRangeBonus().modifyMult(id, WEAPON_RANGE_PENALTY_MULT);
        stats.getMissileWeaponRangeBonus().modifyMult(id, WEAPON_RANGE_PENALTY_MULT);

        stats.getWeaponTurnRateBonus().modifyMult(id, WEAPON_ROTATE_PENALTY_MULT);
        stats.getBeamWeaponTurnRateBonus().modifyMult(id, WEAPON_ROTATE_PENALTY_MULT);

        stats.getMaxRecoilMult().modifyPercent(id, RECOIL_PENALTY_PERCENT);
        stats.getRecoilPerShotMult().modifyPercent(id, RECOIL_PENALTY_PERCENT);

        stats.getFluxCapacity().modifyMult(id, FLUX_PENALTY_MULT);
        stats.getFluxDissipation().modifyMult(id, FLUX_PENALTY_MULT);
    }

    public String getDescriptionParam(int index, HullSize hullSize, ShipAPI ship) {
        if (index == 0) return Math.round((1f - MAINTENANCE_MULT) * 100f) + "%";
        if (index == 1) return Math.round((1f - WEAPON_RANGE_PENALTY_MULT) * 100f) + "%";
        if (index == 2) return Math.round((1f - WEAPON_ROTATE_PENALTY_MULT) * 100f) + "%";
        if (index == 3) return Math.round(RECOIL_PENALTY_PERCENT) + "%";
        if (index == 4) return Math.round((1f - FLUX_PENALTY_MULT) * 100f) + "%";
        return null;
    }

}