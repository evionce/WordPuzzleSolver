package word.puzzle.kata.service;

import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/** This class will parse puzzle and dispatch load to be solved **/
public class PuzzleParser {

    /** Configurable for puzzle file path **/
    @Value("${puzzle.path}") private String puzzlePath;
    /** Configurable for puzzle file name @ configurable file path **/
    @Value("${puzzle.file.name}") private String puzzleFileName;

    /** Class to be constructed with sorted puzzle and puzzle answers **/
    PuzzleSolver solver;
    /** 2d array representing puzzle matrix **/
    String[][] puzzle;
    /** Words to find in the above **/
    List<String> wordsToFind = new ArrayList<>();

    /** After header (words to find) this is set to represent the size of puzzle square **/
    int puzzleSize = 1;

    /** this is to determine when to construct the PuzzleSolver with sorted results **/
    private boolean isFirstRead = true;

    /** This method will pickup a file, extract puzzle answers, and extract puzzle  **/
    public File readPuzzleFile() throws IOException {
        File puzzleProblemFile = new File(puzzlePath + puzzleFileName);
        BufferedReader reader = extractWordsToFind(puzzleProblemFile);
        extractPuzzleBody(reader);
        return puzzleProblemFile;
    }

    /** This method with will extract answers to find in puzzle matrix**/
    private BufferedReader extractWordsToFind(File puzzle) throws IOException {
        BufferedReader puzzleReader = new BufferedReader(new FileReader(puzzle));
        for (String wordToFind : puzzleReader.readLine().split(",")) {
            wordsToFind.add(wordToFind);
        }
        return puzzleReader;
    }

    /** This method will extract puzzle matrix**/
    private void extractPuzzleBody(BufferedReader reader) throws IOException {
        int puzzleBodyCounter = 0;
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            build2DPuzzleArray( puzzleBodyCounter, parsePuzzleLine(line));
            ++puzzleBodyCounter;
        }
    }

    /** This method will make a 2D array exposing the indices for each element in grid from file **/
    public PuzzleSolver build2DPuzzleArray(int rowInPuzzle, String[] lineElements) {
        for(int x = 0; x <lineElements.length; ++x){
            puzzle[rowInPuzzle][x] = lineElements[x];
        } if(rowInPuzzle == puzzle.length - 1){
            solver = new PuzzleSolver(puzzle,wordsToFind);
            return solver;
        } else{
            return null;
        }
    }

    /** This method will parse an array of Strings from a puzzle body line **/
    public String[] parsePuzzleLine(String puzzleBodyLine) {
        if(isFirstRead){
            return initializePuzzleResources(puzzleBodyLine);
        }else{
            ++ puzzleSize;
            return puzzleBodyLine.split(",");
        }
    }

    /** This method will make fields for puzzle to correct size (size of grid)**/
    private String[] initializePuzzleResources(String puzzleBodyLine) {
        String[] firstPuzzleLine = puzzleBodyLine.split(",");
        puzzleSize = firstPuzzleLine.length;
        puzzle = new String[puzzleSize][puzzleSize];
        isFirstRead = false;
        return firstPuzzleLine;
    }
}
