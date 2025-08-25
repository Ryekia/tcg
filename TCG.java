//Added a couple more features.

package tcg;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;
import java.io.*;
import java.util.Comparator;
/**
 *
 * @author kidco
 */

public class TCG {
    private static final String FILE_NAME = "cards.json";

    private static void saveCardsToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            out.writeObject(cardCollection);
            System.out.println("Cards Saved.");
        } catch (IOException e ) {
            System.out.println("Error saving cards: " + e.getMessage());
        }
    }
    
    private static void loadCardsFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            cardCollection = (ArrayList<Card>) in.readObject();
            System.out.println("Cards loaded successfully.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved cards");
        }
    }

    private static ArrayList<Card> cardCollection = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    
    public static void main(String[] args) {
        loadCardsFromFile();
                
        int option;
        
        do {
            System.out.println("*** Card List ***");
            System.out.println("1. Add a Card");
            System.out.println("2. List all Cards");
            System.out.println("3. Delete Cards");
            System.out.println("4. Delete a card by name");
            System.out.println("0. Exit App");
            System.out.println("Make a choice");
            
            option = scanner.nextInt();
            scanner.nextLine();
            
            switch (option) {
                case 1 -> {
                    addCard();
                    break;
                }
                case 2 -> {
                    viewCards();
                    break;
                }
                case 3 -> {
                    deleteCard();
                    break;
                }
                case 4 -> {
                    deleteCardByName();
                    break;
                }
                case 0 -> {
                    saveCardsToFile();
                    System.out.println("Closing");
                    
                }
                default -> System.out.println("Invalid Choice");
                  
            }
        } while (option != 0);
        
        scanner.close();
    }
    
    private static void addCard() {
        System.out.println("Enter Collection name: ");
        String collection = scanner.nextLine();
        
        System.out.println("Enter Card name: ");
        String name = scanner.nextLine();
        
        System.out.println("Enter card type: ");
        String type = scanner.nextLine();
        
        System.out.println("Enter card rarity: ");
        String rarity = scanner.nextLine();
        
        System.out.println("Enter the amount of cards owned: ");
        int amount = scanner.nextInt();
        
        Card card = new Card(collection, name, type, rarity, amount);
        cardCollection.add(card);
        System.out.println("Card added successfully");
    }
    
    private static void viewCards() {
        if (cardCollection.isEmpty()){
            System.out.println("No cards are saved yet. ");
        } else {
            cardCollection.sort(Comparator.comparing(Card::getName, String.CASE_INSENSITIVE_ORDER));
            
            System.out.println("*** Your Card Collection ***");
            cardCollection.forEach(card -> {
                System.out.println(card);
            });
        }
    }
    
    private static void deleteCard() {
        if (cardCollection.isEmpty()) {
            System.out.println("There are no cards to delete");
            return;
        }
        
        System.out.println("Delete card");
        for (int i = 0; i < cardCollection.size(); i++) {
            System.out.println((i + 1) + ". " + cardCollection.get(i).toString()); 
        }
        
        System.out.print("Enter the number of card to delete: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < cardCollection.size()) {
                Card removed = cardCollection.remove(index);
                System.out.println("Deleted card: " + removed.toString());
            } else {
                System.out.println("Invalid card.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid number. ");
        }
    }
    
    private static void deleteCardByName() {
        if(cardCollection.isEmpty()) {
            System.out.println("No cards. ");
            return;
        }
        
        System.out.print("Enter the name of the card you wanted deleted: ");
        String nameToDelete = scanner.nextLine().trim();
        
        boolean found = false;
        for (int i = 0; i <cardCollection.size();i++) {
            Card card = cardCollection.get(i);
            if (card.toString().toLowerCase().contains(nameToDelete.toLowerCase())) {
                cardCollection.remove(i);
                System.out.println("Deleted card: " + card.toString());
                found = true;
                break;
            }
        }
        
        if(!found){
            System.out.println("No card found");
        }
    }
}
