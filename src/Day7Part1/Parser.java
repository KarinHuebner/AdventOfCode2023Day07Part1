package Day7Part1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Parser {
	
	private String file;
	public ArrayList<Hand> hands = new ArrayList<Hand>();
	public ArrayList<Hand> allSortedHands;
	public ArrayList<ArrayList> allHands = new ArrayList<ArrayList>();

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
		BufferedReader br = new BufferedReader(new FileReader(file));
	
		String cards;
		int bid;
	
		for (String line : br.lines().toList()) { // text.split("\n")) {//
			String[] input = line.trim().split(" ");
		
			cards = input[0];
			bid = Integer.parseInt(input[1]);
			hands.add(new Hand (cards, bid));
		}
	}

	public void findResultPart1() {
		// 
		//i.o.: für alle hands Type ermitteln und setzen
		for (int i = 0; i< hands.size(); i++) {
			System.out.print(hands.get(i).getCards());
			findType(hands.get(i));
			
			//SortierString erstellen 
			findSortString(hands.get(i));
		}

		
		//Listen mit hands eines Types erstellen
//		fillTypeLists(hands);
		
//		fillAllHandsList();
		
		//sortieren (stärkste/ höchste nach hinten)
		allSortedHands = new ArrayList<Hand>(sortMyHands(hands));
		
		//totalWinnings bestimmen (Summe aus bid*rank über alle Hands)
		findWinning (allSortedHands);
		
		System.out.println("Result day/Part1: " + determineTotalWinnings(allSortedHands) );
		
	}

	private void findWinning(ArrayList<Hand> allSortedHands) {
		int winning;
		for (int i = 0;i<allSortedHands.size(); i++) {
			winning = allSortedHands.get(i).getBid()*(i+ 1);
				
			allSortedHands.get(i).setWinning(winning);
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

	private void fillAllHandsList() {
		allHands.add(type0Hands);
		allHands.add(type1Hands);
		allHands.add(type2Hands);
		allHands.add(type3Hands);
		allHands.add(type4Hands);
		allHands.add(type5Hands);
		allHands.add(type6Hands);
	}

	private ArrayList<Hand> sortMyHands (ArrayList<Hand> h) {
		h.sort(null);
		for (Hand hand: h) {
			System.out.println("SortMyHands: Type: " + hand.getType() + ", Cards: " + hand.getCards());
		}
		return h;
	}
	
	/**private ArrayList<Hand> sortByRank(ArrayList cardsOfSameType, int i) {
		// TODO Auto-generated method stub
		
		//CompareTo aufrufen
		/**
		 * 	//sortiere innerhalb der Liste anhand der cards (String) im Vergleich zu labels
			ArrayList<String> sortedCards = new ArrayList<String>();
			
			//befüllen: 
			for (int j = 0; j<allHands.get(i).size(); j++) {
				
				//in der neuen Liste
					
			}
			

			//currentList.sort(null); //sort by NaturalOrder 
		return cardsOfSameType;
	}
	 * @param allSortedHands 
*/

	private int determineTotalWinnings(ArrayList<Hand> allSortedHands) {
		int sum=0;
		for (int i =0; i<allSortedHands.size(); i++) {
			sum += allSortedHands.get(i).getWinning(); 
		}
		return sum;
	}

	private void fillTypeLists(ArrayList<Hand> hands) {	// i.o.
		for ( int i = 0; i<hands.size(); i++) {
			if (hands.get(i).getType()== 0) type0Hands.add (hands.get(i));
			if (hands.get(i).getType()== 1) type1Hands.add (hands.get(i));
			if (hands.get(i).getType()== 2) type2Hands.add (hands.get(i));
			if (hands.get(i).getType()== 3) type3Hands.add (hands.get(i));
			if (hands.get(i).getType()== 4) type4Hands.add (hands.get(i));
			if (hands.get(i).getType()== 5) type5Hands.add (hands.get(i));
			if (hands.get(i).getType()== 6) type6Hands.add (hands.get(i));
		}
	/**	System.out.println("Länge Liste Typ 0: " + type0Hands.size());
		System.out.println("Länge Liste Typ 1: " + type1Hands.size());
		System.out.println("Länge Liste Typ 2: " + type2Hands.size());
		System.out.println("Länge Liste Typ 3: " + type3Hands.size());
		System.out.println("Länge Liste Typ 4: " + type4Hands.size());
		System.out.println("Länge Liste Typ 5: " + type5Hands.size());
		System.out.println("Länge Liste Typ 6: " + type6Hands.size());
		*/
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
/**		System.out.print(cards + ", ct: " );
		for (int x = 0; x <ct.length; x++) {
			System.out.print(ct[x] + "\t");
		}
		System.out.println();
	*/
		return ct;
	}
}