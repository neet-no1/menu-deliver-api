package jp.co.suyama.menu.deliver.model.auto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import jp.co.suyama.menu.deliver.model.auto.ErrorInfo;
import jp.co.suyama.menu.deliver.model.auto.MenuDeliverResponse;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * CompositionsResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-24T14:39:40.139+09:00")




public class CompositionsResponse extends MenuDeliverResponse  {
  @JsonProperty("code")
  private Integer code = null;

  @JsonProperty("errorInfo")
  private ErrorInfo errorInfo = null;

  @JsonProperty("info")
  @Valid
  private List<Object> info = null;

  public CompositionsResponse code(Integer code) {
    this.code = code;
    return this;
  }

  /**
   * 0以外はエラーコード
   * @return code
  **/
  @ApiModelProperty(example = "0", value = "0以外はエラーコード")


  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public CompositionsResponse errorInfo(ErrorInfo errorInfo) {
    this.errorInfo = errorInfo;
    return this;
  }

  /**
   * Get errorInfo
   * @return errorInfo
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ErrorInfo getErrorInfo() {
    return errorInfo;
  }

  public void setErrorInfo(ErrorInfo errorInfo) {
    this.errorInfo = errorInfo;
  }

  public CompositionsResponse info(List<Object> info) {
    this.info = info;
    return this;
  }

  public CompositionsResponse addInfoItem(Object infoItem) {
    if (this.info == null) {
      this.info = new ArrayList<Object>();
    }
    this.info.add(infoItem);
    return this;
  }

  /**
   * Get info
   * @return info
  **/
  @ApiModelProperty(value = "")


  public List<Object> getInfo() {
    return info;
  }

  public void setInfo(List<Object> info) {
    this.info = info;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompositionsResponse compositionsResponse = (CompositionsResponse) o;
    return Objects.equals(this.code, compositionsResponse.code) &&
        Objects.equals(this.errorInfo, compositionsResponse.errorInfo) &&
        Objects.equals(this.info, compositionsResponse.info) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, errorInfo, info, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CompositionsResponse {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    errorInfo: ").append(toIndentedString(errorInfo)).append("\n");
    sb.append("    info: ").append(toIndentedString(info)).append("\n");
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

