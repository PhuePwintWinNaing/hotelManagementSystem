package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import model.*;

public class ReservationForm{
	
	private int roomCharges;
	private String rt,checkInDay;

	private Label lCustomerName,lCustomerPh,lCheckInDate,lCheckOutDate,
				  lReserveDate,lRoomNo,lRoomType,lBedType,lRoomCharges,
				  lDeposit,lReserveType,lHeader1,lSuccessMsg,labelNameFormat,labelPhoneFormat;
	private  TextField tfDeposit,tfCustomerName,tfCustomerPh,
						tfRoomCharges,tfRoomNo;
						
	private  DatePicker reserveDate,checkInDate,checkOutDate;
	private  ComboBox<AvailableRoom> comboRoomNo;
	private  ComboBox<RoomType> comboRoomType;
	private  ComboBox<BedType> comboBedType;
	private  ComboBox<ReserveType> comboReserveType;

	
	private Button btnSave;
	private Button btnClear;
	
	
	private BorderPane mainPane;
	private GridPane reservationPane1;
	private GridPane reservationPane2;
	private HBox reservePane,headerPane;
	private VBox nameFormatBox,phoneFormatBox;
	
	CheckInForm checkIn = new CheckInForm();
	
	
	public void createReservationNodes()
	{
		lHeader1 = new Label("Reservation Form");
		lSuccessMsg = new Label();
		lCustomerName = new Label("Customer Name");
		lCustomerPh = new Label("Customer Phone");
		lReserveDate = new Label("Reservation Date");
		lCheckInDate = new Label("Check-In-Date");
		lCheckOutDate = new Label("Check-Out-Date");
		lRoomType = new Label("Room Type");
		lBedType = new Label("Bed Type");
		lRoomNo = new Label("Room No");
		lRoomCharges = new Label("Room Charges");
		lReserveType = new Label("Reservation Type");
		lDeposit = new Label("Deposit");
		labelNameFormat = new Label();
		labelPhoneFormat = new Label();
		
		
		tfCustomerName = new TextField();
		tfCustomerName.setOnKeyReleased(e->{
			String name = tfCustomerName.getText();
			if(ValidChecker.isValidName(name))
			{
				labelNameFormat.setText("");
				
			}else {
				labelNameFormat.setText("Name format is invalid!");
			}
		});
		tfCustomerPh = new TextField();
		tfCustomerPh.setOnKeyReleased(e->{
			String phone = tfCustomerPh.getText();
			if(ValidChecker.isValidPhoneNumber(phone))
			{
				labelPhoneFormat.setText("");
			}else {
				labelPhoneFormat.setText("Phone format is invalid!");
			}
		});
		reserveDate = new DatePicker();
		reserveDate.setValue(LocalDate.now());

		checkInDate = new DatePicker();
		checkInDate.setDayCellFactory(picker->new DateCell() {
			public void updateItem(LocalDate date,boolean empty)
			{
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty || date.compareTo(today)<0);
			}
		});
		checkOutDate = new DatePicker();
		checkOutDate.setDayCellFactory(picker->new DateCell() {
			public void updateItem(LocalDate date,boolean empty)
			{
				super.updateItem(date, empty);
				LocalDate today = LocalDate.now();
				setDisable(empty || date.compareTo(today)< 0);
			}
		});
		tfRoomNo = new TextField();
		tfRoomCharges = new TextField();
		tfDeposit = new TextField();
		
	
		btnSave = new Button("Save");
		btnSave.setOnAction(e->{
			clickedSave();	
		});
		btnClear = new Button("Clear");
		btnClear.setOnAction(e->{
			clearAllReservationData();
		});
		
		comboRoomNo = new ComboBox<>();
		comboBedType = new ComboBox<>();
		ArrayList<RoomType> roomType = DBHandler.getAllRoomType();
		comboRoomType = new ComboBox<>();
		comboRoomType = new ComboBox<RoomType>(FXCollections.observableArrayList(roomType));

