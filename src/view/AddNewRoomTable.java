package view;

import java.util.ArrayList;
import java.util.function.Predicate;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import model.DBHandler;
import model.RoomList;

public class AddNewRoomTable {
	private TableView<RoomList> tvRoomList;
	private ArrayList<RoomList> al;
	private TableColumn<RoomList,Integer> idCol,roomNoCol;
	private TableColumn<RoomList,String> roomTypeCol;
	private FilteredList<RoomList> fl; 

	private BorderPane mainPane;
	
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
		
		tvRoomList.getColumns().add(idCol);
		tvRoomList.getColumns().add(roomNoCol);
		tvRoomList.getColumns().add(roomTypeCol);
		
		tvRoomList.setMaxWidth(500);
		tvRoomList.setPadding(new Insets(30));

	    ScrollPane sp = new ScrollPane(tvRoomList);
	    sp.setFitToHeight(true);
	    sp.setFitToWidth(true);
		
		
		setRoomListData();
		
	}
	
	
	
	
	public void setRoomListData()
	{
		al=DBHandler.getAllRoomInfo();
		fl = new FilteredList<>(FXCollections.observableArrayList(al));
		tvRoomList.getItems().addAll(fl);
	}
	
	
	public void createLayoutRoomListTable()
	{
		mainPane = new BorderPane();
		mainPane.setStyle("-fx-background-color:#D6EAF8");
		mainPane.setCenter(tvRoomList);
		
		
	}
	
	public void setRoomListTableStyle() {
		mainPane.getStyleClass().add("main-room-list-table");
		tvRoomList.getStyleClass().add("tv-room-list");
		
	}
	
	public BorderPane getMainPane() {
		createRoomListTable();
		createLayoutRoomListTable();
		setRoomListTableStyle();
		mainPane = new BorderPane();
		mainPane.setCenter(tvRoomList);
		
		return mainPane;
	}

	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}


}
