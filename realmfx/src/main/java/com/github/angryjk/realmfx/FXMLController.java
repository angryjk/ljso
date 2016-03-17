package com.github.angryjk.realmfx;

import com.jcabi.ssh.SSHByPassword;
import com.jcabi.ssh.Shell;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private int count = 0;
    @FXML
    TableColumn<ServerProperties, Integer> tcId;
    @FXML
    TableColumn<ServerProperties, String> tcHost;
    @FXML
    TableColumn<ServerProperties, String> tcUser;
    @FXML
    TableColumn<ServerProperties, String> tcPassword;
    @FXML
    TableView<ServerProperties> table;
    final ObservableList<ServerProperties> data = FXCollections.observableArrayList();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ServerProperties server = new ServerProperties(count++,Host.getText(),Username.getText(),Password.getText());
        tcId.setCellValueFactory(new PropertyValueFactory<ServerProperties, Integer>("id"));
        tcHost.setCellValueFactory(new PropertyValueFactory<ServerProperties, String>("host"));
        tcUser.setCellValueFactory(new PropertyValueFactory<ServerProperties, String>("username"));
        tcPassword.setCellValueFactory(new PropertyValueFactory<ServerProperties, String>("password"));
        table.setItems(data);
    }
     public void SavePressed(ActionEvent event){
        ServerProperties props = new ServerProperties(count++,Host.getText(),Username.getText(),Password.getText());
        data.add(props);
    }
    public void ConnectPressed(ActionEvent event){
        
    }
    public void ExecutePressed(ActionEvent event) throws IOException,UnknownHostException{
    Shell shell = new SSHByPassword(Host.getText(),22,Username.getText(),Password.getText());
    String output = new Shell.Plain(shell).exec(Command.getText());
        Consoleout.setText(output);
    }
}
