package kolesov.task4;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kolesov.task4.controllers.AddExpressionViewController;
import kolesov.task4.controllers.AddParameterViewController;
import kolesov.task4.controllers.MainWindowController;
import kolesov.task4.controllers.ResultViewController;
import kolesov.task4.logic.ExpressionParseException;
import kolesov.task4.models.Expression;
import kolesov.task4.models.Parameter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class MyApplication extends Application {
    private final ObservableList<Parameter> parameters = FXCollections.observableArrayList();
    private final ObservableList<String> expressions = FXCollections.observableArrayList();
    private Stage primaryStage;

    private static final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private static final PrintStream out = new PrintStream(baos, true, StandardCharsets.UTF_16);

    public ObservableList<Parameter> getParametersData() {
        return parameters;
    }
    public ObservableList<String> getExpressionsData() {
        return expressions;
    }
    public MyApplication() {}

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MyApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Вычисление математических выражений");
        stage.setScene(scene);
        stage.show();

        MainWindowController controller = fxmlLoader.getController();
        controller.setApplication(this);
    }

    public boolean showAddExpressionDialog(Expression expression) throws IOException {
        FXMLLoader loader = new FXMLLoader(MyApplication.class.getResource("add-expression-view.fxml"));
        Scene scene = new Scene(loader.load());

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Добавить выражение");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        dialogStage.setScene(scene);

        AddExpressionViewController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setExpression(expression);

        dialogStage.showAndWait();
        return controller.isOkClicked();
    }

    public boolean showAddParameterDialog(Parameter parameter) throws IOException {
        FXMLLoader loader = new FXMLLoader(MyApplication.class.getResource("add-parameter-view.fxml"));
        Scene scene = new Scene(loader.load());

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Добавить параметр");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        dialogStage.setScene(scene);

        AddParameterViewController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setExpression(parameter);

        dialogStage.showAndWait();
        return controller.isOkClicked();
    }

    public void showResultDialog() throws IOException, ExpressionParseException {
        FXMLLoader loader = new FXMLLoader(MyApplication.class.getResource("result-view.fxml"));
        Scene scene = new Scene(loader.load());

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Результат");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        dialogStage.setScene(scene);

        ResultViewController controller = loader.getController();
        controller.setStage(dialogStage);
        controller.execute(expressions, parameters);

        dialogStage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static PrintStream getOutputStream() {
        return out;
    }

    public static ByteArrayOutputStream getByteArrayOutputStream() {
        return baos;
    }
}