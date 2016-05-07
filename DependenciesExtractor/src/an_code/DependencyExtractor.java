package an_code;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

// this class has methods to find dependencies and returns a string
public class DependencyExtractor {
	private static final String SINGLE_SPACE = " ";
	private static final String CRLF = "\r\n";

	// input: map with keys and dependencies as array
	// output: string with rows separated with CRLF and
	// in each row data is separated with single space
	public static String getDependencySting(Map<String, String[]> dataMap) {
		StringBuilder resultStringBuilder = new StringBuilder();
		Iterator<Entry<String, String[]>> entryIterator = dataMap.entrySet().iterator();
		String entryKey;
		String[] entryValue;
		while (entryIterator.hasNext()) {
			Entry<String, String[]> entry = entryIterator.next();
			entryKey = entry.getKey();
			if (entryKey != null) {
				entryValue = entry.getValue();
				if (entryValue != null) {
					TreeSet<String> dependencySet = new TreeSet<String>();
					populateDependencySet(entryKey, entryValue, dependencySet, dataMap);
					resultStringBuilder.append(entryKey).append(SINGLE_SPACE);
					Iterator<String> itr = dependencySet.iterator();
					while (itr.hasNext()) {
						resultStringBuilder.append(itr.next());
						if (itr.hasNext()) {
							resultStringBuilder.append(SINGLE_SPACE);
						}
					}
					if (entryIterator.hasNext()) {
						resultStringBuilder.append(CRLF);
					}
				}
			}
		}
		return resultStringBuilder.toString();
	}

	private static void populateDependencySet(String key, String[] dependencyKeys, TreeSet<String> dependencySet,
			Map<String, String[]> dataMap) {
		for (int i = 0; i < dependencyKeys.length; i++) {
			dependencySet.add(dependencyKeys[i]);
			if (key.equals(dependencyKeys[i])) {
				continue;
			}
			String[] nextDependencySet = dataMap.get(dependencyKeys[i]);
			if (nextDependencySet != null) {
				populateDependencySet(dependencyKeys[i], nextDependencySet, dependencySet, dataMap);
			}
		}
	}
}
