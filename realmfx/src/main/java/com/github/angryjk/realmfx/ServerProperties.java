/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.angryjk.realmfx;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author javawork
 */
public class ServerProperties {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty host;
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    ServerProperties(Integer id,String host,String username,String password){
        this.id = new SimpleIntegerProperty(id);
        this.host = new SimpleStringProperty(host);
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);    
    }
    public Integer getId(){
        return id.get();
    }
    /**
     * @return the host
     */
    public String getHost() {
        return host.get();
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username.get();
    }
    /**
     * @return the password
     */
    public String getPassword() {
        return password.get();
    }
}
