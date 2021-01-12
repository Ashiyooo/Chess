//Code written by Joseph Victor Sumbong for CMSC 22
//package
package com.chess.player.chessEngine;

//imports
import com.chess.board.Board;
import com.chess.board.Move;
import com.chess.board.MoveTransition;

/**
 * MiniMax is a class that implements the MiniMax algorithm
 * that is a good algorith for zero sum games and chess is one of those.
 * It can either look through the moves to find the maximum value or the minimum value it can gain using
 * the piece values from the piecetypes
 * This class implements MoveStrategy
 */
public class MiniMax implements MoveStrategy{

    //fields
    private final BoardEvaluator boardEvaluator;
    private final int searchDepth;

    //Constructor
    public MiniMax(final int searchDepth){
        this.boardEvaluator = new StandardBoardEvaluator();
        this.searchDepth = searchDepth;
    }

    /**
     * Overrides the execute method from MoveStrategy() to find the best move given the current state of the board
     * @param board is the board object needed by the algorithm to find the best move
     * @return bestMove which is a Move object
     */
    @Override
    public Move execute(Board board) {
        final long startTime = System.currentTimeMillis(); //to see later on how long it took for it to make the move

        Move bestMove = null;

        int highestSeenValue = Integer.MIN_VALUE;
        int lowestSeenValue = Integer.MAX_VALUE;
        int currentValue;

        System.out.println(board.currentPlayer() + " thinking with depth = " + this.searchDepth);

        for(final Move move : board.currentPlayer().getLegalMoves()){
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if(moveTransition.getMoveStatus().isDone()){
                currentValue = board.currentPlayer().getColor().isWhite()? min(moveTransition.getBoard(), this.searchDepth -1):
                                max(moveTransition.getBoard(), this.searchDepth -1);

                if(board.currentPlayer().getColor().isWhite() && currentValue >= highestSeenValue){
                    highestSeenValue = currentValue;
                    bestMove = move;
                }else if(board.currentPlayer().getColor().isBlack() && currentValue <= lowestSeenValue){
                    lowestSeenValue = currentValue;
                    bestMove = move;
                }
            }
        }

        final long executionTime = System.currentTimeMillis() - startTime;
        System.out.println(board.currentPlayer() + " took " + executionTime + "ms to execute the move"); //shows how long it took to make the move

        return bestMove;
    }

    /**
     * isEndGameScenario() is a boolean method to see if in the current board the player is in checkmate or stalemate
     * @param board current board
     * @return boolean true or false
     */
    private static boolean isEndGameScenario(final Board board){
        return board.currentPlayer().isInCheckMate() || board.currentPlayer().isInStaleMate();
    }

    /**
     * min() method is finding the minimum value that it can get
     * @param board current board
     * @param depth the depth that the minimax will go through to find the best move
     * @return lowestSeenValue which is an integer
     */
    public int min(final Board board, final int depth){

        if(depth == 0 || isEndGameScenario(board)){
            return this.boardEvaluator.evaluate(board, depth);
        }

        int lowestSeenValue = Integer.MAX_VALUE;
        for(final Move move : board.currentPlayer().getLegalMoves()){
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if(moveTransition.getMoveStatus().isDone()){
                final int currentValue = max(moveTransition.getBoard(), depth -1);
                if(currentValue <= lowestSeenValue){
                    lowestSeenValue = currentValue;
                }
            }
        }

        return lowestSeenValue;
    }

    /**
     * max() method is finding the maximum value that it can get
     * @param board current board
     * @param depth the depth that the minimax will go through to find the best move
     * @return highestSeenValue which is an integer
     */
    public int max(final Board board, final int depth){

        if(depth == 0 || isEndGameScenario(board)){
            return this.boardEvaluator.evaluate(board, depth);
        }

        int highestSeenValue = Integer.MIN_VALUE;
        for(final Move move : board.currentPlayer().getLegalMoves()){
            final MoveTransition moveTransition = board.currentPlayer().makeMove(move);
            if(moveTransition.getMoveStatus().isDone()){
                final int currentValue = min(moveTransition.getBoard(), depth -1);
                if(currentValue >= highestSeenValue){
                    highestSeenValue = currentValue;
                }
            }
        }
        return highestSeenValue;
    }

    /**
     * Override the toString() and
     * @return "MiniMax"
     */
    @Override
    public String toString(){
        return "MiniMax";
    }
}
