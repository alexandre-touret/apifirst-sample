package info.touret.apifirst.sample;

import io.github.microcks.testcontainers.MicrocksContainersEnsemble;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.Network;

@TestConfiguration(proxyBeanMethods = false)
public class TestGuitarConfiguration {

    private static Network network = Network.newNetwork();


    @Bean
    MicrocksContainersEnsemble microcksEnsemble(DynamicPropertyRegistry registry) {
        // Uncomment these lines (36-38) if you want to use the native image of Microcks
        // and comment the next MicrocksContainersEnsemble declaration line (40).
//      DockerImageName nativeImage = DockerImageName.parse("quay.io/microcks/microcks-uber:1.10.0-native")
//            .asCompatibleSubstituteFor("quay.io/microcks/microcks-uber:1.9.0");
//      MicrocksContainersEnsemble ensemble = new MicrocksContainersEnsemble(network, nativeImage)

        MicrocksContainersEnsemble ensemble = new MicrocksContainersEnsemble(network, "quay.io/microcks/microcks-uber:1.10.0")
                .withPostman()             // We need this to do contract-testing with Postman collection
                .withAsyncFeature()        // We need this for async mocking and contract-testing
                .withAccessToHost(true)   // We need this to access our webapp while it runs
                .withMainArtifacts("third-parties/pastries-openapi.yaml");
        // We need to replace the default endpoints with those provided by Microcks.
        registry.add("application.pastries-base-url",
                () -> ensemble.getMicrocksContainer().getRestMockEndpoint("API Pastries", "0.0.1"));

        return ensemble;
    }
}
