package fr.iglee42.techresourcesbase;

import fr.iglee42.techresourcesbase.init.*;
import fr.iglee42.techresourcesbase.utils.CustomGroup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.Tag;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.ArrayList;
import java.util.List;

@Mod(TechResourcesBase.MODID)
public class TechResourcesBase {

    public static final String MODID = "techresourcesbase";



    public TechResourcesBase() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlock.BLOCKS.register(bus);
        ModTileEntity.TILE_ENTITIES.register(bus);
        ModItem.ITEMS.register(bus);
        ModSurfaceBuilder.SURFACE_BUILDERS.register(bus);
        ModContainers.CONTAINERS.register(bus);

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

    }

    @SubscribeEvent
    public void biomeEvent(BiomeLoadingEvent event){
        event.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES).clear();
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.DISK_SAND);
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.DISK_CLAY);
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.DISK_GRAVEL);
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_DIRT);
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_GRAVEL);
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_GRANITE);
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_DIORITE);
        event.getGeneration().addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Features.ORE_ANDESITE);
    }

    private void setup(FMLCommonSetupEvent e){
        MinecraftForge.EVENT_BUS.register(this);
        ModFeatures features = new ModFeatures();
        features.init();
        MinecraftForge.EVENT_BUS.register(features);
    }
    private void clientSetup(FMLClientSetupEvent e){
        RenderTypeLookup.setRenderLayer(ModBlock.MODIUM_GENERATOR.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlock.DERIUM_GENERATOR.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlock.BLAZUM_GENERATOR.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlock.MANUAL_GENERATOR.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(ModBlock.PILLAR.get(), RenderType.cutoutMipped());
    }
}
