package org.cghr.barshi.command;

import org.cghr.barshi.component.AreaComponentWrapper;
import org.cghr.barshi.component.SurveyorComponentWrapper;
import org.cghr.barshi.component.TeamComponentWrapper;

import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;
import com.vaadin.ui.MenuBar.MenuItem;

public class AreaTeamManagementCommand implements MenuBar.Command {
	@Override
	public void menuSelected(MenuItem selectedItem) {
		SurveyorComponentWrapper surveyorComponents = new SurveyorComponentWrapper();
		Component manageSurveyorComponent = surveyorComponents.getSurveyorManagementComponent();
		TeamComponentWrapper teamComponents = new TeamComponentWrapper();
		
		AreaComponentWrapper areaComponents = new AreaComponentWrapper();
		Component manageAreaComponent = areaComponents.getAreaManagementComponent();
		
		VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel(manageSurveyorComponent, manageAreaComponent);

		// Create Team Management Section
		Component teamManagementComponent = teamComponents.getTeamManagementComponent();
		
		HorizontalSplitPanel horizontalSplitPanel = new HorizontalSplitPanel(verticalSplitPanel, teamManagementComponent);

		Window window = new Window("Area / Team Management");
		window.setSizeFull();
		window.setModal(true);
		window.setDraggable(false);
		window.setResizable(false);
		window.center();

		window.setContent(horizontalSplitPanel);

		UI.getCurrent().addWindow(window);
	}
}
