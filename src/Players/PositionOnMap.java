package Players;

public class PositionOnMap {
    public int arr;
    public int tile;

    public PositionOnMap(int tile){
        this.arr=0;
        this.tile=tile;
    }

    public PositionOnMap(int arr,int tile){
        this.arr=arr;
        this.tile=tile;
    }

    public PositionOnMap(PositionOnMap pos){
        this.arr=pos.arr;
        this.tile=pos.tile;
    }

    public void setTile(int i){
        tile=i;
    }
}
