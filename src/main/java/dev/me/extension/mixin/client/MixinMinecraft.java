package dev.me.extension.mixin.client;

import dev.me.extension.Main;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MixinMinecraft {
    @Inject(method = "startGame", at = @At(value = "RETURN"))
    private void startGame(CallbackInfo callbackInfo) {
        System.out.println("Hello Mixin");
        Main.initEnd();
    }
}