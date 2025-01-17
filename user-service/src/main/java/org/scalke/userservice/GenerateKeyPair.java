package org.scalke.userservice;

import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class GenerateKeyPair {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        System.out.println("Generating key pair");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        var keypair = keyPairGenerator.generateKeyPair();
        byte[] pub = keypair.getPublic().getEncoded();
        byte[] priv = keypair.getPrivate().getEncoded();

        PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream("pub.pem")));
        PemObject pemObject = new PemObject("PUBLIC KEY", pub);
        pemWriter.writeObject(pemObject);
        pemWriter.close();

        PemWriter pemWriter2 = new PemWriter(new OutputStreamWriter(new FileOutputStream("priv.pem")));
        PemObject pemObject2 = new PemObject("PRIVATE KEY", priv);
        pemWriter2.writeObject(pemObject2);
        pemWriter2.close();
    }
}
