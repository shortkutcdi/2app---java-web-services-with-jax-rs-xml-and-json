# implementing ProfileResource

## màj DatabaseClass

````java
package org.koushik.javabrains.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.koushik.javabrains.messenger.model.Message;
import org.koushik.javabrains.messenger.model.Profile;

public class DatabaseClass {
	
	private static Map<Long, Message> messages = new HashMap<>();
	private static Map<String, Profile> profiles = new HashMap<>();
	
	public static Map<Long, Message> getMessages() {
		return messages;
	}
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
}

````

## create a new service - ProfileService

````java
package org.koushik.javabrains.messenger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.koushik.javabrains.messenger.database.DatabaseClass;
import org.koushik.javabrains.messenger.model.Profile;

public class ProfileService {
	
	private Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService() {
		profiles.put("koushik", new Profile(1L, "koushik", "koushik", "Kathagal"));
	}
	
	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values()); 
	}
	
	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile removeProfile(Profile profile) {
		return profiles.remove(profile.getProfileName());
	}
}
````

## ProfileResource


````java
package org.koushik.javabrains.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.koushik.javabrains.messenger.model.Profile;
import org.koushik.javabrains.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	private ProfileService profileService = new ProfileService();
	
	@GET
	public List<Profile> getProfiles() {
		return profileService.getAllProfiles();
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam("profileName") String profileName) {
		return profileService.getProfile(profileName);
	}

	@POST
	public Profile addProfile(Profile profile) {
		return profileService.addProfile(profile);
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam("profileName") String profileName,
								Profile profile) {
		profile.setProfileName(profileName);
		return profileService.updateProfile(profile);
		
	}
	
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam("profileName") String profileName) {
		profileService.removeProfile(profileName);
	}
	
	
}
````

