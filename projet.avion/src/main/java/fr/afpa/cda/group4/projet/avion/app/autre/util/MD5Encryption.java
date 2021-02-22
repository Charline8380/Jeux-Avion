package fr.afpa.cda.group4.projet.avion.app.autre.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Cette classe sert au cryptage des mots de passe.
 * 
 * @author Administrateur
 *
 */
public class MD5Encryption {

    /**
     * 
     */
    public MD5Encryption() {
        // empty method
    }

    /**
     * Permet de crypter le mot de passe.
     * 
     * @param motDePasse
     * @return 
     */
    public static String cryptage(final String motDePasse) {
        final StringBuilder stringBuilder = new StringBuilder();
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            final byte[] hashInBytes = messageDigest.digest(motDePasse.getBytes(StandardCharsets.UTF_8));

            for (final byte b : hashInBytes) {
                stringBuilder.append(String.format("%02x", b));
            }

        } catch (final NoSuchAlgorithmException algorimthmException) {
            algorimthmException.printStackTrace();
        }
        return stringBuilder.toString();

    }
}
