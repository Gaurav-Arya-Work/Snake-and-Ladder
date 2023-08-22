import java.util.ArrayList;

public class GameQueue {

    ArrayList<String> GameQueues = new ArrayList<>();
    public GameQueue(){}

    public void enqueue(String toAdd){
        GameQueues.add(toAdd);
    }
    public String dequeue(int a){
        try {
            return GameQueues.remove(a);
        } catch (Exception e){
            return null;
        }
    }

    public int size(){
        return GameQueues.size();
    }

    public String peek(int a){
        try {
            return GameQueues.get(a);
        } catch (Exception e){
            return null;
        }
    }

    public void debugPrint(){
        for(String element: GameQueues){
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
