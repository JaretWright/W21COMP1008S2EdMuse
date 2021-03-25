import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
//        TreeMap<String, Integer> grades = new TreeMap<>();
//        grades.put("COMP 1008",100);
//        grades.put("MGMT 2003",87);
//        grades.put("COMP 1030",99);
//
//        int value = grades.get("COMP 1011");
//        System.out.println(value);
//
//        ArrayList<String> courses = new ArrayList<>();
//        courses.add("COMP 1008");
//        courses.add("MGMT 2003");
//        courses.add("COMP 1030");
//
//        for (String courseCode : courses)
//            System.out.println(courseCode);
//
//        //the same is true with a Map, but we need to choose whether we want to loop over the keys or
//        //the values
//        for (String key : grades.keySet())
//            System.out.printf("%s -> %d %n", key, grades.get(key));
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/dashboardView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("EdMuse Dashboard");
        stage.show();
    }
}
