import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import step.BaseMethods;

import static org.hamcrest.Matchers.equalTo;

public class UserChangeDataTest extends BaseMethods {

    @Test
    @DisplayName("Изменение имени и электронной почты для авторизованного пользователя")
    @Description("Ожидаемый результат: код 200, данные пользователя изменены")
    public void userChangeDataWithAuthorization() {
        Response responseCreate = getUser().registrationUser(getUserRegistration());
        String accessToken = responseCreate.jsonPath().get("accessToken");
        Response responseChange = getUser().changeUserData(getUserChangeData(), accessToken);
        responseChange.then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("success", equalTo(true))
                .and()
                .body("user.email", equalTo(super.getChangeEmail()))
                .and()
                .body("user.name", equalTo(super.getChangeName()));
    }

    @Test
    @DisplayName("Изменить имя и адрес электронной почты для неавторизованного пользователя.")
    @Description("Ожидаемый результат: код 401, данные пользователя не изменены")
    public void userChangeDataWithoutAuthorization() {
        Response responseChange = getUser().changeUserData(getUserChangeData(), "");
        responseChange.then()
                .assertThat()
                .statusCode(401)
                .and()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("You should be authorised"));
    }

}
