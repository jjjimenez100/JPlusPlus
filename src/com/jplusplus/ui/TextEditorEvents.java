package com.jplusplus.ui;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import org.fxmisc.richtext.CodeArea;

public class TextEditorEvents {

    public static void addTextAreaEvents(CodeArea area, Text text){

        area.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String[] countStr = area.getText().split("\n");
                int count = countStr.length;
                int caretPos = area.getCaretColumn();
                text.setText(count+ "Lines, "+caretPos+" Caret Position.");
            }
        });
        area.caretPositionProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String[] countStr = area.getText().split("\n");
                int count = countStr.length;
                int caretPos = area.getCaretColumn();
                text.setText(count+ " Lines, "+caretPos+" Caret Position.");
            }
        });
    }
}
