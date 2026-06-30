package com.demo.application.views.helloworld;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.apache.logging.log4j.util.Strings;

@PageTitle("Hello-World")
@Route("")
@Menu(order = 0, icon = "line-awesome/svg/columns-solid.svg", title = "Hello world")
@Uses(Icon.class)
public class HelloWorldView extends VerticalLayout {

    public HelloWorldView() {
        TextField textfield = new TextField("Name");
        Button button = new Button("Say hello");
        button.addClickListener(evt -> sayHello(textfield.getValue()));

        add(textfield, button);
    }

    private void sayHello(String name) {
        if (Strings.isBlank(name)) {
            name = "Anonymous";
        }
        Notification.show("Hello " + name, 10000, Notification.Position.MIDDLE);
    }
}
