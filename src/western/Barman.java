package western;

/**
 * Barman - Saloon keeper
 * Serves drinks and manages the bar
 */
public class Barman extends Humain {
    
    private String nomBar;
    
    // Constructor with correct parameter order: name, drink, bar name
    public Barman(String nom, String boissonFavorite, String nomBar){
        super(nom, boissonFavorite);
        this.nomBar = nomBar;
    }
    
    // Getter for the bar name
    public String getNomBar() {
        return nomBar;
    }
    
    // The Barman serves a drink to another human
    public void sert(Humain humain) {
        System.out.println(nom + " (the barman) serves a " 
                           + humain.getBoisson() + " to " 
                           + humain.quelEstTonNom() + ".");
    }
    
    // Barman speaks. This overrides the main humain speak
    @Override 
    public void parle(String message){
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