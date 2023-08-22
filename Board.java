
import java.util.Random;

public class Board extends Game{
    private String[][] Board;
    private int[][] Snakes;
    private int[][] Ladders;
    public Board(String[][] Board, int[][] Snakes, int[][] Ladders){
        this.Snakes = Snakes;
        this.Ladders = Ladders;
        this.Board = Board;
    }
    public void SetUpSnakes(int Max, int Min){
        Random rand = new Random();
        for (int x = 0; x < this.Snakes.length; x ++){
            int SnakesYX = rand.nextInt((Max - 30) - Min) + Min;

            this.Snakes[x][0] = (SnakesYX - ((int)(Math.floor(SnakesYX / this.Board.length)) + 1)) / this.Board.length;

            this.Snakes[x][1] = SnakesYX - ((int)(Math.floor(SnakesYX / this.Board[0].length)) * this.Board[0].length);

            int FallToYX = rand.nextInt(Max - SnakesYX) + SnakesYX;

            this.Snakes[x][2] = (FallToYX - ((int)(Math.floor(FallToYX / this.Board.length)) + 1)) / this.Board.length;

            this.Snakes[x][3] = FallToYX - ((int)(Math.floor(FallToYX / this.Board[0].length)) * this.Board[0].length);
        }
    }
    public void SetUpLadders(int Max, int Min){

        Random rand = new Random();

        for (int x = 0; x < this.Ladders.length; x ++){

            int LaddersYX = rand.nextInt(Max - (Max / 2)) + Max / 2;

            this.Ladders[x][0] = (LaddersYX - ((int)(Math.floor(LaddersYX/  this.Board.length)) + 1)) /  this.Board.length;

            this.Ladders[x][1] = LaddersYX - ((int)(Math.floor(LaddersYX / this.Board[0].length)) * this.Board[0].length);

            try{

                this.Ladders[x][2] = rand.nextInt(this.Ladders[x][0] - 0) + 0;

                this.Ladders[x][3] = rand.nextInt(this.Ladders[x][1] - 0) + 0;

            } catch (IllegalArgumentException e){

                System.out.println("\nLadder generation error, trying again");

            }
        }
        System.out.println("\nLadder generation done");
    }

    public int[][] GetSnakes(){
        return(this.Snakes);
    }

    public int[][] GetLadders(){
        return(this.Ladders);
    }

    public String[][] GetBoard(){
        return(this.Board);
    }

    public void PrintBoard(){

        this.Board[this.Board.length - 1][0] = ">";

        this.Board[0][this.Board[0].length - 1] = "X";

        for (int y = 0; y < this.Snakes.length; y ++){

            int Y = this.Snakes[y][0];

            int X = this.Snakes[y][1];

            int EndY = this.Snakes[y][2];

            int EndX = this.Snakes[y][3];

            this.Board[Y][X] = "S";
        }

        for (int x = 0; x < this.Ladders.length; x ++){

            int Y = this.Ladders[x][0];

            int X = this.Ladders[x][1];

            int EndY = this.Ladders[x][2];

            int EndX = this.Ladders[x][3];

            this.Board[Y][X] = "L";

        }

        System.out.println();

        for (int l = 0; l < this.Board[0].length; l++){

            System.out.print("= ");

        }

        System.out.println();

        for (int i = 0; i < this.Board.length; i++){

                if (i % 2 == 1){

                for (int j = 0; j < this.Board[i].length; j++){

                    System.out.print(this.Board[i][j] + " ");

                }

                System.out.println();

            }else{

                for (int j = this.Board[i].length - 1; j >= 0; j--){

                    System.out.print(this.Board[i][j] + " ");

                }

                System.out.println();

            }
        }

        for (int l = 0; l < this.Board[0].length; l++){
            System.out.print("= ");
        }
        System.out.println();
    }

    public String[][] SetUpBoard(){

        for (int x = 0; x < this.Board.length; x++){

            for (int y = 0; y < this.Board[x].length; y++){

                this.Board[x][y] = "-";

            }

        }

        return (this.Board);
    }

}
