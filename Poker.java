import java.util.ArrayList;
import java.util.Random;

public class Poker {
	public Poker(){
		//start();
	}
	int currentScore = 5;
	Card[] hand = new Card[5];
	Card[] deck = new Card[52];
	String[] suits = {"hearts", "spades", "clubs", "diamonds"};
	int smallest1;
	boolean royalStraight = false;
	
	//initializes deck
	public void start(){
		
		for (int m = 0; m < 52;){
			for (int j = 0; j < 4; j++){
				for (int i = 0; i < 13; i++){
					deck[m] = new Card(i + 1, suits[j]);
					m++;
				}
			}
		}
		
		deal();

		currentScore-=1;
	}
	
	public void printRules() {
		String rules = "Welcome to PokerPlayer!" + "\n" + "\n"+ "Rules: "
		+ "\n" +"You start with 5 points. Each round has a 1 point buy-in.\n You "
		+ "earn points based on your hand, and you have one opportunity to "
		+ "exchange any of your cards for another random card. \n \n";
		String scores = "Scores:" + "\n";
		scores+="Royal Flush: 250 points\n";
		scores+="Straight Flush: 200 points\n";
		scores+="Four of a kind: 100 points\n";
		scores+="Full House: 30 points\n";
		scores+="Flush: 25 points\n";
		scores+="Straight: 20 points\n";
		scores+="Three of a kind: 15 points\n";
		scores+="Two Pair: 10 points\n";
		scores+="One Pair: 5 points\n";


		System.out.println(rules+scores);
	}

//deals card
	public void deal(){
		Random a = new Random();
		int x;
		for (int i = 0; i < 5;){
			x = a.nextInt(52);
			if (!deck[x].used){
				hand[i] = new Card(deck[x].number, deck[x].suit);
				deck[x].use();
				i++;
			}
			else{
			}
	}

	}
//returns String of hand
	public String showCards(){
		String cardName = "";
		for (int i = 0; i < 5; i++){
			cardName = cardName + (i+1) + ".  " + getCard(i) + "\n";
		}
		return cardName;
	}
	
	public Card[] printDeck(){
		return deck;
	}
	
