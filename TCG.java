/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tcg;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;
import java.io.*;
/**
 *
 * @author kidco
 */

class Card implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String collection;
    private String name;
    private String type;
    private String rarity;
    private int amount;
    
    public Card(String collection, String name, String type, String rarity, int amount) {
        this.collection = collection;
        this.name = name;
        this.type = type;
        this.rarity = rarity;
        this.amount = amount;
    }
    
    @Override
    public String toString() {
        return "Collection: " + collection +
                "\nCard Name: " + name +
                "\nType: " + type +
                "\nRarity: " + rarity +
                "\nAmouunt Owned: " + amount + "\n";
    }
}
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
            System.out.println("*** Your Card Collection ***");
            cardCollection.forEach(card -> {
                System.out.println(card);
            });
        }
    }
}
