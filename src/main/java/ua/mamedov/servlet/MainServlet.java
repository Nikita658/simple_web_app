package ua.mamedov.servlet;

import ua.mamedov.utils.UserInfoContainer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;


public class MainServlet extends HttpServlet {
    private final UserInfoContainer userInfoContainer = new UserInfoContainer();

    @Override
    public void init() throws ServletException {
        userInfoContainer.getUserInfo().put("192.168.0.0", "1Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36 Edg/92.0.902.78");
        userInfoContainer.getUserInfo().put("192.168.0.1", "2Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36 Edg/92.0.902.78");
        userInfoContainer.getUserInfo().put("192.168.0.2", "3Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36 Edg/92.0.902.78");
        userInfoContainer.getUserInfo().put("192.168.0.3", "4Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36 Edg/92.0.902.78");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter responseBody = resp.getWriter();

        resp.setContentType("text/html");
        responseBody.println("<h1 align=\"center\">There is list of users which visited our site</h1>");
        userInfoContainer.getUserInfo().put(req.getRemoteAddr(), req.getHeader("user-agent"));
        for (ConcurrentHashMap.Entry<String, String> entry : userInfoContainer.getUserInfo().entrySet()) {
            String key = entry.getKey();
            String tab = entry.getValue();
            // do something with key and/or tab
            if (tab.equals(req.getHeader("user-agent")) && key.equals(req.getRemoteAddr())) {
                responseBody.println("<main align=\"center\"><b>Request from: " + "IP: " + key + ", User-agent: " + tab + "</b></main>");
            } else {
                responseBody.println("<main align=\"center\">Request from: " + "IP: " + key + ", User-agent: " + tab + "</main>");
            }
        }

        String client = req.getParameter("client");
        if (client == null) {
            client = "anonymous user";
        }

        responseBody.println("<h3 align=\"center\">Hi, " + client + " </h3>");
    }
}
