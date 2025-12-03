package repository;

import connector.DBConnector;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        try (Connection conn = DBConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Products")) {

            while(rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("ID"));
                p.setProductName(rs.getString("ProductName"));
                p.setStockQuantity(rs.getInt("StockQuantity"));
                p.setIsAvailable(rs.getBoolean("IsAvailable"));

                Timestamp ts = rs.getTimestamp("LastRestockDate");
                if (ts != null) p.setLastRestockDate(ts.toLocalDateTime());

                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Product getById(Integer id) {
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Products WHERE ID = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("ID"));
                p.setProductName(rs.getString("ProductName"));
                p.setStockQuantity(rs.getInt("StockQuantity"));
                p.setIsAvailable(rs.getBoolean("IsAvailable"));

                Timestamp ts = rs.getTimestamp("LastRestockDate");
                if (ts != null) p.setLastRestockDate(ts.toLocalDateTime());

                return p;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Product p) {
        String sql = "INSERT INTO Products (ProductName, StockQuantity, IsAvailable, LastRestockDate) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getStockQuantity());
            ps.setBoolean(3, p.getIsAvailable());

            if (p.getLastRestockDate() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(p.getLastRestockDate()));
            } else {
                ps.setTimestamp(4, null);
            }

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Product p) {
        String sql = "UPDATE Products " +
                "SET ProductName=?, StockQuantity=?, IsAvailable=?, LastRestockDate=? " +
                "WHERE ID=?";

        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getStockQuantity());
            ps.setBoolean(3, p.getIsAvailable());

            if (p.getLastRestockDate() != null) {
                ps.setTimestamp(4, Timestamp.valueOf(p.getLastRestockDate()));
            } else {
                ps.setTimestamp(4, null);
            }

            ps.setInt(5, p.getId());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Integer id) {
        try (Connection conn = DBConnector.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Products WHERE ID = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}