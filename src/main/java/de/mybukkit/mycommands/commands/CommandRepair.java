package de.mybukkit.mycommands.commands;

import de.mybukkit.mycommands.helper.MyCommandBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;



public class CommandRepair extends MyCommandBase
{
	public CommandRepair(boolean op)
	{
		name = "repair";
		usage = " : repair the Item in Hand";
		opOnly=op;
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
		ItemStack item = player.getActiveItemStack();

		if (item != null)
		{
		item.setItemDamage(0);
		}
	}
}