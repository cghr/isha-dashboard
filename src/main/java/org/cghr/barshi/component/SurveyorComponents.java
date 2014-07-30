package org.cghr.barshi.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.cghr.barshi.DashboardHomeUI;
import org.cghr.barshi.dao.UserDAO;
import org.cghr.barshi.entity.User;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.themes.Reindeer;

public class SurveyorComponents {
	Table surveyorTable = null;

	public Component getSurveyorManagementComponent() {
		Label surveyorTableHeaderLabel = new Label("Surveyors");
		surveyorTableHeaderLabel.setStyleName(Reindeer.LABEL_H1);

		Button addSurveyorButton = new Button("Add");
		addSurveyorButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				final User newUser = new User(UserDAO.getInstance().getNewUserId());
				newUser.setName("");
				newUser.setUsername("");
				newUser.setClearPassword("");
				newUser.setRole("user");

				Component editUserComponent = getEditSurveyorComponent(newUser);

				Window addUserWindow = new Window("Add User");
				addUserWindow.setModal(true);
				addUserWindow.setResizable(false);
				addUserWindow.setDraggable(false);
				addUserWindow.setWidth("300px");

				addUserWindow.setContent(editUserComponent);

				UI.getCurrent().addWindow(addUserWindow);

				addUserWindow.addCloseListener(new Window.CloseListener() {
					@Override
					public void windowClose(CloseEvent e) {
						if (UserDAO.getInstance().read(newUser.getId()) != null) {
							surveyorTable.addItem(newUser);
						}
					}
				});
			}
		});

		HorizontalLayout surveyorHeaderLayout = new HorizontalLayout();
		surveyorHeaderLayout.setSpacing(true);
		surveyorHeaderLayout.addComponent(surveyorTableHeaderLabel);
		surveyorHeaderLayout.addComponent(addSurveyorButton);
		surveyorHeaderLayout.setComponentAlignment(addSurveyorButton, Alignment.BOTTOM_LEFT);

		// surveyorTable = getSurveyorTable();
		List<User> userList = UserDAO.getInstance().getAllUsers();
		surveyorTable = getSurveyorTable(userList, false, true, "id", "name", "role");
		surveyorTable.setDragMode(Table.TableDragMode.MULTIROW);
		surveyorTable.setSizeFull();

		VerticalLayout surveyorVerticalLayout = new VerticalLayout();
		surveyorVerticalLayout.setSpacing(true);
		surveyorVerticalLayout.setSizeFull();
		surveyorVerticalLayout.addComponent(surveyorHeaderLayout);
		surveyorVerticalLayout.addComponent(surveyorTable);
		surveyorVerticalLayout.setExpandRatio(surveyorTable, 1f);

		return surveyorVerticalLayout;
	}

	/**
	 * Method to generate a table containing list of surveyors.
	 * 
	 * @param userCollection
	 *            Content of the table.
	 * @param visibleColumns
	 *            List of the visible columns, taken from the properties for
	 *            bean User.class
	 * @param showRemoveColumn
	 *            Whether to add a Generated Column with "Remove" Button
	 * @return The table containing the list of surveyors as provided.
	 */
	public Table getSurveyorTable(Collection<User> userCollection, boolean showRemoveColumn, boolean showEditColumn, String... visibleColumns) {
		BeanItemContainer<User> userContainer = new BeanItemContainer<User>(User.class);
		userContainer.addAll(userCollection);

		surveyorTable = new Table();
		surveyorTable.setContainerDataSource(userContainer);

		if (showRemoveColumn) {
			surveyorTable.addGeneratedColumn("remove", new Table.ColumnGenerator() {
				@Override
				public Object generateCell(final Table source, final Object itemId, Object columnId) {
					Button removeButton = new Button("Remove");
					removeButton.addClickListener(new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							source.removeItem(itemId);
						}
					});

					return removeButton;
				}
			});
		}

		if (showEditColumn) {
			surveyorTable.addGeneratedColumn("edit", new Table.ColumnGenerator() {
				@Override
				public Object generateCell(Table source, final Object itemId, Object columnId) {
					Button editButton = new Button("Edit");

					editButton.addClickListener(new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							User user = (User) itemId;
							user.setClearPassword("");

							Component editUserComponent = getEditSurveyorComponent(user);

							VerticalLayout containerLayout = new VerticalLayout();
							containerLayout.setWidth("100%");
							containerLayout.addComponent(editUserComponent);
							containerLayout.setComponentAlignment(editUserComponent, Alignment.MIDDLE_CENTER);

							Window addUserWindow = new Window("Edit User");
							addUserWindow.setModal(true);
							addUserWindow.setResizable(false);
							addUserWindow.setDraggable(false);
							addUserWindow.setWidth("300px");

							addUserWindow.setContent(containerLayout);

							UI.getCurrent().addWindow(addUserWindow);

						}
					});

					return editButton;
				}
			});
		}

		List<Object> visibleColumnsList = new ArrayList<Object>();

		if (visibleColumns != null) {
			visibleColumnsList.addAll(Arrays.asList(visibleColumns));
		} else {
			visibleColumnsList.add("id");
			visibleColumnsList.add("name");
			visibleColumnsList.add("username");
			visibleColumnsList.add("role");
		}

		if (showEditColumn) {
			visibleColumnsList.add("edit");
		}

		if (showRemoveColumn) {
			visibleColumnsList.add("remove");
		}

		surveyorTable.setVisibleColumns(visibleColumnsList.toArray());

		return surveyorTable;
	}

	private Component getEditSurveyorComponent(final User user) {
		Item item = null;

		if (surveyorTable.getItem(user) != null) {
			item = surveyorTable.getItem(user);
		} else {
			item = new BeanItem<User>(user);
		}

		final FieldGroup userFieldGroup = new FieldGroup();
		userFieldGroup.setItemDataSource(item);

		TextField idField = new TextField();
		idField.setCaption("ID");
		idField.setInputPrompt("ID");
		userFieldGroup.bind(idField, "id");

		final TextField nameField = new TextField();
		nameField.setImmediate(true);
		nameField.setCaption("Full Name");
		nameField.setInputPrompt("Full Name");
		nameField.setRequired(true);
		nameField.setValidationVisible(true);
		userFieldGroup.bind(nameField, "name");

		final TextField usernameField = new TextField();
		usernameField.setCaption("Username");
		usernameField.setInputPrompt("Username");
		usernameField.setImmediate(true);
		usernameField.setRequired(true);
		usernameField.setValidationVisible(true);
		userFieldGroup.bind(usernameField, "username");

		final PasswordField passwordField = new PasswordField();
		passwordField.setCaption("Password");
		passwordField.setInputPrompt("Password");
		userFieldGroup.bind(passwordField, "clearPassword");

		final PasswordField repasswordField = new PasswordField();
		repasswordField.setValue(user.getClearPassword());
		repasswordField.setInputPrompt("Password");
		repasswordField.setCaption("Re-type Password");

		Button saveButton = new Button("Save");
		saveButton.setImmediate(true);
		saveButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				if (nameField.getValue().trim().length() <= 3) {
					Notification.show("Input Error!", "Name must be contain at least 4 letters", Notification.Type.ERROR_MESSAGE);
				} else if (usernameField.getValue().length() <= 3) {
					Notification.show("Input Error!", "Username must contain at least 4 letters", Notification.Type.ERROR_MESSAGE);
				} else {
					try {
						userFieldGroup.commit();

						UserDAO.getInstance().update(user);
						Notification.show("Success!", "User \"" + user.getName() + "\" with id " + user.getId() + " was saved successfully", Notification.Type.HUMANIZED_MESSAGE);
					} catch (CommitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Notification.show("Error!", "Something is wrong. Contact your admin.", Notification.Type.ERROR_MESSAGE);
					}
				}
			}
		});

		final FormLayout formLayout = new FormLayout();
		formLayout.addComponent(idField);
		formLayout.addComponent(nameField);
		formLayout.addComponent(usernameField);
		formLayout.addComponent(passwordField);
		formLayout.addComponent(repasswordField);
		formLayout.addComponent(saveButton);

		return formLayout;
	}
}
