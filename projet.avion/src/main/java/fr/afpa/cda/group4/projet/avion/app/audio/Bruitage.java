package fr.afpa.cda.group4.projet.avion.app.audio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * 
 * @author Rachel
 *
 */
public class Bruitage {

    private static Clip clip;

    /**
     * Constructeur par defaut
     */
    public Bruitage() {
        super();
    }

    /**
     * Ajout des bruitages dans le jeu
     */
    public Bruitage(final String son) {
        try {
            AudioInputStream musique = AudioSystem.getAudioInputStream(getClass().getResource(son));
            clip = AudioSystem.getClip(null);
            clip.open(musique);
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
        Bruitage bruitage = new Bruitage(son);
        bruitage.play();
    }

    public Clip getClip() {
        return clip;
    }

    public void setClip(final Clip clip) {
        this.clip = clip;
    }

}
