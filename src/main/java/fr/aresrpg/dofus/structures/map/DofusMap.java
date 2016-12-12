package fr.aresrpg.dofus.structures.map;

import java.util.Arrays;

public class DofusMap {
	private int id;
	private int width;
	private int height;
	private int musicId;
	private int capabilities;
	private boolean outdoor;
	private int backgroundId;
	private Cell[] cells;

	public DofusMap(int id, int width, int height, int musicId, int capabilities, boolean outdoor, int backgroundId, Cell[] cells) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.musicId = musicId;
		this.capabilities = capabilities;
		this.outdoor = outdoor;
		this.backgroundId = backgroundId;
		this.cells = cells;
	}

	public int getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Cell[] getCells() {
		return cells;
	}

	/**
	 * @return the backgroundId
	 */
	public int getBackgroundId() {
		return backgroundId;
	}

	/**
	 * @return the capabilities
	 */
	public int getCapabilities() {
		return capabilities;
	}

	/**
	 * @return the musicId
	 */
	public int getMusicId() {
		return musicId;
	}

	@Override
	public String toString() {
		return "DofusMap [id=" + id + ", width=" + width + ", height=" + height + ", musicId=" + musicId + ", capabilities=" + capabilities + ", outdoor=" + outdoor + ", backgroundId=" + backgroundId
				+ ", cells=" + Arrays.toString(cells) + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		return obj instanceof DofusMap && ((DofusMap) obj).getId() == getId();
	}
}
