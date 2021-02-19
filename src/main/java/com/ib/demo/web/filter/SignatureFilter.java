package com.ib.demo.web.filter;

import com.ib.demo.model.Account;
import com.ib.demo.service.AccountService;
import com.ib.demo.service.UtilsService;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebFilter
@Component
public class SignatureFilter implements Filter {

    private final UtilsService utilsService;
    private final AccountService accountService;

    public SignatureFilter(UtilsService utilsService, AccountService accountService) {
        this.utilsService = utilsService;
        this.accountService = accountService;
    }

    public String computeStringToSign() {
        String CanonicalizedResource = "[ \"/\" + Bucket ] +\n" +
                "\t<HTTP-Request-URI, from the protocol name up to the query string> +\n" +
                "\t[ subresource, if present. For example \"?acl\", \"?location\", \"?logging\", or \"?torrent\"];\n";

        String CanonicalizedAmzHeaders = "<described below>";

        return "HTTP-Verb" + "\n" +
                "Content-MD5" + "\n" +
                "Content-Type" + "\n" +
                "Date" + "\n" +
                CanonicalizedAmzHeaders +
                CanonicalizedResource;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getServletPath().equals("/login") || request.getServletPath().equals("/register")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String auth = request.getHeader("Authorization");

            if (auth == null || auth.isEmpty()) {
                response.sendRedirect("/login");
            } else {
                List<String> list = Arrays.asList(auth.split(":").clone());
                String accessKey = list.get(0);
                String signature = list.get(1);

                Optional<Account> account = accountService.findByAccessKey(accessKey);

                if (account.isEmpty()) {
                    response.sendRedirect("/login");
                } else {
                    if (utilsService.signature(account.get().getSecretKey(), computeStringToSign()).equals(signature)) {
                        filterChain.doFilter(servletRequest, servletResponse);
                    } else {
                        response.sendRedirect("/login");
                    }
                }
            }
        }
    }

    @Override
    public void destroy() {

    }
}
