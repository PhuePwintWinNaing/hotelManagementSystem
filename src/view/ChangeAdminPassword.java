package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import model.DBHandler;
import model.ValidChecker;


public class ChangeAdminPassword{
	
	private Label currentPassword,newPassword,confirmPassword,lTitle,currentPasswordErr,
					confirmPasswordErr,labelSuccess,newPasswordErr;

	private PasswordField pfCurrent,pfNewPass,pfConfirmPass;
	private GridPane passwordPane;
	private BorderPane changePasswordPane;
	private Button btnChange,btnCancel;
	private HBox titleBox,btnBox;
	private VBox box1,box2,box3;
	
	
	public void createNodesForChangePassword()
	{
		labelSuccess = new Label();
		currentPasswordErr = new Label();
		newPasswordErr = new Label();
		confirmPasswordErr = new Label();
		lTitle = new Label("Change Password");
		currentPassword = new Label("Current Password");
		newPassword = new Label("New Password");
		confirmPassword = new Label("Confim Password");
		pfCurrent = new PasswordField();
		pfCurrent.setOnKeyReleased(e->{
			int id = LoginForm.getAdminId();
			String password = DBHandler.getLoginAdminPassword(id);
			if(password.equals(ValidChecker.digestMsg(pfCurrent.getText())))
			{
				currentPasswordErr.setText("");
				
			}else {
				currentPasswordErr.setText("Your current password is invalid!!!");
//				pfCurrent.setText("");
			}
		});
		pfNewPass = new PasswordField();
		pfNewPass.setOnKeyReleased(e->{
			if(ValidChecker.isValidPassword(pfNewPass.getText()))
			{
				newPasswordErr.setText("");
			}else {
				newPasswordErr.setText("Your new password is invalid format! Password length must bewteen 6 to 15");
//				pfNewPass.setText("");
			}
		});
		pfConfirmPass = new PasswordField();
		pfConfirmPass.setOnKeyReleased(e->{
			if(pfNewPass.getText().equals(pfConfirmPass.getText()))
			{
				confirmPasswordErr.setText("");
			}else {
//				pfNewPass.setText("");
//				pfConfirmPass.setText("");
				confirmPasswordErr.setText("Your confirm password is did not match with new Password!!");
			}
		});
		btnChange = new Button("Change");
		btnChange.setOnAction(e->{
			updatePassword();
			
			
		});
		btnCancel = new Button("Cancel");
		btnCancel.setOnAction(e->{
			clearPasswordField();
		});
	}
	
	
	public void createLayoutForChangePassword()
	{
		passwordPane = new GridPane();
		passwordPane.add(currentPassword, 0, 0);
		box1 = new VBox(pfCurrent,currentPasswordErr);
		passwordPane.add(box1, 1, 0);
		
		passwordPane.add(newPassword, 0, 1);
		box3 = new VBox(pfNewPass,newPasswordErr);
		passwordPane.add(box3, 1, 1);
		
		passwordPane.add(confirmPassword, 0, 2);
		box2 = new VBox(pfConfirmPass,confirmPasswordErr);
		passwordPane.add(box2, 1, 2);
		
		btnBox = new HBox(30,btnChange,btnCancel);
//		passwordPane.add(btnChange, 0, 3);
//		passwordPane.add(btnCancel, 1, 3);
		passwordPane.add(btnBox, 1, 3);
//		passwordPane.setMargin(btnChange, new Insets(0,30,0,0));
		passwordPane.setHgap(30);
		passwordPane.setVgap(30);
		passwordPane.setPadding(new Insets(30));
		passwordPane.setAlignment(Pos.CENTER);
		
		
		titleBox = new HBox(100,lTitle,labelSuccess);
		lTitle.setStyle("-fx-text-fill:#ffffff");
		
		changePasswordPane = new BorderPane();
		changePasswordPane.setTop(titleBox);
		changePasswordPane.setCenter(passwordPane);
		changePasswordPane.setStyle("-fx-background-color:#D6EAF8");
		
	}
	
	public void createStyleForChangePassword()
	{
		btnChange.getStyleClass().add("btn-check-out");
		btnCancel.getStyleClass().add("clear-room-btn");
		titleBox.getStyleClass().add("check-out-top-pane");
		labelSuccess.getStyleClass().add("success-msg");
		currentPasswordErr.getStyleClass().add("err");
		confirmPasswordErr.getStyleClass().add("err");
		newPasswordErr.getStyleClass().add("err");
		pfCurrent.getStyleClass().add("password-style");
		pfNewPass.getStyleClass().add("password-style");
		pfConfirmPass.getStyleClass().add("password-style");
		
	}

	public void updatePassword()
	{
		String passwordUpdate="";
		int id = LoginForm.getAdminId();
		String password = DBHandler.getLoginAdminPassword(id);
		
		if(password.equals(ValidChecker.digestMsg(pfCurrent.getText())) && 
		ValidChecker.isValidPassword(pfNewPass.getText()) &&
		pfNewPass.getText().equals(pfConfirmPass.getText()))
		{
			passwordUpdate = pfConfirmPass.getText();
			if(!passwordUpdate.equals(""))
			{
				DBHandler.changeAdminPassword(id, passwordUpdate);
				pfCurrent.setText("");
				pfNewPass.setText("");
				pfConfirmPass.setText("");
				currentPasswordErr.setText("");
				confirmPasswordErr.setText("");
				newPasswordErr.setText("");
				labelSuccess.setText("Successfully changed the password");
			}
			else {
				labelSuccess.setText("Can't change the password");
			}
		}
		else
		{
			labelSuccess.setText("Can't change the password! Try Again");
		}
	}
		
	public void clearPasswordField()
	{
		currentPasswordErr.setText("");
		confirmPasswordErr.setText("");
		pfCurrent.setText("");
		pfNewPass.setText("");
		pfConfirmPass.setText("");
		newPasswordErr.setText("");
		labelSuccess.setText("");
	}
	
	public BorderPane getChangePasswordPane() {
		createNodesForChangePassword();
		createLayoutForChangePassword();
		createStyleForChangePassword();
		
		return changePasswordPane;
	}
	

	public void setChangePasswordPane(BorderPane changePasswordPane) {
		this.changePasswordPane = changePasswordPane;
	}

	public static void main(String args[])
	{
		Application.launch(args);
	}

}
