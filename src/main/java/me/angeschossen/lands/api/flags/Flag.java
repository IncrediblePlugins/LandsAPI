package me.angeschossen.lands.api.flags;

import com.google.common.base.Preconditions;
import me.angeschossen.lands.api.flags.enums.FlagModule;
import me.angeschossen.lands.api.flags.enums.FlagTarget;
import me.angeschossen.lands.api.handler.APIHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Use {@link me.angeschossen.lands.api.flags.type.parent.Flag} instead.
 */
@Deprecated
public abstract class Flag<T> implements me.angeschossen.lands.api.flags.type.parent.Flag<T> {

    public static final ItemStack ICON_DEFAULT = new ItemStack(Material.NAME_TAG);

    public final FlagTarget target;
    protected final String name;
    protected final @NotNull Plugin plugin;
    private final int hashCode;
    private boolean applyInSubAreas, alwaysAllowInWilderness;
    protected ItemStack icon = ICON_DEFAULT;
    private @Nullable List<String> description;
    private @Nullable String displayName;
    private boolean warState;
    private boolean display;

    protected abstract T self();

    public Flag(@NotNull Plugin plugin, @NotNull Flag.Target target, @NotNull String name, boolean applyInSubAreas, boolean alwaysAllowInWilderness) {
        Preconditions.checkNotNull(name, "Name cannot be null");
        Preconditions.checkNotNull(plugin, "Plugin cannot be null");

        this.target = FlagTarget.valueOf(target.toString());
        this.name = name;
        this.applyInSubAreas = applyInSubAreas;
        this.alwaysAllowInWilderness = alwaysAllowInWilderness;
        this.hashCode = Objects.hash(this.name);
        this.plugin = plugin;

        if (!plugin.equals(APIHandler.getInstance().getPlugin())) {
            Bukkit.getLogger().warning("[Lands] Plugin " + plugin.getName() + " uses a deprecated flag of Lands: https://wiki.incredibleplugins.com/lands/developers/update");
        }
    }

    @Override
    public boolean isAutoDisable() {
        return false;
    }

    @Override
    public @NotNull T setAutoDisable(boolean autoDisable) {
        return self();
    }

    public final boolean isDisplayInWild() {
        return !alwaysAllowInWilderness;
    }

    public final boolean isAlwaysAllowInWild() {
        return alwaysAllowInWilderness;
    }

    public boolean getWarState() {
        return warState;
    }

    @NotNull
    public T setWarState(boolean state) {
        this.warState = state;
        return self();
    }

    @NotNull
    public abstract String getTogglePerm();

    public enum Target {
        PLAYER, ADMIN, SYSTEM
    }

    public enum Module {
        LAND, WAR, NATION
    }


    @Override
    public @NotNull T setAlwaysAllowInWilderness(boolean allow) {
        this.alwaysAllowInWilderness = allow;
        return self();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof me.angeschossen.lands.api.flags.type.parent.Flag<?>)) {
            return false;
        }

        return ((me.angeschossen.lands.api.flags.type.parent.Flag<?>) obj).getName().equals(this.getName());
    }

    @Override
    public @NotNull T setApplyInSubareas(boolean set) {
        this.applyInSubAreas = set;
        return self();
    }

    @Override
    @NotNull
    public final Plugin getPlugin() {
        return plugin;
    }

    @Override
    @NotNull
    public final ItemStack getIcon() {
        return icon;
    }

    @Override
    @NotNull
    public T setIcon(@Nullable ItemStack icon) {
        this.icon = icon == null ? new ItemStack(Material.NAME_TAG) : icon.clone();
        ItemMeta iconMeta = this.icon.getItemMeta();
        iconMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        this.icon.setItemMeta(iconMeta);
        return self();
    }

    @Override
    public final boolean isDisplayInWilderness() {
        return !alwaysAllowInWilderness;
    }

    @Override
    public final boolean isAlwaysAllowInWilderness() {
        return alwaysAllowInWilderness;
    }

    @Override
    public final boolean isApplyInSubareas() {
        return applyInSubAreas;
    }

    @Override
    @Nullable
    public final List<String> getDescription() {
        return description;
    }

    @Override
    @NotNull
    public T setDescription(@Nullable List<String> description) {
        this.description = description;
        return self();
    }

    @Override
    @NotNull
    public T setDescription(@Nullable String description) {
        if (description == null) {
            this.description = null;

        } else {
            String lc = ChatColor.getLastColors(description);
            this.description = makeLore(lc.isEmpty() ? ChatColor.GRAY + description : description, 40);
        }

        return self();
    }

    protected static List<String> makeLore(String input, int maxLength) {
        List<String> lore = new ArrayList<>();
        int current = -1;
        String lastColor = "";
        String[] var5 = input.split(" ");
        int var6 = var5.length;

        for (int var7 = 0; var7 < var6; ++var7) {
            String line = var5[var7];
            String startWhiteSpaces = "";
            char[] var10 = line.toCharArray();
            int var11 = var10.length;

            for (int var12 = 0; var12 < var11; ++var12) {
                char a = var10[var12];
                if (!Character.isWhitespace(a)) {
                    break;
                }

                startWhiteSpaces = startWhiteSpaces + " ";
            }

            line = line.trim();
            if (!lore.isEmpty() && ((String) lore.get(current)).length() + line.length() <= maxLength) {
                lore.set(current, (String) lore.get(current) + " " + line);
            } else {
                lore.add(lastColor + startWhiteSpaces + line);
                ++current;
            }

            String temp = ChatColor.getLastColors(line);
            if (!temp.isEmpty()) {
                lastColor = temp;
            }
        }

        return lore;
    }

    @Override
    public boolean isDisplay() {
        return display;
    }

    @Override
    @NotNull
    public T setDisplay(boolean display) {
        if (getTarget() != FlagTarget.SYSTEM) {
            this.display = display;
        }

        return self();
    }

    @Override
    @NotNull
    public String getDisplayName() {
        return displayName == null ? name : displayName;
    }

    @Override
    @NotNull
    public T setDisplayName(@Nullable String displayName) {
        this.displayName = displayName;
        return self();
    }

    @Override
    @NotNull
    public final String getName() {
        return name;
    }

    @Override
    public boolean isActiveInWar() {
        return warState;
    }

    @Override
    @NotNull
    public T setActiveInWar(boolean state) {
        this.warState = state;
        return self();
    }

    @Override
    @NotNull
    public abstract String getTogglePermission();

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    @NotNull
    public abstract FlagModule getModule();

    @Override
    public final @NotNull FlagTarget getTarget() {
        return target;
    }
}
