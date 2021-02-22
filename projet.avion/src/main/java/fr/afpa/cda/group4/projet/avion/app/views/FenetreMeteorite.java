package fr.afpa.cda.group4.projet.avion.app.views;

import static fr.afpa.cda.group4.projet.avion.app.views.Parametres.isBruitageOn;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import fr.afpa.cda.group4.projet.avion.app.audio.Bruitage;
import fr.afpa.cda.group4.projet.avion.app.controllers.MenuController;
import fr.afpa.cda.group4.projet.avion.app.dao.imp.JoueurDao;
import fr.afpa.cda.group4.projet.avion.app.dao.imp.ScoreDao;
import fr.afpa.cda.group4.projet.avion.app.modelDo.JouerDo;
import fr.afpa.cda.group4.projet.avion.app.modelDo.JoueurDo;
import fr.afpa.cda.group4.projet.avion.app.modelDo.ScoreDo;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Armement;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Joueur;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Laser;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Meteorite;
import fr.afpa.cda.group4.projet.avion.app.modelDto.MeteoriteFeu;
import fr.afpa.cda.group4.projet.avion.app.modelDto.MeteoriteGlace;
import fr.afpa.cda.group4.projet.avion.app.modelDto.MeteoriteIceberg;
import fr.afpa.cda.group4.projet.avion.app.modelDto.MeteoriteNormale;
import fr.afpa.cda.group4.projet.avion.app.modelDto.MeteoriteZigZag;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Missile;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Partie;
import fr.afpa.cda.group4.projet.avion.app.modelDto.Vaisseau;
import fr.afpa.cda.group4.projet.avion.app.properties.Constantes;

/**
 * 
 * @author 
 *
 */
public class FenetreMeteorite extends JFrame implements KeyListener, Runnable {

    private static final long serialVersionUID = 3865841710409784148L;
    private Vaisseau          vaisseau;
    private Joueur            joueur;
    private Partie            partie;
    private Long              lastMeteoriteArrivedTime;
    private Long              lastVaisseauTouchedTime;
    private JLabel            labExplosion     = null;

    /**
     * 
     * @param vaisseau
     */
    public FenetreMeteorite(Vaisseau vaisseau) {
        requestFocus();
        this.addKeyListener(new KeyInput(this));
        this.vaisseau = vaisseau;
        this.joueur = new Joueur();
        this.partie = new Partie(joueur);
        this.lastMeteoriteArrivedTime = System.currentTimeMillis();
        this.lastVaisseauTouchedTime = System.currentTimeMillis();

    }
    
    /**
     * 
     * @param vaisseau
     * @param joueur
     */
    public FenetreMeteorite(Vaisseau vaisseau, Joueur joueur) {
        requestFocus();
        this.addKeyListener(new KeyInput(this));
        this.vaisseau = vaisseau;
        this.joueur = joueur;
        this.partie = new Partie(joueur);
        this.lastMeteoriteArrivedTime = System.currentTimeMillis();
        this.lastVaisseauTouchedTime = System.currentTimeMillis();

    }

    /**
     * 
     * @param vaisseau
     * @param partie
     * @param joueur
     */
    public FenetreMeteorite(Vaisseau vaisseau, Partie partie, Joueur joueur) {
        requestFocus();
        this.addKeyListener(new KeyInput(this));
        this.vaisseau = vaisseau;
        this.joueur = joueur;
        this.partie = partie;
        this.lastMeteoriteArrivedTime = System.currentTimeMillis();
        this.lastVaisseauTouchedTime = System.currentTimeMillis();

    }
    
    /**
     * 
     * @param partie
     * @param joueur
     */
    public FenetreMeteorite(Partie partie, Joueur joueur) {
        requestFocus();
        this.addKeyListener(new KeyInput(this));
        this.joueur = joueur;
        this.partie = partie;
        this.lastMeteoriteArrivedTime = System.currentTimeMillis();
        this.lastVaisseauTouchedTime = System.currentTimeMillis();

    }
    
    /**
     * 
     * @param joueur
     */
    public FenetreMeteorite( Joueur joueur) {
        requestFocus();
        this.addKeyListener(new KeyInput(this));
        this.joueur = joueur;
        this.partie = new Partie(joueur);
        this.lastMeteoriteArrivedTime = System.currentTimeMillis();
        this.lastVaisseauTouchedTime = System.currentTimeMillis();

    }

    public Vaisseau getVaisseau() {
        return vaisseau;
    }