		try {
			comboRoomType.setOnAction(e->{

				int roomTypeId = comboRoomType.getValue().getRoomTypeId();
				
				rt = comboRoomType.getValue().getRoomType();
				checkInDay = checkInDate.getValue().toString();
				
				ArrayList<AvailableRoom> roomNo = DBHandler.getAllAvailableRoom(roomTypeId, checkInDay, checkInDay);
				comboRoomNo.setItems(FXCollections.observableArrayList(roomNo));
				
				roomCharges = DBHandler.getRoomCharges(roomTypeId);
				tfRoomCharges.setText(""+roomCharges);

				ArrayList<BedType> bedTypes = DBHandler.getAllBedType(rt);
				comboBedType.setItems(FXCollections.observableArrayList(bedTypes));
				comboBedType.getSelectionModel().select(0);
				
				
			});
		}catch(NullPointerException e)
		{
			
		}
		
		ArrayList<ReserveType> reserveType = DBHandler.getReservationType();
		comboReserveType = new ComboBox<>(FXCollections.observableArrayList(reserveType));	
		
	}

	
	public void clickedSave() {
		int deposit,roomCharges,staffId,reserveTypeId,bedTypeId;
		String bedType,reserveType,roomType,name,phone,inDay,outDay,resDate;
		try {
			deposit = Integer.parseInt(tfDeposit.getText());
			roomCharges = Integer.parseInt(tfRoomCharges.getText());
			name = tfCustomerName.getText();
			phone = tfCustomerPh.getText();
			reserveType = comboReserveType.getValue().getReserveType();
			reserveTypeId = comboReserveType.getValue().getReserveTypeId();
			inDay = checkInDate.getValue().toString();
			outDay = checkOutDate.getValue().toString();
			resDate = reserveDate.getValue().toString();	
			staffId = LoginForm.getReceptionistId();
			
			if(ValidChecker.isValidName(name) && ValidChecker.isValidPhoneNumber(phone))
			{
				
				Alert alt = new Alert(AlertType.CONFIRMATION);
				alt.setHeaderText("Confirm");
				alt.setContentText("Are you sure to save to reservation list"+
				"\nName : "+name+"\nPhone : "+phone);
				Optional<ButtonType> ans = alt.showAndWait();
				if(ans.isPresent() && ans.get()==ButtonType.OK)
				{
					DBHandler.insertCustomer(name, phone);
					int cId= DBHandler.getCurrentCustomerId(name,phone);
					DBHandler.insertReservation(resDate, deposit, reserveTypeId,staffId,cId);
					int rId = DBHandler.getCurrentReserveId(resDate,deposit,reserveTypeId,staffId,cId);
					int roomId = getRoomId();
//					lSuccessMsg.setText("Can't save invalid format data");
					if(DBHandler.insertReserveRooms(rId, roomId, inDay, outDay))
					{
						lSuccessMsg.setText("Successfully Save to Reservation Lists");
					}
					
				}
				
			} 
				
			
			}
			catch(NumberFormatException e)
			{
				lSuccessMsg.setText("Can't save invalid format data");
				Alert alt = new Alert(AlertType.ERROR);
				alt.setHeaderText("Invalid Data Format");
				alt.setContentText("Please try again with valid data format");
				Optional<ButtonType> ans = alt.showAndWait();
				if(ans.isPresent() && ans.get()==ButtonType.OK)
				{
					clearAllReservationData();
				}
			}
		}
	
	public int getRoomId()
	{
		int roomNo = Integer.parseInt(comboRoomNo.getValue().toString());
		int id= DBHandler.getRoomId(roomNo);
		return id;
	}
	
	public void clearAllReservationData()
	{
		tfCustomerName.setText("");
		tfCustomerPh.setText("");
		checkInDate.setValue(null);
		checkOutDate.setValue(null);
		reserveDate.setValue(null);
		comboRoomNo.setValue(null);
		tfRoomCharges.setText("");
		tfDeposit.setText("");
		tfRoomNo.setText("");
		comboRoomType.setValue(null);
		comboBedType.setValue(null);
		comboReserveType.setValue(null);
		lSuccessMsg.setText("");
		labelNameFormat.setText("");
		labelPhoneFormat.setText("");
	}

	public void defineReservationLayouts()
	{
		headerPane = new HBox(20,lHeader1,lSuccessMsg);
		lHeader1.setStyle("-fx-text-fill:#ffffff");
		headerPane.setMargin(lSuccessMsg,new Insets(0,0,0,80));
		headerPane.setPadding(new Insets(20));
		headerPane.setAlignment(Pos.CENTER_LEFT);
				
		reservationPane1 = new GridPane();
		
		reservationPane1.add(lCustomerName, 0, 0);
		nameFormatBox = new VBox(tfCustomerName,labelNameFormat);
		reservationPane1.add(nameFormatBox, 1, 0);
		
		reservationPane1.add(lCustomerPh, 0, 1);
		phoneFormatBox = new VBox(tfCustomerPh,labelPhoneFormat);
		reservationPane1.add(phoneFormatBox, 1, 1);
		
		reservationPane1.add(lReserveDate, 0, 2);
		reservationPane1.add(reserveDate, 1, 2);
		
		reservationPane1.add(lCheckInDate, 0, 3);
		reservationPane1.add(checkInDate, 1, 3);
		
		reservationPane1.add(lCheckOutDate, 0, 4);
		reservationPane1.add(checkOutDate, 1, 4);
		
		reservationPane1.add(lReserveType, 0, 5);
		reservationPane1.add(comboReserveType, 1, 5);
		
		
		reservationPane2 = new GridPane();
	
		reservationPane2.add(lRoomType, 0,0);
		reservationPane2.add(comboRoomType, 1, 0);
		
		reservationPane2.add(lBedType, 0, 1);
		reservationPane2.add(comboBedType,1,1);

		
		reservationPane2.add(lRoomNo, 0, 2);
		reservationPane2.add(comboRoomNo, 1, 2);
		
		reservationPane2.add(lRoomCharges, 0, 3);
		reservationPane2.add(tfRoomCharges, 1, 3);
		
		reservationPane2.add(lDeposit, 0, 4);
		reservationPane2.add(tfDeposit, 1, 4);
		
		reservationPane2.add(btnSave, 0, 6);
		reservationPane2.add(btnClear, 1, 6);
		
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
		mainPane.setTop(headerPane);
		mainPane.setCenter(reservePane);
		mainPane.setMargin(reservePane, new Insets(20,0,0,0));
		
	}
	
	public void defineReservationFormStyle()
	{
		mainPane.setId("main-reservation");
		btnSave.getStyleClass().add("btn-check-out");
		btnClear.getStyleClass().add("clear-room-btn");
		tfDeposit.getStyleClass().add("field-reservation-focus");
		tfCustomerName.getStyleClass().add("field-reservation-focus");
		tfCustomerPh.getStyleClass().add("field-reservation-focus");
		tfRoomCharges.getStyleClass().add("field-reservation-focus");
		headerPane.getStyleClass().add("check-out-top-pane");
		lSuccessMsg.getStyleClass().add("success-msg");
		comboRoomNo.getStyleClass().add("combo-box");
		comboRoomType.getStyleClass().add("combo-box");
		comboBedType.getStyleClass().add("combo-box");
		comboReserveType.getStyleClass().add("combo-box");
		labelNameFormat.getStyleClass().add("err");
		labelPhoneFormat.getStyleClass().add("err");
		
		
	}
	
	public BorderPane getMainPane() {
		createReservationNodes();
		defineReservationLayouts();
		defineReservationFormStyle();
		return mainPane;
	}

	
	public static void main(String[] args) {
		
		Application.launch(args);
	}

}
