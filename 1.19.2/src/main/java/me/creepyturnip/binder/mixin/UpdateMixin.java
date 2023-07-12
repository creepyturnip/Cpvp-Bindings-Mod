package me.creepyturnip.binder.mixin;

import me.creepyturnip.binder.CpvpBinder;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class UpdateMixin {
	@Inject(at = @At("HEAD"), method = "tick", cancellable = true)
	public void onUpdate(CallbackInfo info) {
		CpvpBinder.INSTANCE.onUpdate();
	}
}