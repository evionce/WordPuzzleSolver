package word.puzzle.kata.service;

import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PuzzleParser {

    @Value("${puzzle.path}") private String puzzlePath;
    @Value("${puzzle.file.name}") private String puzzleFileName;

    List<String> wordsToFind = new ArrayList<>();
    int indexInGrid = 1;
    String[][] puzzle;
    int puzzleSize;
    PuzzleSolver solver;
    private boolean isFirstRead = true;

    public File readPuzzleFile() throws IOException {
        File puzzle = new File(puzzlePath + puzzleFileName);
        BufferedReader reader = extractWordsToFind(puzzle);
        int puzzleBodyCounter = 0;
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            build2DPuzzleArray( puzzleBodyCounter, parseWordPuzzleLine(line));
            ++puzzleBodyCounter;
        }
        return puzzle;
    }

    private BufferedReader extractWordsToFind(File puzzle) throws IOException {
        BufferedReader puzzleReader = new BufferedReader(new FileReader(puzzle));
        for (String wordToFind : puzzleReader.readLine().split(",")) {
            wordsToFind.add(wordToFind);
        }
        return puzzleReader;
    }

    public String[] parseWordPuzzleLine(String puzzleBodyLine) {
        if(isFirstRead){
            return initializePuzzleResources(puzzleBodyLine);
        }else{
            ++ indexInGrid;
            return puzzleBodyLine.split(",");
        }
    }

    private String[] initializePuzzleResources(String puzzleBodyLine) {
        String[] firstPuzzleLine = puzzleBodyLine.split(",");
        puzzleSize = firstPuzzleLine.length;
        puzzle = new String[puzzleSize][puzzleSize];
        isFirstRead = false;
        return firstPuzzleLine;
    }

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
}
