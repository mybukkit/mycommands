package de.mybukkit.mycommands.commands;


import de.mybukkit.mycommands.helper.HomePoint;
import de.mybukkit.mycommands.helper.McColor;
import de.mybukkit.mycommands.helper.MyCommandBase;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;

public class CommandSetHome extends MyCommandBase {

	public CommandSetHome(boolean op) {
		
			name = "sethome";
			usage = " : set Home location.";
			opOnly=op;
		}

		@Override
		public void processPlayer(EntityPlayerMP player, String[] args)
		{
 
		
		if ( args.length < 1 ||  args.length > 1)
		{
			HomePoint home = HomePoint.getHome(player, "home");
			if(home ==null){
				HomePoint.setHome(player, "home");
				player.addChatMessage(new TextComponentString(McColor.green +"set Home "+McColor.gold +"home"+McColor.green+ "."));

				}else{
					player.addChatMessage(new TextComponentString(McColor.pink + "argument missmatch, /sethome <HomeName> for Home"));

				}
		} 
		else if ( args.length == 1)
		{

			HomePoint.setHome(player,args[0]);
			player.addChatMessage(new TextComponentString(McColor.green +"set Home "+McColor.gold +args[0]+McColor.green+ "."));
		}else{
				player.addChatMessage(new TextComponentString(McColor.pink + "argument missmatch, /sethome <HomeName> for Home"));

			
		}
	}	
}
 

