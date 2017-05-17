package it.polito.tdp.porto.model;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge{
	
	private Paper paper ;

	public Edge() {
		super();
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	@Override
	public String toString() {
		return paper.toString();
	}
	
	
	
}
