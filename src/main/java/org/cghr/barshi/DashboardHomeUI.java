package org.cghr.barshi;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.servlet.annotation.WebServlet;

import org.cghr.barshi.command.AreaTeamManagementCommand;
import org.cghr.barshi.command.DailyAttendanceFormCommand;
import org.cghr.barshi.dao.TeamDataDAO;
import org.cghr.barshi.db.data.entity.TeamDataEntity;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;

@Theme("mytheme")
@SuppressWarnings("serial")
public class DashboardHomeUI extends UI {

	private Table areaTable = null;
	private Table surveyorTable = null;

	@WebServlet(value = "/ui/*", asyncSupported = true)
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

		MenuBar.MenuItem teamMenuItem = menuBar.addItem("Teams", null);

		for (TeamDataEntity team : TeamDataDAO.getInstance().getAllTeams()) {
			MenuBar.MenuItem teamItem = teamMenuItem.addItem(team.getName(), null);
			Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("IST"));
			Date date = calendar.getTime();
			teamItem.addItem("Daily Attendance Form", new DailyAttendanceFormCommand(team, date));
		}

		setContent(menuBar);
	}
}
