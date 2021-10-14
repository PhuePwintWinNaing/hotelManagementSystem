package view;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;


public class LabelPayment extends Application{
	
	private PieChart paymentChart;
	private VBox vb;
	private BorderPane mainPane;
	private LineChart line;
	
	private PaymentReportTable paymentTable = new PaymentReportTable();
	
	public void showPaymentChart()
	{
		paymentChart = new PieChart();
		vb = new VBox();
		ArrayList<PaymentReport> al = DBHandler.getPaymentByType();
		for(PaymentReport pt:al)
		{
			PieChart.Data data = new Data(pt.getPaymentType(),pt.getPaymentAmount());
			paymentChart.getData().addAll(data);
			
		}
		vb.getChildren().add(paymentChart);
		mainPane = new BorderPane();
		mainPane.setCenter(paymentTable.getMainPane());
		mainPane.setMargin(paymentTable.getMainPane(), new Insets(0,0,0,50));
		mainPane.setLeft(vb);
		mainPane.setStyle("-fx-background-color:#D6EAF8");
		
	}

	
	
	public BorderPane getMainPane() {
		showPaymentChart();
		return mainPane;
	}



	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}



	@Override
	public void start(Stage st) throws Exception {
		showPaymentChart();
		Scene sc = new Scene(mainPane);
		st.setScene(sc);
		st.show();
		
	}
	
	public static void main(String args[]) {
		Application.launch(args);
	}
	

}
