package view;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import model.*;


public class RoomOccupiedTable {
	
	private TableView<RoomOccupiedReport> tvRoomOccupiedReport;
	private ArrayList<RoomOccupiedReport> al;
	private TableColumn<RoomOccupiedReport,Integer> noCol,monthCol,yearCol;

	private BorderPane mainPane;
	
	public void createRoomOccupiedReportTable()
	{
		tvRoomOccupiedReport = new TableView<RoomOccupiedReport>();
		
		
		yearCol = new TableColumn<RoomOccupiedReport,Integer>("Year");
		yearCol.setCellValueFactory(new PropertyValueFactory<RoomOccupiedReport,Integer>("year"));
		yearCol.setSortable(true);
		
		monthCol = new TableColumn<RoomOccupiedReport,Integer>("Month");
		monthCol.setCellValueFactory(new PropertyValueFactory<RoomOccupiedReport,Integer>("month"));
		monthCol.setSortable(true);
		
		noCol = new TableColumn<RoomOccupiedReport,Integer>("No Of Room Occupied");
		noCol.setCellValueFactory(new PropertyValueFactory<RoomOccupiedReport,Integer>("noOfRoomOccupied"));
		noCol.setSortable(true);
		
		
		
		
		tvRoomOccupiedReport.getColumns().add(yearCol);
		tvRoomOccupiedReport.getColumns().add(monthCol);
		tvRoomOccupiedReport.getColumns().add(noCol);
		
		
		
		tvRoomOccupiedReport.setMaxWidth(450);
		tvRoomOccupiedReport.setPadding(new Insets(20));

	    ScrollPane sp = new ScrollPane(tvRoomOccupiedReport);
	    sp.setFitToHeight(true);
	    sp.setFitToWidth(true);
		
		setRoomOccupiedReportData();
		
	}
	
	
	
	
	public void setRoomOccupiedReportData()
	{
		al=DBHandler.getRoomOccupiedData(2020);
		tvRoomOccupiedReport.getItems().addAll(al);
	}
	

	
	public void createLayoutRoomOccupiedReportTable()
	{
		mainPane = new BorderPane();
		mainPane.setStyle("-fx-background-color:#F4ECF7");
		mainPane.setCenter(tvRoomOccupiedReport);
		
		
	}
	
	
	public void setRoomOccupiedReportTableStyle() {
		mainPane.getStyleClass().add("main-room-list-table");
		tvRoomOccupiedReport.getStyleClass().add("tv-room-list");
		
	}
	
	public BorderPane getMainPane() {
		createRoomOccupiedReportTable();
		createLayoutRoomOccupiedReportTable();
		setRoomOccupiedReportTableStyle();
		mainPane = new BorderPane();
		mainPane.setCenter(tvRoomOccupiedReport);
		
		return mainPane;
	}

	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}

	public static void main(String[] args) {
		
		Application.launch(args);
	}


}
