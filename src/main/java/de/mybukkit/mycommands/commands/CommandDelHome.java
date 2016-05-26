package de.mybukkit.mycommands.commands;


import de.mybukkit.mycommands.helper.HomePoint;
import de.mybukkit.mycommands.helper.McColor;
import de.mybukkit.mycommands.helper.MyCommandBase;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;


public class CommandDelHome extends MyCommandBase
{
	public CommandDelHome(boolean op)
	{
		name = "delhome";
		usage = "Delete Home <Homepoint name>";
		opOnly=op;
	}


	@Override
    public void processPlayer(EntityPlayerMP player, String[] args)
    {	

		if (args.length < 1 || args.length > 1)
		{
			
		} 
		else if (args.length == 1)
		{
			String homePointName = args[0];
			HomePoint warpPoint = HomePoint.getHome(player, homePointName);

			if (warpPoint == null)
			{
				player.addChatMessage(new TextComponentString(McColor.aqua + homePointName + McColor.darkRed + " does not exist."));
			}
			else
			{
				HomePoint.delHomePoint(player,homePointName);
				player.addChatMessage(new TextComponentString(McColor.aqua + homePointName + McColor.grey +" has been deleted."));
			}
		}
	}

}
