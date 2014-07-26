package com.a9ski.puzzle.solver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Locale;

public class Main {

	
	
	private static final Locale bgLocale = new Locale("bg_BG");
	

	public static void main(String[] args) throws Exception {
		SolverFactory f = new SolverFactory(Main.class.getClassLoader().getResourceAsStream("wordlist.txt"));
		System.out.println("Enter dashboard as flat lines");
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ( (line = r.readLine()) != null && !line.trim().isEmpty()) {
			line = line.trim().toLowerCase(bgLocale);			
			final int n = (int)Math.sqrt(line.length());
			final Character[][] table = new Character[n][n];
			int j = 0;
			for(int y = 0; y < n; y++) {
				for (int x = 0; x < n; x++) {
					table[x][y] = line.charAt(j);
					j++;
				}
			}
			Solver s = f.createSolver(table);
			s.printTable(System.out);
			System.out.println( s.solve() );
		}
		
	}

}
