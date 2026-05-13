package tschipp.primitivecrafting.compat.crafttweaker;

import java.util.List;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.liquid.ILiquidStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import tschipp.primitivecrafting.PrimitiveCrafting;
import tschipp.primitivecrafting.common.crafting.IPrimitiveRecipe;
import tschipp.primitivecrafting.common.crafting.PrimitiveIngredient;
import tschipp.primitivecrafting.common.crafting.PrimitiveRecipe;
import tschipp.primitivecrafting.common.crafting.RecipeRegistry;
import tschipp.primitivecrafting.common.crafting.TransformData;
import tschipp.primitivecrafting.common.crafting.TransformData.TransformType;

@ZenRegister
@ZenClass("mods.primitivecrafting")
public class CTIntegration
{

	private static int recipeCount = 0;

	@ZenMethod
	public static void addRecipe(IItemStack output, IIngredient a, IIngredient b, String registryName, String gamestage)
	{
		if (a != null && b != null && output != null)
		{
			if (!(a instanceof ILiquidStack) && !(b instanceof ILiquidStack))
			{
				ItemStack stackOutput = CraftTweakerMC.getItemStack(output);

				int countA = a.getAmount();
				int countB = b.getAmount();

				if (!stackOutput.isEmpty())
				{
					CTPrimitiveIngredient pA;
					CTPrimitiveIngredient pB;

					pA = new CTPrimitiveIngredient(a, countA);
					pB = new CTPrimitiveIngredient(b, countB);

					IPrimitiveRecipe recipe = new PrimitiveRecipe(stackOutput, pA, pB, new ResourceLocation(registryName));
					recipe.setTier(gamestage);

					RecipeRegistry.registerRecipe(recipe);
				}
			}
		}
	}

	@ZenMethod
	public static void addRecipe(IItemStack output, IIngredient a, IIngredient b)
	{
		addRecipe(output, a, b, PrimitiveCrafting.MODID + ":primitive_crafttweaker_recipe_" + recipeCount, "");
		recipeCount++;
	}

	@ZenMethod
	public static void addRecipe(IItemStack output, IIngredient a, IIngredient b, String registryName)
	{
		addRecipe(output, a, b, registryName, "");
		recipeCount++;
	}

	/**
	 * Adds a primitive crafting recipe where one ingredient is a tool that takes damage instead of being consumed.
	 * The tool ingredient will have its durability reduced but remain in the inventory.
	 * The other ingredient will be consumed normally.
	 * 
	 * @param output The result item
	 * @param toolIngredient The tool ingredient (will take damage but not be consumed)
	 * @param consumeIngredient The ingredient to be consumed
	 * @param damageAmount The amount of damage to apply to the tool (default: 1)
	 */
	@ZenMethod
	public static void addToolRecipe(IItemStack output, IIngredient toolIngredient, IIngredient consumeIngredient, String registryName, String gamestage, int damageAmount)
	{
		if (toolIngredient != null && consumeIngredient != null && output != null)
		{
			if (!(toolIngredient instanceof ILiquidStack) && !(consumeIngredient instanceof ILiquidStack))
			{
				ItemStack stackOutput = CraftTweakerMC.getItemStack(output);

				int toolCount = toolIngredient.getAmount();
				int consumeCount = consumeIngredient.getAmount();

				if (!stackOutput.isEmpty())
				{
					// Create tool ingredient with DAMAGE transform
					PrimitiveIngredient toolPrimIng = createToolIngredient(toolIngredient, toolCount, damageAmount);
					
					// Create consume ingredient with normal SHRINK transform
					CTPrimitiveIngredient consumePrimIng = new CTPrimitiveIngredient(consumeIngredient, consumeCount);

					IPrimitiveRecipe recipe = new PrimitiveRecipe(stackOutput, toolPrimIng, consumePrimIng, new ResourceLocation(registryName));
					recipe.setTier(gamestage);

					RecipeRegistry.registerRecipe(recipe);
				}
			}
		}
	}

	/**
	 * Adds a primitive crafting recipe where one ingredient is a tool that takes damage instead of being consumed.
	 * The tool ingredient will have its durability reduced but remain in the inventory.
	 * The other ingredient will be consumed normally.
	 * Default damage amount is 1.
	 * 
	 * @param output The result item
	 * @param toolIngredient The tool ingredient (will take damage but not be consumed)
	 * @param consumeIngredient The ingredient to be consumed
	 */
	@ZenMethod
	public static void addToolRecipe(IItemStack output, IIngredient toolIngredient, IIngredient consumeIngredient)
	{
		addToolRecipe(output, toolIngredient, consumeIngredient, PrimitiveCrafting.MODID + ":primitive_tool_recipe_" + recipeCount, "", 1);
		recipeCount++;
	}

