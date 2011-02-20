package mitalgo.nanolisp;

import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private String value;
	
	private List<Node> list;

	public Node() {
		super();
	}

	public Node(String value) {
		super();
		this.value = value;
	}

	boolean hasList() {
		return list != null;
	}
	
	List<Node> getList() {
		return list;
	}

	public void add(Node node) {
		if (list == null)
			list = new ArrayList<Node>();
		list.add(node);
	}
	
	public String value() {
		return value;
	}
	
	public String toString() {
		if (list != null) {
			StringBuilder str = new StringBuilder();
			str.append("(");
			for(Node node : list) {
				str.append(node);
				str.append(" ");
			}
			str.append(")");
			return str.toString();
		}
		return value;
	}
}
