package demo.service_gateway;

import demo.service_gateway.filters.PreRequestAuthFilter;
import demo.service_gateway.filters.PreRequestLoggingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

/**
<@EanbleZuulProxy already contains @EnableCircuitBreaker
 */
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayServer {

    @Bean
    public AlwaysSampler alwaysSampler() {
        return new AlwaysSampler();
    }

    @Bean
    public PreRequestAuthFilter preRequestAuthFilter() {
        return new PreRequestAuthFilter();
    }

    @Bean
    public PreRequestLoggingFilter preRequestLoggingFilter() { return new PreRequestLoggingFilter(); }

    public static void main(String[] args) {
        SpringApplication.run(GatewayServer.class, args);
    }
}
