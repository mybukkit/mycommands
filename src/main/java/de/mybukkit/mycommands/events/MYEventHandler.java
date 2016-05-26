package de.mybukkit.mycommands.events;


import de.mybukkit.mycommands.commands.CommandFly;
import de.mybukkit.mycommands.commands.CommandGod;
import de.mybukkit.mycommands.helper.Location;
import de.mybukkit.mycommands.helper.Teleport;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;



public class MYEventHandler
{
	
	
	private World world;

	@SubscribeEvent
	public void onEntityJoinWorld(EntityJoinWorldEvent e) {
		if (!e.getEntity().worldObj.isRemote && e.getEntity() instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP)e.getEntity();

			if (!player.capabilities.allowFlying&&CommandFly.mustFlying(player)) {
			player.capabilities.allowFlying = true;
			player.sendPlayerAbilities();
			player.addChatMessage(new TextComponentString("Fly Active"));
			
			}
		
		if (!player.capabilities.disableDamage && CommandGod.mustbeGod(player)) {
			player.capabilities.disableDamage = true;
			player.sendPlayerAbilities();
			player.addChatMessage(new TextComponentString("God Active"));
			}
		}
	}
	@SubscribeEvent
	public void onlivingDeathEvent(LivingDeathEvent e){
		if (!e.getEntity().worldObj.isRemote && e.getEntity() instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP)e.getEntity();
			Location location = new Location(player);
			Teleport.playerBackMap.put(player, location);
		}

	}
}
	

