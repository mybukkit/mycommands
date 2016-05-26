package de.mybukkit.mycommands.commands;

import de.mybukkit.mycommands.helper.MyCommandBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;


public class CommandDay extends MyCommandBase
{
	public CommandDay(boolean op)
	{
		name = "day";
		usage = "Change for day";
		opOnly=op;
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
		player.addChatMessage(new TextComponentString("Day"));
		player.worldObj.setWorldTime(1000);
	}
}