import java.util.ArrayList;
public class Coordinates {

    ArrayList<Integer> Coordinates = new ArrayList<>();
    public Coordinates(){}
    public void push(int toAdd){
        Coordinates.add(0, toAdd);
    }

    public String pop(){
        try {
            return Integer.toString(Coordinates.remove(0));
        } catch (Exception e){
            return null;
        }
    }
    public String peek(){
        try {
            return Integer.toString(Coordinates.get(0));
        } catch (Exception e){
            return null;
        }
    }

    public void PrintCoordinates(){
        int index = Coordinates.size();
        for(int element: Coordinates){

            double Position = element * 1.0;

            int YPos = (element - (int)(Math.ceil(Position / 12))) / 12;

            int XPos = element - (YPos * 12);

            System.out.print(index + ": YPos: " + YPos + " XPOS: " + XPos);

            index--;

        }

        System.out.println();

    }


    public int size(){
        return Coordinates.size();
    }

}
