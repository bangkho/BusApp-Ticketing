/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DAOBus;
import Model.Data;
import Model.Ticket;
import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author BangKho
 */
public class MainController implements Initializable {
    
    @FXML
    private JFXTreeTableView<Ticket> treeTable;
    
    @FXML
    private JFXDrawer drawer;
    
    @FXML
    private StackPane stack;

    @FXML
    private AnchorPane transDash;

    @FXML
    private AnchorPane scheduleDash;

    @FXML
    private AnchorPane ticketDash;

    @FXML
    private JFXTextField nama_lengkap;

    @FXML
    private JFXTextField ktp;

    @FXML
    private JFXTextField alamat;

    @FXML
    private Label labelDash1;

    @FXML
    private JFXDatePicker tgl_lhr;

    @FXML
    private JFXRadioButton laki;

    @FXML
    private ToggleGroup jk;

    @FXML
    private JFXRadioButton perem;

    @FXML
    private Label labelDash11;

    @FXML
    private JFXComboBox trayek;

    @FXML
    private Label account1;

    @FXML
    private JFXHamburger menuToggle;

    @FXML
    private JFXDatePicker tgl_naik;

    @FXML
    private JFXComboBox tarif;

    @FXML
    private Label labelDash111;

    @FXML
    private JFXButton pesan;

    @FXML
    private JFXComboBox nama_bus;

    @FXML
    private JFXComboBox jam_naik;

    @FXML
    private Label labelDash;

    @FXML
    private Label account;

    @FXML
    private Label datetime;
     private ObservableList<Ticket> data;
    @FXML
    private FontAwesomeIconView close;
    
    JFXTreeTableColumn<Ticket, String> Jam = new JFXTreeTableColumn<>("Jam");
    JFXTreeTableColumn<Ticket, String> Tarif = new JFXTreeTableColumn<>("Tarif");
    JFXTreeTableColumn<Ticket, String> Tgl = new JFXTreeTableColumn<>("Tgl");
    JFXTreeTableColumn<Ticket, String> Trayek = new JFXTreeTableColumn<>("Trayek");
    JFXTreeTableColumn<Ticket, String> Namabus = new JFXTreeTableColumn<>("Nama Bus");
    JFXTreeTableColumn<Ticket, String> Nama = new JFXTreeTableColumn<>("Nama");
     public String dialog;
    
    Connection con;
    Statement stat;
    ResultSet rs;
    String sql;
    Data d = new Data();
    DAOBus dao = new DAOBus();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd-MM-yyyy");
        Date date = new Date();
        datetime.setText(dateFormat.format(date));
        connect DB = new connect();
        con = DB.con;
        stat = DB.stm;
        
