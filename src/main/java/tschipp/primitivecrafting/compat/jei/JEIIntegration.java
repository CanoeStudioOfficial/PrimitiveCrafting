package tschipp.primitivecrafting.compat.jei;

import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import tschipp.primitivecrafting.compat.jei.crafting.PrimitiveCraftingCategory;
import tschipp.primitivecrafting.compat.jei.crafting.PrimitiveCraftingWrapper;

import java.util.ArrayList;
import java.util.List;

@JEIPlugin
public class JEIIntegration implements IModPlugin
{
	public static IRecipeRegistry reg;
	
	public static List<PrimitiveCraftingWrapper> allRecipes = new ArrayList<PrimitiveCraftingWrapper>();
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry)
	{
		registry.addRecipeCategories(new PrimitiveCraftingCategory(registry.getJeiHelpers().getGuiHelper()));
	}

	@Override
	public void register(IModRegistry registry)
	{
		registry.addRecipes(PrimitiveCraftingCategory.getRecipes(), "primitive_crafting");
	}

	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
	{
		reg = jeiRuntime.getRecipeRegistry();
		List<PrimitiveCraftingWrapper> wrappers = JEIIntegration.reg.getRecipeWrappers(JEIIntegration.reg.getRecipeCategory("primitive_crafting"));
		allRecipes.addAll(wrappers);
	}
}
