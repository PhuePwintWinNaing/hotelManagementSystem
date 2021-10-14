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


public class ReceptionistInformation{

	private TableView<Receptionist> tvReceptionist;
	private ArrayList<Receptionist> al;
	private TableColumn<Receptionist,Integer> idCol,noCol;
	private TableColumn<Receptionist,String> nameCol,staffCol,phoneCol,nrcCol,addressCol;
	private FilteredList<Receptionist> fl; 
	private TableViewSelectionModel<Receptionist> selectionModel;
	
	private Label lSearch,lTitle;
	private TextField tfSearch;
	private BorderPane mainReceptionistListPane,updateBox;
	private FlowPane searchPane;
	
	private Label lName,lStaffId,lPhone,lNrc,lAddress,labelNameFormat,labelPhoneFormat,labelNrcFormat,staffIdFormat;
	private TextField tfName,tfStaffId,tfPhone,tfNrc,tfAddress;
	private Button btnUpdate,btnClear;
	private GridPane updatePane;
	private VBox nameFormatBox,phoneFormatBox,nrcFormatBox,staffFormatBox;
	private int receptionistId;
	
	public void createReceptionistTable()
	{
		tvReceptionist = new TableView<Receptionist>();
		
		noCol = new TableColumn<Receptionist,Integer>("No");
		noCol.setCellValueFactory(new PropertyValueFactory<Receptionist,Integer>("no"));
		noCol.setSortable(true);
		
		nameCol = new TableColumn<Receptionist,String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Receptionist,String>("name"));
		nameCol.setSortable(true);
		
		staffCol = new TableColumn<Receptionist,String>("Staff-ID");
		staffCol.setCellValueFactory(new PropertyValueFactory<Receptionist,String>("staffId"));
		staffCol.setSortable(true);
		
