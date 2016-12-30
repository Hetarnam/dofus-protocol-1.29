package fr.aresrpg.dofus.protocol.game.actions.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;

/**
 * 
 * @since
 */
public class GameJoinFightAction implements GameAction {

	private int fightId;

	@Override
	public void read(DofusStream stream) {
		this.fightId = Integer.parseInt(stream.read().split(";")[0]);
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(fightId + ";" + fightId);
	}

	/**
	 * @return the fightId
	 */
	public int getFightId() {
		return fightId;
	}

	/**
	 * @param fightId
	 *            the fightId to set
	 */
	public void setFightId(int fightId) {
		this.fightId = fightId;
	}

	@Override
	public String toString() {
		return "GameJoinFightAction [fightId=" + fightId + "]";
	}

}