package com.demo.application.views.stringlistfield;

import com.demo.application.components.StringListField;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

@PageTitle("String List Field")
@Route("string-list-field")
@Menu(order = 2, icon = "line-awesome/svg/list-solid.svg", title = "String List Field")
public class StringListFieldView extends VerticalLayout {

    public StringListFieldView() {
        StringListField field = new StringListField();
        field.setLabel("Tags");
        field.setWidth("400px");

        Paragraph valueDisplay = new Paragraph("Current value: (empty)");
        field.addValueChangeListener(e -> {
            List<String> value = e.getValue();
            valueDisplay.setText("Current value: "
                    + (value.isEmpty() ? "(empty)" : String.join(", ", value)));
        });

        Button setExampleButton = new Button("Set example values", e ->
                field.setValue(List.of("Alpha", "Beta", "Gamma")));
        setExampleButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        Button clearButton = new Button("Clear", e -> field.setValue(List.of()));
        clearButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_ERROR);

        add(new H3("StringListField Demo"), field, valueDisplay, setExampleButton, clearButton);
        setMaxWidth("600px");
        setPadding(true);
    }
}
