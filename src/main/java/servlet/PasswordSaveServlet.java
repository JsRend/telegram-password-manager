package servlet;

import dto.CreateKeyDto;
import dto.CreateUserDto;
import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.KeyService;
import tgbot.jdbc.utils.JspHelper;
import tgbot.jdbc.utils.UrlPath;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/password-save")
public class PasswordSaveServlet extends HttpServlet {
    private final KeyService keyService = KeyService.getInstance();
    private static final String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("passwordSaved")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var session = req.getSession();
        var user = (UserDto) session.getAttribute(USER);

        Long id = user.getNames();
        String userPassword = req.getParameter("userPassword");
        String userDomain = req.getParameter("userDomain");

        var keyDto = CreateKeyDto.builder()
                .userId(id)
                .keyName(userDomain)
                .userKey(userPassword)
                .createDate(LocalDate.now())
                .difficult(1)
                .build();
        try {
            keyService.create(keyDto);
            resp.sendRedirect("/password-save");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
