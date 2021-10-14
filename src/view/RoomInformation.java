package view;

import java.util.ArrayList;
import java.util.function.Predicate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import model.*;


public class RoomInformation {
	private TableView<RoomStatus> tvRoomStatus;
	private ArrayList<RoomStatus> al;
	private TableColumn<RoomStatus,Integer> idCol,roomNoCol;
	private TableColumn<RoomStatus,String> roomTypeCol;
	private FilteredList<RoomStatus> fl; 

	
	private Label lSearch,lTitle;
	private TextField tfSearch;
	private BorderPane mainPane;
	private FlowPane searchPane;
	
	public void createRoomStatusTable()
	{
		tvRoomStatus = new TableView<RoomStatus>();
		
		idCol = new TableColumn<RoomStatus,Integer>("No");
		idCol.setCellValueFactory(new PropertyValueFactory<RoomStatus,Integer>("No"));
		idCol.setSortable(true);
		
		roomNoCol = new TableColumn<RoomStatus,Integer>("Room No");
		roomNoCol.setCellValueFactory(new PropertyValueFactory<RoomStatus,Integer>("roomNo"));
		roomNoCol.setSortable(true);
		
		roomTypeCol = new TableColumn<RoomStatus,String>("Room Type");
		roomTypeCol.setCellValueFactory(new PropertyValueFactory<RoomStatus,String>("roomType"));
		
	
		tvRoomStatus.getColumns().add(idCol);
		tvRoomStatus.getColumns().add(roomNoCol);
		tvRoomStatus.getColumns().add(roomTypeCol);
		
		setRoomStatusData();
	}
	

	public void setRoomStatusData()
	{
		al=DBHandler.getRoomStatus();
		fl = new FilteredList<>(FXCollections.observableArrayList(al));
		tvRoomStatus.getItems().addAll(fl);
	}
	
	public void createSearchBar()
	{
		lTitle = new Label("Room Information");
		lSearch = new Label("Search Box : ");
		tfSearch = new TextField();
		tfSearch.setOnKeyReleased(e->{
			if(e.getCode()==KeyCode.ENTER)
			{
				filteringRoomStatus();
				
			}
			
		});
		
		searchPane = new FlowPane(20,20,lTitle,lSearch,tfSearch);
		searchPane.setMargin(lTitle, new Insets(0,430,0,0));
		searchPane.setAlignment(Pos.CENTER_RIGHT);
		searchPane.setPadding(new Insets(20));
	}
	
	
	public void createLayoutRoomStatusTable()
	{
		mainPane = new BorderPane();
		mainPane.setTop(searchPane);
		mainPane.setCenter(tvRoomStatus);
		
		
	}
	
	public void filteringRoomStatus()
	{
		fl= new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<RoomStatus>(){

			@Override
			public boolean test(RoomStatus room) {
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
		tvRoomStatus.getItems().clear();
		tvRoomStatus.getItems().addAll(fl);
	}
	
	
	public void setRoomStatusTableStyle() {
		mainPane.getStyleClass().add("main-room-list-table");
		searchPane.getStyleClass().add("search-pane-room-list");
		tvRoomStatus.getStyleClass().add("tv-room-list");
		lTitle.getStyleClass().add("room-info-title");
		
	}
	
	public BorderPane getMainPane() {
		createRoomStatusTable();
		createSearchBar();
		createLayoutRoomStatusTable();
		setRoomStatusTableStyle();
		mainPane = new BorderPane();
		mainPane.setTop(searchPane);
		mainPane.setCenter(tvRoomStatus);
		
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
