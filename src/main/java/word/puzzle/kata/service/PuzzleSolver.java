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
                if(matchIsWithinBoundsForward(line,element) && !checkIfEquals(line, element).equals("")){
                    return checkIfEquals(line, element);
                }else if(!isFirstElement && wordsToFind.get(0).equals(puzzle[line][element] +puzzle[line][element-1])){
                    return wordsToFind.get(0);
                }
                isFirstElement = false;
            }
        }
        return "";
    }

    private String checkIfEquals(int line, int element) {
        String potentialMatch = "";
        for(int letters = 0; letters < wordsToFind.get(0).length(); ++letters){
            potentialMatch += (puzzle[line][letters + element]);
        }
        if(potentialMatch.equals(wordsToFind.get(0))) {
            return wordsToFind.get(0);
        }
        return "";
    }

    public boolean matchIsWithinBoundsForward(int line, int element) {
        if ((element + wordsToFind.get(0).length()) > (puzzle[line][element].length())) {
            return true;
        } else {
            return false;
        }
    }
}