//Code written by Joseph Victor Sumbong for CMSC 22
//package
package com.chess.board;

/**
 * MoveTransition class changes the board from one move to the next and if it is successful it will show in the GUI
 */
public class MoveTransition {
    //fields
    private final Board transitionBoard;
    private final Move move;
    private final MoveStatus moveStatus;

    //constructor
    public MoveTransition(final Board transitionBoard, final Move move, final MoveStatus moveStatus){
        this.transitionBoard = transitionBoard;
        this.move = move;
        this.moveStatus = moveStatus;
    }

    /**
     * getMoveStatus() method allows the caller to get the moveStatus
     * @return moveStatus
     */
    public MoveStatus getMoveStatus(){
        return this.moveStatus;
    }

    /**
     * getBoard() method allows the caller to get the transitionBoard
     * The transitionBoard is the resulting board if the move is made
     * @return transitionBoard which is a Board object
     */
    public Board getBoard() {
        return this.transitionBoard;
    }
}
