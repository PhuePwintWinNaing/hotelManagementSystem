package view;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import model.*;


public class CheckOutForm{
	
	
	private TableView<CheckOut> tvCheckOut;
	private ArrayList<CheckOutData> al;
	private TableColumn<CheckOut,Integer> idCol,noOfNightsCol,totalAmountCol;
	private TableColumn<CheckOut,String> nameCol,phoneCol,roomNoCol,roomTypeCol,bedTypeCol;
	private TableColumn<CheckOut, Date> checkInCol,checkOutCol;
	
	private Label lRoomNo,lPhone,lName,lInDate,lOutDate,lRoomCharges,lNoOfNight,
				  lDeposit,lTotalCharges,lBalance,lTitle,lPaymentAmount,lPaymentType,lSuccessMsg;
	private TextField tfRoomNo,tfPhone,tfName,tfInDate,tfOutDate,tfRoomCharges,tfNoOfNight,
					  tfDeposit,tfTotalCharges,tfBalance,tfPaymentAmount;
	private ComboBox<PaymentType> comboPaymentType;
	private ArrayList<PaymentType> paytype = new ArrayList<PaymentType>();
	private Button btnSearch,btnCheckOut,btnClear,btnPaid;
	private BorderPane mainCheckOutPane;
	private VBox nameBox,phoneBox,checkInBox,checkOutBox,priceBox,noOfNightBox,depositBox,
				 totalChargesBox,balanceBox,paymentBox,payTypeBox,vbox;
	private HBox titleBox,roomNoBox,box1,box2,box3,btnBox,payBox;
	private int rrid,cid;
	private int night=1;

	

	public void createCheckOutNodes()
	{
		lSuccessMsg = new Label("");
		lTitle = new Label("Check Out Form");
		lRoomNo = new Label("Room No");
		lPhone = new Label("Phone Number");
		lName = new Label("Guest Name");
		lInDate = new Label("Check In Date");
		lOutDate = new Label("Check Out Date");
		lRoomCharges = new Label("Room Charges");
		lNoOfNight = new Label("No Of Night");
		lDeposit = new Label("Deposit");
		lTotalCharges = new Label("Total Charges");
		lBalance = new Label("Credits");
		lPaymentAmount = new Label("Payment Amount");
		lPaymentType = new Label("Payment Type");
		tfRoomNo = new TextField();
		tfPhone = new TextField();
		tfName = new TextField();
		tfInDate = new TextField();
		tfOutDate = new TextField();
		tfRoomCharges = new TextField();
		tfNoOfNight = new TextField();
		tfDeposit = new TextField();
		tfTotalCharges = new TextField();
		tfBalance = new TextField();
		tfBalance.setStyle("-fx-text-fill:red");
		tfPaymentAmount = new TextField();

		btnPaid = new Button("Paid");
		btnPaid.getStyleClass().add("btn-paid");
		btnPaid.setOnAction(e->{
			tfPaymentAmount.getStyleClass().add("payment-amount");
			tfBalance.setStyle("-fx-text-fill:#000000");
			paidPayment();
			System.out.println(tfBalance.getText());
			if(tfBalance.getText().equals("0"))
			{
				
				btnCheckOut.setDisable(false);
			}else {
				btnCheckOut.setDisable(true);
				tfBalance.setStyle("-fx-text-fill:red");
			}
			
		});
		
		comboPaymentType = new ComboBox<PaymentType>();
		btnSearch = new Button("Search");
		btnSearch.setOnAction(e->{
			getCheckInDataToCheckOut();
			
		});
		
		btnCheckOut = new Button("Check Out");
		btnCheckOut.setOnAction(e->{
			LocalTime currentTime = LocalTime.now();
			System.out.println(currentTime.format(DateTimeFormatter.ofPattern("HH:mm")));
			if(DBHandler.addCheckOutTime(cid, currentTime.format(DateTimeFormatter.ofPattern("HH:mm"))))
			{
				System.out.println("Successfully Inseted");
			}
			if(DBHandler.addCheckOutList(cid))
			{
				System.out.println("Successfully Check Out this room");
				lSuccessMsg.setText("Successfully Check Out");
			}
			clearTextField();
		});
		
		if(tfBalance.getText().equals("0"))
		{
			btnCheckOut.setDisable(false);
		}else {
			btnCheckOut.setDisable(true);
		}
		
		btnClear = new Button("Clear");
		btnClear.setOnAction(e->{
			clearCheckOut();
		});
		
		paytype =DBHandler.getAllPaymentTypes();
		comboPaymentType = new ComboBox<>(FXCollections.observableArrayList(paytype));
		
	}
	
