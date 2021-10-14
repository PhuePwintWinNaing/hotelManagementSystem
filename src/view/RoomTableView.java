package view;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.*;

public class RoomTableView{
	
	private TableView<RoomList> tvRoomList;
	private ArrayList<RoomList> al;
	private TableColumn<RoomList,Integer> idCol,roomNoCol,roomChargesCol;
	private TableColumn<RoomList,String> roomTypeCol,bedTypeCol;
	private FilteredList<RoomList> fl; 
	
	private Label lSearch,lTitle;
	private TextField tfSearch;
	private BorderPane mainPane;
	private FlowPane searchPane;
	
	public void createRoomListTable()
	{
		tvRoomList = new TableView<RoomList>();
		
		idCol = new TableColumn<RoomList,Integer>("Room ID");
		idCol.setCellValueFactory(new PropertyValueFactory<RoomList,Integer>("roomId"));
		idCol.setSortable(true);
		
		roomNoCol = new TableColumn<RoomList,Integer>("Room No");
		roomNoCol.setCellValueFactory(new PropertyValueFactory<RoomList,Integer>("roomNo"));
		roomNoCol.setSortable(true);
		
		roomTypeCol = new TableColumn<RoomList,String>("Room Type");
		roomTypeCol.setCellValueFactory(new PropertyValueFactory<RoomList,String>("roomType"));
		
		bedTypeCol = new TableColumn<RoomList,String>("Bed Type");
		bedTypeCol.setCellValueFactory(new PropertyValueFactory<RoomList,String>("bedType"));
		
		roomChargesCol = new TableColumn<RoomList,Integer>("Room Charges");
		roomChargesCol.setCellValueFactory(new PropertyValueFactory<RoomList,Integer>("roomCharges"));
		
		
		tvRoomList.getColumns().add(idCol);
		tvRoomList.getColumns().add(roomNoCol);
		tvRoomList.getColumns().add(roomTypeCol);
		tvRoomList.getColumns().add(bedTypeCol);
		tvRoomList.getColumns().add(roomChargesCol);
		
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
		mainPane.setStyle("-fx-background-color:#F4ECF7");
		mainPane.setTop(searchPane);
		mainPane.setCenter(tvRoomList);
		
		
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
					int rCharges = Integer.parseInt(value);
					return rno==room.getRoomNo() || rCharges==room.getRoomCharges();
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
		tvRoomList.getStyleClass().add("tv-room-list");
		lTitle.getStyleClass().add("room-info-title");
		
	}
	
	public BorderPane getMainPane() {
		createRoomListTable();
		createSearchBar();
		createLayoutRoomListTable();
		setRoomListTableStyle();
		mainPane = new BorderPane();
		mainPane.setTop(searchPane);
		mainPane.setCenter(tvRoomList);
		
		return mainPane;
	}

	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}

	public static void main(String[] args) {
		
		Application.launch(args);
	}

}
