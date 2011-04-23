
package me.oracular.Zap;

import java.util.List;

import java.util.logging.Logger;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.World;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Entity;

public class Zap extends JavaPlugin {
	private static final Logger log = Logger.getLogger("Minecraft");

	@Override
	public void onDisable() {
		log.info("Zap disabled");
		
	}

	@Override
	public void onEnable() {
		log.info("Zap enabled");
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		List<World> worlds = this.getServer().getWorlds();
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
					Player player = getPlayer(args[0]);
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

	public Player getPlayer(String player) {
		List<Player> players = this.getServer().matchPlayer(player);
		if (players.isEmpty()) {
			return null;
		} else {
			return players.get(0);
		}
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
}
