package com.application.CNC.views;

import org.hibernate.event.spi.DeleteEvent;

import com.application.CNC.services.GameService;

import com.application.CNC.services.GameService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@PermitAll
@Route(value = "GM_Main")
@PageTitle("GM Main Screen")
public class GMMainView extends VerticalLayout {
    
    private final GameService gameService;

    public GMMainView(GameService gameService) { // <2>
        this.gameService = gameService;
        addClassName("GMMainView");
        setSizeFull();
    }
}
