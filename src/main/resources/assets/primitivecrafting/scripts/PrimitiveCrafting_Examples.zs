// ============================================================================
// PrimitiveCrafting - CraftTweaker 脚本配置示例
// 此文件展示了如何通过 CraftTweaker 脚本配置原始合成配方
// ============================================================================

// ----------------------------------------------------------------------------
// 一、工具合成配方 (addToolRecipe)
// ----------------------------------------------------------------------------
// 格式: mods.primitivecrafting.addToolRecipe(产出, 材料, 工具, 耐久消耗);
//        mods.primitivecrafting.addToolRecipe(产出, 材料, 工具, 耐久消耗, "注册名");
//
// 说明: 使用指定工具 + 材料合成新物品
//       - 材料会被消耗
//       - 工具会保留但消耗指定耐久值
// ----------------------------------------------------------------------------

// 示例1: 用石镐和沙砾合成燧石，石镐消耗2点耐久
mods.primitivecrafting.addToolRecipe(<minecraft:flint>, <minecraft:gravel>, <minecraft:stone_pickaxe>, 2);

// 示例2: 用铁剑和骨头合成骨粉，铁剑消耗5点耐久
mods.primitivecrafting.addToolRecipe(<minecraft:dye:15> * 3, <minecraft:bone>, <minecraft:iron_sword>, 5);

// 示例3: 用任意斧头和原木合成木棍(产出4个)，斧头消耗1点耐久
mods.primitivecrafting.addToolRecipe(<minecraft:stick> * 4, <ore:logWood>, <ore:axe>, 1);

// 示例4: 用金镐和圆石合成沙砾，金镐消耗3点耐久，指定注册名
mods.primitivecrafting.addToolRecipe(<minecraft:gravel>, <minecraft:cobblestone>, <minecraft:golden_pickaxe>, 3, "primitivecrafting:gravel_from_cobble");

// 示例5: 用剪刀和羊毛合成线(产出4个)，剪刀消耗1点耐久
mods.primitivecrafting.addToolRecipe(<minecraft:string> * 4, <minecraft:wool:*>, <minecraft:shears>, 1);

// ----------------------------------------------------------------------------
// 二、直接合成配方 (addShapelessRecipe)
// ----------------------------------------------------------------------------
// 格式: mods.primitivecrafting.addShapelessRecipe(产出, 材料A, 材料B);
//        mods.primitivecrafting.addShapelessRecipe(产出, 材料A, 材料B, "注册名");
//
// 说明: 两个材料直接合成新物品，两个材料都会被消耗
// ----------------------------------------------------------------------------

// 示例6: 用2个橡木木板+1个木棍合成2个碗
mods.primitivecrafting.addShapelessRecipe(<minecraft:bowl> * 2, <minecraft:planks:0> * 2, <minecraft:stick>);

// 示例7: 用石头和煤炭合成8个火把
mods.primitivecrafting.addShapelessRecipe(<minecraft:torch> * 8, <minecraft:cobblestone>, <minecraft:coal:0>);

// 示例8: 用纸和皮革合成命名牌，指定注册名
mods.primitivecrafting.addShapelessRecipe(<minecraft:name_tag>, <minecraft:paper> * 3, <minecraft:leather>, "primitivecrafting:name_tag_recipe");

// 示例9: 用矿辞方式 - 任意木板+任意染料合成彩色黏土
mods.primitivecrafting.addShapelessRecipe(<minecraft:stained_hardened_clay:*>, <ore:plankWood>, <ore:dye>);

// ----------------------------------------------------------------------------
// 三、原始配方 (addRecipe) - 保留 CraftTweaker 转换器行为
// ----------------------------------------------------------------------------
// 格式: mods.primitivecrafting.addRecipe(产出, 材料A, 材料B);
// 说明: 此方法会让 CraftTweaker 自动处理物品转换逻辑
//       (如 .reuse(), .transformDamage() 等)
// ----------------------------------------------------------------------------

// 示例10: 水桶+熔岩桶合成黑曜石，水桶返还空桶(reuse)
mods.primitivecrafting.addRecipe(<minecraft:obsidian>, <minecraft:water_bucket>.reuse(), <minecraft:lava_bucket>.reuse());

// ----------------------------------------------------------------------------
// 四、Ban 配方 - 禁止在原始合成中使用指定配方
// ----------------------------------------------------------------------------
// 格式: mods.primitivecrafting.banRecipe("配方注册名");
//        mods.primitivecrafting.banRecipeForStack(<产出物品>);
//
// 说明: 被 ban 的配方将无法在原始合成(2格合成)中使用，
//       但仍可在工作台等正常合成方式中使用。
// ----------------------------------------------------------------------------

// 示例11: 禁止木棍配方在原始合成中使用
mods.primitivecrafting.banRecipe("minecraft:stick");

// 示例12: 禁止工作台配方在原始合成中使用
mods.primitivecrafting.banRecipe("minecraft:crafting_table");

// 示例13: 禁止所有产出为木板的配方在原始合成中使用
mods.primitivecrafting.banRecipeForStack(<minecraft:planks:*>);

// ----------------------------------------------------------------------------
// 五、移除配方
// ----------------------------------------------------------------------------

// 按产出和材料移除指定配方
// mods.primitivecrafting.removeRecipe(<产出>, <材料A>, <材料B>);

// 按产出物品移除所有相关配方
// mods.primitivecrafting.removeRecipeForStack(<产出>);

// 删除所有原始合成配方 (清空全部)
// mods.primitivecrafting.removeAllRecipes();

// ----------------------------------------------------------------------------
// 六、GameStage 阶段控制 (需要 RecipeStages 模组)
// ----------------------------------------------------------------------------

// 为指定配方添加阶段限制
// mods.primitivecrafting.addRecipeStage("stage_name", "recipe_registry_name");

// 为产出指定物品的所有配方添加阶段限制
// mods.primitivecrafting.addRecipeStageForStack("stage_name", <产出物品>);

// 移除配方的阶段限制
// mods.primitivecrafting.removeRecipeStage("recipe_registry_name");

// 移除产出指定物品的所有配方的阶段限制
// mods.primitivecrafting.removeRecipeStageForStack(<产出物品>);