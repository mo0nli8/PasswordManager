package com.example.passwordmanager;

public class appData {
    private String userName;
    private String password;
    private String webSiteName;
    appData(String userName, String password, String webSiteName){
        this.userName = userName;
        this.password = password;
        this.webSiteName = webSiteName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getWebSiteName() {
        return webSiteName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setWebSiteName(String webSiteName) {
        this.webSiteName = webSiteName;
    }
}
