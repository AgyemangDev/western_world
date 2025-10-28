package characters;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author gyamfiagyemang
 */
public class Brigand extends Humain {
    private int nbDamesEnlevees; //number of enslaved
    private int recompense; //bounty on outlaws head
    private String look; //outlaws appearance 
    private boolean estEnPrison; //is in jail
    
    public Brigand(String nom, String boissonFavorite, int nbDamesEnlevees, int recompense, String look, boolean estEnPrison){
        super(nom, boissonFavorite);
        this.estEnPrison = false;
        this.look = look;
        this.recompense = recompense;
        this.nbDamesEnlevees = nbDamesEnlevees;
    }
    
    //method to return bounty    
    public int getRecompense() {
        return recompense;
    }
    
    //function to detect if a kidnapper has captured someone
    public void kidnapperDame(DameStresse dame){
        if(!estEnPrison){ //if brigand is not in prison
            nbDamesEnlevees++;
            System.out.println("Briggand " + nom + " kidnaps lady " + dame.quelEstTonNom() + "!");
            dame.seFaireEnlever(this);
        }else{
            System.out.println("Brigand " + nom + " cannot kidnap anyone. He's in Jail⾨");
        }
    }
    
    //get brigand is imprisoned by cowbow
    public void seFaireEmprisonner(CowBoy cowboy) {
        estEnPrison = true;
        System.out.println("Brigand🦹‍♂️ " + nom + " was captured by cowboy🤠 " + cowboy.quelEstTonNom() + "!");
    }
    
    //method: get out of prison
    public void echapper(){
        if(estEnPrison){
            estEnPrison = false;
            System.out.println("Brigand🦹‍♂️ " + nom + " has escaped from prison!!! Hunt him down ⛓️‍💥🔫" );
        }
        else{
            System.out.println("Brigand " + nom + " cannot excape. He's already within my custody.😌");
        }
    }

    // Brigand introduction
    @Override
    public void sePresenter(){
        String status = estEnPrison ? "in prison🥺😩" : "not in prison🆓";
        System.out.println("Y'all don't know me. I'm briggand " + nom + "(" + look + "). I have a bounty of " + recompense + ". I've kidnapped " + nbDamesEnlevees + " ladies. It's so fun to "
                + "hear them cry for help all the time. I'm currently " + status);
    }
    
    @Override
    public String quelEstTonNom(){
        return nom;
    }
}
