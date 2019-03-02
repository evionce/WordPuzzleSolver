package word.puzzle.kata.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PuzzleParser {

    @Value("${puzzle.path}") private String puzzlePath;
    @Value("${puzzle.file.name}") private String puzzleFileName;

    List<String> wordsToFind = new ArrayList<>();
    public int puzzleBodyLineCount;

    public File readPuzzleFile() throws IOException {
        File puzzle = new File(puzzlePath + puzzleFileName);
        BufferedReader puzzleReader = new BufferedReader(new FileReader(puzzle));
        for (String wordToFind : puzzleReader.readLine().split(",")) {
            wordsToFind.add(wordToFind);
        }
        puzzleReader.close();
        return puzzle;
    }

    public String[] parseWordPuzzleLine(String puzzleBodyLine) {
        return puzzleBodyLine.split(",");
    }

    public String[][] build2DPuzzleArray(int rowInPuzzle,String[] lineElements) {
        String[][] puzzleLineWithColumnIndex = new String[rowInPuzzle+1][lineElements.length];
        for(int x = 0; x <lineElements.length; ++x){
            puzzleLineWithColumnIndex[rowInPuzzle][x] = lineElements[x];
        }
        return puzzleLineWithColumnIndex;
    }
}
