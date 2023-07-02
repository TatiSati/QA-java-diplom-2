import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import model.UserLogin;
import step.BaseMethods;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class UserLoginTest extends BaseMethods {

    @Test
    @DisplayName("Логин под существующим пользователем")
    @Description("Ожидаемый результат: код 200, пользователь авторизовался") // описание теста
    public void existingLogin() {
        getUser().registrationUser(getUserRegistration());
        Response response = getUser().loginUser(getUserLogin());
        response.then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true))
                .and()
                .body("user.email", equalTo(super.getEmail()))
                .and()
                .body("user.name", equalTo(super.getName()))
                .and()
                .body("accessToken", notNullValue())
                .and()
                .body("refreshToken", notNullValue());
    }

    @Test
    @DisplayName("Логин с неверным логином")
    @Description("Ожидаемый результат: код 401, пользователь не авторизовался")
    public void loginWithInvalidEmail() {
        getUser().registrationUser(getUserRegistration());
        UserLogin loginUser = new UserLogin("", super.getPassword());
        Response response = getUser().loginUser(loginUser);
        response.then().assertThat()
                .statusCode(401)
                .and()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test
    @DisplayName("Логин с неверным паролем")
    @Description("Ожидаемый результат: код 401, пользователь не авторизовался")
    public void loginWithInvalidPassword() {
        getUser().registrationUser(getUserRegistration());
        UserLogin loginUser = new UserLogin(super.getEmail(), "");
        Response response = getUser().loginUser(loginUser);
        response.then()
                .assertThat()
                .statusCode(401)
                .and()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("email or password are incorrect"));
    }
}

