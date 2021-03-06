/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.util;

import fr.aresrpg.dofus.Constants;
import fr.aresrpg.dofus.structures.map.Cell;
import fr.aresrpg.dofus.structures.map.DofusMap;

import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

public class Maps {
	private Maps() {
	}

	public static InputStream downloadMap(String assetsServer, int id, String subId) throws IOException {
		return new URL(assetsServer + Constants.MAP_PATH + id + '_' + subId + "X.swf").openStream();
	}

	public static InputStream downloadMap(int id, String subId) throws IOException {
		return downloadMap(Constants.DEFAULT_DATA_URL, id, subId);
	}

	/**
	 * Load a map with mapData
	 * 
	 * @param data
	 *            the datas
	 * @param decryptKey
	 *            the decrypt key
	 * @param consumeCellDatas
	 *            a consumer if you want retrieve the decrypted cells datas and do whatever you want with
	 * @return the map
	 */
	public static DofusMap loadMap(Map<String, Object> data, String decryptKey, String subid) {
		String cellData = (String) data.get("mapData");
		String key = Crypt.prepareKey(decryptKey);
		cellData = Crypt.decipherData(cellData, key, Integer.parseInt(Character.toString(Crypt.checksum(key)), 16) * 2);
		int width = (int) data.get("width");
		int height = (int) data.get("height");
		Cell[] cells = Compressor.uncompressCells(cellData);
		return new DofusMap((int) data.get("id"), parseDate(subid), width,
				height, (int) data.get("musicId"),
				(int) data.get("capabilities"), (boolean) data.get("bOutdoor"),
				(int) data.get("backgroundNum"), cells);
	}

	public static long parseDate(String date) {
		int year = Integer.parseInt(date.substring(0, 2)) + 2000;
		int month = Integer.parseInt(date.substring(2, 4));
		int day = Integer.parseInt(date.substring(4, 6));
		int hours = Integer.parseInt(date.substring(6, 8));
		int min = Integer.parseInt(date.substring(8));
		LocalDateTime dateTime = LocalDateTime.of(year, month, day, hours, min);
		return dateTime.atZone(ZoneId.of("Europe/Berlin")).toEpochSecond() * 1000;
	}

	public static void main(String[] args) {
		System.out.println(parseDate("0801081546"));
	}

	public static int distance(int from, int to, int width, int height) {
		int xto = getXRotated(to, width, height);
		int xfrom = getXRotated(from, width, height);
		int yto = getYRotated(to, width, height);
		int yfrom = getYRotated(from, width, height);
		return (xto - xfrom) * (xto - xfrom) + (yto - yfrom) * (yto - yfrom);
	}

	public static int distanceManathan(int from, int to, int width, int height) {
		int xto = getXRotated(to, width, height);
		int xfrom = getXRotated(from, width, height);
		int yto = getYRotated(to, width, height);
		int yfrom = getYRotated(from, width, height);
		return Math.abs(xto - xfrom) + Math.abs(yto - yfrom);
	}

	public static int distanceManathan(int xfrom, int yfrom, int xto, int yto, int width, int height) {
		return Math.abs(xto - xfrom) + Math.abs(yto - yfrom);
	}

	public static int distanceManathan(Point from, Point to, int width, int height) {
		return distanceManathan(from.x, from.y, to.x, to.y, width, height);
	}

	public static int getY(int id, int width) {
		return (int) (id / (width - 0.5));
	}

	public static int getX(int id, int width) {
		return (int) ((id % (width - 0.5)) * 2);
	}

	public static int getId(int x, int y, int width) {
		return (int) (y * (width - 0.5) + x / 2.0);
	}

	private static final double PId4 = Math.PI / 4;
	private static final double COS_PId4 = Math.cos(PId4);
	private static final double SIN_PId4 = Math.sin(PId4);
	private static final double COS_mPId4 = COS_PId4;
	private static final double SIN_mPId4 = -SIN_PId4;

	public static int getXRotated(int id, int width, int height) {
		int x = getX(id, width) - width;
		int y = getY(id, width) - height;
		return (int) Math.ceil((x * COS_PId4 - y * SIN_PId4 - 0.25) * 0.7) + width;
	}

	public static int getYRotated(int id, int width, int height) {
		int x = getX(id, width) - width;
		int y = getY(id, width) - height;
		return (int) Math.ceil((x * SIN_PId4 + y * COS_PId4 - 1.75) * 0.7) + height;
	}

