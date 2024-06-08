package com.application.CNC.views;

import org.hibernate.event.spi.DeleteEvent;

import com.application.CNC.views.GMMainView;
import com.application.CNC.data.Item;
import com.application.CNC.data.Player;
import com.application.CNC.services.GameService;
import com.application.CNC.data.Background;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;

import org.hibernate.collection.spi.PersistentSet;
import jakarta.annotation.security.PermitAll;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.SpringComponent;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;

@PermitAll
@Route(value = "playerMainView")
@PageTitle("player view")
public class PlayerMainView extends VerticalLayout implements HasUrlParameter<String> {

    private final GameService gameService;
    private String selectedPlayer;
    private Player player;

    @Override
    public void setParameter(BeforeEvent event, String Parameter) {
        selectedPlayer = Parameter;
    }

    public PlayerMainView(GameService gameService) {
        this.gameService = gameService;
        addClassName("PlayerMainView");
        setSizeFull();
        // set current player
        for (Player player : gameService.findAllPlayers()) {
            if (player.getName() == selectedPlayer) {
                this.player = player;
            }
        }
    }
    
}
