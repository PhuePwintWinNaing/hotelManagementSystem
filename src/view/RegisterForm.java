package view;

import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import model.*;


public class RegisterForm{
	private Label labelName,labelPhoneNo,labelNrc,labelStaffId,lRole,
				  labelAddress,labelPassword,labelRePassword,lSuccessMsg,
				  labelPasswordErr,labelRePasswordErr,labelNameFormat,labelPhoneFormat,labelNrcFormat,lTitle,staffIdErr;
	private TextField textFieldName,textFieldPhoneNo,textFieldNRC,textFieldStaffId;
	private TextField textFieldAddress;
	private RadioButton rbAdmin,rbReceptionist;
	private PasswordField  password;
	private PasswordField rePassword;
	private Button btnRegister,btnClear;
	private ToggleGroup tgRole;
	
	private GridPane registerPane;
	private BorderPane mainRegisterPane;
	private TilePane rolePane;
	private FlowPane btnPane;
	private VBox passwordErrBox,nameFormatBox,phoneFormatBox,nrcFormatBox,rePasswordErrBox,staffFormatBox;
	private HBox msgBox;
	
	private Admin adminData = new Admin();
	private Receptionist receptionistData = new Receptionist();
	
	ArrayList<Admin> adminAl = DBHandler.getAllAdmins();
	ArrayList<Receptionist> receptionistAl = DBHandler.getAllReceptionists();
	
	public void createNodes()
	{
		lTitle = new Label("Employee Register");
		labelName = new Label("Name");
		labelPhoneNo = new Label("PhoneNo");
		labelNrc = new Label("NRC");
		labelStaffId = new Label("Staff-ID");
		labelAddress = new Label("Address");
		labelPassword = new Label("Password");
		labelRePassword = new Label("Confirm Password");
		labelPasswordErr = new Label();
		labelNameFormat = new Label();
		labelPhoneFormat = new Label();
		labelNrcFormat = new Label();
		lSuccessMsg = new Label();
		labelRePasswordErr = new Label();
		staffIdErr = new Label();
		lRole = new Label("Role");
		
		tgRole = new ToggleGroup();
		rbAdmin = new RadioButton("Admin");
		rbAdmin.setToggleGroup(tgRole);
		rbAdmin.setSelected(true);
		rbReceptionist = new RadioButton("Receptionist");
		rbReceptionist.setToggleGroup(tgRole);
		
		textFieldName = new TextField();
		textFieldName.setOnKeyReleased(e->{
			String name = textFieldName.getText();
			if(ValidChecker.isValidName(name))
			{
				labelNameFormat.setText("");
				
			}else {
				labelNameFormat.setText("Your name format is invalid!!! e.g Mg Mg");
			}
		});
		
		textFieldPhoneNo = new TextField();
		textFieldPhoneNo.setOnKeyReleased(e->{
			String phone = textFieldPhoneNo.getText();
			if(ValidChecker.isValidPhoneNumber(phone))
			{
				 if( ValidChecker.isDuplicatePhone(phone))
				 {
					 labelPhoneFormat.setText("Phone Number must be unique");
				 }
				 else {
					 labelPhoneFormat.setText("");
				 }
				
			}else {
				labelPhoneFormat.setText("Your phone format is invalid!!! e.g 09xxxxxxx or 09xxxxxxxxx");
			}
		});
		
		textFieldNRC = new TextField();
		textFieldNRC.setOnKeyReleased(e->{
			String nrc = textFieldNRC.getText();
			if(ValidChecker.isValidNRC(nrc))
			{
				if(ValidChecker.isDuplicateNRC(nrc))
				{
					labelNrcFormat.setText("NRC must be unique");
				}else {
					labelNrcFormat.setText("");
				}

			}else {
				labelNrcFormat.setText("Your nrc is invalid format!!! e.g. 5/SaKaNa(N)xxxxxx");
			}
			
		});
		
		textFieldStaffId = new TextField();
		textFieldStaffId.setOnKeyReleased(e->{
		String staffId = textFieldStaffId.getText();
			if(rbAdmin.isSelected())
			{
				if(ValidChecker.isValidAdminStaffId(staffId))
				{
					if(ValidChecker.isDuplicateStaffId(staffId))
					{
						staffIdErr.setText("StaffId must be unique");
					}
					else
					{
						staffIdErr.setText("");
					}
				}
				else
				{
					staffIdErr.setText("StaffId is invalid format ! e.g. fom-eg01 ");
				}
			}
			else
			{
				if(ValidChecker.isValidReceptionistStaffId(staffId))
				{
					if(ValidChecker.isDuplicateStaffId(staffId))
					{
						staffIdErr.setText("StaffId must be unique");
					}
					else
					{
						staffIdErr.setText("");
					}
				}
				else{
					staffIdErr.setText("StaffId is invalid format ! e.g. fo-eg01");
				}
			}
		});
		textFieldAddress = new TextField();
		password = new PasswordField();
		
		password.setOnKeyReleased(e->{
			String pw = password.getText();
	        if(ValidChecker.isValidPassword(pw))
	        {
	        	labelPasswordErr.setText("");
	        }else {
	        	labelPasswordErr.setText("Your password is invalid format, password must have between 6 to 15 ");
	        }
		});
		rePassword = new PasswordField();
		rePassword.setOnKeyReleased(e->{
			String pw = password.getText();
			String rpw = rePassword.getText();
			if(pw.equals(rpw))
			{
				labelRePasswordErr.setText("");
				
			}else {
				labelRePasswordErr.setText("your password and confirm password did not match!!!");
			
			}
			
			
		});
		
		btnRegister = new Button("Register");
		btnRegister.setOnAction(e->{
			clickedRegister();
		});
		btnClear = new Button("Clear");
		btnClear.setOnAction(e->{
			clickedClear();
		});
		
	}
	
