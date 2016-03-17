package com.github.angryjk.realmfx;

import com.jcabi.ssh.SSHByPassword;
import com.jcabi.ssh.Shell;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {
    @FXML
    Button Save;
    @FXML
    Button Connect;
    @FXML
    Button Execute;
    @FXML
    TextField Host;
    @FXML
    TextField Username;
    @FXML
    TextField Command;
    @FXML
    PasswordField Password;
    @FXML
    Label Consoleout;
    @FXML
    ListView ServerList;
    String SHost;
    String SUser;
    String SPassword;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
     public void SavePressed(ActionEvent event){
        
    }
    public void ConnectPressed(ActionEvent event){
        this.SHost = Host.getText();
        this.SUser = Username.getText();
        this.SPassword = Password.getText();
    }
    public void ExecutePressed(ActionEvent event) throws IOException,UnknownHostException{
    Shell shell = new SSHByPassword(SHost,22,SUser,SPassword);
    String output = new Shell.Plain(shell).exec(Command.getText());
        Consoleout.setText(output);
    }
}
