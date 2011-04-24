package me.oracular.Zap;

import java.util.List;
import java.util.logging.Logger;

import me.oracular.Zap.commands.ZapCommand;
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
		getCommand("zap").setExecutor((new ZapCommand(this)));
		
	}

	@Override
	public void onEnable() {
		log.info("Zap enabled");
		
	}
	
}
