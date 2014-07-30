package org.cghr.barshi;

import javax.servlet.annotation.WebServlet;

import org.cghr.barshi.component.AreaComponents;
import org.cghr.barshi.component.SurveyorComponents;
import org.cghr.barshi.component.TeamComponents;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;

@Theme("mytheme")
@SuppressWarnings("serial")
public class DashboardHomeUI extends UI {

	private Table areaTable = null;
	private Table surveyorTable = null;
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = DashboardHomeUI.class, widgetset = "org.cghr.barshi.AppWidgetSet")
	public static class Servlet extends VaadinServlet {}

	/**
	 * Loads the main Barshi Dashboard View.
	 */
	@Override
	protected void init(VaadinRequest request) {
		MenuBar menuBar = new MenuBar();
		menuBar.setWidth("100%");
		menuBar.setImmediate(true);

		MenuBar.MenuItem administrationMenuItem = menuBar.addItem("Administration", null);
		administrationMenuItem.addItem("Area / Team Management", new AreaTeamManagementCommand());

		setContent(menuBar);
	}

	private class AreaTeamManagementCommand implements MenuBar.Command {
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

			addWindow(window);
		}
	}
}