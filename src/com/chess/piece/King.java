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
 King class is a child class of the Piece class
 It deals with the construction and the movement of the King piece on the board
 and other specific methods needed for the King piece
 The methods found here are: calculateLegalMove(), movePiece(), isColA(), isColH(),
 isCastled(), and toString()
 */
public class King extends Piece{
    //fields
    private final boolean isCastled;
    private final static int[] POSSIBLE_MOVE_TILE = {-9,-8,-7,-1,1,7,8,9};

    //constructor
    public King(final int position, final Color pieceColor, final boolean isCastled) {
        super(PieceType.KING, position, pieceColor,true);
        this.isCastled = false;
    }

    //Convenience constructor
    public King(final int position, final Color pieceColor, final boolean isFirstMove, final boolean isCastled){
        super(PieceType.KING, position, pieceColor, isFirstMove);
        this.isCastled = isCastled;
    }

    /**
     * calculateLegalMove() is an override method from the parent class because the bishop has its own special movements
     * there is a for loop that passes through the constants int array called POSSIBLE_MOVE_TILE and checks if the possibleMove
     * can be done
     * @param board is the current state of the board in which it can calculate its legal moves
     * @return an immutable list of legalMoves
     */
    @Override
    public Collection<Move> calculateLegalMove(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int possibleMove : POSSIBLE_MOVE_TILE) {
            final int possibleTile = this.position + possibleMove;
            if (BoardUtils.isValidTile(possibleTile)) {
                if (isColA(this.position, possibleMove) || isColH(this.position, possibleMove)) {
                    continue;
                }
                final Tile moveTile = board.getTile(possibleTile);

                if (!moveTile.isTileOccupied()) {
                    legalMoves.add(new NonCaptureMove(board, this, possibleTile));
                } else {
                    final Piece pieceAtTile = moveTile.getPiece();
                    final Color color = pieceAtTile.getColor();
                    if (this.pieceColor != color) {
                        legalMoves.add(new MajorAttackMove(board, this, possibleTile, pieceAtTile));
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    /**
     * movePiece() creates a new King for the new state of the board
     * @param move which is the move object
     * @return a new King
     */
    @Override
    public King movePiece(final Move move) {
        return new King(move.getMovePosition(), move.getMovedPiece().getColor(), move.isCastlingMove());
    }

    /**
     * isColA() is a method that checks if the King or its movement is at the column A
     * so that we can create special restrictions according to chess rules
     * @param currentPosition is the current tile coordinate of the king
     * @param offset is the current constant that it is in the POSSIBLE_MOVE_TILE
     * @return a boolean value based on if it is on column A or not
     */
    private static boolean isColA(final int currentPosition, final int offset){
        return BoardUtils.COL_A[currentPosition] &&  ((offset == -9) || (offset == -1) || (offset == 7));
    }

    /**
     * isColH() is a method that checks if the King or its movement is at the column H
     * so that we can create special restrictions according to chess rules
     * @param currentPosition is the current tile coordinate of the king
     * @param offset is the current constant that it is in the POSSIBLE_MOVE_TILE
     * @return a boolean value based on if it is on column H or not
     */
    private static boolean isColH(final int currentPosition, final int offset){
        return BoardUtils.COL_H[currentPosition] && ((offset == 9) || (offset == 1) || (offset == -7));
    }

    /**
     * isCaslted() is a boolean method that allows the caller to get the boolean value stored inside the
     * isCaslted variable
     * @return a boolean value stored in isCastled variable
     */
    public boolean isCastled(){
        return this.isCastled;
    }

    /**
     * This is for the moveLog to show for the GameHistoryPanel for the GUI
     * @return the toString() of the PieceType which is the king in this case
     */
    @Override
    public String toString(){
        return PieceType.KING.toString();
    }
}
