
/**
 * The Triple class is a subclass of the Hand class. It is used to model a hand of Triple cards.
 * It has definitions for the abstract classes of Hand such as isValid() and getType(). It also
 * overrides the  beats( Hand hand) method from the Hand class to give its specific implementation.
 * 
 * In particular, this hand consists of three cards with the same rank. The card with the 
 * highest suit in a triple is referred to as the top card of this triple. A triple with a
 * higher rank beats a triple with a lower rank. 
 * 
 * @author Masood Ahmed
 * @version 1.0
 * @see Hand
 * 
 */

@SuppressWarnings("serial")
public class Triple extends Hand{
	
	// ********** creating a constructor **************

	/**
	 * A constructor for building a Triple hand with the specified player and list of cards.
	 * 
	 * @param player The player who is playing this hand
	 * @param cards  The cards that will make combination for Triple hand
	 * 
	 */
	
	public Triple(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}	
	
	// ***************************************
	
	// ************ Public Methods ************
	
	/**
	 * A method for checking if this hand beats a specified hand.
	 * 
	 * @param hand  A hand that is used for comparison
	 * @return  It returns true if beaten else it returns false
	 */
	
	@Override
	public boolean beats(Hand hand) {

		if ( hand.getType() == "Triple" ) {
			if (this.getTopCard().compareTo(hand.getTopCard()) == 1) {
				return true;
			}
		}
		
		return false;
		
	}
	// ***************************************
	
	
	// ********** abstract classes implementations **********
	
	/**
	 * A method for returning a string specifying the type of this hand. 
	 * 
	 * @return A string specifying that this is of Triple type hand
	 * 
	 */
	
	public String getType() {
		return "Triple";
	}
	
	/**
	 * A method for checking if this is a valid Triple hand.
	 * 
	 * @return It returns true if the Hand is valid, otherwise false
	 * 
	 */
	public boolean isValid() {

		// This would be only valid if the size of hand is 3 and has same rank
		if (this.size() == 3 && this.getCard(0).getRank() == this.getCard(1).getRank() ) {
			if (this.getCard(1).getRank() == this.getCard(2).getRank() ) {
				
				return true;
			
			}
		}
		return false;
	}
	
	// ***************************************
}
