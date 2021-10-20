package com.makarand;

import java.util.ArrayList;

public class CellNode {
//  A class which will contain information about the cells on the board.
    private CellNode bottomNeighbour; // bottomNeighbour is needed to check if there's empty cell below so that a player can insert their pieces.
    private ArrayList<CellNode> neighbours;
    public Player.Piece piece = Player.Piece.EMPTY;
    public int row, col;
    public CellNode(int row, int col) {
        this.neighbours = new ArrayList<>();
        this.row = row;
        this.col = col;
    }

    public CellNode getBottomNeighbour() {
        return bottomNeighbour;
    }

    public void setBottomNeighbour(CellNode bottomNeighbour) {
        this.bottomNeighbour = bottomNeighbour;
    }

    public ArrayList<CellNode> getNeighbours() {
        return neighbours;
    }

    public CellNode getNeighbourAt(int i) {
        return this.neighbours.get(i);
    }
    public void setNeighbours(ArrayList<CellNode> neighbours) {
        this.neighbours = neighbours;
    }

    public Player.Piece getPiece() {
        return piece;
    }

    public void setPiece(Player.Piece piece) {
        this.piece = piece;
    }

    // resetting cellNode.
    public void reset() {
        this.piece = Player.Piece.EMPTY;
        this.row = 0;
        this.col = 0;
    }
}
