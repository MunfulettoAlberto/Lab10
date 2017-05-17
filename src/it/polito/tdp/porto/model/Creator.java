package it.polito.tdp.porto.model;

import java.util.*;

public class Creator {
	
	private Paper paper;
	private List<Author> autori = new ArrayList<>();
	
	
	public Creator(Paper paper, Author autor) {
		super();
		this.paper = paper;
		autori.add(autor);
	}
	
	public Paper getPaper() {
		return paper;
	}
	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public List<Author> getAutori() {
		return autori;
	}

	public void setAutori(List<Author> autori) {
		this.autori = autori;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paper == null) ? 0 : paper.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Creator other = (Creator) obj;
		if (paper == null) {
			if (other.paper != null)
				return false;
		} else if (!paper.equals(other.paper))
			return false;
		return true;
	}

	public void add(Author a){
		autori.add(a);
	}
	
	

	
	
}
