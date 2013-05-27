package org.motechproject.spike.osgi;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.launch.Framework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class OsgiFrameworkService {

    @Autowired
    private Framework osgiFramework;


    public void start() {
        try {
            osgiFramework.init();
            BundleContext bundleContext = osgiFramework.getBundleContext();
            URL url = getS3BundleURL();
            Bundle bundle = bundleContext.installBundle(url.toExternalForm());
            bundle.start();
            osgiFramework.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private URL getLocalBundleURL() throws MalformedURLException {
        return new File("/Users/Sohamgh/.motech/bundles/dummy/dummy-bundle-1.0.jar").toURL();
    }

    private URL getS3BundleURL() throws MalformedURLException {
        String url = "https://dummy-bundle.s3-ap-southeast-1.amazonaws.com/dummy-bundle-1.0.jar";
        return new URL(url);
    }
}
