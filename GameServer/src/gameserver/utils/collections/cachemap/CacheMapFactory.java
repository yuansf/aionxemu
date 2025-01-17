/**
 * This file is part of Aion X Emu <aionxemu.com>
 *
 *  This is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This software is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser Public License
 *  along with this software.  If not, see <http://www.gnu.org/licenses/>.
 */
package gameserver.utils.collections.cachemap;

import gameserver.configs.main.CacheConfig;

/**
 * @author Luno
 */
public class CacheMapFactory {

    /**
     * Returns new instance of either {@link WeakCacheMap} or {@link SoftCacheMap} depending on
     * {@link CacheConfig#SOFT_CACHE_MAP} setting.
     *
     * @param <K>       - Type of keys
     * @param <V>       - Type of values
     * @param cacheName - The name for this cache map
     * @param valueName - Mnemonic name for values stored in the cache
     * @return CacheMap<K, V>
     */
    public static <K, V> CacheMap<K, V> createCacheMap(String cacheName, String valueName) {
        if (CacheConfig.SOFT_CACHE_MAP)
            return createSoftCacheMap(cacheName, valueName);
        else
            return createWeakCacheMap(cacheName, valueName);
    }

    /**
     * Creates and returns an instance of {@link SoftCacheMap}
     *
     * @param <K>       - Type of keys
     * @param <V>       - Type of values
     * @param cacheName - The name for this cache map
     * @param valueName - Mnemonic name for values stored in the cache
     * @return CacheMap<K, V>
     */
    public static <K, V> CacheMap<K, V> createSoftCacheMap(String cacheName, String valueName) {
        return new SoftCacheMap<K, V>(cacheName, valueName);
    }

    /**
     * Creates and returns an instance of {@link WeakCacheMap}
     *
     * @param <K>       - Type of keys
     * @param <V>       - Type of values
     * @param cacheName - The name for this cache map
     * @param valueName - Mnemonic name for values stored in the cache
     * @return CacheMap<K, V>
     */
    public static <K, V> CacheMap<K, V> createWeakCacheMap(String cacheName, String valueName)
	{
		return new WeakCacheMap<K,V>(cacheName, valueName);
	}
}
