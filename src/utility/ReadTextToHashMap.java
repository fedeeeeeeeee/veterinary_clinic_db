package utility;

import java.io.*;
import java.util.*;

public class ReadTextToHashMap {
	InputStream is = getClass().getClassLoader().getResourceAsStream("animale.txt");

	
	public Map<String, List<String>> hashMapFromTextFile() {

		Map<String, List<String>> map = new HashMap<>();
		BufferedReader br = null;

		try {

			br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split("\\r?\\n");
				String key = parts[0].substring(0, parts[0].indexOf(":"));
				
				List<String> values = new ArrayList<>();
				for(int i=0; i<parts.length; i++) {
					String[] array = parts[i].substring(parts[i].indexOf(" ")).split(",");
					for(int j = 0; j<array.length; j++) {
						values.add(array[j]);
					}
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
	
	public void writeMapToFile(Map<String, List<String>> map) {
		emptyFile();
		if (map != null) {
			try (FileWriter writer = new FileWriter(getClass().getResource("/animale.txt").getPath(), true)) {
				for (String key : map.keySet()) {
					List<String> value = map.get(key);
					writer.write(key.trim());
					writer.write(": ");
					for(int j = 0; j<value.size(); j++) {
						if(j==value.size()-1) {
							writer.write(value.get(j) + '\n');
						} else {
							writer.write(value.get(j) + ",");
						}
					}
				}
				writer.close();
			} catch (IOException e) {
				System.out.println("Issue: animale.txt in listaMap");
			}
		} else {
			System.out.println("Null map passed to listaMap.");
		}
	}
	
	
	private void emptyFile() {
		try {
			FileWriter w = new FileWriter(getClass().getResource("/animale.txt").getPath(), false);
			w.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}