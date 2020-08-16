import java.util.Scanner;
import cardgame.*;

// CardGameTest
// author: Ege Ozan Özyedek
// date: 20.02.2018
public class CardGameTest
{
	public static void main( String[] args)
	{
		Scanner scan = new Scanner( System.in);

		System.out.println( "Start of CardGameTest\n");

		// CONSTANTS

		// VARIABLES
		Card       c;
		Cards      cards;
		ScoreCard  scores;
		Player     p;
		Player     px;
		Player     py;
		Player     pz;
		CardGame   game;

		// PROGRAM CODE

		// test Card class
		c = new Card( 3);
		Card c1 = new Card( 42);
		Card c2 = new Card( 36);
		Card c3 = new Card( 50);
		System.out.println( c);
		System.out.println();

		// test Cards class
		cards = new Cards( false);
		cards.addTopCard( c3);
		cards.addTopCard( c2);
		cards.addTopCard( c1);
		cards.getTopCard();
		cards.testOnlyPrint();  // remove method after testing!

		// test ScoreCard class
		scores = new ScoreCard( 4);
		scores.update( 3, 1);
		scores.update( 1, 2);
		System.out.println( "\n" + scores);

		// test Player class
		p = new Player( "Ege");
		System.out.println( p.getName() + "\n");
		p.add( cards.getTopCard());
		System.out.println( p.playCard().toString());

		px = new Player( "Player A");
		py = new Player( "Player B");
		pz = new Player( "Player C");

		// test CardGame class 
		game = new CardGame( p, px, py, pz );
		System.out.println( game.isTurnOf(px));
		System.out.println( game.isGameOver() + "\n" + game.getName(0) + "\n" + game.getScore(2));
		System.out.println( "\n" + game.getRoundNo() + "\n" + game.getTurnOfPlayerNo());
		game.playTurn( p, c);

		System.out.println( "\nEnd of CardGameTest\n" );
		scan.close();
	}

} // end of class CardGameTest
