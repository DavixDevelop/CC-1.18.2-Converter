package me.bteuk.converterplugin;

import me.bteuk.converterplugin.utils.blocks.stairs.StairData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;

public class Converter implements CommandExecutor {

    private Plugin instance;
    private World world;

    private StairData stair;
    private StairData[] stairs;

    public Converter(Plugin instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        //Check if the sender is the console.
        if (!(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(ChatColor.RED + "This command can only be sent from the console.");
        }

        int max = Integer.MAX_VALUE;

        //If args length is 0 just start converting.
        if (args.length > 0) {
            try {
                max = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                sender.sendMessage("The parameter must be an integer!");
            }
        }

        //Get world.
        String worldName = instance.getConfig().getString("world");

        if (worldName == null) {
            sender.sendMessage("Set the world in config.");
            return true;
        }

        world = Bukkit.getWorld(instance.getConfig().getString("world"));

        if (world == null) {
            sender.sendMessage("The world " + worldName + " does not exist.");
            return true;
        }

        //Get datafolder.
        Path folder = Path.of(instance.getDataFolder().getAbsolutePath()).resolve("post-processing");
        String[] files = new File(folder.toString()).list();

        JSONParser parser = new JSONParser();

        for (String file : files) {

            try (Reader reader = new FileReader(folder + "/" + file)) {

                //Get the array of json objects.
                JSONArray jsonArray = (JSONArray) parser.parse(reader);

                //Iterate through array.
                for (Object object : jsonArray) {

                    JSONObject jObject = (JSONObject) object;

                    //Get the location of the block.
                    Location l = new Location(world, (int) jObject.get("x"), (int) jObject.get("y"), (int) jObject.get("z"));

                    //Get the details of the block.


                }


            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }

        }




        return false;
    }

    //Check if block has properties.
    private BlockData getBlockData(JSONObject object, Location l) {

        switch ((String) object.get("block")) {

            case "sunflower" -> {

                //Check the block below:


            }

            case "oak_stairs", "cobblestone_stairs", "brick_stairs", "stone_brick_stairs", "nether_brick_stairs",
                    "sandstone_stairs", "spruce_stairs", "birch_stairs", "jungle_stairs", "quartz_stairs", "acacia_stairs",
                    "dark_oak_stairs", "red_sandstone_stairs", "purpur_stairs", "iron_bars" -> {

                /*

                Stair direction = direction 0
                Stair facing away on left = direction 1
                Stair facing away behind = direction 2
                Stair facing away on right = direction 3

                Order = Below-Left-Above-Right

                Formations:
                    Straight has priority
                    Stair always prioritises itself
                    Small corner over large corner

                Each of the 4 directions has:
                    Straight (Block is stair and attaches with a straight connection)
                    CornerI (Block is stair and attaches with an inner corner)
                    CornerO (Block is stair and attaches with an outer corner)
                    None (Block is stair without connections or a non-stair)

                Calculation:

                    1. If contains Straight
                        - Check for 2nd Straight ->
                            return Straight
                        - Check for CornerI ->
                            if CornerI[facing] != Straight[direction] ->
                                return CornerI in Straight[direction]
                        - Check for CornerO ->
                            if CornerO[facing] == Straight[direction] ->
                                return CornerO in !Straight[direction]
                        - Else return Straight

                    2. If contains CornerI return CornerI in !CornerI[direction]
                    3. If contains CornerO return CornerO in CornerO[direction]

                 */

                //Get facing direction.



            }

            case "oak_fence", "birch_fence", "spruce_fence", "jungle_fence", "acacia_fence", "dark_oak_fence" -> {

            }

            case "cobblestone_wall", "mossy_cobblestone_wall" -> {

            }

            case "glass_pane", "red_stained_glass_pane", "lime_stained_glass_pane", "pink_stained_glass_pane", "gray_stained_glass_pane",
                    "cyan_stained_glass_pane", "blue_stained_glass_pane", "white_stained_glass_pane", "brown_stained_glass_pane",
                    "green_stained_glass_pane", "black_stained_glass_pane", "orange_stained_glass_pane", "yellow_stained_glass_pane",
                    "purple_stained_glass_pane", "magenta_stained_glass_pane", "light_blue_stained_glass_pane", "light_gray_stained_glass_pane" -> {

            }

            case "chest" -> {

            }

            case "redstone_wire" -> {

            }

            case "chorus_plant" -> {

            }

            case "red_bed" -> {

            }

            case "white_banner" -> {

            }

            case "white_wall_banner" -> {

            }

            case "melon_stem", "pumpkin_stem" -> {

            }

            case "flower_pot" -> {

            }

            case "skeleton_skull" -> {

            }

            case "note_block" -> {

            }

            case "repeater" -> {

            }

            case "tripwire" -> {

            }

            case "vine" -> {

            }




        }

    }
}
