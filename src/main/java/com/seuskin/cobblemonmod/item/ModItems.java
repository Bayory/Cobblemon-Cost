package com.seuskin.cobblemonmod.item;

import com.seuskin.cobblemonmod.CosmeticoViltrum;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final EmboarAllItem EMBOAR_ALL = new EmboarAllItem();

    public static void register() {
        Registry.register(
                Registries.ITEM,
                Identifier.of(CosmeticoViltrum.MOD_ID, "emboar_all"),
                EMBOAR_ALL
        );
        CosmeticoViltrum.LOGGER.info("[Viltrum] Item 'emboar_all' registrado.");
    }
}
