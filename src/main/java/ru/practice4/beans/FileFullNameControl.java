package ru.practice4.beans;

import org.springframework.context.annotation.Configuration;
import ru.practice4.model.LogTransformation;
@Configuration
public class FileFullNameControl {
    private final FilesReadData fileData;
    public FileFullNameControl(FilesReadData fileData) {
        this.fileData = fileData;
    }
    public String upperStr( String s) {
        if (s.isEmpty()) return "";
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
    @LogTransformation
    public void invoke() {
        String s;
        int i;
        var className = fileData.getClassStr( this.getClass().getSimpleName());
        for(i = 0; i < fileData.getDataSize(); i++) {
            var rowInfo =  fileData.getDataRow( i);
            s = upperStr(rowInfo.getFirstName());
            if (s.isEmpty())
                rowInfo.setError( className +"FileFullNameControl error");
            else {
                rowInfo.setFirstName(s);
                s = upperStr(rowInfo.getSurName());
                if (s.isEmpty())
                    rowInfo.setError( className + "FileFullNameControl error");
                else {
                    rowInfo.setSurName(s);
                    s = upperStr(rowInfo.getMiddleName());
                    if (s.isEmpty())
                        rowInfo.setError( className + "");
                    else
                        rowInfo.setMiddleName(s);
                }
            }
            if ((rowInfo.getFirstName().length() + rowInfo.getSurName().length() + rowInfo.getMiddleName().length() > 97 )
            || (rowInfo.getLogin().length() > 50) )
                rowInfo.setError( className + "FileFullNameControl error");
        }
    }
}
