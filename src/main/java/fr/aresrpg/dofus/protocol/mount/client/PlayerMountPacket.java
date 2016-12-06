package fr.aresrpg.dofus.protocol.mount.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.io.IOException;

/**
 * 
 * @since
 */
public class PlayerMountPacket implements Packet {

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

}
