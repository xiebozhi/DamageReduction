/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teamglokk.damagereduction;

import com.teamglokk.damagereduction.commands.ReloadCommand;
import com.teamglokk.damagereduction.listeners.DamageReductionLoginEvent;
import com.teamglokk.damagereduction.listeners.DamageReductionPlayerDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author bobbshields
 */
public class DamageReduction extends JavaPlugin{

    private double CONFIG_VERSION;
    //private boolean USE_METRICS;
    
     /**
     * Shut down sequence
     */
    @Override
    public void onDisable() {
        getLogger().info("Shutting Down");
               
        // Save the news items
        this.saveConfig();
        
        getLogger().info("Shut Down sequence complete");
    }

    /**
     * Start up sequence
     */
    @Override
    public void onEnable() {
        getLogger().info("Starting Up");
                
        //Load the configuration file
        //this.saveDefaultConfig(); // saves plugins/Muni/config.yml if !exists
        //loadConfigSettings(); // parses the settings and loads into memory
        
        // Register Damage Reduction listener(s)
        getServer().getPluginManager().registerEvents(new DamageReductionLoginEvent(this),this );
        getServer().getPluginManager().registerEvents(new DamageReductionPlayerDamageEvent(this),this );
        
        
        
        // Register Damage Reduction commands
        getCommand("dr-reload"  ).setExecutor(new ReloadCommand (this) );
             
        /*
        // Start Metrics if allowed by server owner
        if (USE_METRICS){
            if ( isDebug() ) {getLogger().info("Loading Metrics") ; }
            try {
                Metrics metrics = new Metrics(this);
                
                metrics.start();
                if ( isDebug() ) {getLogger().info("Metrics data has been sent") ; }
                
            } catch (IOException e) {
                // Failed to submit the stats :-(
                getLogger().warning("There was an error loading Metrics");
            }
        }
        */
        
        this.getLogger().info ("Loaded and Ready to reduce damages" );
    } 
    
    /**
     * Checks the player's permission
     * @param player
     * @param perm
     * @return 
     */
    public boolean hasPerm (Player player, String perm){
        if ( player.hasPermission(perm) ){
            return true;
        } else if ( player.isOp() ) {
            return true;
        }
        else { return false; }
    }
    
     /**
     * Deletes empty/null elements and trims the elements of a string array
     * @param split the array to be parsed
     * @return resized array of strings
     */
    public String [] trimSplit (String [] split ) {
        if (split.length == 0 ){
            return new String [0];
        } 
        String [] temp = new String[split.length];
        int i = 0;
        for (String entry: split) {
            if (entry.equalsIgnoreCase(" ") || entry.isEmpty() ){
                // do nothing (delete the empty space entries)
            } else {
                temp[i] = entry.trim();
                i++;
            }
        }
        String [] rtn = new String[i];
        int j = 0;
        for (j=0; j<i; j++){
            rtn[j] = temp[j];
        }
        return rtn;
    }
    
    /**
     * Loads the config settings from config.yml in plugins/muni/
     * /
    protected void loadConfigSettings(){
        if (CONFIG_VERSION != this.getConfig().getDouble("config_version") ){
            getLogger().warning("Config version does not match software requirements.");
        }
        USE_METRICS = this.getConfig().getBoolean("use_metrics");
        
        
        // Populate the town ranks array
        townRanks = new TownRank [totalTownRanks+1];
        for ( int i=1; i <= totalTownRanks; i++ ){
            townRanks[i] = new TownRank( i,
                    this.getConfig().getString("townRanks."+(i)+".title"),
                    this.getConfig().getInt   ("townRanks."+(i)+".maxDeputies"),
                    this.getConfig().getInt   ("townRanks."+(i)+".minCitizens"),
                    this.getConfig().getInt   ("townRanks."+(i)+".maxCitizens"),
                    this.getConfig().getDouble("townRanks."+(i)+".moneyCost"),
                    this.getConfig().getInt   ("townRanks."+(i)+".itemCost"),
                    this.getConfig().getInt   ("townRanks."+(i)+".expansions"),
                    this.getConfig().getInt   ("townRanks."+(i)+".outposts"),
                    this.getConfig().getInt   ("townRanks."+(i)+".restaurants"),
                    this.getConfig().getInt   ("townRanks."+(i)+".hospitals"),
                    this.getConfig().getInt   ("townRanks."+(i)+".mines"),
                    this.getConfig().getInt   ("townRanks."+(i)+".embassies"),
                    this.getConfig().getInt   ("townRanks."+(i)+".arenas") );
                    if ( isDebug() ) { getLogger().info( townRanks[i].getName()+
                            " config settings were loaded"); }
        }
        
        getLogger().info("News items loaded");
        
   } */
}
   