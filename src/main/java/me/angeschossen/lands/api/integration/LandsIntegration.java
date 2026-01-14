package me.angeschossen.lands.api.integration;


import com.github.angeschossen.applicationframework.api.util.ULID;
import me.angeschossen.lands.api.configuration.Configuration;
import me.angeschossen.lands.api.exceptions.FlagConflictException;
import me.angeschossen.lands.api.flags.Flag;
import me.angeschossen.lands.api.flags.FlagRegistry;
import me.angeschossen.lands.api.handler.APIHandler;
import me.angeschossen.lands.api.inbox.InboxMessageProvider;
import me.angeschossen.lands.api.land.Area;
import me.angeschossen.lands.api.land.Land;
import me.angeschossen.lands.api.land.LandArea;
import me.angeschossen.lands.api.land.LandWorld;
import me.angeschossen.lands.api.legacy.LandsIntegrator;
import me.angeschossen.lands.api.levels.LevelsHandler;
import me.angeschossen.lands.api.memberholder.MemberHolder;
import me.angeschossen.lands.api.nation.Nation;
import me.angeschossen.lands.api.player.LandPlayer;
import me.angeschossen.lands.api.player.OfflinePlayer;
import me.angeschossen.lands.api.player.PlayerCooldown;
import me.angeschossen.lands.api.sorting.SortingContext;
import me.angeschossen.lands.api.war.storage.WarHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Use {@link me.angeschossen.lands.api.LandsIntegration} instead.
 */
@Deprecated
public class LandsIntegration implements LandsIntegrator, me.angeschossen.lands.api.LandsIntegration {

    private final Plugin plugin;

    @Deprecated
    public LandsIntegration(@NotNull String name, boolean isPublic) {
        this(Bukkit.getPluginManager().getPlugin(name));
    }

    @Deprecated
    public LandsIntegration(@NotNull Plugin plugin, boolean isPublic) {
        this(plugin);
    }

    public LandsIntegration(@NotNull Plugin plugin) {
        Objects.requireNonNull(plugin, "Plugin parameter can't be null");

        if (plugin.equals(APIHandler.getInstance().getPlugin())) {
            throw new IllegalArgumentException("The provided plugin parameter must be a instance of your plugin on not the Lands plugin.");
        }

        this.plugin = plugin;

        this.onLoad(() -> {
            try {
                APIHandler.getLandsIntegrationFactory().of(plugin);
            } catch (NullPointerException e) {
                throw new IllegalStateException("[Lands] Lands isn't enabled yet. Plugin " + getName() + " needs to be enabled after Lands.", e);
            }

            Bukkit.getLogger().info("[Lands] Nag author(s) of plugin " + plugin.getName() + ". It uses the deprecated LandsIntegration of Lands: https://wiki.incredibleplugins.com/lands/developers/update");
        });
    }

    @Override
    public void setWarHandler(@Nullable WarHandler warHandler) {

    }

    @Override
    public @Nullable InboxMessageProvider getInboxMessageProvider() {
        return null;
    }

    @Override
    public void setInboxMessageProvider(@Nullable InboxMessageProvider inboxMessageProvider) {

    }

    @Override
    public @NotNull Configuration getConfiguration() {
        return null;
    }

    @Override
    public boolean canPvP(@NotNull Player attacker, @NotNull Player target, @NotNull Location location, boolean setCombatTag, boolean sendMessage) {
        return APIHandler.getInstance().getLegacySupport().canPvP(attacker, target, location, setCombatTag, sendMessage);
    }

    @Override
    public void disable() {

    }

