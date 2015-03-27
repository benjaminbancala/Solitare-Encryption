package solitaire;

import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.NoSuchElementException;

/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * @author RU NB CS112
 */
public class Solitaire {
	
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;
	
	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	void jokerA() {
		 if(deckRear == null)
		      throw new NoSuchElementException();
		CardNode front=deckRear.next;
		for(CardNode ptr = deckRear.next,prev =ptr;prev!=deckRear;prev=ptr,ptr=ptr.next){
			if(ptr.cardValue==27){
				if(ptr.cardValue==front.cardValue){
					deckRear.next=front.next;
					front.next=front.next.next;
					deckRear.next.next=front;
				}
				else if(ptr.cardValue==deckRear.cardValue){
					deckRear=deckRear.next;
					prev.next=ptr.next;
					ptr.next=ptr.next.next;
					prev.next.next=ptr;
					
				}else{
				prev.next=ptr.next;
				ptr.next=ptr.next.next;
				prev.next.next=ptr;
				
			}
			
		}
		}

	}
	
	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
		 if(deckRear == null)
		      throw new NoSuchElementException();
		CardNode front=deckRear.next;
		for(CardNode ptr = deckRear.next,prev =ptr;prev!=deckRear;prev=ptr,ptr=ptr.next){
			if(ptr.cardValue==28){
				if(ptr.cardValue==front.cardValue){
					deckRear.next=front.next;					
					ptr.next=ptr.next.next.next;					
					deckRear.next.next.next=front;
					
					
				}else if(ptr.next==deckRear){
					ptr.next=front.next;
					front.next=ptr;
					prev.next=deckRear;
				}
				else if(ptr.cardValue==deckRear.cardValue){
					deckRear=deckRear.next;
					front=front.next;
					ptr.next=front;
					deckRear.next=ptr;
					prev.next=deckRear;
					front=ptr;
					
					front=front.next;
					ptr.next=front.next;
					front.next=ptr;
					deckRear.next=front;
					
					
					
					
					
				}else{
			
					prev.next=ptr.next;					
					ptr.next=ptr.next.next.next;					
					prev.next.next.next=ptr;
					
					
			}	
			}
			
	}

	}
	
	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
		   if(deckRear == null)
			      throw new NoSuchElementException();
		   		 CardNode temp;
		   		 CardNode temp2;
			  	 CardNode front = deckRear.next;
			     CardNode prev = deckRear.next;
			     CardNode ptr = deckRear.next.next;
			 
			  if(front.cardValue == 27 || front.cardValue == 28) {
			   while(ptr != deckRear){
			    if(deckRear.cardValue == 27 || deckRear.cardValue == 28){
			    }
			    
			    else if(ptr.cardValue == 27 || ptr.cardValue == 28){
			     temp = ptr;
			     temp2 = ptr.next;
			     deckRear = temp;
			     deckRear.next = temp2;
			     
			    }else{
			     prev = prev.next;
			    } 
			    ptr = ptr.next;
			   }
			   
			  }
			  
			  else if (deckRear.cardValue == 27 || deckRear.cardValue == 28)
			  {
			   prev = deckRear;
			   ptr = deckRear.next;
			   
			   while(ptr != deckRear){
			    if(front.cardValue == 27 || front.cardValue == 28){
			    }
			    
			    else if(ptr.cardValue == 27 || ptr.cardValue == 28){
			     
			      temp = ptr;
			      temp2 = prev;
			     deckRear.next = front;
			     deckRear = temp2;
			     deckRear.next = temp;
			     
			    }
			    else{
			     prev = prev.next;
			    }
			    ptr = ptr.next;
			   }
			  }
			  else{
			   
			   prev = deckRear;
			   ptr = prev.next;
			   
			   while(ptr != deckRear){
			    if(ptr.cardValue == 27 || ptr.cardValue == 28){
			     CardNode jone = ptr;
			     CardNode ptr2 = ptr.next;
			     while(ptr2 != deckRear.next){
			      if(ptr2.cardValue == 27 || ptr2.cardValue == 28){
			       CardNode jtwo = ptr2;
			       CardNode ptr3 = jtwo.next; //pointer2.next
			        front = deckRear.next;
			       deckRear.next = jone;
			       jtwo.next = front;
			       deckRear = prev;
			       deckRear.next = ptr3;
			       return;
			      }
			      else{
			       ptr2 = ptr2.next;
			      }
			     }
			     
			    }
			    else{
			     prev = prev.next;
			     ptr = ptr.next;
			    }
			   }
			  }
	}
	
	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {		
		 if(deckRear == null || deckRear.next == null){
		      throw new NoSuchElementException();
		 }
		 int end;

		  if(deckRear.cardValue == 28){
		   end = 27;
		  }
		  else {
		   end = deckRear.cardValue;
		  }
		  CardNode front = deckRear.next;
		  CardNode mid = new CardNode();
		  CardNode prev = deckRear;
		  CardNode emid = new CardNode();
		  CardNode ptr = deckRear.next;
		  CardNode ec = new CardNode();
		  

		  int x = 0;

		  while(x <= end) {
		   if(x== end-1){
		    if(ptr.next == deckRear) {
		     return;
		    }

		    ec = ptr;
		    mid = ec.next;
		    
		    CardNode ptr2 = ptr.next;
		    while(ptr2 != deckRear){
		     if(ptr2.next == deckRear){
		      emid = ptr2;
		      emid.next = null;
		      ptr2.next = front;
		      ec.next = deckRear;
		      deckRear.next = mid;
		      return;
		     }
		     ptr2 = ptr2.next;
		    }
		   }
		   ptr = ptr.next;
		   prev = prev.next;
		   x++;
		  }
		 }
	
	
        /**
         * Gets a key. Calls the four steps - Joker A, Joker B, Triple Cut, Count Cut, then
         * counts down based on the value of the first card and extracts the next card value 
         * as key, but if that value is 27 or 28, repeats the whole process until a value
         * less than or equal to 26 is found, which is then returned.
         * 
         * @return Key between 1 and 26
         */
	int getKey() {
		int done=0;
		jokerA();
		jokerB();
		tripleCut();
		countCut();
		int count =deckRear.next.cardValue;
		if (count==28){
			count=27;
		}
		CardNode key = deckRear.next;
		while(count!=0){
			key=key.next;
			count--;
		}
		if(key.cardValue<27&&key.cardValue>0){
	    done=key.cardValue;
		}else{
			getKey();
		}
		return done;
	
	}
	
	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	private static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {	
		char letter;
		int c;
		int encrypt;
		char x;
		int d;
		String output="";
		for (int i =0; i<message.length();i++){
			letter = message.charAt(i);
			if (Character.isLetter(letter)==true){
				if (Character.isUpperCase(letter)==false){
				
				}else{
				c=letter-'A'+1; 
				d=getKey();
				encrypt=c+d;
					if(encrypt>26){
					encrypt=encrypt-26;	
					}
					x=(char)(encrypt+64);
					output=output+x;
				}
		}
		}
	    return output;

	}
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	
			char letter;
			int c;
			int decrypt=0;
			int d;
			char x;
			String output="";
			for (int i =0; i<message.length();i++){
				letter = message.charAt(i);
					if (Character.isLetter(letter)==true){
						if(Character.isUpperCase(letter)==false){
						
							}else{
								c=letter-'A'+1;
								d=getKey();
								
								if(c-d<=0){
									c=c+26;
									decrypt=c-d;
								}else{
									decrypt=c-d;
								}
								
								x=(char)(decrypt+64);
								output=output+x;	
							}
					}
			
	}
			return output;
	}

	
}
