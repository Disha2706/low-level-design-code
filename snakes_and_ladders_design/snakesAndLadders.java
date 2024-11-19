class Game{
    Deque<Player> players;
    Board gameBoard;

    Game(){
        initialiseGame();
    }

    void startGame();
    void initialiseGame();
    void isThereWinner(int row, int col, PieceType pieceType);

};

class Player{
    int id;
    PlayingPiece piece;
    Pair<int,int> currentPos;
};

class PlayingPiece{
    PieceType type;

    PlayingPiece(PieceType pieceType){
        type = pieceType;
    }
};

enum PieceType{

    RED,
    GREEN,
    YELLOW,
    BLUE
};

class Dice{
    int min = 1;
    int max = 6;

    int rollDice{
        Random rand = new Random();
        return rand.nextInt(max-min+1) + min;
    }
};

class Board{

    Cell[][] gameBoard;
    
    Board(int size){
        gameBoard = new Cell[size][size];
    }

    void addPiece(int row, int col, Cell cell);

};

class Cell{
    int start;
    int end;
};