package de.mybukkit.mycommands.commands;

import de.mybukkit.mycommands.helper.McColor;
import de.mybukkit.mycommands.helper.MyCommandBase;
import de.mybukkit.mycommands.helper.WarpPoint;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;

public class CommandSetWarp extends MyCommandBase {

	public CommandSetWarp(boolean op) {
		name = "setwarp";
		usage = " : [warpname]";
		opOnly=op;
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
		if ( args.length < 1 ||  args.length > 1)
		{
			
		} 
		else if ( args.length == 1)
		{
			final String warpPointName =  args[0];
			WarpPoint warpPoint = WarpPoint.getWarpPoint(warpPointName);

			if (warpPoint == null)
			{
				WarpPoint.setWarpPoint(player,warpPointName);
				player.addChatMessage(new TextComponentString(McColor.aqua + warpPointName + McColor.green + " has been set."));
			}else{
				
				player.addChatMessage(new TextComponentString(McColor.pink + "Warppoint already exists"));

			}
		}
    }
}