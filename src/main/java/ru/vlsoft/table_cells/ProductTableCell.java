package ru.vlsoft.table_cells;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import ru.vlsoft.models.Product;
import ru.vlsoft.view_models.ProductListViewModel;

import java.util.Optional;

public class ProductTableCell<T> extends TableCell<T, Product> {

    @Override
    public void startEdit() {
        super.startEdit();

        Dialog<ButtonType> dialog = new Dialog<>();
        DialogPane pane = dialog.getDialogPane();
        pane.getButtonTypes().addAll(
                ButtonType.OK,
                ButtonType.CANCEL);
        pane.setContent(new ProductListViewModel().getSelectionDialogContent());
        pane.setMinWidth(600.);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent()) {
            if (result.orElseThrow().getButtonData() == ButtonBar.ButtonData.OK_DONE) {

                try {
                    TableView<Product> dialogTableView = (TableView<Product>) ((VBox) pane.contentProperty().getValue()).getChildren().get(0);
                    updateItem(dialogTableView.getSelectionModel().getSelectedItem(), false);
                }catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }

            }
        }
    }

    @Override
    protected void updateItem(Product product, boolean empty) {
        super.updateItem(product, empty);

        if (!empty && product != null) {
            setItem(product);
            setText(product.getName());
            commitEdit(product);
        }

    }

}
