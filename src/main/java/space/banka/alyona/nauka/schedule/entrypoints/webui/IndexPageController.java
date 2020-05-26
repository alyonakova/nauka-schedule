package space.banka.alyona.nauka.schedule.entrypoints.webui;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import space.banka.alyona.nauka.schedule.security.SecurityConfig;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexPageController {

    @RequestMapping("/")
    String redirectToLargeTimesheet() {
        if (userHasRole("ROLE_" + SecurityConfig.Roles.TIMESHEET_MANAGER)) {
            return "redirect:/departments/all/timesheet";
        } else if (userHasRole("ROLE_" + SecurityConfig.Roles.EMPLOYEES_ADMIN)) {
            return "redirect:/employees";
        } else if (userHasRole("ROLE_" + SecurityConfig.Roles.DEPARTMENTS_ADMIN)) {
            return "redirect:/departments";
        } else {
            throw new UnsupportedOperationException("Unknown user");
        }
    }

    private boolean userHasRole(String role) {
        SecurityContext context = SecurityContextHolder.getContext();

        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return false;
        }

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if (role.equals(auth.getAuthority())) {
                return true;
            }
        }

        return false;
    }
}
