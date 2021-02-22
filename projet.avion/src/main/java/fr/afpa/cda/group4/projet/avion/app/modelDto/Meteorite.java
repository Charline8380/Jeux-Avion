package fr.afpa.cda.group4.projet.avion.app.modelDto;

import javax.swing.JLabel;

/**
 * 
 * @author 
 *
 */
public class Meteorite extends ObjetMouvant implements Runnable {
	private Integer pointsDeVie;
	private Integer pointsDegats;
	private Integer pointsGain;
	private JLabel label;
	private Boolean morte=false;

	public Meteorite() {

	}

	/**
	 * 
	 * @param pointsDeVie
	 * @param pointsDegats
	 * @param pointsGain
	 */
	public Meteorite(Integer pointsDeVie, Integer pointsDegats, Integer pointsGain) {
		super();
		this.pointsDeVie = pointsDeVie;
		this.pointsDegats = pointsDegats;
		this.pointsGain = pointsGain;
		this.label = null;
	}

	/**
	 * @return the pointsDeVie
	 */
	public Integer getPointsDeVie() {
		return pointsDeVie;
	}

	/**
	 * @return the label
	 */
	public JLabel getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(JLabel label) {
		this.label = label;
	}

	/**
	 * @param pointsDeVie the pointsDeVie to set
	 */
	public void setPointsDeVie(Integer pointsDeVie) {
		this.pointsDeVie = pointsDeVie;
	}

	/**
	 * @return the pointsDegats
	 */
	public Integer getPointsDegats() {
		return pointsDegats;
	}

	/**
	 * @param pointsDegats the pointsDegats to set
	 */
	public void setPointsDegats(Integer pointsDegats) {
		this.pointsDegats = pointsDegats;
	}

	/**
	 * @return the pointsGain
	 */
	public Integer getPointsGain() {
		return pointsGain;
	}

	/**
	 * @param pointsGain the pointsGain to set
	 */
	public void setPointsGain(Integer pointsGain) {
		this.pointsGain = pointsGain;
	}
	/**
	 * @return the morte
	 */
	public Boolean getMorte() {
		return morte;
	}

	/**
	 * @param morte the morte to set
	 */
	public void setMorte(Boolean morte) {
		this.morte = morte;
	}

	public void run() {

	}

}
