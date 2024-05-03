package application;

import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;

public class EditingCell extends TableCell<Person, String> {

    private TextField textField;

    public EditingCell() {
        // Initialisation du TextField
        textField = new TextField();
        
        this.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !isEmpty()) {
                startEdit();
            }
        });
    }

    @Override
    public void startEdit() {
        super.startEdit();
        
        textField.setText(getItem());
        setText(null);
        
        setGraphic(textField);
        
        textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        
        setGraphic(null);
        
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
                setText(null);
                setGraphic(textField);
            } else {
                setText(getItem());
                setGraphic(null);
            }
        }
    }
}
