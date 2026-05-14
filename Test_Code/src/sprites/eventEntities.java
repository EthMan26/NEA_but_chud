package sprites;

public class eventEntities {
    public int[] makeEntity(int img, int x, int y, int width, int height, int EventID){
        int[] arr;
        arr = new int[6];
        arr[0] = img;
        arr[1] = x;
        arr[2] = y;
        arr[3] = width;
        arr[4] = height;
        arr[5] = EventID;
        return arr;
    }
}
