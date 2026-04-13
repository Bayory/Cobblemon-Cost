# 🔥 Emboar Cosmetic Mod — Cobblemon Fabric 1.21.1

Mod Java que adiciona o **Kit de Estilo**, um item que aplica sua skin cosmética
customizada na linha evolutiva do Emboar (Tepig → Pignite → Emboar).

---

## 📁 Estrutura completa

```
src/main/
├── java/com/seuskin/cobblemonmod/
│   ├── EmboarCosmeticMod.java         ← Classe principal + aba criativa
│   └── item/
│       ├── ModItems.java              ← Registro do item
│       └── StyleKitItem.java          ← Lógica: aplicar/remover skin
│
└── resources/
    ├── fabric.mod.json
    ├── assets/
    │   ├── emboarcosmeticmod/         ← Recursos do mod (item, aba)
    │   │   ├── models/item/style_kit.json
    │   │   ├── textures/item/style_kit.png
    │   │   └── lang/{pt_br,en_us}.json
    │   └── cobblemon/                 ← Seus arquivos de skin (originais!)
    │       ├── textures/pokemon/
    │       │   ├── 0498_tepig/tepig_cosmetic.png
    │       │   ├── 0499_pignite/pignite_cosmetic.png
    │       │   └── 0500_emboar/{emboar,mega_emboar}_cosmetic.png
    │       └── bedrock/pokemon/resolvers/
    │           ├── 0498_tepig_cosmetic.json
    │           ├── 0499_pignite_cosmetic.json
    │           └── 0500_emboar_cosmetic.json
    └── data/cobblemon/
        ├── species_features/emboar_cosmetic.json
        └── species_additions/{tepig,pignite,emboar}.json
```

---

## 🚀 Como Compilar

```bash
# 1. Clone ou extraia o projeto
# 2. Execute:
./gradlew build

# O .jar ficará em:
build/libs/emboar-cosmetic-mod-1.0.0.jar
```

---

## 🎮 Como usar no jogo

1. Entre no **Modo Criativo** → procure a aba **"Emboar Cosmetics"**
2. Pegue o **Kit de Estilo** (ícone de chama laranja)
3. **Clique direito** em um Tepig, Pignite ou Emboar para **aplicar** a skin
4. **Shift + clique direito** para **remover** a skin
5. Fora do criativo, consome 1 item ao aplicar

### Via comando:
```
/give @p emboarcosmeticmod:style_kit
```

> ⚠️ **Não tem receita de craft** — só obtível via criativo ou comando.

---

## ⚙️ Dependências

| Mod | Versão |
|---|---|
| Minecraft | 1.21.1 |
| Fabric Loader | ≥ 0.16.5 |
| Fabric API | 0.102.0+1.21.1 |
| Cobblemon | 1.6.1+1.21.1 |

---

## 🔧 Detalhes técnicos

- **Aspect usado:** `emboar_cosmetic`
- **Pokémons compatíveis:** Tepig (#498), Pignite (#499), Emboar (#500)
- Os resolvers do Cobblemon mapeiam o aspect para as texturas corretas
- A mega evolução do Emboar também tem skin própria (`mega_emboar_cosmetic.png`)
