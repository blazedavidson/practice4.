package ru.practice4.beans;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
public class ReadFile {
    private FilesReadData filesData;
    public ReadFile() {}

    @Autowired
    public void setFilesData(FilesReadData filesData) {this.filesData = filesData;}

    public void invoke( String dirPath) {

        String sFile;
        filesData.setPath( dirPath);
        var dir = new File( dirPath);
        var listFile = dir.listFiles( );


        if (listFile != null) {
            for (File file : listFile) {
                var fileName = file.getName();
                if (file.isFile() && fileName.toLowerCase().endsWith(".txt")) {
                    try (BufferedReader buffer = new BufferedReader( new FileReader( file, StandardCharsets.UTF_8))) {
                        var iR = 1;
                        while ((sFile = buffer.readLine()) != null) {
                            System.out.println( "data :" + sFile);
                            if ( !sFile.isEmpty()) {
                                var str = sFile.replaceAll( "[\\s]+", " ");
                                var split = str.split( " ");
                                var subst = new ArrayList<>(Arrays.asList( "", "", "", "", "", ""));
                                var vI = 0;
                                for (String s1 : split) {
                                    if (vI < 6) {subst.set( vI, s1);vI++;}
                                    else break;
                                }
                                filesData.insertDatarow( fileName, iR, subst.get(0), subst.get(1), subst.get(2), subst.get(3), subst.get(4), subst.get(5));
                                System.out.println( filesData.getDataRow(filesData.getDataSize() - 1));
                            }
                            iR++;
                        }
                    }
                    catch (FileNotFoundException ex) { ex.printStackTrace(); }
                    catch (IOException ex) { ex.printStackTrace(); }
                }
            }
        }
    }
}
