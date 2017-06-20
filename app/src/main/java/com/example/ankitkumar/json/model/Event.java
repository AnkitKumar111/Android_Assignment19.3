package com.example.ankitkumar.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Event {
	
	public Long id;
	public String type;
	public Date when;
	
	public String repo;
	public String repoUrl;
	
	public String user;
	
	/** Url to user avatar. */
	public String userAvatarUrl;
	
	/** Default Constructor. Can only create events from JSON objects.
	 * 
	 * @param src the json source to create the event.
	 */
	public Event(JSONObject src) {
		try {
			this.id = src.getLong("id");
			this.type = src.getString("type");

			// now repo information
			JSONObject repo = src.getJSONObject("repo");
			this.repo = repo.getString("name");
			this.repoUrl = repo.getString("url");
			
			// user info
			JSONObject user = src.getJSONObject("actor");
			this.user = user.getString("login");
			if (this.user == null) {
				this.user = "NOUAN"; // TODO do something with no users
				
				if ("gistevent".equals(this.type.toLowerCase(Locale.US))) { // special case
					this.user = "GIST";
				}
			}
			this.userAvatarUrl = user.getString("avatar_url");
			
		} catch (JSONException e) {
			e.printStackTrace();
		} 	
	}
	
	/** Usefull when we get more than one.
	 * 
	 * @param jsonObjects array of json objects
	 * 
	 * @return a list of popuplated events.
	 */
    public static ArrayList<Event> fromJson(JSONArray jsonObjects) {
		ArrayList<Event> result = new ArrayList<Event>();
		for (int i = 0; i < jsonObjects.length(); i++) {
			try {
				result.add(new Event(jsonObjects.getJSONObject(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
	}	
    
    @Override
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("id[").append(this.id).append("],");
    	sb.append("type[").append(this.type).append("],");
    	sb.append("repo[").append(this.repo).append("],");
    	sb.append("user[").append(this.user).append("],");
    	
    	return sb.toString();
    }
}
