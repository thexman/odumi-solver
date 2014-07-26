package com.a9ski.puzzle.solver;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Solver {

	private final static class LengthComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			int l1 = (o1 != null ? o1.length() : 0);
			int l2 = (o2 != null ? o2.length() : 0);
			return l2 - l1;
		}
		
	}
	
	private final static LengthComparator LEN_CMP = new LengthComparator();
	
	private final Character[][] table;
	private final boolean[][] used;

	private final static Set<Point> deltas = new HashSet<>(
			Arrays.asList(
					new Point(-1, -1),
					new Point(-1,  0),
					new Point(-1,  1),
					new Point( 0, -1),
					new Point( 0,  1),
					new Point( 1, -1),
					new Point( 1,  0),
					new Point( 1,  1)
	));
	
	
	private final Set<String> foundWords = new TreeSet<String>();
	private final SuffixTree tree; 
	
	
	public Solver(SuffixTree tree, Character[][] table) {		
		this.tree = tree;
		this.table = table;
		this.used = new boolean[table.length][table[0].length];
	}
	
	public void printTable(PrintStream w) {
		for(int y = 0; y < table[0].length; y++) {
			for(int x = 0; x < table.length; x++) {
				w.print(table[x][y]);
			}
			w.println();
		}
	}
	
	public List<String> solve() {
		foundWords.clear();
		for(int x = 0; x < table.length; x++) {
			for(int y = 0; y < table[0].length; y++) {
				solve(new Point(x,y), "", tree);
			}
		}
		final List<String> words = new ArrayList<>(foundWords);
		words.sort(LEN_CMP);		
		return words;
	}
	
	private boolean isValid(Point p) {
		return (p.x >= 0 && p.x < table.length) && (p.y >= 0 && p.y < table[0].length) && !used[p.x][p.y];
	}
	
	private void setUsed(Point p, boolean u) {
		used[p.x][p.y] = u;
	}
	
	private void solve(Point p, String word, SuffixTree t) {
		setUsed(p, true);
		if (t.containsTerminal()) {
			foundWords.add(word);
		}
		for(final Point delta : deltas) {
			Point newPoint = new Point(delta.x + p.x, delta.y + p.y);			
			if (isValid(newPoint)) {
				final Character ch = table[newPoint.x][newPoint.y];
				final SuffixTree subT = t.getSubTree(ch); 
				if (subT != null) {
					solve(newPoint, word + ch, subT);
				}
			}
		}
		setUsed(p, false);
	}	
}
