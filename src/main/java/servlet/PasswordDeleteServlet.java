package servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.KeyService;
import tgbot.jdbc.utils.JspHelper;
import tgbot.jdbc.utils.UrlPath;

import java.io.IOException;

@WebServlet("/password-delete")
public class PasswordDeleteServlet extends HttpServlet {

    private final KeyService keyService = KeyService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long keyId = Long.valueOf(req.getParameter("key"));

        if (keyService.delete(keyId)) {
            resp.sendRedirect(UrlPath.PASSWORD_STORAGE);
        } else {
            req.getRequestDispatcher(JspHelper.getPath("passwordError")).forward(req, resp);
        }

    }
}