package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.DBHandler;
public class AdminWorkSpaceCenter{

	private Label lTotalRooms,lRooms,lTotalAdmin,lAdmins,lTotalReceptionist,lReceptionist,
				lTotalReservations,lReserve,lTotalGuest,lGuest;
	
	private VBox roomBox,adminBox,receptionistBox,reserveBox,guestBox;
	private BorderPane mainCenterPane;
	private GridPane infoPane1,graphPane;
	
	AllReports incomeReport = new AllReports();
	RoomReport roomReport = new RoomReport();
	
	public void createNodesForAdminWorkSpaceCenter() {
		lTotalRooms = new Label("Total Rooms");
		lRooms = new Label(""+DBHandler.getTotalRooms());
		roomBox = new VBox(10,lTotalRooms,lRooms);
		roomBox.setPrefSize(130, 70);
		roomBox.setStyle("-fx-background-color:#ffbfbf");
		
		lTotalAdmin = new Label("Total Admins");
		lAdmins = new Label(""+ DBHandler.getTotalAdmins());
		adminBox = new VBox(10,lTotalAdmin,lAdmins);
		adminBox.setPrefSize(130, 70);
		adminBox.setStyle("-fx-background-color:#8eb669");
		
		lTotalReceptionist = new Label("Total Receptionists");
		lReceptionist = new Label(""+DBHandler.getTotalReceptionists());
		receptionistBox = new VBox(10,lTotalReceptionist,lReceptionist);
		receptionistBox.setPrefSize(130, 70);
		receptionistBox.setStyle("-fx-background-color:#ae94ff");
		
		lTotalReservations = new Label("Total Reservations");
		lReserve = new Label(""+DBHandler.getTotalReservations());
		reserveBox = new VBox(10,lTotalReservations,lReserve);
		reserveBox.setPrefSize(130, 70);
		reserveBox.setStyle("-fx-background-color:#ffc874");
		
		lTotalGuest = new Label("Total Guests");
		lGuest = new Label(""+DBHandler.getTotalGuests());
		guestBox = new VBox(10,lTotalGuest,lGuest);
		guestBox.setPrefSize(130, 70);
		guestBox.setStyle("-fx-background-color:#c3abb7");
		
		infoPane1 = new GridPane();
		infoPane1.add(roomBox, 0,0 );
		infoPane1.add(adminBox, 1, 0);
		infoPane1.add(receptionistBox, 2, 0);
		infoPane1.add(reserveBox, 3,0 );
		infoPane1.add(guestBox, 4, 0);
		infoPane1.setHgap(30);
		
//		lLine = new Label("Yearly Income month");
//		lBar = new Label("Yearly Room Occupied month");
		
		graphPane = new GridPane();
		graphPane.add(incomeReport.getMainPane(),0, 0);
		graphPane.add(roomReport.getMainPane(), 1, 0);
		graphPane.setHgap(20);
		graphPane.setPadding(new Insets(10));
		
		mainCenterPane = new BorderPane();
		mainCenterPane.setStyle("-fx-background-color:#D6EAF8");
		mainCenterPane.setTop(infoPane1);
		mainCenterPane.setMargin(infoPane1, new Insets(20,20,20,40));
		mainCenterPane.setCenter(graphPane);
		mainCenterPane.setMargin(graphPane, new Insets(30,10,0,0));

	}

	public void styleForAdminWorkSpaceCenter()
	{
		roomBox.getStyleClass().add("center-box");
		adminBox.getStyleClass().add("center-box");
		receptionistBox.getStyleClass().add("center-box");
		reserveBox.getStyleClass().add("center-box");
		guestBox.getStyleClass().add("center-box");
	}
	
	
	public BorderPane getMainCenterPane() {
		createNodesForAdminWorkSpaceCenter();
		styleForAdminWorkSpaceCenter();
		return mainCenterPane;
	}

	public void setMainCenterPane(BorderPane mainCenterPane) {
		this.mainCenterPane = mainCenterPane;
	}

	public static void main(String[] args) {
		
		Application.launch(args);
	}

}
