/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import characters.DameStresse;
import characters.CowBoy;

/**
 *
 * @author pc
 */
public interface HorsLaLoi {
    void seFaireEmprisonner(CowBoy cowboy);
    void kidnapperDame(DameStresse dame);
    int getRecompense();
    String quelEstTonNom();
}
