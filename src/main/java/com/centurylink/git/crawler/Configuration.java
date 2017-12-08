package com.centurylink.git.crawler;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Configuration extends io.dropwizard.Configuration {

	@NotEmpty
    @JsonProperty
    private String appname;

    @NotEmpty
    @JsonProperty
    private String defaultName = "GitHub Crawler";


}
