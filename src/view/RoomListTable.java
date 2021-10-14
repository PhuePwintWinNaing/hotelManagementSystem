package view;
import java.util.ArrayList;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import javafx.scene.layout.*;

import model.*;


public class RoomListTable{
	private TableView<RoomList> tvRoomList;
	private ArrayList<RoomList> al;
	private TableColumn<RoomList,Integer> idCol,roomNoCol,roomChargesCol;
	private TableColumn<RoomList,String> roomTypeCol,bedTypeCol;
	private FilteredList<RoomList> fl; 
	private TableViewSelectionModel<RoomList> selectionModel;
	
	private Label lSearch,lTitle;
	private TextField tfSearch;
	private BorderPane mainPane,updateBox;
	private FlowPane searchPane;
	
	
	private Label lRoomNo,lRoomCharges,lRoomType,lBedType;
	private TextField tfRoomCharges;
	private ComboBox<RoomNo> comboRoomNo;
	private ComboBox<RoomType> comboRoomType;
	private ComboBox<BedType> comboBedType;
	
	private Button btnUpdate,btnClear;
	private GridPane updatePane;
	private ArrayList<RoomType> roomType = new ArrayList<RoomType>();
	private ArrayList<BedType> bedTypes = new ArrayList<BedType>();
	private ArrayList<RoomNo> roomNo = new ArrayList<RoomNo>();
	
