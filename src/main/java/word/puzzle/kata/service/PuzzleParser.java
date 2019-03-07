package word.puzzle.kata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import word.puzzle.kata.KataAppRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PuzzleParser {

    private String puzzlePath = System.getProperty("user.dir") + "/";
    @Value("${puzzle.file.name}") private String puzzleFileName;

    @Autowired KataAppRunner appRunner;
    PuzzleSolver solver;
    String[][] puzzle;
    List<String> wordsToFind = new ArrayList<>();

    int puzzleSize = 1;
    private boolean isFirstRead = true;

    public File parseAndSolvePuzzle() throws IOException {
        File puzzleProblemFile = new File(puzzlePath + puzzleFileName);
        BufferedReader reader = extractWordsToFind(puzzleProblemFile);
        extractPuzzleBody(reader);
        appRunner.setResultFormatted(solver.solvePuzzle());
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
