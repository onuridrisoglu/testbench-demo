package com.demo.testbench.e2e.masterdetail;

import com.demo.application.data.SamplePerson;
import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.grid.testbench.GridColumnElement;
import com.vaadin.flow.component.grid.testbench.GridElement;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.annotations.Attribute;
import com.vaadin.testbench.elementsbase.Element;

import java.util.Objects;

@Element("div")
@Attribute(name = "id", value = "master-detail-view")
public class MasterDetailViewElement extends TestBenchElement {

    public void savePerson(SamplePerson person) {
        FormEditorElement editor = findFormEditorElement();
        editor.fillPersonDetails(person);

        ButtonElement save = $(ButtonElement.class).withCaption("Save").first();
        save.click();
    }

    // Scroll down until the person is found
    public void findPersonInGrid(String name) {
        GridElement gridElement = findGridElement();
        GridColumnElement firstNameColumn = gridElement.getColumn("First Name");
        for (int i = gridElement.getFirstVisibleRowIndex(); i <= gridElement.getLastVisibleRowIndex(); i++) {
            if (Objects.equals(name, gridElement.getRow(i).getCell(firstNameColumn).getText())) {
                gridElement.select(i);
                return;
            }
        }
        if (gridElement.getLastVisibleRowIndex() < gridElement.getRowCount()) {
            gridElement.scrollToRow(gridElement.getLastVisibleRowIndex());
            findPersonInGrid(name);
        }
    }

    public GridElement findGridElement() {
        return $(GridElement.class).first();
    }

    public FormEditorElement findFormEditorElement() {
        return $(FormEditorElement.class).first();
    }

}
