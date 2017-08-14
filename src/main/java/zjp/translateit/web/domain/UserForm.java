package zjp.translateit.web.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {

    @NotNull
    @Size(min = 3, max = 9)
    @Pattern(regexp = "[0-9a-zA-Z]*")
    private String name;

    @NotNull
    @Size(min = 6, max = 9)
    @Pattern(regexp = "[0-9a-zA-Z]*")
    private String password;

    @NotNull
    @Pattern(regexp = "[a-z]{9}")
    private String verifyCode;

    @NotNull
    @Pattern(regexp = "[a-zA-Z0-9+._%+-]+@[a-zA-Z0-9]+[.][a-zA-Z0-9]+")
    private String email;

    public UserForm() {
    }

    public UserForm(@NotNull @Size(min = 3, max = 9) @Pattern(regexp = "[0-9a-zA-Z]*") String name,
                    @NotNull @Size(min = 6, max = 9) @Pattern(regexp = "[0-9a-zA-Z]*") String password,
                    @NotNull @Pattern(regexp = "[a-z]{9}") String verifyCode,
                    @NotNull @Pattern(regexp = "[a-zA-Z0-9+._%+-]+@[a-zA-Z0-9]+[.][a-zA-Z0-9]+") String email) {
        this.name = name;
        this.password = password;
        this.verifyCode = verifyCode;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getVerifyCode() {
        return verifyCode;
    }
}
