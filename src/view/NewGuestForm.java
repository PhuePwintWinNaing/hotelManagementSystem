package view;

import java.net.URL;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.DBHandler;

public class NewGuestForm extends Application{
	
	private Label lId,lName,lNRC,lPhone,lEmail,lAddress,lTitle,lSuccessMsg;
	private TextField tfId,tfName,tfNRC,tfPhone,tfEmail,tfAddress;
	private Button btnSave,btnClear;
	
	private BorderPane mainPane;
	private GridPane guestPane;
	private HBox headerPane;

	public void createGuestNodes()
	{
		lSuccessMsg = new Label("");
		lTitle = new Label("Guest Information");
		lId = new Label("ID");
		lName = new Label("Name");
		lPhone = new Label("Phone");
		lNRC = new Label("NRC");
		lEmail = new Label("Email");
		lAddress = new Label("Address");
		
		tfId = new TextField();
		tfId.setText(CheckInLists.getGuestId()+"");
		tfName = new TextField();
		tfName.setText(CheckInLists.getName());
		tfPhone = new TextField();
		tfPhone.setText(CheckInLists.getPhone());
		tfNRC = new TextField();
		tfEmail = new TextField();
		tfAddress = new TextField();
		
		btnSave = new Button("Save");
		btnSave.setOnAction(e->{
			clickedGuestSave();
		});
		
		btnClear = new Button("Clear");
		btnClear.setOnAction(e->{
			clickedGuestClear();
		});
	}
	
	public void defineGuestLayout()
	{
		headerPane = new HBox(20,lTitle,lSuccessMsg);
		HBox.setMargin(lSuccessMsg,new Insets(0,0,0,400));
		lTitle.getStyleClass().add("check-in-list-title");
		headerPane.setPadding(new Insets(20));
		headerPane.setAlignment(Pos.CENTER_LEFT);
		
		guestPane = new GridPane();
		
		guestPane.add(lId, 0, 0);
		guestPane.add(tfId, 1, 0);
		
		guestPane.add(lName,0,1);
		guestPane.add(tfName, 1, 1);
		
		guestPane.add(lPhone, 0, 2);
		guestPane.add(tfPhone, 1, 2);
		
		guestPane.add(lNRC,0,3);
		guestPane.add(tfNRC,1,3);
		
		guestPane.add(lEmail, 0, 4);
		guestPane.add(tfEmail, 1, 4);
		
		guestPane.add(lAddress, 0, 5);
		guestPane.add(tfAddress, 1, 5);
		
		guestPane.add(btnSave, 0, 6);
		guestPane.add(btnClear, 1, 6);
		guestPane.setPadding(new Insets(20));
		guestPane.setVgap(30);
		guestPane.setHgap(30);
		
		mainPane = new BorderPane();
		mainPane.setTop(headerPane);
		mainPane.setLeft(guestPane);
		mainPane.setCenter(new GuestLists().getMainPane());
		
	}

	public void clickedGuestSave()
	{
		String name,phone,nrc,email,address;
		
		int id = Integer.parseInt(tfId.getText());
		name = tfName.getText();
		phone = tfPhone.getText();
		nrc = tfNRC.getText();
		email = tfEmail.getText();
		address = tfAddress.getText();
		
//		System.out.println(id);
//		System.out.println(name);
//		System.out.println(phone);
//		System.out.println(nrc);
//		System.out.println(email);
//		System.out.println(address);
		
		if(DBHandler.fillGuestInfo(id,name,phone,nrc, email, address))
		{
			System.out.println("Successfully Save Guest Info");
			new GuestLists().setGuestData();
		
			lSuccessMsg.setText("Successfully Save Guest Info");
		}
		
	}
	
	public void clickedGuestClear()
	{
		tfName.setText("");
		tfPhone.setText("");
		tfNRC.setText("");
		tfEmail.setText("");
		tfAddress.setText("");
	}
	
	public void defineGuestStyle()
	{
		mainPane.setId("main-guest");
		btnSave.getStyleClass().add("btn-guest");
		btnClear.getStyleClass().add("btn-guest");
		tfName.getStyleClass().add("field-guest-focus");
		tfNRC.getStyleClass().add("field-guest-focus");
		tfPhone.getStyleClass().add("field-guest-focus");
		tfEmail.getStyleClass().add("field-guest-focus");
		tfAddress.getStyleClass().add("field-guest-focus");
		headerPane.getStyleClass().add("search-pane-check-in");
		lSuccessMsg.getStyleClass().add("success-msg");
	}
	
	public BorderPane getMainPane() {
		createGuestNodes();
		defineGuestLayout();
		defineGuestStyle();
		System.out.println("This is guest form of main pane");
		return mainPane;
	}
	

	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}
	
	public TextField getTfName() {
		return tfName;
	}

	public void setTfName(TextField tfName) {
		this.tfName = tfName;
	}

	public TextField getTfPhone() {
		return tfPhone;
	}

	public void setTfPhone(TextField tfPhone) {
		this.tfPhone = tfPhone;
	}

	public static void main(String[] args) {
		
		Application.launch(args);
	}

	@Override
	public void start(Stage st) throws Exception
	{
		createGuestNodes();
		defineGuestLayout();
		defineGuestStyle();
		Scene sc = new Scene(mainPane);
		
		URL url = this.getClass().getResource("style.css");
		sc.getStylesheets().add(url.toExternalForm());
		
		st.setScene(sc);
		st.setTitle("New Guest Form");
		st.show();
		
	}

}
