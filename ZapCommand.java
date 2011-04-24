package me.oracular.Zap.commands;

import java.util.List;
import java.util.logging.Logger;

import me.oracular.Zap.Zap;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.World;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Entity;

public class ZapCommand implements CommandExecutor {
	private final Zap plugin;
	
	public ZapCommand(Zap plugin)
	{
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		List<World> worlds = sender.getServer().getWorlds();
		int counter = 0;
		for (World world : worlds)
		{
			if (isParsableToInt(args[0]) == true)
			{
				List<Entity> entities = world.getEntities();
				for (Entity entity : entities)
				{
					if (entity.getEntityId() == Integer.parseInt(args[0]))
					{
						while (counter < Integer.parseInt(args[1]))
						{
							world.strikeLightning(entity.getLocation());
							counter++;
							try {
								Thread.sleep(10L);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
			else
			{
				if (args[0].equalsIgnoreCase("^"))
				{
					while (counter < Integer.parseInt(args[1]))
					{
						Player stuff = (Player) sender;
						world.strikeLightning(stuff.getTargetBlock(null,256).getLocation());
						counter++;
						try {
							Thread.sleep(10L);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else
				{
					Player player = getPlayer(sender, args[0]);
					while (counter < Integer.parseInt(args[1]))
					{
						world.strikeLightning(player.getLocation());
						counter++;
						try {
							Thread.sleep(10L);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		return true;
	}
	
	public boolean isParsableToInt(String i)
	{
		try
		{
			Integer.parseInt(i);
			return true;
		}
		catch(NumberFormatException nfe)
		{
			return false;
		}
	}
	
	public Player getPlayer(CommandSender sender, String player) {
		List<Player> players = sender.getServer().matchPlayer(player);
		if (players.isEmpty()) {
			return null;
		} else {
			return players.get(0);
		}
	}
}
