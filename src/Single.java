/**
 * The Single class is a subclass of the Hand class. It is used to model a hand of Single cards.
 * It has definitions for the abstract classes of Hand such as isValid() and getType(). It also
 * overrides the  beats( Hand hand) method from the Hand class to give its specific implementation.
 * 
 * In particular, this hand consists of only one single card.The only card in a single is 
 * referred to as the top card of this single.A single with a higher rank beats a single 
 * with a lower rank.For singles with the same rank, the one with a higher suit beats the 
 * one with a lower suit.
 * 
 * @author Masood Ahmed
 * @version 1.0
 * @see Hand
 * 
 */

@SuppressWarnings("serial")
public class Single extends Hand{
	
	// *********** A constructor *********
	
	/**
	 * A constructor for building a Single hand with the specified player and list of cards.
	 * 
	 * @param player The player who is playing this hand
	 * @param cards  The cards that will make combination for Single hand
	 * 
	 */
	
	public Single(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	// ***************************************
	
	// ******** Public Methods *************
	/**
	 * A method for checking if this hand beats a specified hand.
	 * 
	 * @param hand  A hand that is used for comparison
	 * @return  It returns true if beaten else it returns false
	 */
	
	@Override
	public boolean beats(Hand hand) {
		
		if ( hand.getType() == "Single" ) {
			if (this.getTopCard().compareTo(hand.getTopCard()) == 1) {
				return true;
			}
		}
		
		return false;
	}
	
	// ***************************************
	
	// *********** abstract classes implementations  *************
	
	/**
	 * A method for returning a string specifying the type of this hand. 
	 * 
	 * @return A string specifying that this is of Single type hand
	 * 
	 */
	
	public String getType() {
		return "Single";
	}
	

	/**
	 * A method for checking if this is a valid Single hand.
	 * 
	 * @return It returns true if the Hand is valid, otherwise false
	 * 
	 */
	
	public boolean isValid() {
		
		// the single card would be valid if it is only 1 
		
		if (this.size() == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// ***************************************

}
