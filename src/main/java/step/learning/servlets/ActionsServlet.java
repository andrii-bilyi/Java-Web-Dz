package step.learning.servlets;

import com.google.inject.Singleton;
import step.learning.dal.dao.ActionDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class ActionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //запит даних
        ActionDao actionDao = new ActionDao();
        //передача до представлення (View)
        req.setAttribute("actions",actionDao.getAction());


        req.setAttribute("page-body","actions");
        req.getRequestDispatcher("/WEB-INF/_layout.jsp").forward(req,resp);
    }
}
