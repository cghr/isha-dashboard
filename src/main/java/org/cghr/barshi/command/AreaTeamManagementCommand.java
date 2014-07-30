package org.cghr.barshi.command;

import org.cghr.barshi.component.AreaComponents;
import org.cghr.barshi.component.SurveyorComponents;
import org.cghr.barshi.component.TeamComponents;

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
		SurveyorComponents surveyorComponents = new SurveyorComponents();
		Component manageSurveyorComponent = surveyorComponents.getSurveyorManagementComponent();
		TeamComponents teamComponents = new TeamComponents();
		
		AreaComponents areaComponents = new AreaComponents();
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
