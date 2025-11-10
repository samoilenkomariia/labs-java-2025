package service;

import model.Shape;

import java.io.*;

public class ShapeFileService {

    public void saveShapesToFile(String filePath, Shape[] shapes) throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filePath))) {
            output.writeObject(shapes);
        }
    }

    public Shape[] readShapesFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filePath))) {
            Object obj = input.readObject();
            if (obj instanceof Shape[]) {
                return (Shape[]) obj;
            } else {
                throw new IOException("File does not contain valid array of Shapes");
            }
        }
    }
}
