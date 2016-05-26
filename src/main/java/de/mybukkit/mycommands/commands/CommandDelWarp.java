package de.mybukkit.mycommands.commands;


import de.mybukkit.mycommands.helper.McColor;
import de.mybukkit.mycommands.helper.MyCommandBase;
import de.mybukkit.mycommands.helper.WarpPoint;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;


public class CommandDelWarp extends MyCommandBase
{
	public CommandDelWarp(boolean op)
	{
		name = "delwarp";
		usage = "Delete Warp <Warppoint name>";
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
			String warpPointName = args[0];
			WarpPoint warpPoint = WarpPoint.getWarpPoint(warpPointName);

			if (warpPoint == null)
			{
				player.addChatMessage(new TextComponentString(McColor.aqua + warpPointName + McColor.darkRed + " does not exist."));
			}
			else
			{
				WarpPoint.delWarpPoint(warpPointName);
				player.addChatMessage(new TextComponentString(McColor.aqua + warpPointName + McColor.grey +" has been deleted."));
			}
		}
	}

}
