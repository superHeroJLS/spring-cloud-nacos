package springcloudnacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudNacosProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudNacosProviderApplication.class, args);
    }

}
