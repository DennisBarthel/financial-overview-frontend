package de.netos.util;

public class Security {

	private static boolean l = false;
	
	public static boolean getL() {
		return l;
	}
	
	public static void setL() {
		Security.l = true;
	}
}
