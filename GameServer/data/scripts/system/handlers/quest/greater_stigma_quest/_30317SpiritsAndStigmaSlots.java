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

package quest.greater_stigma_quest;

import gameserver.model.gameobjects.Npc;
import gameserver.model.gameobjects.player.Player;
import gameserver.model.templates.quest.QuestItems;
import gameserver.model.gameobjects.Item;
import gameserver.network.aion.serverpackets.SM_DIALOG_WINDOW;
import gameserver.questEngine.handlers.QuestHandler;
import gameserver.questEngine.model.QuestCookie;
import gameserver.questEngine.model.QuestState;
import gameserver.questEngine.model.QuestStatus;
import gameserver.services.ItemService;
import gameserver.services.QuestService;
import gameserver.utils.PacketSendUtility;

/**
 * @Author Khaos
 * 
 */

public class _30317SpiritsAndStigmaSlots extends QuestHandler
{
	private final static int questId = 30317;
	
    public _30317SpiritsAndStigmaSlots()
	{
		super (questId);
	}
	
	@Override
	public void register()
	{
		qe.setNpcQuestData(798208).addOnQuestStart(questId); //Garath start
		qe.setNpcQuestData(799322).addOnTalkEvent(questId); //Herka
		qe.setNpcQuestData(799506).addOnTalkEvent(questId); //Faithful Responded Ultra summoned
		qe.setNpcQuestData(798208).addOnTalkEvent(questId); //Garath finish
	}
	
	@Override
	public boolean onDialogEvent(final QuestCookie env)
	{
		//Instanceof
		final Player player = env.getPlayer();
		int targetId = 0;
		if (env.getVisibleObject() instanceof Npc)
            targetId = ((Npc) env.getVisibleObject()).getNpcId();
		final QuestState qs = player.getQuestStateList().getQuestState(questId);
		
		// ------------------------------------------------------------
        // NPC Quest :
		// Garath start
		if (qs == null || qs.getStatus() == QuestStatus.NONE)
		{
			if (targetId == 798208) // Garath Start
			{
				if (env.getDialogId() == 26)
					return sendQuestDialog(env, 4762);
				else
					return defaultQuestStartDialog(env);
			}
		}
		
		if (qs == null)
			return false;
		
		int var = qs.getQuestVarById(0);
		
		if (qs.getStatus() == QuestStatus.START)
		{
			switch (targetId)
			{
				// Herka
				case 799322:
					if (var == 0)
					{
						switch (env.getDialogId())
						{
							case 26:
								return sendQuestDialog(env, 1011);
							case 10000:
								qs.setQuestVar(1);
								updateQuestStatus(env);
								PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
								return true;
						}
					}
					// Faithful Utra summoned
				case 799506:
					if (var == 1)
					{
						switch (env.getDialogId())
						{
							case 26:
								return sendQuestDialog(env, 1352);
							case 10001:
								qs.setQuestVar(2);
								updateQuestStatus(env);
								PacketSendUtility.sendPacket(player, new SM_DIALOG_WINDOW(env.getVisibleObject().getObjectId(), 10));
								return true;
						}
					}
					// Report The Result To Garath.
				case 798208:
					if (var == 2)
					{
						switch (env.getDialogId())
						{
							case 26:
								return sendQuestDialog(env, 2375);
							case 33:
								if (player.getInventory().getItemCountByItemId(182209718) < 1)
								{
									return sendQuestDialog(env, 10001);
								}
								else if (player.getInventory().getItemCountByItemId(182209719) < 1)
								{
									return sendQuestDialog(env, 10001);
								}
								player.getInventory().removeFromBagByItemId(182209718, 1);
								player.getInventory().removeFromBagByItemId(182209719, 1);
								qs.setStatus(QuestStatus.REWARD);
								updateQuestStatus(env);
								return sendQuestDialog(env, 5);
						}
					}
				return false;
			}
		}
		return false;
	}
}