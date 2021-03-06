//Code written by Joseph Victor Sumbong for CMSC 22
//packages
package com.chess.piece;

//imports
import com.chess.Color;
import com.chess.board.*;
import com.chess.board.Move.MajorAttackMove;
import com.chess.board.Move.NonCaptureMove;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 Queen class is a child class of the Piece class
 It deals with the construction and movement of the Queen piece
 on the board and other specific methods needed for the Queen piece
 The methods found here are: calculateLegalMove(), movePiece(), isColA(), isColH(),
 and toString()
 */
public class Queen extends Piece{
    private final static int[] POSSIBLE_MOVE_TILE = {-1,1,-8,8,-9,-7,7,9};
    public Queen(final int position, final Color pieceColor) {
        super(PieceType.QUEEN, position, pieceColor,true);
    }

    //Convenience constructor
    public Queen(final int position, final Color pieceColor, final boolean isFirstMove){
        super(PieceType.QUEEN, position, pieceColor, isFirstMove);
    }

    /**
     * calculateLegalMove() is an override method from the parent class because the bishop has its own special movements
     * there is a for loop that passes through the constants int array called POSSIBLE_MOVE_TILE and each one of those
     * constants is brought to a while loop that keeps on adding the constant so that it can keep moving diagonally, horizontally, and
     * vertically until it eventually is stopped by the borders of the chess board or another piece
     * @param board is the current state of the board in which it can calculate its legal moves
     * @return an immutable list of legalMoves
     */
    @Override
    public Collection<Move> calculateLegalMove(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int possibleMove : POSSIBLE_MOVE_TILE) {
            int possibleTile = this.position;
            while (BoardUtils.isValidTile(possibleTile)) {
                if (isColA(possibleTile, possibleMove) ||
                        isColH(possibleTile, possibleMove)) {
                    break;
                }
                possibleTile += possibleMove;
                if (BoardUtils.isValidTile(possibleTile)) {
                    final Tile moveTile = board.getTile(possibleTile);
                    if (!moveTile.isTileOccupied()) {
                        legalMoves.add(new NonCaptureMove(board, this, possibleTile));
                    } else {
                        final Piece pieceAtTile = moveTile.getPiece();
                        final Color color = pieceAtTile.getColor();

                        if (this.pieceColor != color) {
                            legalMoves.add(new MajorAttackMove(board, this, possibleTile, pieceAtTile));
                        }
                        break;
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    /**
     * movePiece() creates a new Queen for the new state of the board
     * @param move which is the move object
     * @return a new Queen
     */
    @Override
    public Queen movePiece(final Move move) {
        return new Queen(move.getMovePosition(), move.getMovedPiece().getColor());
    }

    /**
     * isColA() is a method that checks if the Queen or its movement is at the column A
     * so that we can create special restrictions according to chess rules
     * @param currentPosition is the current tile coordinate of the queen
     * @param offset is the current constant that it is in the POSSIBLE_MOVE_TILE
     * @return a boolean value based on if it is on column A or not
     */
    private static boolean isColA(final int currentPosition, final int offset){
        return BoardUtils.COL_A[currentPosition] &&  ((offset == 7) || (offset == -1) || (offset == -9));
    }

    /**
     * isColH() is a method that checks if the Queen or its movement is at the column H
     * so that we can create special restrictions according to chess rules
     * @param currentPosition is the current tile coordinate of the queen
     * @param offset is the current constant that it is in the POSSIBLE_MOVE_TILE
     * @return a boolean value based on if it is on column H or not
     */
    private static boolean isColH(final int currentPosition, final int offset){
        return BoardUtils.COL_H[currentPosition] && ((offset == -7) || (offset == 1) || (offset == 9));
    }

    /**
     * This is for the moveLog to show for the GameHistoryPanel for the GUI
     * @return the toString() of the PieceType which is the queen in this case
     */
    @Override
    public String toString(){
        return PieceType.QUEEN.toString();
    }
}
