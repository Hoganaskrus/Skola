package lab3;

import java.io.File;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import textproc.GeneralWordCounter;

public class BookReaderController extends Application {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("BookReader");
		primaryStage.setScene(scene);
		primaryStage.show();

		// Kopierar från lab.1 Scanner osv.
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
		Scanner s2 = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while (s2.hasNext()) {
			stopwords.add(s2.next().toLowerCase());
		}

		// skapar ett gwc och sätter in ord
		GeneralWordCounter gwc = new GeneralWordCounter(stopwords);
		while (s.hasNext()) {
			gwc.process(s.next().toLowerCase());
		}

		// Skapar ett set och Listview
		Set<Entry<String, Integer>> set = gwc.getWords();
		ObservableList<Entry<String, Integer>> words = FXCollections.observableArrayList(set);
		ListView<Entry<String, Integer>> listView = new ListView<Entry<String, Integer>>(words);

		// Skapar Hbox osv och sätter 2 buttons
		HBox hbox = new HBox();
		RadioButton button1 = new RadioButton("Alphabetic");

		// Förskt sortera, förstår ej hur vi comparar mellan 2 olika set när vi
		// bara har en lista, kom på entry är bokstavligen en "entry" av
		// mapen...
		button1.setOnAction(event -> {
			words.sort((Entry<String, Integer> e1, Entry<String, Integer> e2) -> e1.getKey().toLowerCase()
					.compareTo(e2.getKey().toLowerCase()));

		});
		// Skapara button2
		RadioButton button2 = new RadioButton("Frequency");
		button2.setOnAction(event -> {
			words.sort(
					(Entry<String, Integer> e1, Entry<String, Integer> e2) -> e2.getValue().compareTo(e1.getValue()));
		});
		// Skapar sökfältet och button3
		Button button3 = new Button("Search");
		TextField text = new TextField();
		button3.setOnAction(event -> {
			for (Entry<String, Integer> ent : set) {
				if (ent.getKey().equals(text.getCharacters().toString())) {
					listView.scrollTo(ent);
					//Markera i texten vart säkordet är. VALBAR UPPGIFT 4
					listView.getSelectionModel().select(ent);
					return;
				}
			}
			
			//Gör en alert ifall ent != text. VALBAR UPPGIFT 5
			Alert al = new Alert(AlertType.CONFIRMATION, "Ordet hittades ej");
			al.show();

		});
		
		//Skapar en ToggleGroup som binder ihopa 2 radiobuttons. VALBAR UPPGIFT 6
		ToggleGroup tg = new ToggleGroup();
		button1.setToggleGroup(tg);
		button2.setToggleGroup(tg);
		
		hbox.getChildren().addAll(button1, button2, text, button3);
		
		
		root.setCenter(listView);
		root.setBottom(hbox);
		s.close();
		s2.close();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}