/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controller.connect;
import java.sql.*;
import Model.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BangKho
 */
public class DAOBus {
    
    Connection con;
    Statement stat;
    ResultSet rs;
    PreparedStatement ps;
    
    public String SelectData = "SELECT nama_penumpang,nama_bus,trayek,tarif,tgl,jam FROM `tiket` INNER JOIN `penumpang` ON tiket.no_ktp = penumpang.no_ktp";
    public String InsertDataP = "INSERT INTO `penumpang`(nama_penumpang,alamat,tgl_lahir,no_ktp,jenis_kelamin) VALUES (?, ?, ?, ?, ?)";
    public String InsertDataT = "INSERT INTO `tiket`(nama_bus,no_ktp,trayek,jam,tgl,tarif) VALUES (?, ?, ?, ?, ?, ?)";
    public String DeleteDataP = "DELETE FROM `tiket` WHERE id_tiket=?";
    
    public DAOBus(){
        connect DB = new connect();
        con = DB.con;
        stat = DB.stm;
    }
    
        public void InsertDataP(Data a){
        try {
            ps = con.prepareStatement(InsertDataP);
            ps.setString(1, a.getNama());
            ps.setString(4, a.getKtp());
            ps.setString(2, a.getAlamat());
            ps.setString(3, a.getTtl());
            ps.setString(5, a.getJk());
            ps.executeUpdate();
        } catch (SQLException ex) {
                }
            }
        
        public void InsertDataT(Data a){
        try {
            ps = con.prepareStatement(InsertDataT);
            ps.setString(1, a.getNamabus());
            ps.setString(2, a.getKtp());
            ps.setString(3, a.getTrayek());
            ps.setString(4, a.getJam());
            ps.setString(5, a.getTgl());
            ps.setString(6, a.getTarif());
            ps.executeUpdate();
        } catch (SQLException ex) {
                }
            }
        public ResultSet SelectData() throws SQLException{
            rs = stat.executeQuery(SelectData);
            return rs;
        }
}