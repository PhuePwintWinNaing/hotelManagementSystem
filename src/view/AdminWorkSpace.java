package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.DBHandler;


public class AdminWorkSpace{
	private Stage adminStage;
	
	private Label lStaff,lOption1,lOption2,lOption3,lOption4,lOption5,lOption6,
					lOption7,lOption8,lOption9,lTitle,logout;

	private VBox leftPane;
	private BorderPane topPane,mainAdminPane;
	private HBox staffBox,nameBox;
	RoomListTable roomListTable = new RoomListTable();
	RegisterForm register = new RegisterForm();
	AddNewRoom addNewRoom = new AddNewRoom();
	AddRoomTypeForm addRoomType = new AddRoomTypeForm();
	ReceptionistInformation receptionist = new ReceptionistInformation();
	AdminInformation admin = new AdminInformation();
	AdminWorkSpaceCenter center = new AdminWorkSpaceCenter();
	ChangeAdminPassword changePassword = new ChangeAdminPassword();
	LogBooksTable logbook = new LogBooksTable();
	ReportView rv = new ReportView();

	
	
	public void createTopPane()
	{	
		
		lTitle = new Label("Admin Dashboard");
		lTitle.setId("title");
		FileInputStream tit;
		try {
			tit = new FileInputStream("images/dashboard.png");
			Image myImg = new Image(tit);
		     ImageView myView = new ImageView(myImg);
		     myView.setFitHeight(30);
		      myView.setPreserveRatio(true);
		      lTitle.setGraphic(myView);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		lTitle.setOnMouseClicked(e->{
			mainAdminPane.setCenter(null);
			mainAdminPane.setCenter(center.getMainCenterPane());
		});
		
		logout = new Label("");
		FileInputStream out;
		try {
			out = new FileInputStream("images/mainLogout.png");
			Image img = new Image(out);
		     ImageView view0 = new ImageView(img);
		      view0.setFitHeight(30);
		      view0.setPreserveRatio(true);
		      logout.setGraphic(view0);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		logout.setOnMouseClicked(e->{	
			adminStage.hide();
			new LoginForm().getLoginFormStage();
			
		});
		lStaff = new Label(LoginForm.getAdminName());
		lStaff.setId("active-user");
		try {
			FileInputStream fis = new FileInputStream("images/contact.png");
			Image img = new Image(fis);
			ImageView imgView = new ImageView(img);
			nameBox = new HBox(imgView,lStaff);
			lStaff.getStyleClass().add("staff-name");
			staffBox = new HBox(50,nameBox,logout);
			imgView.setFitHeight(30);
		      imgView.setPreserveRatio(true);
		} catch (FileNotFoundException e1) {
			staffBox = new HBox(30,lStaff,logout);
			logout.setStyle("-fx-text-fill:red");
			
		}
		
		
		topPane = new BorderPane();
		topPane.setLeft(lTitle);
		topPane.setRight(staffBox);
		topPane.setPadding(new Insets(10));
		topPane.setId("top-pane-style");
	}
	
	public void createLeftPane()
	{
//	lHeader = new Label("Admin Dashboard");
//		lHeader.setId("dashboard");
//		FileInputStream dash;
//		try {
//			dash = new FileInputStream("images/desk.png");
//			Image img10 = new Image(dash);
//		     ImageView view10 = new ImageView(img10);
//		      view10.setFitHeight(30);
//		      view10.setPreserveRatio(true);
//		      lHeader.setGraphic(view10);
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		lHeader.setOnMouseClicked(e->{
//			mainAdminPane.setCenter(null);
//			mainAdminPane.setCenter(center.getMainCenterPane());
//		});
		

		lOption1 = new Label("Room Info");
		FileInputStream fis;
		try {
			fis = new FileInputStream("images/door.png");
			Image img1 = new Image(fis);
		     ImageView view1 = new ImageView(img1);
		      view1.setFitHeight(30);
		      view1.setPreserveRatio(true);
		      lOption1.setGraphic(view1);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		lOption1.getStyleClass().add("left-pane-option");
		lOption1.setOnMouseClicked(e->{
			mainAdminPane.setCenter(null);
			mainAdminPane.setCenter(roomListTable.getMainPane());
			lOption1.getStyleClass().add("active-label");
			lOption2.getStyleClass().remove("active-label");
			lOption3.getStyleClass().remove("active-label");
			lOption4.getStyleClass().remove("active-label");
			lOption5.getStyleClass().remove("active-label");
			lOption6.getStyleClass().remove("active-label");
			lOption7.getStyleClass().remove("active-label");
			lOption8.getStyleClass().remove("active-label");
			lOption9.getStyleClass().remove("active-label");
		});
		
		
		lOption2 = new Label("Add Room");
		FileInputStream plus = null;
		try {
			plus = new FileInputStream("images/room.png");
			Image img2 = new Image(plus);
		     ImageView view2 = new ImageView(img2);
		      view2.setFitHeight(30);
		      view2.setPreserveRatio(true);
		      lOption2.setGraphic(view2);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		lOption2.getStyleClass().add("left-pane-option");
		lOption2.setOnMouseClicked(e->{
			mainAdminPane.setCenter(null);
			mainAdminPane.setCenter(addNewRoom.getMainAddNewRoom());
			lOption2.getStyleClass().add("active-label");
			lOption1.getStyleClass().remove("active-label");
			lOption3.getStyleClass().remove("active-label");
			lOption4.getStyleClass().remove("active-label");
			lOption5.getStyleClass().remove("active-label");
			lOption6.getStyleClass().remove("active-label");
			lOption7.getStyleClass().remove("active-label");
			lOption8.getStyleClass().remove("active-label");
			lOption9.getStyleClass().remove("active-label");
		});
		
		
		lOption3 = new Label("Add Room Type");
		FileInputStream type=null;
		try {
			type = new FileInputStream("images/add.png");
			Image img3 = new Image(type);
		     ImageView view3 = new ImageView(img3);
		      view3.setFitHeight(30);
		      view3.setPreserveRatio(true);
		      lOption3.setGraphic(view3);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		lOption3.getStyleClass().add("left-pane-option");
		lOption3.setOnMouseClicked(e->{
			mainAdminPane.setCenter(null);
			mainAdminPane.setCenter(addRoomType.getMainPane());
			lOption1.getStyleClass().remove("active-label");
			lOption2.getStyleClass().remove("active-label");
			lOption3.getStyleClass().add("active-label");
			lOption4.getStyleClass().remove("active-label");
			lOption5.getStyleClass().remove("active-label");
			lOption6.getStyleClass().remove("active-label");
			lOption7.getStyleClass().remove("active-label");
			lOption8.getStyleClass().remove("active-label");
			lOption9.getStyleClass().remove("active-label");
		});
		
		
		lOption4 = new Label("Register Form");
		FileInputStream reg=null;
		try {
			reg = new FileInputStream("images/register.png");
			Image img4 = new Image(reg);
		     ImageView view4 = new ImageView(img4);
		      view4.setFitHeight(30);
		      view4.setPreserveRatio(true);
		      lOption4.setGraphic(view4);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		lOption4.getStyleClass().add("left-pane-option");
		lOption4.setOnMouseClicked(e->{
			mainAdminPane.setCenter(null);
			mainAdminPane.setCenter(register.getMainRegisterPane());
			lOption1.getStyleClass().remove("active-label");
			lOption2.getStyleClass().remove("active-label");
			lOption3.getStyleClass().remove("active-label");
			lOption4.getStyleClass().add("active-label");
			lOption5.getStyleClass().remove("active-label");
			lOption6.getStyleClass().remove("active-label");
			lOption7.getStyleClass().remove("active-label");
			lOption8.getStyleClass().remove("active-label");
			lOption9.getStyleClass().remove("active-label");
		});
		
		lOption5 = new Label("Receptionist");
		FileInputStream reception=null;
		try {
			reception = new FileInputStream("images/receptionist.png");
			Image img5 = new Image(reception);
		     ImageView view5 = new ImageView(img5);
		      view5.setFitHeight(30);
		      view5.setPreserveRatio(true);
		      lOption5.setGraphic(view5);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		lOption5.getStyleClass().add("left-pane-option");
		lOption5.setOnMouseClicked(e->{
			mainAdminPane.setCenter(null);
			mainAdminPane.setCenter(receptionist.getMainReceptionistListPane());
			lOption1.getStyleClass().remove("active-label");
			lOption2.getStyleClass().remove("active-label");
			lOption3.getStyleClass().remove("active-label");
			lOption4.getStyleClass().remove("active-label");
			lOption5.getStyleClass().add("active-label");
			lOption6.getStyleClass().remove("active-label");
			lOption7.getStyleClass().remove("active-label");
			lOption8.getStyleClass().remove("active-label");
			lOption9.getStyleClass().remove("active-label");
		});
		
		lOption6 = new Label("Admin");
		FileInputStream ad=null;
		try {
			ad = new FileInputStream("images/administrator.png");
			Image img6 = new Image(ad);
		     ImageView view6 = new ImageView(img6);
		      view6.setFitHeight(30);
		      view6.setPreserveRatio(true);
		      lOption6.setGraphic(view6);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		lOption6.getStyleClass().add("left-pane-option");
		lOption6.setOnMouseClicked(e->{
			mainAdminPane.setCenter(null);
			mainAdminPane.setCenter(admin.getMainAdminListPane());
			lOption1.getStyleClass().remove("active-label");
			lOption2.getStyleClass().remove("active-label");
			lOption3.getStyleClass().remove("active-label");
			lOption4.getStyleClass().remove("active-label");
			lOption5.getStyleClass().remove("active-label");
			lOption6.getStyleClass().add("active-label");
			lOption7.getStyleClass().remove("active-label");
			lOption8.getStyleClass().remove("active-label");
			lOption9.getStyleClass().remove("active-label");
		});
		
		

		lOption7 = new Label("Log Book");
		FileInputStream log=null;
		try {
			log = new FileInputStream("images/logbook.png");
			Image img7 = new Image(log);
		     ImageView view7 = new ImageView(img7);
		      view7.setFitHeight(30);
		      view7.setPreserveRatio(true);
		      lOption7.setGraphic(view7);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		lOption7.getStyleClass().add("left-pane-option");
		lOption7.setOnMouseClicked(e->{
		mainAdminPane.setCenter(null);
		mainAdminPane.setCenter(logbook.getLogBooksPane());
		lOption1.getStyleClass().remove("active-label");
		lOption2.getStyleClass().remove("active-label");
		lOption3.getStyleClass().remove("active-label");
		lOption4.getStyleClass().remove("active-label");
		lOption5.getStyleClass().remove("active-label");
		lOption6.getStyleClass().remove("active-label");
		lOption7.getStyleClass().add("active-label");
		lOption8.getStyleClass().remove("active-label");
		lOption9.getStyleClass().remove("active-label");
	});
		lOption8 = new Label("Reports");
		FileInputStream rep=null;
		try {
			rep = new FileInputStream("images/report.png");
			Image img8 = new Image(rep);
		     ImageView view8 = new ImageView(img8);
		      view8.setFitHeight(30);
		      view8.setPreserveRatio(true);
		      lOption8.setGraphic(view8);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		lOption8.getStyleClass().add("left-pane-option");
		lOption8.setOnMouseClicked(e->{
			mainAdminPane.setCenter(null);
			mainAdminPane.setCenter(rv.getMainPane());
			lOption1.getStyleClass().remove("active-label");
			lOption2.getStyleClass().remove("active-label");
			lOption3.getStyleClass().remove("active-label");
			lOption4.getStyleClass().remove("active-label");
			lOption5.getStyleClass().remove("active-label");
			lOption6.getStyleClass().remove("active-label");
			lOption7.getStyleClass().remove("active-label");
			lOption8.getStyleClass().add("active-label");
			lOption9.getStyleClass().remove("active-label");
		});
		
		lOption9 = new Label("Change Password");
		FileInputStream setting=null;
		try {
			setting = new FileInputStream("images/setting.png");
			Image img9 = new Image(setting);
		     ImageView view9 = new ImageView(img9);
		      view9.setFitHeight(30);
		      view9.setPreserveRatio(true);
		      lOption9.setGraphic(view9);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		lOption9.getStyleClass().add("left-pane-option");
		lOption9.setOnMouseClicked(e->{
			mainAdminPane.setCenter(null);
			mainAdminPane.setCenter(changePassword.getChangePasswordPane());
			lOption1.getStyleClass().remove("active-label");
			lOption2.getStyleClass().remove("active-label");
			lOption3.getStyleClass().remove("active-label");
			lOption4.getStyleClass().remove("active-label");
			lOption5.getStyleClass().remove("active-label");
			lOption6.getStyleClass().remove("active-label");
			lOption7.getStyleClass().remove("active-label");
			lOption8.getStyleClass().remove("active-label");
			lOption9.getStyleClass().add("active-label");
		});
		
		leftPane = new VBox(20,lOption1,lOption2,lOption3,lOption4,lOption5,
							lOption6,lOption7,lOption8,lOption9);
		leftPane.getStyleClass().add("left-pane");
	}
	
	public void createMainPane()
	{
		mainAdminPane = new BorderPane();
		mainAdminPane.setTop(topPane);
		mainAdminPane.setLeft(leftPane);
		mainAdminPane.setCenter(center.getMainCenterPane());
		
	}
	public AdminWorkSpace()
	{
		adminStage = new Stage();
		createTopPane();
		createLeftPane();
		createMainPane();
		
		Scene scene = new Scene(mainAdminPane,1100,600);
		
		URL url = this.getClass().getResource("style.css");
		scene.getStylesheets().add(url.toExternalForm());
		
		adminStage.setScene(scene);
		adminStage.setTitle("Admin Dashboard");
		adminStage.show();
	}
	

}
