package org.acme.experience;

import io.fabric8.openshift.client.server.mock.OpenShiftServer;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.kubernetes.client.OpenShiftTestServer;
import io.quarkus.test.kubernetes.client.WithOpenShiftTestServer;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

@WithOpenShiftTestServer
@QuarkusTest
public class OpenShiftClientTest {

    @OpenShiftTestServer
    private OpenShiftServer mockOpenShiftServer;

    private String pathToDeploymentConfig;

    @Test
    public void testDeploymentConfigFile() throws URISyntaxException {
        pathToDeploymentConfig = "./src/main/kubernetes/dc.yml";
        mockOpenShiftServer.getOpenshiftClient().deploymentConfigs().load(pathToDeploymentConfig).dryRun();
    }

}
