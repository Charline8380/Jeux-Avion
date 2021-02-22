package fr.afpa.cda.group4.projet.avion.app.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * 
 *  @author Rachel
 */
public class Musique {

    private static Clip clip;

    /**
     * Constructeur par defaut
     */
    public Musique() {
        super();
    }

    /**
     * Ajout de la musique 
     */
    public Musique(final String son) {
        try {
            AudioInputStream musique = AudioSystem.getAudioInputStream(getClass().getResource(son));
            clip = AudioSystem.getClip(null);
            clip.open(musique);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Lecture des bruitages
     */
    public static void play() {
        clip.start();
    }
    
    
    /**
     * Arrêt des bruitages
     */
    public static void stop() {
        clip.stop();
    }

    /**
     * Méthode pour lire le son
     * @param son
     */
    public static void playSound(final String son) {
        Musique musique = new Musique(son);
        musique.play();
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(final Clip clip) {
        this.clip = clip;
    }

}
