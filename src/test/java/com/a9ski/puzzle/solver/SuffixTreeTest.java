package com.a9ski.puzzle.solver;

import static org.junit.Assert.*;

import org.junit.Test;

public class SuffixTreeTest {

	@Test
	public void testContainsWord() {
		SuffixTree t = new SuffixTree();
		t.addWord("alabala");
		t.addWord("ala");
		t.addWord("alibaba");
		assertTrue(t.containsWord("ala"));
		assertTrue(t.containsWord("alabala"));
		assertTrue(t.containsWord("alibaba"));
		assertFalse(t.containsWord("xxx"));
		assertTrue(t.containsChar('a'));
		assertFalse(t.containsChar('b'));
		
		SuffixTree subT = t.getSubTree('a').getSubTree('l').getSubTree('a'); 
		assertTrue(subT.containsTerminal());
		assertTrue(subT.containsChar('b'));		
	}

}
