import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;

public class FanCodeTodoTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
    }

    @Test
    public void testFanCodeUsersCompletedTasksPercentage() {
        // Retrieve users
        Response userResponse = RestAssured.get("/users");
        List<User> users = userResponse.jsonPath().getList("", User.class);

        // Filter users from FanCode city
        List<User> fanCodeUsers = filterFanCodeUsers(users);

        // Assert that at least one user from FanCode city exists
        Assert.assertFalse(fanCodeUsers.isEmpty(), "No users found from FanCode city");

        // Check completed tasks percentage for each user from FanCode city
        for (User user : fanCodeUsers) {
            Response todoResponse = RestAssured.get("/todos?userId=" + user.getId());
            List<Todo> todos = todoResponse.jsonPath().getList("", Todo.class);
            double completedTasksPercentage = calculateCompletedTasksPercentage(todos);
            Assert.assertTrue(completedTasksPercentage > 50, "User " + user.getName() +
                    " from FanCode city does not have more than 50% completed tasks");
        }
    }

    private List<User> filterFanCodeUsers(List<User> users) {
        return users.stream()
                .filter(user -> user.getAddress().getGeo().getLat() > -40 && user.getAddress().getGeo().getLat() < 5 &&
                        user.getAddress().getGeo().getLng() > 5 && user.getAddress().getGeo().getLng() < 100)
                .toList();
    }

    private double calculateCompletedTasksPercentage(List<Todo> todos) {
        long totalTasks = todos.size();
        long completedTasks = todos.stream().filter(Todo::isCompleted).count();
        return (completedTasks * 100.0) / totalTasks;
    }
}
