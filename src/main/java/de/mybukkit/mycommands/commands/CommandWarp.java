package de.mybukkit.mycommands.commands;

import de.mybukkit.mycommands.helper.McColor;
import de.mybukkit.mycommands.helper.MyCommandBase;
import de.mybukkit.mycommands.helper.Teleport;
import de.mybukkit.mycommands.helper.WarpPoint;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;

public class CommandWarp extends MyCommandBase {

	public CommandWarp(boolean op) {
		name = "warp";
		usage = " <WarpPointName>: Warp you to a location or a player.";
		opOnly=op;
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] par2ArrayOfStr)
	{

		if ( par2ArrayOfStr.length < 1 ||  par2ArrayOfStr.length > 1)
		{
			player.addChatMessage(new TextComponentString(McColor.green + "WarpPointNames: " + McColor.aqua + WarpPoint.getWarpPoints()));

		} 
		else if ( par2ArrayOfStr.length == 1)
		{
			final String warpPointName =  par2ArrayOfStr[0];
			WarpPoint warpPoint = WarpPoint.getWarpPoint(warpPointName);

			if (warpPoint != null)
			{
				WarpPoint.getWarpPoint(warpPointName);
				Teleport.warp(player, WarpPoint.getWarpPoint(par2ArrayOfStr[0]).location, false);
				player.addChatMessage(new TextComponentString(McColor.aqua + warpPointName + McColor.green + " has been warped."));
			}else{
				player.addChatMessage(new TextComponentString(McColor.green + "WarpPointNames: " + McColor.aqua + WarpPoint.getWarpPoints()));

			}

		}
	}
} 

