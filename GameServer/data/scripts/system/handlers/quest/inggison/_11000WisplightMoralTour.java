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
package quest.inggison;

import gameserver.model.gameobjects.Npc;
import gameserver.model.gameobjects.player.Player;
import gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import gameserver.questEngine.handlers.QuestHandler;
import gameserver.questEngine.model.QuestCookie;
import gameserver.questEngine.model.QuestState;
import gameserver.questEngine.model.QuestStatus;
import gameserver.utils.PacketSendUtility;

/**
 * @author dta3000
 */
 
public class _11000WisplightMoralTour extends QuestHandler
{
	private final static int	questId	= 11000;
	
	public _11000WisplightMoralTour()
	{
		super(questId);
	}

	@Override
	public void register()
                 {
                qe.setNpcQuestData(798927).addOnQuestStart(questId);
                qe.setNpcQuestData(798927).addOnTalkEvent(questId);
                qe.setNpcQuestData(798929).addOnTalkEvent(questId);
                qe.setNpcQuestData(798934).addOnTalkEvent(questId);
                qe.setNpcQuestData(798933).addOnTalkEvent(questId);
	}

	@Override
	public boolean onDialogEvent(QuestCookie env)
	{
		final Player player = env.getPlayer();
		QuestState qs = player.getQuestStateList().getQuestState(questId);
		
		int targetId = 0;
		if(env.getVisibleObject() instanceof Npc)
			targetId = ((Npc) env.getVisibleObject()).getNpcId();
		
		if(qs == null || qs.getStatus() == QuestStatus.NONE) 
		{
			if(targetId == 798927)
			{
				if(env.getDialogId() == 25)
				{
					return sendQuestDialog(env, 1011);
				}
				else
					return defaultQuestStartDialog(env);
			}
		}
		
		if(qs == null)
			return false;
			
		if(qs.getStatus() == QuestStatus.START)
		{
			switch(targetId)
			{ 
                                case 798929:
				{
					switch(env.getDialogId())
					{
						case 25:
						{
							return sendQuestDialog(env, 1352);
						}
						case 10000:
						{
							qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
							 updateQuestStatus(env); 
							PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
							return true;
						}
					}
				}
                                 case 798934:
				{
					switch(env.getDialogId())
					{
						case 25:
						{
							return sendQuestDialog(env, 1693);
						}
						case 10001:
						{
							qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
							 updateQuestStatus(env); 
							PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
							return true;
						}
					}
				}
                                case 798933:
				{
					switch(env.getDialogId())
					{
						case 25:
						{
							return sendQuestDialog(env, 2034);
						}
						case 10002:
						{
							qs.setQuestVarById(0, qs.getQuestVarById(0) + 1);
							updateQuestStatus(env);
							PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
							return true;
						}
					}
				}
				case 798927:
				{
					switch(env.getDialogId())
					{
						case 25:
						{
							return sendQuestDialog(env, 2375);
						}
						case 1009:
						{
							qs.setQuestVar(4);
							qs.setStatus(QuestStatus.REWARD);
							updateQuestStatus(env);
							return defaultQuestEndDialog(env);
						}
						default: return defaultQuestEndDialog(env);
					}
				}
			}
		}
		else if (qs.getStatus() == QuestStatus.REWARD)
		{
			if (targetId == 798927)
			{
				switch(env.getDialogId())
				{
					case 1009:
					{
						return sendQuestDialog(env, 5);
					}
					default : return defaultQuestEndDialog(env);
				}
			}
		}
		return false;
	}
}