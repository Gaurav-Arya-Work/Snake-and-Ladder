
public class Player extends Game{
    private int PositionRow;
    private int PositionCol;
    private int PreviousCol;
    private int PreviousRow;
    private Coordinates PastCoordinates;
    private String Name;
    public Player (int PositionRow, int PositionCol, int PreviousRow, int PreviousCol, Coordinates PastCoordinates, String Name){
        this.Name = Name;
        this.PositionRow = PositionRow;
        this.PositionCol = PositionCol;
        this.PreviousCol = PreviousCol;
        this.PreviousRow = PreviousRow;
        this.PastCoordinates = PastCoordinates;
    }

    public String GetName(){
        return this.Name;
    }

    public int GetPositionRow(){
        return this.PositionRow;
    }

    public int GetPreviousRow(){
        return this.PreviousRow;
    }

    public int GetPositionCol(){
        return this.PositionCol;
    }

    public int GetPreviousCol(){
        return this.PreviousCol;
    }
    public void GetCoordinates(){
        this.PastCoordinates.PrintCoordinates();
    }

    public void SetName(String NewName){
        this.Name = NewName;
    }

    public void SetPositionRow(int PositionRow){
        this.PositionRow = PositionRow;
    }

    public void SetPreviousRow(){
        this.PreviousRow = this.PositionRow;
    }

    public void SetPreviousCol(){
        this.PreviousCol = this.PositionCol;
    }
    public void SetPositionCol(int PositionCol){
        this.PositionCol = PositionCol;
    }

    public void SetCoordinates(int YPos, int XPos){
        int Coordinate = (YPos * 12) + XPos;
        this.PastCoordinates.push(Coordinate);
    }

    public String toString(){
        return("Name: " + this.Name + " PositionX: " + this.PositionRow + " PositionY: " + this.PositionCol + " PastPositionX: " + this.PreviousCol + " PastPositionY: " + this.PreviousRow);
    }
}