    @Override
    @Deprecated
    public void disable(@Nullable String hookKey) {
        disable();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LandsIntegration)) {
            return false;
        }

        Plugin s = ((LandsIntegration) obj).plugin;
        Plugin d = this.getPlugin();

        if (s == null || d == null) {
            return ((LandsIntegration) obj).getName().equals(this.getName());
        }

        return s.equals(d);
    }

    @Override
    @Deprecated
    public boolean getAccess(@NotNull String hookKey) {
        return false;
    }

    @Override
    @Nullable
    @Deprecated
    public LandArea getArea(@NotNull Location location) {
        Area area = getAreaByLoc(location);
        return area instanceof LandArea ? (LandArea) area : null;
    }

    @Override
    public @Nullable Area getUnloadedArea(@NotNull Location location) {
        return getArea(location);
    }

    @Override
    @Nullable
    public Area getArea(@NotNull World world, int x, int y, int z) {
        return APIHandler.getInstance().getLegacySupport().getArea(new Location(world, x, y, z));
    }

    @Override
    @Nullable
    public Area getAreaByLoc(@NotNull Location location) {
        return APIHandler.getInstance().getLegacySupport().getArea(location);
    }

    @NotNull
    @Override
    public FlagRegistry getFlagRegistry() {
        return APIHandler.getInstance().getLegacySupport().getFlagRegistry();
    }

    @Override
    public @Nullable Land getLandByChunk(@NotNull World world, int chunkX, int chunkZ) {
        return getLand(world, chunkX, chunkZ);
    }

    @Override
    public @Nullable Land getLandByUnloadedChunk(@NotNull World world, int chunkX, int chunkZ) {
        return getLandUnloaded(world, chunkX, chunkZ);
    }

    @Override
    @Deprecated
    public Land getLand(@NotNull String worldName, @NotNull String name) {
        return getLandByName(name);
    }

    @Override
    public Land getLand(int id) {
        return getLandById(id);
    }

    @Override
    public Land getLand(@NotNull String name) {
        return getLandByName(name);
    }

    @Override
    public Land getLandById(int id) {
        return APIHandler.getInstance().getLegacySupport().getLandById(id);
    }

    @Override
    public @Nullable Land getLandByULID(@NotNull ULID ulid) {
        return null;
    }

    @Override
    public @Nullable MemberHolder getMemberholderByULID(@NotNull ULID ulid) {
        return null;
    }

    @Override
    @Nullable
    public Land getLandByName(@NotNull String name) {
        return APIHandler.getInstance().getLegacySupport().getLandByName(name);
    }

    @Deprecated
    @Override
    public Land getLand(@NotNull Location location) {
        Area area = getAreaByLoc(location);
        return area == null ? null : area.getLand();
    }

    @Override
    public @Nullable Land getLand(@NotNull World world, int chunkX, int chunkZ) {
        Area area = APIHandler.getInstance().getLegacySupport().getArea(new Location(world, chunkX << 4, 70, chunkZ << 4));
        return area == null ? null : area.getLand();
    }

    @Override
    public LandPlayer getLandPlayer(@NotNull UUID playerUUID) {
        return APIHandler.getInstance().getLegacySupport().getLandPlayer(playerUUID);
    }

    @Override
    @Nullable
    public Land getLandUnloaded(@NotNull World world, int x, int z) {
        Area area = APIHandler.getInstance().getLegacySupport().getArea(new Location(world, x << 4, 70, z << 4));
        return area == null ? null : area.getLand();
    }

    @Override
    public @Nullable LandWorld getLandWorld(@NotNull World world) {
        return getWorld(world);
    }

    @Override
    @Deprecated
    public LandWorld getLandWorld(@NotNull String worldName) {
        World w = Bukkit.getWorld(worldName);
        return w != null ? APIHandler.getInstance().getLegacySupport().getWorld(w) : null;
    }

    @Override
    public LandWorld getWorld(@NotNull World world) {
        return APIHandler.getInstance().getLegacySupport().getWorld(world);
    }

    @Override
    @NotNull
    public Collection<Land> getLands() {
        return APIHandler.getInstance().getLegacySupport().getLands();
    }

    @Override
    @NotNull
    public LevelsHandler getLevelsHandler() {
        return APIHandler.getInstance().getLegacySupport().getLevelsHandler();
    }

    @Override
    public @Nullable Long getPlayerCooldown(@NotNull UUID playerUUID, @NotNull PlayerCooldown cooldown) {
        return null;
    }

    @Override
    @NotNull
    public String getName() {
        return plugin != null ? plugin.getName() : "undefined";
    }

    @Override
    public @Nullable Nation getNation(int id) {
        return getNationById(id);
    }

    @Override
    public @Nullable Nation getNation(@NotNull String name) {
        return getNationByName(name);
    }

    @Override
    @Nullable
    public Nation getNationById(int id) {
        return APIHandler.getInstance().getLegacySupport().getNationById(id);
    }

    @Override
    public @Nullable Nation getNationByULID(@NotNull ULID ulid) {
        return null;
    }

    @Nullable
    @Override
    public Nation getNationByName(@NotNull String name) {
        return APIHandler.getInstance().getLegacySupport().getNationByName(name);
    }

    @Override
    @NotNull
    public Collection<Nation> getNations() {
        return APIHandler.getInstance().getLegacySupport().getNations();
    }

    @Override
    @NotNull
    public CompletableFuture<OfflinePlayer> getOfflineLandPlayer(@NotNull UUID playerUID) {
        return APIHandler.getInstance().getLegacySupport().getOfflineLandPlayer(playerUID);
    }

    @Override
    @NotNull
    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    @Nullable
    public SortingContext<?> getSortingContext(@NotNull String id) {
        return APIHandler.getInstance().getLegacySupport().getSortingContext(id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @NotNull
    @Override
    @Deprecated
    public String initialize() {
        return "";
    }

    @Override
    @Deprecated
    public boolean isClaimed(@NotNull Location location) {
        return isChunkClaimed(location.getWorld(), location.getBlockX() >> 4, location.getBlockZ() >> 4);
    }

    @Override
    public boolean isChunkClaimed(@NotNull World world, int x, int z) {
        return APIHandler.getInstance().getLegacySupport().getLandByChunk(world, x, z) != null;
    }

    @Override
    @Deprecated
    public CompletableFuture<Boolean> isClaimed(@NotNull String worldName, int x, int z) {
        World world = Bukkit.getWorld(worldName);
        if (world != null) {
            return CompletableFuture.completedFuture(isChunkClaimed(world, x, z));
        }

        return CompletableFuture.completedFuture(false);
    }

    @Override
    public boolean isClaimed(@NotNull World world, int chunkX, int chunkZ) {
        return isChunkClaimed(world, chunkX, chunkZ);
    }

    @Override
    public boolean isClaimedUnloaded(@NotNull World world, int x, int z) {
        return isChunkClaimedUnloaded(world, x, z);
    }

    @Override
    public boolean isChunkClaimedUnloaded(@NotNull World world, int x, int z) {
        return APIHandler.getInstance().getLegacySupport().getLandByUnloadedChunk(world, x, z) != null;
    }

    @Override
    @Deprecated
    public boolean isEnabled() {
        return true;
    }

    @Override
    @Deprecated
    public boolean isPublic() {
        return false;
    }

    @Override
    public void onLoad(@NotNull Runnable r) {
        APIHandler.getInstance().getLegacySupport().onLoad(r);
    }

    @Override
    public void registerFlag(@NotNull Flag flag) throws FlagConflictException, IllegalArgumentException {

    }

    @Override
    public void wild(@NotNull LandPlayer landPlayer, @NotNull World world) {
        randomTeleport(landPlayer, world);
    }

    @Override
    public @NotNull CompletableFuture<Location> randomTeleport(@NotNull LandPlayer landPlayer, @NotNull World world) {
        APIHandler.getInstance().getLegacySupport().randomTeleport(landPlayer, world);
        return null;
    }
}
