package an_code;

import java.util.ArrayList;
import java.util.HashMap;

public class Utils {
	private static final String CRLF = "\r\n";
	private static final String SINGLE_SPACE = " ";

	public static HashMap<String, String[]> getMapFromString(String dataString) {
		String[] data = dataString.split(CRLF);
		HashMap<String, String[]> dataMap = new HashMap<String, String[]>();
		for (int i = 0; i < data.length; i++) {
			appendToDataMap(data[i], dataMap);
		}
		return dataMap;
	}

	public static void appendToDataMap(String line, HashMap<String, String[]> dataMap) {
		String[] dat = line.trim().split(SINGLE_SPACE);
		ArrayList<String> arrayList = new ArrayList<String>();
		// start from 1 since 0 is the key
		for (int i = 1; i < dat.length; i++) {
			if (dat[i] == null || dat[i].length() == 0) {
				continue;
			}
			arrayList.add(dat[i].trim());
		}
		String[] dep = new String[arrayList.size()];
		arrayList.toArray(dep);
		dataMap.put(dat[0], dep);
	}
}