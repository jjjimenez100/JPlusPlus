package com.jplusplus.ui;
import java.nio.file.Path;
import java.util.List;

public class JPPFile {
    private Path path;
    private String filename;
    private final List<String> content;

    public JPPFile(List<String> content) {
        this(null, content);
    }
    public JPPFile(Path path, List<String> content) {
        this.path = path;
        if(path!=null)
        filename = path.toString().substring(path.toString().lastIndexOf("\\")+1);
        this.content = content;
    }
    public String getFilename(){ return filename; }
    public Path getPath(){
        return path;
    }
    public void setPath(Path path) {
        this.path = path;
        filename = path.toString().substring(path.toString().lastIndexOf("\\")+1);
    }
    public List<String> getContent(){
        return content;
    }
}
