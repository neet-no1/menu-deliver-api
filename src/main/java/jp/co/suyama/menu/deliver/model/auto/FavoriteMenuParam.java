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
 * FavoriteMenuParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-19T19:06:35.752+09:00")




public class FavoriteMenuParam   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("added")
  private Boolean added = null;

  public FavoriteMenuParam id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * 献立ID
   * @return id
  **/
  @ApiModelProperty(example = "3", value = "献立ID")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public FavoriteMenuParam added(Boolean added) {
    this.added = added;
    return this;
  }

  /**
   * お気に入りに追加した
   * @return added
  **/
  @ApiModelProperty(example = "true", value = "お気に入りに追加した")


  public Boolean isAdded() {
    return added;
  }

  public void setAdded(Boolean added) {
    this.added = added;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FavoriteMenuParam favoriteMenuParam = (FavoriteMenuParam) o;
    return Objects.equals(this.id, favoriteMenuParam.id) &&
        Objects.equals(this.added, favoriteMenuParam.added);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, added);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FavoriteMenuParam {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    added: ").append(toIndentedString(added)).append("\n");
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

