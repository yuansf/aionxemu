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
package gameserver.restrictions;

import gameserver.model.gameobjects.VisibleObject;
import gameserver.model.gameobjects.player.Player;
import gameserver.skillengine.model.Skill;

/**
 * @author lord_rex
 */
public abstract class AbstractRestrictions implements Restrictions {
    public void activate() {
        RestrictionsManager.activate(this);
    }

    public void deactivate() {
        RestrictionsManager.deactivate(this);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    /**
     * To avoid accidentally multiple times activated restrictions.
     */
    @Override
    public boolean equals(Object obj) {
        return getClass().equals(obj.getClass());
    }

    @DisabledRestriction
    public boolean isRestricted(Player player, Class<? extends Restrictions> callingRestriction) {
        throw new AbstractMethodError();
    }

    @DisabledRestriction
    public boolean canAttack(Player player, VisibleObject target) {
        throw new AbstractMethodError();
    }

    @DisabledRestriction
    public boolean canAffectBySkill(Player player, VisibleObject target) {
        throw new AbstractMethodError();
    }

    @DisabledRestriction
    public boolean canUseSkill(Player player, Skill skill) {
        throw new AbstractMethodError();
    }

    @DisabledRestriction
    public boolean canChat(Player player) {
        throw new AbstractMethodError();
    }

    @DisabledRestriction
    public boolean canInviteToGroup(Player player, Player target) {
        throw new AbstractMethodError();
    }

    @DisabledRestriction
    public boolean canChangeEquip(Player player) {
        throw new AbstractMethodError();
    }

    @DisabledRestriction
    public boolean canUseWarehouse(Player player) {
        throw new AbstractMethodError();
    }

    @DisabledRestriction
    public boolean canTrade(Player player) {
        throw new AbstractMethodError();
    }

    @DisabledRestriction
    public boolean canUseItem(Player player) {
        throw new AbstractMethodError();
    }

}
