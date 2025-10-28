package characters;

import characters.CowBoy;
import characters.Brigand;
import interfaces.HorsLaLoi;

public class DameBrigand extends DameStresse implements HorsLaLoi {

    private int nbDamesEnlevees;
    private int recompense;
    private String look;
    private boolean estEnPrison;

    // Constructor
    public DameBrigand(String nom, String boissonFavorite, String etat, String couleurRobe,
                       int nbDamesEnlevees, int recompense, String look, boolean estEnPrison) {
        super(nom, boissonFavorite, etat, couleurRobe);
        this.nbDamesEnlevees = nbDamesEnlevees;
        this.recompense = recompense;
        this.look = look;
        this.estEnPrison = estEnPrison;
    }
    
    public void echapper() {
    if (estEnPrison) {
        estEnPrison = false;
        System.out.println("DameBrigand " + super.quelEstTonNom() + " has escaped from prison!");
    } else {
        System.out.println("DameBrigand " + super.quelEstTonNom() + " is already free.");
    }
}

    // Implement HorsLaLoi methods
    @Override
    public void seFaireEmprisonner(CowBoy cowboy) {
        estEnPrison = true;
        System.out.println("DameBrigand " + super.quelEstTonNom() 
                           + " has been imprisoned by " + cowboy.quelEstTonNom() + "!");
    }

    @Override
    public void kidnapperDame(DameStresse dame) {
        if (!estEnPrison) {
            nbDamesEnlevees++;
            System.out.println("DameBrigand " + super.quelEstTonNom() 
                               + " kidnaps lady " + dame.quelEstTonNom() + "!");
            dame.seFaireEnlever(new Brigand("Unknown accomplice", "Rum", 0, 0, "masked", false));
        } else {
            System.out.println("DameBrigand " + super.quelEstTonNom() + " cannot kidnap while in prison!");
        }
    }

    @Override
    public int getRecompense() {
        return recompense;
    }

    @Override
    public String quelEstTonNom() {
        return super.quelEstTonNom();
    }

    @Override
    public void sePresenter() {
        String status = estEnPrison ? "in prison" : "free";
        System.out.println("I’m DameBrigand " + super.quelEstTonNom() + " (" + look + "), bounty $" 
                           + recompense + ", kidnapped " + nbDamesEnlevees + " ladies, currently " + status + ".");
    }

    // Optional getters/setters if needed
    public int getNbDamesEnlevees() { return nbDamesEnlevees;}
}