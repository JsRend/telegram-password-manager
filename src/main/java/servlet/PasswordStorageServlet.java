package servlet;

import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.KeyService;
import tgbot.jdbc.utils.JspHelper;

import java.io.IOException;

@WebServlet("/password-storage")
public class PasswordStorageServlet extends HttpServlet {

    private final KeyService keyService = KeyService.getInstance();
    private static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        var user = (UserDto) session.getAttribute(USER);

        var allUserKeys = keyService.getAllKey(user.getNames());
        session.setAttribute("userKeys", allUserKeys);
        req.getRequestDispatcher(JspHelper.getPath("passwordStorage"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
