package view;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import model.*;

public class GuestProfileTable {
	private TableView<GuestProfile> tvGuestProfileList;
	private ArrayList<GuestProfile> al;
	private TableColumn<GuestProfile,Integer> idCol,roomNoCol;
	private TableColumn<GuestProfile,String> nameCol,nrcCol,phoneCol,emailCol,addressCol,
												checkInCol,checkOutCol;
	private FilteredList<GuestProfile> fl; 
	private TableViewSelectionModel<GuestProfile> selectionModel;
	
	private Label lSearch,lTitle;
	private TextField tfSearch;
	private BorderPane mainPane;
	private HBox searchPane;
	private DatePicker searchDate;



	public void createGuestProfileListTable()
	{
		tvGuestProfileList = new TableView<GuestProfile>();
		
		idCol = new TableColumn<GuestProfile,Integer>("No");
		idCol.setCellValueFactory(new PropertyValueFactory<GuestProfile,Integer>("No"));
		idCol.setSortable(true);
		
		roomNoCol = new TableColumn<GuestProfile,Integer>("Room No");
		roomNoCol.setCellValueFactory(new PropertyValueFactory<GuestProfile,Integer>("roomNo"));
		roomNoCol.setSortable(true);
		
		nameCol = new TableColumn<GuestProfile,String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<GuestProfile,String>("name"));
		nameCol.setSortable(true);
		
		phoneCol = new TableColumn<GuestProfile,String>("Phone Number");
		phoneCol.setCellValueFactory(new PropertyValueFactory<GuestProfile,String>("phone"));
		
		nrcCol = new TableColumn<GuestProfile,String>("NRC");
		nrcCol.setCellValueFactory(new PropertyValueFactory<GuestProfile,String>("nrc"));
		
		
		emailCol = new TableColumn<GuestProfile,String>("Email Address");
		emailCol.setCellValueFactory(new PropertyValueFactory<GuestProfile,String>("email"));
		
		addressCol = new TableColumn<GuestProfile,String>("Address");
		addressCol.setCellValueFactory(new PropertyValueFactory<GuestProfile,String>("address"));
		
		checkInCol = new TableColumn<GuestProfile,String>("Check In Date");
		checkInCol.setCellValueFactory(new PropertyValueFactory<GuestProfile,String>("checkIn"));
		
		checkOutCol = new TableColumn<GuestProfile,String>("Check Out Date");
		checkOutCol.setCellValueFactory(new PropertyValueFactory<GuestProfile,String>("checkOut"));
		
	
		tvGuestProfileList.getColumns().add(idCol);
		tvGuestProfileList.getColumns().add(roomNoCol);
		tvGuestProfileList.getColumns().add(nameCol);
		tvGuestProfileList.getColumns().add(phoneCol);
		tvGuestProfileList.getColumns().add(nrcCol);
		tvGuestProfileList.getColumns().add(emailCol);
		tvGuestProfileList.getColumns().add(addressCol);
		tvGuestProfileList.getColumns().add(checkInCol);
		tvGuestProfileList.getColumns().add(checkOutCol);
		
		selectionModel = tvGuestProfileList.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		tvGuestProfileList.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				if(e.getClickCount()==2)
				{
					deleteGuestProfile();
				}
			}
		});
		
		setGuestProfileData();
	
	}
	
	public void setGuestProfileData()
	{
		al=DBHandler.getAllGuestProfile();
		fl = new FilteredList<>(FXCollections.observableArrayList(al));
		tvGuestProfileList.getItems().addAll(fl);
	}
	
	
	public void deleteGuestProfile()
	{
		Alert alt = new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirm");
		alt.setContentText("Do you want to delete?");
		Optional<ButtonType> ans = alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			GuestProfile gl= selectionModel.getSelectedItem();
			tvGuestProfileList.getItems().remove(gl);
//			DBHandler.deleteGuestProfiles(gl.getGuestProfileId());
		}
	}

	public void createSearchBar()
	{
		lTitle = new Label("Guest Profile Details");
		lSearch = new Label("Search Box : ");
		tfSearch = new TextField();
		tfSearch.setOnKeyReleased(e->{
			if(e.getCode()==KeyCode.ENTER)
			{
				filteringGuestProfile();
			}
			
		});
		searchDate = new DatePicker();
		searchDate.setOnAction(e->{
			filteringGuestWithDate();
		});
		
		
		searchPane = new HBox(30,lTitle,lSearch,tfSearch,searchDate);
		FlowPane.setMargin(lTitle, new Insets(0,500,0,0));
		lSearch.setStyle("-fx-text-fill:#ffffff");
		searchPane.setAlignment(Pos.CENTER_RIGHT);
		searchPane.setPadding(new Insets(20));
	}
	
	public void filteringGuestProfile()
	{
		fl= new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<GuestProfile>(){

			@Override
			public boolean test(GuestProfile g) {
				String value = tfSearch.getText();
				if(value.length()==0)
				{
					return true;
				}
				
				return value.equalsIgnoreCase(g.getName());
				
			}
		});
		tvGuestProfileList.getItems().clear();
		tvGuestProfileList.getItems().addAll(fl);
	}

	public void filteringGuestWithDate()
	{
		fl= new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<GuestProfile>(){

			@Override
			public boolean test(GuestProfile gp) {
				String value = searchDate.getValue().toString();
				if(value.length()==0)
				{
					return true;
				}
				try {
					return value.equals(gp.getCheckIn()) || value.equals(gp.getCheckOut());
	
				}catch(Exception e)
				{
					
				}
				return false;

				
			}
		});
		tvGuestProfileList.getItems().clear();
		tvGuestProfileList.getItems().addAll(fl);
	}

	
	public void createLayoutGuestProfileTable()
	{
		mainPane = new BorderPane();
		mainPane.setTop(searchPane);
		mainPane.setCenter(tvGuestProfileList);
	}
	
	public void styleForGuestProfile()
	{
		tvGuestProfileList.getStyleClass().add("tv-check-in-list");
		searchPane.getStyleClass().add("search-pane-check-in");
		lTitle.getStyleClass().add("check-in-list-title");
	}
	
	public BorderPane getMainPane() {
		createGuestProfileListTable();
		createLayoutGuestProfileTable();
		createSearchBar();
		styleForGuestProfile();
		mainPane = new BorderPane();
		mainPane.setTop(searchPane);
		mainPane.setCenter(tvGuestProfileList);
		
		return mainPane;
	}

	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}

	public static void main(String args[])
	{
		Application.launch(args);
	}

}
