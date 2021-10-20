package com.makarand;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// Referee is the class which manages the game. Decides who gets to play
// Also manages the turns and the winner.
public class Referee {
    private Player currentPlayer;
    private final Board board;
    private final Scanner sc;
    private Player player1, player2;
    private int maxRound;

    public Referee(Board board) {
        this.board = board;
        // total number of turns which both players can play.
        this.maxRound = board.boardMatrix.length * board.boardMatrix[0].length;
        sc = new Scanner(System.in);
    }

    public void startGame() {
        decidePlayers();
        this.board.printGame();

        while(maxRound > 0) {
            int colNum = input(currentPlayer.playerName + ", what column do you want to put your piece? ");
            if(!board.insertPiece(colNum, this.currentPlayer)) {
                continue;
            }
            this.board.printGame();
            Player winner = checkWinner();
            if(winner != null) {
                celebrate();
                break;
            }
            maxRound--;
            switchPlayer();
        }
        board.resetBoard();
        if(shouldReset()) {
            startGame();
        }
    }

    private boolean shouldReset() {
        println("\n\nDo you want to play one more round? (y/n): ");
        String input = sc.nextLine();
        if(input.equals("y")) {
            return true;
        } else if(input.equals("n")) {
            println("Stopping game. Bye!");
            return false;
        }
        println("Invalid input.Enter 'y' for yes or 'n' for no.");
        return shouldReset();
    }
    private void celebrate() {
        println(this.currentPlayer.playerName + " wins !!!!");
    }

    private Player checkWinner() {
        CellNode startNode = board.recentCellNode;
        CellNode currentCell = startNode;

        for(int i = 0; i < startNode.getNeighbours().size(); i++) {
            // As the neighbours are added in clock-wise manner
            // 'i' will determine the direction.
            // if i == 0 then the direction will be 'right'
            // if i == 1 then direction == 'bottom-right'
            // and so on...
            Set<Player.Piece> set = new HashSet<>();
            int count = 1; // this is how far we'll have to check.
            while(currentCell.getNeighbourAt(i) != null && currentCell.getNeighbourAt(i).piece == startNode.piece && count <= board.options.p) {
                set.add(currentCell.piece);
                currentCell = currentCell.getNeighbourAt(i);
                count++;
            }
            // set will have exactly 1 element if all the neighbours in certain
            // direction are same.
            if(set.size() == 1 && count == board.options.p) {
                return this.currentPlayer;
            }
        }
        return null;
    }


    private void switchPlayer() {
        if(currentPlayer.playerName.equals(player1.playerName)) {
            currentPlayer = player2;
            return;
        }
        currentPlayer = player1;
    }



    private String decidePlayers() {
        println("\nPlayer 1, do you want red or yellow (r or y)?: ");
        String playerOne = sc.nextLine();
        if(!playerOne.equals("r") && !playerOne.equals("y") ) {
            print("Please enter either 'r' for Red or 'y' for Yellow pieces");
            playerOne = decidePlayers();
        }

        if(playerOne.equals("r")) {
            player1 = new Player("Player 1", Player.Piece.RED);
            player2 = new Player("Player 2", Player.Piece.YELLOW);
        } else {
            player1 = new Player("Player 1", Player.Piece.YELLOW);
            player2 = new Player("Player 2", Player.Piece.RED);
        }

        this.currentPlayer = player1;
        return playerOne;
    }

    private int input(String message) {
        print(message);
        String numString = sc.nextLine();
        // just to make sure the input always is a positive non-zero number.
        // if not then calling the function again.
        try {
            int num = Integer.parseInt(numString);
            // because columns are using 1-based indexing.
            if(num <= 0) {
                println("The input should be a positive non-zero number");
                return input(message);
            } else if(num > board.boardMatrix[0].length) {
                println("The input should be within bounds of the board.");
                return input(message);
            }
            return num;
        } catch (Exception e) {
            println("The input should be a positive non-zero number.");
            return input(message);
        }
    }

    private void print(Object message) {
        System.out.print(message);
    }

    private void println(Object message) {
        System.out.println(message);
    }
}
