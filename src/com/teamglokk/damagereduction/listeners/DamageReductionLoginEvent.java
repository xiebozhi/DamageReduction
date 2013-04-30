/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamglokk.damagereduction.listeners;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import com.teamglokk.damagereduction.DamageReduction;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * The custom Muni Login Event
 * @author bobbshields
 */
public class DamageReductionLoginEvent implements Listener{
    DamageReduction plugin;
    
    public DamageReductionLoginEvent (DamageReduction instance) {
        plugin = instance; 
    }
    /**
     * Displays relevant town info to players as they log in. 
     * @param event 
     */
    @EventHandler (priority = EventPriority.LOW)
    public void playerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.isOp() ){
            player.sendMessage("Chainmail damage reduction is active.  Coded by Bobb Shields");
        }
    }
}