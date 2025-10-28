package characters;

import characters.Brigand;

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
        super(nom, boissonFavorite); // humain constructor
        this.adjectif = adjectif;
        this.popularite = popularite;
    }
    
    // Shoot at a Brigand
    public void tirer(Brigand brigand){
        System.out.println(adjectif + " cowboy " + nom + " shoots at " + brigand.quelEstTonNom());
    }
    
    // Cowboy drinks his favorite drink
    public void boire(){
        System.out.println(nom + " drinks " + boissonFavorite + " to stay refreshed!");
    }
    
    // Cowboy shoots in the air to show off
    public void tirerEnLair(){
        System.out.println(adjectif + " cowboy " + nom + " shoots in the air to show off!");
    }
    
    // Save a lady
    public void libererDame(DameStresse dame){
        System.out.println("Cowboy " + nom + " rescues " + dame.quelEstTonNom() + "!");
    }
    
    // Getter for adjectif
    public String getAdjectif() {
        return adjectif;
    }
    
    // Getter for popularite
    public int getPopularite() {
        return popularite;
    }
    
    @Override
    public void sePresenter(){
        System.out.println("I’m " + adjectif + " cowboy " + nom + ", and I’m known for my popularity of " + popularite + "!");
    }       
}