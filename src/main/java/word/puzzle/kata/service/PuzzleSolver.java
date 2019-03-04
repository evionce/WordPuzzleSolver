package word.puzzle.kata.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class PuzzleSolver {

    @Getter private String[][] puzzle;
    @Getter List<String> wordsToFind;

    public String solvePuzzle() {
        for(int line = 0; line < puzzle.length; ++line){
            if(wordsToFind.get(0).startsWith(puzzle[0][line])){
                return wordsToFind.get(0);
            }
        }
        return "";
    }
}