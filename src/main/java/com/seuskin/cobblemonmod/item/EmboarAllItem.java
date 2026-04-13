package com.seuskin.cobblemonmod.item;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.seuskin.cobblemonmod.CosmeticoViltrum;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;

import java.util.List;
import java.util.Set;

/**
 * Emboar All — aplica ou remove a skin cosmética "emboar_cosmetic"
 * em Tepig, Pignite e Emboar (incluindo Mega Emboar automaticamente).
 *
 * O Mega é tratado pelo resolver do Cobblemon:
 *   aspects: ["emboar_cosmetic", "mega"] → mega_emboar_cosmetic.png
 * Então basta aplicar "emboar_cosmetic" e o Cobblemon cuida do resto.
 *
 * ✔ Clique direito em Pokémon → aplica skin
 * ✘ Shift + clique direito   → remove skin
 * ⚠ Não craftável
 */
public class EmboarAllItem extends Item {

    public static final String COSMETIC_ASPECT = "emboar_cosmetic";

    private static final Set<String> VALID_SPECIES = Set.of("tepig", "pignite", "emboar");

    public EmboarAllItem() {
        super(new Item.Settings().maxCount(16));
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand) {
        if (!(entity instanceof PokemonEntity pokemonEntity)) {
            return ActionResult.PASS;
        }

        Pokemon pokemon = pokemonEntity.getPokemon();
        String speciesName = pokemon.getSpecies().getName().toLowerCase();

        // Só funciona na linha evolutiva do Emboar
        if (!VALID_SPECIES.contains(speciesName)) {
            if (!player.getWorld().isClient()) {
                player.sendMessage(
                        Text.literal("✗ Emboar All só funciona em Tepig, Pignite ou Emboar!")
                                .formatted(Formatting.RED),
                        true
                );
            }
            return ActionResult.FAIL;
        }

        if (!player.getWorld().isClient()) {
            boolean hasCosmetic = pokemon.getAspects().contains(COSMETIC_ASPECT);

            if (player.isSneaking()) {
                // SHIFT + clique: REMOVE
                if (hasCosmetic) {
                    pokemon.getAspects().remove(COSMETIC_ASPECT);
                    pokemon.updateAspects();

                    player.sendMessage(
                            Text.literal("✗ Cosmético removido de ")
                                    .formatted(Formatting.YELLOW)
                                    .append(Text.literal(pokemon.getSpecies().getName()).formatted(Formatting.WHITE))
                                    .append(Text.literal(".").formatted(Formatting.YELLOW)),
                            true
                    );
                    CosmeticoViltrum.LOGGER.info("[Viltrum] Skin removida de {} por {}",
                            speciesName, player.getName().getString());
                } else {
                    player.sendMessage(
                            Text.literal("ℹ Este Pokémon não tem o cosmético.")
                                    .formatted(Formatting.GRAY),
                            true
                    );
                }

            } else {
                // Clique normal: APLICA
                if (hasCosmetic) {
                    player.sendMessage(
                            Text.literal("ℹ ")
                                    .formatted(Formatting.GRAY)
                                    .append(Text.literal(pokemon.getSpecies().getName()).formatted(Formatting.WHITE))
                                    .append(Text.literal(" já tem o cosmético!").formatted(Formatting.GRAY)),
                            true
                    );
                    return ActionResult.SUCCESS;
                }

                pokemon.getAspects().add(COSMETIC_ASPECT);
                pokemon.updateAspects();

                // Detecta se está em forma Mega para a mensagem
                boolean isMega = pokemon.getAspects().contains("mega");
                String displayName = (isMega ? "Mega " : "") + pokemon.getSpecies().getName();

                player.sendMessage(
                        Text.literal("✔ Cosmético aplicado em ")
                                .formatted(Formatting.GREEN)
                                .append(Text.literal(displayName).formatted(Formatting.GOLD))
                                .append(Text.literal("!").formatted(Formatting.GREEN)),
                        true
                );

                CosmeticoViltrum.LOGGER.info("[Viltrum] Skin '{}' aplicada em {} (mega={}) por {}",
                        COSMETIC_ASPECT, speciesName, isMega, player.getName().getString());

                if (!player.isCreative()) {
                    stack.decrement(1);
                }
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.literal("Clique direito → aplica cosmético").formatted(Formatting.GRAY));
        tooltip.add(Text.literal("Shift + clique → remove cosmético").formatted(Formatting.DARK_GRAY));
        tooltip.add(Text.empty());
        tooltip.add(Text.literal("✔ Tepig  ✔ Pignite  ✔ Emboar").formatted(Formatting.GOLD));
        tooltip.add(Text.literal("✔ Mega Emboar").formatted(Formatting.LIGHT_PURPLE));
        tooltip.add(Text.empty());
        tooltip.add(Text.literal("⚠ Não craftável").formatted(Formatting.DARK_RED));
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.literal("Emboar All").formatted(Formatting.GOLD);
    }
}
