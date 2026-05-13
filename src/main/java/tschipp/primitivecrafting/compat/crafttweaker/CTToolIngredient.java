package tschipp.primitivecrafting.compat.crafttweaker;

import crafttweaker.api.item.IIngredient;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import tschipp.primitivecrafting.common.crafting.PrimitiveIngredient;
import tschipp.primitivecrafting.common.crafting.TransformData;
import tschipp.primitivecrafting.common.crafting.TransformData.TransformType;

/**
 * 工具类材料 - 合成后保留工具但消耗工具耐久度
 * 用于"工具 + 材料 = 产出"类型的合成配方
 */
public class CTToolIngredient extends PrimitiveIngredient
{

	private TransformData transformData;

	/**
	 * @param ingredient     CraftTweaker 的工具 IIngredient
	 * @param count          工具数量 (通常为 1)
	 * @param durabilityCost 每次合成消耗的工具耐久值
	 */
	public CTToolIngredient(IIngredient ingredient, int count, int durabilityCost)
	{
		this.count = count;
		this.ingredient = CraftTweakerMC.getIngredient(ingredient);
		this.transformData = TransformData.getTransformData(TransformType.DAMAGE, durabilityCost);
	}

	@Override
	public TransformData getTransformForStack(ItemStack stack)
	{
		return transformData;
	}
}