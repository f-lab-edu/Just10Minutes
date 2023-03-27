package flab.testController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
public class Sample {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World3";
    }

    @GetMapping("/helloDB")
    public String helloDB() {
        String result = null;
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://27.96.135.218:3306/test",
                    "readonly",
                    "0000"
            );
            if(con != null) {
                result = con.toString();
            }
        } catch (SQLException e) {
            result = e.toString();
        }
        return result;
    }
}
