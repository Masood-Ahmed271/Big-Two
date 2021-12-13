
/**
 * The Flush class is a subclass of the Hand class. It is used to model a hand of Flush cards.
 * It has definitions for the abstract classes of Hand such as isValid() and getType(). It also
 * overrides the  beats( Hand hand) method from the Hand class to give its specific implementation.
 * 
 * In particular, this hand consists of five cards with the same suit. The card with the highest 
 * rank in a flush is referred to as the top card of this flush. A flush always beats any 
 * straights. A flush with a higher suit beats a flush with a lower suit. For flushes with 
 * the same suit, the one having a top card with a higher rank beats the one having a top 
 * card with a lower rank. 
 * 
 * @author Masood Ahmed
 * @version 1.0
 * @see Hand
 * 
 */
@SuppressWarnings("serial")
public class Flush extends Hand{
	
	// ************ creating a constructor ****************
	
	/**
	 * A constructor for building a Flush hand with the specified player and list of cards.
	 * 
	 * @param player The player who is playing this hand
	 * @param cards  The cards that will make combination for Flush hand
	 * 
	 */
	
	public Flush(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}	
	
	// ***************************************
	
	
	// ********* Public methods **************
	/**
	 * A method for checking if this hand beats a specified hand.
	 * 
	 * @param hand  A hand that is used for comparison
	 * @return  It returns true if beaten else it returns false
	 */
	
	@Override
	public boolean beats(Hand hand) {
		if (hand.getType() == "Straight") {
			return true;
		}
		else if (hand.getType() != "Straight" && hand.getType() != "Flush") {
			return false;
		}
		else if (hand.getType() == "Flush"){
			if (this.getTopCard().getSuit() > hand.getTopCard().getSuit()) {
				return true;
			}
			else if (this.getTopCard().getSuit() == hand.getTopCard().getSuit()) {
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		return false;
		
	}
	
	// ***************************************
	
	
	// ************* abstract classes implementations ****************
	
	/**
	 * A method for returning a string specifying the type of this hand. 
	 * 
	 * @return A string specifying that this is of Flush type hand
	 * 
	 */
	
	public String getType() {
		return "Flush";
	}
	
	/**
	 * A method for checking if this is a valid Flush hand.
	 * 
	 * @return It returns true if the Hand is valid, otherwise false
	 * 
	 */
	
	public boolean isValid() {
		
		
		// checking if all the cards of the same suit
		if (this.size() == 5) {
			for (int i = 0; i < this.size() - 1; i++) {
				if (this.getCard(i).getSuit() != this.getCard(i+1).getSuit()) {
					return false;
				}
			}
			return true;
		}
		
		return false;	
	}
	
	// ***************************************
}
