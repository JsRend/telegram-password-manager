package servlet;

import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.UserService;
import tgbot.jdbc.utils.JspHelper;
import tgbot.jdbc.utils.UrlPath;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@MultipartConfig
@WebServlet(UrlPath.LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

        req.getRequestDispatcher(JspHelper.getPath("auth"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var userId = req.getParameter("userId");
        var codeInput = req.getParameter("code");


        req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

         userService.login(Long.parseLong(userId), Long.parseLong(codeInput))
                         .ifPresentOrElse(
                                 user -> onLoginSuccess(user, req, resp),
                                 () -> onLoginFail(req, resp)
                         );

    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect(UrlPath.LOGIN + "?error=code");
        //! add in jstl view

    }

    @SneakyThrows
    private void onLoginSuccess(UserDto user, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", user);
        resp.sendRedirect("/menu");
    }
}
