/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author BangKho
 */
public class Data {
    private String nama,ktp,alamat,ttl,jk;
    private String trayek,tgl,jam,namaBus,tarif;
    
    public String getNama(){
        return nama;
    }
    public String getKtp(){
        return ktp;
    }
    public String getAlamat(){
        return alamat;
    }
    public String getTtl(){
        return ttl;
    }
    public String getJk(){
        return jk;
    }
    public String getTrayek(){
        return trayek;
    }
    public String getTgl(){
        return tgl;
    }
    public String getJam(){
        return jam;
    }
    public String getTarif(){
        return tarif;
    }
    public String getNamabus(){
        return namaBus;
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
    public void setKtp(String ktp){
        this.ktp = ktp;
    }
    public void setAlamat(String alt){
        this.alamat = alt;
    }
    public void setTtl(String ttl){
        this.ttl = ttl;
    }
    public void setJk(String jk){
        this.jk = jk;
    }
    public void setTrayek(String tra){
        this.trayek = tra;
    }
    public void setTgl(String tgl){
        this.tgl = tgl;
    }
    public void setJam(String jam){
        this.jam = jam;
    }
    public void setTarif(String tarif){
        this.tarif = tarif;
    }
    public void setNamabus(String nb){
        this.namaBus = nb;
    }
}
