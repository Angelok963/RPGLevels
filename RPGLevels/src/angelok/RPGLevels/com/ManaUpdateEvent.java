package angelok.RPGLevels.com;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class ManaUpdateEvent extends Event implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private double maxMana;
	private double pmana;
	private double manaPerSecond;
	private Player player;
	private boolean cancelled;
	private double manaResult;

	protected ManaUpdateEvent(double maxMana, double pmana, double manaPerSecond, Player player) {
		this.maxMana = maxMana;
		this.pmana = pmana;
		this.manaPerSecond = manaPerSecond;
		this.player = player;
		this.cancelled = false;
		
		if ((pmana + manaPerSecond) >= maxMana)
			this.manaResult =  maxMana;
		else
			this.manaResult = pmana + manaPerSecond;

	}
	
	
	/**
	 * @return Возвращает количество маны, начисляемой каждую секунду игроку
	 */
	public double getManaPerSecond() {

		return this.manaPerSecond;

	}
	
	/**
	 * @return Возвращает лимит маны игрока
	 */
	public double getPlayerMaxMana() {

		return this.maxMana;

	}
	
	/**
	 * @return Возвращает игрока, которому планируется пополнение маны
	 */
	public Player getPlayer() {

		return this.player;

	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * @return Возвращает количество маны игрока до начала ивента
	 */
	public double getFirstMana() {
		return this.pmana;
	}

	
	/**
	 * @return Возвращает количество маны игрока, которое будет установлено, если ивент не будет отменён
	 */
	public double getSecondMana() {

		return this.manaResult;

	}
	/**
	 * @param SecondMana Устанавливает количество маны игрока, которое будет установлено, если ивент не будет отменён
	 */
	public void setSecondMana(double SecondMana) {

		this.manaResult = SecondMana;
	}
	/**
	 * @return Возвращает true если ивент отменён, иначе false
	 */
	@Override
	public boolean isCancelled() {

		return this.cancelled;
	}

	/**
	 * @param cancelled Если true - ивент будет отменён
	 */
	@Override
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;

	}

}