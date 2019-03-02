package word.puzzle.kata.service;

import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PuzzleParser {

    @Value("${puzzle.path}") private String puzzlePath;
    @Value("${puzzle.file.name}") private String puzzleFileName;

    List<String> wordsToFind = new ArrayList<>();

    public File readPuzzleFile() throws IOException {
        File puzzle = new File(puzzlePath + puzzleFileName);
        BufferedReader puzzleReader = new BufferedReader(new FileReader(puzzle));
        for (String wordToFind : puzzleReader.readLine().split(",")) {
            wordsToFind.add(wordToFind);
        }
        puzzleReader.close();
        return puzzle;
    }

    public String[] parseWordPuzzleLine(String testString) {
        return testString.split(",");
    }
}
