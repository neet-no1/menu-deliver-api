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
 * UpdatePasswordParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-19T19:06:35.752+09:00")




public class UpdatePasswordParam   {
  @JsonProperty("currentPassword")
  private String currentPassword = null;

  @JsonProperty("newPassword")
  private String newPassword = null;

  @JsonProperty("newPasswordConfirm")
  private String newPasswordConfirm = null;

  public UpdatePasswordParam currentPassword(String currentPassword) {
    this.currentPassword = currentPassword;
    return this;
  }

  /**
   * 現在パスワード
   * @return currentPassword
  **/
  @ApiModelProperty(example = "password", value = "現在パスワード")


  public String getCurrentPassword() {
    return currentPassword;
  }

  public void setCurrentPassword(String currentPassword) {
    this.currentPassword = currentPassword;
  }

  public UpdatePasswordParam newPassword(String newPassword) {
    this.newPassword = newPassword;
    return this;
  }

  /**
   * 新規パスワード
   * @return newPassword
  **/
  @ApiModelProperty(example = "password", value = "新規パスワード")


  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }

  public UpdatePasswordParam newPasswordConfirm(String newPasswordConfirm) {
    this.newPasswordConfirm = newPasswordConfirm;
    return this;
  }

  /**
   * 新規パスワード再確認
   * @return newPasswordConfirm
  **/
  @ApiModelProperty(example = "password", value = "新規パスワード再確認")


  public String getNewPasswordConfirm() {
    return newPasswordConfirm;
  }

  public void setNewPasswordConfirm(String newPasswordConfirm) {
    this.newPasswordConfirm = newPasswordConfirm;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdatePasswordParam updatePasswordParam = (UpdatePasswordParam) o;
    return Objects.equals(this.currentPassword, updatePasswordParam.currentPassword) &&
        Objects.equals(this.newPassword, updatePasswordParam.newPassword) &&
        Objects.equals(this.newPasswordConfirm, updatePasswordParam.newPasswordConfirm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currentPassword, newPassword, newPasswordConfirm);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UpdatePasswordParam {\n");
    
    sb.append("    currentPassword: ").append(toIndentedString(currentPassword)).append("\n");
    sb.append("    newPassword: ").append(toIndentedString(newPassword)).append("\n");
    sb.append("    newPasswordConfirm: ").append(toIndentedString(newPasswordConfirm)).append("\n");
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

