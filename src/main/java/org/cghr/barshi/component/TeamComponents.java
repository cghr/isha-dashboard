package org.cghr.barshi.component;

import java.util.List;

import org.cghr.barshi.DashboardHomeUI;
import org.cghr.barshi.dao.TeamDAO;
import org.cghr.barshi.entity.Area;
import org.cghr.barshi.entity.Team;
import org.cghr.barshi.entity.User;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.Transferable;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.ServerSideCriterion;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.themes.Reindeer;

public class TeamComponents {
	private Team team = null;
	
	private Table getSurveyorTable(Team team) {
		SurveyorComponents surveyorComponents = new SurveyorComponents();
		
		Table surveyorTable = surveyorComponents.getSurveyorTable(team.getSurveyors(), true, false, "name");
		surveyorTable.setWidth("200px");
		surveyorTable.setHeight("200px");

		surveyorTable.setDropHandler(new DropHandler() {
			@Override
			public AcceptCriterion getAcceptCriterion() {
				// TODO Auto-generated method stub
				return new ServerSideCriterion() {
					@Override
					public boolean accept(DragAndDropEvent dragEvent) {
						Transferable transferable = dragEvent.getTransferable();
						Object itemId = transferable.getData("itemId");
						if (itemId instanceof User && ((User) itemId).getRole().contains("user")) {
							return true;
						} else {
							return false;
						}
					}
				};
			}

			@Override
			public void drop(DragAndDropEvent event) {
				Transferable transferable = event.getTransferable();
				User incomingUser = (User) transferable.getData("itemId");
				Table surveyorTable = (Table) event.getTargetDetails().getTarget();

				BeanItemContainer<User> userContainer = (BeanItemContainer<User>) surveyorTable.getContainerDataSource();
				for(User user : userContainer.getItemIds()) {
					if(user.getId().equals(incomingUser.getId())) {
						return;
					}
				}
				
				surveyorTable.addItem(incomingUser);
			}
		});
		return surveyorTable;
	}
	
	private Table getAreaTable(Team team) {
		AreaComponents areaComponents = new AreaComponents();
		
		Table areaTable = new Table();
		areaTable = areaComponents.getAreaTable(team.getAreas(), true, false, "id", "name", "landmark");
		areaTable.setWidth("500px");
		areaTable.setHeight("200px");

		areaTable.setDropHandler(new DropHandler() {
			@Override
			public AcceptCriterion getAcceptCriterion() {
				// TODO Auto-generated method stub
				return new ServerSideCriterion() {
					@Override
					public boolean accept(DragAndDropEvent dragEvent) {
						Transferable transferable = dragEvent.getTransferable();
						Object itemId = transferable.getData("itemId");
						if (itemId instanceof Area) {
							return true;
						} else {
							return false;
						}
					}
				};
			}

			@Override
			public void drop(DragAndDropEvent event) {
				Transferable transferable = event.getTransferable();
				Area incomingArea = (Area) transferable.getData("itemId");
				Table areaTable = (Table) event.getTargetDetails().getTarget();

				BeanItemContainer<Area> areaContainer = (BeanItemContainer<Area>) areaTable.getContainerDataSource();
				for(Area area : areaContainer.getItemIds()) {
					if(area.getId().equals(incomingArea.getId())) {
						return;
					}
				}
				
				areaTable.addItem(incomingArea);
			}
		});
		return areaTable;
	}
	
