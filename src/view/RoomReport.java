package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DBHandler;
import model.Income;

public class RoomReport extends Application{
	
	private Label roomReport;
	private BarChart roomChart;
	private VBox vb;
	private BorderPane mainPane;
	
	
	
	public void createMenu()
	{
		
		roomReport = new Label("Current Year of Room Occupied Month");
		roomReport.getStyleClass().add("income-report-title");
		roomReport.setOnMouseClicked(e->{
			showRoomReport();
		});
		mainPane = new BorderPane();
		mainPane.setTop(roomReport);
		
	}
	
	public void showRoomReport()
	{
		
		String month[] = {"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct",
				"Nov","Dec"};
		
		int endMonth = LocalDate.now().getMonthValue();
		vb = new VBox();
		
		
		CategoryAxis xa = new CategoryAxis();
		xa.setLabel("Month");
		
		NumberAxis ya = new NumberAxis();
		ya.setLabel("Room");
		
		roomChart = new BarChart<>(xa,ya);
		
		XYChart.Series<String, Integer> room1 = new Series<>();
		room1.setName("Monthly room occupied Report by 2020");
		
		ArrayList<Integer> al = DBHandler.getRoomReport(2020);
		
		try {
			for(int i=0; i<al.size();i++)
			{
				room1.getData().addAll(new XYChart.Data<>(month[i],al.get(i)));
				
			}
			
		}catch(ArrayIndexOutOfBoundsException e)
		{
			
		}

		
		XYChart.Series<String, Integer> room2 = new Series<>();
		room2.setName("Monthly room occupied Report by 2021");
		
		ArrayList<Integer> al2 = DBHandler.getRoomReport(2021);
		
		try {
			for(int i=0; i<al.size() && i<endMonth;i++)
			{
				room2.getData().addAll(new XYChart.Data<>(month[i],al2.get(i)));
				
			}
			
		}catch(ArrayIndexOutOfBoundsException e)
		{
			
		}
		roomChart.getData().addAll(room2);
		vb.getChildren().add(roomChart);
		mainPane.setCenter(vb);
		mainPane.setStyle("-fx-background-color:#D6EAF8");
		
	
	}

	

	public BorderPane getMainPane() {
		createMenu();
		showRoomReport();
		return mainPane;
	}

	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}

	@Override
	public void start(Stage st) throws Exception {
		createMenu();
		Scene sc = new Scene(mainPane,700,600);
		URL url = this.getClass().getResource("style.css");
		sc.getStylesheets().add(url.toExternalForm());
		st.setScene(sc);
		st.show();
				
	}
	
	
	public static void main(String args[])
	{
		Application.launch(args);
	}
	

}
