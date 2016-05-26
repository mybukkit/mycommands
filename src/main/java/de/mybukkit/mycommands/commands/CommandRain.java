package de.mybukkit.mycommands.commands;

import de.mybukkit.mycommands.helper.MyCommandBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.storage.WorldInfo;


public class CommandRain extends MyCommandBase
{
	private EntityPlayerMP sender;

	public CommandRain(boolean op)
	{
		name = "rain";
		usage = "Active the rain";
		opOnly=op;
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
		player.addChatMessage(new TextComponentString("Rain"));
		WorldInfo info = player.worldObj.getWorldInfo ();
		info.setRaining(true);
		info.setThundering(false);
		player.worldObj.updateWeatherBody ();
	}
}