	public void createRoomListTable()
	{
		tvRoomList = new TableView<RoomList>();
		
		idCol = new TableColumn<RoomList,Integer>("No");
		idCol.setCellValueFactory(new PropertyValueFactory<RoomList,Integer>("No"));
		idCol.setSortable(true);
		
		roomNoCol = new TableColumn<RoomList,Integer>("Room No");
		roomNoCol.setCellValueFactory(new PropertyValueFactory<RoomList,Integer>("roomNo"));
		roomNoCol.setSortable(true);
		
		roomTypeCol = new TableColumn<RoomList,String>("Room Type");
		roomTypeCol.setCellValueFactory(new PropertyValueFactory<RoomList,String>("roomType"));
		roomTypeCol.setSortable(true);
		bedTypeCol = new TableColumn<RoomList,String>("Bed Type");
		bedTypeCol.setCellValueFactory(new PropertyValueFactory<RoomList,String>("bedType"));
		bedTypeCol.setSortable(true);
		roomChargesCol = new TableColumn<RoomList,Integer>("Room Charges");
		roomChargesCol.setCellValueFactory(new PropertyValueFactory<RoomList,Integer>("roomCharges"));
		roomChargesCol.setSortable(true);
		
		tvRoomList.getColumns().add(idCol);
		tvRoomList.getColumns().add(roomNoCol);
		tvRoomList.getColumns().add(roomTypeCol);
		tvRoomList.getColumns().add(bedTypeCol);
		tvRoomList.getColumns().add(roomChargesCol);
		
		tvRoomList.setMaxWidth(600);
		tvRoomList.setPadding(new Insets(30));

	    ScrollPane sp = new ScrollPane(tvRoomList);
	    sp.setFitToHeight(true);
	    sp.setFitToWidth(true);
		
		selectionModel = tvRoomList.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		tvRoomList.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				if(e.getClickCount()==2)
				{
					deleteRoom();
				}
				else {
					
					setUpdateRoomInfo();
				}
			}
		});
		
		setRoomListData();
		
	}
	
	public void createUpdateRoomPane() {
		
		lRoomNo = new Label("Room No");
		lRoomType = new Label("Room Type");
		lBedType = new Label("Bed Type");
		lRoomCharges = new Label("Room Charges");
		tfRoomCharges = new TextField();
		btnUpdate = new Button("Update");
		btnUpdate.setOnAction(e->{
			updateRoomInfo();
		});
		btnClear = new Button("Clear");
		btnClear.setOnAction(e->{
			clearRoomInfo();
		});
		
		
		roomType = DBHandler.getAllRoomType();
		comboRoomType = new ComboBox<>(FXCollections.observableArrayList(roomType));
		
		roomNo = DBHandler.getAllRoomNo();
		comboRoomNo = new ComboBox<>(FXCollections.observableArrayList(roomNo));
		
		bedTypes = DBHandler.getAllBedType();
		comboBedType = new ComboBox<>(FXCollections.observableArrayList(bedTypes));
	
		updatePane = new GridPane();
		
		
		updatePane.add(lRoomNo, 0, 0);
		updatePane.add(comboRoomNo, 1, 0);
		
		updatePane.add(lRoomType, 0, 1);
		updatePane.add(comboRoomType,1,1);
		
		updatePane.add(lBedType, 0, 2);
		updatePane.add(comboBedType, 1, 2);
		
		updatePane.add(lRoomCharges, 0, 3);
		updatePane.add(tfRoomCharges, 1, 3);
		
		updatePane.add(btnUpdate, 0, 4);
		updatePane.add(btnClear, 1, 4);
		
		updateBox = new BorderPane();
		updateBox.setCenter(updatePane);
		updatePane.setHgap(20);
		updatePane.setVgap(20);
		updatePane.setPadding(new Insets(20));
		
	}
	
	public void setUpdateRoomInfo()
	{
		RoomList r = selectionModel.getSelectedItem();
		try {
			tfRoomCharges.setText(""+r.getRoomCharges());
			
			int i=0;
			for(RoomType rt:roomType)
			{
				if(rt.getRoomType().equals(r.getRoomType()))
				{
					break;
				}
				i++;
			}
			comboRoomType.getSelectionModel().select(i);
			
			
			int b=0;
			for(BedType bt:bedTypes)
			{
				if(bt.getBedTypes().equals(r.getBedType()))
				{
					break;
				}
				b++;
			}
			comboBedType.getSelectionModel().select(b);

			
			int rno = 0;
			for(RoomNo no:roomNo)
			{
				if(no.getRoomNo()==r.getRoomNo())
				{
					break;
				}
				rno++;
			}
			
			comboRoomNo.getSelectionModel().select(rno);
		}catch(NullPointerException e)
		{
			
		}

	}
	
	public void updateRoomInfo()
	{
		RoomList r = selectionModel.getSelectedItem();
		int id,rno,rTypeId,rCharges,bTypeId;
		String rType,bType;
		id = r.getRoomId();
		rno = comboRoomNo.getValue().getRoomNo();
		rType = comboRoomType.getValue().toString();
		rTypeId = comboRoomType.getValue().getRoomTypeId();
		bType = comboBedType.getValue().toString();
		bTypeId = DBHandler.getBedTypeId(bType);
		rCharges = Integer.parseInt(tfRoomCharges.getText());
//		System.out.println(id);
//		System.out.println(rno);
//		System.out.println(rType);
//		System.out.println(rTypeId);
//		System.out.println(bType);
//		System.out.println(bTypeId);
//		System.out.println(rCharges);
		
		
		Alert alt = new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirm");
		alt.setContentText("Are you sure to update room data");
		Optional<ButtonType> ans = alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{

			if(DBHandler.updateRoomNoAndRoomType(id,rno, rTypeId) || 
					DBHandler.updateRoomBedType(rTypeId,rType, rCharges,bTypeId))
			{
				setRoomListData();
				setUpdateRoomInfo();
				clearRoomInfo();
			}
		}
		
		
	}
	
	public void deleteRoom()
	{
		RoomList rl= selectionModel.getSelectedItem();
		Alert alt = new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirm");
		alt.setContentText("Are you sure to delete");
		Optional<ButtonType> ans = alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			
			tvRoomList.getItems().remove(rl);
			System.out.println(rl.getRoomId());
//			DBHandler.deleteRoomNoAndType(rl.getRoomId());
//			DBHandler.deleteRoom(rl.getRoomId());
			String bType = rl.getBedType();
			int bTypeId = DBHandler.getBedTypeId(bType);
//			DBHandler.deleteBedTypeId(bTypeId);
			String rType = rl.getRoomType();
			int rTypeId = DBHandler.getRoomTypeId(rType);
			DBHandler.deleteRoomNoAndType(rl.getRoomId());
			DBHandler.deleteRoomTypeChargesBedType(rTypeId);
		}
	}
	
	public void clearRoomInfo()
	{
		
		comboRoomNo.setValue(null);
		comboRoomType.setValue(null);
		comboBedType.setValue(null);
		tfRoomCharges.setText("");
	}
	
	public void setRoomListData()
	{
		al=DBHandler.getAllRoomInfo();
		fl = new FilteredList<>(FXCollections.observableArrayList(al));
		tvRoomList.getItems().clear();
		tvRoomList.getItems().addAll(fl);
	}
	
	public void createSearchBar()
	{
		lTitle = new Label("Room Information");
		lSearch = new Label("Search Box : ");
		tfSearch = new TextField();
		tfSearch.setOnKeyReleased(e->{
			if(e.getCode()==KeyCode.ENTER)
			{
				filteringRoom();
				
			}
			
		});
		
		searchPane = new FlowPane(20,20,lTitle,lSearch,tfSearch);
		searchPane.setMargin(lTitle, new Insets(0,430,0,0));
		searchPane.setAlignment(Pos.CENTER_RIGHT);
		searchPane.setPadding(new Insets(20));
	}
	
	
	public void createLayoutRoomListTable()
	{
		mainPane = new BorderPane();
		mainPane.setTop(searchPane);
		mainPane.setCenter(updatePane);
		mainPane.setRight(tvRoomList);
		
		
	}
	
	public void filteringRoom()
	{
		fl= new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<RoomList>(){

			@Override
			public boolean test(RoomList room) {
				String value = tfSearch.getText();
				if(value.length()==0)
				{
					return true;
				}
				try {
					int rno = Integer.parseInt(value);
					return rno==room.getRoomNo();
				}catch(Exception e)
				{

					return room.getRoomType().indexOf(value)>=0;
				}

			}
		});
		tvRoomList.getItems().clear();
		tvRoomList.getItems().addAll(fl);
	}
	
	
	public void setRoomListTableStyle() {
		mainPane.getStyleClass().add("main-room-list-table");
		searchPane.getStyleClass().add("search-pane-room-list");
		updatePane.getStyleClass().add("update-pane-room-list");
		tvRoomList.getStyleClass().add("tv-room-list");
		btnUpdate.getStyleClass().add("update-room-btn");
		btnClear.getStyleClass().add("clear-room-btn");
		lTitle.getStyleClass().add("room-info-title");
		
	}
	
	public BorderPane getMainPane() {
		createRoomListTable();
		createSearchBar();
		createLayoutRoomListTable();
		createUpdateRoomPane();
		setRoomListTableStyle();
		mainPane = new BorderPane();
		mainPane.setTop(searchPane);
		mainPane.setCenter(updatePane);
		mainPane.setRight(tvRoomList);
		
		return mainPane;
	}

	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}
	public static void main(String []args)
	{
		Application.launch(args);
	}

	

}
