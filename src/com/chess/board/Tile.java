//Code written by Joseph Victor Sumbong for CMSC 22
//package
package com.chess.board;

//imports
import com.chess.piece.Piece;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Tile is a class that creates a Tile Object for the game to be played on
 */
public abstract class Tile {
    //fields
    protected final int tileCoordinate;
    private static final Map<Integer, EmptyTile> EMPTY_TILE = createAllPossibleEmptyTiles(); //field that stores all the possible empty tiles that can occur

    //constructor
    protected Tile(final int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }


    /**
     * createAllPossibleEmptyTiles() method creates all possible tiles which is 64 tiles of EmptyTiles
     * this is an Object Pool design pattern since we create it once and we take the objects from here if we need an
     * EmptyTile
     * @return an immutable Map of the emptyTileMap
     */
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles() {
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();

        for( int i= 0; i < BoardUtils.NUM_TILES; i++) {
            emptyTileMap.put(i, new EmptyTile(i));
        }

        return Collections.unmodifiableMap(emptyTileMap);
    }


    /**
     * createTile() is a method to create either an OccupiedTile or an EmptyTile depending on
     * the presence of a piece on that tile
     * @param tileCoordinate is the coordinate of the tile which is an integer value
     * @param piece is the piece for the occupied tile
     * @return either an OccupiedTile or an EmptyTile
     */
    public static Tile createTile(final int tileCoordinate, final Piece piece) {
        return piece != null? new OccupiedTile(tileCoordinate, piece): EMPTY_TILE.get(tileCoordinate);
    }

    /**
     * getTileCoordinate() is a method that allows the caller to get the tileCoordinate
     * @return an integer which is the value of the tileCoordinate
     */
    public int getTileCoordinate(){
        return this.tileCoordinate;
    }

    //abstract methods to be specified in the child classes
    public abstract boolean isTileOccupied();
    public abstract Piece getPiece();

}
