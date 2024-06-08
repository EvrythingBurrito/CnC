package com.application.CNC.services;

import com.application.CNC.data.PlayerRepository;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;

import com.application.CNC.data.Player;
import com.application.CNC.data.Item;
import com.application.CNC.data.ItemRepository;
import com.application.CNC.data.Campaign;
import com.application.CNC.data.CampaignRepository;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.schema.TargetType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import org.hibernate.collection.spi.PersistentSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.imageio.spi.ServiceRegistry;
import javax.sql.DataSource;

import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;

import java.nio.charset.Charset;
import java.io.*;

@Service
public class GameService {

    private final PlayerRepository playerRepository;
    private final ItemRepository itemRepository;
    private final CampaignRepository campaignRepository;

    public GameService(PlayerRepository playerRepository, ItemRepository itemRepository, CampaignRepository campaignRepository) {
        this.playerRepository = playerRepository;
        this.itemRepository = itemRepository;
        this.campaignRepository = campaignRepository;
    }

    // CAMPAIGN STUFF /////////////////////////////////////////////////////////////////////

    @Transactional
    public void createCampaign(Campaign campaign) {
        if (campaign == null) {
            System.err.println("campaign is null");
            return;
        }
        campaignRepository.save(campaign);
    }

    public List<Campaign> findAllCampaigns() {
        return campaignRepository.findAll();
    }

    public void removeCampaign(Campaign campaign) {
        campaignRepository.delete(campaign);
    }

    public int numCampaigns() {
        return (int) campaignRepository.count();
    }

    // PLAYER STUFF ////////////////////////////////////////////////////////////////////////

    @Transactional
    public void createPlayer(Player player) {
        if (player == null) {
            System.err.println("player is null.");
            return;
        }
        playerRepository.save(player);
    }

    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }

    public int numPlayers() {
        return (int) playerRepository.count();
    }

    // ITEM STUFF
    public void initializeItemsFromFile(String filePath) {
        try {
            List<String> allLines = Files.readAllLines(new File(filePath).toPath(), Charset.defaultCharset());
            List<Item> allItems = new ArrayList<Item>();
            for (String aLine : allLines) {
                String[] stringArray = aLine.split(" ");
                Item anItem = new Item(stringArray[0]);
                anItem.setPrice(Integer.valueOf(stringArray[1]));
                // all other initializing/parsing
                allItems.add(anItem);
            }
            addItems(allItems);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void addItems(List<Item> items) {
        for (Item item : items) {
            if (item != null) {
                itemRepository.save(item);
            }
        }
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public List<Long> findItemIDsByNames(List<String> names) {
        List<Long> itemList = new ArrayList<Long>();
        for (String name : names) {
            for (Item item : itemRepository.findByIdIn(itemRepository.getAllIds())) {
                if (item.getName() == name) {
                    itemList.add(item.getId());
                }
            }
        }
        return itemList;
    }

    public List<Item> findItemByID(List<Long> ids) {
        return itemRepository.findByIdIn(ids);
    }

}
