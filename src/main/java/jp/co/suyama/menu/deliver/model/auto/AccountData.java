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
 * AccountData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-19T19:06:35.752+09:00")




public class AccountData   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("imgPath")
  private String imgPath = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("email")
  private String email = null;

  public AccountData id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * ユーザID
   * @return id
  **/
  @ApiModelProperty(example = "1", value = "ユーザID")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public AccountData imgPath(String imgPath) {
    this.imgPath = imgPath;
    return this;
  }

  /**
   * ユーザアイコン画像のパス
   * @return imgPath
  **/
  @ApiModelProperty(example = "/public/user_images/xxx", value = "ユーザアイコン画像のパス")


  public String getImgPath() {
    return imgPath;
  }

  public void setImgPath(String imgPath) {
    this.imgPath = imgPath;
  }

  public AccountData name(String name) {
    this.name = name;
    return this;
  }

  /**
   * ニックネーム
   * @return name
  **/
  @ApiModelProperty(example = "サンプル太朗", value = "ニックネーム")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AccountData email(String email) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountData accountData = (AccountData) o;
    return Objects.equals(this.id, accountData.id) &&
        Objects.equals(this.imgPath, accountData.imgPath) &&
        Objects.equals(this.name, accountData.name) &&
        Objects.equals(this.email, accountData.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, imgPath, name, email);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountData {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    imgPath: ").append(toIndentedString(imgPath)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
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

