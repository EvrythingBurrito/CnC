package com.application.CNC.services;

import com.application.CNC.data.PlayerRepository;
import com.application.CNC.data.Player;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.tool.schema.TargetType;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.imageio.spi.ServiceRegistry;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;

@Service
public class gameService {

    private final PlayerRepository playerRepository;
    // private final NPCRepository -- contains all nonplayer entity data
    // private final landmarkRepository -- contains all landmarks
    // private final regionRepository -- contains all regions
    // note - encounters do not get stored in a repo

    public gameService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // PLAYER STUFF ////////////////////////////////////////////////////////////////////////

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

}
