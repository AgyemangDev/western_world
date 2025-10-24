package western;

public class DameBrigand extends DameStresse {

    private int nbDamesEnlevees;  
    private int recompense;   
    private String look;
    private boolean estEnPrison;

    // Constructor
    public DameBrigand(String nom, String boissonFavorite, String etat, String coleurRobe,
                       int nbDamesEnlevees, int recompense, String look, boolean estEnPrison) {
        super(nom, boissonFavorite, etat, coleurRobe);
        this.nbDamesEnlevees = nbDamesEnlevees;
        this.recompense = recompense;
        this.look = look;
        this.estEnPrison = estEnPrison;
    }

    // Getter for bounty
    public int getRecompense() {
        return recompense;
    }

    // Getter for look
    public String getLook() {
        return look;
    }

    // Getter for prison state
    public boolean isEnPrison() {
        return estEnPrison;
    }

    // Kidnap another lady
    public void kidnapperDame(DameStresse dame) {
        if (!estEnPrison) {
            nbDamesEnlevees++;
            System.out.println("💋 Dame Brigand " + nom + " kidnaps lady " 
                               + dame.quelEstTonNom() + " with charm and cunning!");
        } else {
            System.out.println("Dame Brigand " + nom + " can’t kidnap anyone while in jail!");
        }
    }

    // Get imprisoned
    public void seFaireEmprisonner(Sherif sheriff) {
        estEnPrison = true;
        System.out.println("🚔 Dame Brigand " + nom + " has been captured by Sheriff " 
                           + sheriff.quelEstTonNom() + "! The town celebrates!");
    }

    // Escape from prison
    public void echapper() {
        if (estEnPrison) {
            estEnPrison = false;
            System.out.println("🕶️ Dame Brigand " + nom + " escapes from prison using her wit and charm!");
        } else {
            System.out.println("Dame Brigand " + nom + " is already free and plotting her next move!");
        }
    }

    // Override introduction
    @Override
    public void sePresenter() {
        String status = estEnPrison ? "currently in prison 🔒" : "on the loose 💃";
        System.out.println("I’m Dame Brigand " + nom + " (" + look + "), with a bounty of $" 
                           + recompense + ". I've kidnapped " + nbDamesEnlevees 
                           + " lady(ies) and I’m " + status + ".");
    }
}
