
/**
 * The Hand class is a subclass of the CardList class. It is used to model a hand of cards.
 * It has a private instance variable called player for storing the player who will play 
 * this hand. It also has methods for getting the player of this hand, checking its validity,
 * getting the type and top card of this hand, and checking if it beats a specified hand.
 * 
 * @author Masood Ahmed
 * @version 1.0
 * @see CardList
 * 
 */

@SuppressWarnings("serial")
public abstract class Hand extends CardList{
	
	// ********** Private variables **********
	
	
	private CardGamePlayer player;  // the player who plays this hand
	
	// ***************************************
	
	// *********** constructors *************
	
	/**
	 * A constructor for building a hand with the specified player and list of cards.
	 * 
	 * @param player The player who is playing this hand
	 * @param cards  The cards that will make the hand
	 * 
	 */
	
	public Hand(CardGamePlayer player, CardList cards) {
		
		this.player = player;  // assigning the player to the private variable
		
		// adding cards
		for (int j = 0; j < cards.size(); j++ ) {
			this.addCard(cards.getCard(j));
		}
		
	}
	// ***************************************
	
	
	// ************ public methods ***************
	
	/**
	 * A method for retrieving the player of this hand. 
	 * 
	 * @return Player who plays this hand.
	 */
	
	public CardGamePlayer getPlayer() {
		
		return player;
		
	}
	
	/**
	 * A method for retrieving the top card of this hand.
	 * 
	 * @return The top card of the hand
	 * 
	 */
	
	public Card getTopCard() {
		
		// sorting so that top card is on the top
		this.sort();	
		
		// Top card is at the last now
		return this.getCard(this.size() - 1);
		
	}
	
	
	/**
	 * A method for checking if this hand beats a specified hand.
	 * This method would be overridden by its subclasses. 
	 * 
	 * @param hand  A hand that is used for comparison
	 * @return  It returns true if beaten else it returns false
	 * 
	 */
	
	public boolean beats(Hand hand) {
		return true;
		
	}
	
	// ***************************************
	
	// ************* Abstract methods ***************
	
	/**
	 * A method for checking if this is a valid hand.
	 * 
	 * @return It returns true if the Hand is valid, otherwise false
	 * 
	 */
	
	abstract public boolean isValid();
	
	/**
	 * A method for returning a string specifying the type of this hand. 
	 * 
	 * @return A string dictating the type of the given hand
	 * 
	 */
	abstract public String getType();
	
	// ***************************************
}
