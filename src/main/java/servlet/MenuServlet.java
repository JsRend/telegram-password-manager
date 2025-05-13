package servlet;

import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import service.KeyService;
import service.UserService;
import tgbot.jdbc.utils.JspHelper;
import tgbot.jdbc.utils.UrlPath;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(UrlPath.MENU)
public class MenuServlet extends HttpServlet {

    private final KeyService keyService = KeyService.getInstance();
    private static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        var user = (UserDto) session.getAttribute(USER);

        var passwordCount = keyService.getPasswordCount(user.getNames());
        session.setAttribute("keysCount", passwordCount);

        req.getRequestDispatcher(JspHelper.getPath("mainMenu"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
