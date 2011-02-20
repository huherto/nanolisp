package mitalgo.nanolisp;

import java.util.ArrayList;
import java.util.List;

public class Node {
	
	private String strValue = null;
	
	private Number number = null;
	
	private List<Node> list = null;

	public Node() {
		super();
	}

	public Node(Number num) {
		number = num;
	}

	public Node(String value) {
		super();
		this.strValue = value;
	}
	
	boolean isNull() {
		return strValue == null && number == null && list == null;
	}

	boolean isNumber() {
		return number != null;
	}
	
	boolean isString() {
		return strValue != null;
	}
	
	boolean isList() {
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
	
	public Number number() {
		return number;
	}
	
	public String strValue() {
		return strValue;
	}
	
	public String toString() {
		if (isNumber()) {
			return number.toString();
		}
		if (isString()) {
			return strValue;
		}
		if (isList()) {
			StringBuilder str = new StringBuilder();
			str.append("(");
			int i = 0;
			for(Node node : list) {
				str.append(node);
				if (i < list.size() - 1)
					str.append(" ");
				i++;
			}
			str.append(")");
			return str.toString();
		}
		return "null";
	}

}
