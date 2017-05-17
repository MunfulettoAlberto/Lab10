package it.polito.tdp.porto.model;

import java.util.*;

public class PaperIdMap {

	private Map<Integer, Paper> papers ;

	public PaperIdMap() {
		super();
		papers = new TreeMap<Integer, Paper>() ;
	}
	
	public Paper get(Integer idP){
		return papers.get(idP) ;
	}
	
	public Paper put(Paper p){
		Paper old = papers.get(p.getEprintid());
		if(old==null){
			papers.put(p.getEprintid(), p);
			return p ;
		}
		else{
			return old ;
		}
	}
	
	public Map<Integer, Paper> returnMap(){
		return papers ;
	}
	
}
