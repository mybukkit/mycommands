package de.mybukkit.mycommands.commands;

import de.mybukkit.mycommands.helper.McColor;
import de.mybukkit.mycommands.helper.MyCommandBase;
import de.mybukkit.mycommands.helper.Teleport;
import de.mybukkit.mycommands.helper.HomePoint;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;

public class CommandHome extends MyCommandBase {

	public CommandHome(boolean op) {
		name = "home";
		usage = " : Go home.";
		opOnly=op;
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{		
		if (args.length == 0)
	{
		HomePoint home = HomePoint.getHome(player,"home");
		if (home != null)
		{
			Teleport.warp(player, home.location, false);
			player.addChatMessage(new TextComponentString(McColor.green + "Warped home."));
		}
	}
	else if (args.length == 1)
		{
			HomePoint home = HomePoint.getHome(player,args[0]);
			if (home != null)
			{
				Teleport.warp(player, home.location, false);
				player.addChatMessage(new TextComponentString(McColor.green + "Warped home."));
			} else
			{
				player.addChatMessage(new TextComponentString(McColor.darkRed +"You are homeless!"));
			}
		}
	}
} 


