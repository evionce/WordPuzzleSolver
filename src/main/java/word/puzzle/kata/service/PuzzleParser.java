package word.puzzle.kata.service;

import org.springframework.beans.factory.annotation.Value;

import java.io.File;

public class PuzzleParser {

    @Value("${puzzle.path}") private String puzzlePath;
    @Value("${puzzle.file.name}") private String puzzleFileName;

    public File readPuzzleFile() {
        return new File(puzzlePath+puzzleFileName);
    }
}
