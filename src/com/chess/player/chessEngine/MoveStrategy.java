//Code written by Joseph Victor Sumbong
//package
package com.chess.player.chessEngine;

//imports
import com.chess.board.Board;
import com.chess.board.Move;

/**
 * an interface class for MoveStrategy
 */
public interface MoveStrategy {

    Move execute(Board board);
}
