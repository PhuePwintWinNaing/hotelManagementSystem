package view;

import java.net.URL;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ReportView extends Application{
	private Label report1,report2,report3;
	
	private HBox headerPane;
	private BorderPane mainPane;
	
	LabelIncome income = new LabelIncome();
	LableRoomOcc roomOcc = new LableRoomOcc();
	LabelPayment payment = new LabelPayment();
	
	public void createTopLabel()
	{
		report1 = new Label("Income Report");
		report2 = new Label("Room Occupacy");
		report3 = new Label("Payment");
	
		
		headerPane = new HBox(30,report1,report2,report3);
		headerPane.setPadding(new Insets(20));
		report1.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(income.getMainPane());
			report1.getStyleClass().add("active-label-report");
			report2.getStyleClass().remove("active-label-report");
			report3.getStyleClass().remove("active-label-report");
			
		});
		
		report2.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(roomOcc.getMainPane());
			report2.getStyleClass().add("active-label-report");
			report1.getStyleClass().remove("active-label-report");
			report3.getStyleClass().remove("active-label-report");
			
		});
		
		report3.setOnMouseClicked(e->{
			mainPane.setCenter(null);
			mainPane.setCenter(payment.getMainPane());
			report3.getStyleClass().add("active-label-report");
			report1.getStyleClass().remove("active-label-report");
			report2.getStyleClass().remove("active-label-report");
			
		});
		
		
		
	}
	
	public void createLayoutForReport()
	{
		mainPane = new BorderPane();
		mainPane.setTop(headerPane);
		mainPane.setCenter(income.getMainPane());
	}
	
	public void styleForReport()
	{
		headerPane.getStyleClass().add("report-header-pane");
		report1.getStyleClass().add("report-label");
		report2.getStyleClass().add("report-label");
		report3.getStyleClass().add("report-label");
	}

	@Override
	public void start(Stage st) throws Exception {
		createTopLabel();
		createLayoutForReport();
		styleForReport();
		Scene sc = new Scene(mainPane,900,650);
		
		URL url = this.getClass().getResource("style.css");
		sc.getStylesheets().add(url.toExternalForm());
		st.setScene(sc);
		st.show();
		
	}

	
	public BorderPane getMainPane() {
		createTopLabel();
		createLayoutForReport();
		styleForReport();
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
