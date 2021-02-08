package com.customfarmruns;

import com.google.inject.Provides;
import javax.inject.Inject;
import javax.swing.text.NavigationFilter;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.PluginDescriptor;

import com.customfarmruns.CustomFarmRunsPanel;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;

@Slf4j
@PluginDescriptor(
		name = "Custom Farm Runs",
		description = "Make custom farm runs"
)
public class CustomFarmRunsPlugin extends Plugin
{


	@Inject
	private ItemManager itemManager;

	@Inject
	private Client client;

	@Inject
	private CustomFarmRunsConfig config;

	@Inject
	private ClientToolbar clientToolbar;

	// instantiate stuff
	private CustomFarmRunsPanel panel;
	private NavigationButton navButton;

	@Override
	protected void startUp() throws Exception
	{
		panel = new CustomFarmRunsPanel(this);

		final BufferedImage icon = ImageUtil.getResourceStreamFromClass(getClass(), "panel_icon.png");

		navButton = NavigationButton.builder()
				.tooltip("Custom Farm Runs")
				.priority(6)
				.icon(icon)
				.panel(panel)
				.build();

		clientToolbar.addNavigation(navButton);

		panel.showView();
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

	@Provides
	CustomFarmRunsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(CustomFarmRunsConfig.class);
	}
}
