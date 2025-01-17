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
package gameserver.skillengine.condition;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ATracer
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Conditions", propOrder = {
        "conditions"
})
public class Conditions {
    @XmlElements({
            @XmlElement(name = "target", type = TargetCondition.class),
            @XmlElement(name = "mp", type = MpCondition.class),
            @XmlElement(name = "hp", type = HpCondition.class),
            @XmlElement(name = "dp", type = DpCondition.class),
            @XmlElement(name = "playermove", type = PlayerMovedCondition.class),
            @XmlElement(name = "arrowcheck", type = ArrowCheckCondition.class)
    })
    protected List<Condition> conditions;

    /**
     * Gets the value of the conditions property.
     * <p/>
     * <p/>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the conditions property.
     * <p/>
     * <p/>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConditions().add(newItem);
     * </pre>
     */
    public List<Condition> getConditions() {
        if (conditions == null) {
            conditions = new ArrayList<Condition>();
        }
        return this.conditions;
    }

}
