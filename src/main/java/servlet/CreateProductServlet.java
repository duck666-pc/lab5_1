package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import repository.ProductRepository;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/product/create")
public class CreateProductServlet extends HttpServlet {
    private ProductRepository repo = new ProductRepository();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/create-product.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Product p = new Product();
        p.setProductName(req.getParameter("productName"));
        p.setStockQuantity(Integer.valueOf(req.getParameter("stockQuantity")));
        p.setIsAvailable(req.getParameter("isAvailable") != null);

        String dateStr = req.getParameter("lastRestockDate");
        if (dateStr != null && !dateStr.isEmpty()) {
            p.setLastRestockDate(LocalDateTime.parse(dateStr));
        }

        repo.insert(p);
        resp.sendRedirect(req.getContextPath() + "/product");
    }
}
