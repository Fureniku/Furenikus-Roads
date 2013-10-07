package co.uk.silvania.roads.entity;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;

final class EntityHorseBredSelector implements IEntitySelector
{
    /**
     * Return whether the specified entity is applicable to this filter.
     */
    public boolean isEntityApplicable(Entity par1Entity)
    {
        return par1Entity instanceof EntityRoadHorse && ((EntityRoadHorse)par1Entity).func_110205_ce();
    }
}
