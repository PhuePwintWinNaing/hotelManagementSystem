package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.function.Predicate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import model.CheckIn;
import model.CheckOut;
import model.DBHandler;

public class CheckOutLists {
	private TableView<CheckOut> tvCheckOutList;
	private ArrayList<CheckOut> al;
	private ArrayList<CheckOut> currentAl;
	private TableColumn<CheckOut,Integer> checkInIdCol,roomChargeCol,noOfNightsCol;
	private TableColumn<CheckOut,String> nameCol,phoneCol,roomNoCol,roomTypeCol,
									bedTypeCol,depositCol,inTimeCol,outTimeCol,reTypeCol,receptionNameCol;
	private TableColumn<CheckOut,Date> checkInCol,checkOutCol;
	private Label lSearch,lTitle;
	private DatePicker searchDate;
	private TextField tfSearch;
	private FilteredList<CheckOut> fl;
	private HBox searchPane;
	private BorderPane mainCheckOutPane;
	
	
	public void createCheckOutListTable()
	{
		tvCheckOutList = new TableView<CheckOut>();
		
		checkInIdCol = new TableColumn<CheckOut,Integer>("No");
		checkInIdCol.setCellValueFactory(new PropertyValueFactory<CheckOut,Integer>("No"));
		checkInIdCol.setSortable(true);
		
		nameCol = new TableColumn<CheckOut,String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<CheckOut,String>("name"));
		nameCol.setSortable(true);
		
		phoneCol = new TableColumn<CheckOut,String>("Phone Number");
		phoneCol.setCellValueFactory(new PropertyValueFactory<CheckOut,String>("phone"));
		
		checkInCol = new TableColumn<CheckOut,Date>("Check In");
		checkInCol.setCellValueFactory(new PropertyValueFactory<CheckOut,Date>("checkInDate"));
		checkInCol.setSortable(true);
		
		checkOutCol = new TableColumn<CheckOut,Date>("Check Out");
		checkOutCol.setCellValueFactory(new PropertyValueFactory<CheckOut,Date>("checkOutDate"));
		checkOutCol.setSortable(true);
		
		
		inTimeCol = new TableColumn<CheckOut,String>("Check In Time");
		inTimeCol.setCellValueFactory(new PropertyValueFactory<CheckOut,String>("checkInTime"));
	
		outTimeCol = new TableColumn<CheckOut,String>("Check Out Time");
		outTimeCol.setCellValueFactory(new PropertyValueFactory<CheckOut,String>("checkOutTime"));
	
		noOfNightsCol = new TableColumn<CheckOut,Integer>("Nights");
		noOfNightsCol.setCellValueFactory(new PropertyValueFactory<CheckOut,Integer>("noOfNights"));
	
		
		roomNoCol = new TableColumn<CheckOut,String>("Room No");
		roomNoCol.setCellValueFactory(new PropertyValueFactory<CheckOut,String>("roomNo"));
		roomNoCol.setSortable(true);
		
		roomTypeCol = new TableColumn<CheckOut,String>("Room Type");
		roomTypeCol.setCellValueFactory(new PropertyValueFactory<CheckOut,String>("roomType"));
		
		bedTypeCol = new TableColumn<CheckOut,String>("Bed Type");
		bedTypeCol.setCellValueFactory(new PropertyValueFactory<CheckOut,String>("bedType"));
		
		roomChargeCol = new TableColumn<CheckOut,Integer>("Room Charges");
		roomChargeCol.setCellValueFactory(new PropertyValueFactory<CheckOut,Integer>("roomCharges"));
		
		depositCol = new TableColumn<CheckOut,String>("Deposit");
		depositCol.setCellValueFactory(new PropertyValueFactory<CheckOut,String>("deposit"));
		
		reTypeCol = new TableColumn<CheckOut,String>("Reserve Type");
		reTypeCol.setCellValueFactory(new PropertyValueFactory<CheckOut,String>("reserveType"));
		
