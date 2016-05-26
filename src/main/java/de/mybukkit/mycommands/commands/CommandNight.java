package de.mybukkit.mycommands.commands;

import de.mybukkit.mycommands.helper.MyCommandBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;

public class CommandNight extends MyCommandBase
{
	
	public CommandNight(boolean op)
	{
		name = "night";
		usage = "Change for night";
		opOnly=op;
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
		player.addChatMessage(new TextComponentString("Night"));
		player.worldObj.setWorldTime(13500);
	}
}