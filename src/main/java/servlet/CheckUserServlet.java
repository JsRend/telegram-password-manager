package servlet;

import dto.UserDto;
import jakarta.servlet.ServletException;
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

@WebServlet("/check-user")
public class CheckUserServlet extends HttpServlet {

    private static UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

        req.getRequestDispatcher(JspHelper.getPath("checkUser"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

        Long userId = Long.parseLong(req.getParameter("userId"));

        userService.checkAvailability(userId).ifPresentOrElse(
                user -> onAvailabilitySuccess(req, resp),
                () -> onAvailabilityFalse(req, resp)
        );

    }

    @SneakyThrows
    private void onAvailabilityFalse(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect(UrlPath.REGISTRATION);
    }

    @SneakyThrows
    private void onAvailabilitySuccess(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect(UrlPath.LOGIN);
    }

}
