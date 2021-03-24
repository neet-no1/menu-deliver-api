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
 * FavoriteArticleParam
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-24T14:39:40.139+09:00")




public class FavoriteArticleParam   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("added")
  private Boolean added = null;

  public FavoriteArticleParam id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * 記事ID
   * @return id
  **/
  @ApiModelProperty(example = "3", value = "記事ID")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public FavoriteArticleParam added(Boolean added) {
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
    FavoriteArticleParam favoriteArticleParam = (FavoriteArticleParam) o;
    return Objects.equals(this.id, favoriteArticleParam.id) &&
        Objects.equals(this.added, favoriteArticleParam.added);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, added);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FavoriteArticleParam {\n");
    
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

