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

import gameserver.model.templates.itemset.ItemPart;
import gameserver.model.templates.itemset.ItemSetTemplate;
import gnu.trove.TIntObjectHashMap;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author ATracer
 */
@XmlRootElement(name = "item_sets")
@XmlAccessorType(XmlAccessType.FIELD)
public class ItemSetData {
    @XmlElement(name = "itemset")
    protected List<ItemSetTemplate> itemsetList;

    private TIntObjectHashMap<ItemSetTemplate> sets;

    // key: item id, value: associated item set template
    // This should provide faster search of the item template set by item id
    private TIntObjectHashMap<ItemSetTemplate> setItems;

    void afterUnmarshal(Unmarshaller u, Object parent) {
        sets = new TIntObjectHashMap<ItemSetTemplate>();
        setItems = new TIntObjectHashMap<ItemSetTemplate>();

        for (ItemSetTemplate set : itemsetList) {
            sets.put(set.getId(), set);

            // Add reference to the ItemSetTemplate from
            for (ItemPart part : set.getItempart()) {
                setItems.put(part.getItemid(), set);
            }
        }
        itemsetList = null;
    }

    /**
     * @param itemSetId
     * @return
     */
    public ItemSetTemplate getItemSetTemplate(int itemSetId) {
        return sets.get(itemSetId);
    }

    /**
     * @param itemId
     * @return
     */
    public ItemSetTemplate getItemSetTemplateByItemId(int itemId) {
        return setItems.get(itemId);
    }

    /**
     * @return itemSets.size()
     */
    public int size() {
        return sets.size();
    }
}
