package an_code;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;

//this class accepts file name if passed as parameter
//if no parameter is passed then it accepts input from command line
//for data entry from command line after entering data for each line press enter
//to end data entry press enter without entering any data after last line
public class DependencyExtractorMain {
	private static final String EMPTY_STRING = "";

	public static void main(String[] args) throws Exception {
		if (args.length != 0) {
			processDataFromFile(args[0]);
		} else {
			processDataFromConsoleInput();
		}
	}

	private static void processDataFromFile(String fileName) {
		HashMap<String, String[]> dataMap = new HashMap<String, String[]>();
		try (FileInputStream fileInputStream = new FileInputStream(fileName);
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				Utils.appendToDataMap(line, dataMap);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (dataMap != null) {
			String dependencyString = DependencyExtractor.getDependencySting(dataMap);
			System.out.println(dependencyString);
			// write data to file
			try (FileOutputStream fileOutputStream = new FileOutputStream(".\\misc\\OutDataFile.txt");
					BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));) {
				writer.write(dependencyString);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static void processDataFromConsoleInput() {
		HashMap<String, String[]> dataMap = new HashMap<String, String[]>();
		try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
			String line;
			while ((line = input.readLine()) != null) {
				if (EMPTY_STRING.equals(line)) {
					break;
				}
				Utils.appendToDataMap(line, dataMap);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if (dataMap != null) {
			String dependencyString = DependencyExtractor.getDependencySting(dataMap);
			System.out.println(dependencyString);
		}
	}

}
