//Code written by Joseph Victor Sumbong for CMSC 22
//packages
package com.chess.piece;

//imports
import com.chess.Color;
import com.chess.board.Board;
import com.chess.board.Move;
import java.util.Collection;

/**
    Piece is a parent class for all the other piece types
    within it is also an enum for the piece types
    The methods found here are: computeHashCode(), equals(), getColor(), getPieceType(),
    isFirstMove(), getPiecePosition(), getPieceValue(), and abstract methods which are
    calculateLegalMove() and movePiece()
*/
public abstract class Piece {
    //fields
    protected final PieceType pieceType;
    protected final int position;
    protected final Color pieceColor;
    protected final boolean isFirstMove;
    private final int cachedHashCode;

    //constructor
    Piece(final PieceType pieceType, final int position, final Color pieceColor, final boolean isFirstMove){
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        this.position = position;
        this.isFirstMove = isFirstMove;
        this.cachedHashCode = computeHashCode();
    }

    /**
     * since the piece objects are immutable their hashcode will not change so we can call this method
     * once to compute and get the hashcode
     * @return integer value of result
     */

    private int computeHashCode() {
        int result = pieceType.hashCode();
        result = 31 * result + pieceColor.hashCode();
        result = 31 * result + position;
        result = 31 * result + (isFirstMove? 1: 0);
        return result;
    }

    /**
     * We want Object equality rather than reference equality which is the default for java.lang
     * so we override the equals() method to suit our needs
     * @param other an object to be compared with
     * @return a boolean based on the comparison of the objects if they are equal or not
     */
    @Override
    public boolean equals(final Object other){
        if(this == other){
            return true;
        }
        if(!(other instanceof Piece)){
            return false;
        }
        final Piece otherPiece = (Piece) other;
        return position == otherPiece.getPiecePosition() && pieceType == otherPiece.getPieceType()
                            && pieceColor == otherPiece.getColor() && isFirstMove == otherPiece.isFirstMove();
    }

    /**
     * hashCode() is a method that allows the caller to get the cachedHashCode
     * @return the integer value of cachedHashCode
     */
    @Override
    public int hashCode(){
        return this.cachedHashCode;
    }

    /**
     * getColor() is a method that allows the caller to get the pieceColor of the piece
     * @return the Color of the piece
     */
    public Color getColor(){
        return this.pieceColor;
    }

    /**
     * getPieceType() is a method that allows the caller to get the pieceType of the piece
     * @return the PieceType of the piece
     */
    public PieceType getPieceType(){
        return this.pieceType;
    }

    /**
     * getPiecePosition() is a method that allows the caller to get the position of the piece
     * @return the position of the piece
     */
    public int getPiecePosition(){
        return this.position;
    }

    /**
     * getPieceValue() is a method that allows the caller to get the value of the piece
     * @return the value of the piece
     */
    public int getPieceValue(){return this.pieceType.getPieceValue();}

    /**
     * isFirstMove() is a method that allows the caller to get the boolean value to see if the piece has already moved or not
     * @return the boolean value of isFirstMove
     */
    public boolean isFirstMove(){
        return this.isFirstMove;
    }


    //abstract methods
    public abstract Collection<Move> calculateLegalMove(final Board board);
    public abstract Piece movePiece(Move move);

    /**
        an enum class that specifies the piece type of each piece with values which
        will be needed by the minimax algorithm and other methods that will be needed
        in the program especially the Castling moves
    */
    public enum PieceType {
        PAWN("P",100){
            /**
             * Overrides the isKing() method to return
             * false always since this is not a king piece
             * @return boolean false
             */
            @Override
            public boolean isKing() {
                return false;
            }

            /**
             * Override the isRook() method to return boolean
             * false always since this is not a rook piece
             * @return boolean false
             */
            @Override
            public boolean isRook() {
                return false;
            }
        },
        KNIGHT("N",300) {
            /**
             * Overrides the isKing() method to return
             * false always since this is not a king piece
             * @return boolean false
             */
            @Override
            public boolean isKing() {
                return false;
            }

            /**
             * Override the isRook() method to return boolean
             * false always since this is not a rook piece
             * @return boolean false
             */
            @Override
            public boolean isRook() {
                return false;
            }
        },
        BISHOP("B",300) {
            /**
             * Overrides the isKing() method to return
             * false always since this is not a king piece
             * @return boolean false
             */
            @Override
            public boolean isKing() {
                return false;
            }

            /**
             * Override the isRook() method to return boolean
             * false always since this is not a rook piece
             * @return boolean false
             */
            @Override
            public boolean isRook() {
                return false;
            }
        },
        ROOK("R",500 ) {
            /**
             * Overrides the isKing() method to return
             * false always since this is not a king piece
             * @return boolean false
             */
            @Override
            public boolean isKing() {
                return false;
            }

            /**
             * Override the isRook() method to return boolean
             * true always since this is a rook piece
             * @return boolean true
             */
            @Override
            public boolean isRook() {
                return true;
            }
        },
        QUEEN("Q", 900) {
            /**
             * Overrides the isKing() method to return
             * false always since this is not a king piece
             * @return boolean false
             */
            @Override
            public boolean isKing() {
                return false;
            }

            /**
             * Override the isRook() method to return boolean
             * false always since this is not a rook piece
             * @return boolean false
             */
            @Override
            public boolean isRook() {
                return false;
            }
        },
        KING("K", 10000) {
            /**
             * Overrides the isKing() method to return
             * true always since this is a king piece
             * @return boolean true
             */
            @Override
            public boolean isKing() {
                return true;
            }

            /**
             * Override the isRook() method to return boolean
             * false always since this is not a rook piece
             * @return boolean false
             */
            @Override
            public boolean isRook() {
                return false;
            }
        };

        //fields
        final private String pieceName;
        final private int pieceValue;

        //constructor
        PieceType(final String pieceName, final int pieceValue){
            this.pieceName = pieceName;
            this.pieceValue = pieceValue;
        }

        /**
         * Override the toSring() to return the pieceName of the piece
         * @return String pieceName
         */
        @Override
        public String toString(){
            return this.pieceName;
        }

        /**
         * getPieceValue() method returns the piece value of a piecetype
         * @return
         */
        public int getPieceValue(){return this.pieceValue;}

        //abstract methods
        public abstract boolean isKing();
        public abstract boolean isRook();
    }
}
