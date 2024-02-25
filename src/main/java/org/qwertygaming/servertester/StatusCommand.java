package org.qwertygaming.servertester;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StatusCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, @NotNull String label, String[] args) {
        if (label.equalsIgnoreCase("status")) {
            if (args.length == 0) {
                sender.sendMessage("Usage: /status <server>");
                return true;
            }

            // Handle subcommands
            String subcommand = args[0].toLowerCase();

            if (subcommand.equals("lobby")) {
                // Send the API response to the player
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage("Please wait...");
                    String apiResponse = callApi("http://localhost:9999/api/lobby");
                    String apiParsed = apiResponse.replace('&', '§');
                    player.sendMessage(apiParsed);
                } else {
                    sender.sendMessage("This command can only be used by players.");
                }

                return true;
            }

            if (subcommand.equals("survival")) {
                // Send the API response to the player
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage("Please wait...");
                    String apiResponse = callApi("http://localhost:9999/api/survival");
                    String apiParsed = apiResponse.replace('&', '§');
                    player.sendMessage(apiParsed);
                } else {
                    sender.sendMessage("This command can only be used by players.");
                }

                return true;
            }

            if (subcommand.equals("creative")) {
                // Send the API response to the player
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage("Please wait...");
                    String apiResponse = callApi("http://localhost:9999/api/creative");
                    String apiParsed = apiResponse.replace('&', '§');
                    player.sendMessage(apiParsed);
                } else {
                    sender.sendMessage("This command can only be used by players.");
                }

                return true;
            }

            // Handle unknown subcommand
            sender.sendMessage("Unknown subcommand: " + subcommand);
            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (command.getName().equalsIgnoreCase("status")) {
            if (args.length == 1) {
                // If the player has typed "/status <tab>", provide tab completion options
                String input = args[0].toLowerCase();

                if ("lobby".startsWith(input)) {
                    completions.add("lobby");
                }

                if ("lobby".startsWith(input)) {
                    completions.add("survival");
                }

                if ("lobby".startsWith(input)) {
                    completions.add("creative");
                }
                return completions;
            }
        }

        return new ArrayList<>(); // Return an empty list for default tab completion behavior
    }

    private String callApi(String apiUrl) {
        StringBuilder response = new StringBuilder();

        try {
            System.out.println("Calling API: " + apiUrl);
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();
            System.out.println("API call successful");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error connecting to the API.");
            response.append("Error connecting to the API.");
        }

        return response.toString();
    }
}
