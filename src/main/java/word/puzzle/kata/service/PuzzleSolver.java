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
        StringBuilder[] potentialMatches = initializedIndexesForAllPossibleMatches();
        for(int letters = 0; letters < word.length(); ++letters){
            boolean canBeForward = matchIsWithinBoundsForward(line,element,word);
            boolean canBeBackwards = matchIsWithinBoundsBackwards(element,word);
            boolean canBeDownwards = matchIsWithinBoundsDownwards(line,word);
            boolean canBeUpwards = matchIsWithinBoundsUpwards(line,word);
            if(canBeForward){
                potentialMatches[1].append("(").append(letters + element).append(",").append(line).append(")").append(",");
                potentialMatches[0].append(puzzle[line][letters + element]);
            }
            if(canBeBackwards){
                potentialMatches[3].append("(").append(element - letters).append(",").append(line).append(")").append(",");
                potentialMatches[2].append(puzzle[line][element - letters]);
            }
            if(canBeDownwards){
                potentialMatches[5].append("(").append(element).append(",").append(line + letters).append(")").append(",");
                potentialMatches[4].append(puzzle[line + letters][element]);
            }
            if(canBeUpwards){
                potentialMatches[7].append("(").append(element).append(",").append(line - letters).append(")").append(",");
                potentialMatches[6].append(puzzle[line - letters][element]);
            }
            if(canBeDownwards && canBeForward){
                potentialMatches[9].append("(").append(line + letters).append(",").append(letters + element).append(")").append(",");
                potentialMatches[8].append(puzzle[line + letters][letters + element]);
            }
            if(canBeUpwards && canBeBackwards){
                potentialMatches[11].append("(").append(line - letters).append(",").append(element - letters).append(")").append(",");
                potentialMatches[10].append(puzzle[line - letters][element - letters]);
            }
            if(canBeUpwards && canBeForward){
                potentialMatches[13].append("(").append(line - letters).append(",").append(element + letters).append(")").append(",");
                potentialMatches[12].append(puzzle[line - letters][element + letters]);
            }
            if(canBeDownwards && canBeBackwards){
                potentialMatches[15].append("(").append(line + letters).append(",").append(element - letters).append(")").append(",");
                potentialMatches[14].append(puzzle[line + letters][element - letters]);
            }
        }
        for(int possibilities = 0; possibilities < potentialMatches.length; ++possibilities){
            if(potentialMatches[possibilities].toString().equals(word)){
                return formatOutput(potentialMatches[possibilities], potentialMatches[possibilities + 1]);
            }
        }
        return "";
    }

    private StringBuilder[] initializedIndexesForAllPossibleMatches() {
        StringBuilder[] allPossible = new StringBuilder[16];
        for(int possibilities = 0; possibilities < 16; ++possibilities){
            allPossible[possibilities] = new StringBuilder();
        }
        return allPossible;
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