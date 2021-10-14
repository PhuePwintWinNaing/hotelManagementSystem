package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.*;

public class UpdateReservation extends Application{
	private int roomTypeId;
	private String rt;
	private Label lCustomerName,lCustomerPh,lCheckInDate,lCheckOutDate,
					  lReserveDate,lNoOfNight,lRoomNo,lRoomType,lBedType,lRoomCharges,
					  lDeposit,lReserveType;
	private TextField tfReserveID,tfDeposit,tfCustomerName,tfCustomerPh,tfRoomCharges,tfRoomNo,tfNoOfNight;
	private DatePicker reserveDate,checkInDate,checkOutDate;
	private ComboBox<RoomNo> comboRoomNo;
	private ComboBox<RoomType> comboRoomType;
	private ComboBox<BedType> comboBedType;
	private ComboBox<ReserveType> comboReserveType;
	private Button btnUpdate;
	private Button btnClear;
	
	private BorderPane mainPane;
	private GridPane reservationPane1;
	private GridPane reservationPane2;
	private FlowPane buttonPane;
	private HBox reservePane;
	
	
	public  void createReservationNodes()
	{
	lCustomerName = new Label("Customer Name");
	lCustomerPh = new Label("Customer Phone");
	lReserveDate = new Label("Reservation Date");
	lCheckInDate = new Label("Check-In-Date");
	lCheckOutDate = new Label("Check-Out-Date");
	lNoOfNight = new Label("Number Of Nights");
	lRoomType = new Label("Room Type");
	lBedType = new Label("Bed Type");
	lRoomNo = new Label("Room No");
	lRoomCharges = new Label("Room Charges");
	lReserveType = new Label("Reservation Type");
	lDeposit = new Label("Deposit");
	
	tfReserveID = new TextField();
	tfCustomerName = new TextField();
	tfCustomerPh = new TextField();
	reserveDate = new DatePicker();
	checkInDate = new DatePicker();
	checkOutDate = new DatePicker();
	tfNoOfNight = new TextField();
	tfRoomNo = new TextField();
	tfRoomCharges = new TextField();
	tfDeposit = new TextField();
	btnUpdate = new Button("Update");
	btnUpdate.setOnAction(e->{
	updateReservation();
	});
	btnClear = new Button("Clear");
	btnClear.setOnAction(e->{;
	updateClear();
	});
	
	comboRoomNo = new ComboBox<>();
	comboBedType = new ComboBox<>();
	ArrayList<RoomType> roomType = DBHandler.getAllRoomType();
	comboRoomType = new ComboBox<RoomType>(FXCollections.observableArrayList(roomType));
	comboRoomType.setOnAction(e->{
		rt = comboRoomType.getValue().toString();
		roomTypeId = comboRoomType.getValue().getRoomTypeId();
		
		ArrayList<RoomNo> roomNo = DBHandler.getRoomNo(roomTypeId);
		comboRoomNo.setItems(FXCollections.observableArrayList(roomNo));

		ArrayList<BedType> bedTypes = DBHandler.getAllBedType(rt);
		comboBedType.setItems(FXCollections.observableArrayList(bedTypes));
		
	});
	ArrayList<ReserveType> reserveType = DBHandler.getReservationType();
	comboReserveType = new ComboBox<ReserveType>(FXCollections.observableArrayList(reserveType));
	
	}
	
	public void clickedCheckIn() {
	int rId,deposit,roomCharges,noOfNight;
	String name,phone,roomNo;
	ReserveType reserveType;
	int extraBed;
	BedType bedType;
	RoomType roomType;
	LocalDate checkIn,checkOut,resDate;
	
	rId = Integer.parseInt(tfReserveID.getText());
	deposit = Integer.parseInt(tfDeposit.getText());
	roomCharges = Integer.parseInt(tfRoomCharges.getText());
	noOfNight = Integer.parseInt(tfNoOfNight.getText());
	name = tfCustomerName.getText();
	phone = tfCustomerPh.getText();
	roomNo = tfRoomNo.getText();
	roomType = comboRoomType.getValue();
	bedType = comboBedType.getValue();
	reserveType = comboReserveType.getValue();
	checkIn = checkInDate.getValue();
	checkOut = checkOutDate.getValue();
	resDate = reserveDate.getValue();	
	
	}
	
