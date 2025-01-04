package org.scalke.productsservice.web.controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.scalke.productsservice.config.global.GlobalConfig;
import org.scalke.productsservice.config.global.ProductServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api//config")
@RefreshScope
public class ConfigTestController {

    private GlobalConfig globalConfig;
    private ProductServiceConfig productServiceConfig;

    public ConfigTestController(GlobalConfig globalConfig, ProductServiceConfig productServiceConfig) {
        this.globalConfig = globalConfig;
        this.productServiceConfig = productServiceConfig;
    }


    @GetMapping("/global")
    public Map<String, Integer> configGlobal(){
        return Map.of("a1", globalConfig.getA1(), "a2", globalConfig.getA2());
    }

    @GetMapping("/self")
    public Map<String, Integer> configSelf(){
        return Map.of("p1", productServiceConfig.getP1(), "p2", productServiceConfig.getP2());
    }
}
