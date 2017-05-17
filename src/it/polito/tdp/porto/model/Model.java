package it.polito.tdp.porto.model;

import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {

	private AuthorIdMap autori;
	private PaperIdMap papers ;
	private PortoDAO dao ;
	private UndirectedGraph<Author, Edge> graph ;

	private List<Author> listAutori = new ArrayList <Author>() ;
	private List<Paper> listPapers = new ArrayList <Paper>() ;
	private Map<Integer, Author> mapAutori = new TreeMap <> () ;
	private Map<Integer, Paper> mapPaper = new TreeMap <> () ;
	private Map<Paper, Creator> creators = new TreeMap <>() ;
	
	public Model(){
		dao = new PortoDAO() ;
		autori = new AuthorIdMap() ;
		papers = new PaperIdMap() ;
	}
	
	public List<Author> createGraph(){
		
		
		mapAutori = dao.getAutori(autori) ;
		listAutori.addAll(mapAutori.values()) ;
		
		
		graph= new Multigraph<>(Edge.class) ;
		
		Graphs.addAllVertices(graph, listAutori) ;
		
		
		mapPaper = dao.getArticoli(papers) ;
		listPapers.addAll(mapPaper.values()) ;
		creators = dao.getCreators(autori, papers) ;
		
		for(Creator c : creators.values()){
			for(Author a : c.getAutori()){
				for(Author b :c.getAutori()){
					if(!a.equals(b)){
						Edge e = graph.addEdge(a, b);
						e.setPaper(c.getPaper());
					}
				}
			}
		}
		
		return listAutori ;
		
		
	}
	
	public String restituiscicoAutori(Author a){
	
		List<Author> coAutori = new ArrayList<>() ;
		
		for(Author au : Graphs.neighborListOf(graph, a)){
			if(!coAutori.contains(au)){
				coAutori.add(au);
			}
		}

		String s = "";
		
		for(Author au : coAutori){
			s+="- "+au.toString()+"\n";
		}
		
		return s;
	}
	
	public List<Author> restituisciINonCoautori(Author a){
		List<Author> nonCoautori = new ArrayList <>() ;
		nonCoautori.addAll(listAutori);
		nonCoautori.removeAll(Graphs.neighborListOf(graph, a));
		nonCoautori.remove(a);
		
		return nonCoautori;
	}
	
	public String restituisciArticoli(Author a, Author b){
		
		DijkstraShortestPath<Author, Edge> shortest = new DijkstraShortestPath(graph, a, b) ;

		String s = "";
		for(Edge e : shortest.getPathEdgeList()){
			s+="- "+e.toString()+"\n";
		}
		
		return s;
	}
}
