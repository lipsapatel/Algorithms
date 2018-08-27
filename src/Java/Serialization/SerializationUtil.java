package Java.Serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 */
public class SerializationUtil {

    /**
     * Deserialize
     * @param filename
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deserialize(String filename) throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object obj = ois.readObject();
        ois.close();
        fis.close();
        return obj;

    }
}
