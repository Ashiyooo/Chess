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
 Knight class is a child class of the Piece class
 It deals with the construction and movement of the Knight piece
 on the board and other specific methods needed for the Knight piece
 The methods found here are: calculateLegalMove(), movePiece(), isColA(), isColH(),
 isColB(), isColG(), and toString()
 */
public class Knight extends Piece {
    //fields
    private final static int[] POSSIBLE_MOVE_TILE = {-17,-15,-10,-6,6,10,15,17};

    public Knight(final int position, final Color pieceColor) {
        super(PieceType.KNIGHT, position, pieceColor, true);
    }

    //Convenience constructor
    public Knight(final int position, final Color pieceColor, final boolean isFirstMove){
        super(PieceType.KNIGHT, position, pieceColor, isFirstMove);
    }

    /**
     * calculateLegalMove() is an override method from the parent class because the bishop has its own special movements
     * there is a for loop that passes through the constants int array called POSSIBLE_MOVE_TILE and the code below checks
     * if it can be done
     * @param board is the current state of the board in which it can calculate its legal moves
     * @return an immutable list of legalMoves
     */
    @Override
    public Collection<Move> calculateLegalMove(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for(final int possibleMove : POSSIBLE_MOVE_TILE){
            int possibleTile = this.position + possibleMove;
            if(BoardUtils.isValidTile(possibleTile)){
                if(isColA(this.position, possibleMove) || isColB(this.position, possibleMove)
                        || isColG(this.position, possibleMove) || isColH(this.position, possibleMove)){
                    continue;
                }
                final Tile moveTile = board.getTile(possibleTile);

                if(!moveTile.isTileOccupied()){
                    legalMoves.add(new NonCaptureMove(board, this, possibleTile));
                }
                else{
                    final Piece pieceAtTile = moveTile.getPiece();
                    final Color color = pieceAtTile.getColor();
                    if(this.pieceColor != color){
                        legalMoves.add(new MajorAttackMove(board, this, possibleTile, pieceAtTile));
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    /**
     * movePiece() creates a new knight for the new state of the board
     * @param move which is the move object
     * @return a new Knight
     */
    @Override
    public Knight movePiece(final Move move) {
        return new Knight(move.getMovePosition(), move.getMovedPiece().getColor());
    }

    /**
     * isColA() is a method that checks if the Knight or its movement is at the column A
     * so that we can create special restrictions according to chess rules
     * @param currentPosition is the current tile coordinate of the knight
     * @param offset is the current constant that it is in the POSSIBLE_MOVE_TILE
     * @return a boolean value based on if it is on column A or not
     */
    private static boolean isColA(final int currentPosition, final int offset){
        return BoardUtils.COL_A[currentPosition] && ((offset == -17) || (offset == -10) || (offset == 6) || (offset == 15));
    }

    /**
     * isColB() is a method that checks if the Knight or its movement is at the column B
     * so that we can create special restrictions according to chess rules
     * @param currentPosition is the current tile coordinate of the knight
     * @param offset is the current constant that it is in the POSSIBLE_MOVE_TILE
     * @return a boolean value based on if it is on column B or not
     */
    private static boolean isColB(final int currentPosition, final int offset){
        return BoardUtils.COL_B[currentPosition] && ((offset == -10) || (offset == 6) );
    }

    /**
     * isColG() is a method that checks if the Knight or its movement is at the column G
     * so that we can create special restrictions according to chess rules
     * @param currentPosition is the current tile coordinate of the knight
     * @param offset is the current constant that it is in the POSSIBLE_MOVE_TILE
     * @return a boolean value based on if it is on column G or not
     */
    private static boolean isColG(final int currentPosition, final int offset){
        return BoardUtils.COL_G[currentPosition] && ((offset == -6) || (offset == 10));
    }

    /**
     * isColH() is a method that checks if the Knight or its movement is at the column H
     * so that we can create special restrictions according to chess rules
     * @param currentPosition is the current tile coordinate of the knight
     * @param offset is the current constant that it is in the POSSIBLE_MOVE_TILE
     * @return a boolean value based on if it is on column H or not
     */
    private static boolean isColH(final int currentPosition, final int offset){
        return BoardUtils.COL_H[currentPosition] && ((offset == 17) || (offset == 10) || (offset == -6) || (offset == -15));
    }

    /**
     * This is for the moveLog to show for the GameHistoryPanel for the GUI
     * @return the toString() of the PieceType which is the knight in this case
     */
    @Override
    public String toString(){
        return PieceType.KNIGHT.toString();
    }
}
