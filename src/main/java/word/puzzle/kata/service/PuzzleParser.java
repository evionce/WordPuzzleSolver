package word.puzzle.kata.service;

import java.io.File;

public class PuzzleParser {

    public File readPuzzleFile(String pathToFile) {
        return new File(pathToFile);
    }
}
