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

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.*;

public class ReservationLists{
	private TableView<Reservation> tvReserveList;
	private ArrayList<Reservation> al;
	private TableColumn<Reservation,Integer> idCol,roomChargeCol,noOfNightsCol;
	private TableColumn<Reservation,String> nameCol,phoneCol,roomNoCol,roomTypeCol,
									bedTypeCol,depositCol,reserveTypeCol;
	private TableColumn<Reservation,Date> checkInCol,checkOutCol,reserveDateCol;
	private FilteredList<Reservation> flReserve;
	private TableViewSelectionModel<Reservation> selectionModel;
	
	
	private Label lSearch,lTitle;
	private DatePicker searchDate;
	private TextField tfSearch;
	private BorderPane mainListPane;
	private Button btnCheckIn,btnClear;
	private HBox btnBox,searchPane;
	public void createReserveListTable()
	{
		tvReserveList = new TableView<Reservation>();
		
		idCol = new TableColumn<Reservation,Integer>("No");
		idCol.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("no"));
		idCol.setSortable(true);
		
		nameCol = new TableColumn<Reservation,String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("customerName"));
		nameCol.setSortable(true);
		
		phoneCol = new TableColumn<Reservation,String>("Phone Number");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("customerPhone"));
		
		reserveDateCol = new TableColumn<Reservation,Date>("Reserve Date");
		reserveDateCol.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("reserveDate"));
		reserveDateCol.setSortable(true);
		
		checkInCol = new TableColumn<Reservation,Date>("Check In");
		checkInCol.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("checkInDate"));
		checkInCol.setSortable(true);
		
		checkOutCol = new TableColumn<Reservation,Date>("Check Out");
		checkOutCol.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("checkOutDate"));
		checkOutCol.setSortable(true);
		
		noOfNightsCol = new TableColumn<Reservation,Integer>("Nights");
		noOfNightsCol.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("noOfNights"));
		noOfNightsCol.setSortable(true);
		
		roomNoCol = new TableColumn<Reservation,String>("Room No");
		roomNoCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("roomNo"));
		roomNoCol.setSortable(true);
		
		roomTypeCol = new TableColumn<Reservation,String>("Room Type");
		roomTypeCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("roomType"));
		
		bedTypeCol = new TableColumn<Reservation,String>("Bed Type");
		bedTypeCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("bedType"));
		
		roomChargeCol = new TableColumn<Reservation,Integer>("Room Charges");
		roomChargeCol.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("roomCharges"));
		
		depositCol = new TableColumn<Reservation,String>("Deposit");
		depositCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("deposit"));
		
		reserveTypeCol = new TableColumn<Reservation,String>("Reserve Type");
		reserveTypeCol.setCellValueFactory(new PropertyValueFactory<Reservation,String>("reserveType"));
	
		tvReserveList.getColumns().add(idCol);
		tvReserveList.getColumns().add(nameCol);
		tvReserveList.getColumns().add(phoneCol);
		tvReserveList.getColumns().add(reserveDateCol);
		tvReserveList.getColumns().add(checkInCol);
		tvReserveList.getColumns().add(checkOutCol);
		tvReserveList.getColumns().add(noOfNightsCol);
		tvReserveList.getColumns().add(roomNoCol);
		tvReserveList.getColumns().add(roomTypeCol);
		tvReserveList.getColumns().add(bedTypeCol);
		tvReserveList.getColumns().add(roomChargeCol);
		tvReserveList.getColumns().add(depositCol);
		tvReserveList.getColumns().add(reserveTypeCol);
		tvReserveList.setPadding(new Insets(30));
		
		btnCheckIn = new Button("Check In");
		btnClear = new Button("Delete");
		btnBox = new HBox(30,btnCheckIn,btnClear);
		btnBox.setAlignment(Pos.BOTTOM_CENTER);
		btnBox.setPadding(new Insets(30));

		
		selectionModel = tvReserveList.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		tvReserveList.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				if(e.getClickCount()==2)
				{
					deleteReservation();
				}
				else {
					getReserveDataToCheckIn();
					
				}
			}
		});
		
		setData();
		editData();
	}
	
	public void getReserveDataToCheckIn()
	{
		String name,phone,reType,rType,bType;
		Date outDate,inDate,reDate;
		int roomNo,roomCharges,deposit,reserveRoomId;
		Reservation r = selectionModel.getSelectedItem();
		try {
			name = r.getCustomerName();
			phone = r.getCustomerPhone();
			reDate = r.getReserveDate();
			inDate = r.getCheckInDate();
			outDate = r.getCheckOutDate();
			reType = r.getReserveType();
			rType = r.getRoomType();
			bType = r.getBedType();
			try {
				roomNo = Integer.parseInt(r.getRoomNo());
				roomCharges = r.getRoomCharges();
				deposit = r.getDeposit();
				reserveRoomId = r.getReserveRoomId();
				btnCheckIn.setOnAction(e->{
					Alert alt = new Alert(AlertType.CONFIRMATION);
					alt.setHeaderText("Confirm");
					alt.setContentText("Are you sure to Check In?"+"\nName : "+name
							+"\nroomNo : "+roomNo+"\nCheck In Date : "+inDate+"\nCheck Out Date : "+outDate);
					Optional<ButtonType> ans = alt.showAndWait();
					if(ans.isPresent() && ans.get()==ButtonType.OK)
					{
						DBHandler.insertGuestNameAndPhone(name,phone );
						int gid = DBHandler.getGuestId(name,phone);
						LocalTime currentTime = LocalTime.now();
						System.out.println(currentTime.format(DateTimeFormatter.ofPattern("HH:mm")));
						if(DBHandler.insertCheckIn(reserveRoomId,gid, currentTime.format(DateTimeFormatter.ofPattern("HH:mm"))))
						{
							
						}
						if(DBHandler.addReserveToCheckInDate(reserveRoomId))
						{
							tvReserveList.getItems().remove(r);
//							System.out.println("Successfully Moved to Check In");
						}
					}
					
				});
			}
			catch(NumberFormatException e)
			{
				
			}
			
		}catch(NullPointerException e)
		{
			
		}
		
	}
	
	public void deleteReservation()
	{
		Alert alt = new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirm");
		alt.setContentText("Do you want to delete?");
		Optional<ButtonType> ans = alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			Reservation r= selectionModel.getSelectedItem();
			tvReserveList.getItems().remove(r);
			DBHandler.deleteReservation(r.getId());
//			System.out.println(r.getId());
		}
	}
	
	public void setData()
	{
		al=DBHandler.getAllReservationLists();
		flReserve = new FilteredList<Reservation>(FXCollections.observableArrayList(al));
		tvReserveList.getItems().addAll(flReserve);
	}
	
	public void editData()
	{
		tvReserveList.setEditable(true);
		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nameCol.setOnEditCommit(
		t->t.getTableView().getItems().get(t.getTablePosition().getRow()).setCustomerName(t.getNewValue())
		);
	
	}
	
	public void createSearchBar()
	{
		lTitle = new Label("Reservation Lists");
		lSearch = new Label("Search Box : ");
		tfSearch = new TextField();
		tfSearch.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.ENTER)
			{
				filtering();
			}
			
		});
		searchDate = new DatePicker();
		searchDate.setOnAction(e->{
			filteringDate();
		});
		
		
		searchPane = new HBox(20,lTitle,lSearch,tfSearch,searchDate);
		lTitle.setStyle("-fx-text-fill:#ffffff;-fx-font-size:18");
		lSearch.setStyle("-fx-text-fill:#ffffff");
		searchPane.setMargin(lTitle, new Insets(0,180,0,0));
		searchPane.setAlignment(Pos.CENTER_RIGHT);
		searchPane.setPadding(new Insets(20));
		searchPane.setStyle("-fx-font-weight:bolder;-fx-font-size:13");
	
		
	}
	
	
	
	public void createLayoutTable()
	{
		
		mainListPane = new BorderPane();
		mainListPane.setTop(searchPane);
		mainListPane.setCenter(tvReserveList);
		mainListPane.setBottom(btnCheckIn);
	}
	
	public void filtering()
	{
		flReserve= new FilteredList<>(FXCollections.observableArrayList(al));
		flReserve.setPredicate(new Predicate<Reservation>(){

			@Override
			public boolean test(Reservation r) {
				String value = tfSearch.getText();
				if(value.length()==0)
				{
					return true;
				}
				try {
					String ph = r.getCustomerPhone();
					return value.equals(ph) || value.equals(r.getCustomerName());
	
				}catch(Exception e)
				{
					
				}
				return false;

				
			}
		});
		tvReserveList.getItems().clear();
		tvReserveList.getItems().addAll(flReserve);
	}
	
	public void filteringDate()
	{
		flReserve= new FilteredList<>(FXCollections.observableArrayList(al));
		flReserve.setPredicate(new Predicate<Reservation>(){

			@Override
			public boolean test(Reservation re) {
				LocalDate value = searchDate.getValue();
				Date date = Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant());
				return date.equals(re.getReserveDate());
			}
		});
		tvReserveList.getItems().clear();
		tvReserveList.getItems().addAll(flReserve);
	}

	public void defineReservationListStyle()
	{
		searchPane.getStyleClass().add("search-pane-check-in");
		mainListPane.getStyleClass().add("reserve-list-main");
		tvReserveList.getStyleClass().add("reserve-list");
		btnCheckIn.getStyleClass().add("btn-reserve-check-in");
//		btnClear.getStyleClass().add("clear-room-btn");
	}
	
	
	public BorderPane getmainListPane()
	{
		createReserveListTable();
		createSearchBar();
		createLayoutTable();
		defineReservationListStyle();
		return mainListPane;
	}

	public void setmainListPane(BorderPane mainListPane) {
		this.mainListPane = mainListPane;
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
