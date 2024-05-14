package ru.practice4.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class FileDateControl {
    @Autowired
    private FilesReadData fileData;
    public void invoke() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        sdf.setLenient(false);
        for (int i = 0; i < fileData.getDataSize(); i++) {
            var vFile = fileData.getDataRow(i);
            if (vFile.getError().isEmpty()) {
                var vDate = vFile.getStrDate();
                try {Date dDate = sdf.parse(vDate);}
                catch (Exception ex) {vFile.setError("FileDateControl error");}
            }
        }
    }
}