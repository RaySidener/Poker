
public class Card {
	public int number;
	public String suit;
	boolean used = false;
	
//cards are objects
//cards in the deck are "used" to indicate they are in the hand to avoid having the same card show up twice in a hand
	public Card(int numb, String sut){
		number = numb;
		suit = sut;
	}
	public void use(){
		used = true;
	}

	public void unuse(){
		used = false;
	}
}
