//Code written by Joseph Victor Sumbong
//packages
package com.tests.chess.board;

//imports
import com.chess.Color;
import com.chess.board.Board;
import com.chess.board.BoardUtils;
import com.chess.board.Move;
import com.chess.board.MoveTransition;
import com.chess.piece.King;
import com.chess.piece.Pawn;
import com.chess.piece.Queen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *CheckCheckMateStaleMateTest is a class that test the check, checkmate, and stalemate states of the game if it
 * works as intended
 */
public class CheckCheckMateStaleMateTest {

    /**
     * check() tests if the isInCheck() works properly
     */
    @Test
    public void check(){
        final Board.Builder builder = new Board.Builder();

        builder.setPiece(new King(7, Color.WHITE, false));
        builder.setPiece(new King(22, Color.BLACK, false));
        builder.setPiece(new Queen(24, Color.BLACK));
        builder.setMoveMaker(Color.BLACK);

        final Board board = builder.build();
        final MoveTransition t1 = board.currentPlayer().makeMove(Move.MoveFactory.createMove(board, BoardUtils.getPositionAtTile("a5"),
                BoardUtils.getPositionAtTile("h5")));
        assertTrue(t1.getMoveStatus().isDone());
        assertTrue(t1.getBoard().currentPlayer().isInCheck());
    }

    /**
     * discoveredCheck() tests if the isInCheck() works properly when it a discovered check move or moving this piece subsequently
     * attacks the opponents king leading to discovered check
     */
    @Test
    public void discoveredCheck(){
        final Board.Builder builder = new Board.Builder();

        builder.setPiece(new King(23, Color.WHITE, false));
        builder.setPiece(new King(0, Color.BLACK, false));
        builder.setPiece(new Pawn(16, Color.WHITE, false));
        builder.setPiece(new Pawn(9, Color.BLACK));
        builder.setPiece(new Queen(56, Color.WHITE));
        builder.setMoveMaker(Color.WHITE);

        final Board board = builder.build();
        final MoveTransition t1 = board.currentPlayer().makeMove(Move.MoveFactory.createMove(board, BoardUtils.getPositionAtTile("a6"),
                BoardUtils.getPositionAtTile("b7")));
        assertTrue(t1.getMoveStatus().isDone());
        assertTrue(t1.getBoard().currentPlayer().isInCheck());
    }

    /**
     * checkMateTest() tests if the isInCheckMate() works properly
     */
    @Test
    public void checkMateTest(){
        final Board.Builder builder = new Board.Builder();

        builder.setPiece(new King(7, Color.WHITE, false));
        builder.setPiece(new King(22, Color.BLACK, false));
        builder.setPiece(new Queen(8, Color.BLACK));
        builder.setMoveMaker(Color.BLACK);

        final Board board = builder.build();
        final MoveTransition t1 = board.currentPlayer().makeMove(Move.MoveFactory.createMove(board, BoardUtils.getPositionAtTile("a7"),
                BoardUtils.getPositionAtTile("g7")));
        assertTrue(t1.getMoveStatus().isDone());
        assertTrue(t1.getBoard().currentPlayer().isInCheckMate());
    }

    /**
     * staleMateTest() tests if the isInStaleMate() works properly
     */
    @Test
    public void staleMateTest(){
        final Board.Builder builder = new Board.Builder();

        builder.setPiece(new King(7, Color.WHITE, false));
        builder.setPiece(new King(23, Color.BLACK, false));
        builder.setPiece(new Queen(56, Color.BLACK));
        builder.setMoveMaker(Color.BLACK);

        final Board board = builder.build();
        final MoveTransition t1 = board.currentPlayer().makeMove(Move.MoveFactory.createMove(board, BoardUtils.getPositionAtTile("a1"),
                BoardUtils.getPositionAtTile("g1")));
        assertTrue(t1.getMoveStatus().isDone());
        assertTrue(t1.getBoard().currentPlayer().isInStaleMate());
    }

}
