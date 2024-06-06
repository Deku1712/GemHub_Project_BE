package exeGemHub.gemhub.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

    @GetMapping("/setsession")
    public String setSession(@RequestParam("name") int orderId, HttpSession session) {
        session.setAttribute("Order ID", orderId);
        return "Session attribute 'name' set to: " + orderId;
    }

    @GetMapping("/getsession")
    public String getSession(HttpSession session) {
        String name =  session.getAttribute("orderId").toString();
        if (name == null) {
            return "No session attribute 'name' found!";
        }
        return "Session attribute 'name' is: " + name;
    }

    @GetMapping("/clearsession")
    public String clearSession(HttpSession session) {
        session.invalidate();
        return "Session cleared!";
    }
}
