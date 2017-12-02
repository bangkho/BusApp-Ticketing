/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;



/**
 *
 * @author BangKho
 */
public class Ticket extends RecursiveTreeObject<Ticket>{
    private final StringProperty nama;
    private final StringProperty namabus;
    private final StringProperty trayek;
    private final StringProperty tarif;
    private final StringProperty tgl;
    private final StringProperty jam;

    public Ticket(String nama, String namabus, String trayek, String tarif, String tgl, String jam) {
        this.nama = new SimpleStringProperty(nama);
        this.namabus = new SimpleStringProperty(namabus);
        this.trayek = new SimpleStringProperty(trayek);
        this.tarif = new SimpleStringProperty(tarif);
        this.tgl = new SimpleStringProperty(tgl);
        this.jam = new SimpleStringProperty(jam);
    }
    
    public String getName() {
        return nama.get();
    }
    public String getNamabus() {
        return namabus.get();
    }
    public String getTrayek() {
        return trayek.get();
    }
    public String getTarif() {
        return tarif.get();
    }
    public String getTgl() {
        return tgl.get();
    }
    public String getJam() {
        return jam.get();
    }
    
    public void setName(String value) {
        nama.set(value);
    }
    public void setNamabus(String value) {
        namabus.set(value);
    }
    public void setTrayek(String value) {
        trayek.set(value);
    }
    public void setTarif(String value) {
        tarif.set(value);
    }
    public void setTgl(String value) {
        tgl.set(value);
    }
    public void setJam(String value) {
        jam.set(value);
    }
    
     public StringProperty namaProperty() {
        return nama;
    }
     public StringProperty namabusProperty() {
        return namabus;
    }
     public StringProperty trayekProperty() {
        return trayek;
    }
     public StringProperty tarifProperty() {
        return tarif;
    }
     public StringProperty tglProperty() {
        return tgl;
    }
     public StringProperty jamProperty() {
        return jam;
    }
}
