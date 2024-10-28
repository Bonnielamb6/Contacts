package contacts;

import java.io.*;
import java.util.ArrayList;

public final class Serialization {
    private Serialization() {
    }

    public static void serialize(Object obj, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        try (ObjectOutput oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
        }
    }

    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            if (file.createNewFile()) {
                System.out.println("New file created: " + fileName);
                return new ArrayList<>();
            } else {
                throw new IOException("Failed to create new file: " + fileName);
            }
        }
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        try (ObjectInput ois = new ObjectInputStream(bis)) {
            return ois.readObject();
        }
    }
}