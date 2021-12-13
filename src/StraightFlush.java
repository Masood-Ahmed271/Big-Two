
/**
 * The Single class is a subclass of the Hand class. It is used to model a hand of Single cards.
 * It has definitions for the abstract classes of Hand such as isValid() and getType(). It also
 * overrides the  beats( Hand hand) method from the Hand class to give its specific implementation.
 * 
 * In particular, this hand consists of five cards with consecutive ranks and the same 
 * suit. For the sake of simplicity, 2 and A can only form a straight flush with K but not 
 * with 3. The card with the highest rank in a straight flush is referred to as the top card 
 * of this straight flush. A straight flush always beats any straights, flushes, full houses, 
 * and quads. A straight flush having a top card with a higher rank beats a straight flush 
 * having  a  top  card  with  a  lower  rank.  For  straight  flushes  having  top  cards  with  the 
 * same rank, the one having a top card with a higher suit beats one having a top card 
 * with a lower suit.
 * 
 * @author Masood Ahmed
 * @version 1.0
 * @see Hand
 * 
 */

@SuppressWarnings("serial")
public class StraightFlush extends Hand{
	
	// ************ creating a constructor  **************
	
	/**
	 * A constructor for building a StraightFlush hand with the specified player and list of cards.
	 * 
	 * @param player The player who is playing this hand
	 * @param cards  The cards that will make combination for StraightFlush hand
	 * 
	 */
	
	public StraightFlush(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}	

	// ***************************************
	
	// ********* Public Methods ****************
	/**
	 * A method for checking if this hand beats a specified hand.
	 * 
	 * @param hand  A hand that is used for comparison
	 * @return  It returns true if beaten else it returns false
	 */
	
	@Override
	public boolean beats(Hand hand) {
		if (hand.getType() == "Straight" || hand.getType() == "Flush" || hand.getType() == "FullHouse"
				|| hand.getType() == "Quad") {
			return true;
		}
		else if ( hand.getType() == "StraightFlush") {
			if (this.getTopCard().compareTo(hand.getTopCard()) == 1) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
		
	}
	
	// ***************************************
	
	
	// ************ abstract classes implementations ************
	
	/**
	 * A method for returning a string specifying the type of this hand. 
	 * 
	 * @return A string specifying that this is of StraightFlush type hand
	 * 
	 */
	
	public String getType() {
		return "StraightFlush";
	}
	
	/**
	 * A method for checking if this is a valid StraightFlush hand.
	 * 
	 * @return It returns true if the Hand is valid, otherwise false
	 * 
	 */
	public boolean isValid() {
		this.sort();
		
		if (this.size() == 5) {
			int initialSuit = this.getCard(0).getSuit();
			for (int i = 0; i < this.size(); i++) {
				if (initialSuit != this.getCard(i).getSuit()) {
					return false;
				}
			}
			for (int i = 0; i < 4; i++) {
				if ((this.getCard(i+1).getRank()+11)%13 - (this.getCard(i).getRank()+11)%13 != 1 ){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	// ***************************************
	
}
