package servlet;

import dto.CreateUserDto;
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
import java.time.LocalDate;

@WebServlet("/change-code-password")
public class CodePasswordServlet extends HttpServlet {

    private static UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("changeCode"))
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userId = Long.parseLong(req.getParameter("userId"));
        Long userCode = Long.parseLong(req.getParameter("userCode"));

        userService.checkAvailability(userId).ifPresentOrElse(
                user -> doChangeCode(userId, userCode, req, resp),
                () -> userNotFound(req, resp)
        );

    }

    @SneakyThrows
    private void doChangeCode(Long userId, Long userCode, HttpServletRequest req, HttpServletResponse resp) {

        var userDto = CreateUserDto.builder()
                .name(userId)
                .code(userCode)
                .createDate(LocalDate.now())
                .build();
        try {
            userService.changeCode(userDto);
            resp.sendRedirect(UrlPath.LOGIN);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @SneakyThrows
    private void userNotFound(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect(UrlPath.CHECK);
    }
}
