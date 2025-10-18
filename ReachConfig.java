package com.reachmod;

public class ReachConfig {
    
    public boolean enabled = true;
    public float attackReach = 6.0f;
    public float blockReach = 6.0f;
    public boolean visualEffects = true;
    
    public static final float MIN_REACH = 3.0f;
    public static final float MAX_REACH = 10.0f;
    public static final float DEFAULT_REACH = 3.0f;
    
    public void setAttackReach(float reach) {
        this.attackReach = Math.max(MIN_REACH, Math.min(MAX_REACH, reach));
    }
    
    public void setBlockReach(float reach) {
        this.blockReach = Math.max(MIN_REACH, Math.min(MAX_REACH, reach));
    }
    
    public float getAttackReach() {
        return enabled ? attackReach : DEFAULT_REACH;
    }
    
    public float getBlockReach() {
        return enabled ? blockReach : DEFAULT_REACH;
    }
    
    public void toggle() {
        enabled = !enabled;
    }
    
    public void increaseAttackReach() {
        setAttackReach(attackReach + 0.5f);
    }
    
    public void decreaseAttackReach() {
        setAttackReach(attackReach - 0.5f);
    }
    
    public void increaseBlockReach() {
        setBlockReach(blockReach + 0.5f);
    }
    
    public void decreaseBlockReach() {
        setBlockReach(blockReach - 0.5f);
    }
}
