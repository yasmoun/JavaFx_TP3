package application;

import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

public class EditingCell extends TableCell<Person, String> {

    private TextField textField;

    public EditingCell() {
        // Initialisation du TextField
        textField = new TextField();
        
        // Configuration de l'action de double-clic pour commencer l'édition
        this.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !isEmpty()) {
                startEdit();
            }
        });
    }

    @Override
    public void startEdit() {
        super.startEdit();
        
        // Affichage du texte dans le TextField
        textField.setText(getItem());
        setText(null);
        
        // Ajout du TextField à la cellule
        setGraphic(textField);
        
        // Sélection du texte dans le TextField
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        
        // Suppression du TextField de la cellule
        setGraphic(null);
        
        // Affichage du texte dans la cellule
        setText(getItem());
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if (isEditing()) {
                // Affichage du TextField lors de l'édition
                setText(null);
                setGraphic(textField);
            } else {
                // Affichage du texte normal dans la cellule
                setText(getItem());
                setGraphic(null);
            }
        }
    }
}