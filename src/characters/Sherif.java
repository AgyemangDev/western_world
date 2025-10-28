package characters;

// 🤠 The Sherif class represents a lawman who hunts brigands.

import characters.CowBoy;
import characters.Brigand;

// It inherits from CowBoy, which already inherits from Humain.

public class Sherif extends CowBoy {

    private int nbBrigandCoffre; // number of brigands captured

    // 🧱 Constructor for Sheriff
    public Sherif(String nom, String boissonFavorite, int popularite, String adjectif) {
        super(nom, boissonFavorite, popularite, adjectif);
        this.nbBrigandCoffre = 0; // starts with 0 brigands captured
    }

    // Method: Search for a brigand
    public void rechercher(Brigand brigand) {
        System.out.println("Sheriff " + nom + " is searching for brigand " 
                           + brigand.quelEstTonNom() + " with a reward of $" 
                
                           + brigand.getRecompense() + "!");
    }

    //Method: Capture (jail) a brigand
    public void coffrer(Brigand brigand) {
        if (!brigandIsInPrison(brigand)) {
            brigand.seFaireEmprisonner(this); // Sheriff arrests the brigand
            nbBrigandCoffre++;
            System.out.println("Sheriff " + nom + " has now jailed " 
                               + nbBrigandCoffre + " brigand(s) in total!");
        } else {
            System.out.println("Brigand " + brigand.quelEstTonNom() 
                               + " is already in prison. No action needed.");
        }
    }

    // Helper method to check if brigand is already in prison
    private boolean brigandIsInPrison(Brigand brigand) {
        return false;
    }

    //Introduce the Sheriff
    @Override
    public void sePresenter() {
        System.out.println("I’m Sheriff " + nom + ", the " + getAdjectif() 
                           + " lawman of this town. I’ve already jailed " 
                           + nbBrigandCoffre + " brigand(s)!");
    }

    //Getter for name
    @Override
    public String quelEstTonNom() {
        return nom;
    }
}

