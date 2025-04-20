package fungorium;

import java.util.ArrayList;
import java.util.List;

/**
 * A Hypha osztály egy gombafonalat (hifát) reprezentál, amely a gombatestből nő ki, és terjed a tektonokon.
 * Felelőssége a gombák terjedésének és a rovarok mozgásának elősegítése, valamint támpont nyújtása a gombahálózatban.
 */
public class Hypha implements IHyphaView, IHyphaController {
    /** A szomszédos gombafonalak listája. */
    private List<Hypha> neighbours = new ArrayList<>();

    /** A gombafonalat birtokló gomba (Fungus) referencia. */
    private Fungus hostFungus;

    /** A gombafonalat meghatározó egy vagy két tekton (Tecton) tömbje. */
    private List<Tecton> tectons = new ArrayList<>();

    /**
     * Konstruktor, amely inicializálja a gombafonal attribútumait.
     * @param n A szomszédos gombafonalak kezdeti listája.
     * @param h A gombafonalat birtokló gomba (Fungus).
     * @param t A gombafonalat meghatározó egy vagy két tekton tömbje.
     */
    public Hypha(List<Hypha> n, Fungus h, List<Tecton> t) {
        neighbours = n;
        hostFungus = h;
        tectons = t;
    }

    /**
     * Hozzáad egy szomszédos gombafonalat a szomszédok listájához, ha még nem szerepel benne,
     * ugyanahhoz a gombához tartozik, és van közös tektonjuk. A hozzáadás kölcsönös.
     * 
     * @param h A hozzáadandó szomszédos gombafonal.
     * @return Igaz, ha a szomszéd hozzáadása sikeres, különben hamis.
     */
    public boolean AddNeighbour(Hypha h) {
        // Ellenőrizzük, hogy nem szerepel-e már a szomszédok között és azonos-e a hostFungus
        if (!neighbours.contains(h) && h.GetHostFungus().equals(this.GetHostFungus())) {
            List<Tecton> otherTectons = h.GetTectons();
            List<Tecton> thisTectons = this.GetTectons();
            
            // Ellenőrizzük a közös tektonokat
            boolean hasCommonTecton = false;
            for (Tecton t1 : thisTectons) {
                if (t1 != null) {  // Null check az 1 tektonos eset miatt
                    for (Tecton t2 : otherTectons) {
                        if (t2 != null && t1.equals(t2)) {  // Null check és egyenlőség ellenőrzés
                            hasCommonTecton = true;
                            break;
                        }
                    }
                }
                if (hasCommonTecton) break;
            }
        
            // Csak akkor adjuk hozzá, ha van közös tekton
            if (hasCommonTecton) {
                neighbours.add(h);
                h.AddNeighbour(this);
                return true;
            }
        }
        return false;
    }

    /**
     * Eltávolít egy szomszédos gombafonalat a szomszédok listájából, ha ugyanahhoz a gombához tartozik
     * és szerepel a listában. Az eltávolítás kölcsönös.
     * 
     * @param h Az eltávolítandó szomszédos gombafonal.
     * @return Igaz, ha a szomszéd eltávolítása sikeres, különben hamis.
     */
    public boolean RemoveNeighbours(Hypha h) {
        if(h != null && h.GetHostFungus().equals(this.GetHostFungus()) && neighbours.contains(h)){
            neighbours.remove(h);
            h.RemoveNeighbours(this);
            return true;
        }
        return false;
    }

    /**
     * Beállítja a gombafonalat birtokló gomba referenciát, ha az új gomba nem null és különbözik
     * a jelenlegitől.
     * 
     * @param f A beállítandó gomba referencia.
     * @return Igaz, ha a beállítás sikeres, különben hamis.
     */
    public boolean SetHostFungus(Fungus f) {
        if(f != null && f != this.GetHostFungus()){
            hostFungus = f;
            return true;
        }
        return false;
    }

    /**
     * Visszaadja a gombafonalat birtokló gomba (Fungus) referenciát.
     * @return A hostFungus attribútum értéke.
     */
    public Fungus GetHostFungus() { return hostFungus; }

    /**
     * Visszaadja a gombafonalat meghatározó egy vagy két tekton tömbjét.
     * @return A tectons lista referenciája.
     */
    public List<Tecton> GetTectons() { return tectons; }

    /**
     * Visszaadja a hifa szomszédjainak listáját.
     * @return A tectons lista referenciája.
     */
    public List<Hypha> GetNeighbours() { return neighbours; }
    
