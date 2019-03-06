package word.puzzle.kata.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class PuzzleSolver {

    @Getter private String[][] puzzle;
    @Getter List<String> wordsToFind;

    public String solvePuzzle(){
        String results = "";
        for(String word: wordsToFind){
            results += (solvePuzzleAgainstWord(word)+ "\n");
        }
        return results.substring(0, results.lastIndexOf('\n'));
    }

    public String solvePuzzleAgainstWord(String word) {
        for(int line = 0; line < puzzle.length; ++line){
            for(int element = 0; element < puzzle[line].length; ++element){
                String result = checkIfEquals(line, element,word);
                if(!result.equals("")){
                    return result;
                }
            }
        }
        return "";
    }

    private String checkIfEquals(int line, int element, String word) {
        StringBuilder potentialMatchBackwards = new StringBuilder();
        StringBuilder potentialMatchBackwardsPositions = new StringBuilder();
        StringBuilder potentialMatchForward = new StringBuilder();
        StringBuilder potentialMatchForwardPositions = new StringBuilder();
        StringBuilder potentialMatchDownwards = new StringBuilder();
        StringBuilder potentialMatchDownwardsPositions = new StringBuilder();
        StringBuilder potentialMatchUpwards = new StringBuilder();
        StringBuilder potentialMatchUpwardsPositions = new StringBuilder();
        StringBuilder potentialMatchDownDiagonal = new StringBuilder();
        StringBuilder potentialMatchDownDiagonalPositions = new StringBuilder();
        for(int letters = 0; letters < word.length(); ++letters){
            if(matchIsWithinBoundsForward(line,element,word)){
                potentialMatchForwardPositions.append("(").append(letters + element).append(",").append(line).append(")").append(",");
                potentialMatchForward.append(puzzle[line][letters + element]);
            }
            if(matchIsWithinBoundsBackwards(element,word)){
                potentialMatchBackwardsPositions.append("(").append(element - letters).append(",").append(line).append(")").append(",");
                potentialMatchBackwards.append(puzzle[line][element - letters]);
            }
            if(matchIsWithinBoundsDownwards(line,word)){
                potentialMatchDownwardsPositions.append("(").append(element).append(",").append(line + letters).append(")").append(",");
                potentialMatchDownwards.append(puzzle[line + letters][element]);
            }
            if(matchIsWithinBoundsUpwards(line,word)){
                potentialMatchUpwardsPositions.append("(").append(element).append(",").append(line - letters).append(")").append(",");
                potentialMatchUpwards.append(puzzle[line - letters][element]);
            }
            if(matchIsWithinBoundsDownwards(line, word) && matchIsWithinBoundsForward(line, element,word )){
                potentialMatchDownDiagonalPositions.append("(").append(line + letters).append(",").append(letters + element).append(")").append(",");
                potentialMatchDownDiagonal.append(puzzle[line + letters][letters + element]);
            }
        }
        if(potentialMatchForward.toString().equals(word)){
            return formatOutput(potentialMatchForward, potentialMatchForwardPositions);
        }
        if(potentialMatchBackwards.toString().equals(word)){
            return formatOutput(potentialMatchBackwards, potentialMatchBackwardsPositions);
        }
        if(potentialMatchDownwards.toString().equals(word)){
            return formatOutput(potentialMatchDownwards, potentialMatchDownwardsPositions);
        }
        if(potentialMatchUpwards.toString().equals(word)){
            return formatOutput(potentialMatchUpwards, potentialMatchUpwardsPositions);
        }
        if(potentialMatchDownDiagonal.toString().equals(word)){
            return  formatOutput(potentialMatchDownDiagonal, potentialMatchDownDiagonalPositions);
        }
        return "";
    }

    private String formatOutput(StringBuilder potentialMatchForward, StringBuilder potentialMatchForwardPositions) {
        String match = potentialMatchForward.append(": ").append(potentialMatchForwardPositions).toString();
        return match.substring(0, match.length() - 1);
    }

    private boolean matchIsWithinBoundsUpwards(int line, String word) {
        int wordLength = word.length() - 1;
        int potentialUp = line - wordLength;
        return isIndexNegative(potentialUp);
    }

    private boolean matchIsWithinBoundsForward(int line, int element,String word) {
        int wordLength = word.length() -1;
        int lengthOfPuzzle = puzzle[line].length;
        if ((element + wordLength) < lengthOfPuzzle) {
            return true;
        } else {
            return false;
        }
    }

    private boolean matchIsWithinBoundsBackwards(int element,String word){
        int wordLength = word.length() -1;
        int potentialIndex = element - wordLength;
        return isIndexNegative(potentialIndex);
    }

    private boolean isIndexNegative(int potentialIndex) {
        if (0 <= potentialIndex) {
            return true;
        } else {
            return false;
        }
    }

    private boolean matchIsWithinBoundsDownwards(int line, String word) {
        int wordLength = word.length();
        int potentialUp = line + puzzle.length;
        if(wordLength >= potentialUp){
            return true;
        }else {
            return false;
        }
    }
}