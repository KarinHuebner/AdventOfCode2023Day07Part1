package Day7Part1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
	private String file;
	public ArrayList<Hand> hands = new ArrayList<Hand>();
	public ArrayList<Hand> allSortedHands;
	public ArrayList<ArrayList<Hand>> allHands = new ArrayList<ArrayList<Hand>>();

	public ArrayList<Hand> type6Hands = new ArrayList<Hand>();
	public ArrayList<Hand> type5Hands = new ArrayList<Hand>();
	public ArrayList<Hand> type4Hands = new ArrayList<Hand>();
	public ArrayList<Hand> type3Hands = new ArrayList<Hand>();
	public ArrayList<Hand> type2Hands = new ArrayList<Hand>();
	public ArrayList<Hand> type1Hands = new ArrayList<Hand>();
	public ArrayList<Hand> type0Hands = new ArrayList<Hand>();
	
	int type = 0;
	char[] label = {'A', 'K', 'Q', 'J', 'T','9','8','7','6','5','4','3','2'};

	public Parser(String file) {
		this.file = file;
	}
	
	public void parseFile() throws FileNotFoundException {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String cards;
			int bid;
			for (String line : br.lines().toList()) { 
				String[] input = line.trim().split(" ");
			
				cards = input[0];
				bid = Integer.parseInt(input[1]);
				hands.add(new Hand (cards, bid));
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	public void findResultPart1() {	//i.o.: für alle hands Type ermitteln und setzen
		for (int i = 0; i< hands.size(); i++) {
			System.out.print(hands.get(i).getCards());
			findType(hands.get(i));
			
			//SortierString erstellen 
			findSortString(hands.get(i));
		}
		
		//sortieren (stärkste/ höchste nach hinten)
		allSortedHands = new ArrayList<Hand>(sortMyHands(hands));
		
		//totalwinnings bestimmen (Summe aus bid*rank über alle Hands)
		findwinnings (allSortedHands);
		System.out.println("Result day/Part1: " + determineTotalwinningss(allSortedHands) );
	}

	private void findwinnings(ArrayList<Hand> allSortedHands) {
		int winnings;
		for (int i = 0;i<allSortedHands.size(); i++) {
			winnings = allSortedHands.get(i).getBid()*(i+ 1);
				
			allSortedHands.get(i).setWinnings(winnings);
		}	
	}

	private void findSortString(Hand hand) {//i.O.
		//vor dem Sortieren: String für die Sortier-Reihenfolge in Hand eintragen:
		//'T' durch 'B'
		//'J' durch	'C'	 ersetzen  
		//'Q' durch 'D'	 ersetzen
		//'K' durch 'E'	ersetzen
		//'A' durch 'F' ersetzen
		char[] data = hand.getCards().toCharArray();
		String sortString = new String(String.copyValueOf(data));
		
		sortString= sortString.replace('T', 'B');
		sortString= sortString.replace('J', 'C');
		sortString= sortString.replace('Q', 'D');
		sortString= sortString.replace('K', 'E');
		sortString= sortString.replace('A', 'F');

//		System.out.println(", sortString: " + sortString);
		hand.setSortString(sortString);
	}

	private ArrayList<Hand> sortMyHands (ArrayList<Hand> h) {
		h.sort(null);
		for (Hand hand: h) {
			System.out.println("SortMyHands: Type: " + hand.getType() + ", Cards: " + hand.getCards());
		}
		return h;
	}

	private int determineTotalwinningss(ArrayList<Hand> allSortedHands) {
		int sum=0;
		for (int i =0; i<allSortedHands.size(); i++) {
			sum += allSortedHands.get(i).getWinnings(); 
		}
		return sum;
	}

	private void findType(Hand h) { //i.o.
		int[] count = new int[label.length]; 
		for (int i = 0; i <label.length; i++) {
			count[i] = 0;
		}
		count = countLabels(h.getCards(),count);
				
		//setType
		h.setType(determineType(count));
		System.out.print(", Type: " + h.getType());
	}

	private int determineType(int[] ct) {	// i.o.
//		5ofAKind,	4OfAKind,	FullHouse,	3OfAKind,TwoPair,	OnePair, 	HighCard
		//	6			,5			,4			,3		,2			,1			,0 (default)
		
		for (int  i = 0; i<ct.length; i++) {
			if (ct[i] == 5) return 6;	
			if (ct[i] == 4) return 5;
			if (ct[i] == 3) {	//looking for a FullHouse
				for (int j = 0; j<ct.length; j++) {
					if (ct[j] == 2) {
						return 4;
					} 
				}
				return 3;
			}
			if (ct[i] == 2) { // looking for TwoPair
								//or looking for FullHouse!
				for (int j = 0; j<ct.length; j++) {
					if ((i!=j) && ct[j] == 2) {
						return 2;	//TwoPair
					} 
					if (ct[j] == 3) {
						return 4;	//FullHouse
					}
				}
				return 1;
			}
		}
		return 0;	
	}

	private int[] countLabels(String cards, int[] ct) { //i.o.
		for (int i = 0; i< cards.length(); i++) {	
			for (int j = 0; j<label.length; j++) {
				if (cards.charAt(i) == label[j]) {
					ct[j]++;
					continue;
				}
			}
		}	
		return ct;
	}
}