	private Component getTeamComponent(Team team) {
		Label headerLabel = new Label(team.getName());
		headerLabel.setStyleName(Reindeer.LABEL_H1);

		HorizontalLayout surveyorHeaderLayout = new HorizontalLayout();
		surveyorHeaderLayout.setSpacing(true);
		surveyorHeaderLayout.addComponent(headerLabel);

		Label surveyorComponentHeaderLabel = new Label("Surveyors");
		surveyorComponentHeaderLabel.setStyleName(Reindeer.LABEL_H2);

		Table surveyorTable = getSurveyorTable(team);

		VerticalLayout surveyorLayout = new VerticalLayout();
		surveyorLayout.setSpacing(true);
		surveyorLayout.addComponent(surveyorComponentHeaderLabel);
		surveyorLayout.addComponent(surveyorTable);

		Label areaComponentHeaderLabel = new Label("Assigned Areas");
		areaComponentHeaderLabel.setStyleName(Reindeer.LABEL_H2);

		Table areaTable = getAreaTable(team);

		VerticalLayout areaLayout = new VerticalLayout();
		areaLayout.setSpacing(true);
		areaLayout.addComponent(areaComponentHeaderLabel);
		areaLayout.addComponent(areaTable);

		HorizontalLayout contentLayout = new HorizontalLayout();
		contentLayout.setSpacing(true);
		contentLayout.addComponent(surveyorLayout);
		contentLayout.addComponent(areaLayout);
		contentLayout.setExpandRatio(areaLayout, 1f);

		VerticalLayout teamComponentLayout = new VerticalLayout();
		teamComponentLayout.setSizeFull();
		teamComponentLayout.addComponent(surveyorHeaderLayout);
		teamComponentLayout.addComponent(contentLayout);
		teamComponentLayout.addStyleName("sectioned");

		return teamComponentLayout;
	}
	
	public Component getTeamManagementComponent() {
		Label headerLabel = new Label("Teams");
		headerLabel.setStyleName(Reindeer.LABEL_H1);

		Button addButton = new Button("Add");
		
		final VerticalLayout teamComponentsLayout = new VerticalLayout();
		
		addButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				final Team newTeam = new Team(TeamDAO.getInstance().getNewTeamId());
				newTeam.setName("");
				Component editTeamComponent = getEditTeamComponent(newTeam);
				
				Window addTeamWindow = new Window("Add Team");
				addTeamWindow.setResizable(false);
				addTeamWindow.setDraggable(false);
				addTeamWindow.setModal(true);
				addTeamWindow.setContent(editTeamComponent);
				
				addTeamWindow.addCloseListener(new Window.CloseListener() {
					@Override
					public void windowClose(CloseEvent e) {
						Team team = TeamDAO.getInstance().read(newTeam.getId());
						if(team != null) {
							Component teamComponent = getTeamComponent(newTeam);
							teamComponentsLayout.addComponent(teamComponent);
						}
					}
				});
				
				DashboardHomeUI.showWindow(addTeamWindow);
			}
		});
		
		HorizontalLayout headerLayout = new HorizontalLayout();
		headerLayout.setSpacing(true);
		headerLayout.addComponent(headerLabel);
		headerLayout.addComponent(addButton);
		headerLayout.setComponentAlignment(addButton, Alignment.MIDDLE_LEFT);

		List<Team> teamList = TeamDAO.getInstance().getAllTeams();

		for (Team team : teamList) {
			Component teamComponent = getTeamComponent(team);
			teamComponentsLayout.addComponent(teamComponent);
		}

		VerticalSplitPanel verticalSplitPanel = new VerticalSplitPanel(headerLayout, teamComponentsLayout);
		verticalSplitPanel.setSplitPosition(30, Unit.PIXELS);
		verticalSplitPanel.setLocked(true);

		return verticalSplitPanel;
	}


	private Component getEditTeamComponent(final Team team) {
		BeanItem<Team> teamBeanItem = new BeanItem<Team>(team);
		
		final FieldGroup editTeamFieldGroup = new FieldGroup(teamBeanItem);
		
		TextField idField = new TextField();
		idField.setCaption("ID");
		idField.setInputPrompt("ID");
		editTeamFieldGroup.bind(idField, "id");
		
		final TextField nameField = new TextField();
		nameField.setCaption("Name");
		nameField.setInputPrompt("Name");
		nameField.setRequired(true);
		editTeamFieldGroup.bind(nameField, "name");
		
		Button saveButton = new Button("Save");
		saveButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if(nameField.getValue().trim().length() <= 3) {
					Notification.show("Input Error", "Team Name must contain at least 4 letters", Notification.Type.ERROR_MESSAGE);
				} else {
					try {
						editTeamFieldGroup.commit();
						
						TeamDAO.getInstance().update(team);
						
						Notification.show("Success!", "Team \"" + team.getId() + "\"  with id " + team.getId() + " created successfully", Notification.Type.HUMANIZED_MESSAGE);
					} catch (CommitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(idField);
		formLayout.addComponent(nameField);
		formLayout.addComponent(saveButton);
		
		return formLayout;
	}
}