	/**
	 * Adds a primitive crafting recipe where one ingredient is a tool that takes damage instead of being consumed.
	 * The tool ingredient will have its durability reduced but remain in the inventory.
	 * The other ingredient will be consumed normally.
	 * 
	 * @param output The result item
	 * @param toolIngredient The tool ingredient (will take damage but not be consumed)
	 * @param consumeIngredient The ingredient to be consumed
	 * @param damageAmount The amount of damage to apply to the tool
	 */
	@ZenMethod
	public static void addToolRecipe(IItemStack output, IIngredient toolIngredient, IIngredient consumeIngredient, int damageAmount)
	{
		addToolRecipe(output, toolIngredient, consumeIngredient, PrimitiveCrafting.MODID + ":primitive_tool_recipe_" + recipeCount, "", damageAmount);
		recipeCount++;
	}

	/**
	 * Adds a primitive crafting recipe where one ingredient is a tool that takes damage instead of being consumed.
	 * The tool ingredient will have its durability reduced but remain in the inventory.
	 * The other ingredient will be consumed normally.
	 * 
	 * @param output The result item
	 * @param toolIngredient The tool ingredient (will take damage but not be consumed)
	 * @param consumeIngredient The ingredient to be consumed
	 * @param registryName The recipe registry name
	 */
	@ZenMethod
	public static void addToolRecipe(IItemStack output, IIngredient toolIngredient, IIngredient consumeIngredient, String registryName)
	{
		addToolRecipe(output, toolIngredient, consumeIngredient, registryName, "", 1);
		recipeCount++;
	}

	/**
	 * Creates a PrimitiveIngredient with DAMAGE transform type for tools.
	 */
	private static PrimitiveIngredient createToolIngredient(IIngredient ingredient, int count, int damageAmount)
	{
		Ingredient mcIngredient = CraftTweakerMC.getIngredient(ingredient);
		PrimitiveIngredient primIng = new PrimitiveIngredient(mcIngredient, count, false);
		
		ItemStack[] stacks = mcIngredient.getMatchingStacks();
		TransformData[] transformData = new TransformData[stacks.length];
		
		for (int i = 0; i < stacks.length; i++)
		{
			transformData[i] = TransformData.getTransformData(TransformType.DAMAGE, damageAmount);
		}
		
		primIng.transformData = transformData;
		
		return primIng;
	}

	@ZenMethod
	public static void addRecipeStage(String gamestage, String recipeName)
	{
		IPrimitiveRecipe rec = RecipeRegistry.getRecipe(new ResourceLocation(recipeName));
		if (rec != null)
		{
			rec.setTier(gamestage);
		}
	}

	@ZenMethod
	public static void addRecipeStageForStack(String gamestage, IItemStack stack)
	{
		if (stack != null)
		{
			ItemStack mcstack = CraftTweakerMC.getItemStack(stack);
			for (IPrimitiveRecipe recipe : RecipeRegistry.getRecipeForStack(mcstack))
			{
				recipe.setTier(gamestage);
			}
		}
	}

	@ZenMethod
	public static void removeRecipeStage(String recipeName)
	{
		IPrimitiveRecipe rec = RecipeRegistry.getRecipe(new ResourceLocation(recipeName));
		if (rec != null)
		{
			rec.setTier("");
		}
	}

	@ZenMethod
	public static void removeRecipeStageForStack(IItemStack stack)
	{
		if (stack != null)
		{
			ItemStack mcstack = CraftTweakerMC.getItemStack(stack);
			for (IPrimitiveRecipe recipe : RecipeRegistry.getRecipeForStack(mcstack))
			{
				recipe.setTier("");
			}
		}
	}

	@ZenMethod
	public static void removeRecipe(IItemStack output, IIngredient a, IIngredient b)
	{
		if (a != null && b != null && output != null)
		{
			if (!(a instanceof ILiquidStack) && !(b instanceof ILiquidStack))
			{
				Ingredient ingA = CraftTweakerMC.getIngredient(a);
				Ingredient ingB = CraftTweakerMC.getIngredient(b);
				ItemStack stackOutput = CraftTweakerMC.getItemStack(output);

				int countA = a.getAmount();
				int countB = b.getAmount();

				if (ingA != null && ingB != null && !stackOutput.isEmpty())
				{
					PrimitiveIngredient pA = new PrimitiveIngredient(ingA, countA);
					PrimitiveIngredient pB = new PrimitiveIngredient(ingB, countB);

					RecipeRegistry.removeRecipe(pA, pB, stackOutput);
				}
			}
		}
	}

	@ZenMethod
	public static void removeRecipeForStack(IItemStack output)
	{
		if (output != null)
		{
			ItemStack stack = CraftTweakerMC.getItemStack(output);
			List<IPrimitiveRecipe> recipes = RecipeRegistry.getRecipeForStack(stack);
			
			RecipeRegistry.removeAll(stack, recipes);
		}
	}

}
