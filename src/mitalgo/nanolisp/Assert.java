package mitalgo.nanolisp;

public class Assert {
	
	public static void isTrue(boolean value)  {
		if (!value)
			throw new Error("Assertion failed");
	}

	public static void isTrue(boolean value, String msg)  {
		if (!value)
			throw new Error(msg);
	}
	
	public static void notNull(Object obj)  {
		if (obj == null)
			throw new Error("Assertion failed");
	}

	public static void notNull(Object obj, String msg)  {
		if (obj == null)
			throw new Error(msg);
	}
	
}
