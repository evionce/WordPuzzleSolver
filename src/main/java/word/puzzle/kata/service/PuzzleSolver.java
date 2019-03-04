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
            for(int element = 0; element < puzzle[line].length; ++element){
                if(!checkIfEquals(line, element).equals("")){
                    return checkIfEquals(line, element);
                }
            }
        }
        return "";
    }

    private String checkIfEquals(int line, int element) {
        String potentialMatchForward = "";
        String potentialMatchBackwards = "";
        for(int letters = 0; letters < wordsToFind.get(0).length(); ++letters){
            if(matchIsWithinBoundsForward(line,element)){
                int targetForward = letters + element;
                potentialMatchForward += (puzzle[line][targetForward]);
            }if(matchIsWithinBoundsBackwards(element)){
                int targetBack = element - letters;
                potentialMatchBackwards += (puzzle[line][targetBack]);
            }
        }
        if(potentialMatchForward.equals(wordsToFind.get(0)) || potentialMatchBackwards.equals(wordsToFind.get(0))) {
            return wordsToFind.get(0);
        }
        return "";
    }

    public boolean matchIsWithinBoundsForward(int line, int element) {
        int wordLength = wordsToFind.get(0).length() -1;
        int lengthOfPuzzle = puzzle[line].length;
        if ((element + wordLength) < lengthOfPuzzle) {
            return true;
        } else {
            return false;
        }
    }

    public boolean matchIsWithinBoundsBackwards(int element){
        int wordLength = wordsToFind.get(0).length() -1;
        int potentialIndex = element - wordLength;
        if (0 <= potentialIndex) {
            return true;
        } else {
            return false;
        }
    }
}