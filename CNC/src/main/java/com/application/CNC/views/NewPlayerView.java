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
import java.util.HashSet;
import java.util.Set;


@PermitAll
@Route(value = "newPlayerView")
@PageTitle("Create New Player")
public class NewPlayerView extends VerticalLayout {
    
    private final GameService gameService;
    private TextField nameField = new TextField();
    private ComboBox<Background> backgroundBox = new ComboBox<>("Background:");
    private ComboBox<Integer> levelBox = new ComboBox<>("Level:");
    private Button finishButton = new Button("create!");

    public NewPlayerView(GameService gameService) {
        this.gameService = gameService;
        addClassName("NewPlayerView");
        setSizeFull();
        configComponents();
        add(nameField);
        add(new HorizontalLayout(levelBox, backgroundBox));
        add(finishButton);
        //<theme-editor-local-classname>
        addClassName("new-player-view-vertical-layout-1");
    }

    private void configComponents() {
        nameField.setLabel("Player Name:");
        nameField.setClearButtonVisible(true);
        nameField.setPrefixComponent(VaadinIcon.BOOK.create());
        backgroundBox.setItems(EnumSet.allOf(Background.class));
        levelBox.setItems(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        finishButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        finishButton.addClickShortcut(Key.ENTER);
        finishButton.addClickListener(event -> {
            addPlayer();
            finishButton.getUI().ifPresent(ui -> 
                ui.navigate(CampaignView.class));
        });
    }

    private void addPlayer() {
        Player newplayer = new Player();
        newplayer.setName(nameField.getValue());
        newplayer.setBackground(backgroundBox.getValue());
        newplayer.setLevel(levelBox.getValue());
        newplayer.setInventory(getInitialInventory(newplayer.getBackground()));
        gameService.createPlayer(newplayer);
    }

    private List<Long> getInitialInventory(Background background) {
        switch (background) {
            case Blacksmith:
                return gameService.findItemIDsByNames(Arrays.asList("hammer", "apron"));
            case Actor:
                return gameService.findItemIDsByNames(Arrays.asList("dagger", "shirt"));
            default:
                return null;
        }
    }
}
