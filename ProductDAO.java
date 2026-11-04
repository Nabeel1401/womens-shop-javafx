package womenshop.dao;

import womenshop.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public static void insertProduct(Product p) {
        String sql;

        if (p instanceof Clothes) {
            sql = "INSERT INTO clothes (name, purchase_price, sell_price, discount_price, size, stock) VALUES (?, ?, ?, ?, ?, ?)";
        } else if (p instanceof Shoes) {
            sql = "INSERT INTO shoes (name, purchase_price, sell_price, discount_price, shoe_size, stock) VALUES (?, ?, ?, ?, ?, ?)";
        } else {
            sql = "INSERT INTO accessories (name, purchase_price, sell_price, discount_price, stock) VALUES (?, ?, ?, ?, ?)";
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getName());
            stmt.setDouble(2, p.getPurchasePrice());
            stmt.setDouble(3, p.getSellPrice());
            stmt.setDouble(4, p.getDiscountPrice());

            if (p instanceof Clothes c) {
                stmt.setInt(5, c.getSize());
                stmt.setInt(6, c.getNbItems());
            } else if (p instanceof Shoes s) {
                stmt.setInt(5, s.getShoeSize());
                stmt.setInt(6, s.getNbItems());
            } else { // Accessories
                stmt.setInt(5, p.getNbItems());
            }

            stmt.executeUpdate();
            System.out.println(" INSERTED: " + p.getName());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {

            // CLOTHES
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM clothes");
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    products.add(new Clothes(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("purchase_price"),
                            rs.getDouble("sell_price"),
                            rs.getDouble("discount_price"),
                            rs.getInt("size"),
                            rs.getInt("stock")
                    ));
                }
            }

            // SHOES
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM shoes");
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    products.add(new Shoes(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("purchase_price"),
                            rs.getDouble("sell_price"),
                            rs.getDouble("discount_price"),
                            rs.getInt("shoe_size"),
                            rs.getInt("stock")
                    ));
                }
            }

            // ACCESSORIES
            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM accessories");
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    products.add(new Accessories(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("purchase_price"),
                            rs.getDouble("sell_price"),
                            rs.getDouble("discount_price"),
                            rs.getInt("stock")
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public static void updateProduct(Product p) {
        String sql;

        if (p instanceof Clothes) {
            sql = "UPDATE clothes SET name=?, purchase_price=?, sell_price=?, discount_price=?, size=?, stock=? WHERE id=?";
        } else if (p instanceof Shoes) {
            sql = "UPDATE shoes SET name=?, purchase_price=?, sell_price=?, discount_price=?, shoe_size=?, stock=? WHERE id=?";
        } else {
            sql = "UPDATE accessories SET name=?, purchase_price=?, sell_price=?, discount_price=?, stock=? WHERE id=?";
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getName());
            stmt.setDouble(2, p.getPurchasePrice());
            stmt.setDouble(3, p.getSellPrice());
            stmt.setDouble(4, p.getDiscountPrice());

            if (p instanceof Clothes c) {
                stmt.setInt(5, c.getSize());
                stmt.setInt(6, c.getNbItems());
                stmt.setInt(7, p.getNumber());
            } else if (p instanceof Shoes s) {
                stmt.setInt(5, s.getShoeSize());
                stmt.setInt(6, s.getNbItems());
                stmt.setInt(7, p.getNumber());
            } else {
                stmt.setInt(5, p.getNbItems());
                stmt.setInt(6, p.getNumber());
            }

            stmt.executeUpdate();
            System.out.println(" UPDATED: " + p.getName());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(int id) {

        String[] tables = {"clothes", "shoes", "accessories"};

        try (Connection conn = DBConnection.getConnection()) {

            for (String table : tables) {
                try (PreparedStatement stmt =
                             conn.prepareStatement("DELETE FROM " + table + " WHERE id=?")) {

                    stmt.setInt(1, id);
                    stmt.executeUpdate();
                }
            }

            System.out.println("🗑 DELETED product ID: " + id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
