import java.util.Scanner;
import cardgame.*;

// MyCardGame - provides a menu allowing any of the players to play their card,
//              an option to see the score card, and one to quit the game at any time.
//              When the game is over it dislays the winners.
// author: Ege Ozan Özyedek
// date: 20.02.2018
public class MyCardGame
{
	public static void main( String[] args)
	{
		Scanner scan = new Scanner( System.in);

		System.out.println( "Start of MyCardGame\n");

		// CONSTANTS
		final int MENU_EXIT    = 0;
		final int MENU_PLAY_P1 = 1;
		final int MENU_PLAY_P2 = 2;
		final int MENU_PLAY_P3 = 3;
		final int MENU_PLAY_P4 = 4;
		final int MENU_SCORES  = 5;

		// VARIABLES
		Player     p1, p2, p3, p4;
		CardGame   game;
		int        selection;
		boolean    condition;

		// PROGRAM CODE

		// create players...
		p1 = new Player( "Player 1");
		p2 = new Player( "Player 2");
		p3 = new Player( "Player 3");
		p4 = new Player( "Player 4");

		// create game with the 4 players...
		game = new CardGame( p1, p2, p3, p4);

		condition = true;

		// display menu, get and process selection, until exit
		do 
		{
			// display menu
			System.out.println();
			System.out.println( "MyCardGame   Round: " + game.getRoundNo() 
			+ "\t TurnOfPlayer: " + game.getTurnOfPlayerNo() );
			System.out.println();
			System.out.println( MENU_PLAY_P1 + " - Player " + MENU_PLAY_P1 + " plays" );
			System.out.println( MENU_PLAY_P2 + " - Player " + MENU_PLAY_P2 + " plays" );
			System.out.println( MENU_PLAY_P3 + " - Player " + MENU_PLAY_P3 + " plays" );
			System.out.println( MENU_PLAY_P4 + " - Player " + MENU_PLAY_P4 + " plays" );
			System.out.println( MENU_SCORES  + " - Show scores" );

			// ask for and get selection
			System.out.println();
			System.out.println( "Selection (" + MENU_EXIT + " to exit): ");
			selection = scan.nextInt();

			// process selection
			if ( selection == MENU_PLAY_P1 )
				condition = play( p1, game);

			else if ( selection == MENU_PLAY_P2 )
				condition = play( p2, game);

			else if ( selection == MENU_PLAY_P3 )
				condition = play( p3, game);

			else if ( selection == MENU_PLAY_P4 )
				condition = play( p4, game);

			else if ( selection == MENU_SCORES )
				System.out.println( game.showScoreCard() );

			else if ( selection != MENU_EXIT)
				System.out.println( "Invalid selection! \n" );

			if ( !condition)
				System.out.println("\nIts not the chosen players turn to play");

		} while ( selection != MENU_EXIT && !game.isGameOver() );

		if ( game.isGameOver()) {
			String result;
			result = "";
			Player[] winners = game.getWinners();
			for (int i = 0; i < winners.length; i++) {
				result += "\n" + winners[i].getName();
			}
			System.out.println( game.showScoreCard() + "\n\nWinner(s)" + "\n_________" + result + "\n_________" +"\nCongrats!");
		}

		System.out.println( "\nEnd of MyCardGame\n" );   
		scan.close();
	}

	private static boolean play( Player p, CardGame game)
	{
		Card       c;
		boolean    accepted;

		c = p.playCard();

		accepted = game.playTurn(p, c);

		if ( !accepted)
			p.add(c);

		return accepted;
	}


} // end class MyCardGame
