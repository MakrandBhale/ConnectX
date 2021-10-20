package com.makarand;

public class Main {
    public static void main(String[] args) {
        ArgOptions argOptions = new ArgOptions(args);
        ArgOptions.Options options = argOptions.getOptions();
        if(options == null) return;

        Board board = Board.getInstance(options);
        Referee referee = new Referee(board);

        referee.startGame();
    }
}
