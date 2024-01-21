package org.daniliszyn;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MeshLoader {
    public static Mesh load(String path) {
        List<Triangle> triangles = new ArrayList<>();
        List<Vector3D> vectors = new LinkedList<>();
        List<Face> faces = new LinkedList<>();

        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] split = data.split("\\s+");
                if (split[0].equals("v")) {
                    double x = Double.parseDouble(split[1]);
                    double y = Double.parseDouble(split[2]);
                    double z = Double.parseDouble(split[3]);
                    Vector3D v = new Vector3D(x, y, z);
                    vectors.add(v);
                }
                if (split[0].equals("f")) {
                    faces.add(new Face(Integer.parseInt(split[1])-1, Integer.parseInt(split[2])-1, Integer.parseInt(split[3])-1));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < faces.size(); i++) {
            Triangle triangle = new Triangle(vectors.get(faces.get(i).v1), vectors.get(faces.get(i).v2), vectors.get(faces.get(i).v3));
            triangles.add(triangle);
        }

        return new Mesh(triangles.toArray(new Triangle[0]));
    }
}
