package com.chaottic.adaptive.villagers.common.mixin;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.world.entity.npc.Villager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(VillagerRenderer.class)
public abstract class VillagerRendererMixin extends MobRenderer<Villager, VillagerModel<Villager>> {

    private VillagerRendererMixin(EntityRendererProvider.Context context, VillagerModel<Villager> entityModel, float f) {
        super(context, entityModel, f);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(EntityRendererProvider.Context context, CallbackInfo ci) {
        layers.clear();
    }

    @ModifyArgs(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/MobRenderer;<init>(Lnet/minecraft/client/renderer/entity/EntityRendererProvider$Context;Lnet/minecraft/client/model/EntityModel;F)V"))
    private static void init(Args args) {
        EntityRendererProvider.Context context = args.get(0);

        args.set(1, new HumanoidModel<Villager>(context.bakeLayer(ModelLayers.ZOMBIE)));
    }
}
