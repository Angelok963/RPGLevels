package angelok.RPGLevels.com;

public class RPGPlayer {

	private int lvl;
	private double mana;
	private int exp;
	private int skills;
	private String pclass;
	private double heal;
	private double lastheal;
	
	public RPGPlayer(int lvl, double mana, int exp, int skills, String pclass, double heal, double lastheal){
		this.exp = exp;
		this.heal = heal;
		this.lastheal = lastheal;
		this.lvl = lvl;
		this.mana = mana;
		this.pclass = pclass;
		this.skills = skills;
	}
	
	public double getHeal() {
		return heal;
	}
	public void setHeal(double heal) {
		this.heal = heal;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public double getMana() {
		return mana;
	}
	public void setMana(double mana) {
		this.mana = mana;
	}
	public int getSkills() {
		return skills;
	}
	public void setSkills(int skills) {
		this.skills = skills;
	}
	public double getLastheal() {
		return lastheal;
	}
	public void setLastheal(double lastheal) {
		this.lastheal = lastheal;
	}
	public String getPclass() {
		
		if(RPGLevels.rpgclass.keySet().contains(pclass))
		return pclass;
		else return "";
	}
	public void setPclass(String pclass) {
		this.pclass = pclass;
	}
	
	
}
