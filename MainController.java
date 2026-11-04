package womenshop.controller;

import womenshop.dao.ProductDAO;
import womenshop.model.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;

public class MainController {

    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, Integer> colID;
    @FXML private TableColumn<Product, String> colName;
    @FXML private TableColumn<Product, String> colCategory;
    @FXML private TableColumn<Product, Double> colBuy;
    @FXML private TableColumn<Product, Double> colSell;
    @FXML private TableColumn<Product, Double> colDiscount;
    @FXML private TableColumn<Product, Integer> colSize;
    @FXML private TableColumn<Product, Integer> colStock;

    @FXML private ComboBox<String> categoryFilter;

    @FXML private Label lblCapital, lblIncome, lblCost;

    private ObservableList<Product> products;

    @FXML
    public void initialize() {
        setupTableColumns();
        loadProducts();
        setupCategoryFilter();
        loadFinancials();
        updateFinancialLabels();
    }

    private void setupTableColumns() {

        colID.setCellValueFactory(new PropertyValueFactory<>("number"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        colCategory.setCellValueFactory(
                cell -> new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getClass().getSimpleName()));

        colBuy.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        colSell.setCellValueFactory(new PropertyValueFactory<>("sellPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discountPrice"));

        colSize.setCellValueFactory(cell -> {
            Product p = cell.getValue();
            int size = (p instanceof Clothes) ? ((Clothes) p).getSize() :
                    (p instanceof Shoes) ? ((Shoes) p).getShoeSize() : 0;
            return new javafx.beans.property.SimpleIntegerProperty(size).asObject();
        });

        colStock.setCellValueFactory(new PropertyValueFactory<>("nbItems"));
    }

    private void setupCategoryFilter() {
        categoryFilter.getItems().addAll("All", "Clothes", "Shoes", "Accessories");
        categoryFilter.setValue("All");
        categoryFilter.setOnAction(e -> filterByCategory());
    }

    private void loadProducts() {
        products = FXCollections.observableArrayList(ProductDAO.getAllProducts());
        productTable.setItems(products);
    }

    private void filterByCategory() {
        String selected = categoryFilter.getValue();

        if (selected.equals("All")) {
            productTable.setItems(products);
            return;
        }

        ObservableList<Product> filtered = FXCollections.observableArrayList();

        for (Product p : products) {
            if (p.getClass().getSimpleName().equals(selected)) {
                filtered.add(p);
            }
        }

        productTable.setItems(filtered);
    }

    private void loadFinancials() {
        if (Product.getCapital() <= 0)
            Product.setCapital(30000);
    }

    private void updateFinancialLabels() {
        lblCapital.setText(String.format("%.2f", Product.getCapital()));
        lblIncome.setText(String.format("%.2f", Product.getIncome()));
        lblCost.setText(String.format("%.2f", Product.getCost()));
    }

    @FXML
    private void onAdd() {
        openProductWindow("Add Product", null);
    }

    @FXML
    private void onEdit() {
        Product selected = productTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Please select a product to edit.");
            return;
        }

        openProductWindow("Edit Product", selected);
    }

    private void openProductWindow(String title, Product productToEdit) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/womenshop/view/AddProductView.fxml"));
            Parent root = loader.load();

            AddProductController controller = loader.getController();
            if (productToEdit != null) controller.setEditProduct(productToEdit);

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.showAndWait();

            loadProducts();
            updateFinancialLabels();

        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error: " + e.getMessage());
        }
    }

    @FXML
    private void onDelete() {
        Product selected = productTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Select a product to delete.");
            return;
        }

        if (selected.getNbItems() > 0) {
            showAlert(Alert.AlertType.ERROR,
                    "Product cannot be deleted: stock still available");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete \"" + selected.getName() + "\" ?", ButtonType.YES, ButtonType.NO);

        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            ProductDAO.deleteProduct(selected.getNumber());
            products.remove(selected);
            showAlert(Alert.AlertType.INFORMATION, "Deleted successfully.");
        }
    }

    @FXML
    private void onPurchase() {
        Product selected = productTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Select a product to purchase.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Purchase");
        dialog.setHeaderText("Purchase " + selected.getName());
        dialog.setContentText("Enter quantity:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                int qty = Integer.parseInt(input);
                if (qty <= 0) throw new NumberFormatException();

                selected.purchase(qty);
                ProductDAO.updateProduct(selected);

                loadProducts();
                updateFinancialLabels();

                showAlert(Alert.AlertType.INFORMATION, "Purchased " + qty + " units.");

            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        });
    }

    @FXML
    private void onSell() {
        Product selected = productTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Select a product to sell.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Sell");
        dialog.setHeaderText("Sell " + selected.getName());
        dialog.setContentText("Enter quantity:");

        dialog.showAndWait().ifPresent(input -> {
            try {
                int qty = Integer.parseInt(input);
                if (qty <= 0) throw new NumberFormatException();

                selected.sell(qty);
                ProductDAO.updateProduct(selected);

                loadProducts();
                updateFinancialLabels();

                showAlert(Alert.AlertType.INFORMATION, "Sold " + qty + " units.");

            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, e.getMessage());
            }
        });
    }

    @FXML
    private void onApplyDiscount() {
        Product selected = productTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Select a product.");
            return;
        }

        selected.applyDiscount();
        ProductDAO.updateProduct(selected);

        loadProducts();
    }

    @FXML
    private void onRemoveDiscount() {
        Product selected = productTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert(Alert.AlertType.WARNING, "Select a product.");
            return;
        }

        selected.unApplyDiscount();
        ProductDAO.updateProduct(selected);

        loadProducts();
    }

    @FXML
    private void onSortByPrice() {
        FXCollections.sort(products, (a, b) -> Double.compare(a.getSellPrice(), b.getSellPrice()));
        productTable.refresh();
    }


    private void showAlert(Alert.AlertType type, String msg) {
        new Alert(type, msg).showAndWait();
    }
}
