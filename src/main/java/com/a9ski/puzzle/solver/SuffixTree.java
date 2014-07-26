package com.a9ski.puzzle.solver;

import java.util.HashMap;
import java.util.Map;

public class SuffixTree {
	
	private static final Character TERMINAL = Character.valueOf('*');
		
	private final Map<Character, SuffixTree> subTrees = new HashMap<>(8); 
	
	public SuffixTree() {
		
	}
	
	public void addWord(String s) {
		if (s.isEmpty()) {
			subTrees.put(TERMINAL, null);
		} else {
			char ch = s.charAt(0);		
			SuffixTree subTree = subTrees.get(ch);
			if (subTree == null) {
				subTree = new SuffixTree();
				subTrees.put(ch, subTree);
			}
			subTree.addWord(s.substring(1));
		}
	}
	
	public boolean containsWord(String s) {
		boolean found = false;
		if (s.isEmpty()) {
			found = containsTerminal();
		} else {
			char ch = s.charAt(0);
			final SuffixTree subTree = getSubTree(ch);
			if (subTree != null) {
				found = subTree.containsWord(s.substring(1));
			} else {
				found = false;
			}
		}
		return found;
	}
	
	public boolean containsChar(Character ch) {
		return subTrees.keySet().contains(ch);
	}
	
	public SuffixTree getSubTree(Character ch) {
		return subTrees.get(ch);
	}
	
	public boolean containsTerminal() {
		return subTrees.containsKey(TERMINAL);				
	}

}
