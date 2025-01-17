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
package loginserver.network.aion.clientpackets;

import loginserver.network.aion.AionAuthResponse;
import loginserver.network.aion.AionClientPacket;
import loginserver.network.aion.AionConnection;
import loginserver.network.aion.AionConnection.State;
import loginserver.network.aion.serverpackets.SM_AUTH_GG;
import loginserver.network.aion.serverpackets.SM_LOGIN_FAIL;

import java.nio.ByteBuffer;

/**
 * @author -Nemesiss-
 */
public class CM_AUTH_GG extends AionClientPacket {
    /**
     * session id - its should match sessionId that was send in Init packet.
     */
    private int sessionId;

    /*
      * private final int data1; private final int data2; private final int data3; private final int data4;
      */

    /**
     * Constructs new instance of <tt>CM_AUTH_GG</tt> packet.
     *
     * @param buf
     * @param client
     */
    public CM_AUTH_GG(ByteBuffer buf, AionConnection client) {
        super(buf, client, 0x07);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void readImpl() {
        sessionId = readD();

        /*data1 = */
        readD();
        /*data2 = */
        readD();
        /*data3 = */
        readD();
        /*data4 = */
        readD();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void runImpl() {
        AionConnection con = getConnection();
        if (con.getSessionId() == sessionId) {
            con.setState(State.AUTHED_GG);
            con.sendPacket(new SM_AUTH_GG(sessionId));
        } else {
            /**
             * Session id is not ok - inform client that smth went wrong - dc client
             */
            con.close(new SM_LOGIN_FAIL(AionAuthResponse.SYSTEM_ERROR), true);
        }
    }
}
