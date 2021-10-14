package view;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import model.*;

public class PaymentForm{
	private Label lDate,lAmount,lType,lName,lPhone,lAccount,lTitle;
	private DatePicker payDate;
	private TextField tfAmount,tfName,tfPhone,tfAccount;
	private ComboBox<PaymentType> comboPaymentType;
	private Button btnConfirm;
	
	private GridPane paymentPane;
	private BorderPane mainPane;
	private ArrayList<PaymentType> payType = new ArrayList<PaymentType>();
	private HBox topBox;
	
	public void createPaymentNodes()
	{
		lTitle = new Label("Payment Form");
		lDate = new Label("Payment Date");
		lAmount = new Label("Payment Amount");
		lType = new Label("Payment Type");
		lName = new Label("Name");
		lPhone = new Label("Phone");
		lAccount = new Label("Account");
		
		payDate = new DatePicker();
		tfAmount = new TextField();
		comboPaymentType = new ComboBox<PaymentType>();
		tfName = new TextField();
		tfPhone = new TextField();
		tfAccount = new TextField();
		btnConfirm = new Button("Confirm");
		
		payType =DBHandler.getAllPaymentTypes();
		comboPaymentType = new ComboBox<>(FXCollections.observableArrayList(payType));
		
	}
	
	public void createPaymentLayout()
	{
		paymentPane = new GridPane();
		paymentPane.add(lDate, 0, 0);
		paymentPane.add(payDate,1,0);
		
		paymentPane.add(lAmount, 0, 1);
		paymentPane.add(tfAmount, 1, 1);
		
		paymentPane.add(lType, 0, 2);
		paymentPane.add(comboPaymentType, 1, 2);
		
		paymentPane.add(lName, 0, 3);
		paymentPane.add(tfName, 1, 3);
		
		paymentPane.add(lPhone, 0, 4);
		paymentPane.add(tfPhone, 1, 4);
		
		paymentPane.add(lAccount, 0, 5);
		paymentPane.add(tfAccount, 1, 5);
		
		paymentPane.add(btnConfirm, 1, 6);
		
		paymentPane.setHgap(30);
		paymentPane.setVgap(30);
		paymentPane.setPadding(new Insets(20));
		
		topBox = new HBox(lTitle);
		topBox.setPadding(new Insets(20));
		lTitle.setStyle("-fx-text-fill:#ffffff");
		lTitle.setAlignment(Pos.TOP_LEFT);
		
		mainPane = new BorderPane();
		mainPane.setTop(topBox);
		mainPane.setCenter(paymentPane);
		
	}
	
	public void createPaymentStyle()
	{
		btnConfirm.getStyleClass().add("btn-confirm-payment");
		mainPane.getStyleClass().add("main-payment-pane");
		paymentPane.getStyleClass().add("payment-pane");
		topBox.getStyleClass().add("top-payment-pane");
		
	}
	
	
	
	public BorderPane getMainPane() {
		createPaymentNodes();
		createPaymentLayout();
		createPaymentStyle();
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
