
/**
 * The Quad class is a subclass of the Hand class. It is used to model a hand of Quad cards.
 * It has definitions for the abstract classes of Hand such as isValid() and getType(). It also
 * overrides the  beats( Hand hand) method from the Hand class to give its specific implementation.
 * 
 * In particular, this hand consists of five cards, with four having the same rank. The card in 
 * the quadruplet with the highest suit in a quad is referred to as the top card of this quad. 
 * A quad always beats any straights, flushes, and full houses. A quad having a top card 
 * with a higher rank beats a quad having a top card with a lower rank.
 * 
 * @author Masood Ahmed
 * @version 1.0
 * @see Hand
 * 
 */
@SuppressWarnings("serial")
public class Quad extends Hand{
	
	// *********** creating a constructor ************
	
	/**
	 * A constructor for building a Quad hand with the specified player and list of cards.
	 * 
	 * @param player The player who is playing this hand
	 * @param cards  The cards that will make combination for Quad hand
	 * 
	 */
	
	public Quad(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	// ***************************************
	
	// ********* Public Methods **********
	/**
	 * This method will retrieve the top card for Quad hand. This method is being 
	 * overridden because top card of quad has a separate implementation.
	 * 
	 * @return It returns the top card of the Quad hand
	 * 
	 */
	
	@Override
	public Card getTopCard() {
		// test this thoroughly
		
		this.sort();
		int topSuitValue = 0;
		//assuming same ranks are at the first 4 position of the hand
		if (this.getCard(0).getRank()==this.getCard(1).getRank()) {
			int topSuit = this.getCard(0).getSuit();
			topSuitValue = 0;
			for (int i = 1; i < 4; i++) {
				if (this.getCard(i).getSuit() > topSuit) {
					topSuit = this.getCard(i).getSuit();
					topSuitValue = i;
				}
			}
			return this.getCard(topSuitValue);
		}
		else if (this.getCard(1).getRank()==this.getCard(2).getRank()){
			int topSuit = this.getCard(1).getSuit();
			topSuitValue = 1;
			for (int i = 2; i < 5; i++) {
				if (this.getCard(i).getSuit() > topSuit) {
					topSuit = this.getCard(i).getSuit();
					topSuitValue = i;
				}			
	        }
			return this.getCard(topSuitValue);
		}
		return this.getCard(topSuitValue);
	}

	/**
	 * A method for checking if this hand beats a specified hand.
	 * 
	 * @param hand  A hand that is used for comparison
	 * @return  It returns true if beaten else it returns false
	 */
	
	@Override
	public boolean beats(Hand hand) {
		if (hand.getType() == "StraighFlush") {
			return false;
		}
		else if (hand.getType() == "Quad") {
			if(this.getTopCard().compareTo(hand.getTopCard()) == 1) {
				return true;
			}
			else {
				return false;
			}
		}
		else if (hand.getType() == "Straight" || hand.getType() == "Flush" || hand.getType() == "FullHouse"){
			return true;
		}
		
		return false;
		
	}
	
	// ***************************************
	
	// ************ abstract classes implementations ***********
	
	/**
	 * A method for returning a string specifying the type of this hand. 
	 * 
	 * @return A string specifying that this is of Quad type hand
	 * 
	 */
	
	public String getType() {
		return "Quad";
	}
	
	/**
	 * A method for checking if this is a valid Quad hand.
	 * 
	 * @return It returns true if the Hand is valid, otherwise false
	 * 
	 */
	public boolean isValid() {
		
		if (this.size() == 5) {
			int cardRank = -1;
			int countRank = 0;
			if (this.getCard(0).getRank() == this.getCard(1).getRank()) {
				cardRank = this.getCard(0).getRank();
			}
			else if (this.getCard(2).getRank() == this.getCard(1).getRank()) {
				cardRank = this.getCard(1).getRank();
			}
			else {
				return false;
			}
			for (int i = 0; i < this.size(); i++) {
				if (this.getCard(i).getRank() == cardRank) {
					countRank++;
				}
			}
			
			if (countRank == 4) {
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
