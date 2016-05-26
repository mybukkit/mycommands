package de.mybukkit.mycommands.commands;

import de.mybukkit.mycommands.helper.Location;
import de.mybukkit.mycommands.helper.MyCommandBase;
import de.mybukkit.mycommands.helper.Teleport;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;


public class CommandSpawn extends MyCommandBase
{
	public CommandSpawn(boolean op)
	{
		name = "spawn";
		usage = " : teleport to server's spawn location";
		opOnly=op;
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
		BlockPos spawn = player.getServerForPlayer().getSpawnPoint();
		Teleport.warp(player, new Location(spawn.up(),0), true);
	}
}