		phoneCol = new TableColumn<Receptionist,String>("Phone");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Receptionist,String>("phone"));
		phoneCol.setSortable(true);
		
		nrcCol = new TableColumn<Receptionist,String>("NRC Number");
		nrcCol.setCellValueFactory(new PropertyValueFactory<Receptionist,String>("nrc"));
		nrcCol.setSortable(true);
		
		addressCol = new TableColumn<Receptionist,String>("Address");
		addressCol.setCellValueFactory(new PropertyValueFactory<Receptionist,String>("address"));
		addressCol.setSortable(true);
		
	
		
		tvReceptionist.getColumns().add(noCol);
		tvReceptionist.getColumns().add(nameCol);
		tvReceptionist.getColumns().add(staffCol);
		tvReceptionist.getColumns().add(phoneCol);
		tvReceptionist.getColumns().add(nrcCol);
		tvReceptionist.getColumns().add(addressCol);
	
		
		tvReceptionist.setMaxWidth(600);
		tvReceptionist.setPadding(new Insets(20));

	    ScrollPane sp = new ScrollPane(tvReceptionist);
	    sp.setFitToHeight(true);
	    sp.setFitToWidth(true);
		
		selectionModel = tvReceptionist.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		tvReceptionist.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				if(e.getClickCount()==2)
				{
					deleteReceptionist();
				}
				else {
					
					setUpdateReceptionistInfo();
				}
			}
		});
		
		setReceptionistData();
		
	}
	
	public void createUpdateRoomPane() {
		
		lName = new Label("Name");
		lStaffId = new Label("Staff-ID");
		lPhone = new Label("Phone Number");
		lNrc = new Label("NRC Number");
		lAddress = new Label("Address");
		labelNameFormat = new Label();
		labelPhoneFormat = new Label();
		labelNrcFormat = new Label();
		staffIdFormat = new Label();
		tfName = new TextField();
		tfName.setOnKeyReleased(e->{
			String name = tfName.getText();
			if(ValidChecker.isValidName(name))
			{
				labelNameFormat.setText("");
				
			}else {
				labelNameFormat.setText("Your name format is invalid!");
			}
		});
		tfStaffId= new TextField();
		tfStaffId.setOnKeyReleased(e->{
			
			if(ValidChecker.isValidReceptionistStaffId(tfStaffId.getText()))
			{
				if(ValidChecker.isDuplicateStaffId(tfStaffId.getText()))
				{
					staffIdFormat.setText("StaffId must be unique");
				}
				else
				{
					staffIdFormat.setText("");
				}
			}
			else
			{
				staffIdFormat.setText("StaffId is invalid format!");
			}
		
	});
		tfPhone= new TextField();
		tfPhone.setOnKeyReleased(e->{
			String phone = tfPhone.getText();
			if(ValidChecker.isValidPhoneNumber(phone))
			{
				 if( ValidChecker.isDuplicatePhone(phone))
				 {
					 labelPhoneFormat.setText("Phone No must be unique");
				 }
				 else {
					 labelPhoneFormat.setText("");
				 }
				
			}else {
				labelPhoneFormat.setText("Phone no format is invalid!");
			}
		});
		tfNrc= new TextField();
		tfNrc.setOnKeyReleased(e->{
			String nrc = tfNrc.getText();
			if(ValidChecker.isValidNRC(nrc))
			{
				if(ValidChecker.isDuplicateNRC(nrc))
				{
					labelNrcFormat.setText("NRC must be unique");
				}else {
					labelNrcFormat.setText("");
				}

			}else {
				labelNrcFormat.setText("Your nrc is invalid format!");
			}
		});
		tfAddress= new TextField();
		btnUpdate = new Button("Update");
		btnUpdate.setOnAction(e->{
			updateReceptionist();
		});
		btnClear = new Button("Clear");
		btnClear.setOnAction(e->{
			clearReceptionist();
		});
		
		updatePane = new GridPane();
		
		updatePane.add(lName, 0, 0);
		nameFormatBox = new VBox(tfName,labelNameFormat);
		updatePane.add(nameFormatBox, 1, 0);
		
		updatePane.add(lStaffId, 0, 1);
		staffFormatBox = new VBox(tfStaffId,staffIdFormat);
		updatePane.add(staffFormatBox,1,1);
		
		updatePane.add(lPhone, 0, 2);
		phoneFormatBox = new VBox(tfPhone,labelPhoneFormat);
		updatePane.add(phoneFormatBox, 1, 2);
		
		updatePane.add(lNrc, 0, 3);
		nrcFormatBox = new VBox(tfNrc,labelNrcFormat);
		updatePane.add(nrcFormatBox, 1, 3);
		
		updatePane.add(lAddress, 0, 4);
		updatePane.add(tfAddress, 1, 4);
		
		updatePane.add(btnUpdate, 0, 5);
		updatePane.add(btnClear, 1, 5);
		
		updateBox = new BorderPane();
		updateBox.setCenter(updatePane);
		updatePane.setHgap(20);
		updatePane.setVgap(20);
		updatePane.setPadding(new Insets(20));
		
		
	}
	
	public void setUpdateReceptionistInfo()
	{
		Receptionist re = selectionModel.getSelectedItem();
		receptionistId = re.getId();
		tfName.setText(re.getName());
		tfStaffId.setText(re.getStaffId());
		tfPhone.setText(re.getPhone());
		tfNrc.setText(re.getNrc());
		tfAddress.setText(re.getAddress());
//		System.out.println(re.getName()+re.getStaffId()+re.getNrc()+re.getAddress());
	}
	
	public void updateReceptionist()
	{
		try {
			String name,staffId,phone,nrc,address;
			name = tfName.getText();
			staffId = tfStaffId.getText();
			phone = tfPhone.getText();
			nrc = tfNrc.getText();
			address = tfAddress.getText();
			if(ValidChecker.isValidName(name) && ValidChecker.isValidReceptionistStaffId(staffId) &&
			ValidChecker.isValidPhoneNumber(phone) && ValidChecker.isValidNRC(nrc))
			{
				if(DBHandler.updateReceptionists(receptionistId,name,staffId,phone,nrc,address))
				{
					setReceptionistData();
					clearReceptionist();
				}
			}else {
				Alert alt = new Alert(AlertType.ERROR);
				alt.setHeaderText("Invalid Data Format");
				alt.setContentText("Try Again with valid data format");
				Optional<ButtonType> ans = alt.showAndWait();
				if(ans.isPresent() && ans.get()==ButtonType.OK)
				{
					clearReceptionist();
				}
			}
			
		}catch(NullPointerException e)
		{
			Alert alt = new Alert(AlertType.ERROR);
			alt.setHeaderText("Invalid Data Format");
			alt.setContentText("Try Again with valid data format");
			Optional<ButtonType> ans = alt.showAndWait();
			if(ans.isPresent() && ans.get()==ButtonType.OK)
			{
				clearReceptionist();
			}
		}
		
		
	}
	public void deleteReceptionist()
	{
		Alert alt = new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirm");
		alt.setContentText("Do you want to delete?");
		Optional<ButtonType> ans = alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			Receptionist rl= selectionModel.getSelectedItem();
			tvReceptionist.getItems().remove(rl);
			DBHandler.addReceptionistDeleteDate(rl.getId());
			clearReceptionist();
		}
	}
	
	public void clearReceptionist()
	{
		tfName.setText("");
		tfStaffId.setText("");
		tfPhone.setText("");
		tfNrc.setText("");
		tfAddress.setText("");
		labelNameFormat.setText("");
		labelPhoneFormat.setText("");
		labelNrcFormat.setText("");
		staffIdFormat.setText("");
	}
	
	public void setReceptionistData()
	{
		al=DBHandler.getAllReceptionists();
		tvReceptionist.getItems().clear();
		fl = new FilteredList<>(FXCollections.observableArrayList(al));
		tvReceptionist.getItems().addAll(fl);
	}
	
	public void createSearchBar()
	{
		lTitle = new Label("Receptionist Information");
		lSearch = new Label("Search Box : ");
		tfSearch = new TextField();
		tfSearch.setOnKeyReleased(e->{
			if(e.getCode()==KeyCode.ENTER)
			{
				filteringReceptionist();
			}
		});
		
		searchPane = new FlowPane(20,20,lTitle,lSearch,tfSearch);
		searchPane.setMargin(lTitle,new Insets(0,350,0,0));
		searchPane.setAlignment(Pos.CENTER_RIGHT);
		searchPane.setPadding(new Insets(20));
	}
	
	public void filteringReceptionist()
	{
		
		fl= new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<Receptionist>(){

			@Override
			public boolean test(Receptionist r) {
				String value = tfSearch.getText();
				if(value.length()==0)
				{
					return true;
				}
				
				
				return r.getName().equalsIgnoreCase(value);
			}
		});
		tvReceptionist.getItems().clear();
		tvReceptionist.getItems().addAll(fl);
	}

	public void createLayoutReceptionistTable()
	{
		mainReceptionistListPane = new BorderPane();
		mainReceptionistListPane.setTop(searchPane);
		mainReceptionistListPane.setCenter(updatePane);
		mainReceptionistListPane.setRight(tvReceptionist);
		mainReceptionistListPane.setId("main-receptionist-pane");
	}
	
	public void setReceptionistTableStyle() {
		searchPane.getStyleClass().add("admin-search-pane");
		updatePane.getStyleClass().add("admin-update-pane");
		tvReceptionist.getStyleClass().add("tv-admin");
		btnUpdate.getStyleClass().add("update-room-btn");
		btnClear.getStyleClass().add("clear-room-btn");
		lTitle.getStyleClass().add("room-info-title");
		labelNameFormat.getStyleClass().add("err");
		labelPhoneFormat.getStyleClass().add("err");
		labelNrcFormat.getStyleClass().add("err");
		staffIdFormat.getStyleClass().add("err");
		
	}
	
	public BorderPane getMainReceptionistListPane() {
		createSearchBar();
		createUpdateRoomPane();
		createReceptionistTable();
		setReceptionistTableStyle();
		createLayoutReceptionistTable();
		return mainReceptionistListPane;
	}

	public void setMainReceptionistListPane(BorderPane mainReceptionistListPane) {
		this.mainReceptionistListPane = mainReceptionistListPane;
	}


	public static void main(String[] args) {
		Application.launch(args);

	}
}
