package fr.afpa.cda.group4.projet.avion.app.modelDto;

import javax.swing.JLabel;

/**
 * 
 * @author 
 *
 */
public class Armement extends ObjetMouvant implements Runnable {
	private JLabel label;
	private Integer pointsDegats;

	public Armement(Integer pointsDegats) {
		super();
		this.pointsDegats = pointsDegats;
		this.label = null;
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

	public void run() {

	}
	
	
}
