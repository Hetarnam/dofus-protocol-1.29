package fr.aresrpg.dofus.protocol.basic.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.io.IOException;

public class BasicConfirmPacket implements Packet {
	@Override
	public void read(DofusStream stream) throws IOException {

	}

	@Override
	public void write(DofusStream stream) throws IOException {

	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "BasicConfirmPacket()[" + getId() + "]";
	}
}
