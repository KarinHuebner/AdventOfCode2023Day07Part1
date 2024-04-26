package Day7Part1;
/**
 * Description of the Problem: 
 * https://adventofcode.com/2023/day/7
 */
public class Main {

	public static void main(String[] args) {
		try {
			String file = "input07.txt";
		//	String file = "test07.txt";
			Parser p = new Parser (file);
			p.parseFile();

			p.findResultPart1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
