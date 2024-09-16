package ir.xenoncommunity.mapping;

import lombok.experimental.UtilityClass;

import java.util.HashMap;

@UtilityClass
public class FileMapping {
    public HashMap<String, String> fileMaps = new HashMap<>();
    public HashMap<String, String> types = new HashMap<>();

    static {
        fileMaps.put("/", "files/index.html");
        fileMaps.put("/index.html", "files/index.html");
        fileMaps.put("/projects.html", "files/projects.html");
        fileMaps.put("/styles.css", "files/styles.css");
        fileMaps.put("/projects.css", "files/projects.css");
        fileMaps.put("/script.js", "files/script.js");

        types.put("/", "text/html");
        types.put("/index.html", "text/html");
        types.put("/projects.html", "text/html");
        types.put("/styles.css", "text/css");
        types.put("/projects.css", "text/css");
        types.put("/script.js", "text/javascript");
    }
}
