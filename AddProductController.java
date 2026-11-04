package womenshop.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import womenshop.dao.ProductDAO;
import womenshop.model.*;

public class AddProductController {

    @FXML private TextField txtName;
    @FXML private ComboBox<String> cmbCategory;
    @FXML private TextField txtBuy;
    @FXML private TextField txtSell;
    @FXML private TextField txtSize;
    @FXML private TextField txtStock;
    @FXML private Label lblTitle;
    @FXML private Label lblEditMode;
    @FXML private Button btnSave;

    private Product editingProduct = null;

    @FXML
    public void initialize() {

        cmbCategory.getItems().addAll("Clothes", "Shoes", "Accessories");

        if (lblEditMode != null)
            lblEditMode.setVisible(false);

        cmbCategory.setOnAction(e -> {
            String selected = cmbCategory.getValue();
            boolean needsSize = selected != null &&
                    (selected.equals("Clothes") || selected.equals("Shoes"));

            txtSize.setDisable(!needsSize);
            txtSize.setPromptText(needsSize ? "Enter size" : "N/A");

            if (!needsSize) txtSize.clear();
        });
    }

    public void setEditProduct(Product product) {
        this.editingProduct = product;

        lblTitle.setText("✎ Edit Product");
        lblEditMode.setVisible(true);
        btnSave.setText("Update");

        txtName.setText(product.getName());
        cmbCategory.setValue(product.getClass().getSimpleName());
        txtBuy.setText(String.valueOf(product.getPurchasePrice()));
        txtSell.setText(String.valueOf(product.getSellPrice()));
        txtSell.setDisable(true);

        if (product instanceof Clothes c)
            txtSize.setText(String.valueOf(c.getSize()));
        else if (product instanceof Shoes s)
            txtSize.setText(String.valueOf(s.getShoeSize()));
        else
            txtSize.clear();
        cmbCategory.setDisable(true);
        txtStock.setDisable(true);

    }

    @FXML
    private void onSave() {
        try {
            String name = txtName.getText().trim();
            String category = cmbCategory.getValue();
            double buy = Double.parseDouble(txtBuy.getText());
            double sell = Double.parseDouble(txtSell.getText());
            int size = txtSize.getText().isEmpty() ? 0 : Integer.parseInt(txtSize.getText());

            if (name.isEmpty() || category == null) {
                showWarning("Please fill all required fields.");
                return;
            }

            if (buy < 0 || sell < 0) {
                showError("Prices cannot be negative.");
                return;
            }

            if (buy > sell) {
                showError("Purchase price cannot be greater than sale price.");
                return;
            }

            if (category.equals("Clothes") && (size < 34 || size > 54)) {
                showError("Clothing size must be between 34 and 54.");
                return;
            }

            if (category.equals("Shoes") && (size < 36 || size > 50)) {
                showError("Shoe size must be between 36 and 50.");
                return;
            }

            if (editingProduct != null) {

                editingProduct.setName(name);
                editingProduct.setPurchasePrice(buy);

                if (editingProduct instanceof Clothes c)
                    c.setSize(size);
                else if (editingProduct instanceof Shoes s)
                    s.setShoeSize(size);

                ProductDAO.updateProduct(editingProduct);
                showInfo("Product updated successfully!");
                closeWindow();
                return;
            }

            Product newProduct;

            switch (category) {
                case "Clothes" -> newProduct = new Clothes(name, buy, sell, size);
                case "Shoes" -> newProduct = new Shoes(name, buy, sell, size);
                default -> newProduct = new Accessories(name, buy, sell);
            }

            newProduct.setNbItems(0);

            ProductDAO.insertProduct(newProduct);
            showInfo("Product added successfully!");
            closeWindow();

        } catch (NumberFormatException e) {
            showError("Enter valid numeric values.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    private void onCancel() {
        closeWindow();
    }

    private void showError(String msg) {
        new Alert(Alert.AlertType.ERROR, msg).showAndWait();
    }

    private void showWarning(String msg) {
        new Alert(Alert.AlertType.WARNING, msg).showAndWait();
    }

    private void showInfo(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }

    private void closeWindow() {
        Stage stage = (Stage) txtName.getScene().getWindow();
        stage.close();
    }
}
