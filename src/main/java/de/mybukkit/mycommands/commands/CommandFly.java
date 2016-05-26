package de.mybukkit.mycommands.commands;

import java.util.ArrayList;

import de.mybukkit.mycommands.mycommands;
import de.mybukkit.mycommands.helper.McColor;
import de.mybukkit.mycommands.helper.MyCommandBase;
import de.mybukkit.mycommands.helper.PlayerManager;
import de.mybukkit.mycommands.helper.SaveFile;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;


public class CommandFly extends MyCommandBase
{
	
	private static SaveFile _flyingSaveFile = new SaveFile("fly.txt",mycommands.configPath+"/commands/");
	private static ArrayList<String> _usersFlying = new ArrayList<String>();
	
	public static void loadConfig () {
		_flyingSaveFile.load();
		_usersFlying.clear();
		
		for(String info : _flyingSaveFile.data) {
			_usersFlying.add(info);
		}
	}
	
	public static void saveConfig () {
		_flyingSaveFile.clear ();
		for(String username : _usersFlying)
			_flyingSaveFile.data.add(username);
		_flyingSaveFile.save(false);
	}
	
	public static boolean mustFlying (EntityPlayerMP player) {
		return _usersFlying.contains(player.getName());
	}

	public CommandFly(boolean op)
	{
		name = "fly";
		opOnly = op;
		usage = " [<PlayerName>]: You toggle your fly capability or for other player's fly capability";
	}
	
	public void toggleFlying (EntityPlayerMP player) {
		
		if (player.capabilities.allowFlying) {
			player.capabilities.allowFlying = false;
			player.sendPlayerAbilities();
			
			while (_usersFlying.contains (player.getName())) {
				_usersFlying.remove(player.getName());
			}
			saveConfig ();
			
			player.addChatMessage(new TextComponentString(McColor.green + "Fly Mode Deaktiviert"));
		} else {
			player.capabilities.allowFlying = true;
			player.sendPlayerAbilities();
			_usersFlying.add (player.getName());
			saveConfig ();
			player.addChatMessage(new TextComponentString(McColor.green + "Fly Mode Aktiviert"));
		}
	}
	
	@Override
	public void processPlayer(EntityPlayerMP player, String[] astring) {
   
		if (astring.length == 0)
		{  
			
			toggleFlying (player);
		} else
		{
			EntityPlayerMP target = PlayerManager.getPlayer(astring[0], true);

			if (target == null) {
				target = PlayerManager.getPossiblePlayer(astring[0]);
			}
			
			if (target != null) {
				if (target.capabilities.allowFlying) {
					toggleFlying (target);
					target.addChatMessage(new TextComponentString(McColor.green + "Fly Mode Aktiviert "));
				} else {
					toggleFlying (target);
					target.addChatMessage(new TextComponentString(McColor.green + "Fly Mode Deaktiviert "));
				}
			} else {player.addChatMessage(new TextComponentString(McColor.green + "Player is Offline "));

			
		}
        }
	}


}