	public void getCheckInDataToCheckOut()
	{
		int outRoom;
		outRoom = Integer.parseInt(tfRoomNo.getText());
		al = DBHandler.getCheckOutData(outRoom);
		for(CheckOutData cod:al)
		{
			rrid = cod.getReserveRoomId();
			cid = cod.getCid();
			tfName.setText(cod.getName());
			tfName.setEditable(false);
			tfPhone.setText(cod.getPhone());
			tfPhone.setEditable(false);
			tfInDate.setText(cod.getCheckIn());
			tfInDate.setEditable(false);
			tfOutDate.setText(cod.getCheckOut());
			tfOutDate.setEditable(true);
			tfOutDate.setOnAction(e->{
				String changeDate = tfOutDate.getText();
				DBHandler.updateCheckOutDate(changeDate, rrid);
				{
					System.out.println("Successfull change check out date");
				}
				
			});
			
//			night = DBHandler.getNoOfNight(tfInDate.getText(), tfOutDate.getText());
			tfNoOfNight.setText(""+cod.getNoOfNight());
			tfNoOfNight.setEditable(true);
			
			tfRoomCharges.setText(""+cod.getRoomCharges()*night);
			tfRoomCharges.setEditable(false);
			tfDeposit.setText(cod.getDeposit()+"");
			tfDeposit.setEditable(false);
			tfTotalCharges.setText(""+cod.getTotalCharges());
			tfTotalCharges.setEditable(false);
			tfBalance.setText(""+cod.getBalance());
			tfBalance.setEditable(false);
			
			System.out.println(cod);
		}
		
	}
	
	public void paidPayment()
	{
		int payAmount,payTypeId,credit,balance;
		String payDate,payType;
		payAmount = Integer.parseInt(tfPaymentAmount.getText());
		balance = Integer.parseInt(tfBalance.getText());
		credit = balance-payAmount;
		tfBalance.setText(credit+"");
		payDate = tfOutDate.getText();
		payType = comboPaymentType.getValue().toString();
		payTypeId = DBHandler.getPaymentTypeId(payType);
		DBHandler.insertPayment(payDate, payAmount, payTypeId, rrid);
		
	}
	
	public void clearCheckOut()
	{
		tfRoomNo.setText("");
		tfName.setText("");
		tfPhone.setText("");
		tfInDate.setText("");
		tfOutDate.setText("");
		tfNoOfNight.setText("");
		tfRoomCharges.setText("");
		tfDeposit.setText("");
		tfTotalCharges.setText("");
		tfBalance.setText("");
		lSuccessMsg.setText("");
		tfPaymentAmount.setText("");
		comboPaymentType.setValue(null);
	}
	
	public void clearTextField()
	{
		tfRoomNo.setText("");
		tfName.setText("");
		tfPhone.setText("");
		tfInDate.setText("");
		tfOutDate.setText("");
		tfNoOfNight.setText("");
		tfRoomCharges.setText("");
		tfDeposit.setText("");
		tfTotalCharges.setText("");
		tfBalance.setText("");
		tfPaymentAmount.setText("");
		comboPaymentType.setValue(null);
	}
	
	public void createCheckOutLayout()
	{
		titleBox = new HBox(lTitle);
		lTitle.setStyle("-fx-text-fill:#ffffff");
		nameBox = new VBox(10,lName,tfName);
		phoneBox = new VBox(10,lPhone,tfPhone);
		checkInBox = new VBox(10,lInDate,tfInDate);
		checkOutBox = new VBox(10,lOutDate,tfOutDate);
		priceBox = new VBox(10,lRoomCharges,tfRoomCharges);
		noOfNightBox = new VBox(10,lNoOfNight,tfNoOfNight);
		depositBox = new VBox(10,lDeposit,tfDeposit);
		totalChargesBox = new VBox(10,lTotalCharges,tfTotalCharges);
		balanceBox = new VBox(10,lBalance,tfBalance);
		payBox = new HBox(7,tfPaymentAmount,btnPaid);
		paymentBox = new VBox(10,lPaymentAmount,payBox);
		payTypeBox = new VBox(10,lPaymentType,comboPaymentType);
		
		
		roomNoBox = new HBox(20,lRoomNo,tfRoomNo,btnSearch);
		box1 = new HBox(30,nameBox,phoneBox,checkInBox,checkOutBox);
		box2 = new HBox(30,noOfNightBox,priceBox,totalChargesBox,depositBox);
		box3 = new HBox(30,payTypeBox,paymentBox,balanceBox);
		btnBox = new HBox(20,btnCheckOut,btnClear);
		vbox = new VBox(30,roomNoBox,box1,box2,box3,btnBox);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(20));
		
