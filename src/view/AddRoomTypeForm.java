package view;

import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;

import model.*;

public class AddRoomTypeForm{

	private Label lRoomType,lRoomCharges,lBedType,lTitle,lSuccessMsg;
	private TextField tfRoomType,tfBedType,tfRoomCharges;

	private Button btnAdd,btnClear;
	private GridPane typePane;
	private BorderPane mainPane;
	private HBox titleBox;
	
	
	RoomTypeList roomTypeList = new RoomTypeList();
	
	public void createRoomTypeNodes()
	{
		lSuccessMsg = new Label();
		lTitle = new Label("Add New Room Type");
		lRoomType = new Label("Room Type");
		lBedType = new Label("Bed Type");
		lRoomCharges = new Label("Room Charges");
		tfRoomType = new TextField();
		tfBedType = new TextField();
		tfRoomCharges = new TextField();
		btnAdd = new Button("Add");
		btnAdd.setOnAction(e->{
			add();
			roomTypeClear();
		});

		btnClear = new Button("Clear");
		btnClear.setOnAction(e->{
			roomTypeClear();
		});

	}
	
	public void add() {
		String roomType,bedType;
		int price,bid;
		roomType = tfRoomType.getText();
		bedType = tfBedType.getText();
		try {
			price = Integer.parseInt(tfRoomCharges.getText());
//			System.out.println(roomType+":"+bedType+":"+price);
			DBHandler.insertBedType(bedType);
			bid = DBHandler.getCurrentBedTypeId(bedType);
//			System.out.println(bid);
			Alert alt = new Alert(AlertType.CONFIRMATION);
			alt.setHeaderText("Confirm");
			alt.setContentText("Are you add new room type ?"+"\n Room Type : "+roomType+"\nBed Type : "+bedType
					+"\nRoom Charges : "+price);
			Optional<ButtonType> ans = alt.showAndWait();
			if(ans.isPresent() && ans.get()==ButtonType.OK)
			{
				if(	DBHandler.insertRoomTypeBedType(roomType, price, bid))
				{
					new RoomTypeList().setRoomListData();
					lSuccessMsg.setText("Successfully added new room type");
				}
			}
		}catch(NumberFormatException e)
		{
			
		}
		
		
	}
	
	public void roomTypeClear() {
		tfRoomType.setText("");
		tfBedType.setText("");
		tfRoomCharges.setText("");
		lSuccessMsg.setText("");
	}
	
	public void defineNewRoomLayout()
	{
	
		typePane = new GridPane();
		typePane.add(lRoomType, 0, 0);
		typePane.add(tfRoomType, 1, 0);
		
		typePane.add(lBedType, 0, 1);
		typePane.add(tfBedType, 1, 1);
		
		typePane.add(lRoomCharges, 0, 2);
		typePane.add(tfRoomCharges, 1, 2);
		
		typePane.add(btnAdd, 0, 3);
		typePane.add(btnClear, 1, 3);
		
		typePane.setVgap(30);
		typePane.setHgap(20);
		typePane.setPadding(new Insets(50));
		
		titleBox = new HBox(80,lTitle,lSuccessMsg);
		lTitle.setStyle("-fx-text-fill:#ffffff");
		titleBox.setPadding(new Insets(20));
		
		mainPane = new BorderPane();
		mainPane.setTop(titleBox);
		
		mainPane.setMargin(titleBox, new Insets(0,0,20,0));
		mainPane.setLeft(typePane);
		mainPane.setMargin(typePane, new Insets(10,0,0,30));
		mainPane.setRight(roomTypeList.getTvRoomTypeTable());
		mainPane.setMargin(roomTypeList.getTvRoomTypeTable(), new Insets(0,20,0,0));
		
		mainPane.setPadding(new Insets(20));
	}

	public void defineStyleForAddNewRoomType()
	{
		mainPane.setId("main-room-form");
		btnAdd.getStyleClass().add("update-room-btn");
		btnClear.getStyleClass().add("clear-room-btn");
		titleBox.getStyleClass().add("top-payment-pane");
		lSuccessMsg.getStyleClass().add("success-msg");
	}
	
	public BorderPane getMainPane() {
		createRoomTypeNodes();
		defineNewRoomLayout();
		defineStyleForAddNewRoomType();
		return mainPane;
	}

	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}

	public static void main(String[] args) {
		
		Application.launch(args);
	}



}
