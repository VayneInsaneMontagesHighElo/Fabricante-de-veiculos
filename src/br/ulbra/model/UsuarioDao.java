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
public class UsuarioDao 
{
    Connection con;
    
    public UsuarioDao() throws SQLException
    {
        con = ConnFac.getConnection();
    }
    
    
    public boolean checkLogin(String email, String senha) //nao precisa no veiculo
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        
        try
        {
            stmt = con.prepareStatement("SELECT * FROM tbusuario WHERE email = ? AND senha = ?");
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            rs = stmt.executeQuery();
            
            if(rs.next())
            {
                check = true;
            }
        }
        catch (SQLException e)
        {
             JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());       
        }
        
        return check;
    }
    
    
    public void create(Usuario u)
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = con.prepareStatement("INSERT INTO tbusuario (nome,email,senha,tipo) VALUE (?,?,?,?)");
            
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, u.getTipo());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario " + u.getNome() + " salvo com sucesso");
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
    
    
    public void update(Usuario u)
    {
       PreparedStatement stmt = null;
        try
        {
            stmt = con.prepareStatement("UPDATE tbusuario SET nome = ?, email = ?, senha = ?, tipo = ? WHERE id = ?");
            
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, u.getTipo());
            stmt.setInt(5, u.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario " + u.getNome() + " modificado com sucesso");
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
    
    
    public void delete(Usuario u)
    {
        PreparedStatement stmt = null;
        try
        {
            stmt = con.prepareStatement("DELETE FROM tbusuario WHERE id = ?");
            
            stmt.setInt(1, u.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Usuario " + u.getNome() + " excluido com sucesso");
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
    
    
    public ArrayList<Usuario> read()
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Usuario> usuarios = new ArrayList<>();
        try
        {
            stmt = con.prepareStatement("SELECT * FROM tbusuario ORDER BY id ASC");
            rs = stmt.executeQuery();
            while(rs.next())
            {
                Usuario usuario = new Usuario();
                
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
                
                usuarios.add(usuario);
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
        return (ArrayList<Usuario>) usuarios;
    
    }
    
    
    public ArrayList<Usuario> readPesq(String nome)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Usuario> usuarios = new ArrayList<>();
        try
        {
            stmt = con.prepareStatement("SELECT * FROM tbusuario WHERE nome LIKE ?");
            
            stmt.setString(1, "%" + nome + "%");
            
            rs = stmt.executeQuery();
            
            while(rs.next())
            {
                Usuario usuario = new Usuario();
                
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
                
                usuarios.add(usuario);
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
        
        return (ArrayList<Usuario>) usuarios;
    
    }
}

