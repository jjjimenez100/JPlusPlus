package com.jplusplus.ui;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Arrays;

public class Controller {

    @FXML private TextArea textarea;
    private Model model;
    private JPPFile currentFile;
    @FXML private Text updates;
    @FXML private SplitPane splitPane;
    @FXML private TabPane tabs;

    public Controller(Model model){
        this.model = model;
    }
    @FXML
    protected void initialize(){
        TextEditorEvents.addTextAreaEvents(textarea, updates);
    }

    @FXML private void onOpen(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        fileChooser.setTitle("Choose a JPP File");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("JPP Files (*.jpp)", "*.jpp");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            Result<JPPFile> result = model.open(file.toPath());
            if(result.isTrue() && result.hasContent()){
                currentFile = result.getContent();
                textarea.clear();
                currentFile.getContent().forEach(e -> textarea.appendText(e + "\n"));
            }
        }
        Main.setTitle(currentFile.getFilename());
    }
    @FXML private void onSave(){
        if(currentFile!=null) {
            JPPFile file = new JPPFile(currentFile.getPath(), Arrays.asList(textarea.getText().split("\n")));
            currentFile = file;
            model.save(file);
        }
        else{
            JPPFile file = new JPPFile(Arrays.asList(textarea.getText().split("\n")));
            currentFile = file;
            model.saveNewFile(currentFile);
        }
        Main.setTitle(currentFile.getFilename());
    }
    @FXML private void onClose(){
        model.close();
    }
    @FXML private void onRun(){
        onSave();
        tabs.getTabs().add(model.run(currentFile, splitPane));
    }
    @FXML private void onStop(){

    }
    @FXML private void onAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("About");
        alert.setTitle("About");
        alert.setContentText("JPP Text Editor.\n"+"Version: "+ About.version + "\nAbout: "+ About.about);
        alert.show();

    }
}
