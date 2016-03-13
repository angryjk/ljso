package control;



import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Control implements Initializable  {
	@FXML
	public Button btn = new Button();
	@FXML
	public Button sed = new Button();
	@FXML
	TextField host;
	@FXML
	TextField comm;
	@FXML
	TextField user;
	@FXML
	TextField password;
	@FXML
	private Label lbl;
	@FXML
	TextArea cons;
	String shost = null;
	String suser = null;
	String spassword = null;
	private PrintStream print;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	JSch jsch=new JSch();
	Session sess = null;
	
	
	public Session pressed(ActionEvent event){
		lbl.setText("Clicked");
			try{
				shost = host.getText();
				suser = user.getText();
				spassword = password.getText();
				sess = connect(shost,suser,spassword,jsch);
				
			}catch(Exception e){System.out.println(e);};
		return sess;
		}
public Session connect(String host, String user,String password,Object jsch){
		Session session = null;
		try {
		session = ((JSch) jsch).getSession(user, host, 22);
		session.setPassword(spassword);
	      UserInfo ui = new MyUserInfo(){
	          public void showMessage(String message){
	            JOptionPane.showMessageDialog(null, message);
	          }
	          public boolean promptYesNo(String message){
	            Object[] options={ "yes", "no" };
	            int foo=JOptionPane.showOptionDialog(null, 
	                                                 message,
	                                                 "Warning", 
	                                                 JOptionPane.DEFAULT_OPTION, 
	                                                 JOptionPane.WARNING_MESSAGE,
	                                                 null, options, options[0]);
	            return foo==0;
	          }
	        };
	    session.setUserInfo(ui);
		session.connect(3000);
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return session;
}
public void sendbtn(ActionEvent event){
	try{
	Channel channel=sess.openChannel("shell");
	PipedInputStream pip = new PipedInputStream(40);
    channel.setInputStream(pip);
    PipedOutputStream pop = new PipedOutputStream(pip);
    print = new PrintStream(pop);
    OutputStream logOutput = new BufferedOutputStream(new FileOutputStream("1.txt"));
    channel.setOutputStream(new MultiplexOutputStream(logOutput, System.out), true);
    String send = comm.getText();
    print.println(send);
    channel.connect(3*1000);
	}catch(Exception e){};
	}
}


