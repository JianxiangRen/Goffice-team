import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import backend.service.UserService;
import backend.model.User;
import org.json.JSONObject;

public class App {

    private static UserService userService = new UserService();

    public static void main(String[] args) throws IOException {
        // Create an HTTP server listening on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);

        // Define API endpoints
        server.createContext("/register", handleRegister());
        server.createContext("/login", handleLogin());
        server.createContext("/update", handleUpdate());
        server.createContext("/delete", handleDelete());

        // Start the server
        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8000");
    }

    private static HttpHandler handleRegister() {
        return (exchange -> {
            if ("POST".equals(exchange.getRequestMethod())) {
                JSONObject userJson = new JSONObject(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8));

                User user = new User(
                        userJson.getString("userID"),
                        userJson.getString("password"),
                        userJson.getString("firstName"),
                        userJson.getString("lastName"),
                        userJson.getString("emailAddr")
                );

                boolean success = userService.addUser(user);
                sendResponse(exchange, success ? 201 : 400, success ? "User registered successfully" : "Failed to register user");
            } else {
                sendResponse(exchange, 405, "Method Not Allowed");
            }
        });
    }

    private static HttpHandler handleLogin() {
        return (exchange -> {
            if ("POST".equals(exchange.getRequestMethod())) {
                JSONObject userJson = new JSONObject(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8));

                boolean success = userService.loginUser(
                        userJson.getString("userID"),
                        userJson.getString("password")
                );

                sendResponse(exchange, success ? 200 : 401, success ? "Login successful" : "Invalid userID or password");
            } else {
                sendResponse(exchange, 405, "Method Not Allowed");
            }
        });
    }

    private static HttpHandler handleUpdate() {
        return (exchange -> {
            if ("POST".equals(exchange.getRequestMethod())) {
                JSONObject userJson = new JSONObject(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8));

                User user = new User(
                        userJson.getString("userID"),
                        "", // Password is not updated here
                        userJson.getString("firstName"),
                        userJson.getString("lastName"),
                        userJson.getString("emailAddr")
                );

                boolean success = userService.updateUser(user);
                sendResponse(exchange, success ? 200 : 400, success ? "User updated successfully" : "Failed to update user");
            } else {
                sendResponse(exchange, 405, "Method Not Allowed");
            }
        });
    }

    private static HttpHandler handleDelete() {
        return (exchange -> {
            if ("DELETE".equals(exchange.getRequestMethod())) {
                Map<String, String> queryParams = queryToMap(exchange.getRequestURI().getQuery());
                String userID = queryParams.get("userID");

                boolean success = userService.deleteUser(userID);
                sendResponse(exchange, success ? 200 : 400, success ? "User deleted successfully" : "Failed to delete user");
            } else {
                sendResponse(exchange, 405, "Method Not Allowed");
            }
        });
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String responseText) throws IOException {
        exchange.sendResponseHeaders(statusCode, responseText.getBytes().length);
        OutputStream responseBody = exchange.getResponseBody();
        responseBody.write(responseText.getBytes(StandardCharsets.UTF_8));
        responseBody.close();
    }

    // Helper method to parse query parameters
    private static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        if (query != null) {
            for (String param : query.split("&")) {
                String[] entry = param.split("=");
                if (entry.length > 1) {
                    result.put(entry[0], entry[1]);
                } else {
                    result.put(entry[0], "");
                }
            }
        }
        return result;
    }
}


