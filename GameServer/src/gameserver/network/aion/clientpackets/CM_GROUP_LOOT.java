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
package gameserver.network.aion.clientpackets;

import gameserver.model.gameobjects.player.Player;
import gameserver.network.aion.AionClientPacket;
import gameserver.services.DropService;

/**
 * @author Rhys2002
 */
public class CM_GROUP_LOOT extends AionClientPacket {
    private int groupId;
    @SuppressWarnings("unused")
    private int unk1;
    @SuppressWarnings("unused")
    private int unk2;
    private int itemId;
    private int itemIndex;
    private int npcId;
    private int distributionId;
    private int roll;
    private long bid;

    public CM_GROUP_LOOT(int opcode) {
        super(opcode);
    }

    @Override
    protected void readImpl() {
        groupId = readD();
        unk1 = readD();
        unk2 = readD();
        itemId = readD();
        itemIndex = readC();
        npcId = readD();
        distributionId = readC();// 2: Roll 3: Bid
        roll = readD();// 0: Never Rolled 1: Rolled
        bid = readQ();// 0: No Bid else bid amount
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void runImpl() {
        Player player = getConnection().getActivePlayer();
        if (player == null)
            return;

        switch (distributionId) {
            case 2:
                DropService.getInstance().handleRoll(player, groupId, roll, itemId, itemIndex, npcId);
                break;
            case 3:
                DropService.getInstance().handleBid(player, groupId, bid, itemId, itemIndex, npcId);
                break;
        }
    }
}
