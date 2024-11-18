import java.util.Deque;

class Game {
    Deque<Player> players;
    Board board;

    Game() {
        initialiseGame();
    }

    void startGame();

    void initialiseGame();

    boolean isThereWinner(int row, int column, PieceType pieceType);

}

class Player {
    String name;
    PlayingPiece piece;
}

class Board {
    PlayingPiece[][] playingBoard;

    Board(int size) {
        playingBoard = new PlayingPiece[size][size];
    }

    void addPiece(int row, int col, PlayingPiece piece);
}

class PlayingPiece {
    PieceType type;
}

enum PieceType {
    X,
    O
}

class PlayingPieceX {
    PlayingPieceX() {
        super(PieceType.X);
    }
}

class PlayingPieceO {
    PlayingPieceO() {
        super(PieceType.O);
    }
}
