package cardgame;

// Player - Simple card game player with name and hand of cards
// author: Ege Ozan Özyedek
// date: 20.02.2018
public class Player
{
	// properties
	String name;
	Cards hand;


	// constructors
	public Player( String name )
	{
		this.name = name;
		hand = new Cards( false );
	}

	// methods
	public String getName()
	{
		return name;
	}

	public void add( Card c )
	{
		hand.addTopCard( c );
	}

	public Card playCard()
	{
		if ( hand.valid > 0 ) 
			return hand.getTopCard();
		else
			return null;
	}
	
	public Cards getHand() {
		return hand;

	}
	
} // end class Player
