package view;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.DBHandler;

public class LableRoomOcc extends Application{

	
	private BarChart roomChart;
	
	private VBox vb;
	private BorderPane mainPane;
	
	RoomOccupiedTable occ = new RoomOccupiedTable();
	
	
	
	
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
			for(int i=0; i<al.size() || i<endMonth;i++)
			{
				room1.getData().addAll(new XYChart.Data<>(month[i],al.get(i)));
				
			}
			
		}catch(ArrayIndexOutOfBoundsException e)
		{
			
		}
		
		roomChart.getData().addAll(room1);
		vb.getChildren().add(roomChart);
		mainPane = new BorderPane();
		mainPane.setRight(vb);
		mainPane.setLeft(occ.getMainPane());
		mainPane.setMargin(vb, new Insets(20,50,0,20));
		mainPane.setMargin(occ.getMainPane(), new Insets(20,0,0,50));
		mainPane.setStyle("-fx-background-color:#D6EAF8");
		
	}


	public BorderPane getMainPane() {
		showRoomReport();
		return mainPane;
	}

	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}

	@Override
	public void start(Stage st) throws Exception {
		
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
