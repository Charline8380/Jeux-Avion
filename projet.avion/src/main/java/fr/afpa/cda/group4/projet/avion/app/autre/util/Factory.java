/**
 * 
 */
package fr.afpa.cda.group4.projet.avion.app.autre.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * @author Charline
 *
 */
public class Factory {

    /**
     * Permet de retouner une instance de l'implémentation d'un interface <br>
     * L'implémetation doit implémenter une methode getInstance en static <br>
     * qui permet d'instancier la classe (l'instanciation du singleton)
     *
     * @param clazzInterface interface dont on veut l'impl�mentation
     * @return l'implémentation choisie de l'interface
     */
    public static <T> T getInstance(final Class<T> clazzInterface) {

        // on vérifie si le paramétre est une interface
        if (!clazzInterface.isInterface()) {
            throw new IllegalArgumentException("Ceci n'est pas une interface!");
        }

        // on récupére le nom de l'interface
        final String interfaceName = clazzInterface.getSimpleName();

        // on en déduit le nom de la classe d'implémentation
        final String className = clazzInterface.getPackage().getName() + ".impl." + interfaceName.substring(1);

        // on essaie de charger cette classe pour vérifier son existance
        Class<?> clazzToInstance = null;
        try {
            clazzToInstance = Class.forName(className);
        } catch (final ClassNotFoundException e1) {
            throw new IllegalArgumentException("Aucune implémentation trouvée pour cette interface! ", e1);
        }

        // on appelle la méthode getInstance() de l'implémentation 
        Object implementation = null;
        try {
            // on récupére la méthode getInstance
            final Method methodToCall = clazzToInstance.getMethod("getInstance", new Class[0]);
            // on l'invoque et on stocke son retour
            implementation = methodToCall.invoke(null);
        } catch (final SecurityException e) {
            e.printStackTrace();
            throw new RuntimeException("Problème de sécurité");
        } catch (final NoSuchMethodException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("La classe d'implémentation ne respecte pas le pattern singleton (getInstance)!");
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Accés aux invocations interdit!");
        } catch (final InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("Probléme d'invocation!");
        }

        // on retourne le résultat
        return clazzInterface.cast(implementation);
    }

}
