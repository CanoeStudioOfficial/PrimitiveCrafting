package tschipp.primitivecrafting.compat.crafttweaker;

import crafttweaker.api.item.IIngredient;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import tschipp.primitivecrafting.common.crafting.PrimitiveIngredient;
import tschipp.primitivecrafting.common.crafting.TransformData;
import tschipp.primitivecrafting.common.crafting.TransformData.TransformType;

/**
 * 直接合成材料类 - 合成后原材料不保留，直接消耗
 * 用于"物品 + 物品 = 产出"类型的直接合成配方
 */
public class CTShapelessIngredient extends PrimitiveIngredient
{

	private TransformData transformData;

	/**
	 * @param ingredient CraftTweaker 的物品 IIngredient
	 * @param count      物品消耗数量
	 */
	public CTShapelessIngredient(IIngredient ingredient, int count)
	{
		this.count = count;
		this.ingredient = CraftTweakerMC.getIngredient(ingredient);
		this.transformData = TransformData.getTransformData(TransformType.SHRINK, count);
	}

	@Override
	public TransformData getTransformForStack(ItemStack stack)
	{
		return transformData;
	}
}