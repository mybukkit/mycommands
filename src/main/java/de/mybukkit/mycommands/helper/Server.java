package de.mybukkit.mycommands.helper;

import de.mybukkit.mycommands.mycommands;
import net.minecraft.command.CommandHandler;
import net.minecraft.server.MinecraftServer;

public class Server 
{
	static MinecraftServer server11;
	public static MinecraftServer getServer()
	{
		return mycommands.server.getServer();
	}


	public static boolean isDedicatedServer()
	{
		return mycommands.server.getServer().isDedicatedServer();
	}

	public static CommandHandler getCommandHandler()
	{
		return (CommandHandler)getServer().getCommandManager();
	}
}
