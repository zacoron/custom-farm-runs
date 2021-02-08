package com.customfarmruns;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class CustomFarmRunsTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(CustomFarmRunsPlugin.class);
		RuneLite.main(args);
	}
}