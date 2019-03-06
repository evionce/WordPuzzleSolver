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
                if(!checkIfEquals(line, element,word).equals("")){
                    return checkIfEquals(line, element,word);
                }
            }
        }
        return "";
    }

    private String checkIfEquals(int line, int element, String word) {
        StringBuilder potentialMatchBackwards = new StringBuilder();
        StringBuilder potentialMatchForward = new StringBuilder();
        StringBuilder potentialMatchDownwards = new StringBuilder();
        for(int letters = 0; letters < word.length(); ++letters){
            if(matchIsWithinBoundsForward(line,element,word)){
                int targetForward = letters + element;
                potentialMatchForward.append(puzzle[line][targetForward]);
            }
            if(matchIsWithinBoundsBackwards(element,word)){
                int targetBack = element - letters;
                potentialMatchBackwards.append(puzzle[line][targetBack]);
            }
            if(matchIsWithinBoundsDownwards(line,word)){
                int targetDownward = line + letters;
                potentialMatchDownwards.append(puzzle[targetDownward][element]);
            }
        }
        if(potentialMatchForward.toString().equals(word)
                || potentialMatchBackwards.toString().equals(word)){
            return buildAnswerStatement(element,line,word);
        }
        if(potentialMatchDownwards.toString().equals(word)){
            return buildVerticalAnswerStatement(element,line,word);
        }
        return "";
    }

    private String buildVerticalAnswerStatement(int element, int line, String word) {
        StringBuilder builder = new StringBuilder();
        builder.append(word).append(": ");
        for (int charInWord = 1; charInWord <= word.length(); ++charInWord) {
            builder.append("(").append(line + (charInWord - 1)).append(",").append(element).append(")");
            if (charInWord != word.length()) builder.append(",");
        }
        return builder.toString();
    }

    private String buildAnswerStatement(int element, int line, String word) {
        StringBuilder builder = new StringBuilder();
        builder.append(word).append(": ");
        int elementZero = 0;
        for(int charInWord = 1; charInWord <= word.length(); ++charInWord){
            builder.append("(");
            builder.append(line);
            builder.append(",");
            if(element ==1){
                builder.append(charInWord -1 +elementZero);
            }else{
                builder.append(charInWord -1 +element);
            }
            builder.append(")");
            if(charInWord != word.length()) builder.append(",");
        }
        return builder.toString();
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
        if (0 <= potentialIndex) {
            return true;
        } else {
            return false;
        }
    }


    private boolean matchIsWithinBoundsDownwards(int line, String word) {
        if(word.length() > puzzle.length + line){
            return false;
        }
        return true;
    }
}