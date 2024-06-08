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

import org.hibernate.collection.spi.PersistentSet;

@Entity
public class Campaign extends AbstractEntity {
    @NotBlank
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
