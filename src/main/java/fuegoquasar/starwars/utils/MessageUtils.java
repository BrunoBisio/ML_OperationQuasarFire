package fuegoquasar.starwars.utils;

public class MessageUtils {
    
    public static void addMissingWords(String[] baseArray, String[] injectedArray){
        for (int i = baseArray.length-1, j = injectedArray.length-1; i != 0; i--, j--) {
            if (baseArray[i] == "" && injectedArray[j] != "") {
                baseArray[i] = injectedArray[j];
            }
        }
    }

    public static boolean isComplete(String[] message) {
        // Para determinar si el mensaje esta completo o no
        boolean hasContent = false;
        // Checkeo de no tener una palabra y a continuacion un espacio ["este", ""]
        for (int i = 0; i < message.length-1; i++) {
            if (!message[i].isEmpty() && message[i+1].isEmpty()) {
                return false;
            }
            if (!message[i].isEmpty()) {
                hasContent = true;
            }
        }
        // Checkeo de que la ultima palabra haya podido ser determinada
        if (hasContent && message[message.length-1].isEmpty())
            return false;
        return true;
    }
}
