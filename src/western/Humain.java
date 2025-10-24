package western;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gyamfiagyemang
 */
public class Humain {
    protected String nom; //name
    protected String  boissonFavorite; //favorite drink
    
    //constructor for the class
    public Humain (String nom, String boissonFavorite){
    this.nom = nom;
    this.boissonFavorite = boissonFavorite;
    }
    
    public void parle(String message){
    System.out.println(nom + "says: " + message);
    } 
    
    //function to present
    public void sePresenter(){
    System.out.println("Hello, my name is " + nom + ". I love to drink " + boissonFavorite);
    }
    
    //getters for getting drink
    public String getBoisson(){
    return boissonFavorite;
    }
    
    //
    public String quelEstTonNom(){
        return nom;
    }
}