	public static int getIdRotated(int xRot, int yRot, int width, int height) {
		double xR = Math.ceil(xRot - width) / 0.7 + 0.25;
		double xY = Math.ceil(yRot - height) / 0.7 + 1.75;
		int x = (int) Math.round(xR * COS_mPId4 - xY * SIN_mPId4 - 0.1) + width;
		int y = (int) Math.round(xR * SIN_mPId4 + xY * COS_mPId4) + height;
		return getId(x, y, width);
	}

	public static void mazin(String[] args) {
		int id = 478;
		int width = 15;
		int heigth = 17;
		int x = getXRotatedSceat(id, width, heigth) + 1;
		int y = getYRotatedSceat(id, width, heigth);
		int idSceat = getIdRotatedSceat(x, y, width, heigth);
		System.out.println("test = " + id);
		System.out.println("x = " + x);
		System.out.println("y = " + y);
		System.out.println("final id = " + idSceat);
		System.out.println("isInMap = " + isInMapRotated(x, y, width, heigth));
	}

	public static int getXRotatedSceat(int id, int width, int height) {
		int n84 = width * (width * 2 - 2);
		if (id <= n84 && id % width == 0) return getXOfMultiple(id, width, height);
		int var = id;
		int count = 0;
		while (var % width != 0 || var > n84) {
			var -= (width * 2 - 1);
			count++;
			if (var < 0) return getXupper(id, width, height);
		}
		int xOfMultiple = getXOfMultiple(var, width, height);
		while (count-- > 0)
			xOfMultiple--;
		return xOfMultiple;
	}

	public static int getYRotatedSceat(int id, int width, int height) {
		int n84 = width * (width * 2 - 2);
		if (id <= n84 && id % width == 0) return height;
		int var = id;
		int count = 0;
		while (var % width != 0 || var > n84) {
			var -= (width * 2 - 1);
			count++;
			if (var < 0) return getYupper(id, width, height);
		}
		int yOfMultiple = height;
		while (count-- > 0)
			yOfMultiple--;
		return yOfMultiple;
	}

	public static int getIdRotatedSceat(int x, int y, int width, int height) {
		int id = (width + height - 1 - x) * width;
		return y > height ? id - (y - height) * (width - 1) : y < height ? id + (height - y) * (width - 1) : id;
	}

	private static int getXupper(int id, int width, int height) {
		int count = 0;
		int var = id;
		while (var % width != 0) {
			var += (width * 2 - 1);
			count++;
		}
		int xOfMultiple = getXOfMultiple(var, width, height);
		while (count-- > 0)
			xOfMultiple++;
		return xOfMultiple;
	}

	private static int getYupper(int id, int width, int height) {
		int count = 0;
		int var = id;
		while (var % width != 0) {
			var += (width * 2 - 1);
			count++;
		}
		int yOfMultiple = height;
		while (count-- > 0)
			yOfMultiple++;
		return yOfMultiple;
	}

	static int getXOfMultiple(int id, int width, int height) {
		return (width + height - 1) - id / width;
	}

	public static boolean isInMapRotated(int x, int y, int width, int height) {
		if (leftUpCorner(x, y, width, height)) return x + y >= height - 1;
		else if (leftDownCorner(x, y, width, height)) return x >= y - (height - 1);
		else if (rightUpCorner(x, y, width, height)) return (x - (height - 1)) <= y;
		else if (rightDownCorner(x, y, width, height)) return x + y <= (width - 1) * 2 + (height - 1);
		return true;
	}

	private static boolean leftUpCorner(int x, int y, int width, int height) {
		return x < height && y < height;
	}

	private static boolean leftDownCorner(int x, int y, int width, int height) {
		return x < width - 1 && y > height - 1;
	}

	private static boolean rightUpCorner(int x, int y, int width, int height) {
		return x > height - 1 && y < width - 1;
	}

	private static boolean rightDownCorner(int x, int y, int width, int height) {
		int max = width - 1;
		return x > max && y > max;
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return true if the provided coordinate are inside the dofus map
	 */
	public static boolean isInMapRotatedBug(int x, int y, int width, int height) {
		return isInMap(getIdRotated(x, y, width, height), width, height);
	}

	public static boolean isInMap(int id, int width, int height) {
		return id >= 0 && id < Math.round((width - 0.5) * (height - 0.5) * 2);
	}

}
