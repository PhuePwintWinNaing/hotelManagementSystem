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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
import model.*;



public class GuestLists{
	private TableView<Guest> tvGuestList;
	private ArrayList<Guest> al;
	private TableColumn<Guest,Integer> idCol;
	private TableColumn<Guest,String> nameCol,nrcCol,phoneCol,emailCol,addressCol;
	private TableViewSelectionModel<Guest> selectionModel;
	
//	private Label lSearch,lTitle;
	private TextField tfSearch;
	private BorderPane mainPane;
	private FlowPane searchPane;



	public void createGuestListTable()
	{
		tvGuestList = new TableView<Guest>();
		
		idCol = new TableColumn<Guest,Integer>("Guest ID");
		idCol.setCellValueFactory(new PropertyValueFactory<Guest,Integer>("guestId"));
		idCol.setSortable(true);
		
		nameCol = new TableColumn<Guest,String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Guest,String>("name"));
		nameCol.setSortable(true);
		
		phoneCol = new TableColumn<Guest,String>("Phone Number");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Guest,String>("phone"));
		
		nrcCol = new TableColumn<Guest,String>("NRC");
		nrcCol.setCellValueFactory(new PropertyValueFactory<Guest,String>("nrc"));
		
		
		emailCol = new TableColumn<Guest,String>("Email Address");
		emailCol.setCellValueFactory(new PropertyValueFactory<Guest,String>("email"));
		
		addressCol = new TableColumn<Guest,String>("Address");
		addressCol.setCellValueFactory(new PropertyValueFactory<Guest,String>("address"));
	
		tvGuestList.getColumns().add(idCol);
		tvGuestList.getColumns().add(nameCol);
		tvGuestList.getColumns().add(phoneCol);
		tvGuestList.getColumns().add(nrcCol);
		tvGuestList.getColumns().add(emailCol);
		tvGuestList.getColumns().add(addressCol);
		
		selectionModel = tvGuestList.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		tvGuestList.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				if(e.getClickCount()==2)
				{
					deleteGuest();
				}
			}
		});
		
		setGuestData();
	
	}
	
	public void setGuestData()
	{
		al=DBHandler.getAllGuests();
		try {
			tvGuestList.getItems().addAll(al);
		}catch(NullPointerException e)
		{
			
		}
		
	}
	
	
	public void deleteGuest()
	{
		Alert alt = new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirm");
		alt.setContentText("Do you want to delete?");
		Optional<ButtonType> ans = alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			Guest gl= selectionModel.getSelectedItem();
			tvGuestList.getItems().remove(gl);
			DBHandler.deleteGuests(gl.getGuestId());
		}
	}

//	public void createSearchBar()
//	{
////		lTitle = new Label("Guest Information");
////		lSearch = new Label("Search Box : ");
//		tfSearch = new TextField();
//		tfSearch.setOnKeyReleased(e->{
//			if(e.getCode()==KeyCode.ENTER)
//			{
//				filteringGuest();
//			}
//			
//		});
//		
//		searchPane = new FlowPane(20,20,lTitle,lSearch,tfSearch);
//		FlowPane.setMargin(lTitle, new Insets(0,300,0,0));
//		lTitle.setStyle("-fx-text-fill:#ffffff");
//		lSearch.setStyle("-fx-text-fill:#ffffff");
//		searchPane.setAlignment(Pos.CENTER_RIGHT);
//		searchPane.setPadding(new Insets(20));
//	}
	
//	public void filteringGuest()
//	{
//		fl= new FilteredList<>(FXCollections.observableArrayList(al));
//		fl.setPredicate(new Predicate<Guest>(){
//
//			@Override
//			public boolean test(Guest g) {
//				String value = tfSearch.getText();
//				if(value.length()==0)
//				{
//					return true;
//				}
//				
//				return value.equalsIgnoreCase(g.getName());
//				
//			}
//		});
//		tvGuestList.getItems().clear();
//		tvGuestList.getItems().addAll(fl);
//	}

	public void createLayoutGuestTable()
	{
		mainPane = new BorderPane();
		mainPane.setTop(searchPane);
		mainPane.setCenter(tvGuestList);
	}
	
	public void styleForGuest()
	{
		tvGuestList.getStyleClass().add("guest-list-table");
//		searchPane.getStyleClass().add("check-out-top-pane");
	}
	
	public BorderPane getMainPane() {
		createGuestListTable();
		createLayoutGuestTable();
//		createSearchBar();
		styleForGuest();
		mainPane = new BorderPane();
		mainPane.setTop(searchPane);
		mainPane.setCenter(tvGuestList);
		
		return mainPane;
	}

	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}

	public static void main(String args[])
	{
		Application.launch(args);
	}

}
