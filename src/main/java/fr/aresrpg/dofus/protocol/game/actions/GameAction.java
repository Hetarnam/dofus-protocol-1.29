/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.DofusStream;

public interface GameAction {
	void read(DofusStream stream);

	void write(DofusStream stream);

	default int[] getId() {
		return GameActions.getAction(getClass()).getId();
	}
}
