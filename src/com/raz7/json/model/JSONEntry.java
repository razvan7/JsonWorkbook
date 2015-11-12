package com.raz7.json.model;

/**
 * Class JSONEntry. POJO class used for deserialization of the JSON.
 */
public class JSONEntry {

	/**
	 * The id of the entry.
	 */
	private String Id;
	/**
	 * The username of the entry.
	 */
	private String username;
	/**
	 * The finger of the entry.
	 */
	private String finger;
	/**
	 * The force of the entry.
	 */
	private String force;
	/**
	 * The timecreated of the entry.
	 */
	private String timecreated;
	/**
	 * The sent of the entry.
	 */
	private String sent;

	/**
	 * Returns the ID of the entry.
	 * 
	 * @return The ID of the entry.
	 */
	public String getId() {
		return Id;
	}

	/**
	 * Sets the ID attribute of the entry.
	 * 
	 * @param Id
	 *            The ID attribute to set.
	 */
	public void setId(String Id) {
		this.Id = Id;
	}

	/**
	 * Returns the username attribute of the entry.
	 * 
	 * @return The username attribute of the entry.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username attribute of the entry.
	 * 
	 * @param username
	 *            The username attribute to set.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns the finger attribute of the entry.
	 * 
	 * @return The finger attribute of the entry.
	 */
	public String getFinger() {
		return finger;
	}

	/**
	 * Sets the finger attribute of the entry.
	 * 
	 * @param finger
	 *            The finger attribute to set.
	 */
	public void setFinger(String finger) {
		this.finger = finger;
	}

	/**
	 * Returns the force attribute of the entry.
	 * 
	 * @return The force attribute of the entry.
	 */
	public String getForce() {
		return force;
	}

	/**
	 * Sets the force attribute of the entry.
	 * 
	 * @param force
	 *            The force attribute to set.
	 */
	public void setForce(String force) {
		this.force = force;
	}

	/**
	 * Returns the timecreated attribute of the entry.
	 * 
	 * @return The timecreated attribute of the entry.
	 */
	public String getTimecreated() {
		return timecreated;
	}

	/**
	 * Sets the timecreated attribute of the entry.
	 * 
	 * @param timecreated
	 *            The timecreated attribute to set.
	 */
	public void setTimecreated(String timecreated) {
		this.timecreated = timecreated;
	}

	/**
	 * Returns the sent attribute of the entry.
	 * 
	 * @return The sent attribute of the entry.
	 */
	public String getSent() {
		return sent;
	}

	/**
	 * Sets the sent attribute of the entry.
	 * 
	 * @param sent
	 *            The sent attribute to set.
	 */
	public void setSent(String sent) {
		this.sent = sent;
	}

}
