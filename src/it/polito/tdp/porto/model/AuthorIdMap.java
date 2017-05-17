package it.polito.tdp.porto.model;

import java.util.*;

public class AuthorIdMap {

	private Map<Integer, Author> authorIdMap;

	public AuthorIdMap() {
		super();
		authorIdMap = new TreeMap<Integer, Author>() ; 
	} 
	
	public Author get(Integer idCode){
		return authorIdMap.get(idCode) ;
	}
	
	public Author put(Author a){
		Author old = authorIdMap.get(a.getId());
		if(old == null){
			authorIdMap.put(a.getId(), a) ;
			return a ;
		}
		else{
			return old ;
		}
	}
	
	public Map<Integer, Author> returnMap (){
		return authorIdMap;
	}
	
}
