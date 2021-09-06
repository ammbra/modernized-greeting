package com.redhat.developers;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "global")
interface GlobalTagsConfig {
    String PROFILE = "profile";
     String REGION = "region";
     String COUNTRY="country";

     String region();
     String country();
}