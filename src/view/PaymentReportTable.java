package view;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.DBHandler;
import model.PaymentReport;

public class PaymentReportTable {
	private TableView<PaymentReport> tvPaymentReport;
	private ArrayList<PaymentReport> al;
	private TableColumn<PaymentReport,Integer> payCol;
	private TableColumn<PaymentReport,String> typeCol;
	private BorderPane mainPane;
	
	public void createPaymentReportTable()
	{
		
		tvPaymentReport = new TableView<PaymentReport>();
		
		typeCol = new TableColumn<PaymentReport,String>("Payment Type");
		typeCol.setCellValueFactory(new PropertyValueFactory<PaymentReport,String>("paymentType"));
		typeCol.setSortable(true);
		
		payCol = new TableColumn<PaymentReport,Integer>("Payment Amount");
		payCol.setCellValueFactory(new PropertyValueFactory<PaymentReport,Integer>("paymentAmount"));
		payCol.setSortable(true);
		
		tvPaymentReport.getColumns().add(typeCol);
		tvPaymentReport.getColumns().add(payCol);
		
		tvPaymentReport.setMaxWidth(300);
		tvPaymentReport.setMaxHeight(200);
		tvPaymentReport.setPadding(new Insets(30));
		tvPaymentReport.setStyle("-fx-background-color:#D6EAF8");
		
		setPaymentReportData();
		
	}
	
	
	
	
	public void setPaymentReportData()
	{
		
			al=DBHandler.getPaymentByType();
			tvPaymentReport.getItems().addAll(al);
	}
	

	
	public void createLayoutPaymentReportTable()
	{
		mainPane = new BorderPane();
		mainPane.setStyle("-fx-background-color:#F4ECF7");
		mainPane.setCenter(tvPaymentReport);
		
	}
	
	
	public void setPaymentReportTableStyle() {
		mainPane.getStyleClass().add("main-room-list-table");
		tvPaymentReport.getStyleClass().add("tv-room-list");
		
	}
	
	public BorderPane getMainPane() {
		createPaymentReportTable();
		createLayoutPaymentReportTable();
		setPaymentReportTableStyle();
		mainPane = new BorderPane();
		mainPane.setCenter(tvPaymentReport);
		
		return mainPane;
	}

	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}

	public static void main(String[] args) {
		
		Application.launch(args);
	}

}
