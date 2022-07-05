package fr.kangpvp.lastarria.commands;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class CommandModif implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(player.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
                return true;
            }

            AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
            AttributeModifier modifiers = new AttributeModifier(UUID.randomUUID(), "generic.attack", 200.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
            AttributeModifier modifierss = new AttributeModifier(UUID.randomUUID(), "generic", 200.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();
            meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier);
            meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, modifiers);
            meta.addAttributeModifier(Attribute.HORSE_JUMP_STRENGTH, modifierss);

            meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

            item.setItemMeta(meta);




        }


        return false;
    }
}