    /**
     * Megvizsgálja, hogy van-e élő gombatest az azonos fajhoz tartozó szomszédos gombafonalak hálózatában.
     * Ha nincs, akkor az összes bejárt gombafonal elhal, és eltávolításra kerül a tektonokról.
     * 
     * @return Igaz, ha a hálózat elhalt (nincs élő gombatest vagy étető tekton), különben hamis.
     */
    public boolean Atrophy() {
       // Halmazok a bejárt Hypha-k és az esetleges törlendők nyilvántartására
       List<Hypha> visited = new ArrayList<>();
       List<Hypha> hyphaeToCheck = new ArrayList<>();
       //hyphaeToCheck = this.GetNeighbours();
       // Ellenőrizzük a hálózatot, és gyűjtjük a potenciálisan törlendő Hypha-kat
       boolean hasLivingFungusBody = checkNetworkForLivingFungusBody(this, visited, hyphaeToCheck);
       // Ha nincs élő gombatest, töröljük az összes bejárt Hypha-t
       if (!hasLivingFungusBody) {
           for (Hypha h : hyphaeToCheck) {
               for (Tecton t : h.GetTectons()) {
                   if (t != null) {
                       //t.RemoveHypha(h);
                       t.GetHyphas().remove(h);
                   }
               }
           }
           return true;
       }
       // Ha van élő gombatest, nem csinálunk semmit
       return false;
   }
   
   /**
    * Rekurzívan ellenőrzi a szomszédos, azonos fajhoz tartozó gombafonalak hálózatát,
    * hogy van-e élő gombatest vagy tápanyagot biztosító tekton.
    * 
    * @param hypha Az aktuális gombafonal, ahonnan az ellenőrzés indul.
    * @param visited A már meglátogatott gombafonalak listája.
    * @param hyphaeToCheck Az összes bejárt gombafonal listája, amelyeket törölni kell, ha nincs élő gombatest.
    * @return Igaz, ha van élő gombatest vagy tápanyagot biztosító tekton, különben hamis.
    */
    private boolean checkNetworkForLivingFungusBody(Hypha hypha, List<Hypha> visited, List<Hypha> hyphaeToCheck) {
       // Ha már meglátogattuk ezt a Hypha-t, kilépünk
       if (visited.contains(hypha)) {
           return false;
       }
       visited.add(hypha);
       hyphaeToCheck.add(hypha); // Hozzáadjuk a potenciálisan törlendőkhöz
       // Ellenőrizzük az aktuális Hypha Tecton-jain lévő gombatesteket
       for (Tecton t : hypha.GetTectons()) {
           if (t != null) {
               FungusBody fb = t.GetFungusBody();
               if ((fb != null && !fb.GetIsDead() && fb.GetHostFungus().equals(hypha.GetHostFungus())) || t.SupplyHyphas()) {
                   return true; // Találtunk élő gombatestet
               }
           }
       }
       // Rekurzívan ellenőrizzük a szomszédos Hypha-kat
       for (Hypha neighbour : hypha.GetNeighbours()) {
           if (neighbour.GetHostFungus() == hypha.GetHostFungus() && !visited.contains(neighbour)) {
               if (checkNetworkForLivingFungusBody(neighbour, visited, hyphaeToCheck)) {
                   return true;
               }
           }
       }
       // Ha nincs több szomszéd, és nem találtunk gombatestet, hamis
       return false;
    }

    /**
     * Elkábult rovar megevése, ha a rovar a gombafonal egyetlen tektonján van, él, el van kábulva,
     * és még nem ette meg más gomba. A rovar sebessége és hatása nullázódik, és a gomba megeszi.
     * 
     * @param insect A megevendő rovar.
     * @return Igaz, ha a rovar megevése sikeres, különben hamis.
     */
    public boolean EatStunnedInsect(Insect insect){
        if (this.GetTectons().size()==1 && insect.GetTecton().equals(this.GetTectons().get(0)) && insect.GetEatenBy()==null && insect.GetSpeed() == 0 && insect.GetEffectTimeLeft() > 0) {
            insect.SetEatenBy(this.GetHostFungus());
            insect.SetEffectTimeLeft(0);
            insect.SetCutAbility(false);
            insect.SetSpeed(0);
            return true;
        }
        return false;
    }

    @Override
    public String ToString(String data){
        return "Hypha,"+data;
    }
}