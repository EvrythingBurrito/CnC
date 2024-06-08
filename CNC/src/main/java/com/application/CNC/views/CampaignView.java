package com.application.CNC.views;

import org.hibernate.event.spi.DeleteEvent;

import com.application.CNC.views.GMMainView;
import com.application.CNC.data.Item;
import com.application.CNC.data.Player;
import com.application.CNC.data.Campaign;
import com.application.CNC.services.GameService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.shared.ui.*;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.SpringComponent;
import java.util.ArrayList;


@PermitAll
@Route(value = "campaignView") // <1>
@PageTitle("Campain Main Menu")
public class CampaignView extends VerticalLayout implements HasUrlParameter<String> {
    private final GameService gameService;
    private String selectedCampaign;
    private Campaign campaign;
    private Button deleteCampaignButton = new Button("Delete Campaign");

    @Override
    public void setParameter(BeforeEvent event, String Parameter) {
        selectedCampaign = Parameter;
    }

    public CampaignView(GameService gameService) { // <2>
        this.gameService = gameService;
        addClassName("LoginView");
        setSizeFull();
        // set current campaign
        for (Campaign campaign : gameService.findAllCampaigns()) {
            if (campaign.getName() == selectedCampaign) {
                this.campaign = campaign;
            }
        }
        // configuration
        configureComponents();
        // display options
        add(new H1("Player Select:"));
        add(getPlayerList());
        add(new RouterLink("New Player", NewPlayerView.class));
        add(new RouterLink("Game Master", GMMainView.class));
        add(deleteCampaignButton);
    }

    private void configureComponents() {
        deleteCampaignButton.addClickListener(event -> {
            deleteCampaign();
            deleteCampaignButton.getUI().ifPresent(ui -> 
                ui.navigate(MainMenuView.class));    
        });
    }

    private void deleteCampaign() {
        gameService.removeCampaign(this.campaign);
    }

    private VerticalLayout getPlayerList() {
        VerticalLayout playerListLayout = new VerticalLayout();
        gameService.findAllPlayers().forEach(player -> { 
            Button playerLink = new Button(player.getName());
            playerLink.addClickListener(event -> 
                playerLink.getUI().ifPresent(ui -> 
                    ui.navigate(PlayerMainView.class, player.getName())
                )
            );
            playerListLayout.add(playerLink);
            } 
        );
        return playerListLayout;
    }
}
