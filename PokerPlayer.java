import java.util.Scanner;

public class PokerPlayer {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Poker game = new Poker();
		game.printRules();
		boolean playing = true;
		while (playing){
			
		
				game.start();
				System.out.println("Your hand: ");
				System.out.println(game.showCards());
				
				
				System.out.print("Would you like to reject any of your cards? (y/n)" + "\n");
				boolean done = false;
				int numberToSwitch = 0;
				int indexToSwitch;
//determines how many cards the user wants to switch
				while (!done){
					System.out.println("Please input either 'y' or 'n'");
					String ans = scan.nextLine();
					if (ans.contains("y")){
						System.out.println("How many do you want to switch?");
						numberToSwitch = scan.nextInt();
						done = true;
					}
					else if (ans.contains("n")){
						done = true;
					}
				}
//switches cards				
				String exampleCard = game.getCard(0);
				for (int i = 0; i < numberToSwitch;){
					System.out.println("Which card do you want to switch?" + "\n" + "Indicate position (eg, type '1' to switch your first card, the " + exampleCard + ")");
					indexToSwitch = scan.nextInt() - 1;
					if (indexToSwitch<5){
						game.reject(indexToSwitch);
						i++;
					}
					else {
						System.out.println("Please indicate the position of the card " + "\n" + "you want to switch with a number 1-5");
					}
				}	
//Displays current hand (with switched cards), gives score from that hand, and gives current score				
				System.out.println("Your new hand: " + "\n" + game.showCards());
				game.score();
				System.out.print("Your current total score: ");
				System.out.println(game.currentScore);
	
//asks user to play again		
//if user inputs y for yes, they play again
				System.out.println("Play again?");
				String an;
				an = scan.next();
				if (an.contains("y")){
					
				}
//otherwise the user receives their final score and a "good game" message based on final score
//unless they're playing again, game ends
				else{
					System.out.println("Final Score: " + game.currentScore);
					if (game.currentScore>10){
						System.out.println("Great Job!!! :o");
					}
					else if(game.currentScore>0){
						System.out.println("Nice!");
					}
					else{
						System.out.println("GG buddy :) better luck next time");
					}
					playing = false;
				}
				
		
		}
		scan.close();
	}

}
