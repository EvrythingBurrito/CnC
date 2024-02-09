package com.application.CNC.views;

import org.hibernate.event.spi.DeleteEvent;

import com.application.CNC.views.GM_MainView;
import com.application.CNC.data.Item;
import com.application.CNC.services.gameService;
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
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.spring.annotation.SpringComponent;


@PermitAll
@Route(value = "") // <1>
@PageTitle("CNC Login")
public class LoginView extends VerticalLayout {
    private final gameService pservice;

    public LoginView(gameService pservice) { // <2>
        this.pservice = pservice;
        addClassName("LoginView");
        setSizeFull();
        
        add(new H1("Player Select:"));
        add(new RouterLink("Game Master", GM_MainView.class));
        add(new RouterLink("New Player", NewPlayerView.class));
        add(getPlayerList());
    }

    private VerticalLayout getPlayerList() {
        VerticalLayout playerListLayout = new VerticalLayout();
        pservice.findAllPlayers().forEach(player -> playerListLayout.add(new H2(player.getName())));
        // pservice.findAllPlayers().forEach(player -> playerListLayout.add(new RouterLink(player.getName(),Player_MainView.class)));
        return playerListLayout;
    }
}
