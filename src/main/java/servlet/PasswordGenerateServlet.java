package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.KeyService;
import service.UserService;
import tgbot.jdbc.utils.JspHelper;
import tgbot.jdbc.utils.PasswordGenerator;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/password-generate")
public class PasswordGenerateServlet extends HttpServlet {

    private static final String EXAMPLE_PASSWORD = "Ght&8kLm92";
    private final KeyService keyService = KeyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("passwordGenerate")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int length = Integer.parseInt(req.getParameter("length"));
        boolean useNumbers = Boolean.parseBoolean(req.getParameter("useNumbers"));
        boolean useSpecialChar = Boolean.parseBoolean(req.getParameter("useSpecialСhar"));
        boolean useUppercase = Boolean.parseBoolean(req.getParameter("useUppercase"));

        req.setAttribute("contextDomain", req.getParameter("domain"));


        String password = keyService.generatePassword(length, useNumbers, useSpecialChar, useUppercase);

        req.setAttribute("password", password);
        req.getRequestDispatcher(JspHelper.getPath("passwordGenerate")).include(req, resp);
    }
}
