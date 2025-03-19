package fungorium;

import java.util.ArrayList;

public class FungusBody {
    boolean isDeveloped;
    int age;
    boolean isDead;
    int sporeCount;
    int shotsLeft;
    Tecton tecton;
    Fungus hostFungus;

    public FungusBody(){
        isDeveloped=false;
        age=0;
        isDead=false;
        sporeCount=0;
        shotsLeft=4;
    }
    public void SetIsDeveloped(boolean b){isDeveloped=b;}
    public boolean GetIsDeveloped(){return isDeveloped;}
    public void SetAge(int a){age=a;}
    public int GetAge(){return age;}
    public void SetIsDead(boolean b){isDead=b;}
    public boolean GetIsDead(){return isDead;}
    public void SetSporeCount(int i){sporeCount=i;}
    public int GetSporeCount(){return sporeCount;}
    public void SetShotsLeft(int i){shotsLeft=i;}
    public int GetShotsLeft(){return shotsLeft;}
    public void SetTecton(Tecton t){tecton=t;}
    public Tecton GetTecton(){return tecton;}
    public void SetHostFungus(Fungus f){hostFungus=f;}
    public Fungus GetHostFungus(){return hostFungus;}

    public void ShootSpores(){
        ArrayList<Tecton> neighbours = new ArrayList<>(); 
        neighbours=tecton.GetNeighbours();

        for(int i=0; i<neighbours.size(); ++i) neighbours.get(i).AddSpore();
        if(isDeveloped){
            ArrayList<Tecton> nns = new ArrayList<>();
            for(int i=0; i<neighbours.size();++i){
                nns=neighbours.get(i).GetNeighbours();
                for(int j=0; j < nns.size(); ++j) nns.get(j).AddSpore();
            }
        }
        sporeCount-=4;
        shotsLeft--;
    }

    public void Die(){
        isDead=true;
    }
    public void ProduceSpore(){
        sporeCount++;
    }

}
