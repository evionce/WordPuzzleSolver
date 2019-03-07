package word.puzzle.kata.service;

import lombok.Getter;

import java.util.List;

public class PuzzleSolver {

    @Getter private String[][] puzzle;
    @Getter List<String> wordsToFind;

    public PuzzleSolver (String[][] puzzle, List<String> wordsToFind){
        this.puzzle = puzzle;
        this.wordsToFind = wordsToFind;
    }

    private int lineSearching =0;
    private int elementSearching =0;
    StringBuilder[] potentialMatches;


    public String solvePuzzle(){
        String results = "";
        for(String word: wordsToFind){
            results += (solvePuzzleAgainstWord(word)+ "\n");
        }
        return results.substring(0, results.lastIndexOf('\n'));
    }

    public String solvePuzzleAgainstWord(String word) {
        for(int line = 0; line < puzzle.length; ++line){
            lineSearching = line;
            for(int element = 0; element < puzzle[line].length; ++element){
                elementSearching = element;
                String result = checkAllPossibilitiesForPosition(word);
                if(!result.equals("")){
                    return result;
                }
            }
        }
        return "";
    }

    private String checkAllPossibilitiesForPosition(String word) {
        potentialMatches = initializedIndexesForAllPossibleMatches();
        for(int letters = 0; letters < word.length(); ++letters){
            boolean canBeForward = matchIsWithinBoundsForward(word);
            boolean canBeBackwards = matchIsWithinBoundsBackwards(word);
            boolean canBeDownwards = matchIsWithinBoundsDownwards(word);
            boolean canBeUpwards = matchIsWithinBoundsUpwards(word);
            if(canBeForward){
                potentialMatches[1].append("(").append(letters + elementSearching).append(",").append(lineSearching).append(")").append(",");
                potentialMatches[0].append(puzzle[lineSearching][letters + elementSearching]);
                if(canBeDownwards){
                    potentialMatches[3].append("(").append(letters + elementSearching).append(",").append(lineSearching + letters).append(")").append(",");
                    potentialMatches[2].append(puzzle[lineSearching + letters][letters + elementSearching]);
                }
                if(canBeUpwards){
                    potentialMatches[5].append("(").append(elementSearching + letters).append(",").append(lineSearching - letters).append(")").append(",");
                    potentialMatches[4].append(puzzle[lineSearching - letters][elementSearching + letters]);
                }
            }
            if(canBeBackwards){
                potentialMatches[7].append("(").append(elementSearching - letters).append(",").append(lineSearching).append(")").append(",");
                potentialMatches[6].append(puzzle[lineSearching][elementSearching - letters]);
                if(canBeUpwards){
                    potentialMatches[9].append("(").append(elementSearching - letters).append(",").append(lineSearching - letters).append(")").append(",");
                    potentialMatches[8].append(puzzle[lineSearching - letters][elementSearching - letters]);
                }
                if(canBeDownwards){
                    potentialMatches[11].append("(").append(elementSearching - letters).append(",").append(lineSearching + letters).append(")").append(",");
                    potentialMatches[10].append(puzzle[lineSearching + letters][elementSearching - letters]);
                }
            }
            if(canBeDownwards){
                potentialMatches[13].append("(").append(elementSearching).append(",").append(lineSearching + letters).append(")").append(",");
                potentialMatches[12].append(puzzle[lineSearching + letters][elementSearching]);
            }
            if(canBeUpwards){
                potentialMatches[15].append("(").append(elementSearching).append(",").append(lineSearching - letters).append(")").append(",");
                potentialMatches[14].append(puzzle[lineSearching - letters][elementSearching]);
            }
        }
        return checkPossibilities(word, potentialMatches);
    }

    private String checkPossibilities(String word, StringBuilder[] potentialMatches) {
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

    private boolean matchIsWithinBoundsUpwards(String word) {
        int wordLength = word.length() - 1;
        int potentialUp = lineSearching - wordLength;
        return isIndexNegative(potentialUp);
    }

    private boolean matchIsWithinBoundsForward(String word) {
        int wordLength = word.length() -1;
        int lengthOfPuzzle = puzzle[lineSearching].length;
        if ((elementSearching + wordLength) < lengthOfPuzzle) {
            return true;
        } else {
            return false;
        }
    }

    private boolean matchIsWithinBoundsBackwards(String word){
        int wordLength = word.length() -1;
        int potentialIndex = elementSearching - wordLength;
        return isIndexNegative(potentialIndex);
    }

    private boolean isIndexNegative(int potentialIndex) {
        if (0 <= potentialIndex) {
            return true;
        } else {
            return false;
        }
    }

    private boolean matchIsWithinBoundsDownwards(String word) {
        int wordLength = word.length();
        int potentialDown = lineSearching + wordLength;
        if(puzzle.length >= potentialDown){
            return true;
        }else {
            return false;
        }
    }
}