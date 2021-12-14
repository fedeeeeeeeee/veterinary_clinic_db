package utility;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadTextToHashMap {
	final static String filePath = "src/utility/animale.txt";
	
	public static Map<String, List<String>> HashMapFromTextFile() {

		Map<String, List<String>> map = new HashMap<>();
		BufferedReader br = null;

		try {
			// create BufferedReader object from the File
			br = new BufferedReader(new FileReader(new File(filePath)));

			String line = null;

			// read file line by line
			while ((line = br.readLine()) != null) {

				// split the line by ;
				String[] parts = line.split(";");
				String key = parts[0].substring(0, parts[0].indexOf(":"));
				
				List<String> values = new ArrayList<>();
				for(int i=0; i<parts.length; i++) {
					values.add(Stream.of(parts[i].substring(parts[i].indexOf(" "), parts[i].length())).collect(Collectors.joining(",")));
				}
				map.put(key, values);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
				};
			}
		}
		return map;
	}
}