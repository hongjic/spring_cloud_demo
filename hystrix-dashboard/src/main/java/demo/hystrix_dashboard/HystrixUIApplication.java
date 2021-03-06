package demo.hystrix_dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class HystrixUIApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixUIApplication.class, args);
    }
}
