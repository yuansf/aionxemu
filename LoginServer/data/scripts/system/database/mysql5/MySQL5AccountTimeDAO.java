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
package mysql5;

import com.aionemu.commons.database.DB;
import com.aionemu.commons.database.IUStH;
import loginserver.dao.AccountTimeDAO;
import loginserver.model.AccountTime;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MySQL5 AccountTimeDAO implementation
 *
 * @author EvilSpirit
 */
public class MySQL5AccountTimeDAO extends AccountTimeDAO {
    /**
     * Logger
     */
    private static final Logger log = Logger.getLogger(MySQL5AccountTimeDAO.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateAccountTime(final int accountId, final AccountTime accountTime) {
        return DB.insertUpdate("REPLACE INTO account_time (account_id, last_active, expiration_time, " +
                "session_duration, accumulated_online, accumulated_rest, penalty_end) values " +
                "(?,?,?,?,?,?,?)",
                new IUStH() {
                    @Override
                    public void handleInsertUpdate(PreparedStatement preparedStatement) throws SQLException {
                        preparedStatement.setLong(1, accountId);
                        preparedStatement.setTimestamp(2, accountTime.getLastLoginTime());
                        preparedStatement.setTimestamp(3, accountTime.getExpirationTime());
                        preparedStatement.setLong(4, accountTime.getSessionDuration());
                        preparedStatement.setLong(5, accountTime.getAccumulatedOnlineTime());
                        preparedStatement.setLong(6, accountTime.getAccumulatedRestTime());
                        preparedStatement.setTimestamp(7, accountTime.getPenaltyEnd());
                        preparedStatement.execute();
                    }
                });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AccountTime getAccountTime(int accountId) {
        AccountTime accountTime = null;
        PreparedStatement st = DB.prepareStatement("SELECT * FROM account_time WHERE account_id = ?");

        try {
            st.setLong(1, accountId);

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                accountTime = new AccountTime();

                accountTime.setLastLoginTime(rs.getTimestamp("last_active"));
                accountTime.setSessionDuration(rs.getLong("session_duration"));
                accountTime.setAccumulatedOnlineTime(rs.getLong("accumulated_online"));
                accountTime.setAccumulatedRestTime(rs.getLong("accumulated_rest"));
                accountTime.setPenaltyEnd(rs.getTimestamp("penalty_end"));
                accountTime.setExpirationTime(rs.getTimestamp("expiration_time"));
            }
        }
        catch (Exception e) {
            log.error("Can't get account time for account with id: " + accountId, e);
        }
        finally {
            DB.close(st);
        }

        return accountTime;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(String database, int majorVersion, int minorVersion) {
        return MySQL5DAOUtils.supports(database, majorVersion, minorVersion);
    }
}
