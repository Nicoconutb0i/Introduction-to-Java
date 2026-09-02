package introductionToJava;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class HappyFace extends Application {

    // Face layout constants
    private static final double FACE_CENTER_X = 200;
    private static final double FACE_CENTER_Y = 180;
    private static final double FACE_RADIUS = 120;

    private Pane facePane;

    @Override
    public void start(Stage primaryStage) {

        // Input controls 
        Label widthLabel = new Label("Width:");
        TextField widthField = new TextField("60");
        widthField.setPrefWidth(60);

        Label heightLabel = new Label("Height:");
        TextField heightField = new TextField("40");
        heightField.setPrefWidth(60);

        Label arcLabel = new Label("Arc:");
        TextField arcField = new TextField("180");
        arcField.setPrefWidth(60);

        Button drawButton = new Button("Apply Smile");

        VBox controls = new VBox(8,
                widthLabel, widthField,
                heightLabel, heightField,
                arcLabel, arcField,
                drawButton);
        controls.setAlignment(Pos.TOP_LEFT);
        controls.setPadding(new Insets(10));

        // Drawing area 
        facePane = new Pane();
        facePane.setPrefSize(400, 360);

        // Default values for initial face
        drawFace(60, 40, 180);

        // Button action 
        drawButton.setOnAction(e -> {
            try {
                double mouthWidth = Double.parseDouble(widthField.getText().trim());
                double mouthHeight = Double.parseDouble(heightField.getText().trim());
                double mouthArc = Double.parseDouble(arcField.getText().trim());
                drawFace(mouthWidth, mouthHeight, mouthArc);
            } catch (NumberFormatException ex) {
                
                System.out.println("Please enter valid numbers for width, height, and arc.");
            }
        });

        // Root layout 
        BorderPane root = new BorderPane();
        root.setLeft(controls);
        root.setCenter(facePane);

        Scene scene = new Scene(root, 400, 420);
        primaryStage.setTitle("Smiley Face - JavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawFace(double mouthWidth, double mouthHeight, double mouthArc) {
        facePane.getChildren().clear();

        // Face outline
        Circle face = new Circle(FACE_CENTER_X, FACE_CENTER_Y, FACE_RADIUS);
        face.setFill(Color.TRANSPARENT);
        face.setStroke(Color.BLACK);
        face.setStrokeWidth(2);

        // Eyes
        Circle leftEye = new Circle(FACE_CENTER_X - 45, FACE_CENTER_Y - 35, 10);
        leftEye.setFill(Color.BLACK);

        Circle rightEye = new Circle(FACE_CENTER_X + 45, FACE_CENTER_Y - 35, 10);
        rightEye.setFill(Color.BLACK);

        double arcLength = Math.abs(mouthArc);
        double centerAngle = (mouthArc >= 0) ? 270 : 90;
        double startAngle = centerAngle - (arcLength / 2);
        Arc mouth = new Arc(FACE_CENTER_X, FACE_CENTER_Y + 20, mouthWidth, mouthHeight, startAngle, arcLength);
        mouth.setType(ArcType.OPEN);
        mouth.setFill(Color.TRANSPARENT);
        mouth.setStroke(Color.BLACK);
        mouth.setStrokeWidth(2);

        facePane.getChildren().addAll(face, leftEye, rightEye, mouth);
    }

    public static void main(String[] args) {
        launch(args);
    }
}