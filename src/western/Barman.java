/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package western;

/**
 *
 * @author gyamfiagyemang
 */
public class Barman extends Humain {
    
    private String nomBar;
    
    public Barman(String nom, String nomBar, String boissonFavorite){
        super (nom, boissonFavorite);
        this.nomBar = nomBar;
    }
    
     //Getter for the bar name
    public String getNomBar() {
        return nomBar;
    }
    
        // The Barman serves a drink to another human
    public void sert(Humain humain) {
        System.out.println(nom + " (the barman) serves a " 
                           + humain.getBoisson() + " to " 
                           + humain.quelEstTonNom() + ".");
    }
    
    
    //barman speaks. This overrides the main humain speak
    @Override 
    public void parle (String message){
        System.out.println("[" + nom + " the barman says]: " + message);
    }
    
      // The Barman introduces himself
    @Override
    public void sePresenter() {
        System.out.println("Hello, I'm " + nom 
                           + ", the barman of " + nomBar 
                           + ". My favorite drink is " + boissonFavorite + ".");
    }
    
}
