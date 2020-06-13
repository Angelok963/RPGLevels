package angelok.RPGLevels.com;

public class RPGPlayer {

	private int lvl;
	private double mana;
	private int exp;
	private int skills;
	private String pclass;
	private double heal;
	private double lastheal;
	
	protected RPGPlayer(int lvl, double mana, int exp, int skills, String pclass, double heal, double lastheal){
		this.exp = exp;
		this.heal = heal;
		this.lastheal = lastheal;
		this.lvl = lvl;
		this.mana = mana;
		this.pclass = pclass;
		this.skills = skills;
	}
	
	protected double getHeal() {
		return heal;
	}
	protected void setHeal(double heal) {
		this.heal = heal;
	}
	protected int getLvl() {
		return lvl;
	}
	protected void setLvl(int lvl) {
		this.lvl = lvl;
	}
	protected int getExp() {
		return exp;
	}
	protected void setExp(int exp) {
		this.exp = exp;
	}
	protected double getMana() {
		return mana;
	}
	protected void setMana(double mana) {
		this.mana = mana;
	}
	protected int getSkills() {
		return skills;
	}
	protected void setSkills(int skills) {
		this.skills = skills;
	}
	protected double getLastheal() {
		return lastheal;
	}
	protected void setLastheal(double lastheal) {
		this.lastheal = lastheal;
	}
	protected String getPclass() {
		
		if(DataManager.getClasses().contains(pclass))
		return pclass;
		else return "";
	}
	protected void setPclass(String pclass) {
		this.pclass = pclass;
	}
	
	
}
