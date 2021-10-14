package view;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.stage.Stage;
import model.DBHandler;

public class LabelIncome{
	
	private Label incomeReport;
	private LineChart line;
	private VBox vb;
	private BorderPane mainPane;
	IncomeReportTable incomeTable = new IncomeReportTable();
	

	
	public void setLayoutForIncomeReport()
	{
		incomeReport = new Label("Income Report");
		
		mainPane = new BorderPane();
		mainPane.setMargin(incomeReport, new Insets(10,0,10,0));
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
		
		XYChart.Series<String, Integer> income1 =new Series<>();
		
		line = new LineChart<>(xa,ya);
		try {
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
		}catch(NumberFormatException e)
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
		mainPane.setLeft(incomeTable.getMainPane());
		mainPane.setMargin(incomeTable.getMainPane(),new Insets(20,0,0,30));
		mainPane.setCenter(vb);
		mainPane.setMargin(vb,new Insets(20,0,0,30));
		mainPane.setStyle("-fx-background-color:#D6EAF8");
		
	
	}
	
	public void setStyleForIncomeReport()
	{
		incomeReport.getStyleClass().add("income-report-title");
	}


	public BorderPane getMainPane() {
		setLayoutForIncomeReport();
		showIncomeReport();
		setStyleForIncomeReport();
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
