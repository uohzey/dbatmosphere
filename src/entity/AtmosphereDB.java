package entity;

public class AtmosphereDB {
	private int id;
	private String year;
	private String month;
	private String date;
	private String time;
	private String air_temperature;
	private String dewpoint_temperature;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getAir_temperature() {
		return air_temperature;
	}
	public void setAir_temperature(String air_temperature) {
		this.air_temperature = air_temperature;
	}
	public String getDewpoint_temperature() {
		return dewpoint_temperature;
	}
	public void setDewpoint_temperature(String dewpoint_temperature) {
		this.dewpoint_temperature = dewpoint_temperature;
	}
}
