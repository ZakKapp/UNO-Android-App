package Front_End;

public class Bound {
    float minX;
    float minY;
    float maxX;
    float maxY;
    float scale;



    public Bound(float minX, float minY, float maxX, float maxY){
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }


    public void setScale(float scale){
        this.scale = scale;
    }


}
