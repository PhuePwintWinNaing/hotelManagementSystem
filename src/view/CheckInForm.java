package view;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import model.*;

public class CheckInForm{
	
		private Label lName,lPhone,lCheckInDate,lCheckOutDate,
					  lReserveDate,lRoomNo,lRoomType,lBedType,lRoomCharges,
					  lDeposit,lTitle,lSuccessMsg;
		private  TextField tfDeposit,tfName,tfPhone,tfRoomCharges,
							tfRoomNo;
		private  ComboBox<BedType> tfBedType;
		private  ComboBox<RoomType> tfRoomType;
		private  DatePicker tfCheckOutDate;
		private  DatePicker tfCheckInDate;
		private  DatePicker tfReserveDate;
		
		private Button btnCheckIn,btnClear;
		
		private BorderPane mainPane;
		private GridPane checkInPane1;
		private GridPane checkInPane2;
		private FlowPane buttonPane;
		private HBox checkInPane,topPane;
		
		ReserveData reserve = new ReserveData();
		
		public void createcheckInNodes()
		{
			lTitle = new Label("Check In Form");
			lSuccessMsg = new Label();
		
			lName = new Label("Guest Name");
			lPhone = new Label("Phone Number");
			lReserveDate = new Label("Reserve Date");
			lCheckInDate = new Label("Check-In-Date");
			lCheckOutDate = new Label("Check-Out-Date");
			lRoomType = new Label("Room Type");
			lBedType = new Label("Bed Type");
			lRoomNo = new Label("Room No");
			lRoomCharges = new Label("Room Charges");
			lDeposit = new Label("Deposit");
			
			tfName = new TextField();
			tfPhone = new TextField();
			tfReserveDate = new DatePicker();
			tfCheckInDate = new DatePicker();
			tfCheckOutDate = new DatePicker();
			tfRoomType = new ComboBox<RoomType>();
			tfRoomNo = new TextField();
			tfRoomCharges = new TextField();
			tfBedType = new ComboBox<BedType>();
			tfDeposit = new TextField();
			
			btnCheckIn = new Button("Confirm Check In");
			btnCheckIn.setOnAction(e->{;
//			clickedConfirmCheckIn();
			});
			btnClear = new Button("Clear");
			btnClear.setOnAction(e->{
			
		});
		}
		

		
		public void defineReservationLayouts()
		{
		checkInPane1 = new GridPane();

		checkInPane1.add(lName, 0, 0);
		checkInPane1.add(tfName, 1, 0);
		
		checkInPane1.add(lPhone, 0, 1);
		checkInPane1.add(tfPhone, 1, 1);
		
		checkInPane1.add(lReserveDate, 0, 2);
		checkInPane1.add(tfReserveDate, 1, 2);
		
		checkInPane1.add(lCheckInDate, 0, 3);
		checkInPane1.add(tfCheckInDate, 1, 3);
		
		checkInPane1.add(lCheckOutDate, 0, 4);
		checkInPane1.add(tfCheckOutDate, 1, 4);
		
		checkInPane1.add(lRoomType, 0, 5);
		checkInPane1.add(tfRoomType, 1, 5);
		
		checkInPane2 = new GridPane();
		
		checkInPane2.add(lBedType, 0, 0);
		checkInPane2.add(tfBedType,1,0);
		
		checkInPane2.add(lRoomNo, 0, 1);
		checkInPane2.add(tfRoomNo, 1, 1);
		
		checkInPane2.add(lRoomCharges, 0, 4);
		checkInPane2.add(tfRoomCharges, 1, 4);
		
		checkInPane2.add(lDeposit, 0, 5);
		checkInPane2.add(tfDeposit, 1, 5);
		
		buttonPane = new FlowPane(lSuccessMsg,btnCheckIn);
		buttonPane.setVgap(20);
		buttonPane.setPadding(new Insets(30));
		
		checkInPane1.setPadding(new Insets(15));
		checkInPane1.setHgap(30);
		checkInPane1.setVgap(20);
		
		checkInPane2.setPadding(new Insets(15));
		checkInPane2.setHgap(30);
		checkInPane2.setVgap(20);
		
		checkInPane = new HBox(30,checkInPane1,checkInPane2);
		checkInPane.setPadding(new Insets(20));
		checkInPane.setAlignment(Pos.CENTER);
	
		
		topPane = new HBox(lTitle);
		mainPane = new BorderPane();
		mainPane.setTop(topPane);
		mainPane.setCenter(checkInPane);
		mainPane.setBottom(buttonPane);
		}
		
		public void defineReservationFormStyle()
		{
		mainPane.setId("main-reservation");
		btnCheckIn.getStyleClass().add("btn-reserve-check-in");
		tfDeposit.getStyleClass().add("field-checkIn-focus");
		tfName.getStyleClass().add("field-checkIn-focus");
		tfPhone.getStyleClass().add("field-checkIn-focus");
		tfRoomCharges.getStyleClass().add("field-checkIn-focus");
		topPane.getStyleClass().add("check-in-top-pane");
		
		}
		

		public BorderPane getMainPane()
		{
//			getReserveData();
			createcheckInNodes();
			defineReservationLayouts();
			defineReservationFormStyle();
			return mainPane;
		}
		
	

		
		public static void main(String[] args) {
		
		Application.launch(args);
}

	
}
