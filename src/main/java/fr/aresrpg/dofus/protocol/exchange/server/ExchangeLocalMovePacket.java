package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ExchangeLocalMovePacket implements ServerPacket {

	private int itemType;
	private int itemAmount;
	private int localKama;

	public ExchangeLocalMovePacket() {
	}

	/**
	 * @param itemType
	 * @param itemAmount
	 * @param localKama
	 */
	public ExchangeLocalMovePacket(int itemType, int itemAmount) {
		this.itemType = itemType;
		this.itemAmount = itemAmount;
		this.localKama = -1;
	}

	public ExchangeLocalMovePacket(int localKama) {
		this.itemType = -1;
		this.itemAmount = -1;
		this.localKama = localKama;
	}

	@Override
	public void read(DofusStream stream) {
		String data = stream.read().substring(1); // remove bSuccess
		char loc5 = data.charAt(0);
		if (loc5 == 'O') {
			data = data.substring(2);
			this.itemType = Integer.parseInt(data);
			this.itemAmount = stream.readInt();
			this.localKama = -1;
		} else if (loc5 == 'G')
			this.localKama = Integer.parseInt(data.substring(1));
		else throw new IllegalArgumentException("The char '" + loc5 + "' is invalid");
	}

	@Override
	public void write(DofusStream stream) {
		if (getLocalKama() == -1) stream.allocate(2).write("KO" + itemType).writeInt(itemAmount);
		else stream.allocate(1).write("KG" + getLocalKama());
	}

	/**
	 * @return the itemType
	 */
	public int getItemType() {
		return itemType;
	}

	/**
	 * @param itemType
	 *            the itemType to set
	 */
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	/**
	 * @return the itemAmount
	 */
	public int getItemAmount() {
		return itemAmount;
	}

	/**
	 * @param itemAmount
	 *            the itemAmount to set
	 */
	public void setItemAmount(int itemAmount) {
		this.itemAmount = itemAmount;
	}

	/**
	 * @return the localKama
	 */
	public int getLocalKama() {
		return localKama;
	}

	/**
	 * @param localKama
	 *            the localKama to set
	 */
	public void setLocalKama(int localKama) {
		this.localKama = localKama;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeLocalMovePacket [itemType=" + itemType + ", itemAmount=" + itemAmount + ", localKama=" + localKama + "]";
	}

}