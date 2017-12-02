/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.*;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.sql.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author BangKho
 */
public class BusLoginController implements Initializable {
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Label loginText;
    @FXML
    private JFXTextField userLogin;
    @FXML
    private JFXPasswordField passLogin;
    @FXML
    private JFXButton login;
    @FXML
    private StackPane stack;
    @FXML
    private FontAwesomeIconView close;
     @FXML
    private Label cp;
    @FXML
    
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        connect DB = new connect();
        con = DB.con;
        stat = DB.stm;
    }     

    @FXML
    private void login(ActionEvent event) {
        try {
            sql = "SELECT * FROM admin WHERE user_admin='"+userLogin.getText()+"' AND pass_admin='"+passLogin.getText()+"'";
            rs = stat.executeQuery(sql);
            if(rs.next()){
                if(userLogin.getText().equals(rs.getString("user_admin")) && 
                      passLogin.getText().equals(rs.getString("pass_admin"))){
                     this.DialogIn("Okay", "Login Success");
                     ((Node)(event.getSource())).getScene().getWindow().hide();
                     URL viewURL = getClass().getClassLoader().getResource("View/Main.fxml");
        AnchorPane root = FXMLLoader.<AnchorPane>load(viewURL);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
                    /*((Node)(event.getSource())).getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("Main.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = new Stage();*/
                    scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.setTitle("Main Bus App");
                    stage.show();
            }
        } else{
                 this.DialogIn("Okay", "Wrong Username and Password");
            }
        }catch (Exception e) {
            this.DialogIn("Okay", "Error opening main dashboard","Check your database server");
        }
    }
    
    public void DialogIn(String b,String p){
        stack.setDisable(false);
                JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text(p));
            JFXDialog dialog = new JFXDialog(stack,content,JFXDialog.DialogTransition.CENTER);
            JFXButton btn = new JFXButton(b); 
            btn.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                dialog.close();
                stack.setDisable(true);
            }
            });
            content.setActions(btn);
            dialog.show();
    }
    public void DialogIn(String b,String h,String p){
        stack.setDisable(false);
                JFXDialogLayout content = new JFXDialogLayout();
            content.setHeading(new Text(h));
            content.setBody(new Text(p));
            JFXDialog dialog = new JFXDialog(stack,content,JFXDialog.DialogTransition.CENTER);
            JFXButton btn = new JFXButton(b); 
            btn.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event){
                dialog.close();
                stack.setDisable(true);
            }
            });
            content.setActions(btn);
            dialog.show();
    }
    public void close(MouseEvent event){
        System.exit(0);
    }
    public void hoverIn(MouseEvent event){
        close.setFill(Color.color(1, 1, 1, 0.7));
    }
    public void hoverOut(MouseEvent event){
        close.setFill(Color.color(1, 1, 1, 1));
    }
    public void about(MouseEvent event){ //Remember to EDIT THIS KHOI
        this.DialogIn("Close","About This App",
        "Bus App Ticketing"
        + "\nV 1.0 - 23 November 2017"
        + "\nAhmad Khoirudin - XIR5 - 03"
        + "\nSMK TELKOM MALANG"
        );
    }
}