	public void defineLayout()
	{
		registerPane = new GridPane();
		registerPane.add(labelName,0,0);
		nameFormatBox = new VBox(textFieldName,labelNameFormat);
		registerPane.add(nameFormatBox, 1, 0);
		
		registerPane.add(labelPhoneNo,0,1);
		phoneFormatBox = new VBox(textFieldPhoneNo,labelPhoneFormat);
		registerPane.add(phoneFormatBox, 1, 1);
		
		registerPane.add(labelNrc, 0, 2);
		nrcFormatBox = new VBox(textFieldNRC,labelNrcFormat);
		registerPane.add(nrcFormatBox, 1, 2);
		
		registerPane.add(lRole, 0, 3);
		rolePane = new TilePane(20,20,rbAdmin,rbReceptionist);
		registerPane.add(rolePane, 1, 3);
		
		registerPane.add(labelStaffId, 0, 4);
		staffFormatBox = new VBox(textFieldStaffId,staffIdErr);
		registerPane.add(staffFormatBox, 1, 4);
		
		registerPane.add(labelAddress, 0, 5);
		registerPane.add(textFieldAddress, 1, 5);
		
		registerPane.add(labelPassword, 0, 6);
		passwordErrBox = new VBox(password,labelPasswordErr);
		registerPane.add(passwordErrBox, 1, 6);
		
		registerPane.add(labelRePassword, 0, 7);
		rePasswordErrBox = new VBox(rePassword,labelRePasswordErr);
		registerPane.add(rePasswordErrBox, 1, 7);
		
		btnPane = new FlowPane(btnRegister,btnClear);
		btnPane.setHgap(30);
		btnPane.setAlignment(Pos.CENTER);
		registerPane.setVgap(20);
		registerPane.setHgap(20);
		registerPane.setAlignment(Pos.CENTER);
		
		msgBox = new HBox(100,lTitle,lSuccessMsg);
		lTitle.setStyle("-fx-text-fill:#ffffff");
		lSuccessMsg.setStyle("-fx-text-fill:#ffffff");
		msgBox.setPadding(new Insets(10));
		mainRegisterPane = new BorderPane();
		mainRegisterPane.setTop(msgBox);
		mainRegisterPane.setCenter(registerPane);
		mainRegisterPane.setBottom(btnPane);
//		mainRegisterPane.setPadding(new Insets(20));
	
	}


	
	public void defineStyle()
	{
		mainRegisterPane.getStyleClass().add("main-register-pane");
		textFieldName.getStyleClass().add("field-focus");
		textFieldPhoneNo.getStyleClass().add("field-focus");
		textFieldNRC.getStyleClass().add("field-focus");
		textFieldAddress.getStyleClass().add("field-focus");
		password.getStyleClass().add("field-focus");
		rePassword.getStyleClass().add("field-focus");
		labelPasswordErr.getStyleClass().add("err");
		labelRePasswordErr.getStyleClass().add("err");
		labelNameFormat.getStyleClass().add("err");
		labelPhoneFormat.getStyleClass().add("err");
		labelNrcFormat.getStyleClass().add("err");
		staffIdErr.getStyleClass().add("err");
		btnRegister.getStyleClass().add("btn-check-out");
		btnClear.getStyleClass().add("clear-room-btn");
		lSuccessMsg.getStyleClass().add("success-msg");
		msgBox.getStyleClass().add("top-payment-pane");
	}
	
	
	
