package org.scalke.productsservice.config.global;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "global.params")
@RefreshScope
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class GlobalConfig {
    private int a1;
    private int a2;
}
