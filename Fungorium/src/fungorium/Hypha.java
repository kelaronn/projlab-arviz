package fungorium;

import java.util.ArrayList;
import java.util.List;

/**
 * A Hypha osztály egy gombafonalat (hifát) reprezentál, amely a gombatestből nő ki, és terjed a tektonokon.
 * Felelőssége a gombák terjedésének és a rovarok mozgásának elősegítése, valamint támpont nyújtása.
 */
public class Hypha {
    /** A szomszédos gombafonalak listája. */
    private List<Hypha> neighbours = new ArrayList<>();

    /** A gombafonalat birtokló gomba (Fungus) referencia. */
    private Fungus hostFungus;

    /** A gombafonalat meghatározó egy vagy két tekton (Tecton) tömbje. */
    private Tecton[] tectons = new Tecton[2];

    /**
     * Konstruktor, amely inicializálja a gombafonal attribútumait.
     * @param n A szomszédos gombafonalak kezdeti listája.
     * @param h A gombafonalat birtokló gomba (Fungus).
     * @param t A gombafonalat meghatározó egy vagy két tekton tömbje.
     */
    public Hypha(List<Hypha> n, Fungus h, Tecton[] t) {
        neighbours = n;
        hostFungus = h;
        tectons = t;
    }

    /**
     * Hozzáad egy szomszédos gombafonalat a neighbours listához, ha még nem szerepel benne.
     * @param h A hozzáadandó szomszédos gombafonal.
     */
    public void AddNeighbour(Hypha h) {
        // Ellenőrizzük, hogy nem szerepel-e már a szomszédok között és azonos-e a hostFungus
        if (!neighbours.contains(h) && h.GetHostFungus() == this.GetHostFungus()) {
            Tecton[] otherTectons = h.GetTectons();
            Tecton[] thisTectons = this.GetTectons();
            
            // Ellenőrizzük a közös tektonokat
            boolean hasCommonTecton = false;
            for (Tecton t1 : thisTectons) {
                if (t1 != null) {  // Null check az 1 tektonos eset miatt
                    for (Tecton t2 : otherTectons) {
                        if (t2 != null && t1 == t2) {  // Null check és egyenlőség ellenőrzés
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
            }
        }
    }

    /**
     * Eltávolít egy szomszédos gombafonalat a neighbours listából.
     * @param h Az eltávolítandó szomszédos gombafonal.
     */
    public void RemoveNeighbours(Hypha h) {
        if(h != null && h.GetHostFungus() == hostFungus && neighbours.contains(h)){
            neighbours.remove(h);
        }
    }

    /**
     * Beállítja a gombafonalat birtokló gomba (Fungus) referenciát.
     * @param f A beállítandó gomba referencia.
     */
    public void SetHostFungus(Fungus f) {
        if(f != null && f != hostFungus){
            hostFungus = f;
        }
    }

    /**
     * Visszaadja a gombafonalat birtokló gomba (Fungus) referenciát.
     * @return A hostFungus attribútum értéke.
     */
    public Fungus GetHostFungus() { return hostFungus; }

    /**
     * Visszaadja a gombafonalat meghatározó egy vagy két tekton tömbjét.
     * @return A tectons tömb referenciája.
     */
    public Tecton[] GetTectons() { return tectons; }

    /**
     * Visszaadja a hifa szomszédjainak listáját.
     * @return A tectons tömb referenciája.
     */
    public List<Hypha> GetNeighbours() { return neighbours; }
    
    /**
    * Megnézi, hogy van-e élő gombatest az azonos fajhoz tartozó, szomszédos gombafonalak hálózatában,
    * és ha nincs, akkor az összes bejárt gombafonal elhal.
    */
    public void Atrophy() {
       // Halmazok a bejárt Hypha-k és az esetleges törlendők nyilvántartására
       java.util.Set<Hypha> visited = new java.util.HashSet<>();
       java.util.Set<Hypha> hyphaeToCheck = new java.util.HashSet<>();
       
       // Ellenőrizzük a hálózatot, és gyűjtjük a potenciálisan törlendő Hypha-kat
       boolean hasLivingFungusBody = checkNetworkForLivingFungusBody(this, visited, hyphaeToCheck);
   
       // Ha nincs élő gombatest, töröljük az összes bejárt Hypha-t
       if (!hasLivingFungusBody) {
           for (Hypha h : hyphaeToCheck) {
               for (Tecton t : h.GetTectons()) {
                   if (t != null) {
                       t.RemoveHypha(h);
                   }
               }
           }
       }
       // Ha van élő gombatest, nem csinálunk semmit
   }
   
   /**
    * Rekurzívan ellenőrzi a szomszédos, azonos fajhoz tartozó Hypha-k hálózatát,
    * hogy van-e élő gombatest.
    * @param hypha Az aktuális gombafonal, ahonnan indulunk
    * @param visited A már meglátogatott Hypha-k halmaza
    * @param hyphaeToCheck Az összes bejárt Hypha, amelyeket törölni kell, ha nincs gombatest
    * @return Igaz, ha van élő gombatest, különben hamis
    */
    private boolean checkNetworkForLivingFungusBody(Hypha hypha, java.util.Set<Hypha> visited, java.util.Set<Hypha> hyphaeToCheck) {
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
               if (fb != null && !fb.GetIsDead() && fb.GetHostFungus() == hypha.GetHostFungus()) {
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
}