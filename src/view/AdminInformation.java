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

public class AdminInformation{

	private TableView<Admin> tvAdmin;
	private ArrayList<Admin> al;
	private TableColumn<Admin,Integer> idCol,noCol;
	private TableColumn<Admin,String> nameCol,staffCol,phoneCol,nrcCol,addressCol;
	private FilteredList<Admin> fl; 
	private TableViewSelectionModel<Admin> selectionModel;
	
	private Label lSearch,lTitle;
	private TextField tfSearch;
	private BorderPane mainAdminListPane,updateBox;
	private FlowPane searchPane;
	
	private Label lName,lStaffId,lPhone,lNrc,lAddress,labelNameFormat,labelPhoneFormat,labelNrcFormat,staffIdFormat;
	private TextField tfName,tfStaffId,tfPhone,tfNrc,tfAddress;
	private Button btnUpdate,btnClear;
	private GridPane updatePane;
	private VBox nameFormatBox,phoneFormatBox,nrcFormatBox,staffFormatBox;
	private int adminId;
	
	public void createAdminTable()
	{
		tvAdmin = new TableView<Admin>();
		
		noCol = new TableColumn<Admin,Integer>("No");
		noCol.setCellValueFactory(new PropertyValueFactory<Admin,Integer>("no"));
		noCol.setSortable(true);
		
		
		nameCol = new TableColumn<Admin,String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Admin,String>("name"));
		nameCol.setSortable(true);
		
		staffCol = new TableColumn<Admin,String>("Staff-ID");
		staffCol.setCellValueFactory(new PropertyValueFactory<Admin,String>("staffId"));
		
		phoneCol = new TableColumn<Admin,String>("Phone");
		phoneCol.setCellValueFactory(new PropertyValueFactory<Admin,String>("phone"));
		
		nrcCol = new TableColumn<Admin,String>("NRC Number");
		nrcCol.setCellValueFactory(new PropertyValueFactory<Admin,String>("nrc"));
		
		addressCol = new TableColumn<Admin,String>("Address");
		addressCol.setCellValueFactory(new PropertyValueFactory<Admin,String>("address"));
	
		tvAdmin.getColumns().add(noCol);
		tvAdmin.getColumns().add(nameCol);
		tvAdmin.getColumns().add(staffCol);
		tvAdmin.getColumns().add(phoneCol);
		tvAdmin.getColumns().add(nrcCol);
		tvAdmin.getColumns().add(addressCol);
		tvAdmin.setMaxWidth(600);
		tvAdmin.setPadding(new Insets(20));

	    ScrollPane sp = new ScrollPane(tvAdmin);
	    sp.setFitToHeight(true);
	    sp.setFitToWidth(true);
		
		selectionModel = tvAdmin.getSelectionModel();
		selectionModel.setSelectionMode(SelectionMode.SINGLE);
		
		tvAdmin.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				if(e.getClickCount()==2)
				{
					deleteAdmin();
				
				}
				else {
					
					setUpdateAdminInfo();
				}
			}
		});
		
		setAdminData();
		
		
	}
	
	public void createUpdateAdmin() {
	
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
			
				if(ValidChecker.isValidAdminStaffId(tfStaffId.getText()))
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
			updateAdmin();
			
		});
		btnClear = new Button("Clear");
		btnClear.setOnAction(e->{
			clearAdmin();
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
	
	public void setUpdateAdminInfo()
	{
		try {
			Admin am = selectionModel.getSelectedItem();
			adminId = am.getId();
			System.out.println(adminId);
			tfName.setText(am.getName());
			tfStaffId.setText(am.getStaffId());
			tfPhone.setText(am.getPhone());
			tfNrc.setText(am.getNrc());
			tfAddress.setText(am.getAddress());
		}catch(NullPointerException e)
		{
			
		}
	}
	
	public void updateAdmin()
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
				if(DBHandler.updateAdmin(adminId,name,staffId,phone,nrc,address))
				{
					setAdminData();
					clearAdmin();
				}

			}else {
				Alert alt = new Alert(AlertType.ERROR);
				alt.setHeaderText("Invalid Data Format");
				alt.setContentText("Try Again with valid data format");
				Optional<ButtonType> ans = alt.showAndWait();
				if(ans.isPresent() && ans.get()==ButtonType.OK)
				{
					clearAdmin();
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
				clearAdmin();
			}
		}
	}
	
	public void deleteAdmin()
	{
		Alert alt = new Alert(AlertType.CONFIRMATION);
		alt.setHeaderText("Confirm");
		alt.setContentText("Do you want to delete?");
		Optional<ButtonType> ans = alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			Admin am= selectionModel.getSelectedItem();
			tvAdmin.getItems().remove(am);
			DBHandler.addAdminDeleteDate(am.getId());
			clearAdmin();
	
		}
	}
	
	public void clearAdmin()
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
	
	public void setAdminData()
	{
		al=DBHandler.getAllAdmins();
		fl = new FilteredList<>(FXCollections.observableArrayList(al));
		tvAdmin.getItems().clear();
		tvAdmin.getItems().addAll(fl);
	}
	
	public void createSearchBar()
	{
		lTitle = new Label("Admin Information");
		lSearch = new Label("Search Box : ");
		tfSearch = new TextField();
		tfSearch.setOnKeyReleased(e->{
			if(e.getCode()==KeyCode.ENTER)
			{
				filteringAdmin();
			}
		});
		
		searchPane = new FlowPane(20,20,lTitle,lSearch,tfSearch);
		searchPane.setMargin(lTitle,new Insets(0,380,0,0));
		searchPane.setAlignment(Pos.CENTER_RIGHT);
		searchPane.setPadding(new Insets(20));
	}
	
	public void filteringAdmin()
	{
		
		fl= new FilteredList<>(FXCollections.observableArrayList(al));
		fl.setPredicate(new Predicate<Admin>(){

			@Override
			public boolean test(Admin a) {
				String value = tfSearch.getText();
				if(value.length()==0)
				{
					return true;
				}
				
				return value.equals(a.getName()) || value.equals(a.getNrc()) ||
							value.equals(a.getPhone()) || value.equals(a.getStaffId());

			}
		});
		tvAdmin.getItems().clear();
		tvAdmin.getItems().addAll(fl);
	}

	public void createLayoutAdminTable()
	{
		mainAdminListPane = new BorderPane();
		mainAdminListPane.setTop(searchPane);
		mainAdminListPane.setCenter(updatePane);
		mainAdminListPane.setRight(tvAdmin);
		mainAdminListPane.setId("main-admin-pane");
	}
	
	public void setAdminTableStyle() {
		
		searchPane.getStyleClass().add("admin-search-pane");
		updatePane.getStyleClass().add("admin-update-pane");
		tvAdmin.getStyleClass().add("tv-admin");
		btnUpdate.getStyleClass().add("update-room-btn");
		btnClear.getStyleClass().add("clear-room-btn");
		lTitle.getStyleClass().add("room-info-title");
		labelNameFormat.getStyleClass().add("err");
		labelPhoneFormat.getStyleClass().add("err");
		labelNrcFormat.getStyleClass().add("err");
		staffIdFormat.getStyleClass().add("err");
		
	}
	
	public BorderPane getMainAdminListPane() {
		createSearchBar();
		createUpdateAdmin();
		createAdminTable();
		setAdminTableStyle();
		createLayoutAdminTable();
		return mainAdminListPane;
	}

	public void setMainAdminListPane(BorderPane mainAdminListPane) {
		this.mainAdminListPane = mainAdminListPane;
	}


	public static void main(String[] args) {
		Application.launch(args);

	}
}
