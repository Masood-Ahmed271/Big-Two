
/**
 * The Pair class is a subclass of the Hand class. It is used to model a hand of Pair cards.
 * It has definitions for the abstract classes of Hand such as isValid() and getType(). It also
 * overrides the  beats( Hand hand) method from the Hand class to give its specific implementation.
 * 
 * In particular, this hand consists of two cards with the same rank. The card with a higher suit 
 * in a pair is referred to as the top card of this pair. A pair with a higher rank beats a 
 * pair with a lower rank. For pairs with the same rank, the one containing the highest 
 * suit beats the other.
 * 
 * @author Masood Ahmed
 * @version 1.0
 * @see Hand
 * 
 */

@SuppressWarnings("serial")
public class Pair extends Hand {
	
	// *********** creating a constructor ***************

	/**
	 * A constructor for building a Pair hand with the specified player and list of cards.
	 * 
	 * @param player The player who is playing this hand
	 * @param cards  The cards that will make combination for Pair hand
	 * 
	 */
	
	public Pair(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}	
	
	// ***************************************
	
	
	// ********** Public Methods *************
	/**
	 * A method for checking if this hand beats a specified hand.
	 * 
	 * @param hand  A hand that is used for comparison
	 * @return  It returns true if beaten else it returns false
	 */
	
	@Override
	public boolean beats(Hand hand) {

		if ( hand.getType() == "Pair" ) {
			if (this.getTopCard().compareTo(hand.getTopCard()) == 1) {
				return true;
			}
		}
		
		return false;
		
	}
	
	// ***************************************
	
	// ************* abstract classes implementations   **************
	
	/**
	 * A method for returning a string specifying the type of this hand. 
	 * 
	 * @return A string specifying that this is of Pair type hand
	 * 
	 */
	
	public String getType() {
		return "Pair";
	}
	
	/**
	 * A method for checking if this is a valid Pair hand.
	 * 
	 * @return It returns true if the Hand is valid, otherwise false
	 * 
	 */
	public boolean isValid() {

		// A pair would be valid if it's size is 2 and has same rank
		if (this.size() == 2 && this.getCard(0).getRank() == this.getCard(1).getRank()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// ***************************************
}
