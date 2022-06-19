package fr.kangpvp.lastarria.commands;

import fr.kangpvp.lastarria.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class CommandMami implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;

        NamespacedKey namespacedKey = new NamespacedKey(Main.INSTANCE, "mami");

        PersistentDataContainer data = player.getPersistentDataContainer();

        if (!data.has(namespacedKey, PersistentDataType.DOUBLE))
            data.set(namespacedKey, PersistentDataType.DOUBLE, 0.0);


        double balance = data.get(namespacedKey, PersistentDataType.DOUBLE);

        if (args.length == 0)
        {
            player.sendMessage("§3Ta money : " + balance);

        } else if (args.length == 2)
        {
            double qty = Double.parseDouble(args[1]);
            if (args[0].equalsIgnoreCase("a"))
            {
                balance += qty;
                data.set(namespacedKey, PersistentDataType.DOUBLE, balance);

            } else if (args[0].equalsIgnoreCase("r"))
            {
                balance -= qty;
                data.set(namespacedKey, PersistentDataType.DOUBLE, balance);
            }
        }
        return true;
    }
}
