package org.motechproject.spike.osgi;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.launch.Framework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
            URL url = getBundleUrl();
            Bundle bundle = bundleContext.installBundle(url.toExternalForm());
            bundle.start();
            osgiFramework.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private URL getBundleUrl() throws MalformedURLException {
        String url = "https://dummy-bundle.s3.amazonaws.com/dummy-bundle-1.0.jar?AWSAccessKeyId=AKIAJKSRCIF4BJMJR4WA&Expires=1372359159&Signature=6L2mWSE0Hxa9FG3qWsmATrkJTXI%3D";
        return new URL(url);
    }
}
