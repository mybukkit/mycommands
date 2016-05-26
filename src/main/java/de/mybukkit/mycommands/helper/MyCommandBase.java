package de.mybukkit.mycommands.helper;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;





public class MyCommandBase implements ICommand
{
	public static String trueName;
	public String name;
	public String usage;
	public boolean opOnly = false;

	/**
	 * Override this method to process things when command is used by console.
	 */
	public void processConsole(String[] args)
	{
	}

	/**
	 * Override this method to process things when command is used by a player.
	 * @param server 
	 */
	public void processPlayer(EntityPlayerMP player, String[] args)
	{
	}
	
	@Override
	public int compareTo(ICommand arg0) 
	{
		return this.getCommandName().charAt(0) - arg0.getCommandName().charAt(0);
	}

	@Override
	public String getCommandName() 
	{
		return name;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) 
	{
		return "/" + name + " " + usage;
	}

	@Override
	public List<String> getCommandAliases() 
	{
		ArrayList<String> name = new ArrayList<String>();
		return name;
	}

	@Override
	public void execute(MinecraftServer server,ICommandSender sender, String[] args)
			throws CommandException {
		if(sender instanceof EntityPlayerMP)
			this.processPlayer((EntityPlayerMP)sender, args);
		else
			this.processConsole(args);
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
	
		
		
		//Console or RCON
		if(!(sender instanceof EntityPlayerMP))
			return true;
		//Dedicated Server
	
		if(opOnly){
			return server.getPlayerList().canSendCommands(((EntityPlayerMP) sender).getGameProfile());
		}
			return true;
	}



	public void argumentMismatch(ICommandSender sender)
	{
		sender.addChatMessage(new TextComponentString(McColor.darkRed + "Argument mismatch, try:"));
		sender.addChatMessage(new TextComponentString(McColor.green + name + usage));
	}

	@Override
	public boolean isUsernameIndex(String[] astring, int i) {
		return false;
	}




	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos pos) {
		
		return null;
	}







}
