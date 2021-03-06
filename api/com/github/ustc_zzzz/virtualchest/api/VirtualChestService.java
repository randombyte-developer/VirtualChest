package com.github.ustc_zzzz.virtualchest.api;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.ServiceManager;
import org.spongepowered.api.util.annotation.NonnullByDefault;

import java.util.Optional;
import java.util.Set;

/**
 * Representation of a service provider for the chest GUIs. The implementation can be retrieved by
 * {@link ServiceManager#provideUnchecked}. The implementation may not be thread safe.
 * <p>
 * The interface does not handle chest GUI registration. Please firstly register an event listener
 * for {@link VirtualChest.LoadEvent} and while the event is fired, register all the chest GUIs by
 * invoking {@link VirtualChest.LoadEvent#register} method.
 *
 * @author ustc_zzzz
 * @see ServiceManager
 * @see VirtualChest.LoadEvent
 */
@NonnullByDefault
public interface VirtualChestService
{
    /**
     * Returns all of the ids whose corresponding chest GUI is available.
     * <p>
     * The set itself is unmodifiable, while the values in this set may be changed dynamically. A
     * typical implementation is to wrap with {@link java.util.Collections#unmodifiableSet}. It is
     * promised that the set would not be changed until a {@link VirtualChest.LoadEvent} is fired.
     *
     * @return An unmodifiable set for ids.
     */
    Set<String> ids();

    /**
     * Returns the id of the chest GUI marked as opened if available.
     *
     * @param player the player
     * @return the id or {@link Optional#empty}
     */
    Optional<String> lookup(Player player);

    /**
     * Open the chest GUI for player.
     *
     * @param identifier the id for the chest GUI to be opened
     * @param player     the player
     * @return true if the chest GUI is available and finally marked to be opened, false otherwise
     */
    boolean open(String identifier, Player player);

    /**
     * Close the chest GUI for player.
     *
     * @param identifier the id for the chest GUI to be opened
     * @param player     the player
     * @return true if the chest GUI is available and finally mark to be closed, false otherwise
     */
    boolean close(String identifier, Player player);
}
