package view;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import model.*;

public class IncomeReportTable {

	private TableView<IncomeReport> tvIncomeReport;
	private ArrayList<IncomeReport> al;
	private TableColumn<IncomeReport,Integer> totalCol,monthCol,yearCol;

	private BorderPane mainPane;
	
	public void createIncomeReportTable()
	{
		tvIncomeReport = new TableView<IncomeReport>();
		
		totalCol = new TableColumn<IncomeReport,Integer>("Total Incomes");
		totalCol.setCellValueFactory(new PropertyValueFactory<IncomeReport,Integer>("total"));
		totalCol.setSortable(true);
		
		monthCol = new TableColumn<IncomeReport,Integer>("Month");
		monthCol.setCellValueFactory(new PropertyValueFactory<IncomeReport,Integer>("month"));
		monthCol.setSortable(true);
		
		yearCol = new TableColumn<IncomeReport,Integer>("Year");
		yearCol.setCellValueFactory(new PropertyValueFactory<IncomeReport,Integer>("year"));
		yearCol.setSortable(true);
		
		
		tvIncomeReport.getColumns().add(totalCol);
		tvIncomeReport.getColumns().add(monthCol);
		tvIncomeReport.getColumns().add(yearCol);
		
		tvIncomeReport.setMaxWidth(300);
		tvIncomeReport.setPadding(new Insets(30));

	    ScrollPane sp = new ScrollPane(tvIncomeReport);
	    sp.setFitToHeight(true);
	    sp.setFitToWidth(true);
		
		setIncomeReportData();
		
	}
	
	
	
	
	public void setIncomeReportData()
	{
		al=DBHandler.getIncomeReportTable();
		tvIncomeReport.getItems().addAll(al);
	}
	

	
	public void createLayoutIncomeReportTable()
	{
		mainPane = new BorderPane();
		mainPane.setStyle("-fx-background-color:#F4ECF7");
		mainPane.setCenter(tvIncomeReport);
		
		
	}
	
	
	public void setIncomeReportTableStyle() {
		mainPane.getStyleClass().add("main-room-list-table");
		tvIncomeReport.getStyleClass().add("tv-room-list");
		
	}
	
	public BorderPane getMainPane() {
		createIncomeReportTable();
		createLayoutIncomeReportTable();
		setIncomeReportTableStyle();
		mainPane = new BorderPane();
		mainPane.setCenter(tvIncomeReport);
		
		return mainPane;
	}

	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}

	public static void main(String[] args) {
		
		Application.launch(args);
	}
}
