package me.angeschossen.lands.api.role;

import com.github.angeschossen.pluginframework.api.events.ExpressionEntity;
import me.angeschossen.lands.api.flags.type.RoleFlag;
import me.angeschossen.lands.api.role.enums.RoleType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;


public interface Role extends ExpressionEntity {

    @NotNull
    ItemStack getIcon();

    void setIcon(@NotNull ItemStack icon);

    RoleType getType();

    @NotNull
    String getName();

    void setName(@NotNull String name) throws IllegalArgumentException;

    @NotNull
    String getColorName();

    boolean isVisitorRole();

    boolean toggleFlag(RoleFlag flag);

    boolean hasFlag(RoleFlag flag);
}