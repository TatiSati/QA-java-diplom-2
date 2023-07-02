package step;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import model.*;

import java.util.ArrayList;
import java.util.Collections;


public class BaseMethods {

    public final static String BASE_URI = "https://stellarburgers.nomoreparties.site/";
    private final String email = "tanuysik_94@mail.ru";
    private final String password = "12345Qwerty";
    private final String name = "Татьяна";
    private final String changeEmail = "change_" + email;
    private final String changeName = "change_" + name;
    private final ArrayList<String> ingredients = new ArrayList<>(Collections.singleton("61c0c5a71d1f82001bdaaa6f"));
    private final ArrayList<String> ingredientsWrong = new ArrayList<>(Collections.singleton("12345Qwerty"));



    private final UserRegistration userRegistration = new UserRegistration(email, password, name);
    private final UserLogin userLogin = new UserLogin(email, password);
    private final User user = new User();
    private final UserChangeData userChangeData = new UserChangeData(changeEmail, changeName);
    private final OrderCreate orderCreate = new OrderCreate(ingredients);
    private final Order order = new Order();


    @Before
    public void setUp() {

        RestAssured.baseURI = BASE_URI;
        UserRegistration userRegistration = new UserRegistration(email, password, name);
    }

    @After
    public void cleanUp(){
        Response responseNoChange = getUser().loginUser(getUserLogin());
        if(responseNoChange.jsonPath().get("success").equals(true)){
            getUser().deleteUser(responseNoChange.jsonPath().get("accessToken"));
        }
        Response responseChange = getUser().loginUser(new UserLogin(changeEmail, password));
        if(responseChange.jsonPath().get("success").equals(true)){
            getUser().deleteUser(responseChange.jsonPath().get("accessToken"));
        }
    }

    public String getEmail() {
        return email;
    } // Геттеры позволяют получать значения

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public UserRegistration getUserRegistration() {
        return userRegistration;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public User getUser() {
        return user;
    }


    public String getChangeEmail() {
        return changeEmail;
    }

    public String getChangeName() {
        return changeName;
    }

    public UserChangeData getUserChangeData() {
        return userChangeData;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public OrderCreate getOrderCreate() {
        return orderCreate;
    }

    public Order getOrder() {
        return order;
    }

    public ArrayList<String> getIngredientsWrong() {
        return ingredientsWrong;
    }

}
