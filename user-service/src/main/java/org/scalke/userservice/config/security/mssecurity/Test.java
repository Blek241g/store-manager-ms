package org.scalke.userservice.config.security.mssecurity;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {
    private static RsakeysConfig rsakeysConfig;

    public static void main(String[] args) throws Exception {


        byte[] bytes= Files.readAllBytes(Paths.get("user-service/pub.pem"));

        System.out.println(bytes.length);

    RsaKeysUtil.getPublicKey("user-service/pub.pem");


    byte[] bytes1= Files.readAllBytes(Paths.get("user-service/priv.pem"));

        System.out.println(bytes1.length);

    RsaKeysUtil.getPrivateKey("user-service/priv.pem");
    }
}
