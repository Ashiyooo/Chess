//Code written by Joseph Victor Sumbong
//package
package com.chess.board;

/**
* an enum class which basically tells if a
* move can be done meaning that the move is a legal move
* and not an illegal move that a piece cannot do
* or a move that cant be allowed to occur since it exposes the
* king
 */
public enum MoveStatus{
    DONE{
        /**
         * Overrides the isDone() method and returns true to
         * show that the move has been made on the board
         * @return true
         */
        @Override
        public boolean isDone() {
            return true;
        }
    },

    LEAVES_PLAYER_IN_CHECK{
        /**
         * Overrides the isDone() method and returns false to
         * show that the move cannot be made since it leaves the playerKing in check
         * @return false
         */
        @Override
        public boolean isDone() {
            return false;
        }
    },
    ILLEGAL_MOVE{
        /**
         * Overrides the isDone() method and returns false to
         * show that the move cannot be made since it is not a calculatedLegalMove based on
         * the board state
         * @return false
         */
        @Override
        public boolean isDone() {
            return false;
        }
    };

    //abstract method
    public abstract boolean isDone();
}
