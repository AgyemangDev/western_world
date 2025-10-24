package western;

public class Ripoux extends Sherif implements HorsLaLoi {

    private int nbDamesEnlevees;   // number of ladies kidnapped
    private int recompense;        // bounty on head
    private String look;           // appearance
    private boolean estEnPrison;   // prison state

    // Constructor
    public Ripoux(String nom, String boissonFavorite, int popularite, String adjectif,
                  int nbDamesEnlevees, int recompense, String look, boolean estEnPrison) {
        super(nom, boissonFavorite, popularite, adjectif);
        this.nbDamesEnlevees = nbDamesEnlevees;
        this.recompense = recompense;
        this.look = look;
        this.estEnPrison = estEnPrison;
    }

    // Implement HorsLaLoi methods

    @Override
    public void seFaireEmprisonner(CowBoy cowboy) {
        estEnPrison = true;
        System.out.println("Ripoux " + super.quelEstTonNom() 
                           + " has been imprisoned by " + cowboy.quelEstTonNom() + "!");
    }

    @Override
    public void kidnapperDame(DameStresse dame) {
        if (!estEnPrison) {
            nbDamesEnlevees++;
            System.out.println("Ripoux " + super.quelEstTonNom() 
                               + " kidnaps lady " + dame.quelEstTonNom() + "!");
            dame.seFaireEnlever(new Brigand("Unknown accomplice", "Rum", 0, 0, "masked", false));
        } else {
            System.out.println("Ripoux " + super.quelEstTonNom() + " cannot kidnap while in prison!");
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

    // Optional: introduce the Ripoux
    @Override
    public void sePresenter() {
        String status = estEnPrison ? "in prison" : "free";
        System.out.println("I’m Ripoux " + super.quelEstTonNom() + " (" + look + "), bounty $" 
                           + recompense + ", kidnapped " + nbDamesEnlevees + " ladies, currently " + status + ".");
    }
}
