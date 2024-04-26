package Day7Part1;

public class Hand implements Comparable<Hand>{

	private String cards;
	private int bid;
	private int type;
	private String sortString;
	private int winning;
	
	public Hand(String cards, int bid) {
		this.setCards(cards);
		this.setBid(bid);
	}
	
	@Override
	public int compareTo(Hand otherHand) {
		int c = this.getType() - otherHand.getType();
		if (c==0) {
			return (this.getSortString()).compareTo(otherHand.getSortString());
		} 
		return c;
	}
	
	public String getSortString() {
		return sortString;
	}

/**	@Override
	public int compareTo(Hand otherHand) {
		//Return: -1 = lower; 0 = equal;  1 = higher
		return Integer.compare(this.getCards(), otherHand.getCards());
		return 0;
	}
	*/
	
	public String getCards() {
		return cards;
	}

	public void setCards(String cards) {
		this.cards = cards;
	}

	public int getBid() {
		return bid;
	}
	
	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setSortString(String sortString) {
		this.sortString= sortString;
	}

	public void setWinning(int winning) {
		this.winning= winning;
	}

	public int getWinning() {
		return winning;
	}

	

}
