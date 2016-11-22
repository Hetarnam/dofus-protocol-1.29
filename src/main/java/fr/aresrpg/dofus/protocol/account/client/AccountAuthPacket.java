package fr.aresrpg.dofus.protocol.account.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class AccountAuthPacket implements Packet{

	private String version;
	private String pseudo;
	private String hashedPassword;

	public AccountAuthPacket() {}

	@Override
	public void read(DofusStream stream) {
		String data[] = stream.read().split("\n");
		version = data[0];
		pseudo = data[1];
		hashedPassword = data[2];
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocatePacket(2)
				.allocate(1).write(version)
				.nextPacket()
				.allocate(1).write(pseudo + '\n' + hashedPassword + '\n');
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}


	public String getHashedPassword() {
		return hashedPassword;
	}

	public String getPseudo() {
		return pseudo;
	}

	public String getVersion() {
		return version;
	}

	public AccountAuthPacket setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
		return this;
	}

	public AccountAuthPacket setPseudo(String pseudo) {
		this.pseudo = pseudo;
		return this;
	}

	public AccountAuthPacket setVersion(String version) {
		this.version = version;
		return this;
	}
}
