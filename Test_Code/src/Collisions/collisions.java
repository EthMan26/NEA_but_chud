package Collisions;

import main.GamePanel;

public class collisions {
    GamePanel gp;
    public void Collide (int x, int y, int entitySize, int[][] Arr, int pointer) {
        //background collision detection
        //problems encountered were, detecting that the x was not in the right area leading to a line that you could not pass,
        // doing all the checks without finding the minimum so that the touching of the collision would lead to going to the bottom right
        //subtracting the X when it should be the Y
        //coordinate not lining up as they were not adjusted correctly
        for(int i=0;i<pointer;i++){
            if(Arr[i][6]==1){
                if(Arr[i][2]<(x+entitySize) && (Arr[i][2] + Arr[i][4])>x && Arr[i][3]<(y+entitySize) && (Arr[i][3] + Arr[i][5])>y){
                    int distanceFromLeft = (x + entitySize) - Arr[i][2];
                    int distanceFromRight = (Arr[i][2] + Arr[i][4]) - x;
                    int distanceFromTop = (y + entitySize) - Arr[i][3];
                    int distanceFromBottom = (Arr[i][3] + Arr[i][5]) - y;

                    int minDistanceFrom = Math.min(Math.min(distanceFromLeft, distanceFromRight),Math.min(distanceFromTop, distanceFromBottom));

                    if(minDistanceFrom == distanceFromLeft) {
                        x = Arr[i][2] - entitySize;
                        gp.coordsX -= distanceFromLeft;
                    } else if(minDistanceFrom == distanceFromRight) {
                        x = Arr[i][2] + Arr[i][4];
                        gp.coordsX += distanceFromRight;
                    } else if(minDistanceFrom == distanceFromTop) {
                        y = Arr[i][3] - entitySize;
                        gp.coordsY += distanceFromTop;
                    } else {
                        y = Arr[i][3] + Arr[i][5];
                        gp.coordsY -= distanceFromBottom;
                    }
                }
            }
        }
    }
}
