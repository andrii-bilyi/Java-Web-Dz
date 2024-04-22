package step.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import step.learning.dal.dao.CartDao;
import step.learning.dal.dao.ProductDao;
import step.learning.model.CartPageModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class CartServlet extends HttpServlet {
    private final ProductDao productDao;
    private final CartDao cartDao;

    @Inject
    public CartServlet(ProductDao productDao, CartDao cartDao) {
        this.productDao = productDao;
        this.cartDao = cartDao;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("user-id");
        if (userId != null) {
            CartDao.UserId = userId;
            req.setAttribute("skip-container", "true");
            req.setAttribute("model", new CartPageModel(
                    productDao.getList(0,10),
                    cartDao.getCartList(userId),
                    cartDao.getCart(userId)
            ));
            req.setAttribute("page-body","cart");
            req.getRequestDispatcher("/WEB-INF/_layout.jsp").forward(req,resp);
        } else {
            req.setAttribute("skip-container", "true");
            //передача до представлення (View)
            req.setAttribute("model", new CartPageModel(
                    productDao.getList(0,10),
                    cartDao.getCartList(CartDao.UserId),
                    cartDao.getCart(CartDao.UserId)
            ) );
            CartDao.UserId = null;
            req.setAttribute("page-body","cart");
            req.getRequestDispatcher("/WEB-INF/_layout.jsp").forward(req,resp);
        }
    }
}
