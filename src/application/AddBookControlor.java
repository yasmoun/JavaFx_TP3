package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class AddBookControlor implements Initializable {
    @FXML
    private TextField tfLastName;
    @FXML
    private TextField tfFirstName;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button addBtn;
    @FXML
    private Button exportBtn;
    @FXML
    private Button importBtn;
    @FXML
    private Button removeBtn;
    @FXML
    private Button quitBtn;
    @FXML
    private TableView<Person> table;
    @FXML
    private TableColumn<Person, String> emailCol;
    @FXML
    private TableColumn<Person, String> FirstNameCol;
    @FXML
    private TableColumn<Person, String> lastNameCol;
    private DataClass data;

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        data = new DataClass();

        FirstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        // Permettre l'édition des cellules
        FirstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        FirstNameCol.setOnEditCommit(event -> {
            Person person = event.getRowValue();
            person.setFirstName(event.getNewValue());
        });

        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(event -> {
            Person person = event.getRowValue();
            person.setLastName(event.getNewValue());
        });

        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(event -> {
            Person person = event.getRowValue();
            person.setEmail(event.getNewValue());
        });

        table.setEditable(true);
    }


    @FXML
    private void remove() {
        Person selectedPerson = table.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            table.getItems().remove(selectedPerson);
        }
    }

    @FXML
    private void importer() {
        ObservableList<Person> importedData = FXCollections.observableArrayList(data.getImportList());
        table.getItems().addAll(importedData);    }

    @FXML
    private void export() {
        data.setExportlist(table.getItems());
    }

    @FXML
    private void quit() {
        System.exit(0);
    }

    @FXML
    private void add() {
        if (tfFirstName.getText().isEmpty() || tfLastName.getText().isEmpty() || tfEmail.getText().isEmpty()) {
            showAlert("Tous les champs doivent être remplis.");
            return;
        }

        if (!isEmailAdress(tfEmail.getText())) {
            showAlert("Adresse email invalide.");
            return;
        }

        table.getItems().add(new Person(tfFirstName.getText(), tfLastName.getText(), tfEmail.getText()));

        tfFirstName.clear();
        tfLastName.clear();
        tfEmail.clear();
    }

    public static boolean isEmailAdress(String email) {
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Avertissement");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}