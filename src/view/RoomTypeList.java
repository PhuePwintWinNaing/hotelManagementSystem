package view;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.util.converter.IntegerStringConverter;
import model.*;


public class RoomTypeList {
		private TableView<RoomTypeTable> tvRoomTypeTable;
		private ArrayList<RoomTypeTable> al;
		private TableColumn<RoomTypeTable,Integer> idCol,roomChargesCol;
		private TableColumn<RoomTypeTable,String> roomTypeCol,bedTypeCol;
		private TableViewSelectionModel<RoomTypeTable> selectionModel;


		public void createRoomListTable()
		{
			tvRoomTypeTable = new TableView<RoomTypeTable>();
			
			idCol = new TableColumn<RoomTypeTable,Integer>("No");
			idCol.setCellValueFactory(new PropertyValueFactory<RoomTypeTable,Integer>("no"));
			
			roomTypeCol = new TableColumn<RoomTypeTable,String>("Room Type");
			roomTypeCol.setCellValueFactory(new PropertyValueFactory<RoomTypeTable,String>("roomType"));
			
			bedTypeCol = new TableColumn<RoomTypeTable,String>("Bed Type");
			bedTypeCol.setCellValueFactory(new PropertyValueFactory<RoomTypeTable,String>("bedType"));
			

			roomChargesCol = new TableColumn<RoomTypeTable,Integer>("Room Charges");
			roomChargesCol.setCellValueFactory(new PropertyValueFactory<RoomTypeTable,Integer>("roomCharges"));
			
			
			tvRoomTypeTable.getColumns().add(idCol);
			tvRoomTypeTable.getColumns().add(roomTypeCol);
			tvRoomTypeTable.getColumns().add(bedTypeCol);
			tvRoomTypeTable.getColumns().add(roomChargesCol);
			
			tvRoomTypeTable.setMaxWidth(500);
			tvRoomTypeTable.setMaxHeight(400);
			tvRoomTypeTable.setPadding(new Insets(30));

		    ScrollPane sp = new ScrollPane(tvRoomTypeTable);
		    sp.setFitToHeight(true);
		    sp.setFitToWidth(true);
			
			selectionModel = tvRoomTypeTable.getSelectionModel();
			selectionModel.setSelectionMode(SelectionMode.SINGLE);
			
			tvRoomTypeTable.setOnMouseClicked(e->{
				if(e.getButton()==MouseButton.PRIMARY)
				{
					if(e.getClickCount()==2)
					{
						
						editRoomTypeData();
					}
				}
			});
			setRoomListData();
			
		}
		
		
		public void setRoomListData()
		{
			al=DBHandler.getRoomTypeTable();
			tvRoomTypeTable.getItems().addAll(al);
		}
		
		public void editRoomTypeData()
		{
			tvRoomTypeTable.setEditable(true);
			roomTypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
			roomTypeCol.setOnEditCommit(
					new EventHandler<CellEditEvent<RoomTypeTable,String>>()
					{

						@Override
						public void handle(CellEditEvent<RoomTypeTable, String>t) {
							((RoomTypeTable) t.getTableView().getItems().get(
									t.getTablePosition().getRow())
									).setRoomType(t.getNewValue());
							
						}
						
					}
				);
					

			
			bedTypeCol.setCellFactory(TextFieldTableCell.forTableColumn());
			bedTypeCol.setOnEditCommit(
					new EventHandler<CellEditEvent<RoomTypeTable,String>>()
					{

						@Override
						public void handle(CellEditEvent<RoomTypeTable, String>t) {
							((RoomTypeTable) t.getTableView().getItems().get(
									t.getTablePosition().getRow())
									).setBedType(t.getNewValue());
							
						}
						
					}
				);
			
			
//			bedTypeCol.setOnEditCommit(
//			t->t.getTableView().getItems().get(t.getTablePosition().getRow()).setBedType(t.getNewValue())
//			);
			
			roomChargesCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
			roomChargesCol.setOnEditCommit(
					new EventHandler<CellEditEvent<RoomTypeTable,Integer>>()
					{

						@Override
						public void handle(CellEditEvent<RoomTypeTable, Integer>t) {
							((RoomTypeTable) t.getTableView().getItems().get(
									t.getTablePosition().getRow())
									).setRoomCharges(t.getNewValue());
							
						}
						
					}
				);
			
			
//			roomChargesCol.setOnEditCommit(
//			t->t.getTableView().getItems().get(t.getTablePosition().getRow()).setRoomCharges(t.getNewValue())
//			);
			
		
		}
		
		public void setRoomListTableStyle()
		{
			tvRoomTypeTable.getStyleClass().add("reserve-list-main");
			
		}

		public TableView<RoomTypeTable> getTvRoomTypeTable() {
			createRoomListTable();
			setRoomListTableStyle();
			return tvRoomTypeTable;
		}

		public void setTvRoomTypeTable(TableView<RoomTypeTable> tvRoomTypeTable) {
			this.tvRoomTypeTable = tvRoomTypeTable;
		}
		
		

}

