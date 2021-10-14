package view;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Predicate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.*;



public class LogBooksTable{
	private TableView<LogBooks> tvLogBooks;
	private ArrayList<LogBooks> al;
	private TableColumn<LogBooks,Integer> idCol;
	private TableColumn<LogBooks,String> loginTimeCol,loginDateCol,logoutTimeCol,
										logoutDateCol,receptionistNameCol;
	private FilteredList<LogBooks> fl;
	
	private Label lTitle,lSearch;
	private TextField tfSearch;
	private BorderPane logBooksPane,headerBox;
	private HBox titleBox,searchBox;
	private DatePicker searchDate;

	public void createLogBooks()
	{
		tvLogBooks = new TableView<LogBooks>();
		
		idCol = new TableColumn<LogBooks,Integer>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<LogBooks,Integer>("id"));
		idCol.setSortable(true);
		
		receptionistNameCol = new TableColumn<LogBooks,String>("Receptionist Name");
		receptionistNameCol.setCellValueFactory(new PropertyValueFactory<LogBooks,String>("receptionistName"));
		
		loginDateCol = new TableColumn<LogBooks,String>("Login Date");
		loginDateCol.setCellValueFactory(new PropertyValueFactory<LogBooks,String>("loginDate"));
		loginDateCol.setSortable(true);
		
		
		loginTimeCol = new TableColumn<LogBooks,String>("Login Time");
		loginTimeCol.setCellValueFactory(new PropertyValueFactory<LogBooks,String>("loginTime"));
		
		logoutDateCol = new TableColumn<LogBooks,String>("Logout Date");
		logoutDateCol.setCellValueFactory(new PropertyValueFactory<LogBooks,String>("logoutDate"));
		logoutDateCol.setSortable(true);
		
		logoutTimeCol = new TableColumn<LogBooks,String>("Logout Time");
		logoutTimeCol.setCellValueFactory(new PropertyValueFactory<LogBooks,String>("logoutTime"));
		
		
		tvLogBooks.getColumns().add(idCol);
		tvLogBooks.getColumns().add(receptionistNameCol);
		tvLogBooks.getColumns().add(loginDateCol);
		tvLogBooks.getColumns().add(loginTimeCol);
		tvLogBooks.getColumns().add(logoutDateCol);
		tvLogBooks.getColumns().add(logoutTimeCol);
		tvLogBooks.setPadding(new Insets(20));
		
		tvLogBooks.setMaxWidth(600);
		tvLogBooks.setPadding(new Insets(30));

	    ScrollPane sp = new ScrollPane(tvLogBooks);
	    sp.setFitToHeight(true);
	    sp.setFitToWidth(true);
		
		setLogBookData();

	}
	
	public void setLogBookData()
	{
		al=DBHandler.getReceptionistDetails();
		tvLogBooks.getItems().addAll(al);
	}
		
	
	public void createLayoutLogBooks()
	{
		lTitle = new Label("Employee's LogBooks");
		lTitle.setStyle("-fx-text-fill:#ffffff;-fx-font-size:16;-fx-font-weight:bolder");
		lTitle.setPadding(new Insets(20));
		titleBox = new HBox(lTitle);
		
		lSearch = new Label("Search Box : ");
		lSearch.setStyle("-fx-text-fill:#ffffff");
		tfSearch = new TextField();
		tfSearch.setOnKeyPressed(e->{
			if(e.getCode()==KeyCode.ENTER)
			{
				filteringLogBook();
			}
			
		});
		searchDate = new DatePicker();
		searchDate.setOnAction(e->{
			filteringLogBookWithDate();
			
		});
		searchBox = new HBox(20,lSearch,tfSearch,searchDate);
		headerBox = new BorderPane();
		headerBox.setLeft(titleBox);
		headerBox.setMargin(searchDate, new Insets(0,20,0,0));
		headerBox.setRight(searchBox);
		searchBox.setAlignment(Pos.CENTER_RIGHT);
		logBooksPane = new BorderPane();
		logBooksPane.setTop(headerBox);
		logBooksPane.setCenter(tvLogBooks);
		
		
	}
	
	public void filteringLogBook()
	{
		fl= new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<LogBooks>(){

			@Override
			public boolean test(LogBooks lb) {
				String value = tfSearch.getText();
				if(value.length()==0)
				{
					return true;
				}
					return value.equals(lb.getReceptionistName());
	
			}
		});
		tvLogBooks.getItems().clear();
		tvLogBooks.getItems().addAll(fl);
	}
	
	public void filteringLogBookWithDate()
	{
		
		fl= new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<LogBooks>(){

				@Override
				public boolean test(LogBooks lb) {
					String value = searchDate.getValue().toString();
					
					return value.equals(lb.getLoginDate()) || value.equals(lb.getLogoutDate());
				}
			});
			tvLogBooks.getItems().clear();
			tvLogBooks.getItems().addAll(fl);
	}
	
	public void setLogBooksStyle() {
		logBooksPane.getStyleClass().add("log-book-table");
		headerBox.getStyleClass().add("search-pane-check-in");
		
	}
	
	
	public BorderPane getLogBooksPane() {
		createLogBooks();
		createLayoutLogBooks();
		setLogBooksStyle();
		return logBooksPane;
	}




	public void setLogBooksPane(BorderPane logBooksPane) {
		this.logBooksPane = logBooksPane;
	}




	public static void main(String[] args) {
		Application.launch(args);

	}

	
}
