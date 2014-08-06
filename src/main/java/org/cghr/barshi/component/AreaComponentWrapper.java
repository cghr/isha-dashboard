package org.cghr.barshi.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.cghr.barshi.DashboardHomeUI;
import org.cghr.barshi.dao.AreaDataDAO;
import org.cghr.barshi.db.data.entity.AreaDataEntity;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Window.CloseEvent;
import com.vaadin.ui.themes.Reindeer;

public class AreaComponentWrapper {
	private Table areaTable = null;
	
	public Component getAreaManagementComponent() {
		Label areaTableHeaderLabel = new Label("Areas");
		areaTableHeaderLabel.setStyleName(Reindeer.LABEL_H1);

		Button addAreaButton = new Button("Add");
		addAreaButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				final AreaDataEntity newArea = new AreaDataEntity(AreaDataDAO.getInstance().getNewAreaId());
				newArea.setName("");
				newArea.setLandmark("");
				newArea.setPincode("");

				Component editAreaComponent = getEditAreaComponent(newArea);

				Window addAreaWindow = new Window("Add Area");
				addAreaWindow.setModal(true);
				addAreaWindow.setResizable(false);
				addAreaWindow.setDraggable(false);
				addAreaWindow.setWidth("300px");

				addAreaWindow.setContent(editAreaComponent);

				UI.getCurrent().addWindow(addAreaWindow);

				addAreaWindow.addCloseListener(new Window.CloseListener() {
					@Override
					public void windowClose(CloseEvent e) {
						if (AreaDataDAO.getInstance().read(newArea.getId()) != null) {
							areaTable.addItem(newArea);
						}
					}
				});
			}
		});

		HorizontalLayout areaHeaderLayout = new HorizontalLayout();
		areaHeaderLayout.setSpacing(true);
		areaHeaderLayout.addComponent(areaTableHeaderLabel);
		areaHeaderLayout.addComponent(addAreaButton);
		areaHeaderLayout.setComponentAlignment(addAreaButton, Alignment.BOTTOM_LEFT);

		List<AreaDataEntity> areasList = AreaDataDAO.getInstance().getAllAreas();
		areaTable = getAreaTable(areasList, false, true, "id", "name", "landmark", "pincode");
		areaTable.setDragMode(Table.TableDragMode.MULTIROW);
		areaTable.setSizeFull();

		VerticalLayout areaVerticalLayout = new VerticalLayout();
		areaVerticalLayout.setSpacing(true);
		areaVerticalLayout.setSizeFull();
		areaVerticalLayout.addComponent(areaHeaderLayout);
		areaVerticalLayout.addComponent(areaTable);
		areaVerticalLayout.setExpandRatio(areaTable, 1f);
		
		return areaVerticalLayout;
	}
	
	public Table getAreaTable(Collection<AreaDataEntity> areaCollection, boolean showRemoveColumn, boolean showEditColumn, String... visibleColumns) {
		BeanItemContainer<AreaDataEntity> areaContainer = new BeanItemContainer<AreaDataEntity>(AreaDataEntity.class);
		areaContainer.addAll(areaCollection);

		areaTable = new Table();
		areaTable.setContainerDataSource(areaContainer);

		if (showRemoveColumn) {
			areaTable.addGeneratedColumn("remove", new Table.ColumnGenerator() {
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
			areaTable.addGeneratedColumn("edit", new Table.ColumnGenerator() {
				@Override
				public Object generateCell(Table source, final Object itemId, Object columnId) {
					Button editButton = new Button("Edit");

					editButton.addClickListener(new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							AreaDataEntity area = (AreaDataEntity) itemId;

							Component editAreaComponent = getEditAreaComponent(area);

							Window addAreaWindow = new Window("Add Area");
							addAreaWindow.setModal(true);
							addAreaWindow.setResizable(false);
							addAreaWindow.setDraggable(false);
							addAreaWindow.setWidth("300px");

							addAreaWindow.setContent(editAreaComponent);

							UI.getCurrent().addWindow(addAreaWindow);
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
			visibleColumnsList.add("landmark");
			visibleColumnsList.add("pincode");
		}

		if (showRemoveColumn) {
			visibleColumnsList.add("remove");
		}

		if (showEditColumn) {
			visibleColumnsList.add("edit");
		}

		areaTable.setVisibleColumns(visibleColumnsList.toArray());

		return areaTable;
	}
	
	private Component getEditAreaComponent(final AreaDataEntity area) {
		Item item = null;

		if (areaTable.getItem(area) != null) {
			item = areaTable.getItem(area);
		} else {
			item = new BeanItem<AreaDataEntity>(area);
		}

		final FieldGroup areaFieldGroup = new FieldGroup();
		areaFieldGroup.setItemDataSource(item);

		TextField idField = new TextField();
		idField.setCaption("ID");
		idField.setInputPrompt("ID");
		areaFieldGroup.bind(idField, "id");

		final TextField nameField = new TextField();
		nameField.setCaption("Area Name");
		nameField.setInputPrompt("Area Name");
		nameField.setRequired(true);
		areaFieldGroup.bind(nameField, "name");

		final TextField landmarkField = new TextField();
		landmarkField.setCaption("Landmark");
		landmarkField.setInputPrompt("Landmark");
		landmarkField.setRequired(true);
		areaFieldGroup.bind(landmarkField, "landmark");

		final TextField pincodeField = new TextField();
		pincodeField.setCaption("Pincode");
		pincodeField.setInputPrompt("Pincode");
		pincodeField.setRequired(true);
		areaFieldGroup.bind(pincodeField, "pincode");

		Button saveButton = new Button("Save");
		saveButton.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				Pattern pincodePattern = Pattern.compile("^[1-9][0-9]{5}$");
				Matcher pincodeMatcher = pincodePattern.matcher(pincodeField.getValue().replaceAll("\\s", ""));
				if (nameField.getValue().trim().length() <= 3) {
					Notification.show("Input Error!", "Name must contain at least 4 letters", Notification.Type.ERROR_MESSAGE);
				} else if (landmarkField.getValue().trim().length() <= 3) {
					Notification.show("Input Error!", "Landmark must contain at least 4 letters", Notification.Type.ERROR_MESSAGE);
				} else if (!pincodeMatcher.matches()) {
					Notification.show("Input Error!", "Pincode must contain exactly 6 numeric values", Notification.Type.ERROR_MESSAGE);
				} else {
					try {
						areaFieldGroup.commit();

						AreaDataDAO.getInstance().update(area);
						Notification.show("Success!", "Area \"" + area.getName() + "\" with ID " + area.getId() + " created successfully", Notification.Type.HUMANIZED_MESSAGE);
					} catch (CommitException e) {
						e.printStackTrace();
						Notification.show("Error!", "Something is wrong. Contact your admin.", Notification.Type.ERROR_MESSAGE);
					}
				}
			}
		});

		FormLayout formLayout = new FormLayout();
		formLayout.addComponent(idField);
		formLayout.addComponent(nameField);
		formLayout.addComponent(landmarkField);
		formLayout.addComponent(pincodeField);
		formLayout.addComponent(saveButton);

		return formLayout;
	}
}
