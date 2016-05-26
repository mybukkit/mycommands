package de.mybukkit.mycommands.commands;

import de.mybukkit.mycommands.helper.McColor;
import de.mybukkit.mycommands.helper.MyCommandBase;
import de.mybukkit.mycommands.helper.Teleport;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;



public class CommandBack extends MyCommandBase
{
	public CommandBack(boolean op)
	{
		name = "back";
		usage = " : Warp back to your previous location.";
		opOnly=op;
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
		if (Teleport.goBack(player))
		{
			player.addChatMessage(new TextComponentString(McColor.green + "Warped back to previous location."));
		} else
		{
			player.addChatMessage(new TextComponentString(McColor.darkRed	+ "You have not warped anywhere!"));
		}
	}
}