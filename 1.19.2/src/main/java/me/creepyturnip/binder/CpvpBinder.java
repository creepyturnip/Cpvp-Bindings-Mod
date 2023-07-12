package me.creepyturnip.binder;

import me.creepyturnip.binder.mixin.MinecraftClientAcces;
import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.*;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Predicate;

public class CpvpBinder implements ClientModInitializer {
	public static final CpvpBinder INSTANCE = new CpvpBinder();
    public static final Logger LOGGER = LoggerFactory.getLogger("CpvpBindMod");
	private static final KeyBinding useBind = new KeyBinding("Place Bind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Crystal Pvp");
	private static final KeyBinding attackBind = new KeyBinding("Attack Bind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Crystal Pvp");
	private static final KeyBinding obsidianBind = new KeyBinding("Obsidian Bind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Crystal Pvp");
	private static final KeyBinding crystalBind = new KeyBinding("Crystal Bind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Crystal Pvp");
	private static final KeyBinding anchorBind = new KeyBinding("Respawn Anchor Bind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Crystal Pvp");
	private static final KeyBinding glowstoneBind = new KeyBinding("Glowstone Bind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Crystal Pvp");
	private static final KeyBinding foodBind = new KeyBinding("Food Bind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Crystal Pvp");
	private static final KeyBinding bowBind = new KeyBinding("Bow Bind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Crystal Pvp");
	private static final KeyBinding pickAxeBind = new KeyBinding("Pickaxe Bind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Crystal Pvp");
	private static final KeyBinding axeBind = new KeyBinding("Axe Bind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Crystal Pvp");
	private static final KeyBinding shieldBind = new KeyBinding("Shield Bind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Crystal Pvp");
	private static final KeyBinding totemBind = new KeyBinding("Totem Of Undying Bind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Crystal Pvp");
	private static final KeyBinding pearlBind = new KeyBinding("Ender Pearl Bind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Crystal Pvp");
	private static final KeyBinding swordBind = new KeyBinding("Sword Bind", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Crystal Pvp");

	//i joinked the hotbat selection part from skliggahack :) credits to anker
	public static boolean selectItemFromHotbar(Predicate<Item> item)
	{
		PlayerInventory inv = MinecraftClient.getInstance().player.getInventory();

		for (int i = 0; i < 9; i++)
		{
			ItemStack itemStack = inv.getStack(i);
			if (!item.test(itemStack.getItem()))
				continue;
			inv.selectedSlot = i;
			return true;
		}

		return false;
	}
	public static boolean selectItemFromHotbar(Item item)
	{
		return selectItemFromHotbar(i -> i == item);
	}

	@Override
	public void onInitializeClient() {
		KeyBindingHelper.registerKeyBinding(obsidianBind);
		KeyBindingHelper.registerKeyBinding(crystalBind);
		KeyBindingHelper.registerKeyBinding(anchorBind);
		KeyBindingHelper.registerKeyBinding(glowstoneBind);
		KeyBindingHelper.registerKeyBinding(foodBind);
		KeyBindingHelper.registerKeyBinding(shieldBind);
		KeyBindingHelper.registerKeyBinding(pickAxeBind);
		KeyBindingHelper.registerKeyBinding(axeBind);
		KeyBindingHelper.registerKeyBinding(bowBind);
		KeyBindingHelper.registerKeyBinding(totemBind);
		KeyBindingHelper.registerKeyBinding(pearlBind);
		KeyBindingHelper.registerKeyBinding(swordBind);
		KeyBindingHelper.registerKeyBinding(useBind);
		KeyBindingHelper.registerKeyBinding(attackBind);
		LOGGER.info("Cpvp binder is working!");
	}
	public void onUpdate(){
		handleObsidianBind();
		handleCrystalBind();
		handleAnchorBind();
		handleGlowStoneBind();
		handleFoodBind();
		handlePickaxeBind();
		handleBowBind();
		handleShieldBind();
		handleAxeBind();
		handleTotemBind();
		handlePearlBind();
		handleSwordBind();
		handleUseBind();
		handleAttackBind();
	}
	private void handleAttackBind(){
		if (attackBind.isPressed()){
			((MinecraftClientAcces)MinecraftClient.getInstance()).invokeDoAttack();
		}
	}
	private void handleUseBind(){
		if (useBind.isPressed()){
			((MinecraftClientAcces)MinecraftClient.getInstance()).invokeDoItemUse();
		}
	}
	private void handleObsidianBind(){
		if (obsidianBind.wasPressed()){
			selectItemFromHotbar(Items.OBSIDIAN);
		}
	}
	private void handleCrystalBind(){
		if (crystalBind.wasPressed()){
			selectItemFromHotbar(Items.END_CRYSTAL);
		}
	}
	private void handleAnchorBind(){
		if (anchorBind.wasPressed()){
			selectItemFromHotbar(Items.RESPAWN_ANCHOR);
		}
	}
	private void handleGlowStoneBind(){
		if (glowstoneBind.wasPressed()){
			selectItemFromHotbar(Items.GLOWSTONE);
		}
	}
	private void handleFoodBind(){
		if (MinecraftClient.getInstance().player != null) {
			for (int slot = 0; slot < 9; slot++) {
				ItemStack itemStack = MinecraftClient.getInstance().player.getInventory().getStack(slot);
				Item item = itemStack.getItem();
				if (item.isFood() ) {
					if (foodBind.wasPressed()) {
						MinecraftClient.getInstance().player.getInventory().selectedSlot = slot;
					}
				}
			}
		}
	}
	private void handleBowBind(){
		if (MinecraftClient.getInstance().player != null) {
			for (int slot = 0; slot < 9; slot++) {
				ItemStack itemStack = MinecraftClient.getInstance().player.getInventory().getStack(slot);
				Item item = itemStack.getItem();
				if (item instanceof RangedWeaponItem) {
					if (bowBind.wasPressed()) {
						MinecraftClient.getInstance().player.getInventory().selectedSlot = slot;
					}
				}
			}
		}
	}
	private void handleShieldBind(){
		if (shieldBind.wasPressed()){
			selectItemFromHotbar(Items.SHIELD);
		}
	}
	private void handlePickaxeBind(){
		if (MinecraftClient.getInstance().player != null) {
			for (int slot = 0; slot < 9; slot++) {
				ItemStack itemStack = MinecraftClient.getInstance().player.getInventory().getStack(slot);
				Item item = itemStack.getItem();
				if (item instanceof PickaxeItem) {
					if (pickAxeBind.wasPressed()) {
						MinecraftClient.getInstance().player.getInventory().selectedSlot = slot;
					}
				}
			}
		}
	}
	private void handleAxeBind(){
		if (MinecraftClient.getInstance().player != null) {
			for (int slot = 0; slot < 9; slot++) {
				ItemStack itemStack = MinecraftClient.getInstance().player.getInventory().getStack(slot);
				Item item = itemStack.getItem();
				if (item instanceof AxeItem) {
					if (axeBind.wasPressed()) {
						MinecraftClient.getInstance().player.getInventory().selectedSlot = slot;
					}
				}
			}
		}
	}
	private void handleTotemBind(){
		if (totemBind.wasPressed()){
			selectItemFromHotbar(Items.TOTEM_OF_UNDYING);
		}
	}
	private void handlePearlBind(){
		if (pearlBind.wasPressed()){
			selectItemFromHotbar(Items.ENDER_PEARL);
		}
	}
	private void handleSwordBind(){
		if (MinecraftClient.getInstance().player != null) {
			for (int slot = 0; slot < 9; slot++) {
				ItemStack itemStack = MinecraftClient.getInstance().player.getInventory().getStack(slot);
				Item item = itemStack.getItem();
				if (item instanceof SwordItem) {
					if (swordBind.wasPressed()) {
						MinecraftClient.getInstance().player.getInventory().selectedSlot = slot;
					}
				}
			}
		}
	}

}