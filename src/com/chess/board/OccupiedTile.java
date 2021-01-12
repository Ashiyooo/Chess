//Code written by Joseph Victor Sumbong for CMSC 22
//package
package com.chess.board;

//imports
import com.chess.piece.Piece;

/**
 * OccupiedTile is a child class of Tile
 * It is for tiles in which there is piece on it
 */
public final class OccupiedTile extends Tile{
    //fields
    private final Piece pieceOnTile;

    //constructor that creates an occupied tile
    protected OccupiedTile(int tileCoordinate, final Piece pieceOnTile){
        super(tileCoordinate);
        this.pieceOnTile = pieceOnTile;
    }

    /**
     * Overrides the isTileOccupied() method in the parent class to always return
     * true because by definition an OccuppiedTile always has a piece on it
     * @return boolean value true
     */
    @Override
    public boolean isTileOccupied(){
        return true;
    }

    /**
     * Overrides the getPiece() method in the parent class and returns the piece on the tile
     * @return pieceOnTile which is a piece object
     */
    @Override
    public Piece getPiece(){
        return this.pieceOnTile;
    }
}
