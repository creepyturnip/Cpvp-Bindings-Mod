package me.creepyturnip.binder.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MinecraftClient.class)
public interface MinecraftClientAcces {
    @Invoker
    void invokeDoItemUse();
    @Invoker
    boolean invokeDoAttack();
}
