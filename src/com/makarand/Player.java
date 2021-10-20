package com.makarand;

public class Player {
    public enum Piece {RED, YELLOW, EMPTY}
    public final String playerName;
    public Piece piece;
    public Player(String playerName, Piece piece) {
        this.playerName = playerName;
        this.piece = piece;
    }

    public String getPieceInitial(Piece piece) {
        if(piece == Piece.RED) return "r";
        return "y";
    }
}
