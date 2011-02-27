package mitalgo.nanolisp;

import java.math.BigDecimal;
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
	
	public BigDecimal toDecimal() {
		if (number != null) { 
			if (isFloat(number))
				return new BigDecimal(number.doubleValue());
			else if (isDecimal(number)) {
				return (BigDecimal)number;
			}
			else if (isInteger(number)){
				return new BigDecimal(number.longValue());
			}
		}
		return null;
	}
	
	public String strValue() {
		return strValue;
	}
	
	public boolean isDecimal() {
		return number != null && isDecimal(number);
	}
	
	public boolean isFloat() {
		return number != null && isFloat(number);
	}
	
	public boolean isInteger() {
		return number != null && isInteger(number);
	}
	
	public static boolean isDecimal(Number num) {
		return num instanceof BigDecimal;
	}
	
	public static boolean isFloat(Number num) {
		return num instanceof Float || num instanceof Double; 
	}
	
	public static boolean isInteger(Number num) {
		return num instanceof Integer || num instanceof Long || num instanceof Short; 
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