	public void clickedRegister()
	{
		String name,phoneNo,staffId,nrc,address,pass,rePass;
		
		name = textFieldName.getText();
		phoneNo = textFieldPhoneNo.getText();
		nrc = textFieldNRC.getText();
		staffId = textFieldStaffId.getText();
		address = textFieldAddress.getText();
		pass = password.getText();
		rePass = rePassword.getText();
		
	
	
	if(rbAdmin.isSelected() && pass.equals(rePass) && ValidChecker.isValidName(name) &&
			ValidChecker.isValidPhoneNumber(phoneNo) && ValidChecker.isValidNRC(nrc) && 
			ValidChecker.isValidPassword(pass) && ValidChecker.isValidAdminStaffId(staffId) &&
			!ValidChecker.isDuplicatePhone(phoneNo) && !ValidChecker.isDuplicateNRC(nrc) &&
			!ValidChecker.isDuplicateStaffId(staffId))
	{
		if(DBHandler.addAdmin(name, staffId, phoneNo, nrc, address, rePass))
		{
			lSuccessMsg.setText("Successfully Registered");
			System.out.println("Successfully Registered");
		}
		else
		{
			Alert alt = new Alert(AlertType.ERROR);
			alt.setHeaderText("Something Wrong!");
			alt.setContentText("Your Registration Failed! Try Again with valid data");
			Optional<ButtonType> ans = alt.showAndWait();
			if(ans.isPresent() && ans.get()==ButtonType.OK)
			{
				clickedClear();
			}
		}
	}
	else if(rbReceptionist.isSelected() && pass.equals(rePass) && ValidChecker.isValidName(name) &&
			ValidChecker.isValidPhoneNumber(phoneNo) && ValidChecker.isValidNRC(nrc) &&
			ValidChecker.isValidPassword(pass) && ValidChecker.isValidReceptionistStaffId(staffId)&&
			!ValidChecker.isDuplicatePhone(phoneNo) && !ValidChecker.isDuplicateNRC(nrc) &&
			!ValidChecker.isDuplicateStaffId(staffId)
			)
	{
		 if(DBHandler.addReceptionist(name, staffId, phoneNo, nrc,address,rePass))
			{
				lSuccessMsg.setText("Successfully Registered!");
				System.out.println("Successfully Registered");
			}
		 	else
			{
		 		Alert alt = new Alert(AlertType.ERROR);
				alt.setHeaderText("Something Wrong!");
				alt.setContentText("Your Registration Failed! Try Again with valid data");
				Optional<ButtonType> ans = alt.showAndWait();
				if(ans.isPresent() && ans.get()==ButtonType.OK)
				{
					clickedClear();
				}
			}
	}
	else
	{
	
		Alert alt = new Alert(AlertType.ERROR);
		alt.setHeaderText("Something Wrong!");
		alt.setContentText("Your Registration Failed! Try Again with valid data");
		Optional<ButtonType> ans = alt.showAndWait();
		if(ans.isPresent() && ans.get()==ButtonType.OK)
		{
			clickedClear();
		}
	}
	
}

	
	public void clickedClear()
	{
		textFieldName.setText("");
		textFieldPhoneNo.setText("");
		textFieldNRC.setText("");
		textFieldAddress.setText("");
		password.setText("");
		rePassword.setText("");
		textFieldName.requestFocus();
		labelPasswordErr.setText("");
		labelRePasswordErr.setText("");
		labelNameFormat.setText("");
		labelPhoneFormat.setText("");
		labelNrcFormat.setText("");
		textFieldStaffId.setText("");
		lSuccessMsg.setText("");
		staffIdErr.setText("");
		rbAdmin.setSelected(true);
	}
	
	public BorderPane getMainRegisterPane() {
		createNodes();
		defineLayout();
		defineStyle();
		return mainRegisterPane;
	}

	public void setMainRegisterPane(BorderPane mainRegisterPane) {
		this.mainRegisterPane = mainRegisterPane;
	}

	public static void main(String[] args)
	{
		Application.launch(args);

	}


}
