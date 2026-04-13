package com.seuskin.cobblemonmod;

import com.seuskin.cobblemonmod.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CosmeticoViltrum implements ModInitializer {

    public static final String MOD_ID = "cosmeticoviltrum";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    /** Aba "Cosmético: Viltrum" no menu criativo */
    public static final ItemGroup VILTRUM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.EMBOAR_ALL))
            .displayName(Text.translatable("itemgroup.cosmeticoviltrum.main"))
            .entries((context, entries) -> {
                entries.add(ModItems.EMBOAR_ALL);
            })
            .build();

    @Override
    public void onInitialize() {
        LOGGER.info("[Cosmético: Viltrum] Carregando mod...");

        ModItems.register();

        Registry.register(
                Registries.ITEM_GROUP,
                Identifier.of(MOD_ID, "main"),
                VILTRUM_GROUP
        );

        LOGGER.info("[Cosmético: Viltrum] Mod carregado! Use 'Emboar All' na linha do Emboar.");
    }
}
