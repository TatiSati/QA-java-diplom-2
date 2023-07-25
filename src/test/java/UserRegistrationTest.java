import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import step.BaseMethods;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


public class UserRegistrationTest extends BaseMethods {

    @Test
    @DisplayName("Создание уникального пользователя")
    @Description("Ожидаемый результат: код 201, пользователь создан")
    public void registrationNewUser() {
        // отправляет запрос и сохраняет ответ в переменную response, экзмепляр класса Response
        Response response = getUser().registrationUser(getUserRegistration());
        // проверяет, что в теле ответа ключам success, email, name соответствует успеху (success), email и имени пользователя, а Матчер notNullValue() проверяет, что аргумент метода assertThat — не null-значение
        response.then().assertThat()
                // проверяем, что статус-код ответа равен 200
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
    @DisplayName("Создать пользователя, который уже зарегистрирован")
    @Description("Ожидаемый результат: код 403, пользователь существует")
    public void registrationUserAlreadyExist() {
        getUser().registrationUser(getUserRegistration());
        Response response = getUser().registrationUser(getUserRegistration());
        response.then().assertThat()
                .statusCode(403)
                .and()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("User already exists"));
    }

    /*@After
    public void deleteUser() {

        if (userAction.getUserToken(userCard) != null) {
            userAction.deleteRequestRemoveUser(userCard);
        }
    }*/
}
