package view;

import java.io.FileInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.DBHandler;

public class LoginForm extends Application{
	private static int adminId=-1,receptionistId=-1;;
	private static String adminName,receptionistName;
	private Stage loginFormStage;
	
	private Label labelStaffId,labelPassword,lErr;
	private TextField textFieldStaffId;
	private PasswordField password;
	private Button btnLogin;
	
	private GridPane loginPane,photoLoginPane;
	private BorderPane mainLoginPane,bgPane;
	private VBox loginBox;

	
	public void createNodesForLogin()
	{
		
		labelStaffId = new Label("Staff-ID");
		labelStaffId.setStyle("-fx-font-weight:bolder;-fx-text-fill:#0c0372;-fx-font-size:16");
		labelPassword = new Label("Password");
		labelPassword.setStyle("-fx-font-weight:bolder;-fx-text-fill:#0c0372;-fx-font-size:16");
		lErr = new Label();
		textFieldStaffId = new TextField();
		password = new PasswordField();
		
		btnLogin = new Button("Login");
		btnLogin.setOnAction(e->{
			clickedLogin();
			clickedClearLogin();
		});
		
	
	}
	
	public void defineLayoutForLogin()
	{
		loginPane = new GridPane();
		loginPane.add(labelStaffId, 0, 0);
		loginPane.add(textFieldStaffId, 1, 0);
		
		loginPane.add(labelPassword, 0, 1);
		loginPane.add(password, 1, 1);
		
		loginPane.setVgap(30);
		loginPane.setHgap(20);
		loginPane.setAlignment(Pos.CENTER);

		btnLogin.setAlignment(Pos.CENTER_RIGHT);
		btnLogin.setPadding(new Insets(20));
		
		loginBox = new VBox(35,loginPane,btnLogin);
		loginBox.setAlignment(Pos.CENTER);
		loginBox.setPadding(new Insets(20));
		

		lErr.setAlignment(Pos.CENTER);
		lErr.setPadding(new Insets(10));
		mainLoginPane = new BorderPane();
		mainLoginPane.setTop(lErr);
		mainLoginPane.setMargin(lErr, new Insets(100,20,0,80));
		mainLoginPane.setCenter(loginBox);
//		mainLoginPane.setBottom(btnPane);
		
		photoLoginPane = new GridPane();
		photoLoginPane.setId("login-photo");
		FileInputStream fis;
		try {
			fis = new FileInputStream("images/jseHotel.png");
			Image img = new Image(fis);
			ImageView imgView = new ImageView(img);
			photoLoginPane.add(imgView,0,0);
			photoLoginPane.add(mainLoginPane, 1, 0);
		}catch(Exception e){
			
			photoLoginPane.add(mainLoginPane, 0, 0);
		}
		bgPane = new BorderPane();
		bgPane.setCenter(photoLoginPane);
		bgPane.setStyle("-fx-background:#b5daf6");
	}
	
	public void setLoginStyle()
	{
		mainLoginPane.getStyleClass().add("main-login");
		btnLogin.getStyleClass().add("btn-login");
		lErr.getStyleClass().add("error");
	}
	
	public void clickedLogin()
	{
		String sid,pass;
		sid= textFieldStaffId.getText();
		pass = password.getText();
	
		try {
			adminId = DBHandler.loginAdmin(sid,pass);
			adminName = DBHandler.getLoginAdminName(adminId);
			receptionistId = DBHandler.loginReceptionist(sid, pass);
			receptionistName = DBHandler.getLoginReceptionistName(receptionistId);
			if(adminId!=-1)
			{
				lErr.setText("");
				loginFormStage.hide();
				new AdminWorkSpace();
			}
			else if(receptionistId != -1)
			{
				lErr.setText("");
				loginFormStage.hide();
				
				LocalTime currentTime = LocalTime.now();
//				System.out.println(currentTime.format(DateTimeFormatter.ofPattern("HH:mm")));
				if(DBHandler.addReceptionistLoginTime(receptionistId, currentTime.format(DateTimeFormatter.ofPattern("HH:mm"))))
				{
//					System.out.println("Successfully Inseted");
				}
				new FrontDesk();
				
			}
			else {
				lErr.setText("Login Failed! Try Again!!!");
			}

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void clickedClearLogin()
	{
		textFieldStaffId.setText("");
		password.setText("");
	}

	public static int getAdminId() {
		return adminId;
	}

	public static void setAdminId(int adminId) {
		LoginForm.adminId = adminId;
	}

	public static int getReceptionistId() {
		return receptionistId;
	}

	public static void setReceptionistId(int receptionistId) {
		LoginForm.receptionistId = receptionistId;
	}

	public static String getAdminName() {
		return adminName;
		
	}

	public static void setAdminName(String adminName) {
		LoginForm.adminName = adminName;
	}

	public static String getReceptionistName() {
		return receptionistName;
	}

	public static void setReceptionistName(String receptionistName) {
		LoginForm.receptionistName = receptionistName;
	}
	
	
	

	public Stage getLoginFormStage() {
		loginFormStage = new Stage();
		createNodesForLogin();
		defineLayoutForLogin();
		Scene scene = new Scene(bgPane,850,490);
		loginFormStage.setScene(scene);
		
		URL url = this.getClass().getResource("style.css");
		scene.getStylesheets().add(url.toExternalForm());
		setLoginStyle();
		
		loginFormStage.setTitle("LOGIN");
		loginFormStage.show();
		return loginFormStage;
	}

	public void setLoginFormStage(Stage loginFormStage) {
		this.loginFormStage = loginFormStage;
	}

	@Override
	public void start(Stage loginStage) throws Exception 
	{
		loginFormStage = loginStage;
		createNodesForLogin();
		defineLayoutForLogin();
		Scene scene = new Scene(bgPane,850,490);
		loginStage.setScene(scene);
		
		URL url = this.getClass().getResource("style.css");
		scene.getStylesheets().add(url.toExternalForm());
		setLoginStyle();
		
		loginStage.setTitle("Login Form");
		loginStage.show();
		
	}

	public static void main(String[] args) 
	{
		Application.launch(args);
	}

}
