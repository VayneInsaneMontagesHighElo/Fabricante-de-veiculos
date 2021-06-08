/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ulbra.model;

import java.sql.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Killer Queen
 */
public class VeiculoDao 
{
    Connection con;
    
    public VeiculoDao() throws SQLException
    {
        con = ConnFac.getConnection();
    }
    
    
    public void create(Veiculo v)
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = con.prepareStatement("INSERT INTO tbveiculo (marca,modelo,ano,cor,placa,motor,km,valor) VALUE (?,?,?,?,?,?,?,?)");
            
            stmt.setString(1, v.getMarca());
            stmt.setString(2, v.getModelo());
            stmt.setInt(3, v.getAno());
            stmt.setString(4, v.getCor());
            stmt.setString(5, v.getPlaca());
            stmt.setString(6, v.getMotor());
            stmt.setInt(7, v.getKm());
            stmt.setDouble(8, v.getValor());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Veiculo " + v.getModelo() + " de ID " + v.getId() + " salvo com sucesso");
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            
        }
        finally
        {
            ConnFac.closeConnection(con, stmt);
        }
    }
    
    
    public void update(Veiculo v)
    {
       PreparedStatement stmt = null;
        try
        {
            stmt = con.prepareStatement("UPDATE tbveiculo SET marca = ?, modelo = ?, ano = ?, cor = ?, placa = ?, motor = ?, km = ?, valor = ? WHERE id = ?");
            
            stmt.setString(1, v.getMarca());
            stmt.setString(2, v.getModelo());
            stmt.setInt(3, v.getAno());
            stmt.setString(4, v.getCor());
            stmt.setString(5, v.getPlaca());
            stmt.setString(6, v.getMotor());
            stmt.setInt(7, v.getKm());
            stmt.setDouble(8, v.getValor());
            stmt.setInt(9, v.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Veiculo " + v.getModelo() + " de ID " + v.getId() + " modificado com sucesso");
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            
        }
        finally
        {
            ConnFac.closeConnection(con, stmt);
        } 
    }
    
    
    public void delete(Veiculo v)
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = con.prepareStatement("DELETE FROM tbveiculo WHERE id = ?");
            
            stmt.setInt(1, v.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Veiculo " + v.getModelo() + " de ID " + v.getId() + " excluido com sucesso");
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
            
        }
        finally
        {
            ConnFac.closeConnection(con, stmt);
        }
    }
    
    
    public ArrayList<Veiculo> read()
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Veiculo> veiculos = new ArrayList<>();
        try
        {
            stmt = con.prepareStatement("SELECT * FROM tbveiculo ORDER BY id ASC");
            rs = stmt.executeQuery();
            while(rs.next())
            {
                Veiculo veiculo = new Veiculo();
                
                veiculo.setId(rs.getInt("id"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setMotor(rs.getString("motor"));
                veiculo.setKm(rs.getInt("km"));
                veiculo.setValor(rs.getDouble("valor"));
                
                veiculos.add(veiculo);
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        finally
        {
            ConnFac.closeConnection(con, stmt, rs);
        }
        return (ArrayList<Veiculo>) veiculos;
    
    }
    
    
    public ArrayList<Veiculo> readPesq(String modelo)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Veiculo> veiculos = new ArrayList<>();
        try
        {
            stmt = con.prepareStatement("SELECT * FROM tbveiculo WHERE modelo LIKE ?");
            
            stmt.setString(1, "%" + modelo + "%");
            
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Veiculo veiculo = new Veiculo();
                
                veiculo.setId(rs.getInt("id"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setAno(rs.getInt("ano"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setMotor(rs.getString("motor"));
                veiculo.setKm(rs.getInt("km"));
                veiculo.setValor(rs.getDouble("valor"));
                
                veiculos.add(veiculo);
            }
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        finally
        {
            ConnFac.closeConnection(con, stmt, rs);
        }
        
        return (ArrayList<Veiculo>) veiculos;
    
    }
}
