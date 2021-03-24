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
 * FollowUserData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-24T14:39:40.139+09:00")




public class FollowUserData   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("imgPath")
  private String imgPath = null;

  @JsonProperty("name")
  private String name = null;

  public FollowUserData id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * ユーザID
   * @return id
  **/
  @ApiModelProperty(example = "3", value = "ユーザID")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public FollowUserData imgPath(String imgPath) {
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

  public FollowUserData name(String name) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FollowUserData followUserData = (FollowUserData) o;
    return Objects.equals(this.id, followUserData.id) &&
        Objects.equals(this.imgPath, followUserData.imgPath) &&
        Objects.equals(this.name, followUserData.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, imgPath, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FollowUserData {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    imgPath: ").append(toIndentedString(imgPath)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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

