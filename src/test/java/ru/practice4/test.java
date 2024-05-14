package ru.practice4;

import ru.practice4.beans.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import java.io.File;
import java.util.*;

@ContextConfiguration(classes = { config.class}, loader = AnnotationConfigContextLoader.class )
@SpringBootTest( classes = {Main.class} )
public class test {
    int i;
    @Autowired
    FilesReadData fileData;
    @Autowired
    ReadFile readData;
    @Autowired
    FileFullNameControl nameControl;
    @Autowired
    FileAppControl appControl;
    @Autowired
    FileDateControl dateControl;
    @Autowired
    SQLUpdate sqlUpdate;
    @Autowired
    SelectUsers sqlUser;
    @Autowired
    SelectLogins sqlLogin;
    private int checkErr(FilesReadData readData, String s) {
        int iE = 0;
        for(i = 0; i < readData.getDataSize(); i++) {
            String strErr = readData.getDataRow(i).getError();
            if ( !strErr.isEmpty() && (strErr.contains( s))) iE++;
        }
        return iE;
    }

    @Test
    public void test() {
        var hUser = new HashMap<String, Long>();
        String[] args = new String[0];
        String src = "src/main/resources";
        String nameControl = src + "/FileFullNameControl.txt";

        System.setProperty( "USER", "postgres");
        System.setProperty( "PASS", "blaze");
        System.setProperty( "DB", "jdbc:postgresql://localhost:5432/postgres");

        readData.invoke(src);
        Assertions.assertEquals(fileData.getDataSize(), 5);
        File file = new File( nameControl);
        if (file.exists()) file.delete();
        this.nameControl.invoke();
        Assertions.assertEquals( checkErr(fileData,"FileFullNameControl:"), 4);
        Assertions.assertTrue( file.exists());

        appControl.invoke();
        Assertions.assertEquals( checkErr(fileData,"FileAppControl:"), 1);
        dateControl.invoke();
        Assertions.assertEquals( checkErr(fileData,"FileDateControl:"), 1);
        sqlUpdate.invoke(sqlUser, sqlLogin);
        Assertions.assertEquals( sqlUser.count(), 2);
        Assertions.assertEquals( sqlLogin.count(), 4);

    }
}
