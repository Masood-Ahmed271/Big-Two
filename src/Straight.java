
/**
 * The Single class is a subclass of the Hand class. It is used to model a hand of Single cards.
 * It has definitions for the abstract classes of Hand such as isValid() and getType(). It also
 * overrides the  beats( Hand hand) method from the Hand class to give its specific implementation.
 * 
 * In particular, this hand consists of five cards with consecutive ranks. For the sake of 
 * simplicity, 2 and A can only form a straight with K but not with 3. The card with the 
 * highest rank in a straight is referred to as the top card of this straight. A straight 
 * having a top card with a higher rank beats a straight having a top card with a lower 
 * rank. For straights having top cards with the same rank, the one having a top card 
 * with a higher suit beats the one having a top card with a lower suit.
 * 
 * @author Masood Ahmed
 * @version 1.0
 * @see Hand
 * 
 */

@SuppressWarnings("serial")
public class Straight extends Hand{
	
	// *********** creating a constructor **************
	
	/**
	 * A constructor for building a Straight hand with the specified player and list of cards.
	 * 
	 * @param player The player who is playing this hand
	 * @param cards  The cards that will make combination for Straight hand
	 * 
	 */
	
	public Straight(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	// ***************************************
	
	// ************ Public Methods *************
	/**
	 * A method for checking if this hand beats a specified hand.
	 * 
	 * @param hand  A hand that is used for comparison
	 * @return  It returns true if beaten else it returns false
	 * 
	 */
	
	@Override
	public boolean beats(Hand hand) {
		// straight is the weakest of all the other 5 cards
		if (hand.getType() != "Straight") {
			return false;
		}
		else {
			if(this.getTopCard().compareTo(hand.getTopCard()) == 1) {
				return true;
			}
		}
		
		return false;
	}
	// ***************************************
	
	
	// ************** abstract classes implementations **************
	
	/**
	 * A method for returning a string specifying the type of this hand. 
	 * 
	 * @return A string specifying that this is of Straight type hand
	 * 
	 */
	
	public String getType() {
		return "Straight";
	}
	
	/**
	 * A method for checking if this is a valid Straight hand.
	 * 
	 * @return It returns true if the Hand is valid, otherwise false
	 * 
	 */
	
	public boolean isValid() {
		
		this.sort();
		
		// Checking the rank of the cards that are close by
		// adding 11 to the rank index and then dividing by 13 and checking for reminder
		// just shifts the card ahead so that 2 and A ranked cards can be checked.
		// cards would shift as follows:
		// 'A' = 11, '2' = 12, '3' = 0, '4' = 1, '5' = 2, '6' = 3, '7' = 4, '8' = 5, '9' = 6,
		// '10' = 7, 'J' = 8, 'Q' = 9, 'K' = 10
		// so order would be like;
		// cards:   3,4,5,6,7,8,9,10,J,Q, K, A, 2
		// indexes: 0,1,2,3,4,5,6, 7,8,9,10,11,12
		if (this.size() == 5) {
			for (int i = 0; i < this.size() - 1; i++) {
				if (((this.getCard(i).getRank() + 11) % 13) - ((this.getCard(i+1).getRank() + 11) % 13) != -1) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	// ***************************************
}
