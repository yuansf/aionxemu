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

package gameserver.questEngine.handlers.models.xmlQuest.conditions;

import gameserver.model.gameobjects.Npc;
import gameserver.model.gameobjects.VisibleObject;
import gameserver.questEngine.model.QuestCookie;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Mr. Poke
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NpcIdCondition")
public class NpcIdCondition extends QuestCondition {

    @XmlAttribute(required = true)
    protected int values;

    /*
      * (non-Javadoc)
      * @see
      * com.aionemu.gameserver.questEngine.handlers.template.xmlQuest.condition.QuestCondition#doCheck(com.aionemu.gameserver
      * .questEngine.model.QuestEnv)
      */

    @Override
    public boolean doCheck(QuestCookie env) {
        int id = 0;
        VisibleObject visibleObject = env.getVisibleObject();
        if (visibleObject != null && visibleObject instanceof Npc) {
            id = ((Npc) visibleObject).getNpcId();
        }
        switch (getOp()) {
            case EQUAL:
                return id == values;
            case GREATER:
                return id > values;
            case GREATER_EQUAL:
                return id >= values;
            case LESSER:
                return id < values;
            case LESSER_EQUAL:
                return id <= values;
            case NOT_EQUAL:
                return id != values;
            default:
                return false;
        }
    }
}