		mainCheckOutPane = new BorderPane();
		mainCheckOutPane.setTop(titleBox);
		mainCheckOutPane.setCenter(vbox);
		mainCheckOutPane.setBottom(tvCheckOut);
		
	}
	
	public void createCheckOutStyle()
	{
		mainCheckOutPane.setId("main-check-out");
		vbox.getStyleClass().add("rom-no-box");
		box1.getStyleClass().add("room-no-box");
		box2.getStyleClass().add("room-no-box");
		box3.getStyleClass().add("room-no-box");
		btnBox.getStyleClass().add("room-no-box");
		roomNoBox.getStyleClass().add("room-no-box");
		btnCheckOut.getStyleClass().add("btn-check-out");
		btnSearch.getStyleClass().add("btn-check-out");
		btnClear.getStyleClass().add("btn-check-out");
		titleBox.getStyleClass().add("check-out-top-pane");
		
	}
	
	public void createCheckOutTable()
	{
		tvCheckOut = new TableView<CheckOut>();
		
		idCol = new TableColumn<CheckOut,Integer>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<CheckOut,Integer>("reId"));
		idCol.setSortable(true);
		
		nameCol = new TableColumn<CheckOut,String>("Guest Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<CheckOut,String>("name"));
		nameCol.setSortable(true);
		
		phoneCol = new TableColumn<CheckOut,String>("Phone Number");
		phoneCol.setCellValueFactory(new PropertyValueFactory<CheckOut,String>("phone"));
		
		roomNoCol = new TableColumn<CheckOut,String>("Room No");
		roomNoCol.setCellValueFactory(new PropertyValueFactory<CheckOut,String>("roomNo"));
		roomNoCol.setSortable(true);
		
		roomTypeCol = new TableColumn<CheckOut,String>("Room Type");
		roomTypeCol.setCellValueFactory(new PropertyValueFactory<CheckOut,String>("roomType"));
		
		bedTypeCol = new TableColumn<CheckOut,String>("Bed Type");
		bedTypeCol.setCellValueFactory(new PropertyValueFactory<CheckOut,String>("bedType"));
		
		checkInCol = new TableColumn<CheckOut,Date>("Check In");
		checkInCol.setCellValueFactory(new PropertyValueFactory<CheckOut,Date>("checkIn"));
		checkInCol.setSortable(true);
		
		checkOutCol = new TableColumn<CheckOut,Date>("Check Out");
		checkOutCol.setCellValueFactory(new PropertyValueFactory<CheckOut,Date>("checkOut"));
		checkOutCol.setSortable(true);
		
		noOfNightsCol = new TableColumn<CheckOut,Integer>("Nights");
		noOfNightsCol.setCellValueFactory(new PropertyValueFactory<CheckOut,Integer>("noOfNight"));
		noOfNightsCol.setSortable(true);
	
		totalAmountCol = new TableColumn<CheckOut,Integer>("Total Charges");
		totalAmountCol.setCellValueFactory(new PropertyValueFactory<CheckOut,Integer>("totalCharges"));
		totalAmountCol.setSortable(true);
		
		tvCheckOut.getColumns().add(idCol);
		tvCheckOut.getColumns().add(nameCol);
		tvCheckOut.getColumns().add(phoneCol);
		tvCheckOut.getColumns().add(roomNoCol);
		tvCheckOut.getColumns().add(roomTypeCol);
		tvCheckOut.getColumns().add(bedTypeCol);
		tvCheckOut.getColumns().add(checkInCol);
		tvCheckOut.getColumns().add(checkOutCol);
		tvCheckOut.getColumns().add(noOfNightsCol);
		tvCheckOut.getColumns().add(totalAmountCol);
//		setData();
	}


	
	
	public BorderPane getMainCheckOutPane() {
		createCheckOutNodes();
		createCheckOutLayout();
		createCheckOutStyle();
		return mainCheckOutPane;
	}

	public void setMainCheckOutPane(BorderPane mainCheckOutPane) {
		this.mainCheckOutPane = mainCheckOutPane;
	}


	public static void main(String[] args) {
		Application.launch(args);

	}
}