	public String getCard(int index) {
		return format(hand[index].number) + " of " + hand[index].suit;
	}
	
//removes a card and adds random card
	public void reject(int index){
		boolean done = false;
		Random a = new Random();
		int x = a.nextInt(52);
		while (!done){
			if (!deck[x].used){
				hand[index] = deck[x];
				deck[x].use();
				done = true;
			}
			else {
				x = a.nextInt(52);
			}
		}
	}
	
//determines what kind of hand the player has and returns the appropriate score
	public int score(){
		int numberofvalues = numberofvalues();
		int signaturesum = 0;
		for (int i = 0; i < 5; i++){
			signaturesum += scoreSignature()[i]; 
		}

//creates booleans for each score type
		boolean isRoyal = false;
		boolean isStraightFlush = false;
		boolean fourof = false;
		boolean fullHouse = false;
		boolean flush = false;
		boolean straight = false;
		boolean threeof = false;
		boolean twopair = false;
		boolean onepair = false;

//calls private methods sameSuit and isStraight to initialize booleans, which are used to determine score types		
		boolean samesuit = sameSuit();
		boolean inOrder = isStraight();
		int score = 0;

//uses score signature to differentiate between two pair/three of a kind and full house/four of a kind
		if (signaturesum==9){
			twopair = true;
		}
		
		if(signaturesum==11){
			threeof = true;
		}
		
		if (signaturesum==13){
			fullHouse = true;
		}
		
		if (signaturesum == 17){
			fourof = true;
		}

//uses numberofvalues to determine if there is exactly one pair		
		if (numberofvalues==4){
			onepair = true;
		}

//uses samesuit and inOrder to determine if it's a flush, a straight, and/or a straight flush, and/or a royal flush
		if (samesuit){
			flush = true;
		}
		
		if (inOrder){
			straight = true;
		}
		
		if (inOrder && samesuit){
			isStraightFlush = true;
		}
		
		if (inOrder && samesuit && royalStraight){
			isRoyal = true;
		}
		
//adjusts score based on what the score type is		
		if (isRoyal){
			score = 250;
			System.out.println("WOW!!! A royal flush!!!");
		}
		else if (isStraightFlush){
			score = 200;
			System.out.println("Whoa! You got a straight flush!");
		}
		else if (fourof){
			score = 100;
			System.out.println("Wow, four of a kind!");
		}
		else if (fullHouse){
			score = 30;
			System.out.println("Wow, a full house!");
		}
		else if (flush){
			score = 25;
			System.out.println("You got a flush!");
		}
		else if (straight){
			score = 20;
			System.out.println("You got a straight!");
		}
		else if (threeof){
			score = 15;
			System.out.println("You got three of a kind!");
		}
		else if (twopair){
			score = 10;
			System.out.println("You got two pair!");
		}
		else if (onepair){
			score = 5;
			System.out.println("You got one pair!");
		}
		else {
			score = 0;
		}
		
		if (score>0) {
			System.out.println("This gives you another " + score + " points!");
		}
		
		
		currentScore += score;
		return score;
	
	}
	
//returns true if the entire hand is the same suit
	private boolean sameSuit(){
		boolean a = true;
		//checks for the same suit
		for (int i = 0; i < 4; i++){
			if (hand[i].suit.contentEquals(hand[i + 1].suit)){
				
			}
			else {
				a = false;
			}
		}
		return a;
	}
	

//returns the number of unique values the hand contains
//a hand with no matches would return 5, with one match would return 4, with two matches or three of a kind would return 3, etc
	private int numberofvalues(){

		int values = 1;
		
		for (int i = 1; i < 5; i++){
			for (int j = 0; j < i;){
				
				if (i < 5 && j < 5){
					
					if(hand[i].number==hand[j].number){
						j = 0;
						i++;
					}
					else{
						j++;
					}
					
				}
				else {
					return values;
				}
			}
				values++;
		}
		return values;
	}
	
//creates an array of how many times each value appears in the hand
//this is used to differentiate between hands that have the same number of values but different score types
//for example, two pair and three of a kind have the same number of values but different score signatures
	private int[] scoreSignature(){
		int[] numberofeach = {0, 0, 0, 0, 0};
		int l;
		for (int i = 0; i < 5; i++){
			l = hand[i].number;
			for (int j = 0; j < 5; j++){
				if (hand[j].number==l){
					numberofeach[i]++;
				}
			}
			
		}
		return numberofeach;
	}

//changes card value to either a String of the number or Jack, Queen, King, or Ace 
	public String format(int numb){
		String nu = numb + "";

			if (numb == 11){
				nu = "Jack";
			}
			else if (numb == 12){
				nu = "Queen";
			}
			else if (numb == 13){
				nu = "King";
			}
			else if (numb == 1){
				nu = "Ace";
			}
	return nu;
	}
	
//determines whether or not the hand is a straight
//if I were a hand, this method would return false
	private boolean isStraight(){
		boolean isStraight = true;
		int smallest = smallest();
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (int i = 0; i < 5; i++){
			values.add(hand[i].number);
		}
				
		if (smallest==1 && values.contains(13)){
			
			for (int i = 0; i < 4; i++){
				if (!values.contains(10 + i)){
					isStraight = false;
				}
			}
			
			if (isStraight){
				royalStraight = true;
			}
		}
		else{	
			for (int i = 0; i < 5; i++){
				if (!values.contains(smallest + i)){
					isStraight = false;
				}
			}
		}
		return isStraight;
	}

//returns the smallest value in the hand
//considers ace as low
	private int smallest(){
		int smallest = hand[0].number;
		for (int i = 1; i < 5; i++){
			if (hand[i].number<smallest){
				smallest = hand[i].number;
			}
		}
		return smallest;
	}
	
	
}
