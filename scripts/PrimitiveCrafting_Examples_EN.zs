// ============================================================================
// PrimitiveCrafting - CraftTweaker Script Configuration Examples
// This file demonstrates how to configure primitive crafting recipes via CraftTweaker scripts
// ============================================================================

// ----------------------------------------------------------------------------
// 1. Tool Recipes (addToolRecipe)
// ----------------------------------------------------------------------------
// Syntax: mods.primitivecrafting.addToolRecipe(output, material, tool, durabilityCost);
//         mods.primitivecrafting.addToolRecipe(output, material, tool, durabilityCost, "registryName");
//
// Description: Crafts a new item using a specific tool + material
//              - Material is consumed
//              - Tool is retained but loses specified durability
// ----------------------------------------------------------------------------

// Example 1: Craft flint from gravel using a stone pickaxe, consumes 2 durability
mods.primitivecrafting.addToolRecipe(<minecraft:flint>, <minecraft:gravel>, <minecraft:stone_pickaxe>, 2);

// Example 2: Craft 3 bone meal from bone using an iron sword, consumes 5 durability
mods.primitivecrafting.addToolRecipe(<minecraft:dye:15> * 3, <minecraft:bone>, <minecraft:iron_sword>, 5);

// Example 3: Craft 4 sticks from any axe and any log, consumes 1 durability
mods.primitivecrafting.addToolRecipe(<minecraft:stick> * 4, <ore:logWood>, <ore:axe>, 1);

// Example 4: Craft gravel from cobblestone using a golden pickaxe, consumes 3 durability, with custom registry name
mods.primitivecrafting.addToolRecipe(<minecraft:gravel>, <minecraft:cobblestone>, <minecraft:golden_pickaxe>, 3, "primitivecrafting:gravel_from_cobble");

// Example 5: Craft 4 strings from any wool using shears, consumes 1 durability
mods.primitivecrafting.addToolRecipe(<minecraft:string> * 4, <minecraft:wool:*>, <minecraft:shears>, 1);

// ----------------------------------------------------------------------------
// 2. Shapeless Recipes (addShapelessRecipe)
// ----------------------------------------------------------------------------
// Syntax: mods.primitivecrafting.addShapelessRecipe(output, materialA, materialB);
//         mods.primitivecrafting.addShapelessRecipe(output, materialA, materialB, "registryName");
//
// Description: Crafts a new item directly from two materials. Both materials are consumed.
// ----------------------------------------------------------------------------

// Example 6: Craft 2 bowls from 2 oak planks + 1 stick
mods.primitivecrafting.addShapelessRecipe(<minecraft:bowl> * 2, <minecraft:planks:0> * 2, <minecraft:stick>);

// Example 7: Craft 8 torches from cobblestone and coal
mods.primitivecrafting.addShapelessRecipe(<minecraft:torch> * 8, <minecraft:cobblestone>, <minecraft:coal:0>);

// Example 8: Craft a name tag from paper and leather, with custom registry name
mods.primitivecrafting.addShapelessRecipe(<minecraft:name_tag>, <minecraft:paper> * 3, <minecraft:leather>, "primitivecrafting:name_tag_recipe");

// Example 9: OreDict usage - any plank + any dye crafts stained clay
mods.primitivecrafting.addShapelessRecipe(<minecraft:stained_hardened_clay:*>, <ore:plankWood>, <ore:dye>);

// ----------------------------------------------------------------------------
// 3. Raw Recipes (addRecipe) - Preserves CraftTweaker converter behavior
// ----------------------------------------------------------------------------
// Syntax: mods.primitivecrafting.addRecipe(output, materialA, materialB);
// Description: This method allows CraftTweaker to automatically handle item transformation logic
//              (e.g., .reuse(), .transformDamage(), etc.)
// ----------------------------------------------------------------------------

// Example 10: Water bucket + lava bucket crafts obsidian, both buckets are returned (reuse)
mods.primitivecrafting.addRecipe(<minecraft:obsidian>, <minecraft:water_bucket>.reuse(), <minecraft:lava_bucket>.reuse());

// ----------------------------------------------------------------------------
// 4. Removing Recipes
// ----------------------------------------------------------------------------

// Remove a specific recipe by output and materials
// mods.primitivecrafting.removeRecipe(<output>, <materialA>, <materialB>);

// Remove all recipes associated with the output item
// mods.primitivecrafting.removeRecipeForStack(<output>);

// ----------------------------------------------------------------------------
// 5. GameStage Control (Requires RecipeStages mod)
// ----------------------------------------------------------------------------

// Add a stage restriction to a specific recipe by its registry name
// mods.primitivecrafting.addRecipeStage("stage_name", "recipe_registry_name");

// Add a stage restriction to all recipes producing the given output item
// mods.primitivecrafting.addRecipeStageForStack("stage_name", <output>);

// Remove stage restriction from a recipe
// mods.primitivecrafting.removeRecipeStage("recipe_registry_name");

// Remove stage restriction from all recipes producing the given output item
// mods.primitivecrafting.removeRecipeStageForStack(<output>);