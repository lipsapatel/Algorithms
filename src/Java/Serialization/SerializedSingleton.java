package Java.Serialization;

import Node.SinglyLinkedListNode;

import java.io.Serializable;

public class SerializedSingleton implements Serializable {

    private static final long serialVersionID = -7890030434343434L;

    private SerializedSingleton() {

    }

    private static class SingletonHelper {
        private static final SerializedSingleton instance = new SerializedSingleton();
    }

    public static SerializedSingleton getInstance() {
        return SingletonHelper.instance;
    }

    /**
     * To keep singleton pattern, add this
     * @return
     */
    protected Object readResolve() {
        return getInstance();
    }
}
