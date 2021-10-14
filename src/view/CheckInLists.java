package view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.*;

public class CheckInLists{

	private TableView<CheckIn> tvCheckInList;
	private ArrayList<CheckIn> al;
	private ArrayList<CheckIn> currentAl;
	private TableColumn<CheckIn,Integer> checkInIdCol,roomChargeCol,noOfNightsCol;
	private TableColumn<CheckIn,String> nameCol,phoneCol,roomNoCol,roomTypeCol,
									bedTypeCol,depositCol,inTimeCol,reTypeCol,receptionNameCol;
	private TableColumn<CheckIn,Date> checkInCol,checkOutCol;
	private TableViewSelectionModel<CheckIn> selectionModel;
	private Label lSearch,lTitle;
	private TextField tfSearch;
	private DatePicker searchDate;
	private FilteredList<CheckIn> fl;
	private BorderPane mainCheckInPane;
	private HBox searchPane;
	
	private static String name=null;
	private static String phone=null;
	private static int guestId;
	
	public void createCheckInListTable()
	{
		tvCheckInList = new TableView<CheckIn>();
		
		checkInIdCol = new TableColumn<CheckIn,Integer>("Check In ID");
		checkInIdCol.setCellValueFactory(new PropertyValueFactory<CheckIn,Integer>("checkInId"));
		checkInIdCol.setSortable(true);
		
		nameCol = new TableColumn<CheckIn,String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<CheckIn,String>("name"));
		nameCol.setSortable(true);
		
		phoneCol = new TableColumn<CheckIn,String>("Phone Number");
		phoneCol.setCellValueFactory(new PropertyValueFactory<CheckIn,String>("phone"));
		
		checkInCol = new TableColumn<CheckIn,Date>("Check In");
		checkInCol.setCellValueFactory(new PropertyValueFactory<CheckIn,Date>("checkInDate"));
		checkInCol.setSortable(true);
		
		checkOutCol = new TableColumn<CheckIn,Date>("Check Out");
		checkOutCol.setCellValueFactory(new PropertyValueFactory<CheckIn,Date>("checkOutDate"));
		checkOutCol.setSortable(true);
		
		
		inTimeCol = new TableColumn<CheckIn,String>("Check In Time");
		inTimeCol.setCellValueFactory(new PropertyValueFactory<CheckIn,String>("checkInTime"));
	
	
		noOfNightsCol = new TableColumn<CheckIn,Integer>("Nights");
		noOfNightsCol.setCellValueFactory(new PropertyValueFactory<CheckIn,Integer>("noOfNights"));
		noOfNightsCol.setSortable(true);
		
		roomNoCol = new TableColumn<CheckIn,String>("Room No");
		roomNoCol.setCellValueFactory(new PropertyValueFactory<CheckIn,String>("roomNo"));
		roomNoCol.setSortable(true);
		
		roomTypeCol = new TableColumn<CheckIn,String>("Room Type");
		roomTypeCol.setCellValueFactory(new PropertyValueFactory<CheckIn,String>("roomType"));
		
		bedTypeCol = new TableColumn<CheckIn,String>("Bed Type");
		bedTypeCol.setCellValueFactory(new PropertyValueFactory<CheckIn,String>("bedType"));
		
		roomChargeCol = new TableColumn<CheckIn,Integer>("Room Charges");
		roomChargeCol.setCellValueFactory(new PropertyValueFactory<CheckIn,Integer>("roomCharges"));
		
		depositCol = new TableColumn<CheckIn,String>("Deposit");
		depositCol.setCellValueFactory(new PropertyValueFactory<CheckIn,String>("deposit"));
		
		reTypeCol = new TableColumn<CheckIn,String>("Reserve Type");
		reTypeCol.setCellValueFactory(new PropertyValueFactory<CheckIn,String>("reserveType"));
		
		receptionNameCol = new TableColumn<CheckIn,String>("Receptionist Name");
		receptionNameCol.setCellValueFactory(new PropertyValueFactory<CheckIn,String>("receptionistName"));
		
		tvCheckInList.getColumns().add(checkInIdCol);
		tvCheckInList.getColumns().add(nameCol);
		tvCheckInList.getColumns().add(phoneCol);
		tvCheckInList.getColumns().add(checkInCol);
		tvCheckInList.getColumns().add(checkOutCol);
		tvCheckInList.getColumns().add(inTimeCol);
		tvCheckInList.getColumns().add(noOfNightsCol);
		tvCheckInList.getColumns().add(roomNoCol);
		tvCheckInList.getColumns().add(roomTypeCol);
		tvCheckInList.getColumns().add(bedTypeCol);
		tvCheckInList.getColumns().add(roomChargeCol);
		tvCheckInList.getColumns().add(depositCol);
		tvCheckInList.getColumns().add(reTypeCol);
		tvCheckInList.getColumns().add(receptionNameCol);
		
