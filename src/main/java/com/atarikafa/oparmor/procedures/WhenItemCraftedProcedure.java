package com.atarikafa.oparmor.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;

import java.util.Map;
import java.util.HashMap;

import com.atarikafa.oparmor.item.AtariArmorArmorItem;
import com.atarikafa.oparmor.OparmorMod;

public class WhenItemCraftedProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
			Entity entity = event.getPlayer();
			World world = entity.world;
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			ItemStack itemStack = event.getCrafting();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", world);
			dependencies.put("entity", entity);
			dependencies.put("itemstack", itemStack);
			dependencies.put("event", event);
			executeProcedure(dependencies);
		}
	}
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				OparmorMod.LOGGER.warn("Failed to load dependency itemstack for procedure WhenItemCrafted!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((((itemstack).getItem() == new ItemStack(AtariArmorArmorItem.helmet, (int) (1)).getItem())
				|| ((itemstack).getItem() == new ItemStack(AtariArmorArmorItem.body, (int) (1)).getItem()))
				|| ((itemstack).getItem() == new ItemStack(AtariArmorArmorItem.legs, (int) (1)).getItem()))
				|| ((itemstack).getItem() == new ItemStack(AtariArmorArmorItem.boots, (int) (1)).getItem()))) {
			((itemstack)).addEnchantment(Enchantments.PROTECTION, (int) 10);
		}
	}
}
