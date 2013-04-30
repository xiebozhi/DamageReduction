

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamglokk.damagereduction.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import com.teamglokk.damagereduction.DamageReduction;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.PlayerInventory;

/**
 * The custom Muni Login Event
 * @author bobbshields
 */
public class DamageReductionPlayerDamageEvent implements Listener{
    DamageReduction plugin;
    
    public DamageReductionPlayerDamageEvent (DamageReduction instance) {
        plugin = instance; 
    }
    /**
     * Displays relevant town info to players as they log in. 
     * @param event 
     */
    @EventHandler (priority = EventPriority.LOW)
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            
            if ( plugin.hasPerm(player, "damagereduction.chain") ) {
            
                PlayerInventory inv = player.getInventory();
                int boots = (inv.getBoots() == null ? -1 : inv.getBoots().getTypeId() );
                int pants = (inv.getLeggings() == null ? -1 : inv.getLeggings().getTypeId() );
                int chest = (inv.getChestplate() == null ? -1 : inv.getChestplate().getTypeId() );
                int helm  = (inv.getHelmet() == null ? -1 : inv.getHelmet().getTypeId() );

                double reduction = 0;

                if (boots == 305) { reduction += .3; }
                if (pants == 304) { reduction += .3; }
                if (chest == 303) { reduction += .9; }
                if (helm  == 302) { reduction += .4; }

                double damage = event.getDamage();
                damage = damage - reduction; 
                event.setDamage( (int) damage);

                player.sendMessage("Damage has been reduced by "+reduction+".");
            } else {
                player.sendMessage("Sad day, no damage reduction for you (no permission)");
            }
        }
    }
}

