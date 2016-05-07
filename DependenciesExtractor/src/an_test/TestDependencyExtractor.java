package an_test;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;

import an_code.DependencyExtractor;
import an_code.Utils;

public class TestDependencyExtractor {

	private static final String CRLF = "\r\n";

	@Test
	public void testGetDependencyStingWithAlphabets() {
		StringBuilder sb = new StringBuilder();
		sb.append("A B C").append(CRLF);
		sb.append("B C E").append(CRLF);
		sb.append("C G").append(CRLF);
		sb.append("D A F").append(CRLF);
		sb.append("E F").append(CRLF);
		sb.append("F H");

		Map<String, String[]> inputMap = Utils.getMapFromString(sb.toString());
		String actualOutput = DependencyExtractor.getDependencySting(inputMap);

		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("A B C E F G H").append(CRLF);
		expectedOutput.append("B C E F G H").append(CRLF);
		expectedOutput.append("C G").append(CRLF);
		expectedOutput.append("D A B C E F G H").append(CRLF);
		expectedOutput.append("E F H").append(CRLF);
		expectedOutput.append("F H");

		assertEquals(expectedOutput.toString(), actualOutput);
	}

	@Test
	public void testGetDependencyStingWithDigits() {
		StringBuilder sb = new StringBuilder();
		sb.append("1 2 3").append(CRLF);
		sb.append("2 3 5").append(CRLF);
		sb.append("3 7").append(CRLF);
		sb.append("4 1 6").append(CRLF);
		sb.append("5 6").append(CRLF);
		sb.append("6 8");

		Map<String, String[]> inputMap = Utils.getMapFromString(sb.toString());
		String actualOutput = DependencyExtractor.getDependencySting(inputMap);

		StringBuilder expectedOutput = new StringBuilder();
		expectedOutput.append("1 2 3 5 6 7 8").append(CRLF);
		expectedOutput.append("2 3 5 6 7 8").append(CRLF);
		expectedOutput.append("3 7").append(CRLF);
		expectedOutput.append("4 1 2 3 5 6 7 8").append(CRLF);
		expectedOutput.append("5 6 8").append(CRLF);
		expectedOutput.append("6 8");

		assertEquals(expectedOutput.toString(), actualOutput);
	}
}
