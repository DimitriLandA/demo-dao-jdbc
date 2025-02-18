/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;

import db.DB;
import db.DbException;
import java.util.List;
import model.dao.SellerDao;
import model.entities.Seller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import model.entities.Department;

public class SellerDaoJDBC implements SellerDao {
    
    private Connection conn;
    
    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }
    
    public SellerDaoJDBC() {
    }
    
    @Override
    public void insert(Seller obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Seller obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteById(Integer id) {
      
      
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
      ResultSet rs = null;
      
      try{
          st = conn.prepareStatement("SELECT seller.*, department.Name as DepName" +
                                     "FROM seller INNER JOIN department " +
                                     "ON seller.DepartmentId = department.Id "  +
                                     "WHERE seller.Id = ?");
          
          st.setInt(1, id);
          rs = st.executeQuery();
          
          if(rs.next()){
              Department dep = new Department();
              dep.setId(rs.getInt("Department"));
              dep.setName(rs.getString("DepName"));
              
              Seller obj = new Seller();
              obj.setId(rs.getInt("id"));
              obj.setName(rs.getString("Name"));
              obj.setEmail(rs.getString("Email"));
              obj.setBaseSalary(rs.getDouble("BaseSalary"));
              obj.setBirthDate(rs.getDate("BirthDate"));
              obj.setDepartment(dep);
              return obj;
          }
          return null;
      }catch(SQLException e){
          throw new DbException(e.getMessage());
      } finally{
          DB.closeStatement(st);
          DB.closeResultSet(rs);
      }
      
    }

    @Override
    public List<Seller> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
