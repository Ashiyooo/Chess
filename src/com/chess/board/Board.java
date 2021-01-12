//Code written by Joseph Victor Sumbong for CMSC 22
//packages
package com.chess.board;

//imports
import com.chess.Color;
import com.chess.piece.*;
import com.chess.player.BlackPlayer;
import com.chess.player.Player;
import com.chess.player.WhitePlayer;
import java.util.*;

/**
 * Board Class is the class where the board is created
 * It has the following methods: whitePlayer(), blackPlayer(), currentPlayer(),
 * getAllLegalMoves(), calculateActivePieces(), calculateLegalMoves(), getWhitePieces(), getBlackPieces(),
 * getEnPassantPawn(), getTile(), createGameBoard(), and createStandardBoard()
 *
 * The static class Builder can also be found within this file
 */
public class Board {
    //fields
    private final List<Tile> gameBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    private final WhitePlayer whitePlayer;
    private final BlackPlayer blackPlayer;
    private final Player currentPlayer;
    private final Pawn enPassantPawn;

    /**
     * Board() is private since it should not be instantiated by the other classes other than the Builder class
     * @param builder from the builder class
     */
    private Board(final Builder builder){
        this.gameBoard = createGameBoard(builder);
        this.whitePieces = calculateActivePieces(this.gameBoard, Color.WHITE);
        this.blackPieces = calculateActivePieces(this.gameBoard, Color.BLACK);
        this.enPassantPawn = builder.enPassantPawn;
        final Collection<Move> whiteStandardLegalMove = calculateLegalMoves(this.whitePieces);
        final Collection<Move> blackStandardLegalMove = calculateLegalMoves(this.blackPieces);
        this.whitePlayer = new WhitePlayer(this, whiteStandardLegalMove, blackStandardLegalMove);
        this.blackPlayer = new BlackPlayer(this, whiteStandardLegalMove, blackStandardLegalMove);
        this.currentPlayer = builder.nextMoveMaker.choosePlayer(this.whitePlayer,this.blackPlayer);
    }

    /**
      whitePlayer() method does not take any parameter and
     it is basically a getter method for other parts of the program
     to get the whitePlayer initialized in the board.
      it returns a Player which is the whitePlayer
     */
    public Player whitePlayer(){
        return this.whitePlayer;
    }

    /**
     blackPlayer() method does not take any parameter and
     it is basically a getter method for other parts of the program
     to get the blackPlayer initialized in the board.
     it returns a Player which is the blackPlayer
     */
    public Player blackPlayer(){
        return this.blackPlayer;
    }

    /**
     * currentPLayer() method does not take any parameter.
     * it is basically a getter method for other parts of the program
     * to get the currentPlayer allowed to make the move
     * @return a Player which is the currentPlayer
     */
    public Player currentPlayer(){
        return this.currentPlayer;
    }

    /**
     * getEnPassantPawn() method does not take any parameter.
     * It is a getter method for other parts of the program to
     * get the enPassantPawn of the current board.
     * @return a Pawn which is the stated enPassantPawn
     */
    public Pawn getEnPassantPawn(){return this.enPassantPawn;}

    /**
     * getBlackPieces() does not take any parameter
     * allows other parts of the program to access the collection
     * of blackPieces which are currently active
     * @return Collection<Piece> of blackPieces
     */
    public Collection<Piece> getBlackPieces(){
        return this.blackPieces;
    }

    /**
     * getWhitekPieces() does not take any parameter
     * allows other parts of the program to access the collection
     * of whitePieces which are currently active
     * @return Collection<Piece> of whitePieces
     */
    public Collection<Piece> getWhitePieces(){
        return this.whitePieces;
    }

    /**
     * calculateLegalMoves() is a private method that calls on each active piece and their individual calculateLegalMove()
     * method all of those immutable list are stored in another immutable list here called legalMoves
     * @param pieces
     * @return immutable list of legalMoves
     */
    private Collection<Move> calculateLegalMoves(final Collection<Piece> pieces) {
        final List<Move> legalMoves = new ArrayList<>();
        for(final Piece piece: pieces){
            legalMoves.addAll(piece.calculateLegalMove(this));
        }
        return Collections.unmodifiableList(legalMoves);
    }

    /**
     * calculateActivePieces() is a private static method that looks at the gameBoard and finds all active pieces on it.
     * All the active pieces found on the gameBoard is stored in a list called activePieces
     * @param gameBoard which is the current board state
     * @param color or the alliance of the piece (whether it is a white or a black piece)
     * @return an immutable list of activePieces
     */
    private static Collection<Piece> calculateActivePieces(final List<Tile> gameBoard, final Color color) {
        final List<Piece> activePieces = new ArrayList<>();
        for(final Tile tile : gameBoard){
            if(tile.isTileOccupied()){
                final Piece piece = tile.getPiece();
                if(piece.getColor() == color){
                    activePieces.add(piece);
                }
            }
        }
        return Collections.unmodifiableList(activePieces);
    }

