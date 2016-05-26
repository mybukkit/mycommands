package de.mybukkit.mycommands.commands;

import java.util.ArrayList;

import de.mybukkit.mycommands.mycommands;
import de.mybukkit.mycommands.helper.McColor;
import de.mybukkit.mycommands.helper.MyCommandBase;
import de.mybukkit.mycommands.helper.PlayerManager;
import de.mybukkit.mycommands.helper.SaveFile;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;

public class CommandGod extends MyCommandBase {

	private static SaveFile _godSaveFile = new SaveFile("god.txt",mycommands.configPath+
			"/commands/");
	private static ArrayList<String> _usersgod = new ArrayList<String>();

	public static void loadConfig() {
		_godSaveFile.load();
		_usersgod.clear();

		for (String info : _godSaveFile.data) {
			_usersgod.add(info);
		}

	}

	public static boolean mustbeGod(EntityPlayerMP player) {
		return _usersgod.contains(player.getName());
	}

	public static void saveConfig() {
		_godSaveFile.clear();
		for (String username : _usersgod)
			_godSaveFile.data.add(username);
		_godSaveFile.save(false);
	}
	public CommandGod(boolean op)
	{
		name = "god";
		opOnly = op;
		usage = " [<PlayerName>]: You toggle your God capability or for other player's God capability";
	}


	public void togglegod(EntityPlayerMP player) {

		if (player.capabilities.disableDamage) {
			player.capabilities.disableDamage = false;
			player.setHealth(player.getMaxHealth());
			player.getFoodStats().addStats(20, 2);

			player.sendPlayerAbilities();

			while (_usersgod.contains(player.getName())) {
				_usersgod.remove(player.getName());
			}
			saveConfig();

			player.addChatMessage(new TextComponentString(McColor.green
					+ "God Mode Deaktiviert"));
		} else {
			player.capabilities.disableDamage = true;
			player.setHealth(player.getMaxHealth());
			player.getFoodStats().addStats(20, 2);

			player.sendPlayerAbilities();
			_usersgod.add(player.getName());
			saveConfig();
			player.addChatMessage(new TextComponentString(McColor.green
					+ "God Mode Aktiviert"));
		}
	}

	@Override
	public void processPlayer(EntityPlayerMP player, String[] astring) {

		
			if (astring.length == 0) {
				togglegod(player);
			} else {
				EntityPlayerMP target = PlayerManager.getPlayer(astring[0],
						true);

				if (target == null) {
					target = PlayerManager.getPossiblePlayer(astring[0]);
				}

				if (target != null) {
					if (target.capabilities.disableDamage) {
						togglegod(target);
						target.addChatMessage(new TextComponentString(
								McColor.green + "God Mode Deaktiviert "));
					} else {
						togglegod(target);
						target.addChatMessage(new TextComponentString(
								McColor.green + "God Mode Aktiviert"));
					}
				} else {
					player.addChatMessage(new TextComponentString(McColor.green
							+ "Player is Offline "));
			}
		}
	}
}