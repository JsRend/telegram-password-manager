package servlet;

import dto.KeyDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.KeyService;
import tgbot.jdbc.utils.JspHelper;

import java.io.IOException;

@WebServlet("/password-detail")
public class PasswordDetailsServlet extends HttpServlet {

    private final KeyService keyService = KeyService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("passwordDetails")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long keyId = Long.valueOf(req.getParameter("key"));

        keyService.showKeyById(keyId).ifPresentOrElse(
                key -> onAvailabilitySuccess(key, req, resp),
                () -> onAvailabilityFalse(req, resp)
        );

    }

    @SneakyThrows
    private void onAvailabilityFalse(HttpServletRequest req, HttpServletResponse resp) {
        req.getRequestDispatcher(JspHelper.getPath("passwordError")).forward(req, resp);
    }

    @SneakyThrows
    private void onAvailabilitySuccess(KeyDto key, HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("keyName", key.getKeyName());
        req.setAttribute("key", key.getUserKey());
        req.setAttribute("keyId", key.getId());
        req.getRequestDispatcher(JspHelper.getPath("passwordDetails")).forward(req, resp);
    }
}
