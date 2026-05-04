package com.demo.application.components;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.dataview.GridListDataView;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

import java.util.ArrayList;
import java.util.List;

public class StringListField extends CustomField<List<String>> {

    private final TextField input = new TextField();
    private final Grid<String> grid = new Grid<>();
    private GridListDataView<String> dataView;

    public StringListField() {
        input.setPlaceholder("Add item…");
        input.setClearButtonVisible(true);
        input.setValueChangeMode(ValueChangeMode.EAGER);
        input.addKeyDownListener(Key.ENTER, e -> addItem());

        Button addButton = new Button(VaadinIcon.PLUS.create());
        addButton.addThemeVariants(ButtonVariant.LUMO_SMALL, ButtonVariant.LUMO_ICON);
        addButton.addClickListener(e -> addItem());

        HorizontalLayout inputRow = new HorizontalLayout(input, addButton);
        inputRow.setAlignItems(FlexComponent.Alignment.CENTER);
        inputRow.setWidthFull();
        inputRow.setFlexGrow(1, input);
        inputRow.setPadding(false);

        grid.addColumn(s -> s).setFlexGrow(1);
        grid.addComponentColumn(item -> {
            Button removeBtn = new Button(VaadinIcon.CLOSE_SMALL.create());
            removeBtn.addThemeVariants(
                    ButtonVariant.LUMO_TERTIARY_INLINE,
                    ButtonVariant.LUMO_SMALL,
                    ButtonVariant.LUMO_ERROR);
            removeBtn.addClickListener(e -> {
                dataView.removeItem(item);
                updateValue();
            });
            return removeBtn;
        }).setWidth("56px").setFlexGrow(0);

        grid.setAllRowsVisible(true);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COMPACT);
        grid.addClassName("no-header");

        dataView = grid.setItems(new ArrayList<>());

        VerticalLayout layout = new VerticalLayout(inputRow, grid);
        layout.setSpacing(false);
        layout.setPadding(false);
        layout.setWidthFull();

        add(layout);
        setWidthFull();
    }

    private void addItem() {
        String value = input.getValue().trim();
        if (!value.isEmpty()) {
            dataView.addItem(value);
            input.clear();
            updateValue();
        }
    }

    @Override
    protected List<String> generateModelValue() {
        List<String> result = new ArrayList<>();
        dataView.getItems().forEach(result::add);
        return result;
    }

    @Override
    protected void setPresentationValue(List<String> value) {
        dataView = grid.setItems(
                value == null ? new ArrayList<>() : new ArrayList<>(value));
    }
}
