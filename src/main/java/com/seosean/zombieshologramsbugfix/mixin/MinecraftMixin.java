package com.seosean.zombieshologramsbugfix.mixin;

import com.seosean.zombieshologramsbugfix.ZombiesHologramsbugFixClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Shadow public MultiPlayerGameMode gameMode;
    @Shadow public ClientLevel level;
    @Shadow public LocalPlayer player;
    @Shadow public HitResult hitResult;
    @Shadow public GameRenderer gameRenderer;
    @Shadow private int rightClickDelay;

    @Inject(method = "startUseItem", at = @At("HEAD"), cancellable = true)
    private void zhf$startUseItem(CallbackInfo ci) {
        if (!ZombiesHologramsbugFixClient.toggleZHF) return;
        ci.cancel();
        if (gameMode.isDestroying()) return;
        rightClickDelay = 4;
        if (player.isHandsBusy()) return;

        for (InteractionHand hand : InteractionHand.values()) {
            ItemStack stack = player.getItemInHand(hand);
            if (!stack.isItemEnabled(level.enabledFeatures())) return;
            if (hitResult != null) {
                if (hitResult.getType() == HitResult.Type.ENTITY) {
                    EntityHitResult entityHit = (EntityHitResult) hitResult;
                    Entity entity = entityHit.getEntity();
                    if (!level.getWorldBorder().isWithinBounds(entity.blockPosition())) return;
                    InteractionResult result = gameMode.interactAt(player, entity, entityHit, hand);
                    if (!result.consumesAction()) result = gameMode.interact(player, entity, hand);
                    if (result instanceof InteractionResult.Success success) {
                        if (success.swingSource() == InteractionResult.SwingSource.CLIENT) player.swing(hand);
                        return;
                    }
                } else if (hitResult.getType() == HitResult.Type.BLOCK
                        && !ZombiesHologramsbugFixClient.config.ignoreBlockReactions) {
                    BlockHitResult blockHit = (BlockHitResult) hitResult;
                    if (!level.getBlockState(blockHit.getBlockPos()).isAir()) {
                        int count = stack.getCount();
                        InteractionResult result = gameMode.useItemOn(player, hand, blockHit);
                        if (result instanceof InteractionResult.Success success) {
                            if (success.swingSource() == InteractionResult.SwingSource.CLIENT
                                    && !ZombiesHologramsbugFixClient.config.disableRightClickSwinging) {
                                player.swing(hand);
                            }
                            if (!stack.isEmpty() && (stack.getCount() != count || player.hasInfiniteMaterials())) {
                                gameRenderer.itemInHandRenderer.itemUsed(hand);
                            }
                            return;
                        }
                        if (result instanceof InteractionResult.Fail) return;
                    }
                }
            }
            if (!stack.isEmpty()) {
                InteractionResult result = gameMode.useItem(player, hand);
                if (result instanceof InteractionResult.Success success) {
                    if (success.swingSource() == InteractionResult.SwingSource.CLIENT) player.swing(hand);
                    gameRenderer.itemInHandRenderer.itemUsed(hand);
                    return;
                }
            }
        }
    }
}
