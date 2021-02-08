package com.customfarmruns;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Singleton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import lombok.AccessLevel;
import lombok.Getter;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.FontManager;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.PluginErrorPanel;
import net.runelite.client.util.ColorUtil;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.util.QuantityFormatter;

import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.PluginErrorPanel;

import javax.inject.Inject;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;

import com.customfarmruns.CustomFarmRunsConfig;
import net.runelite.client.config.ConfigManager;

public class CustomFarmRunsPanel extends PluginPanel {

    @Inject
    @Getter
    private CustomFarmRunsConfig config;

    private final static Color BACKGROUND_COLOR = ColorScheme.DARK_GRAY_COLOR;
    private final static Color BUTTON_HOVER_COLOR = ColorScheme.DARKER_GRAY_HOVER_COLOR;

    // instantiate objects
    private final CustomFarmRunsPlugin plugin;
    private JLabel farmRunCountTextLabel = new JLabel();
    private JLabel farmRunCountIntLabel = new JLabel();

    // define the base panel
    public CustomFarmRunsPanel(final CustomFarmRunsPlugin plugin) {
        super(false);
        this.plugin = plugin;

        this.setBackground(ColorScheme.DARK_GRAY_COLOR);
        this.setLayout(new BorderLayout());
    }

    // define the view in the panel
    public void showView() {
        // define the menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.X_AXIS));
        menuPanel.setBorder(new EmptyBorder(10,10,10,10));
        menuPanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);
        menuPanel.add(farmRunCountTextLabel);
        farmRunCountTextLabel.setText("Farm Runs: ");
        menuPanel.add(farmRunCountIntLabel);
        farmRunCountIntLabel.setText("0"); // eventually add getter method to calc number of runs
        JButton addButton = new JButton("+");
        menuPanel.add(addButton, BorderLayout.EAST);



        this.add(wrapContainer(menuPanel), BorderLayout.NORTH);

        this.revalidate();
        this.repaint();
    }

    private JScrollPane wrapContainer(final JPanel container)
    {
        final JPanel wrapped = new JPanel(new BorderLayout());
        wrapped.add(container, BorderLayout.NORTH);
        wrapped.setBackground(BACKGROUND_COLOR);

        final JScrollPane scroller = new JScrollPane(wrapped);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroller.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        scroller.setBackground(BACKGROUND_COLOR);

        return scroller;
    }

}
