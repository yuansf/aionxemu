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

package quest.beluslan;

import java.util.Collections;

import gameserver.model.gameobjects.Npc;
import gameserver.model.gameobjects.player.Player;
import gameserver.model.templates.quest.QuestItems;
import gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import gameserver.questEngine.handlers.QuestHandler;
import gameserver.questEngine.model.QuestCookie;
import gameserver.questEngine.model.QuestState;
import gameserver.questEngine.model.QuestStatus;
import gameserver.services.ItemService;
import gameserver.utils.PacketSendUtility;

/**
 * @author Leunam
 *
 */
public class _2564WiththePowerofFlame extends QuestHandler {
	private final static int	questId	= 2564;
	private final static int[] npc_ids = {204753, 204821, 204822, 204823};
	
	public _2564WiththePowerofFlame() {
        super(questId);
    }
	
	@Override
	public void register() {
	qe.setNpcQuestData(204753).addOnQuestStart(questId);
	for(int npc_id : npc_ids)
		qe.setNpcQuestData(npc_id).addOnTalkEvent(questId);	
	}

	@Override
	public boolean onDialogEvent(QuestCookie env) {
	final Player player = env.getPlayer();
	int targetId = 0;
	if (env.getVisibleObject() instanceof Npc)
	targetId = ((Npc) env.getVisibleObject()).getNpcId();
	QuestState qs = player.getQuestStateList().getQuestState(questId);
	if(targetId == 204753)
	{
		if(qs == null || qs.getStatus() == QuestStatus.NONE)
		{	
			if(env.getDialogId() == 25)
				return sendQuestDialog(env, 4762);
			else if(env.getDialogId() == 1002)
			{
				if (ItemService.addItems(player, Collections.singletonList(new QuestItems(182204444, 1))))
					return defaultQuestStartDialog(env);
				else
					return true;
			}
			else
				return defaultQuestStartDialog(env);
		}
	}
	if(qs == null)
	return false;
		
	int var = qs.getQuestVarById(0);
	if(qs.getStatus() == QuestStatus.REWARD)
	{
		if(targetId == 204753)
		{
			if (env.getDialogId() == -1)
				return sendQuestDialog(env, 10002);
			else if (env.getDialogId() == 1009)
				return sendQuestDialog(env, 5);
			else return defaultQuestEndDialog(env);
		}
	}
	else if(qs.getStatus() != QuestStatus.START)
	{
		return false;
	}
	if(targetId == 204821)
	{
		switch(env.getDialogId())
		{
			case 25:
				if(var == 0)
					return sendQuestDialog(env, 1011);
			case 10000:
				if(var == 0)
				{
					qs.setQuestVarById(0, var + 1);
					updateQuestStatus(env);								
					PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
					return true;
				}
			return false;
		}
	}			
	else if(targetId == 204822)
	{
		switch(env.getDialogId())
		{
			case 25:
				if(var == 1)
					return sendQuestDialog(env, 1352);
			case 10001:
				if(var == 1)
				{
					qs.setQuestVarById(0, var + 1);
					updateQuestStatus(env);								
					PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
					return true;
				}
			return false;
		}
	}			
	else if(targetId == 204823)
	{
		switch(env.getDialogId())
		{
			case 25:
				if(var == 2)
					return sendQuestDialog(env, 1693);
			case 10002:
				if(var == 2)
				{
					qs.setQuestVarById(0, var + 1);
					qs.setStatus(QuestStatus.REWARD);
					updateQuestStatus(env);								
					PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
					return true;
				}
			return false;
		}
	}			
	return false;
   }
}
