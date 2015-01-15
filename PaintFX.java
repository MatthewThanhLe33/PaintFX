import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.control.ColorPicker;

/**
 * Rectangle drawing tool
 *
 * @author Matthew Le
 * @version 1.6
 */
public class PaintFX extends Application {

    private GraphicsContext gL;
    private Color textColor = colorGrid.getValue();
    private final Color[] colorSpectrum = {Color.WHITE, Color.RED, Color.ORANGE,
        Color.YELLOW, Color.GREEN, Color.BLUE, Color.PURPLE, Color.BROWN,
        Color.BLACK};
    private int colorIndex;
    private AbstractTools t;
    private AbstractTools pencil1 = new pencilTool(6, textColor);
    private AbstractTools pencil2 = new pencilTool(12, textColor);
    private AbstractTools eraser1 = new pencilTool(5, colorSpectrum[colorIndex]);
    private AbstractTools eraser2 = new pencilTool(50, colorSpectrum[colorIndex]);
    private AbstractTools r = new rectangleTool(textColor,
        colorSpectrum[colorIndex]);

    public void start(Stage primaryStage) {

        primaryStage.setTitle("PaintFX");

        Canvas background = new Canvas(900, 600);
        GraphicsContext gc = background.getGraphicsContext2D();
        gc.setFill(colorSpectrum[colorIndex]);
        gc.fillRect(0, 0, 900, 600);

        Canvas canvas = new Canvas(900, 600);
        canvas.setOnMousePressed((e) -> t.onPress(e, gL));
        canvas.setOnMouseDragged((e) -> t.onDrag(e, gL));
        canvas.setOnMouseReleased((e) -> t.onRelease(e, gL));
        gL = background.getGraphicsContext2D();

        Label toolbar = new Label("Toolbar");
        toolbar.setFont(new Font(20));
        toolbar.setTextFill(Color.BLUE);
        toolbar.setUnderline(true);

        ColorPicker colorGrid = new ColorPicker();
        //setWidth?
        colorGrid.setValue(Color.BLACK);
        textColor = colorGrid.getValue();
        colorGrid.setOnAction(e -> {
            textColor = colorGrid.getValue();
            t.colorSet(colorGrid.getValue());
        });

        Button pencilThin = new Button("Pencil (thin)");
        pencilThin.setFont(Font.font(10));
        pencilThin.setOnAction(e -> {
            t = pencil1;
            t.colorSet(textColor);
        });

        Button pencilThick = new Button("Pencil (THICK)");
        pencilThick.setFont(Font.font(10));
        pencilThick.setOnAction(e -> {
            t = pencil2;
            t.colorSet(textColor);
        });

        Button eraserThin = new Button("Eraser (thin)");
        eraserThin.setFont(Font.font(10));
        eraserThin.setOnAction(e -> {
            t = eraser1;
            t.colorSet(colorSpectrum[colorIndex]);
        });

        Button eraserThick = new Button("Eraser (THICK)");
        eraserThick.setFont(Font.font(10));
        eraserThick.setOnAction(e -> {
            t = eraser2;
            t.colorSet(colorSpectrum[colorIndex]);
        });

        Button rectangle = new Button("Rectangle");
        rectangle.setFont(new Font(10));
        rectangle.setOnAction(e -> {
            t = r;
            t.colorSetBack(colorSpectrum[colorIndex]);
            t.colorSet(textColor);
        });

        Button clear = new Button("CLEAR");
        clear.setFont(new Font(10));
        clear.setOnAction(e -> {
            colorIndex = 0;
            gc.clearRect(0, 0, 900, 600);
            gL.clearRect(0, 0, 900, 600);
            gc.setFill(colorSpectrum[0]);
            gc.fillRect(0, 0, 900, 600);

            if (t.getName().equals("Rectangle")) {
                t.colorSetBack(colorSpectrum[colorIndex]);
            }
            if (t.getName().equals("Eraser (thin)")
                || t.getName().equals("Eraser (THICK)")) {
                t.colorSet(colorSpectrum[colorIndex]);
            }
        });


        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: #000000;");
        vBox.getChildren().addAll(toolbar, colorGrid,
            pencilThin, pencilThick, eraserThin, eraserThick,
            rectangle, clear);

        Pane p = new Pane();
        p.getChildren().addAll(background, canvas);
        canvas.toFront();

        HBox hBox = new HBox();
        hBox.getChildren().addAll(vBox, p);

        Scene scene = new Scene(hBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}