	public void clickedSave() {
	
	int deposit,roomCharges,noOfNight,extraBed,noOfGuest,extraPrice;
	String name,phone;
	RoomNo roomNo;
	ReserveType reserveType;
	BedType bedType;
	RoomType roomType;
	LocalDate checkIn,checkOut,resDate;
	
	
	deposit = Integer.parseInt(tfDeposit.getText());
	roomCharges = Integer.parseInt(tfRoomCharges.getText());
	noOfNight = Integer.parseInt(tfNoOfNight.getText());
	name = tfCustomerName.getText();
	phone = tfCustomerPh.getText();
	roomNo = comboRoomNo.getValue();
	roomType = comboRoomType.getValue();
	bedType = comboBedType.getValue();
	reserveType = comboReserveType.getValue();
	checkIn = checkInDate.getValue();
	checkOut = checkOutDate.getValue();
	resDate = reserveDate.getValue();	
//	noOfGuest = Integer.parseInt(tfNoOfGuest.getText());
//	extraPrice = Integer.parseInt(tfExtraBedPrice.getText());
//	
//	System.out.println(deposit+":"+roomCharges+":"+noOfNight+":"+name+":"+
//	phone+":"+roomNo+":"+roomType+":"+bedType+":"+extraBed+":"+reserveType+":"+
//		checkIn+":"+checkOut+":"+resDate+noOfGuest);
	
	
	}
	
	public void updateReservation()
	{
		
	}
	
	public void updateClear()
	{
		tfCustomerName.setText("");
		tfCustomerPh.setText("");
		checkInDate.setValue(null);
		checkOutDate.setValue(null);
		reserveDate.setValue(null);
		tfRoomNo.setText("");
		comboRoomType.setValue(null);
		comboBedType.setValue(null);
		comboReserveType.setValue(null);
		tfDeposit.setText("");
		
	}
	
	public void defineReservationLayouts()
	{
	reservationPane1 = new GridPane();
	
	reservationPane1.add(lCustomerName, 0, 0);
	reservationPane1.add(tfCustomerName, 1, 0);
	
	reservationPane1.add(lCustomerPh, 0, 1);
	reservationPane1.add(tfCustomerPh, 1, 1);
	
	reservationPane1.add(lReserveDate, 0, 2);
	reservationPane1.add(reserveDate, 1, 2);
	
	reservationPane1.add(lCheckInDate, 0, 3);
	reservationPane1.add(checkInDate, 1, 3);
	
	reservationPane1.add(lCheckOutDate, 0, 4);
	reservationPane1.add(checkOutDate, 1, 4);
	
	reservationPane1.add(lNoOfNight, 0, 5);
	reservationPane1.add(tfNoOfNight, 1, 5);
	
	
	reservationPane2 = new GridPane();
	reservationPane2.add(lReserveType, 0, 0);
	reservationPane2.add(comboReserveType, 1, 0);
	
	reservationPane2.add(lRoomType, 0, 1);
	reservationPane2.add(comboRoomType, 1, 1);
	
	reservationPane2.add(lBedType, 0, 2);
	reservationPane2.add(comboBedType,1,2);
	
	reservationPane2.add(lRoomNo, 0, 3);
	reservationPane2.add(comboRoomNo, 1, 3);
	
	reservationPane2.add(lRoomCharges, 0, 4);
	reservationPane2.add(tfRoomCharges, 1, 4);
	
	reservationPane2.add(lDeposit, 0, 5);
	reservationPane2.add(tfDeposit, 1, 5);
	
	buttonPane = new FlowPane(btnUpdate,btnClear);
	btnUpdate.setPadding(new Insets(10));
	btnClear.setPadding(new Insets(10));
	buttonPane.setAlignment(Pos.BOTTOM_RIGHT);
	buttonPane.setHgap(20);
	buttonPane.setVgap(20);
	buttonPane.setPadding(new Insets(20));
	
	reservationPane1.setPadding(new Insets(15));
	reservationPane1.setHgap(30);
	reservationPane1.setVgap(20);
	
	
	reservationPane2.setPadding(new Insets(15));
	reservationPane2.setHgap(30);
	reservationPane2.setVgap(20);
	
	reservePane = new HBox(30,reservationPane1,reservationPane2);
	reservePane.setPadding(new Insets(20));
	reservePane.setAlignment(Pos.CENTER);
	mainPane = new BorderPane();
	mainPane.setCenter(reservePane);
	mainPane.setBottom(buttonPane);
	
	}
	
	public void defineReservationFormStyle()
	{
	mainPane.setId("main-reservation");
	btnUpdate.getStyleClass().add("btn-reserve-save");
	btnClear.getStyleClass().add("btn-reserve-check-in");
	tfReserveID.getStyleClass().add("field-reservation-focus");
	tfDeposit.getStyleClass().add("field-reservation-focus");
	tfCustomerName.getStyleClass().add("field-reservation-focus");
	tfCustomerPh.getStyleClass().add("field-reservation-focus");
	tfRoomCharges.getStyleClass().add("field-reservation-focus");
	
	}
	
	@Override
	public void start(Stage reservationStage) throws Exception {
	createReservationNodes();
	defineReservationLayouts();
	defineReservationFormStyle();
	
	Scene scene = new Scene(mainPane,850,450);
	
	URL url=this.getClass().getResource("style.css");
	scene.getStylesheets().add(url.toExternalForm());
	
	reservationStage.setScene(scene);
	reservationStage.setTitle("Update Reservation");
	reservationStage.show();
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
