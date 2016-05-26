package de.mybukkit.mycommands.helper;

import java.util.ArrayList;

import de.mybukkit.mycommands.mycommands;
import net.minecraft.entity.player.EntityPlayerMP;

public class HomePoint
{
	public String UUID;
	public Location location;
	public String homename;

	private static SaveFile homesSaveFile = new SaveFile("homes.txt",mycommands.configPath+"/homes/");

	public static ArrayList<HomePoint> homes = new ArrayList<HomePoint>();

	public HomePoint(EntityPlayerMP player, String name)
	{
		this.UUID = player.getUniqueID().toString();
		this.homename =	name;
		location = new Location(player);
	}

	public HomePoint(EntityPlayerMP player,String name, Location location)
	{
		this.UUID=player.getUniqueID().toString();
		this.homename = name;
		this.location = location;
	}

	public static HomePoint getHome(EntityPlayerMP player,String name)
	{
		HomePoint target = new HomePoint(player,name,null);
		if(homes.contains(target))
			return homes.get(homes.indexOf(target));

		return null;
	}





	public static void setHome(EntityPlayerMP player,String name)
	{
		Location location = new Location(player);
		
		HomePoint home = new HomePoint(player,name, location);

		if(homes.contains(home))
			homes.remove(home);

		homes.add(home);

		if(name.equalsIgnoreCase("home")&&location.dimension == 0)
			location.setSpawn(player);

		saveAll();
	}



	/**
	 * @return true on success, false when there's no WarpPoint with this name.
	 */

	public static void loadAll()
	{

		homesSaveFile.load();
		homes.clear();
		for(String info : homesSaveFile.data)
			homes.add(new HomePoint(info));
	}

	public static void saveAll()
	{

		homesSaveFile.clear();
		for(HomePoint home : homes)
			homesSaveFile.data.add(home.toString());

		homesSaveFile.save(false);
	}

	/**
	 * used to rebuild from string
	 * @param location2 
	 * @param homename 
	 */
	public HomePoint(String info) 
	{
		try
		{
			this.UUID = info.substring(0,info.indexOf(","));
			this.homename = info.substring(info.indexOf(",")+1,info.indexOf("("));

			String locationInfo = info.substring(info.indexOf("(") + 1, info.indexOf(")"));
			this.location = new Location(locationInfo);
		}
		catch(Exception e)
		{
			System.err.println("Exception on attemping to rebuild WarpPoint from String.");
			UUID="Error";
			homename = "Error";
			location = new Location(0,0,0,0);
		}
	}

	/**
	 * return a describing String that can be used to rebuild a WarpPoint
	 */
	public String toString()
	{
		if(location == null)
			return "";

		return UUID+","+homename +"(" + location.toString() + ")";
	}

	@Override
	public boolean equals(Object o)
	{
		if(o instanceof HomePoint)
			return homename.equals(((HomePoint)o).homename);

		return false;
	}

	@Override
	public int hashCode()
	{
		return homename.hashCode();
	}

	/**
	 * dummy constructor used to create a empty instance
	 */
	private HomePoint(EntityPlayerMP player,String name, Object dummy)
	{
		this.UUID=player.getUniqueID().toString();
		this.homename = name;
	}
	public static boolean delHomePoint(EntityPlayerMP player,String homename)
	{
		HomePoint warpPoint = new HomePoint(player,homename, null);
		if(homes.contains(warpPoint))
		{
			homes.remove(warpPoint);
			saveAll();
			return true;
		}
		return false;
	}
}
