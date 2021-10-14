package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.DBHandler;
import model.Income;

public class AllReports extends Application{


	
	private Label incomeReport;
	private LineChart line;
	private VBox vb;
	private BorderPane mainPane;
	
	public void createMenu()
	{
		
		incomeReport = new Label("Current And Previous Year of Incomes");
		incomeReport.setOnMouseClicked(e->{
			showIncomeReport();
		});
		mainPane = new BorderPane();
		mainPane.setTop(incomeReport);
		
	}
	
	public void showIncomeReport()
	{
		
		String month[] = {"Jan","Feb","Mar","Apr","May","June","July","Aug","Sep","Oct",
				"Nov","Dec"};
		
		int endMonth = LocalDate.now().getMonthValue();
		vb = new VBox();
		
		
		CategoryAxis xa = new CategoryAxis();
		xa.setLabel("Month");
		
		NumberAxis ya = new NumberAxis();
		ya.setLabel("Income");
		
		line = new LineChart<>(xa,ya);
		
		XYChart.Series<String, Integer> income1 = new Series<>();
		income1.setName("Monthly Income Report by 2020");
		
		try {
			ArrayList<Integer> al = DBHandler.getIncomeReport(2020);
			for(int i=0; i<al.size() || i<=endMonth;i++)
			{
				income1.getData().addAll(new XYChart.Data<>(month[i],al.get(i)));
			}
		}catch(ArrayIndexOutOfBoundsException e)
		{
			
		}
		
		XYChart.Series<String, Integer> income2 = new Series<>();
		income2.setName("Monthly Income Report by 2021");
		
		try {
			ArrayList<Integer> al = DBHandler.getIncomeReport(2021);
			for(int i=0; i<al.size() && i<endMonth;i++)
			{
				income2.getData().addAll(new XYChart.Data<>(month[i],al.get(i)));
			}
		}catch(ArrayIndexOutOfBoundsException e)
		{
			
		}
		
		line.getData().addAll(income1,income2);
		vb.getChildren().add(line);
		mainPane.setCenter(vb);
		
		
		
	
	}
	
	public void setStyleForIncomeReport()
	{
		incomeReport.getStyleClass().add("income-report-title");
	}


	public BorderPane getMainPane() {
		createMenu();
		showIncomeReport();
		setStyleForIncomeReport();
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