        try {
            URL viewURL = getClass().getClassLoader().getResource("View/SideNav.fxml");
            AnchorPane box = FXMLLoader.<AnchorPane>load(viewURL);
            drawer.setSidePane(box);
            String dialogAbout =
        "Bus App Ticketing"
        + "\nV 1.0 - 23 November 2017"
        + "\nAhmad Khoirudin - XIR5 - 03"
        + "\nSMK TELKOM MALANG";
            
            for (Node node : box.getChildren()){
                if (node.getAccessibleText() != null){
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->{
                        switch (node.getAccessibleText()){
                            case "Ticket" : ticketDash.setVisible(true);transDash.setVisible(false);
                                break;
                            case "History" : ticketDash.setVisible(false);transDash.setVisible(true);
                                break;
                            case "About" : DialogSign("Close","About This App",dialogAbout);
                                break;
                        }
                    });
                }
            }
        } catch (IOException ex) {
            }
        
        HamburgerBackArrowBasicTransition task1 = new HamburgerBackArrowBasicTransition(menuToggle);
        task1.setRate(-1);
        menuToggle.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
            task1.setRate(task1.getRate()*-1);
            task1.play();
            drawer.setDisable(true);
            if(drawer.isShown())
            {
                drawer.close();
            }else{
                drawer.open();
                drawer.setDisable(false);}
        });
        
        nama_bus.getItems().addAll("Rosalia Indah","Restu","Harapan Jaya","Bagong","Lorena");
        jam_naik.getItems().addAll("08.00","10.00","13.00","16.00","20.00");
        trayek.getItems().addAll("Malang - Surabaya","Jombang - Malang","Tulungagung - Surabaya","Solo - Surabaya","Blitar - Surabaya");
        tarif.getItems().addAll("30.000","20.000","35.000","100.000","30.000");
        
        nama_bus.setValue(null);
        jam_naik.setValue(null);
        trayek.setValue(null);
        tarif.setValue(null);
        tgl_naik.setValue(null);
        tgl_lhr.setValue(null);
        
    }    

    @FXML
    private void trans(ActionEvent event) throws SQLException {
        this.setColumn();
        this.setTable();
    }
    
    @FXML
    public void close(MouseEvent event){
        System.exit(0);
    }
    
    @FXML
    void report(ActionEvent event) throws JRException{
        /*JasperReport jasRep; 
        JasperPrint jasPri;
        JasperDesign jasDes;
        File report = new File("Report.jrxml");
        jasDes = JRXmlLoader.load(report);
        jasRep = JasperCompileManager.compileReport("Report.jrxml");
        jasPri = JasperFillManager.fillReport(jasRep, null, con);
        JasperViewer.viewReport(jasPri, false);*/
        DialogSign("Okay","Maaf untuk fitur Report sedang dalam proses pengembangan, Terima Kasih");   
    }
    
    @FXML
    public void pesan(ActionEvent event){
        String ttl = null,tgl = null;
        d.setNama(nama_lengkap.getText());
        d.setKtp(ktp.getText());
        d.setAlamat(alamat.getText());
        if(laki.isSelected()){
            d.setJk("L");
        } else if(perem.isSelected()){
            d.setJk("P");
        }
        d.setNamabus(nama_bus.getValue().toString());
        d.setTrayek(trayek.getValue().toString());
        d.setJam((jam_naik.getValue().toString()));
        d.setTarif((tarif.getValue().toString()));
        ttl = tgl_lhr.getValue().toString();
        d.setTtl(ttl);
        tgl = tgl_naik.getValue().toString();
        d.setTgl(tgl);
        dao.InsertDataP(d);
        dao.InsertDataT(d);
        String hasil;
        hasil = "\nNama : "+d.getNama()
                + "\nNo. KTP : "+d.getKtp()
                + "\nNama Bus : "+d.getNamabus()
                + "\nTrayek : "+d.getTrayek()
                + "\nTarif : "+d.getTarif()
                + "\nTgl Berangkat : "+d.getTgl()
                + "\nJam Berangkat : "+d.getJam();
        DialogSign("Okay","Selamat Anda Berhasil Pesan",hasil);
    }
    
    @FXML
    private void menuToggle(MouseEvent event) {
        
    }
    public void DialogSign(String b,String p){
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
    public void DialogSign(String b,String h,String p){
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
    
    public void setColumn(){
        Nama.setPrefWidth(125);
        Nama.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Ticket, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Ticket, String> param) {
                return param.getValue().getValue().namaProperty();
            }
        });
        Namabus.setPrefWidth(100);
        Namabus.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Ticket, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Ticket, String> param) {
                return param.getValue().getValue().namabusProperty();
            }
        });
        Trayek.setPrefWidth(100);
        Trayek.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Ticket, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Ticket, String> param) {
                return param.getValue().getValue().trayekProperty();
            }
        });
        Tarif.setPrefWidth(75);
        Tarif.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Ticket, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Ticket, String> param) {
                return param.getValue().getValue().tarifProperty();
            }
        });
        Tgl.setPrefWidth(75);
        Tgl.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Ticket, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Ticket, String> param) {
                return param.getValue().getValue().tglProperty();
            }
        });
        Jam.setPrefWidth(75);
        Jam.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Ticket, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Ticket, String> param) {
                return param.getValue().getValue().jamProperty();
            }
        });
    }
    
    public void setTable() throws SQLException{
        ObservableList<Ticket> ticket = FXCollections.observableArrayList();
        rs = dao.SelectData();
        while (rs.next()) {
        ticket.add(new Ticket(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
        }
        
        final TreeItem<Ticket> root = new RecursiveTreeItem<Ticket>(ticket, RecursiveTreeObject::getChildren);
        treeTable.setRoot(root);
        treeTable.setShowRoot(false);
        treeTable.setEditable(false);
        treeTable.getColumns().setAll(Nama,Namabus,Trayek,Tarif,Tgl,Jam);
    }
}