package com.centurylink.git.crawler.api.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.centurylink.git.crawler.common.exception.DataNotFoundException;
import com.centurylink.git.crawler.model.GHUser;
import com.centurylink.git.crawler.service.GitHubUsersService;

/**
 * Rest Resource pertaining to followers of github user
 * @author sravani
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class FollowersResource {

	final static Logger logger = LoggerFactory.getLogger(FollowersResource.class);
	private GitHubUsersService serviceImpl;

	@Inject
	public FollowersResource(GitHubUsersService serviceImpl) {
		this.serviceImpl = serviceImpl;
	}

	/**
	 * Method to fetch the list of followers for a given github user
	 * @param userid
	 * @param level
	 * @return
	 */
	@GET
	@Path("{userid}/followers")
	public List<GHUser> getInventoryDetails(@PathParam("userid") String userid, @QueryParam("level") int level) {
		List<GHUser> info = null;
		try {
			info = serviceImpl.GetFollowerDetails(userid, level);
		} catch (DataNotFoundException e) {
			logger.error("Error while fetching book information.", e);
			// throwing down further to handle exception and send relevant response to
			// client
			throw e;
		}
		return info;
	}
}
