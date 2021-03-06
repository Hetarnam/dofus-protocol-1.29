/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.ClientPacket;
import fr.aresrpg.dofus.protocol.ClientPacketHandler;
import fr.aresrpg.dofus.protocol.DofusStream;

public class GameClientReadyPacket implements ClientPacket {
	private boolean ready;

	@Override
	public void read(DofusStream stream) {
		ready = stream.read().equals("1");
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(ready ? "1" : "2");
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	public boolean isReady() {
		return ready;
	}

	public GameClientReadyPacket setReady(boolean ready) {
		this.ready = ready;
		return this;
	}

	@Override
	public String toString() {
		return "GameClientReadyPacket(ready=" + ready +
				")[" + getId() + ']';
	}
}
