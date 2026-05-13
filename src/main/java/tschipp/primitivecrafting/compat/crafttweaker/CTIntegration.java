package tschipp.primitivecrafting.compat.crafttweaker;

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

import java.util.List;

@ZenRegister
@ZenClass("mods.primitivecrafting")
public class CTIntegration
{

	private static int recipeCount = 0;
	private static int toolRecipeCount = 0;
	private static int shapelessRecipeCount = 0;

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

	@ZenMethod
	public static void addToolRecipe(IItemStack output, IIngredient input, IIngredient tool, int durabilityCost)
	{
		addToolRecipe(output, input, tool, durabilityCost, PrimitiveCrafting.MODID + ":tool_recipe_" + toolRecipeCount);
		toolRecipeCount++;
	}

	@ZenMethod
	public static void addToolRecipe(IItemStack output, IIngredient input, IIngredient tool, int durabilityCost, String registryName)
	{
		if (input != null && tool != null && output != null)
		{
			if (!(input instanceof ILiquidStack) && !(tool instanceof ILiquidStack))
			{
				ItemStack stackOutput = CraftTweakerMC.getItemStack(output);

				int countInput = input.getAmount();
				int countTool = tool.getAmount();

				if (!stackOutput.isEmpty())
				{
					CTShapelessIngredient pInput = new CTShapelessIngredient(input, countInput);
					CTToolIngredient pTool = new CTToolIngredient(tool, countTool, durabilityCost);

					IPrimitiveRecipe recipe = new PrimitiveRecipe(stackOutput, pInput, pTool, new ResourceLocation(registryName));

					RecipeRegistry.registerRecipe(recipe);
				}
			}
		}
	}

	@ZenMethod
	public static void addShapelessRecipe(IItemStack output, IIngredient a, IIngredient b)
	{
		addShapelessRecipe(output, a, b, PrimitiveCrafting.MODID + ":shapeless_recipe_" + shapelessRecipeCount);
		shapelessRecipeCount++;
	}

	@ZenMethod
	public static void addShapelessRecipe(IItemStack output, IIngredient a, IIngredient b, String registryName)
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
					CTShapelessIngredient pA = new CTShapelessIngredient(a, countA);
					CTShapelessIngredient pB = new CTShapelessIngredient(b, countB);

					IPrimitiveRecipe recipe = new PrimitiveRecipe(stackOutput, pA, pB, new ResourceLocation(registryName));

					RecipeRegistry.registerRecipe(recipe);
				}
			}
		}
	}

}
