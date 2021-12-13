
/**
 * The FullHouse class is a subclass of the Hand class. It is used to model a hand of FullHouse cards.
 * It has definitions for the abstract classes of Hand such as isValid() and getType(). It also
 * overrides the  beats( Hand hand) method from the Hand class to give its specific implementation.
 * 
 * In particular, this hand consists of five cards, with two having the same rank and three 
 * having another same rank. The card in the triplet with the highest suit in a full house 
 * is referred to as the top card of this full house. A full house always beats any straights 
 * and  flushes.  A  full  house  having  a  top  card  with  a  higher  rank  beats  a  full  house 
 * having a top card with a lower rank.
 * 
 * @author Masood Ahmed
 * @version 1.0
 * @see Hand
 * 
 */

@SuppressWarnings("serial")
public class FullHouse extends Hand{

	// ************ creating a constructor **************
	
	/**
	 * A constructor for building a FullHouse hand with the specified player and list of cards.
	 * 
	 * @param player The player who is playing this hand
	 * @param cards  The cards that will make combination for FullHouse hand
	 * 
	 */
	
	public FullHouse(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}	
	
	// ***************************************
	
	// ********* Public Methods *****************
	
	/**
	 * This method will retrieve the top card for FullHouse hand. This method is being 
	 * overridden because top card of FullHouse has a separate implementation.
	 * 
	 * @return It returns the top card of the FullHouse hand
	 * 
	 */
	
	@Override
	public Card getTopCard() {
		
		
		this.sort();
		
		if (this.getCard(2).getRank() == this.getCard(0).getRank()) {
			int topSuit = this.getCard(0).getSuit();
			int value = 0;
			for (int i = 1; i < 3; i++) {
				if (this.getCard(i).getSuit() > topSuit) {
					topSuit = this.getCard(i).getSuit();
					value = i;
				}
			}
			return this.getCard(value);
		}
		else {
			int topSuit = this.getCard(2).getSuit();
			int value = 2;
			for (int j = 3; j < 5; j++ ) {
				if (this.getCard(j).getSuit() > topSuit) {
					topSuit = this.getCard(j).getSuit();
					value = j;
				}
			}
			return this.getCard(value);
		}
	}
	
	/**
	 * A method for checking if this hand beats a specified hand.
	 * 
	 * @param hand  A hand that is used for comparison
	 * @return  It returns true if beaten else it returns false
	 */
	
	@Override
	public boolean beats(Hand hand) {
		if (hand.getType() == "Straight" || hand.getType() == "Flush" ) {
			return true;
		}
		else if (hand.getType() != "Straight" && hand.getType() != "Flush" && hand.getType() != "FullHouse") {
			return false;
		}
		else if (hand.getType() == "FullHouse"){
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
	
	
	// ************** abstract classes implementations **************
	
	/**
	 * A method for returning a string specifying the type of this hand. 
	 * 
	 * @return A string specifying that this is of FullHouse type hand
	 * 
	 */
	
	public String getType() {
		return "FullHouse";
	}
	
	/**
	 * A method for checking if this is a valid FullHouse hand.
	 * 
	 * @return It returns true if the Hand is valid, otherwise false
	 * 
	 */
	public boolean isValid() {
		
		int countOne = 0;
		int countTwo = 0;
		int rankOne = this.getCard(0).getRank();
		int rankTwo = -1;
		for (int i = 1; i < this.size(); i++) {
			if (this.getCard(i).getRank() != rankOne) {
				rankTwo = this.getCard(i).getRank();
				break;
			}
		}
		
		if (rankTwo == -1 ) {
			return false;
		}
		
		if (this.size() == 5) {
			for (int j = 0; j < this.size(); j++) {
				if (this.getCard(j).getRank() == rankOne) {
					countOne++;
				}
				else if (this.getCard(j).getRank() == rankTwo) {
					countTwo++;
				}
			}
			
			if ((countTwo == 2 && countOne == 3) || (countTwo == 3 && countOne == 2)) {
				return true;
			}
			else {
				return false;
			}
		}
		
		return false;
	}
	
	// ***************************************
}
