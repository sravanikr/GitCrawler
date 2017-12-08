package com.centurylink.git.crawler;

import com.centurylink.git.crawler.api.resources.FollowersResource;
import com.centurylink.git.crawler.api.resources.HealthCheckResource;
import com.centurylink.git.crawler.common.exception.DataNotFoundExceptionMapper;
import com.centurylink.git.crawler.service.GitHubUsersServiceImpl;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MainApp extends Application<Configuration> {

	public static void main(String[] args) throws Exception {
		new MainApp().run(args);
	}

	@Override
	public String getName() {
		return "inventory";
	}

	@Override
	public void initialize(Bootstrap<Configuration> bootstrap) {

	}

	@Override
	public void run(Configuration configuration, Environment environment) {

		// use metrics healthchecks
		environment.jersey().register(new HealthCheckResource(environment.healthChecks()));
		environment.jersey().register(new FollowersResource(new GitHubUsersServiceImpl()));

		// Register the custom ExceptionMapper(s)
		environment.jersey().register(new DataNotFoundExceptionMapper());
	}
}
