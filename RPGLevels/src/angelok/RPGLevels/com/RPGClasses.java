package angelok.RPGLevels.com;


public class RPGClasses {
	
	private String info;
	private String item;
	private double defaultheal;
	private double changehealtolvl;
	private double defaultmana;
	private double changemanatolvl;
	private double manapersecond;
	
	public RPGClasses(String info, String item, double defaultheal, double changehealtolvl, double defaultmana,
			double changemanatolvl, double manapersecond){
		
		this.changehealtolvl = changehealtolvl;
		this.changemanatolvl = changemanatolvl;
		this.defaultheal = defaultheal;
		this.defaultmana = defaultmana;
		this.info = info;
		this.item = item;
		this.manapersecond = manapersecond;
		
	}
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getDefaultheal() {
		return defaultheal;
	}
	public void setDefaultheal(double defaultheal) {
		this.defaultheal = defaultheal;
	}
	public double getChangehealtolvl() {
		return changehealtolvl;
	}
	public void setChangehealtolvl(double changehealtolvl) {
		this.changehealtolvl = changehealtolvl;
	}
	public double getDefaultmana() {
		return defaultmana;
	}
	public void setDefaultmana(double defaultmana) {
		this.defaultmana = defaultmana;
	}
	public double getChangemanatolvl() {
		return changemanatolvl;
	}
	public void setChangemanatolvl(double changemanatolvl) {
		this.changemanatolvl = changemanatolvl;
	}
	public double getManapersecond() {
		return manapersecond;
	}
	public void setManapersecond(double manapersecond) {
		this.manapersecond = manapersecond;
	}

}
