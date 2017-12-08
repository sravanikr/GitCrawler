package com.centurylink.git.crawler.service;

import java.util.List;

import com.centurylink.git.crawler.model.GHUser;

/**
 * @author Sravani
 *
 */
public interface GitHubUsersService{

	public List<GHUser> GetFollowerDetails(String userid, int level);
}