    public void setVaisseau(Vaisseau vaisseau) {
        this.vaisseau = vaisseau;
    }

    /**
     * @return the joueur
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * @param joueur the joueur to set
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public void run() {

        FenetreMeteorite frame = new FenetreMeteorite(this.vaisseau, joueur);
        frame.setSize(1506, 829);
        FondEtoiles fondEtoiles = new FondEtoiles();
        fondEtoiles.setLayout(null);
        fondEtoiles.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        fondEtoiles.add(frame.getJoueur().getVaisseau().getLabel());
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Musique.playSound(Constantes.getDOSSIER_SON() + "audio.wav");
        JLabel pdvVaisseau = new JLabel("Vies : " + frame.getJoueur().getVaisseau().getPointsDeVie().toString(), SwingConstants.CENTER);
        Font f = new Font("Agency FB", Font.PLAIN, 28);
        pdvVaisseau.setFont(f);
        pdvVaisseau.setForeground(Color.white);
        pdvVaisseau.setBounds(1280, 20, 200, 50);

        pdvVaisseau.setBorder(BorderFactory.createLineBorder(Color.white));
        // pdvVaisseau.setAlignmentX(CENTER_ALIGNMENT);
        fondEtoiles.add(pdvVaisseau);
        JLabel score = new JLabel("Score : " + frame.getJoueur().getScore().toString(), SwingConstants.CENTER);
        score.setFont(f);
        score.setForeground(Color.white);
        score.setBounds(20, 20, 200, 50);
        // score.setAlignmentX(CENTER_ALIGNMENT);
        score.setBorder(BorderFactory.createLineBorder(Color.white));
        fondEtoiles.add(score);
        SimpleDateFormat monFormatChrono = new SimpleDateFormat("mm:ss");
        JLabel time = new JLabel(monFormatChrono.format(0l), SwingConstants.CENTER);
        time.setFont(f);
        time.setForeground(Color.white);

        time.setBounds(650, 20, 200, 50);

        time.setBorder(BorderFactory.createLineBorder(Color.white));
        // time.setAlignmentX(CENTER_ALIGNMENT);
        fondEtoiles.add(time);
        partie.setDebutPartie(new Date());
        final Long START_TIME = System.currentTimeMillis();
        Long tempsJeu = 0l;
        ////

        frame.add(fondEtoiles);
        frame.setVisible(true);
        (new Thread(frame.getJoueur().getVaisseau())).start();

        List<Meteorite> meteoritesEnJeu = new CopyOnWriteArrayList<Meteorite>();

        ///////// AJOUT //////////////
        List<Armement> laserEnJeu = new CopyOnWriteArrayList<Armement>();
        //		Armement laser = null;
        Armement missile = null;
        ///////////////////////////////

        Random ran = new Random();
        while (frame.getJoueur().getVaisseau().getPointsDeVie() > 0) {

            fondEtoiles.repaint();
            pdvVaisseau.setText("Vies : " + frame.getJoueur().getVaisseau().getPointsDeVie().toString());
            score.setText("Score : " + frame.getJoueur().getScore().toString());
            tempsJeu = System.currentTimeMillis() - START_TIME;
            time.setText(monFormatChrono.format(tempsJeu));
            ////

            ///////// AJOUT //////////////
            if (frame.getJoueur().getVaisseau().isTirerLaser()) {
                missile = new Laser();
                laserEnJeu.add(missile);
                fondEtoiles.add(missile.getLabel());
                missile.setPosX(frame.getJoueur().getVaisseau().getPosX());
                missile.setPosY(frame.getJoueur().getVaisseau().getPosY());

                (new Thread(missile)).start();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (frame.getJoueur().getVaisseau().isTirerMissile()) {
                missile = new Missile();
                laserEnJeu.add(missile);
                fondEtoiles.add(missile.getLabel());
                missile.setPosX(frame.getJoueur().getVaisseau().getPosX());
                missile.setPosY(frame.getJoueur().getVaisseau().getPosY());

                (new Thread(missile)).start();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (frame.getJoueur().getVaisseau().isTirerRafale()) {
                Missile missile1 = new Missile();
                Missile missile2 = new Missile();
                Missile missile3 = new Missile();
                laserEnJeu.add(missile1);
                laserEnJeu.add(missile2);
                laserEnJeu.add(missile3);
                fondEtoiles.add(missile1.getLabel());
                fondEtoiles.add(missile2.getLabel());
                fondEtoiles.add(missile3.getLabel());
                missile1.setPosX(frame.getJoueur().getVaisseau().getPosX());
                missile1.setPosY(frame.getJoueur().getVaisseau().getPosY());
                missile2.setPosX(frame.getJoueur().getVaisseau().getPosX() + 50);
                missile2.setPosY(frame.getJoueur().getVaisseau().getPosY());
                missile3.setPosX(frame.getJoueur().getVaisseau().getPosX() + 100);
                missile3.setPosY(frame.getJoueur().getVaisseau().getPosY());
                missile1.setDegre(-1);
                missile2.setDegre(0);
                missile3.setDegre(+1);
                missile1.setRafale(true);
                missile2.setRafale(true);
                missile3.setRafale(true);
                (new Thread(missile1)).start();
                (new Thread(missile2)).start();
                (new Thread(missile3)).start();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ///////////////////////////////

            if (meteoritesEnJeu.size() < 5 && System.currentTimeMillis() - lastMeteoriteArrivedTime > 500) {
                Integer numMeteorite = ran.nextInt(5);
                Meteorite met = null;
                switch (numMeteorite) {
                    case 0 :
                        met = new MeteoriteNormale();
                        break;
                    case 1 :
                        met = new MeteoriteFeu();
                        break;

                    case 2 :
                        met = new MeteoriteGlace();
                        break;

                    case 3 :
                        met = new MeteoriteZigZag();
                        break;
                    case 4 :
                        met = new MeteoriteIceberg();
                        break;
                }

                if (met != null) {
                    meteoritesEnJeu.add(met);
                    fondEtoiles.add(met.getLabel());
                    (new Thread(met)).start();
                }
                lastMeteoriteArrivedTime = System.currentTimeMillis();

            }
            for (int i = 0; i < meteoritesEnJeu.size(); i++) {
                if (meteoritesEnJeu.get(i).getMorte() == true) {
                    fondEtoiles.remove(meteoritesEnJeu.get(i).getLabel());
                    meteoritesEnJeu.remove(i);

                }
            }

            if (System.currentTimeMillis() - lastVaisseauTouchedTime > 1100 && labExplosion != null) {
                fondEtoiles.remove(labExplosion);
                labExplosion = null;
            }

            for (Meteorite meteorite : meteoritesEnJeu) {
                if (!(frame.getJoueur().getVaisseau().getPosX() > meteorite.getPosX() + meteorite.getTailleX() || frame.getJoueur().getVaisseau().getPosY() > meteorite.getPosY() + meteorite.getTailleY()
                                || frame.getJoueur().getVaisseau().getPosX() + frame.getJoueur().getVaisseau().getTailleX() < meteorite.getPosX()
                                || frame.getJoueur().getVaisseau().getPosY() + frame.getJoueur().getVaisseau().getTailleY() < meteorite.getPosY()) && System.currentTimeMillis() - lastVaisseauTouchedTime > 1200) {
                    lastVaisseauTouchedTime = System.currentTimeMillis();
                    frame.getJoueur().getVaisseau().setPointsDeVie(frame.getJoueur().getVaisseau().getPointsDeVie() - meteorite.getPointsDegats());
                    File img = new File(Constantes.getDOSSIER_IMAGES() + "Explosion/source.gif");
                    if (isBruitageOn) {
                        Bruitage.playSound("resources/explosionVaisseau.wav");
                    }
                    ImageIcon icon = new ImageIcon(new ImageIcon(img.getAbsolutePath().toString()).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
                    labExplosion = new JLabel(icon);
                    labExplosion.setBounds((frame.getJoueur().getVaisseau().getPosX() + meteorite.getPosX()) / 2 - 30, (frame.getJoueur().getVaisseau().getPosY() + meteorite.getPosY()) / 2 - 30, 150, 150);
                    labExplosion.setVisible(true);

                    fondEtoiles.add(labExplosion);
                    Integer num = fondEtoiles.getComponentCount();
                    fondEtoiles.setComponentZOrder(frame.getJoueur().getVaisseau().getLabel(), num - 1);
                    fondEtoiles.setComponentZOrder(labExplosion, 0);
                    meteorite.setPointsDegats(0);
                    //					try {
                    //						Thread.sleep(1200);
                    //					} catch (InterruptedException e) {
                    //						// TODO Auto-generated catch block
                    //						e.printStackTrace();
                    //					}
                    // fondEtoiles.remove(lab);

                }
            }

            /////////// AJOUT DES ACTION DES MISSILE//////////

            for (Armement armement : laserEnJeu) {
                for (Meteorite meteorite : meteoritesEnJeu) {
                    if (!(armement.getPosX() > meteorite.getPosX() + meteorite.getTailleX() || armement.getPosY() > meteorite.getPosY() + meteorite.getTailleY()
                                    || armement.getPosX() + armement.getTailleX() < meteorite.getPosX() || armement.getPosY() + armement.getTailleY() < meteorite.getPosY())) {

                        meteorite.setPointsDeVie(meteorite.getPointsDeVie() - armement.getPointsDegats());
                        System.out.println("Météorite Touché ! il lui reste : " + meteorite.getPointsDeVie() + " point de vie !");
                        if (isBruitageOn) {
                            Bruitage.playSound("resources/explosionMeteorite.wav");
                        }
                        if (meteorite.getPointsDeVie() <= 0) {
                            File img = new File(Constantes.getDOSSIER_IMAGES() + "Explosion/source.gif");
                            ImageIcon icon = new ImageIcon(new ImageIcon(img.getAbsolutePath().toString()).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
                            JLabel lab = new JLabel(icon);
                            lab.setBounds((armement.getPosX() + meteorite.getPosX()) / 2 - 30, (armement.getPosY() + meteorite.getPosY()) / 2 - 30, 150, 150);
                            lab.setVisible(true);
                            fondEtoiles.add(lab);
                            Integer num = fondEtoiles.getComponentCount();
                            //fondEtoiles.setComponentZOrder(armement.getLabel(), num - 1);
                            fondEtoiles.setComponentZOrder(lab, 0);
                            frame.getJoueur().setScore(frame.getJoueur().getScore() + meteorite.getPointsGain());
                            fondEtoiles.remove(meteorite.getLabel());
                            meteoritesEnJeu.remove(meteorite);
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }

                            fondEtoiles.remove(lab);
                        }
                        fondEtoiles.remove(armement.getLabel());
                        laserEnJeu.remove(armement);

                    }
                    for (Armement reste : laserEnJeu) {

                        if (reste.getPosY() <= 0) {
                            laserEnJeu.remove(reste);
                            fondEtoiles.remove(reste.getLabel());
                        }
                    }

                }

            }

        }

        System.out.println("Game over");
        System.out.println(joueur.getNom() + " : " + (new Date().toString() + " - " + frame.getJoueur().getScore()));
        JoueurDo joueurDo = new JoueurDo(joueur.getId(), joueur.getNom(), joueur.getPassword());
        
        ScoreDao scoreDao =new ScoreDao();   
        ScoreDo scoreDo = scoreDao.findIdByScore(joueur.getScore());
        if (scoreDo==null) {
        	scoreDo=new ScoreDo(joueur.getScore());
        	scoreDao.createScore(scoreDo);
            scoreDo = scoreDao.findIdByScore(joueur.getScore());
        }

        JouerDo jouerDo = new JouerDo(joueur.getId(), joueurDo, scoreDo);
        JoueurDao joueurDao = new JoueurDao();
        joueurDao.insererJouer(jouerDo);
        MenuController.ecranFinJeuSolo(new PannelFinJeuSolo(frame.getJoueur()), frame);

        ////

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            // System.out.println("bouton droit pressé");
            this.getJoueur().getVaisseau().verifPos(this.getJoueur().getVaisseau().getPosX(), this.getJoueur().getVaisseau().getPosY());
            if (this.getJoueur().getVaisseau().isDeplacementDroite()) {
                this.getJoueur().getVaisseau().setPosX(this.getJoueur().getVaisseau().getPosX() + this.getJoueur().getVaisseau().getVitesseX());
            	ImageIcon icon = new ImageIcon(new ImageIcon(this.getJoueur().getVaisseau().getImageRotateRight().getAbsolutePath().toString()).getImage());
                this.getJoueur().getVaisseau().getLabel().setIcon(icon);
                this.getJoueur().getVaisseau().verifPos(this.getJoueur().getVaisseau().getPosX(), this.getJoueur().getVaisseau().getPosY());
            }

        } else if (key == KeyEvent.VK_LEFT) {
            // System.out.println("bouton gauche pressé");
            this.getJoueur().getVaisseau().verifPos(this.getJoueur().getVaisseau().getPosX(), this.getJoueur().getVaisseau().getPosY());
            if (this.getJoueur().getVaisseau().isDeplacementGauche()) {
                this.getJoueur().getVaisseau().setPosX(this.getJoueur().getVaisseau().getPosX() - this.getJoueur().getVaisseau().getVitesseX());
            	ImageIcon icon = new ImageIcon(new ImageIcon(this.getJoueur().getVaisseau().getImageRotateLeft().getAbsolutePath().toString()).getImage());           
                this.getJoueur().getVaisseau().getLabel().setIcon(icon);
                this.getJoueur().getVaisseau().verifPos(this.getJoueur().getVaisseau().getPosX(), this.getJoueur().getVaisseau().getPosY());
            }

        } else if (key == KeyEvent.VK_UP) {
            // System.out.println("bouton haut pressé");
            this.getJoueur().getVaisseau().verifPos(this.getJoueur().getVaisseau().getPosX(), this.getJoueur().getVaisseau().getPosY());
            if (this.getJoueur().getVaisseau().isDeplacementHaut()) {
                this.getJoueur().getVaisseau().setPosY(this.getJoueur().getVaisseau().getPosY() - this.getJoueur().getVaisseau().getVitesseY());
                this.getJoueur().getVaisseau().verifPos(this.getJoueur().getVaisseau().getPosX(), this.getJoueur().getVaisseau().getPosY());
            }

        } else if (key == KeyEvent.VK_DOWN) { 
            // System.out.println("bouton haut pressé");
        	this.getJoueur().getVaisseau().verifPos(this.getJoueur().getVaisseau().getPosX(), this.getJoueur().getVaisseau().getPosY());
            if (this.getJoueur().getVaisseau().isDeplacementBas()) {
                this.getJoueur().getVaisseau().setPosY(this.getJoueur().getVaisseau().getPosY() + this.getJoueur().getVaisseau().getVitesseY());
                this.getJoueur().getVaisseau().verifPos(this.getJoueur().getVaisseau().getPosX(), this.getJoueur().getVaisseau().getPosY());
            }

        }

        //////////// AJOUT DES MISSICILES /////////////////
        else if (key == KeyEvent.VK_SPACE) {
            // System.out.println("bouton espace préssé");
            this.getJoueur().getVaisseau().setTirerLaser(true);
            if (isBruitageOn) {
                Bruitage.playSound("resources/laser.wav");
            }
        } else if (key == KeyEvent.VK_Q) {
            // System.out.println("bouton Q préssé");
            this.getJoueur().getVaisseau().setTirerMissile(true);
            if (isBruitageOn) {
                Bruitage.playSound("resources/missile.wav");
            }
        } else if (key == KeyEvent.VK_F) {
            // System.out.println("bouton F préssé");
            this.getJoueur().getVaisseau().setTirerRafale(true);
            if (isBruitageOn) {
                Bruitage.playSound("resources/missile.wav");
            }
        }
        ////////////////////////////////////////////////////

    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT) {
            // System.out.println("bouton gauche relaché");
        	ImageIcon icon = new ImageIcon(new ImageIcon(this.getJoueur().getVaisseau().getImage().getAbsolutePath().toString()).getImage()
    				.getScaledInstance(this.getJoueur().getVaisseau().getTailleX(), this.getJoueur().getVaisseau().getTailleY(), Image.SCALE_DEFAULT));
            this.getJoueur().getVaisseau().getLabel().setIcon(icon);

        }
        if (key == KeyEvent.VK_LEFT) {
            // System.out.println("bouton gauche relaché");
        	ImageIcon icon = new ImageIcon(new ImageIcon(this.getJoueur().getVaisseau().getImage().getAbsolutePath().toString()).getImage()
    				.getScaledInstance(this.getJoueur().getVaisseau().getTailleX(), this.getJoueur().getVaisseau().getTailleY(), Image.SCALE_DEFAULT));
            this.getJoueur().getVaisseau().getLabel().setIcon(icon);
        }
        if (key == KeyEvent.VK_UP) {
            // System.out.println("bouton haut relaché");
        }
        if (key == KeyEvent.VK_DOWN) {
            // System.out.println("bouton haut relaché");
        }

        //////////// AJOUT DES MISSICILES /////////////////
        if (key == KeyEvent.VK_SPACE) {
            // System.out.println("bouton espace relaché");
            this.getJoueur().getVaisseau().setTirerLaser(false);
        }
        if (key == KeyEvent.VK_Q) {
            // System.out.println("bouton Q relaché");
            this.getJoueur().getVaisseau().setTirerMissile(false);
        }
        if (key == KeyEvent.VK_F) {
            // System.out.println("bouton F relaché");
            this.getJoueur().getVaisseau().setTirerRafale(false);
        }
        ////////////////////////////////////////////////////

    }

}
