package com.makarand;

import java.util.ArrayList;

public class Board {
    static Board board;
    ArgOptions.Options options;
    CellNode recentCellNode = null; // the node where last piece was inserted.
    CellNode[][] boardMatrix;
    // Singleton to create an instance of the board. Requires ArgOptions.Options.
    public static Board getInstance(ArgOptions.Options options) {
        if(board == null)
            board = new Board(options);
        return board;
    }

    private Board(ArgOptions.Options options) {
        this.options = options;
        initBoard();
    }

    // initializes the board in row*col matrix.
    private void initBoard() {
        this.boardMatrix = new CellNode[this.options.rowCount][this.options.colCount];
        // Iterating over the board matrix to fill it with CellNode.
        for(int i = 0; i < boardMatrix.length; i++) {
            for(int j = 0; j < boardMatrix[0].length; j++) {
                boardMatrix[i][j] = new CellNode(i, j);
            }
        }
        createBounds();
    }

    // Now that the boardMatrix has the CellNodes in it.
    // We still need to create a graph out of it.
    // each node is connected with nodes around it except for the node above.
    // Graph will help us quickly check if there's a winner or not.
    private void createBounds() {
        for(int i = 0; i < boardMatrix.length; i++) {
            for(int j = 0; j < boardMatrix[0].length; j++) {
                ArrayList<CellNode> neighbours = new ArrayList<>();
                CellNode bottom = getNodeAt(i+1, j);

                // neighbours added in clock-wise manner
                // this will help us in determining the winner.
                // we won't have to play the game of indices.
                neighbours.add(getNodeAt(i, j+1)); // right neighbour
                neighbours.add(getNodeAt(i+1, j+1)); // bottom right
                neighbours.add(bottom);
                neighbours.add(getNodeAt(i+1, j-1)); // bottom left
                neighbours.add(getNodeAt(i, j-1)); // left
                neighbours.add(getNodeAt(i - 1, j -1)); // top left
                neighbours.add(getNodeAt(i-1, j)); // top
                neighbours.add(getNodeAt(i-1, j+1)); // top right
                boardMatrix[i][j].setBottomNeighbour(bottom);
                boardMatrix[i][j].setNeighbours(neighbours);
            }
        }
    }

    private CellNode getNodeAt(int row, int col) {
        // check if the indices are within bounds of matrix.
        if(row >= 0 && row < this.options.rowCount && col >= 0 && col < this.options.colCount) {
            // if so then return the node at that index.
            return boardMatrix[row][col];
        }
        return null;
    }

    // inserts a piece into the given column
    // returns false if the column is already full.
    // return true if the piece insertion was successful
    public boolean insertPiece(int col, Player currentPlayer) {
        CellNode node = board.boardMatrix[0][col-1];
        if(node.getPiece() != Player.Piece.EMPTY) {
            println("The column is overflowed. Please choose another column");
            return false;
        }
        while (node.getBottomNeighbour() != null && node.getBottomNeighbour().piece == Player.Piece.EMPTY) {
            node = node.getBottomNeighbour();
        }
        node.setPiece(currentPlayer.piece);
        this.recentCellNode = node;
        return true;
    }

    private void print(Object message) {
        System.out.print(message);
    }

    private void println(Object message) {
        System.out.println(message);
    }

    // Just prints the game with some styling.
    public void printGame() {
        println("");
        for (CellNode[] matrix : this.boardMatrix) {
            for (int j = 0; j < this.boardMatrix[0].length; j++) {
                CellNode node = matrix[j];
                if (j == 0) {
                    print("|" + getDisplayString(node.getPiece()) + "|");
                } else {
                    print(getDisplayString(node.getPiece()) + "|");
                }
            }
            println("");
            for (int k = 0; k < board.boardMatrix[0].length; k++) {
                print("----");
            }
            print("-");
            println("");
        }
        println("");
    }

    public void resetBoard() {
        for(int i = 0; i < boardMatrix.length; i++) {
            for(int j = 0; j < boardMatrix[0].length; j++) {
                boardMatrix[i][j].reset();
            }
        }
    }


    private String getDisplayString(Player.Piece p) {
        switch (p) {
            case RED:
                return " r ";
            case YELLOW:
                return " y ";
            default:
                return "   ";
        }
    }
}
