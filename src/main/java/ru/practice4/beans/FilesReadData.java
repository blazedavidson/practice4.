package ru.practice4.beans;

import ru.practice4.model.FileConstruct;
import lombok.Getter;
import lombok.Setter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;



public class FilesReadData {
    ArrayList<FileConstruct> aData;
    @Getter
    @Setter
    String path;

    public FilesReadData() {
        this.aData = new ArrayList<>(100);
    }
    public int getDataSize() {
        return aData.size();
    }
    public void insertDatarow(String fileName, int rowNum, String login, String fio1, String fio2, String fio3, String strDate, String appl) {
        aData.add( new FileConstruct(fileName, login, fio1, fio2, fio3, strDate, appl, "", rowNum));
    }
    public FileConstruct getDataRow(int i) {
        if (i < aData.size()) return aData.get( i);
        return null;
    }
    public String getClassStr(String sClass) {
        var ind = sClass.indexOf("$$");
        if (ind > 0) return sClass.substring(0, ind);
        else return sClass;
    }

    public void insertLog(String sClass, String sLog, LocalDateTime dStart, Long lMs) {
        int i;
        String sFile;
        if (sLog.isEmpty()) sFile = path + "/" + sClass + ".txt";
        else sFile = path + "/" + sLog + ".txt";

        try {
            File file = new File(sFile);
            if (!file.exists()) file.createNewFile();
            try (FileWriter writer = new FileWriter( sFile, true)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.mm.yyyy HH:mm:ss");
                writer.write(sClass + ":{" + dStart.format(formatter) + " time " + lMs + " ms" + "}\n");
                for (i = 0; i < aData.size(); i++) {
                    var rowInfo = aData.get(i);
                    var strErr = rowInfo.getError();
                    if (strErr.isEmpty()) writer.append(rowInfo.toLog() + "\n");
                    else if (strErr.contains(sClass + ":")) writer.append(strErr.substring(sClass.length() + 2) + ": " + rowInfo.toLog() + "\n");
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String toString() {
        String str = "FilesReadData[";
        for(int i = 0; i < aData.size(); i++) str += i + " {" + aData.get( i).toString() + "}";
        return  str + "]";
    }
}
