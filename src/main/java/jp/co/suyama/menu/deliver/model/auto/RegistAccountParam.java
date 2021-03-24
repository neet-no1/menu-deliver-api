package jp.co.suyama.menu.deliver.model.auto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * RegistAccountParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-24T14:39:40.139+09:00")




public class RegistAccountParam   {
  @JsonProperty("email")
  private String email = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("passwordConfirm")
  private String passwordConfirm = null;

  public RegistAccountParam email(String email) {
    this.email = email;
    return this;
  }

  /**
   * メールアドレス
   * @return email
  **/
  @ApiModelProperty(example = "aaa.bbb@gmail.com", value = "メールアドレス")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public RegistAccountParam password(String password) {
    this.password = password;
    return this;
  }

  /**
   * パスワード
   * @return password
  **/
  @ApiModelProperty(example = "password", value = "パスワード")


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public RegistAccountParam passwordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
    return this;
  }

  /**
   * パスワード再確認
   * @return passwordConfirm
  **/
  @ApiModelProperty(example = "password", value = "パスワード再確認")


  public String getPasswordConfirm() {
    return passwordConfirm;
  }

  public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegistAccountParam registAccountParam = (RegistAccountParam) o;
    return Objects.equals(this.email, registAccountParam.email) &&
        Objects.equals(this.password, registAccountParam.password) &&
        Objects.equals(this.passwordConfirm, registAccountParam.passwordConfirm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password, passwordConfirm);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegistAccountParam {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    passwordConfirm: ").append(toIndentedString(passwordConfirm)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

