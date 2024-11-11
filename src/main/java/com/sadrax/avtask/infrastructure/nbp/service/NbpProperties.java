package com.sadrax.avtask.infrastructure.nbp.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(prefix = "avtask.nbp")
@Data
public class NbpProperties {
        private String url;
}
