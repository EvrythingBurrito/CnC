package com.application.CNC.data;

import com.application.CNC.data.Item;
import com.application.CNC.data.Background;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;

@Entity
public class Player extends AbstractEntity {
    @NotBlank
    private String name;
    private Background background;
    private Integer level;
    // private Stats stats;
    @OneToMany
    private ArrayList<Item> inventory;

    // GETTERS/SETTERS
    public String getName() {
        if (name == null) {
            return new String("error - no name");
        }
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
    }
    public Background getBackground() {
        return background;
    }
    public void setBackground(Background background) {
        this.background = background;
    }

    // INVENTORY STUFF

    // Fixme - this may cause error if Item is not implemented as Serializable
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public void addToInventory(Item newItem) {
        this.inventory.add(newItem);
    }
    public boolean removeFromInventory(Item anItem) {
        if (this.inventory.indexOf(anItem) != -1) {
            this.inventory.remove(anItem);
            return true;
        } else {
            return false;
        }
    }

    
}