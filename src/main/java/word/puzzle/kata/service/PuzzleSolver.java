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
            boolean isFirstElement = true;
            for(int element = 0; element < puzzle[line].length; ++element){
                if(wordsToFind.get(0).equals(puzzle[line][element] + puzzle[line][element+1])){
                    return wordsToFind.get(0);
                }else if(!isFirstElement && wordsToFind.get(0).equals(puzzle[line][element] +puzzle[line][element-1])){
                    return wordsToFind.get(0);
                }
                isFirstElement = false;
            }
        }
        return "";
    }
}