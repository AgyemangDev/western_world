package western;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gyamfiagyemang
 */
public class DameStresse extends Humain {
    private String etat; //is DameStress free or kidnapped
    private String coleurRobe; //color of robe
    
    public DameStresse(String nom, String boissonFavorite, String etat, String coleurRobe){
        super(nom, boissonFavorite);
        this.etat = etat;
        this.coleurRobe = coleurRobe;
    }
    
    //method when captured by Brigand
    public void seFaireEnlever(Brigand brigand) {
        etat = "kidnapped";
        System.out.println("Oh no! Lady " + nom + " has been kidnapped by brigand " + brigand.quelEstTonNom() + "!");
    }
     
    //method: Cowboy rescue lady
    public void seFaireLiberer(CowBoy cowboy){
        if(etat.equals("kidnapped")){
            etat = "free";
            System.out.println("Lady " + nom + " has been rescued by " + cowboy.quelEstTonNom());
        }
        else {
            System.out.println("Lady " + nom + " doesn’t need rescuing!");
        }
    }
    
        // Method: Change her dress color
    public void changerRobe(CowBoy cowboy) {
        coleurRobe = "new color"; 
        System.out.println("Lady " + nom + " changes her dress color after meeting cowboy " + cowboy.quelEstTonNom() + ".");
    }
    
    // Override: Introduce herself
    @Override
    public void sePresenter() {
        System.out.println("I’m Lady " + nom + " wearing a " + coleurRobe + " dress, and I’m currently " + etat + ".");
    }
    
    @Override 
    public String quelEstTonNom(){
        return nom;
    }

}
