package ru.practice4.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FileConstruct {
    String file;
    String login;
    @Setter
    String firstName;
    @Setter
    String surName;
    @Setter
    String middleName;
    @Setter
    String strDate;
    @Setter
    String application;
    @Setter
    String error;
    int i;
    @Override
    public String toString() {
        return "RowInfo{" + "fileName='" + file + '\'' + ", rowNum=" + i + ", login='" + login + '\''  // fileName rowNum login
                + ", fio1='" + firstName + '\'' + ", fio2='" + surName + '\'' + ", fio3='" + middleName + '\'' + ", " + //fio
                "strDate='" + strDate + '\'' + ", appl='" + application + '\'' + ", errMess='" + error + '\'' + '}';//strDate appl errMess
    }
    public String toLog() {
        return  file + "(" + i + ")" + login + " " + firstName + " " + surName + " " + middleName + " " + strDate + " " + application;
    }
}

