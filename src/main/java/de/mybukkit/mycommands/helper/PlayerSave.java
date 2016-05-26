package de.mybukkit.mycommands.helper;

import java.util.HashMap;
import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class PlayerSave {
	private static UUID plUUid;
	private static EntityPlayer player;
	private static String name;
	private static Location loc;
	private static HashMap<String,Location>home = new HashMap<String,Location>();

	public PlayerSave(EntityPlayer player){
		this.player=player;
	}
	public static UUID getUuid(){
		return plUUid;
	}
	public static UUID setUuid(){
		return  plUUid=player.getUniqueID();
	}

}
