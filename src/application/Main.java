package application;
	
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	
	//Metoda Main de lansare a aplicatiei
	public static void main(String[] args) {
		launch(args);
	}
	
	//Declaratii initiale
	Button startButton;
	Button exitButton;
	Button procButton;
	Scene appScene;
	Scene startScene;
	Scene listingScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
			
			//Buton Start app(change view)
			startButton = new Button();
			startButton.setText("Start the App");	//Text Buton Start
			//Stilizare Buton Start
			startButton.setLayoutX(10);
			startButton.setLayoutY(20);
			startButton.setStyle("-fx-background-color: #350808; -fx-text-fill: white");
		    Font font = Font.font("Calibri", FontWeight.BOLD, 14);	//Selectie font
		    startButton.setFont(font);
		    //Efect de umbra buton
		    DropShadow shadow = new DropShadow();
		    //Adding the shadow when the mouse cursor is on
		    startButton.addEventHandler(MouseEvent.MOUSE_ENTERED, 
		      new EventHandler<MouseEvent>() {
		          @Override public void handle(MouseEvent e) {
		              startButton.setEffect(shadow);
		          }
		  });
		    //Removing the shadow when the mouse cursor is off
		    startButton.addEventHandler(MouseEvent.MOUSE_EXITED, 
		      new EventHandler<MouseEvent>() {
		          @Override public void handle(MouseEvent e) {
		        	  startButton.setEffect(null);
		          }
		  });
		    //Event de schimbat view
		    startButton.setOnAction(e -> primaryStage.setScene(appScene));
		    	    
		    
		    //Buton grafic inchidere aplicatie
		    exitButton = new Button();
		    //Stilizare grafica buton
		    Image imageDecline = new Image(getClass().getResourceAsStream("/resources/exitimg.png"));	//Selectie Imagine
		    ImageView imageView = new ImageView(imageDecline);
		    //Parametrizare dimensiuni
			imageView.setFitWidth(20);
			imageView.setFitHeight(20);
		    exitButton.setGraphic(imageView);
		    exitButton.setOnAction(e -> Platform.exit());	//Functionalitate de inchidere aplicatie
		    
		    
		    //BorderPane layout pentru prima fereastra
			BorderPane layout = new BorderPane();
			BorderPane.setAlignment(startButton, Pos.CENTER);	//Aliniere componente centru
			layout.setCenter(startButton);
			BorderPane.setAlignment(exitButton, Pos.TOP_RIGHT);	//Aliniere componente dreapta-sus
			layout.setTop(exitButton);
			//Stilizare scena
			layout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #dc143c, #661a33)");
			startScene = new Scene(layout,350,350);
			
			
			//TextLabel cu informatii personale
			Label nameLabel = new Label();
			nameLabel.setText("  Gaga Sebastian-Augustin \n  \t\t   341A3 \n         Normalization App");
			nameLabel.setTextFill(Color.WHITE);	//Alegere font
			nameLabel.setStyle("-fx-border-style: dotted; -fx-border-width: 0 1 1 0;"
	                + "-fx-border-color: white; -fx-font-weight: bold");	//Stilizare
			layout.setBottom(nameLabel);
			
			
			//Fereastra Noua
			//Application window		
			GridPane mainLayout = new GridPane();		//GridPane layout pentru a doua fereastra
			//Dimensiuni si padding
			mainLayout.setPadding(new Insets(10,10,10,10));
			mainLayout.setVgap(8);
			mainLayout.setHgap(10);
			mainLayout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #c2e59c, #64b3f4)");
			
			//Hyperlink informatii despre Normalizare
			Hyperlink link = new Hyperlink();
			link.setText("Color Normalization  ");
			link.setOnAction(e -> getHostServices().showDocument("https://en.wikipedia.org/wiki/Color_normalization"));	//Link catre Informatii
			
			//TextLabels
			Label label2 = new Label();
			label2.setText("Add Input File Path");	//Cale fisier input
			label2.setTextFill(Color.BLACK);
			
			Label label3 = new Label();
			label3.setText("Add Ouput File Path");	//Cale fisier output
			label3.setTextFill(Color.BLACK);
			
			Label label4 = new Label();	//Label modificare dimensiuni Width
			label4.setText("0");
			Label label5 = new Label();	//Label modificare dimensiuni Height
			label5.setText("0");
			
			Label label6 = new Label();	//Label cu informatii pentru selectie dimensiuni
			label6.setText("Select File Width");
			label6.setTextFill(Color.BLACK);
			Label label7 = new Label();
			label7.setText("Select File Height");	//Label cu informatii pentru selectie dimensiuni
			label7.setTextFill(Color.BLACK);
			
			//Creating a graphic Label
			Label labelGraphic = new Label();
		    Image img = new Image("/resources/pallete.png");	//Imagine grafica label
		    ImageView view = new ImageView(img);
		    //Stilizare graphic Label
		    view.setFitHeight(50);
		    view.setPreserveRatio(true);
		    labelGraphic.setText("Color");
		    labelGraphic.setGraphic(view);
			
			//Textfields for user input
			TextField filePath = new TextField();
			TextField outputPath = new TextField();

			//Progress bar and slider
			Slider slider = new Slider();
			slider.setMin(0);
			slider.setMax(100);
			
			ProgressBar pb = new ProgressBar(0);
			ProgressIndicator pi = new ProgressIndicator(0);
			//Asociere functionalitati
			slider.valueProperty().addListener(new ChangeListener<Number>() {
	            public void changed(ObservableValue<? extends Number> ov,
	                Number old_val, Number new_val) {
	                pb.setProgress(new_val.doubleValue()/100);
	                pi.setProgress(new_val.doubleValue()/100);
	            }
	        });
			
			
			
			final String[] fileWH = new String[]{"720","1080","1256"};
			//Choice Boxes
			ChoiceBox<String> cbW = new ChoiceBox<String>(FXCollections.observableArrayList(
					"720","1080","1256")
				);
			//Selectie Width
			cbW.getSelectionModel().selectedIndexProperty().addListener(
					new ChangeListener<Number>() {public void changed(ObservableValue ov, Number value, Number new_value){label4.setText(fileWH[new_value.intValue()]);}});
			
			ChoiceBox<String> cbH = new ChoiceBox<String>(FXCollections.observableArrayList(
					"720","1080","1256")
				);
			//Selectie Height
			cbH.getSelectionModel().selectedIndexProperty().addListener(
					new ChangeListener<Number>() {public void changed(ObservableValue ov, Number value, Number new_value){label5.setText(fileWH[new_value.intValue()]);}});
			
			
			
			int fileW = Integer.parseInt(label4.getText());
			int fileH = Integer.parseInt(label5.getText());
			
			
			//Buton Procesare
			procButton = new Button();
			procButton.setText("Normalize");
			procButton.setStyle("-fx-background-color: #cff28f; -fx-text-fill: black");	//Stilizare Buton
		    procButton.setFont(font);	//Selectie Font
		    procButton.setPrefSize(100,30);
		    //Adding the shadow when the mouse cursor is on
		    procButton.addEventHandler(MouseEvent.MOUSE_ENTERED, 
				      new EventHandler<MouseEvent>() {
				          @Override public void handle(MouseEvent e) {
				              procButton.setEffect(shadow);
				          }
				  });
		    //Removing the shadow when the mouse cursor is off
		    procButton.addEventHandler(MouseEvent.MOUSE_EXITED, 
				      new EventHandler<MouseEvent>() {
				          @Override public void handle(MouseEvent e) {
				        	  procButton.setEffect(null);
				          }
				  });
			//Normalizing Process
			procButton.setOnAction(e -> {
				try {
				//Citire fisier cu input de la utilizator
				String inputFilePath = filePath.getText();
				String outputFilePath = outputPath.getText();
				BufferedImage imagineSursa = null;
		        File fisierInput = new File(inputFilePath);
		        imagineSursa = ImageIO.read(fisierInput);
		        //Procesare Poza
		        BufferedImage imagineOutput = new BufferedImage(imagineSursa.getWidth(), imagineSursa.getHeight(), BufferedImage.TYPE_INT_ARGB);
		        imagineOutput = normalize(imagineSursa.getWidth(),imagineSursa.getHeight(),imagineSursa);
		        //Scriere Fisier Output
		        File fisierOutput = new File(outputFilePath);
	            ImageIO.write(imagineOutput, "PNG", fisierOutput);
	            //Terminarea procesului
	            pb.setProgress(100);
                pi.setProgress(100);
				} catch (Exception exception){}
			});

			
			//Back Button
			Button backButton = new Button("Back");
			backButton.setOnAction(e -> primaryStage.setScene(startScene));	//Revenire la fereastra de start
			
			Label toolbarLabel = new Label("Toolbar: ");
			//Toolbar
			ToolBar toolBar = new ToolBar();
			toolBar.getItems().add(toolbarLabel);
			toolBar.getItems().add(backButton);
			toolBar.getItems().add(exitButton);
			//Toggle Button
			Label toggleLabel = new Label();
			ToggleButton tb = new ToggleButton("View Image");
			tb.setStyle("-fx-background-color: #cff28f; -fx-text-fill: black");	//Stilizare
			tb.setOnMouseClicked(e -> 
			{	//Functionalitate buton Toggle pentru afisare imagine
				Image imgT = new Image("/resources/monkey.png");
			    ImageView viewT = new ImageView(imgT);
			    viewT.setFitHeight(50);
			    viewT.setPreserveRatio(true);
			    toggleLabel.setGraphic(viewT);
			    
			    if (tb.isSelected()) {
			        toggleLabel.setGraphic(viewT);
			      } else {
			        toggleLabel.setGraphic(null);
			      }
			});
			//Buton next View
			Button nextViewButton = new Button("ColorScheme");
			//Stilizare buton
			BackgroundImage backgroundImage = new BackgroundImage( new Image( getClass().getResource("/resources/rainbow.jpg").toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	        Background background = new Background(backgroundImage);
	        nextViewButton.setBackground(background);
	        nextViewButton.setFont(font);
	        nextViewButton.setStyle("-fx-text-fill: white");
	        //Adding the shadow when the mouse cursor is on
			nextViewButton.addEventHandler(MouseEvent.MOUSE_ENTERED, 
				      new EventHandler<MouseEvent>() {
				          @Override public void handle(MouseEvent e) {
				        	  nextViewButton.setEffect(shadow);
				          }
				  });
			//Removing the shadow when the mouse cursor is off
			nextViewButton.addEventHandler(MouseEvent.MOUSE_EXITED, 
				      new EventHandler<MouseEvent>() {
				          @Override public void handle(MouseEvent e) {
				        	  nextViewButton.setEffect(null);
				          }
				  });
			nextViewButton.setOnAction(e -> primaryStage.setScene(listingScene));	//Actiune pentru mutare la noua fereastra
			
			
			//Adaugare elemente in GridPane Layout
			mainLayout.add(toolBar, 0, 0);
			mainLayout.add(label2, 0, 1);
			mainLayout.add(filePath, 0, 2);
			mainLayout.add(label3, 0, 3);
			mainLayout.add(outputPath, 0, 4);
			mainLayout.add(cbW,0,5);
			mainLayout.add(label6,1,5);
			mainLayout.add(label4,2,5);
			mainLayout.add(cbH,0,6);
			mainLayout.add(label7,1,6);
			mainLayout.add(label5,2,6);
			mainLayout.add(link, 3, 1);
			mainLayout.add(labelGraphic, 0, 8);
			mainLayout.add(procButton, 2, 8);
			mainLayout.add(slider, 0,9);
			mainLayout.add(pb, 1, 9);
			mainLayout.add(pi, 2, 9);
			mainLayout.add(tb, 1, 10);
			mainLayout.add(toggleLabel, 1, 11);
			mainLayout.add(nextViewButton, 3, 11);		
			//Setare scena
			appScene = new Scene(mainLayout, 500,500);
			
			
			//Fereastra noua
			//Listing GridPane Layout
			GridPane listingLayout = new GridPane();
			listingLayout.setPadding(new Insets(10,10,10,10));
			listingLayout.setVgap(8);
			listingLayout.setHgap(10);
			listingLayout.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #ff9966, #ff5e62)");	//Stilizare
			
			//Buton Back
			Button backButton2 = new Button("Back");
			backButton2.setStyle("-fx-background-color: #350808; -fx-text-fill: white");	//Stilizare
			backButton2.setOnAction(e -> primaryStage.setScene(appScene));	//Functionalitate intoarcere la fereastra precedenta
			
			//ListView
			ListView<String> list = new ListView<String>();
			//Populare Lista valori
		    ObservableList<String> data = FXCollections.observableArrayList(
		            "chocolate", "salmon", "gold", "coral", "darkorchid",
		            "darkgoldenrod", "lightsalmon", "black", "rosybrown", "blue",
		            "blueviolet", "brown");
		    list.setItems(data);
		    //Functionalitate de generare culori in ListView
	        list.setCellFactory(new Callback<ListView<String>, 
	            ListCell<String>>() {
	                @Override 
	                public ListCell<String> call(ListView<String> list) {
	                    return new ColorRectCell();
	                }
	            }
	        );		    
	        list.setVisible(false);
	        
	        //ScrollBar
		    ScrollBar scroll = new ScrollBar();
		    scroll.setMin(0);
		    scroll.setMax(100);
		    scroll.setValue(0);
		    scroll.setOrientation(Orientation.VERTICAL);
		    
	        //Tabel
		    TableView table = new TableView();
		    final Label title = new Label("Color Names");
	        title.setFont(new Font("Calibri", 20));
	        table.setEditable(true);
	        //Populare date Tabel
	        final ObservableList<TableData> tableData = FXCollections.observableArrayList(
	                new TableData("1", "Chocolate", "#D2691E"),
	                new TableData("2", "Salmon", "#FF8C69"),
	                new TableData("3", "Gold", "#FFD700"),
	                new TableData("4", "Coral", "#FF7F50"),
	                new TableData("5", "Darkorchid", "#9932CC"),
	                new TableData("6", "Darkgoldenrod", "#B8860B"),
	                new TableData("7", "Lightsalmon", "#FFA07A"),
	                new TableData("8", "Black", "#000000"),
	                new TableData("9", "Rosybrown", "#BC8F8F"),
	                new TableData("10", "Blue", "#0000FF"),
	                new TableData("11", "Blueviolet", "#8A2BE2"),
	                new TableData("12", "Brown", "#A52A2A")
	             );
	             //Creare coloane
	             TableColumn ColorID = new TableColumn("ColorID");
	             ColorID.setCellValueFactory(new PropertyValueFactory<>("ColorID"));
	             TableColumn ColorName = new TableColumn("ColorName");
	             ColorName.setCellValueFactory(new PropertyValueFactory("ColorName"));
	             TableColumn ColorCode = new TableColumn("ColorCode");
	             ColorCode.setCellValueFactory(new PropertyValueFactory("ColorCode"));
	             //Adding data to table
	             ObservableList<String> colorList = FXCollections.observableArrayList();
	             table.setItems(tableData);
	             table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	             table.getColumns().addAll(ColorID, ColorName, ColorCode);
	             table.setVisible(false);
		    
	             
	 		//Checkbox
	 		CheckBox check1 = new CheckBox("View Color Scheme");
	 		//Functionalitate de afisare si ascundere componente
	 		check1.selectedProperty().addListener(e -> {
	 			if (check1.isSelected()){
	 		    	list.setVisible(true);
	 		    	table.setVisible(true);
	 		    	} else {
	 		    		list.setVisible(false);
	 		    		table.setVisible(false);
	 		    	}
	 		    });
	             
		    //Adaugare Elemente
		    listingLayout.add(scroll, 3, 3);
			listingLayout.add(backButton2, 0, 1);
			listingLayout.add(check1, 0, 2);
			listingLayout.add(list, 0, 3);
			listingLayout.add(table, 1, 3);
			//Setare scena
			listingScene = new Scene(listingLayout, 500,500);
			
			
			//Stage properties
			primaryStage.setScene(startScene);
			primaryStage.setTitle("Normalize App");
			primaryStage.setResizable(false);
			primaryStage.show();	
	}
	
	//Metoda de Normalizare - Procesul de editare poza
	public BufferedImage normalize(int x, int y, BufferedImage img) {
		//Initializare pixeli si variabile auxiliare
		int[][] pixels = new int[x][y];
		int sum = 0;
		int average = 0;
		//Citire imagine
		BufferedImage imagineOutput = new BufferedImage(x,y,BufferedImage.TYPE_INT_ARGB);
        for( int i = 0; i < x; i++ )
        {
        	for( int j = 0; j < y; j++ )
        	{
        		//Achizitionare date din imagine
                pixels[i][j] = img.getRGB(i, j);
                
                sum += pixels[i][j];            
        	}    	
        }
        //Calcule pentru normalizare
        average = sum / (x*y);
        for( int i = 0; i < x; i++ )
        {
        	for( int j = 0; j < y; j++ )
        	{	
        		//Algoritm Normalizare
        		pixels[i][j] = (int)(pixels[i][j]+Math.sqrt((double)((1/average)*x*y))+9);
        		imagineOutput.setRGB(i, j, pixels[i][j]);
        		 
        	}
        }
        
        return imagineOutput;
	}
	
	//Clasa generare culori
	static class ColorRectCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(100, 20);
            if (item != null) {
                rect.setFill(Color.web(item));
                setGraphic(rect);
            }
        }
    }
	
	//Clasa Model pentru datele din Tabel
	public class TableData {
		   SimpleStringProperty ColorID;
		   SimpleStringProperty ColorName;
		   SimpleStringProperty ColorCode;
		   //Constructor
		   TableData(String ColorID, String ColorName, String ColorCode) {
		      this.ColorID = new SimpleStringProperty(ColorID);
		      this.ColorName = new SimpleStringProperty(ColorName);
		      this.ColorCode = new SimpleStringProperty(ColorCode);
		   }
		   //Getteri si setteri
		   public String getColorID(){
		      return ColorID.get();
		   }
		   public void setColorID(String colorID){
			   ColorID.set(colorID);
		   }
		   public String getColorName(){
		      return ColorName.get();
		   }
		   public void setColorName(String colorName){
			   ColorName.set(colorName);
		   }
		   public String getColorCode(){
		      return ColorCode.get();
		   }
		   public void setColorCode(String colorCode){
			   ColorCode.set(colorCode);
		   }
		}
	
}