		tvCheckInList.setPadding(new Insets(30));
		
		selectionModel = tvCheckInList.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		tvCheckInList.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				if(e.getClickCount()==2)
				{
					getGuestNameAndPhone();
					mainCheckInPane.setTop(null);
					mainCheckInPane.setCenter(null);
					mainCheckInPane.setCenter(new NewGuestForm().getMainPane());
				}
			}
		});
		
		setCheckInData();
	}
	
	
	
	public void getGuestNameAndPhone()
	{
		CheckIn ci = selectionModel.getSelectedItem();
		name = ci.getName();
		phone = ci.getPhone();
//		System.out.println(name+":"+phone);
		guestId = DBHandler.getGuestId(name, phone);
//		System.out.println("This is selected guest id"+guestId);
		
	}

	
	public void setCheckInData()
	{
		al=DBHandler.getAllCheckInLists();
		currentAl = DBHandler.getCurrentCheckInLists();
		fl = new FilteredList<CheckIn>(FXCollections.observableArrayList(al));
		tvCheckInList.getItems().addAll(currentAl);
	}
	
//	public void editCheckInData()
//	{
//		tvCheckInList.setEditable(true);
//		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
//		nameCol.setOnEditCommit(
//		t->t.getTableView().getItems().get(t.getTablePosition().getRow()).setName(t.getNewValue())
//		);
//	}
//	
	public void createSearchBarCheckIn()
	{
		lTitle = new Label("Check In Lists");
		lSearch = new Label("Search Box : ");
		lSearch.setStyle("-fx-text-fill:#D6EAF8;-fx-font-size:16;-fx-font-weight:bolder");
		tfSearch = new TextField();
		tfSearch.setOnKeyReleased(e->{
			if(e.getCode()==KeyCode.ENTER)
			{
				filteringCheckIn();
			}
			
		});
		searchDate = new DatePicker();
		searchDate.setOnAction(e->{
			filteringCheckInListWihtDate();
		});
		
		
		searchPane = new HBox(20,lTitle,lSearch,tfSearch,searchDate);
		searchPane.setMargin(lTitle, new Insets(0,200,0,0));
		searchPane.setAlignment(Pos.CENTER_RIGHT);
		searchPane.setPadding(new Insets(20));
		
	}
	
	public void createLayoutTableCheckIn()
	{
		
		mainCheckInPane = new BorderPane();
		mainCheckInPane.setTop(searchPane);
		mainCheckInPane.setCenter(tvCheckInList);
	}
	
	public void filteringCheckIn()
	{
		fl= new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<CheckIn>() {

			@Override
			public boolean test(CheckIn c) {
				String value = tfSearch.getText();
				if(value.length()==0)
				{
					return true;
				}
				try {
					int rno = Integer.parseInt(value);
					return rno==c.getRoomNo();
				}catch(Exception e)
				{

					return c.getRoomType().indexOf(value)>=0 || c.getName().equalsIgnoreCase(value);
				}
				
			}
			
		});
		tvCheckInList.getItems().clear();
		tvCheckInList.getItems().addAll(fl);
	}
	
	public void filteringCheckInListWihtDate()
	{
		fl= new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<CheckIn>(){

			@Override
			public boolean test(CheckIn ci) {
				String value = searchDate.getValue().toString();
				if(value.length()==0)
				{
					return true;
				}
				try {
					return value.equals(ci.getCheckInDate());
	
				}catch(Exception e)
				{
					
				}
				return false;

				
			}
		});
		tvCheckInList.getItems().clear();
		tvCheckInList.getItems().addAll(fl);
	}

	public void styleForCheckInList()
	{
		tvCheckInList.getStyleClass().add("tv-check-in-list");
		searchPane.getStyleClass().add("search-pane-check-in");
		lTitle.getStyleClass().add("check-in-list-title");
	}

	
	public BorderPane getMainCheckInPane() {
		createCheckInListTable();
		createSearchBarCheckIn();
		createLayoutTableCheckIn();
		styleForCheckInList();
		return mainCheckInPane;
	}
	
	


	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		CheckInLists.name = name;
	}

	public static String getPhone() {
		return phone;
	}


	public static int getGuestId() {
		return guestId;
	}

	public static void setGuestId(int guestId) {
		CheckInLists.guestId = guestId;
	}

	public static void setPhone(String phone) {
		CheckInLists.phone = phone;
	}
	
	



	public static void main(String[] args) {
		Application.launch(args);

	}


}
