/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tcg;

import java.io.Serializable;

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
    
    public String getName() {
        return name;
    }
}
