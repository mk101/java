package kolesov.task5;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kolesov.task5.controllers.AddExpressionViewController;
import kolesov.task5.controllers.AddParameterViewController;
import kolesov.task5.controllers.MainWindowController;
import kolesov.task5.controllers.ResultViewController;
import kolesov.task5.logic.ExpressionParseException;
import kolesov.task5.models.Expression;
import kolesov.task5.models.IOData;
import kolesov.task5.models.Parameter;
import kolesov.task5.models.ParameterData;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MyApplication extends Application {
    private final String fileName = "bd.dat";

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
    public MyApplication() {
        loadFromFile();
    }

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

    private void loadFromFile() {
        try {
            var fis = new FileInputStream(fileName);
            var ois = new ObjectInputStream(fis);

            IOData data = (IOData)ois.readObject();

            expressions.clear();
            expressions.addAll(data.getExpressions());

            parameters.clear();
            parameters.addAll(data.getParameters().stream().map(ParameterData::getParameter).toList());

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile() {
        try {
            IOData data = new IOData(expressions.stream().toList(), parameters.stream().map( e -> new ParameterData(e.getName(), e.getValue())).toList());
            var fos = new FileOutputStream(fileName);
            var oos = new ObjectOutputStream(fos);

            oos.writeObject(data);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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