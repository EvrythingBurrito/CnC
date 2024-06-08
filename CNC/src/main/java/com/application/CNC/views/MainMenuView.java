package com.application.CNC.views;

import org.hibernate.event.spi.DeleteEvent;

import com.application.CNC.views.GMMainView;
import com.application.CNC.data.Item;
import com.application.CNC.data.Player;
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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.SpringComponent;
import java.util.ArrayList;


@PermitAll
@Route(value = "") // <1>
@PageTitle("Caves & Cockatrices")
public class MainMenuView extends VerticalLayout {
    
    private final GameService gameService;

    public MainMenuView (GameService gameService) {
        this.gameService = gameService;
        addClassName("MainMenuView");
        setSizeFull();

        // display options
        add(new H1("Campaign Select:"));
        add(getCampaignList());
        add(new RouterLink("New Campaign", NewCampaignView.class));
    }

    private VerticalLayout getCampaignList() {
        VerticalLayout campaignListLayout = new VerticalLayout();
        gameService.findAllCampaigns().forEach(campaign -> { 
            Button campaignLink = new Button(campaign.getName());
            campaignLink.addClickListener(event -> 
                campaignLink.getUI().ifPresent(ui -> 
                    ui.navigate(CampaignView.class, campaign.getName())
                )
            );
            campaignListLayout.add(campaignLink);
            } 
        );
        return campaignListLayout;
    }

}