		receptionNameCol = new TableColumn<CheckOut,String>("Receptionist Name");
		receptionNameCol.setCellValueFactory(new PropertyValueFactory<CheckOut,String>("receptionistName"));
		
		tvCheckOutList.getColumns().add(checkInIdCol);
		tvCheckOutList.getColumns().add(nameCol);
		tvCheckOutList.getColumns().add(phoneCol);
		tvCheckOutList.getColumns().add(checkInCol);
		tvCheckOutList.getColumns().add(checkOutCol);
		tvCheckOutList.getColumns().add(inTimeCol);
		tvCheckOutList.getColumns().add(noOfNightsCol);
		tvCheckOutList.getColumns().add(roomNoCol);
		tvCheckOutList.getColumns().add(roomTypeCol);
		tvCheckOutList.getColumns().add(bedTypeCol);
		tvCheckOutList.getColumns().add(roomChargeCol);
		tvCheckOutList.getColumns().add(depositCol);
		tvCheckOutList.getColumns().add(reTypeCol);
		tvCheckOutList.getColumns().add(receptionNameCol);
		
		tvCheckOutList.setPadding(new Insets(30));
		
		
		setCheckOutData();
	}
	
	
	public void setCheckOutData()
	{
		currentAl=DBHandler.getCurrentCheckOutListTable();
		al = DBHandler.getCheckOutListTable();
		fl = new FilteredList<CheckOut>(FXCollections.observableArrayList(al));
		tvCheckOutList.getItems().addAll(currentAl);
	}
	

	public void createSearchBarCheckOut()
	{
		lTitle = new Label("Check Out Lists");
		lSearch = new Label("Search Box : ");
		lSearch.setStyle("-fx-text-fill:#D6EAF8;-fx-font-size:16;-fx-font-weight:bolder");
		tfSearch = new TextField();
		tfSearch.setOnKeyReleased(e->{
			if(e.getCode()==KeyCode.ENTER)
			{
				filteringCheckOut();
			}
			
		});
		searchDate = new DatePicker();
		searchDate.setOnAction(e->{
			filteringCheckOutWithDate();
		});
		
		
		searchPane = new HBox(20,lTitle,lSearch,tfSearch,searchDate);
		searchPane.setMargin(lTitle, new Insets(0,220,0,0));
		searchPane.setAlignment(Pos.CENTER_RIGHT);
		searchPane.setPadding(new Insets(20));
		
	}
	
	public void createLayoutTableCheckOut()
	{
		
		mainCheckOutPane = new BorderPane();
		mainCheckOutPane.setTop(searchPane);
		mainCheckOutPane.setCenter(tvCheckOutList);
	}
	
	public void filteringCheckOut()
	{
		fl= new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<CheckOut>() {
			@Override
			public boolean test(CheckOut c) {
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
					return value.equalsIgnoreCase(c.getName()) || value.equals(c.getPhone());
				}
				
			}
			
		});
		tvCheckOutList.getItems().clear();
		tvCheckOutList.getItems().addAll(fl);
	}
	

	public void filteringCheckOutWithDate()
	{
		fl= new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<CheckOut>(){

			@Override
			public boolean test(CheckOut co) {
				String value = searchDate.getValue().toString();
				if(value.length()==0)
				{
					return true;
				}
				try {
					return value.equals(co.getCheckOutDate());
	
				}catch(Exception e)
				{
					
				}
				return false;

				
			}
		});
		tvCheckOutList.getItems().clear();
		tvCheckOutList.getItems().addAll(fl);
	}


	public void styleForCheckOutList()
	{
		tvCheckOutList.getStyleClass().add("tv-check-in-list");
		searchPane.getStyleClass().add("search-pane-check-in");
		lTitle.getStyleClass().add("check-in-list-title");
	}

	
	public BorderPane getMainCheckOutPane() {
		createCheckOutListTable();
		createSearchBarCheckOut();
		createLayoutTableCheckOut();
		styleForCheckOutList();
		return mainCheckOutPane;
	}
	

	public static void main(String[] args) {
		Application.launch(args);

	}


}
