package angelok.RPGLevels.com;


public class RPGClasses {
	
	private String info;
	private String item;
	private double defaultheal;
	private double changehealtolvl;
	private double defaultmana;
	private double changemanatolvl;
	private double manapersecond;
	
	protected RPGClasses(String info, String item, double defaultheal, double changehealtolvl, double defaultmana,
			double changemanatolvl, double manapersecond){
		
		this.changehealtolvl = changehealtolvl;
		this.changemanatolvl = changemanatolvl;
		this.defaultheal = defaultheal;
		this.defaultmana = defaultmana;
		this.info = info;
		this.item = item;
		this.manapersecond = manapersecond;
		
	}
	
	protected String getInfo() {
		return info;
	}
	protected void setInfo(String info) {
		this.info = info;
	}
	protected String getItem() {
		return item;
	}
	protected void setItem(String item) {
		this.item = item;
	}
	protected double getDefaultheal() {
		return defaultheal;
	}
	protected void setDefaultheal(double defaultheal) {
		this.defaultheal = defaultheal;
	}
	protected double getChangehealtolvl() {
		return changehealtolvl;
	}
	protected void setChangehealtolvl(double changehealtolvl) {
		this.changehealtolvl = changehealtolvl;
	}
	protected double getDefaultmana() {
		return defaultmana;
	}
	protected void setDefaultmana(double defaultmana) {
		this.defaultmana = defaultmana;
	}
	protected double getChangemanatolvl() {
		return changemanatolvl;
	}
	protected void setChangemanatolvl(double changemanatolvl) {
		this.changemanatolvl = changemanatolvl;
	}
	protected double getManapersecond() {
		return manapersecond;
	}
	protected void setManapersecond(double manapersecond) {
		this.manapersecond = manapersecond;
	}

}
