package com.gmail.moonmasters200.Spawners2Money;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
public class Main extends JavaPlugin implements Listener
{
  public void onEnable()
  {
    getServer().getPluginManager().registerEvents(this, this);
  }
  
  @ EventHandler(priority = EventPriority.MONITOR)
  public void onBlockBreak(BlockBreakEvent event)
  {
    // If it's cancelled, move on
    if (event.isCancelled()) 
    {
      return;
    } 
    else
    {
      Block block = event.getBlock();
      Player player = event.getPlayer();
      ItemStack tool = player.getItemInHand();
      Material blockName = block.getType();
      player.sendMessage("You just broke " + blockName);
      if (block.getType() != Material.MOB_SPAWNER)
      {
        return;
      }
      Enchantment SILK_TOUCH = new EnchantmentWrapper(33);
      if (tool.containsEnchantment(SILK_TOUCH))
      {
        /** Tool does have silk */
        if (player.hasPermission("spawners2money.donor"))
        {
          player.sendMessage("You just mined a spawnwer with Silk Touch!  Use a regular pick for money.");
          // Does nothing
          return;
        } 
        else
        {
          player.sendMessage("You got $10,000 for mining a spawner!");
          Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + player.getName() + "10000");
          return;
        }
      }
      /** Tool does not have silk */
      else
      {
        if (player.hasPermission("spawners2money.donor"))
        {
          player.sendMessage("You got $10,000 for mining a spawner!");
          Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + player.getName() + "10000");
          return;
        } else
        {
          player.sendMessage("Use a silk touch pick axe in the future to get money from spawners!");
          // Does nothing
          return;
        }
      }
    }
  }
}