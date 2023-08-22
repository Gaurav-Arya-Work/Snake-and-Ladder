import java.util.Scanner;

abstract class Game {
 public Game(){}
    public int DiceRoll(int Min, int Max){
        int Die = (int) (Math.random() * Max) + Min;
        return (Die);
    }

    public void MainGame(Player[] PlayersStorage, String[][]SnakesAndLadders, Board GameBoard, GameQueue Turn){

        Scanner sc = new Scanner (System.in);

        int Max = SnakesAndLadders.length * SnakesAndLadders.length;

        int DiceMin = 1;

        int DiceMax = 6;

        String DiceResponse = "";

        GameBoard.SetUpSnakes(Max, 0);

        GameBoard.SetUpLadders(Max, 0);

        int[][] Snakes = GameBoard.GetSnakes();

        int[][] Ladders = GameBoard.GetLadders();

        boolean GameEnded = false;

        String Response;

        int PreviousRow;

        int PreviousCol;

        int Rounds = 0;

        while (GameEnded == false){

            for (int o = 0; o < Turn.size(); o++){

                int Roll = 0;

                int CoordinatesFix = 0;

                String Name = PlayersStorage[o].GetName();

                Name.substring(0, 1);

                Rounds += 1;

                int YPos = PlayersStorage[o].GetPositionRow();

                int XPos = PlayersStorage[o].GetPositionCol();

                String PlayerName = PlayersStorage[o].GetName();

                PlayersStorage[o].SetCoordinates(YPos, XPos);

                Response = "l";

                while (!Response.equals("yes") || !Response.equals("no")){

                    System.out.println("============");

                    System.out.println("\nPlayer: " + o + "'s Turn!");

                    System.out.println("\nRoll? (yes to roll, no to quit)");

                    System.out.print(">");

                    Response = sc.nextLine();

                    if (Response.equals("yes")){

                        Roll = GameBoard.DiceRoll(DiceMin, DiceMax);

                        break;

                    } else if (Response.equals("no")){

                        if (Rounds >= 10){

                            System.out.println(Turn.dequeue(o));

                            System.out.println(PlayerName + " has quit the game.");

                            SnakesAndLadders[YPos][XPos] = "Q";

                            if (Turn.size() == 1){

                                GameEnded = true;

                                System.out.println("Everybody quit, " + PlayersStorage[o].GetName() + " wins!");

                                break;

                            } else {

                                break;

                            }
                        } else {

                            System.out.println("\n10 rounds or more must be played");

                            Response = "";
                        }

                    } else if (Response.equals("past")){

                        PlayersStorage[o].GetCoordinates();

                    } else {

                        System.out.println("\nInvalid Response\n");

                    }
                }

                if (YPos == 0){

                    if (XPos + Roll == SnakesAndLadders[0].length - 1){

                        System.out.println("\nPlayer " + o + ", rolled an " + Roll);

                        XPos += Roll;

                        System.out.println ("Player: " + o + ", wins in " + Rounds + " rounds!");

                        GameEnded = true;

                        break;

                    } else if (XPos + Roll > SnakesAndLadders[0].length - 1){

                        System.out.println("\nPlayer " + o + ", rolled an " + Roll);

                        Roll = Roll - ((SnakesAndLadders[0].length) - XPos);

                        System.out.println("You moved past the finish line, so you moved back " + (Roll + 1) + " spaces!");

                        XPos = (SnakesAndLadders[0].length - 1) - (Roll + 1);

                        while (SnakesAndLadders[YPos][XPos] != "-" & SnakesAndLadders[YPos][XPos] != "S" & SnakesAndLadders[YPos][XPos] != "L" & SnakesAndLadders[YPos][XPos] != Name){

                            XPos -= 1;

                            CoordinatesFix =(SnakesAndLadders[0].length - 1) - XPos;

                            if (YPos % 2 == 1){
                                System.out.println("You've landed on a player, so you moved backwards to, " + YPos + ", " + XPos);
                            } else {
                                System.out.println("You've landed on a player, so you moved backward to, " + YPos + ", " + CoordinatesFix);
                            }
                        }

                    } else {

                        System.out.println("\nPlayer " + o + ", rolled an " + Roll);

                        XPos += Roll;

                        while (SnakesAndLadders[YPos][XPos] != "-" & SnakesAndLadders[YPos][XPos] != "S" & SnakesAndLadders[YPos][XPos] != "L" & SnakesAndLadders[YPos][XPos] != Name){

                            XPos -= 1;

                            CoordinatesFix = (SnakesAndLadders[0].length - 1) - XPos;

                            if (YPos % 2 == 1){
                                System.out.println("You've landed on a player, so you moved backward to, " + YPos + ", " + XPos);
                            } else {
                                System.out.println("You've landed on a player, so you moved backward to, " + YPos + ", " + CoordinatesFix);
                            }
                        }
                    }

                } if (XPos + Roll >= SnakesAndLadders[0].length & YPos != 0){

                    System.out.println("\nPlayer " + o + ", rolled an " + Roll);
                    double NumA = Roll * 10.0;
                    double NumB = SnakesAndLadders[0].length * 10.0;

                    YPos -= (int) (Math.ceil(NumA / NumB));
                    Roll = Roll - ((SnakesAndLadders[0].length) - XPos);

                    XPos = 0;
                    XPos += Roll;

                    while (SnakesAndLadders[YPos][XPos] != "-" & SnakesAndLadders[YPos][XPos] != "S" & SnakesAndLadders[YPos][XPos] != "L" & SnakesAndLadders[YPos][XPos] != Name){

                        XPos += 1;

                        CoordinatesFix = (SnakesAndLadders[0].length - 1) - XPos;

                        if (YPos % 2 == 1){
                            System.out.println("You've landed on a player, so you moved forward to, " + YPos + ", " + XPos);
                        } else {
                            System.out.println("You've landed on a player, so you moved forward to, " + YPos + ", " + CoordinatesFix);
                        }

                        if (XPos == SnakesAndLadders[0].length){
                            YPos -= 1;
                            XPos = 0;
                        }
                    }

                } else if (YPos != 0){

                    System.out.println("\nPlayer " + o + ", rolled an " + Roll);

                    XPos += Roll;

                    while (SnakesAndLadders[YPos][XPos] != "-" & SnakesAndLadders[YPos][XPos] != "S" & SnakesAndLadders[YPos][XPos] != "L" & SnakesAndLadders[YPos][XPos] != Name){

                        XPos += 1;

                        CoordinatesFix = (SnakesAndLadders[0].length - 1) - XPos;

                        if (YPos % 2 == 1){
                            System.out.println("You've landed on a player, so you moved forward to, " + YPos + ", " + XPos);
                        } else {
                            System.out.println("You've landed on a player, so you moved forward to, " + YPos + ", " + CoordinatesFix);
                        }

                        if (XPos == SnakesAndLadders[0].length){
                            YPos -= 1;
                            XPos = 0;
                        }
                    }
                }

                if (SnakesAndLadders[YPos][XPos] == "S"){

                    System.out.println("You've landed on a snake!");

                    for (int x = 0; x < Snakes.length; x ++){

                        if (Snakes[x][0] == YPos & Snakes[x][1] == XPos){

                            YPos = Snakes[x][2];

                            XPos = Snakes[x][3];
                            CoordinatesFix = (SnakesAndLadders[0].length - 1) - XPos;

                            if (YPos % 2 == 1){
                                System.out.println("You've been moved to " + YPos + ", " + XPos);
                            } else {
                                System.out.println("You've been moved to " + YPos + ", " + CoordinatesFix);
                            }

                            while (SnakesAndLadders[YPos][XPos] != "-" & SnakesAndLadders[YPos][XPos] != "S" & SnakesAndLadders[YPos][XPos] != "L" & SnakesAndLadders[YPos][XPos] != Name){
                                XPos += 1;
                                CoordinatesFix = (SnakesAndLadders[0].length - 1) - XPos;

                                if (YPos % 2 == 1){
                                    System.out.println("You've landed on a player, so you moved forward to, " + YPos + ", " + XPos);
                                } else {
                                    System.out.println("You've landed on a player, so you moved forward to, " + YPos + ", " + CoordinatesFix);
                                }

                                if (XPos == SnakesAndLadders[0].length){
                                    YPos -= 1;
                                    XPos = 0;
                                }
                            }

                            break;
                        }
                    }
                }


                if (SnakesAndLadders[YPos][XPos] == "L"){

                    System.out.println("You've landed on a Ladder!");

                    for (int x = 0; x < Snakes.length; x ++){

                        if (Ladders[x][0] == YPos & Ladders[x][1] == XPos){

                            YPos = Ladders[x][2];

                            XPos = Ladders[x][3];

                            CoordinatesFix = (SnakesAndLadders[0].length - 1) - XPos;

                            if (YPos % 2 == 1){

                                System.out.println("You've been moved to " + YPos + ", " + XPos);

                            } else {

                                System.out.println("You've been moved to " + YPos + ", " + CoordinatesFix);

                            }

                            while (SnakesAndLadders[YPos][XPos] != "-" & SnakesAndLadders[YPos][XPos] != "S" & SnakesAndLadders[YPos][XPos] != "L" & SnakesAndLadders[YPos][XPos] != Name){

                                XPos += 1;

                                CoordinatesFix = (SnakesAndLadders[0].length - 1) - XPos;

                                if (YPos % 2 == 1){

                                    System.out.println("You've landed on a player, so you moved forward to, " + YPos + ", " + XPos);

                                } else {

                                    System.out.println("You've landed on a player, so you moved forward to, " + YPos + ", " + ((SnakesAndLadders[0].length - 1) - XPos));

                                }

                                if (XPos == SnakesAndLadders[0].length){

                                    YPos -= 1;

                                    XPos = 0;

                                }
                            }

                            break;
                        }
                    }
                }

                PlayersStorage[o].SetPositionRow(YPos);
                PlayersStorage[o].SetPositionCol(XPos);

                PreviousRow = PlayersStorage[o].GetPreviousRow();
                PreviousCol = PlayersStorage[o].GetPreviousCol();

                SnakesAndLadders[PreviousRow][PreviousCol] = "-";

                PlayersStorage[o].SetPreviousRow();
                PlayersStorage[o].SetPreviousCol();

                GameBoard.MovePlayer (XPos, YPos, SnakesAndLadders, PlayerName, GameBoard);

                System.out.println("\nRound: " + Rounds);

                GameBoard.PrintBoard();

                System.out.println("\nPlayers: ");

                for (int q = 0; q < Turn.size(); q ++){
                    if (YPos % 2 == 1){
                        System.out.println(q + ": " + Turn.peek(q) + ": " + PlayersStorage[q].GetPositionRow() + ", " + PlayersStorage[q].GetPositionCol());
                    } else {
                        System.out.println(q + ": " + Turn.peek(q) + ": " + PlayersStorage[q].GetPositionRow() + ", " + ((SnakesAndLadders[0].length - 1) - XPos));
                    }
                }

                System.out.println("\n");

            }
        }
    }

    public String[][] MovePlayer (int Col, int Row, String[][] Game, String Name, Board GameBoard){

        Game[Row][Col] = Name.substring(0, 1);

        return (Game);
    }
}
