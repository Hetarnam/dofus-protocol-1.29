/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.party.client;

import fr.aresrpg.dofus.protocol.*;

import java.util.Optional;

/**
 * 
 * @since
 */
public class PartyLeavePacket implements ClientPacket {

	private Optional<String> pseudo;

	/**
	 * @return the pseudo
	 */
	public Optional<String> getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo
	 *            the pseudo to set
	 */
	public void setPseudo(Optional<String> pseudo) {
		this.pseudo = pseudo;
	}

	@Override
	public void read(DofusStream stream) {
		this.pseudo = Optional.of(stream.available() < 1 || stream.peek().isEmpty() ? null : stream.read());
	}

	@Override
	public void write(DofusStream stream) {
		getPseudo().ifPresent(stream.allocate(1)::write);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "PartyLeavePacket [pseudo=" + pseudo + "]";
	}

}
