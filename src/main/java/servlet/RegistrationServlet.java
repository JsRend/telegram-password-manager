package servlet;

import dto.CreateUserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
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

@WebServlet(UrlPath.REGISTRATION)
public class RegistrationServlet extends HttpServlet {

    private static UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long userId = Long.parseLong(req.getParameter("userId"));
        Long userCode = Long.parseLong(req.getParameter("userCode"));

        req.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        resp.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));

        userService.checkAvailability(userId).ifPresentOrElse(
                user -> userAvailabile(req, resp),
                () -> doRegistration(userId, userCode, req, resp)
        );

    }

    private void doRegistration(Long userId, Long userCode, HttpServletRequest req, HttpServletResponse resp) {

        var userDto = CreateUserDto.builder()
                .name(userId)
                .code(userCode)
                .createDate(LocalDate.now())
                .build();
        try {
            userService.create(userDto);
            resp.sendRedirect(UrlPath.LOGIN);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    private void userAvailabile(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect(UrlPath.LOGIN);
    }
}
