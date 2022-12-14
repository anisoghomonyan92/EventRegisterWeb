package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns="/users")
public class ShowAllUsers extends HttpServlet {
    private UserManager userManager=new UserManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList=userManager.showUsers();
        req.setAttribute("user", userList);
        req.getRequestDispatcher("/WEB-INF/showAllUsers.jsp").forward(req,resp);
    }
}
