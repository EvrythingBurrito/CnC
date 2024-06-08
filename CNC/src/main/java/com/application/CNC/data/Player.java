package com.application.CNC.data;

import com.application.CNC.data.Item;
import com.application.CNC.data.Background;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import java.util.Set;
// import org.hibernate.collection.spi.PersistentSet;

@Entity
public class Player extends AbstractEntity {
    @NotBlank
    private String name;
    // @Enumerated(EnumType.STRING)
    private Background background;
    private Integer level;
    // private Stats stats;
    // items are saved by ID
    private List<Long> inventory;

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
    public List<Long> getInventory() {
        return inventory;
    }

    public void setInventory(List<Long> anInventory) {
        this.inventory = anInventory;
    }

    public boolean addToInventory(Long newItem) {
        return this.inventory.add(newItem);
    }

    public boolean removeFromInventory(Long anItem) {
        if (this.inventory.contains(anItem)) {
            this.inventory.remove(anItem);
            return true;
        } else {
            return false;
        }
    }

    
}