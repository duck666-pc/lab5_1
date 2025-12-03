package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.ProductRepository;

import java.io.IOException;

@WebServlet("/product/delete")
public class DeleteProductServlet extends HttpServlet {
    private ProductRepository repo = new ProductRepository();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        repo.deleteById(id);
        resp.sendRedirect(req.getContextPath() + "/product");
    }
}
