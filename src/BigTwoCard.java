/**
 * It is the subclass of Card class which is used to model a card used in Big Two 
 * card game.It inherits all the methods from the Card class and overrides compareTo() 
 * method so that it can reflect the ordering of cards used in a Big Two card game.
 * 
 * @author Masood Ahmed
 * @version 1.0
 * @see Card
 *
 */
@SuppressWarnings("serial")
public class BigTwoCard extends Card {

	// *********** constructor ***************
	/**
	 * A constructor for building a card with the specified suit and rank.
	 * suit is an integer between 0 to 3, and rank is an integer between 0 to 12.
	 * 
	 * @param suit An integer representing a suit with a value between 0 and 3
	 * @param rank An integer representing a rank with a value between 0 and 12
	 * 
	 */
	
	public BigTwoCard(int suit, int rank) {
		super(suit,rank);
	}
	// ***************************************
	
	// ************ Public Methods *************
	/**
	 * A method for comparing the order of this BigTwo card with the specified BigTwo card.
	 * Returns a negative integer, zero, or a positive integer when this card is 
	 * less than, equal to, or greater than the specified card.  
	 * 
	 * @param card The card that need to be compared with this card.
	 * @return A negative integer, zero, or a positive integer if this card is 
	 *         less than, equal to, or greater than the specified card.
	 *         
	 */
	
	@Override
	public int compareTo(Card card) {
		
		// if the card is an A or 2 
		if (this.rank == 0 || this.rank == 1) {
			
			// If other cards are from 3 to K 
			// then check which is greater
			
			if (card.rank > 1) {
				return 1;
			}
			
			// If other cards are A or 2 
			
			else { 
				// then check which is greater
				
				if (this.rank > card.rank) {
					return 1;
					}
				
				// if ranks are equal
				
				else if (this.rank == card.rank) {
					
					// if other card's suit is less then this card's suit
					if (this.suit > card.suit) {
						return 1;
					}
					
					// if suits are equal
					
					else if (this.suit == card.suit){
						return 0;
					}
					
					// if less than other card
					
					else {
						return -1;
					}
				}
				
				// if less than other card
				
				else {
					return -1;
				}
			}
		}
		// checking if the card given index in the range of 2 to 12
		else if (this.rank > 1 && card.rank > 1) {
			
			// check which rank is greater
			
			if (this.rank > card.rank) {
				return 1;
			}
			
			// if ranks are equal
			
			else if (this.rank == card.rank) {
				
				// if other card's suit is less then this card's suit
				
				if (this.suit > card.suit) {
					return 1;
				}
				
				// if suits are equal
				
				else if (this.suit == card.suit){
					return 0;
				}
				
				// if less than other card
				
				else {
					return -1;
				}
			}
			
			// if less than other card
			
			else {
				return -1;
			}
		}
		
		// if less than other card
		
		else {
			return -1;
		}
	}
	
	// ***************************************
}
