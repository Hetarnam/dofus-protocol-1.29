/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;

/**
 * 
 * @since
 */
public class GameOnReadyPacket implements ServerPacket {

	private boolean ready;
	private String playerid;

	@Override
	public String toString() {
		return "GameOnReadyPacket(ready:" + ready + "|playerid:" + playerid + ")[" + getId() + "]";
	}

	@Override
	public void read(DofusStream stream) {
		String val = stream.read();
		this.ready = val.charAt(0) == '1';
		this.playerid = val.substring(1);
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write((ready ? "1" : "0") + playerid);
	}

	/**
	 * @return the ready
	 */
	public boolean isReady() {
		return ready;
	}

	/**
	 * @return the playerid
	 */
	public String getPlayerid() {
		return playerid;
	}

	/**
	 * @param ready
	 *            the ready to set
	 */
	public void setReady(boolean ready) {
		this.ready = ready;
	}

	/**
	 * @param playerid
	 *            the playerid to set
	 */
	public void setPlayerid(String playerid) {
		this.playerid = playerid;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}


}
