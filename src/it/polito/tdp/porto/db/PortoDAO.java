package it.polito.tdp.porto.db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorIdMap;
import it.polito.tdp.porto.model.Creator;
import it.polito.tdp.porto.model.Paper;
import it.polito.tdp.porto.model.PaperIdMap;

public class PortoDAO {

	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Map<Integer, Author> getAutori(AuthorIdMap autori) {

		final String sql = "SELECT * FROM author";
		

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				
				autori.put(autore);
			}
			
			conn.close();
			return autori.returnMap() ;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Map<Integer, Paper> getArticoli(PaperIdMap paperIdMap) {

		final String sql = "SELECT * FROM paper";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				
				paperIdMap.put(paper);
			}
			
			conn.close();
			return paperIdMap.returnMap();

			

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	
	public Map<Paper, Creator> getCreators(AuthorIdMap autori, PaperIdMap papers){
		
		final String sql = "SELECT * FROM creator";
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			Map<Paper, Creator> creators = new HashMap<>() ;

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Paper paper = papers.get(rs.getInt(1)) ;
				Author author = autori.get(rs.getInt(2));
				if(!creators.containsKey(paper)){
					Creator creator = new Creator(paper, author) ;
					creators.put(paper, creator) ;
				}
				else{
					creators.get(paper).add(author);
				}
			}
			
			conn.close();
			return creators;

			

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
}

