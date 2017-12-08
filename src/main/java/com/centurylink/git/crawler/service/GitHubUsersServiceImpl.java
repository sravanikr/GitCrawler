package com.centurylink.git.crawler.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import com.centurylink.git.crawler.model.GHFollower;
import com.centurylink.git.crawler.model.GHUser;

public class GitHubUsersServiceImpl implements GitHubUsersService {

	private static final String github_uri = "https://api.github.com/users/";
	private static int MAX_FRIENDS_PER_LEVEL = 5;
	List<GHUser> responselist = null;

	@Override
	public List<GHUser> GetFollowerDetails(String userId, int level) {
		responselist = new ArrayList<GHUser>();
		getFollowerList(userId, level);
		return responselist;
	}

	/**
	 * get list of followers for given user id and drill down to specified level
	 * @param userId
	 * @param level
	 */
	private void getFollowerList(String userId, int level) {
		// check level depth
		if (level > 0) {
			// get  details and follower count for count for current user
			if (getFullDetailsOfUser(userId).getFollowers() > 0) {
				Response response = getDataFromGitHub(userId + "/followers");
				List<GHFollower> followerList = response.readEntity(new GenericType<List<GHFollower>>() {
				});

				for (int i = 0; i < followerList.size() && i < 5; i++) {
					String followerId = followerList.get(i).getLogin();
					if (!isDuplicate(followerId))
						responselist.add(getFullDetailsOfUser(followerId));
					getFollowerList(followerId, --level);
				}
			}
		}

	}

	/**
	 * Check if the current follower is already part of list
	 * @param followerUserID
	 * @return
	 */
	private boolean isDuplicate(String followerUserID) {
		return responselist.stream().anyMatch(u -> u.getLogin().equalsIgnoreCase(followerUserID));
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	private GHUser getFullDetailsOfUser(String userId) {
		// read user name and other details
		Response response = getDataFromGitHub(userId);
		GHUser user = response.readEntity(GHUser.class);
		return user;

	}

	/**
	 * Util method to consume rest api
	 * 
	 * @param path
	 * @return
	 */
	private Response getDataFromGitHub(String path) {
		ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
		WebTarget service = client.target(UriBuilder.fromUri(github_uri).build());
		return service.path(path).request(MediaType.APPLICATION_JSON).get();
	}
}
