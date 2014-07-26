package com.a9ski.puzzle.solver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class SolverFactory {

	private final SuffixTree tree = new SuffixTree();
	
	public SolverFactory(File dictionary) throws IOException {
		final List<String> words = FileUtils.readLines(dictionary);		
		addWords(words);		
	}

	private void addWords(final List<String> words) {
		for(final String word : words) {
			if (word.length() > 2) {
				tree.addWord(word);
			}
		}
	}
	
	public SolverFactory(InputStream is) throws IOException {
		final List<String> words = IOUtils.readLines(is, "UTF-8");		
		addWords(words);		
	}
	
	public Solver createSolver(Character[][] table) { 
		return new Solver(tree, table);
	}
	

}
