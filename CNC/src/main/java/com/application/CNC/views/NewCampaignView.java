package com.application.CNC.views;

import org.hibernate.event.spi.DeleteEvent;

import com.application.CNC.views.GMMainView;
import com.application.CNC.data.Item;
import com.application.CNC.data.Player;
import com.application.CNC.data.Campaign;
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

@PermitAll
@Route(value = "newCampaign") // <1>
@PageTitle("New Campaign")
public class NewCampaignView extends VerticalLayout {
    private final GameService gameService;
    private TextField nameField = new TextField();
    private Button finishButton = new Button("create!");

    public NewCampaignView(GameService gameService) { // <2>
        this.gameService = gameService;
        addClassName("LoginView");
        setSizeFull();

        // initialize game item lists
        gameService.initializeItemsFromFile("data/weapons.txt");
        gameService.initializeItemsFromFile("data/apparel.txt");
        // gameService.initializeItemsFromFile("data/custom_items.txt");

        add(nameField);
        add(finishButton);
        configComponents();
        //<theme-editor-local-classname>
        // addClassName("new-player-view-vertical-layout-1");
    }

    private void configComponents() {
        nameField.setLabel("Campaign Name:");
        nameField.setClearButtonVisible(true);
        nameField.setPrefixComponent(VaadinIcon.BOOK.create());
        finishButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        finishButton.addClickShortcut(Key.ENTER);
        finishButton.addClickListener(event -> {
            addCampaign();
            finishButton.getUI().ifPresent(ui -> 
                ui.navigate(MainMenuView.class));
        });
    }

    private void addCampaign() {
        Campaign newCampaign = new Campaign();
        newCampaign.setName(nameField.getValue());
        gameService.createCampaign(newCampaign);
    }

}
