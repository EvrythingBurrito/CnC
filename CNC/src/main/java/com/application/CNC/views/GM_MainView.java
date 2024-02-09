package com.application.CNC.views;

import org.hibernate.event.spi.DeleteEvent;

import com.application.CNC.services.gameService;

import com.application.CNC.services.gameService;
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
public class GM_MainView extends VerticalLayout {
    
    private final gameService service;

    public GM_MainView(gameService service) { // <2>
        this.service = service;
        addClassName("GM_MainView");
        setSizeFull();
    }
}
