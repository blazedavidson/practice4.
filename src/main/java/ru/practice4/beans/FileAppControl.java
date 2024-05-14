package ru.practice4.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileAppControl {
    @Autowired
    private FilesReadData fileData;

    public void invoke() {
        for (int i = 0; i < fileData.getDataSize(); i++) {
            var vFile =  fileData.getDataRow( i);
            if ( vFile.getError().isEmpty()) {
                var prevStr = vFile.getApplication();
                if ( !prevStr.isEmpty()) {
                    var newStr = prevStr.toLowerCase();
                    newStr = newStr.substring(0, 10);
                    if (!prevStr.equals(newStr)) vFile.setApplication(newStr);
                }
                else  vFile.setError( "FileAppControl error");
            }
        }
    }
}