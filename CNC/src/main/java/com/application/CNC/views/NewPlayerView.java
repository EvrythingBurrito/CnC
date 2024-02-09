package com.application.CNC.views;

import org.hibernate.event.spi.DeleteEvent;

import com.application.CNC.views.GM_MainView;
import com.application.CNC.data.Item;
import com.application.CNC.data.Player;
import com.application.CNC.services.gameService;
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

import jakarta.annotation.security.PermitAll;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.SpringComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;

@PermitAll
@Route(value = "create_player")
@PageTitle("Create New Player")
public class NewPlayerView extends VerticalLayout {
    
    private final gameService service;
    private TextField nameField = new TextField();
    private ComboBox<Background> backgroundBox = new ComboBox<>("Background:");
    private ComboBox<Integer> levelBox = new ComboBox<>("Level:");
    private Button finishButton = new Button();

    public NewPlayerView(gameService service) {
        this.service = service;
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
        finishButton.addClickListener(event -> addPlayer());
    }

    private void addPlayer() {
        Player newplayer = new Player();
        newplayer.setName(nameField.getValue());
        newplayer.setBackground(backgroundBox.getValue());
        newplayer.setLevel(levelBox.getValue());
        service.createPlayer(newplayer);
    }
}
