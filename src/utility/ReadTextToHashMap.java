package utility;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadTextToHashMap {
	final static String filePath = "src/utility/animale.txt";
	
	public static Map<String, List<String>> HashMapFromTextFile() {

		Map<String, List<String>> map = new HashMap<>();
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(new File(filePath)));
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
	
	public static void writeOnFile(Optional<String> key, Optional<Pair<String, String>> value) {
		BufferedWriter bv = null;
		try {
			bv = new BufferedWriter(new FileWriter(new File(filePath)));
			if(key.isPresent()) {
				// scrivi nuova chiave nel file
				bv.append(key.get());
				bv.append(":");
			} else if(value.isPresent()) {
				// scrivi nuovo valore
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void listaMap(Map<String, List<String>> map) {
		if (map != null) {
			try (FileWriter writer = new FileWriter(filePath, true)) {
				for (String key : map.keySet()) {
					List<String> value = map.get(key);
					/*if (value == null) {
						System.out.println("Warning: " + key + " in listaMap.");
						value = "Not sure";
					}*/
					writer.write(key.trim());
					writer.write(":");
					value.forEach(v-> {
						try {
							writer.write(v.trim() + ",");
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
					writer.write('\n');
				}
				writer.close();
			} catch (IOException e) {
				System.out.println("Issue: " + filePath + " in listaMap");
			}
		} else {
			System.out.println("Null map passed to listaMap.");
		}
	}
	
	
	public static void addKeyOrValueToFile(Optional<String> key, Optional<Pair<String, String>> value) {
			try (FileWriter writer = new FileWriter(filePath, true)) {
				
				if(key.isPresent()) {
					writer.append(key.get() + ": " + '\n');
				} else if(value.isPresent()) {

				}
								
				writer.close();
			} catch (IOException e) {
				System.out.println("Issue: " + filePath + " in listaMap");
		}
	}

}