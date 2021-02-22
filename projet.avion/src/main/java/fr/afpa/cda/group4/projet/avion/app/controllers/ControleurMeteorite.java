package fr.afpa.cda.group4.projet.avion.app.controllers;

import java.util.ArrayList;
import java.util.List;

import fr.afpa.cda.group4.projet.avion.app.modelDto.Meteorite;

/**
 * 
 * @author
 *
 */
public class ControleurMeteorite {
    List<Meteorite> meteoritesEnCours = new ArrayList<Meteorite>();

    public ControleurMeteorite() {}

    /**
     * @return the meteoritesEnCours
     */
    public List<Meteorite> getMeteoritesEnCours() {
        for (final Meteorite meteorite : meteoritesEnCours) {

        }
        return meteoritesEnCours;
    }

    /**
     * @param meteoritesEnCours the meteoritesEnCours to set
     */
    public void setMeteoritesEnCours(List<Meteorite> meteoritesEnCours) {
        this.meteoritesEnCours = meteoritesEnCours;

    }

}
