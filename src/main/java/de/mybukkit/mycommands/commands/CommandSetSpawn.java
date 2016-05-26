package de.mybukkit.mycommands.commands;

import de.mybukkit.mycommands.helper.Location;
import de.mybukkit.mycommands.helper.McColor;
import de.mybukkit.mycommands.helper.MyCommandBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

public class CommandSetSpawn extends MyCommandBase
{
	public CommandSetSpawn(boolean op)
	{
		name = "setspawn";
		usage = " : Set the server's spawn location.";
		opOnly=op;
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
	
		Location loc = new Location(player);
		player.worldObj.getWorldInfo().setSpawn(new BlockPos(loc.x,loc.y,loc.z));
		player.addChatMessage(new TextComponentString(McColor.green + "spawn set."));
	}
}