    /**
     * getAllLegalMoves() is a method that gets all the legal of both the
     * whitePlayer and the blackPlayer
     * @return an immutable list of allLegalMoves
     */
    public Iterable<Move> getAllLegalMoves() {
        List<Move> allLegalMoves = new ArrayList<>();
        allLegalMoves.addAll(this.whitePlayer.getLegalMoves());
        allLegalMoves.addAll(this.blackPlayer.getLegalMoves());
        return Collections.unmodifiableList(allLegalMoves);
    }

    /**
     * getTile() is a getter method for other parts of the program to access a certain tile in the gameBoard
     * @param tileCoordinate which is an integer and must be between 0-63 including both 0-63
     * @return a Tile at that specific coordinate
     */
    public Tile getTile(final int tileCoordinate){
        return gameBoard.get(tileCoordinate);
    }

    /**
     * createGameBoard() method creates the tiles of the gameBoard which in the
     * standard game of chess has a total of 64 tiles
     * @param builder from the Builder design pattern
     * @return an array as list of the tiles created
     */
    private static List<Tile> createGameBoard(final Builder builder){
        final Tile[] tiles = new Tile[BoardUtils.NUM_TILES];
        for(int i = 0; i < BoardUtils.NUM_TILES; i++){
            tiles[i] = Tile.createTile(i, builder.boardConfig.get(i));
        }
        return Arrays.asList(tiles);
    }

    /**
     * createStandardBoard() method does not take in a parameter.
     * It creates the initial standard layout of a chess game
     *  where in both the black and white player has not made a move yet
     * @return a Board which is built by the Builder class
     */
    public static Board createStandardBoard(){
        final Builder builder = new Builder();

        //Black Layout
        builder.setPiece(new Rook(0, Color.BLACK));
        builder.setPiece(new Knight(1, Color.BLACK));
        builder.setPiece(new Bishop(2, Color.BLACK));
        builder.setPiece(new Queen(3, Color.BLACK));
        builder.setPiece(new King(4, Color.BLACK, false));
        builder.setPiece(new Bishop(5, Color.BLACK));
        builder.setPiece(new Knight(6, Color.BLACK));
        builder.setPiece(new Rook(7, Color.BLACK));
        for(int i = 8; i < 16; i++){
            builder.setPiece(new Pawn(i, Color.BLACK));
        }

        //White Layout
        for(int i = 48; i < 56; i++){
            builder.setPiece(new Pawn(i, Color.WHITE));
        }
        builder.setPiece(new Rook(56, Color.WHITE));
        builder.setPiece(new Knight(57, Color.WHITE));
        builder.setPiece(new Bishop(58, Color.WHITE));
        builder.setPiece(new Queen(59, Color.WHITE));
        builder.setPiece(new King(60, Color.WHITE, false));
        builder.setPiece(new Bishop(61, Color.WHITE));
        builder.setPiece(new Knight(62, Color.WHITE));
        builder.setPiece(new Rook(63, Color.WHITE));

        //setting the maker of the first move of the game as white
        builder.setMoveMaker(Color.WHITE);

        return builder.build();
    }


    /**
     * Builder class uses the Builder Design Pattern to create a complex object using simple object
     * basically creating a board for a chess game in our case
     * the methods found here are setPiece(), setMoveMaker(), build() and, setEnPassantPawn
     */
    public static class Builder{

        Map<Integer, Piece> boardConfig;
        Color nextMoveMaker;
        Pawn enPassantPawn;

        /**
         * Builder() is a constructor and sets the boardConfig as a HashMap<> which lets us have a less expensive
         * program since we can just map each piece to a certain tile coordinate
         */
        public Builder(){
            this.boardConfig = new HashMap<>();
        }

        /**
         * setPiece() is a method to place a piece on a certain tile on the board
         * It only takes 1 parameter since we can get the other needed data from the piece itself
         * @param piece that is to be set or placed in a certain tile
         * @return boardConfig with the piece set/put on it
         */
        public Builder setPiece(final Piece piece){
            this.boardConfig.put(piece.getPiecePosition(), piece);
            return this;
        }

        /**
         * setMoveMaker() is a method to set which Color/Alliance player can make the move
         * @param nextMoveMaker is a Color defined in the Color enum class in this program which is either black or white
         * @return the builder with the new nextMoveMaker set color
         */
        public Builder setMoveMaker(final Color nextMoveMaker){
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        //to identify the enPassantPawn
        public void setEnPassantPawn(Pawn enPassantPawn) {
            this.enPassantPawn = enPassantPawn;
        }
        /**
         * build() is a method that creates the board with the specific boardConfig
         * @return a mew Board
         */
        public Board build(){
            return new Board(this);
        }
    }
}
