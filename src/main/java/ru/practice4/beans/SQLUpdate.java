package ru.practice4.beans;

import ru.practice4.model.SQLLogins;
import ru.practice4.model.SQLUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class SQLUpdate {
    @Autowired
    private FilesReadData readData;
    public void invoke(SelectUsers ur, SelectLogins lg) {
        int i;
        Date dDate;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        format.setLenient(false);
        SQLUsers sqlUser;
        SQLLogins sqlLogin;
        for (i = 0; i < readData.getDataSize(); i++) {
            var rowInfo = readData.getDataRow(i);
            if (rowInfo.getError().isEmpty()) {
                sqlUser = ur.findFirstByUsername( rowInfo.getLogin());
                if (sqlUser == null) {
                    sqlUser = new SQLUsers();
                    sqlUser.setFio(rowInfo.getFirstName() + " " + rowInfo.getSurName() + " " + rowInfo.getMiddleName());
                    sqlUser.setUsername(rowInfo.getLogin());
                    ur.save(sqlUser);
                }
                sqlLogin = new SQLLogins();
                sqlLogin.setUserId( sqlUser.getId());
                sqlLogin.setApplication(rowInfo.getApplication());
                try {dDate = format.parse(rowInfo.getStrDate());}
                catch (Exception ex) {continue;}
                sqlLogin.setAccessDate(dDate);
                lg.save( sqlLogin);

            }
        }
    }
}
