package com.kurui.kums.agent._entity;

import java.sql.Timestamp;

import com.kurui.kums.agent.Agent;

// Generated 2011-9-24 12:56:38 by Hibernate Tools 3.2.2.GA

/**
 * AgentHabit generated by hbm2java
 */

public class _AgentHabit extends org.apache.struts.action.ActionForm implements
		Cloneable {

	private static final long serialVersionUID = 1L;

	protected long id;
	protected Long airplaneSeat;
	protected String travelAppoint;
	protected String drink;
	protected String food;
	protected String flower;
	protected String scaredAnimal;
	protected String breedAnimal;
	protected String filmType;
	protected String sports;
	protected String oppositeSex;
	protected String homoSex;
	protected String bigWish;
	protected String religion;
	protected String amuse;
	protected String reading;
	protected String memo;
	protected Timestamp updateTime;
	protected Long status;
	protected java.util.Set agents = new java.util.HashSet(0);

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getAirplaneSeat() {
		return airplaneSeat;
	}

	public void setAirplaneSeat(Long airplaneSeat) {
		this.airplaneSeat = airplaneSeat;
	}


	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public java.util.Set getAgents() {
		return agents;
	}

	public void setAgents(java.util.Set agents) {
		this.agents = agents;
	}

	public String getTravelAppoint() {
		return travelAppoint;
	}

	public void setTravelAppoint(String travelAppoint) {
		this.travelAppoint = travelAppoint;
	}

	public String getDrink() {
		return drink;
	}

	public void setDrink(String drink) {
		this.drink = drink;
	}

	public String getFlower() {
		return flower;
	}

	public void setFlower(String flower) {
		this.flower = flower;
	}

	public String getScaredAnimal() {
		return scaredAnimal;
	}

	public void setScaredAnimal(String scaredAnimal) {
		this.scaredAnimal = scaredAnimal;
	}

	public String getBreedAnimal() {
		return breedAnimal;
	}

	public void setBreedAnimal(String breedAnimal) {
		this.breedAnimal = breedAnimal;
	}

	public String getFilmType() {
		return filmType;
	}

	public void setFilmType(String filmType) {
		this.filmType = filmType;
	}

	public String getSports() {
		return sports;
	}

	public void setSports(String sports) {
		this.sports = sports;
	}

	public String getOppositeSex() {
		return oppositeSex;
	}

	public void setOppositeSex(String oppositeSex) {
		this.oppositeSex = oppositeSex;
	}

	public String getHomoSex() {
		return homoSex;
	}

	public void setHomoSex(String homoSex) {
		this.homoSex = homoSex;
	}

	public String getBigWish() {
		return bigWish;
	}

	public void setBigWish(String bigWish) {
		this.bigWish = bigWish;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getAmuse() {
		return amuse;
	}

	public void setAmuse(String amuse) {
		this.amuse = amuse;
	}

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}

	// The following is extra code
	public Object clone() {
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	private String thisAction = "";

	public String getThisAction() {
		return thisAction;
	}

	public void setThisAction(String thisAction) {
		this.thisAction = thisAction;
	}

	private int index = 0;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	// end of extra code

}