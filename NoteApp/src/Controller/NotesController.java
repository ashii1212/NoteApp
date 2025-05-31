package Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Note;
import dao.NotesDAO;
public class NotesController {
    @FXML private TextField titleField;
    @FXML private TextArea contentArea;
    @FXML private ListView<Note> notesList;

    private NotesDAO dao = new NotesDAO();

    @FXML
    public void initialize() {
        refreshNotes();
        notesList.getSelectionModel().selectedItemProperty().addListener((obs, oldNote, newNote) -> {
            if (newNote != null) {
                titleField.setText(newNote.getTitle());
                contentArea.setText(newNote.getContent());
            }
        });
    }

    @FXML
    public void addNote() {
        dao.addNote(titleField.getText(), contentArea.getText());
        titleField.clear();
        contentArea.clear();
        refreshNotes();
    }

    private void refreshNotes() {
        ObservableList<Note> notes = FXCollections.observableArrayList(dao.getAllNotes());
        notesList.setItems(notes);
    }

}
