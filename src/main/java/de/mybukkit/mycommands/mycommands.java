package de.mybukkit.mycommands;
import java.io.File;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.mybukkit.mycommands.commands.CommandBack;
import de.mybukkit.mycommands.commands.CommandDay;
import de.mybukkit.mycommands.commands.CommandDelHome;
import de.mybukkit.mycommands.commands.CommandDelWarp;
import de.mybukkit.mycommands.commands.CommandFly;
import de.mybukkit.mycommands.commands.CommandGod;
import de.mybukkit.mycommands.commands.CommandHeal;
import de.mybukkit.mycommands.commands.CommandHome;
import de.mybukkit.mycommands.commands.CommandNight;
import de.mybukkit.mycommands.commands.CommandRain;
import de.mybukkit.mycommands.commands.CommandRepair;
import de.mybukkit.mycommands.commands.CommandSetHome;
import de.mybukkit.mycommands.commands.CommandSetSpawn;
import de.mybukkit.mycommands.commands.CommandSetWarp;
import de.mybukkit.mycommands.commands.CommandSpawn;
import de.mybukkit.mycommands.commands.CommandSun;
import de.mybukkit.mycommands.commands.CommandWarp;
import de.mybukkit.mycommands.events.MYEventHandler;
import de.mybukkit.mycommands.helper.Forge;
import de.mybukkit.mycommands.helper.HomePoint;
import de.mybukkit.mycommands.helper.WarpPoint;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;


@Mod(modid = "mycommands", name = "MyCommands", version = "1.9-02",acceptableRemoteVersions="*")

public class mycommands {
	public static MinecraftServer server;

	@Instance("mycommands")
	public static mycommands instance;
	public static final Logger log = LogManager.getLogger("mycommands");
	
	public static String configPath;
	private File mainConfigFile;
	private Configuration config;
	
	private boolean delwarp;
	private boolean delhome;
	private boolean god;
	private boolean fly;
	private boolean back;
	private boolean home;
	private boolean sethome;
	private boolean setwarp;
	private boolean warp;
	private boolean setspawn;
	private boolean spawn;
	private boolean night;
	private boolean rain;
	private boolean sun;
	private boolean heal;
	private boolean repair;
	private boolean day;

	
	@EventHandler
	public void serverStart(FMLServerStartingEvent event)
	{

		server = event.getServer(); //Gets current server
		ICommandManager command = server.getCommandManager(); //Gets the command manager to use for server
		ServerCommandManager serverCommand = ((ServerCommandManager) command); //Turns it into another form to use
		serverCommand.registerCommand(new CommandGod(god));
		serverCommand.registerCommand(new CommandFly(fly));
		serverCommand.registerCommand(new CommandHome(home));
		serverCommand.registerCommand(new CommandSetHome(sethome));
		serverCommand.registerCommand(new CommandSetWarp(setwarp));
		serverCommand.registerCommand(new CommandWarp(warp));
		serverCommand.registerCommand(new CommandDelHome(delhome));
		serverCommand.registerCommand(new CommandDelWarp(delwarp));
		serverCommand.registerCommand(new CommandBack(back));
		serverCommand.registerCommand(new CommandSetSpawn(setspawn));
		serverCommand.registerCommand(new CommandSpawn(spawn));
		serverCommand.registerCommand(new CommandDay(day));
		serverCommand.registerCommand(new CommandNight(night));
		serverCommand.registerCommand(new CommandRain(rain));
		serverCommand.registerCommand(new CommandSun(sun));
		serverCommand.registerCommand(new CommandHeal(heal));
		serverCommand.registerCommand(new CommandRepair(repair));

		CommandFly.loadConfig();
		CommandGod.loadConfig();
		Forge.registerEventHandler(new MYEventHandler());

		WarpPoint.loadAll();
		HomePoint.loadAll();
	}
	@EventHandler
	public void serverStop(FMLServerStoppingEvent event)
	{
		CommandFly.saveConfig();
		CommandGod.saveConfig();
		WarpPoint.saveAll();
		HomePoint.saveAll();
	}

	@Mod.EventHandler
	public void serverStarted(FMLServerStartedEvent event) {

	}
	@EventHandler
	public void init(FMLPreInitializationEvent event){
		configPath = event.getModConfigurationDirectory() + "/mycommands/";
		mainConfigFile = new File(configPath + "mycommands.cfg");
		cinit(mainConfigFile);
	}
	private void cinit(File configFile)
	{
		config = new Configuration(configFile);
		String catcom="Commands";
		
		config.addCustomCategoryComment(catcom, "true = Op Only | false for All");

		try
		{
			config.load();

			back = config.get(catcom, "Back", false).getBoolean();
			day = config.get(catcom, "Day", true).getBoolean();
			delhome = config.get(catcom, "DelHome", false).getBoolean();
			delwarp = config.get(catcom, "DelWarp", true).getBoolean();
			fly = config.get(catcom, "Fly", true).getBoolean();
			god = config.get(catcom, "God", true).getBoolean();
			heal = config.get(catcom, "Heal", true).getBoolean();
			home = config.get(catcom, "Home", false).getBoolean();
			night = config.get(catcom, "Night", true).getBoolean();
			rain = config.get(catcom, "Rain", true).getBoolean();
			repair = config.get(catcom, "Repair", true).getBoolean();
			sethome = config.get(catcom, "SetHome", false).getBoolean();
			setspawn = config.get(catcom, "SetSpawn", true).getBoolean();
			setwarp = config.get(catcom, "SetWarp", true).getBoolean();
			spawn = config.get(catcom, "Spawn", false).getBoolean();
			sun = config.get(catcom, "Sun", true).getBoolean();
			warp = config.get(catcom, "Warp", false).getBoolean();


		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, "config fehler", e);
		}
		finally
		{
			if (config.hasChanged()) {
				config.save();
			}
		}
	}
	@EventHandler
	public void load(FMLInitializationEvent event)
	{


	}
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	}

}

