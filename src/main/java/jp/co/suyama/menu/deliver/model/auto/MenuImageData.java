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
 * MenuImageData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-24T14:39:40.139+09:00")




public class MenuImageData   {
  @JsonProperty("imageDescription")
  private String imageDescription = null;

  @JsonProperty("uploadImageUrl")
  private String uploadImageUrl = null;

  public MenuImageData imageDescription(String imageDescription) {
    this.imageDescription = imageDescription;
    return this;
  }

  /**
   * 画像の説明
   * @return imageDescription
  **/
  @ApiModelProperty(example = "焼いている途中です", value = "画像の説明")


  public String getImageDescription() {
    return imageDescription;
  }

  public void setImageDescription(String imageDescription) {
    this.imageDescription = imageDescription;
  }

  public MenuImageData uploadImageUrl(String uploadImageUrl) {
    this.uploadImageUrl = uploadImageUrl;
    return this;
  }

  /**
   * 画像のパス
   * @return uploadImageUrl
  **/
  @ApiModelProperty(example = "/public/menu_images/xxx", value = "画像のパス")


  public String getUploadImageUrl() {
    return uploadImageUrl;
  }

  public void setUploadImageUrl(String uploadImageUrl) {
    this.uploadImageUrl = uploadImageUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenuImageData menuImageData = (MenuImageData) o;
    return Objects.equals(this.imageDescription, menuImageData.imageDescription) &&
        Objects.equals(this.uploadImageUrl, menuImageData.uploadImageUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(imageDescription, uploadImageUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MenuImageData {\n");
    
    sb.append("    imageDescription: ").append(toIndentedString(imageDescription)).append("\n");
    sb.append("    uploadImageUrl: ").append(toIndentedString(uploadImageUrl)).append("\n");
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

