package de.mybukkit.mycommands.commands;


import de.mybukkit.mycommands.helper.MyCommandBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.storage.WorldInfo;


public class CommandSun extends MyCommandBase
{
	public CommandSun(boolean op)
	{
		name = "sun";

		usage = "Activate the sun";
		opOnly=op;
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
		player.addChatMessage(new TextComponentString("Sun"));
		WorldInfo info = player.worldObj.getWorldInfo ();
		info.setRaining(false);
		info.setThundering(false);
		player.worldObj.updateWeatherBody ();
	}
}
