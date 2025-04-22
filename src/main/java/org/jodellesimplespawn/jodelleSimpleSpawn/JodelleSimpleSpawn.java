package org.jodellesimplespawn.jodelleSimpleSpawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.player.PlayerRespawnEvent;

public final class JodelleSimpleSpawn extends JavaPlugin implements Listener {

    // ANSI escape codes for adding color to console output (if the console supports it)
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    /**
     * This method is called when the plugin is enabled (e.g., server start or plugin reload).
     */
    @Override
    public void onEnable() {
        // Load the default config file (creates it if it doesn't exist)
        saveDefaultConfig();

        // Register the plugin as an event listener for player join events
        getServer().getPluginManager().registerEvents(this, this);

        // Log to console with color
        getLogger().info(GREEN + "JodelleSimpleSpawn enabled!" + RESET);
    }

    /**
     * This method is called when the plugin is disabled (e.g., server stop or plugin reload).
     */
    @Override
    public void onDisable() {
        // Log to console with color
        getLogger().info(YELLOW + "JodelleSimpleSpawn disabled!" + RESET);
    }

    /**
     * This method handles custom commands registered in plugin.yml.
     * Currently supports: /jsp setspawn
     */
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        // Check if the command is either /jsp or /jodellesimplespawn
        if (cmd.getName().equalsIgnoreCase("jsp") || cmd.getName().equalsIgnoreCase("jodellesimplespawn")) {

            // Check for the "setspawn" argument
            if (args.length == 1 && args[0].equalsIgnoreCase("setspawn")) {

                // Ensure the sender is a player (not console)
                if (sender instanceof Player) {
                    Player player = (Player) sender;

                    // Check if the player has permission to use the command
                    if (player.hasPermission("jodellesimplespawn.setspawn")) {

                        // Get player's current location
                        Location loc = player.getLocation();
                        double x = loc.getX();
                        double y = loc.getY();
                        double z = loc.getZ();

                        // Save the location to config.yml
                        getConfig().set("spawn.x", x);
                        getConfig().set("spawn.y", y);
                        getConfig().set("spawn.z", z);
                        saveConfig();

                        // Inform the player of success
                        player.sendMessage(ChatColor.GREEN + "✔ Spawn point set to: " +
                                ChatColor.AQUA + "X: " + x + " Y: " + y + " Z: " + z);

                        // Log the action to the console
                        getLogger().info(GREEN + "✔ Spawn set by " + player.getName() +
                                " at X: " + x + ", Y: " + y + ", Z: " + z + RESET);

                    } else {
                        // Player doesn't have permission
                        player.sendMessage(ChatColor.RED + "✖ You do not have permission to use this command.");
                    }

                } else {
                    // Command was not run by a player
                    sender.sendMessage(ChatColor.RED + "✖ Only players can use this command.");
                }

                return true; // Command was handled
            }
        }

        return false; // Command was not handled
    }

    /**
     * This event is triggered whenever a player joins the server.
     * If teleporting is enabled in the config, teleport the player to the configured spawn.
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Check if teleportation is enabled in the config
        if (getConfig().getBoolean("enabled")) {

            // Load coordinates from the config file
            double x = getConfig().getDouble("spawn.x");
            double y = getConfig().getDouble("spawn.y");
            double z = getConfig().getDouble("spawn.z");

            // Create a location object using those coordinates (uses player's current world)
            Location spawnLocation = new Location(event.getPlayer().getWorld(), x, y, z);

            // Teleport the player
            event.getPlayer().teleport(spawnLocation);

            // Log the teleportation to the console
            getLogger().info(YELLOW + event.getPlayer().getName() +
                    " teleported to spawn at X: " + x + ", Y: " + y + ", Z: " + z + RESET);
        }
    }


    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (getConfig().getBoolean("enabled")) {

            // Load coordinates from the config
            double x = getConfig().getDouble("spawn.x");
            double y = getConfig().getDouble("spawn.y");
            double z = getConfig().getDouble("spawn.z");

            // Create a spawn location using the player's current world
            Location spawnLocation = new Location(event.getPlayer().getWorld(), x, y, z);

            // Set the player's respawn location
            event.setRespawnLocation(spawnLocation);

            // Log the teleportation to the console
            getLogger().info(YELLOW + event.getPlayer().getName() +
                    " respawned and was teleported to spawn at X: " + x + ", Y: " + y + ", Z: " + z + RESET);
        }
    }
}
