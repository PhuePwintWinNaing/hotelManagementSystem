package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DBHandler;

public class FrontDesk{

	
private Stage receptionStage;
	
	private Label lStaff,lHeader,lOption2,lOption3,lOption4,lOption5,lOption6,
					lOption7,lOption8,logout,lTitle;

	private VBox leftPane;
	private BorderPane topPane,receptionPane;
	private HBox staffBox,nameBox;

	RoomInformation roomInfo = new RoomInformation();
	FrontDeskCenter center = new FrontDeskCenter();
//	RoomTableView roomTable = new RoomTableView();
	RoomListTable roomList = new RoomListTable();
	ReservationForm reservationForm = new ReservationForm();
	LoginForm login = new LoginForm();
	ReservationLists reserveList = new ReservationLists();
	CheckOutLists checkOutLists = new CheckOutLists();
//	NewGuestForm guestForm = new NewGuestForm();
	GuestLists guestLists = new GuestLists();
	CheckInForm checkIn = new CheckInForm();
	CheckOutForm checkOut = new CheckOutForm();
	PaymentForm payment = new PaymentForm();
	CheckInLists checkInList = new CheckInLists(); 
	ChangeReceptionistPassword receptionistPassword= new ChangeReceptionistPassword();
	int receptionistId = LoginForm.getReceptionistId();
	
	
	public void createTopPane()
	{	
		lTitle = new Label("HOTEL JSE");
		lTitle.setId("title");
		
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
			LocalTime currentTime = LocalTime.now();
//			System.out.println(currentTime.format(DateTimeFormatter.ofPattern("HH:mm")));
			if(DBHandler.addReceptionistLogoutTime(receptionistId, currentTime.format(DateTimeFormatter.ofPattern("HH:mm"))))
			{
//				System.out.println("Successfully Inseted");
			}
			receptionStage.hide();
			login.getLoginFormStage();
		});
		
		lStaff = new Label(LoginForm.getReceptionistName());
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
		lHeader = new Label("Reception");
		lHeader.setId("dashboard");
		FileInputStream fis;
		try {
			fis = new FileInputStream("images/desk.png");
			Image img1 = new Image(fis);
		     ImageView view1 = new ImageView(img1);
		      view1.setFitHeight(30);
		      view1.setPreserveRatio(true);
		      lHeader.setGraphic(view1);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		lHeader.getStyleClass().add("left-pane-option");
		lHeader.setOnMouseClicked(e->{
			receptionPane.setCenter(null);
			receptionPane.setCenter(center.getMainCenterPane());
			lHeader.getStyleClass().add("active-label");
			lOption2.getStyleClass().remove("active-label");
			lOption3.getStyleClass().remove("active-label");
			lOption4.getStyleClass().remove("active-label");
			lOption5.getStyleClass().remove("active-label");
			lOption6.getStyleClass().remove("active-label");
			lOption7.getStyleClass().remove("active-label");
			lOption8.getStyleClass().remove("active-label");
			
		});

		lOption2 = new Label("New Reservation");
		FileInputStream reserve;
		try {
			reserve = new FileInputStream("images/booking.png");
			Image img2 = new Image(reserve);
		     ImageView view2 = new ImageView(img2);
		      view2.setFitHeight(30);
		      view2.setPreserveRatio(true);
		      lOption2.setGraphic(view2);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		lOption2.getStyleClass().add("left-pane-option");
		lOption2.setOnMouseClicked(e->{
			receptionPane.setCenter(null);
			receptionPane.setCenter(reservationForm.getMainPane());
			lOption2.getStyleClass().add("active-label");
			lHeader.getStyleClass().remove("active-label");
			lOption3.getStyleClass().remove("active-label");
			lOption4.getStyleClass().remove("active-label");
			lOption5.getStyleClass().remove("active-label");
			lOption6.getStyleClass().remove("active-label");
			lOption7.getStyleClass().remove("active-label");
			lOption8.getStyleClass().remove("active-label");
		});
		lOption3 = new Label("Reservation Lists");
		FileInputStream list;
		try {
			list = new FileInputStream("images/list3.png");
			Image img3 = new Image(list);
		     ImageView view3 = new ImageView(img3);
		      view3.setFitHeight(30);
		      view3.setPreserveRatio(true);
		      lOption3.setGraphic(view3);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		lOption3.getStyleClass().add("left-pane-option");
		lOption3.setOnMouseClicked(e->{
			receptionPane.setCenter(null);
			receptionPane.setCenter(reserveList.getmainListPane());
			lOption3.getStyleClass().add("active-label");
			lHeader.getStyleClass().remove("active-label");
			lOption2.getStyleClass().remove("active-label");
			lOption4.getStyleClass().remove("active-label");
			lOption5.getStyleClass().remove("active-label");
			lOption6.getStyleClass().remove("active-label");
			lOption7.getStyleClass().remove("active-label");
			lOption8.getStyleClass().remove("active-label");
			
		});
	
		lOption4 = new Label("Check In Lists");
		FileInputStream cin;
		try {
			cin = new FileInputStream("images/checkin.png");
			Image img4 = new Image(cin);
		     ImageView view4 = new ImageView(img4);
		      view4.setFitHeight(30);
		      view4.setPreserveRatio(true);
		      lOption4.setGraphic(view4);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		lOption4.getStyleClass().add("left-pane-option");
		lOption4.setOnMouseClicked(e->{
			receptionPane.setCenter(null);
			receptionPane.setCenter(checkInList.getMainCheckInPane());
			lOption4.getStyleClass().add("active-label");
			lHeader.getStyleClass().remove("active-label");
			lOption2.getStyleClass().remove("active-label");
			lOption3.getStyleClass().remove("active-label");
			lOption5.getStyleClass().remove("active-label");
			lOption6.getStyleClass().remove("active-label");
			lOption7.getStyleClass().remove("active-label");
			lOption8.getStyleClass().remove("active-label");
			
		});
		
		
		lOption5 = new Label("Check Out");
		FileInputStream cout;
		try {
			cout = new FileInputStream("images/logout.png");
			Image img5 = new Image(cout);
		     ImageView view5 = new ImageView(img5);
		      view5.setFitHeight(30);
		      view5.setPreserveRatio(true);
		      lOption5.setGraphic(view5);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		lOption5.getStyleClass().add("left-pane-option");
		lOption5.setOnMouseClicked(e->{
			receptionPane.setCenter(null);
			receptionPane.setCenter(checkOut.getMainCheckOutPane());
			lHeader.getStyleClass().remove("active-label");
			lOption5.getStyleClass().add("active-label");
			lOption2.getStyleClass().remove("active-label");
			lOption3.getStyleClass().remove("active-label");
			lOption4.getStyleClass().remove("active-label");
			lOption6.getStyleClass().remove("active-label");
			lOption7.getStyleClass().remove("active-label");
			lOption8.getStyleClass().remove("active-label");
			
		});
		
		lOption6 = new Label("Check Out Lists");
		FileInputStream pay;
		try {
			pay = new FileInputStream("images/list.png");
			Image img6 = new Image(pay);
		     ImageView view6 = new ImageView(img6);
		      view6.setFitHeight(30);
		      view6.setPreserveRatio(true);
		      lOption6.setGraphic(view6);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		lOption6.getStyleClass().add("left-pane-option");
		lOption6.setOnMouseClicked(e->{
			receptionPane.setCenter(null);
			receptionPane.setCenter(checkOutLists.getMainCheckOutPane());
			lOption6.getStyleClass().add("active-label");
			lHeader.getStyleClass().remove("active-label");
			lOption2.getStyleClass().remove("active-label");
			lOption3.getStyleClass().remove("active-label");
			lOption4.getStyleClass().remove("active-label");
			lOption5.getStyleClass().remove("active-label");
			lOption7.getStyleClass().remove("active-label");
			lOption8.getStyleClass().remove("active-label");
			
		});
	
		lOption7 = new Label("Guest Profile Details");
		FileInputStream guest;
		try {
			guest = new FileInputStream("images/guest.png");
			Image img7 = new Image(guest);
		     ImageView view7 = new ImageView(img7);
		      view7.setFitHeight(30);
		      view7.setPreserveRatio(true);
		      lOption7.setGraphic(view7);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		lOption7.getStyleClass().add("left-pane-option");
		lOption7.setOnMouseClicked(e->{
			receptionPane.setCenter(null);
			receptionPane.setCenter(new GuestProfileTable().getMainPane());
			lHeader.getStyleClass().remove("active-label");
			lOption7.getStyleClass().add("active-label");
			lOption2.getStyleClass().remove("active-label");
			lOption3.getStyleClass().remove("active-label");
			lOption4.getStyleClass().remove("active-label");
			lOption5.getStyleClass().remove("active-label");
			lOption6.getStyleClass().remove("active-label");
			lOption8.getStyleClass().remove("active-label");
			
		});
		
		
		lOption8 = new Label("Change Password");
		FileInputStream pw;
		try {
			pw = new FileInputStream("images/setting.png");
			Image img8 = new Image(pw);
		     ImageView view8 = new ImageView(img8);
		      view8.setFitHeight(30);
		      view8.setPreserveRatio(true);
		      lOption8.setGraphic(view8);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		lOption8.getStyleClass().add("left-pane-option");
		lOption8.setOnMouseClicked(e->{
			receptionPane.setCenter(null);
			receptionPane.setCenter(receptionistPassword.getChangePasswordPane());
			lOption8.getStyleClass().add("active-label");
			lHeader.getStyleClass().remove("active-label");
			lOption2.getStyleClass().remove("active-label");
			lOption3.getStyleClass().remove("active-label");
			lOption4.getStyleClass().remove("active-label");
			lOption5.getStyleClass().remove("active-label");
			lOption6.getStyleClass().remove("active-label");
			lOption7.getStyleClass().remove("active-label");
		});
		
		
		leftPane = new VBox(20,lHeader,lOption2,lOption3,lOption4,lOption5,
							lOption6,lOption7,lOption8);
		leftPane.getStyleClass().add("left-pane");
	}
	
	public void createMainPane()
	{
		receptionPane = new BorderPane();
		receptionPane.setTop(topPane);
		
		receptionPane.setLeft(leftPane);
		receptionPane.setCenter(center.getMainCenterPane());
		
	}
	
	public FrontDesk() {
		receptionStage = new Stage();
		createTopPane();
		createLeftPane();
		createMainPane();
		
		Scene scene = new Scene(receptionPane,1100,600);
		
		URL url = this.getClass().getResource("style.css");
		scene.getStylesheets().add(url.toExternalForm());
		
		receptionStage.setScene(scene);
		receptionStage.setTitle("Front Desk");
		receptionStage.show();
	}

	

}
