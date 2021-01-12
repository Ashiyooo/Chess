//Code written by Joseph Victor Sumbong CMSC 22
//package
package com.chess.player.chessEngine;

//imports
import com.chess.board.Board;

/**
 * an interface class to evaluate the board
 */
public interface BoardEvaluator {

    int evaluate(Board board, int depth);
}
