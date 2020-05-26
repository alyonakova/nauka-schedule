package space.banka.alyona.nauka.schedule.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public interface Roles {
        String DEPARTMENTS_ADMIN = "DepartmentsAdmin";
        String EMPLOYEES_ADMIN = "EmployeesAdmin";
        String TIMESHEET_MANAGER = "TimesheetManager";
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails departmentsAdmin = User.withDefaultPasswordEncoder()
                .username("DepartmentsAdmin")
                .password("password")
                .roles(Roles.DEPARTMENTS_ADMIN)
                .build();

        UserDetails employeesAdmin = User.withDefaultPasswordEncoder()
                .username("EmployeesAdmin")
                .password("password")
                .roles(Roles.EMPLOYEES_ADMIN)
                .build();

        UserDetails timesheetManager = User.withDefaultPasswordEncoder()
                .username("TimesheetManager")
                .password("password")
                .roles(Roles.TIMESHEET_MANAGER)
                .build();

        return new InMemoryUserDetailsManager(departmentsAdmin, employeesAdmin, timesheetManager);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/departments").hasRole(Roles.DEPARTMENTS_ADMIN)
                .antMatchers("/employees").hasRole(Roles.EMPLOYEES_ADMIN)
                .antMatchers("/departments/*/timesheet").hasRole(Roles.TIMESHEET_MANAGER);
        http.formLogin().permitAll();
        http.logout().permitAll();
    }
}
