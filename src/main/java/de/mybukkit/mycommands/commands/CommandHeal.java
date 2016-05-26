package de.mybukkit.mycommands.commands;

import de.mybukkit.mycommands.helper.McColor;
import de.mybukkit.mycommands.helper.MyCommandBase;
import de.mybukkit.mycommands.helper.PlayerManager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;


public class CommandHeal extends MyCommandBase
{
	public CommandHeal(boolean op)
	{
		name = "heal";
		usage = "heal or heal <Player>";
		opOnly=op;
	}


	@Override
	public void processPlayer(EntityPlayerMP player, String[] astring)
	{

	        
	        	if (astring.length==0){
	               
	        		player.setHealth(player.getMaxHealth());
	        		player.getFoodStats().addStats(20, 2);
	        		String txt="Du bist geheilt ";
	        		player.addChatMessage(new TextComponentString(txt)); //Just send a simple message to player
	        } else {
				EntityPlayerMP target = PlayerManager.getPlayer(astring[0],
						true);

				if (target == null) {
					target = PlayerManager.getPossiblePlayer(astring[0]);
				}

				if (target != null) {
	        		target.setHealth(target.getMaxHealth());
	        		target.getFoodStats().addStats(20, 2);
	        		String txt="Du wurdest geheilt ";
	        		target.addChatMessage(new TextComponentString(txt)); //Just send a simple message to player
					
				} else {
					player.addChatMessage(new TextComponentString(McColor.green
							+ "Player is Offline "));
			}
		}
	}
}
	
