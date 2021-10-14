package view;

import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import model.*;

public class AddNewRoom{
	private Label lRoomNo,lRoomType,lTitle,lSuccessMsg;
	private TextField tfRoomId,tfRoomNo;
	private ComboBox<RoomType> comboRoomType;
	private Button btnAdd;
	private Button btnClear;

	private BorderPane mainAddNewRoom;
	private GridPane newRoomPane;
	private HBox titleBox;


	AddNewRoomTable newRoomTable = new AddNewRoomTable();
	
	public void createRoomNodes()
	{
		lTitle = new Label("Add New Rooms");
//		lRoomId = new Label("Room ID");
		lRoomNo = new Label("Room No");
		lRoomType = new Label("Room Type");
		lSuccessMsg = new Label();
		
		tfRoomId = new TextField();
		tfRoomNo = new TextField();
		
		btnAdd = new Button("Add");
		btnAdd.setOnAction(e->{
			clickedAddRoom();
			
		});
		btnClear = new Button("Clear");
		btnClear.setOnAction(e->{
			clickedClearRoom();
		});
	
		ArrayList<RoomType> roomType = DBHandler.getAllRoomType();
		comboRoomType = new ComboBox<RoomType>(FXCollections.observableArrayList(roomType));

	}
	
	public void defineNewRoomLayout()
	{
		newRoomPane = new GridPane();
//		newRoomPane.add(lRoomId,0,0);
//		newRoomPane.add(tfRoomId, 1, 0);
		
		newRoomPane.add(lRoomNo, 0, 0);
		newRoomPane.add(tfRoomNo, 1, 0);
		
		newRoomPane.add(lRoomType, 0, 1);
		newRoomPane.add(comboRoomType, 1, 1);
		
		newRoomPane.add(btnAdd, 0, 2);
		newRoomPane.add(btnClear, 1, 2);
		
		newRoomPane.setHgap(20);
		newRoomPane.setVgap(20);
		newRoomPane.setPadding(new Insets(50));
		
		
		titleBox = new HBox(80,lTitle,lSuccessMsg);
		lTitle.setStyle("-fx-text-fill:#ffffff");
		titleBox.setPadding(new Insets(20));
		
		
		mainAddNewRoom = new BorderPane();
		mainAddNewRoom.setTop(titleBox);
		mainAddNewRoom.setLeft(newRoomPane);
		mainAddNewRoom.setMargin(newRoomPane,new Insets(10, 0,0,30));
		mainAddNewRoom.setMargin(titleBox, new Insets(0,0,20,0));
		mainAddNewRoom.setCenter(newRoomTable.getMainPane());

		mainAddNewRoom.setPadding(new Insets(20));
		
		
		
	}
	
	public void setRoomFormStyle()
	{
		mainAddNewRoom.setId("main-room-form");
		btnAdd.getStyleClass().add("update-room-btn");
		btnClear.getStyleClass().add("clear-room-btn");
		tfRoomId.getStyleClass().add("room-tf-foucs");
		tfRoomNo.getStyleClass().add("room-tf-foucs");
		lSuccessMsg.getStyleClass().add("success-msg");
		titleBox.getStyleClass().add("top-payment-pane");
	
	}

	public void clickedAddRoom() 
	{
		int roomNo,roomType;
		String typeName;
		roomNo = Integer.parseInt(tfRoomNo.getText());
		typeName = comboRoomType.getValue().toString();
		roomType = comboRoomType.getValue().getRoomTypeId();
		Alert alt = new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Are you sure to add New Room");
		alt.setContentText("New Room No : "+roomNo+"\n RoomType :"+typeName);
		Optional<ButtonType> ans = alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			if(DBHandler.insertRoomNoAndType(roomNo, roomType))
			{
				newRoomTable.setRoomListData();
				clickedClearRoom();
//				lSuccessMsg.setText("Successfully Added New Room");
			}
		}
		
	}
	
	public void clickedClearRoom()
	{
//		tfRoomId.setText("");
		tfRoomNo.setText("");
		comboRoomType.setValue(null);
		lSuccessMsg.setText("");
	}
	
	public BorderPane getMainAddNewRoom() {
		createRoomNodes();
		defineNewRoomLayout();
		setRoomFormStyle();
		return mainAddNewRoom;
	}

	public void setMainAddNewRoom(BorderPane mainAddNewRoom) {
		this.mainAddNewRoom = mainAddNewRoom;
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
