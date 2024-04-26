package Day7Part1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String file = "input07.txt";
		//	String file = "test07.txt";
			Parser p = new Parser (file);
			p.parseFile();

	//		System.out.println(p.hands.size());

			p.findResultPart1();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
