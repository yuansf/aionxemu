/**
 * This file is part of alpha team <alpha-team.com>.
 *
 * alpha team is pryvate software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * alpha team is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with alpha team.  If not, see <http://www.gnu.org/licenses/>.
 */
package gameserver.dataholders;

import gameserver.model.templates.WorldMapTemplate;
import gnu.trove.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Iterator;
import java.util.List;

/**
 * Object of this class is containing <tt>WorldMapTemplate</tt> objects for all world maps. World maps are defined in
 * data/static_data/world_maps.xml file.
 *
 * @author Luno
 */
@XmlRootElement(name = "world_maps")
@XmlAccessorType(XmlAccessType.NONE)
public class WorldMapsData implements Iterable<WorldMapTemplate> {
    @XmlElement(name = "map")
    private List<WorldMapTemplate> worldMaps;

    private TIntObjectHashMap<WorldMapTemplate> worldIdMap = new TIntObjectHashMap<WorldMapTemplate>();

    void afterUnmarshal(Unmarshaller u, Object parent) {
        for (WorldMapTemplate map : worldMaps) {
            worldIdMap.put(map.getMapId(), map);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<WorldMapTemplate> iterator() {
        return worldMaps.iterator();
    }

    /**
     * Returns the count of maps.
     *
     * @return worldMaps.size()
     */
    public int size() {
        return worldMaps == null ? 0 : worldMaps.size();
    }

    /**
     * @param worldId
     * @return
     */
    public WorldMapTemplate getTemplate(int worldId) {
        return worldIdMap.get(worldId);
	}
}
