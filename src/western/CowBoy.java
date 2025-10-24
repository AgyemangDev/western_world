package western;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gyamfiagyemang
 */
public class CowBoy extends Humain {
    private int popularite;
    private String adjectif;
    
    public CowBoy(String nom, String boissonFavorite, int popularite, String adjectif){
        super(nom, boissonFavorite); //humain constructor
        this.adjectif = adjectif;
        this.popularite = popularite;
    };
    
    //function to return string of cowboy shooting brigand
    public void tirer(Brigand brigand){
        System.out.println(adjectif + " cowboy " + nom + " shoots at " + brigand.quelEstTonNom());
    }
    
    //getters to get Adjectif
    public String getAdjectif() {
       return adjectif;
    }
      
    //function to print Cowboy saving Lady
    public void libererDame(DameStresse dame){
        System.out.println("Cowboy " + nom + " resuces" + dame.quelEstTonNom() + "!");
    }
    
    @Override
    public void sePresenter(){
        System.out.println("I’m " + adjectif + " cowboy " + nom + ", and I’m known for my popularity of " + popularite + "!");
    }
            
}

