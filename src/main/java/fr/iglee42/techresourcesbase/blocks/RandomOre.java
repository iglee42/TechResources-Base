package fr.iglee42.techresourcesbase.blocks;

import fr.iglee42.techresourcesbase.config.TechResourcesBaseCommonConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RandomOre extends Block {
    public RandomOre(Properties prop) {
        super(prop);
    }

    @Override
    public List<ItemStack> getDrops(BlockState p_60537_, LootContext.Builder p_60538_) {
        ItemStack ore = new ItemStack(selectOre(),RANDOM.nextInt(16)+1);
        List<ItemStack> drop = new ArrayList<>();
        drop.add(ore);
        return drop;
    }

    private Item selectOre() {
        Item ore = randomOre();
        List<Item> blacklisted = new ArrayList<>();
        if (TechResourcesBaseCommonConfig.BLACKLISTED_RANDOM_ORE_DROPS.get().isEmpty()) return ore;
        for (String s : TechResourcesBaseCommonConfig.BLACKLISTED_RANDOM_ORE_DROPS.get()){
            blacklisted.add(ForgeRegistries.ITEMS.getValue(new ResourceLocation(s)));
        }
        if (blacklisted.contains(ore)){
            ore = selectOre();
        }

        return ore;
    }

    private Item randomOre(){
        return ForgeRegistries.ITEMS.tags().getTag(Tags.Items.ORES).getRandomElement(RANDOM).get();
    }

    @Override
    public void appendHoverText(ItemStack p_49816_, @Nullable BlockGetter p_49817_, List<Component> p_49818_, TooltipFlag p_49819_) {
        p_49818_.add(new TextComponent("§eDrop a random ore"));
        super.appendHoverText(p_49816_, p_49817_, p_49818_, p_49819_);
    }
}
