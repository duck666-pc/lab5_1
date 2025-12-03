package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.ProductRepository;

import java.io.IOException;

@WebServlet("/product")
public class ViewProductServlet extends HttpServlet {
    private ProductRepository repo = new ProductRepository();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("products", repo.getAll());
        req.getRequestDispatcher("/WEB-INF/views/view-products.jsp").forward(req, resp);
    }
}
