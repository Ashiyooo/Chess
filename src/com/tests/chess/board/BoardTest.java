//Code written by Joseph Victor Sumbong
//package
package com.tests.chess.board;

//imports
import com.chess.board.Board;
import com.chess.player.chessEngine.StandardBoardEvaluator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * BoardTest is a class that test if the Board class works properly
 */
class BoardTest {

    /**
     * Test the initialBoard or the standardBoard
     */
    @Test
    public void initialBoard(){
        final Board board = Board.createStandardBoard();
        assertEquals(board.currentPlayer().getLegalMoves().size(), 20);
        assertEquals(board.currentPlayer().getOpponent().getLegalMoves().size(), 20);
        assertFalse(board.currentPlayer().isInCheck());
        assertFalse(board.currentPlayer().isInCheckMate());
        assertFalse(board.currentPlayer().isInStaleMate());
        assertFalse(board.currentPlayer().isCastled());
        assertEquals(board.currentPlayer(), board.whitePlayer());
        assertEquals(board.currentPlayer().getOpponent(), board.blackPlayer());
        assertFalse(board.currentPlayer().getOpponent().isInCheck());
        assertFalse(board.currentPlayer().getOpponent().isInCheckMate());
        assertFalse(board.currentPlayer().getOpponent().isCastled());
        assertFalse(board.currentPlayer().getOpponent().isInStaleMate());
        assertEquals(new StandardBoardEvaluator().evaluate(board, 0), 0);
    }

}