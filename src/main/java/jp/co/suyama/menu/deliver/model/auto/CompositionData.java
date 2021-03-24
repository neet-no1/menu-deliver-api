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
 * CompositionData
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-24T14:39:40.139+09:00")




public class CompositionData   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("refuse")
  private Integer refuse = null;

  @JsonProperty("energy")
  private Integer energy = null;

  @JsonProperty("protein")
  private Double protein = null;

  @JsonProperty("lipid")
  private Double lipid = null;

  @JsonProperty("carbohydrate")
  private Double carbohydrate = null;

  @JsonProperty("calcium")
  private Integer calcium = null;

  @JsonProperty("iron")
  private Double iron = null;

  @JsonProperty("cholesterol")
  private Integer cholesterol = null;

  @JsonProperty("dietaryFibers")
  private Double dietaryFibers = null;

  @JsonProperty("saltEquivalents")
  private Double saltEquivalents = null;

  public CompositionData id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * 食品ID
   * @return id
  **/
  @ApiModelProperty(example = "123", value = "食品ID")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public CompositionData name(String name) {
    this.name = name;
    return this;
  }

  /**
   * 食品名
   * @return name
  **/
  @ApiModelProperty(example = "かぶ　葉　生", value = "食品名")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CompositionData refuse(Integer refuse) {
    this.refuse = refuse;
    return this;
  }

  /**
   * 廃棄率(%)
   * @return refuse
  **/
  @ApiModelProperty(example = "30", value = "廃棄率(%)")


  public Integer getRefuse() {
    return refuse;
  }

  public void setRefuse(Integer refuse) {
    this.refuse = refuse;
  }

  public CompositionData energy(Integer energy) {
    this.energy = energy;
    return this;
  }

  /**
   * エネルギー(kcal)
   * @return energy
  **/
  @ApiModelProperty(example = "20", value = "エネルギー(kcal)")


  public Integer getEnergy() {
    return energy;
  }

  public void setEnergy(Integer energy) {
    this.energy = energy;
  }

  public CompositionData protein(Double protein) {
    this.protein = protein;
    return this;
  }

  /**
   * たんぱく質(g)
   * @return protein
  **/
  @ApiModelProperty(example = "2.3", value = "たんぱく質(g)")


  public Double getProtein() {
    return protein;
  }

  public void setProtein(Double protein) {
    this.protein = protein;
  }

  public CompositionData lipid(Double lipid) {
    this.lipid = lipid;
    return this;
  }

  /**
   * 脂質(g)
   * @return lipid
  **/
  @ApiModelProperty(example = "0.1", value = "脂質(g)")


  public Double getLipid() {
    return lipid;
  }

  public void setLipid(Double lipid) {
    this.lipid = lipid;
  }

  public CompositionData carbohydrate(Double carbohydrate) {
    this.carbohydrate = carbohydrate;
    return this;
  }

  /**
   * 炭水化物(g)
   * @return carbohydrate
  **/
  @ApiModelProperty(example = "3.9", value = "炭水化物(g)")


  public Double getCarbohydrate() {
    return carbohydrate;
  }

  public void setCarbohydrate(Double carbohydrate) {
    this.carbohydrate = carbohydrate;
  }

  public CompositionData calcium(Integer calcium) {
    this.calcium = calcium;
    return this;
  }

  /**
   * カルシウム(mg)
   * @return calcium
  **/
  @ApiModelProperty(example = "250", value = "カルシウム(mg)")


  public Integer getCalcium() {
    return calcium;
  }

  public void setCalcium(Integer calcium) {
    this.calcium = calcium;
  }

  public CompositionData iron(Double iron) {
    this.iron = iron;
    return this;
  }

  /**
   * 鉄(mg)
   * @return iron
  **/
  @ApiModelProperty(example = "2.1", value = "鉄(mg)")


  public Double getIron() {
    return iron;
  }

  public void setIron(Double iron) {
    this.iron = iron;
  }

  public CompositionData cholesterol(Integer cholesterol) {
    this.cholesterol = cholesterol;
    return this;
  }

  /**
   * コレステロール(mg)
   * @return cholesterol
  **/
  @ApiModelProperty(example = "0", value = "コレステロール(mg)")


  public Integer getCholesterol() {
    return cholesterol;
  }

  public void setCholesterol(Integer cholesterol) {
    this.cholesterol = cholesterol;
  }

  public CompositionData dietaryFibers(Double dietaryFibers) {
    this.dietaryFibers = dietaryFibers;
    return this;
  }

  /**
   * 食物繊維
   * @return dietaryFibers
  **/
  @ApiModelProperty(example = "2.9", value = "食物繊維")


  public Double getDietaryFibers() {
    return dietaryFibers;
  }

  public void setDietaryFibers(Double dietaryFibers) {
    this.dietaryFibers = dietaryFibers;
  }

  public CompositionData saltEquivalents(Double saltEquivalents) {
    this.saltEquivalents = saltEquivalents;
    return this;
  }

  /**
   * 食塩相当量(g)
   * @return saltEquivalents
  **/
  @ApiModelProperty(example = "0.0", value = "食塩相当量(g)")


  public Double getSaltEquivalents() {
    return saltEquivalents;
  }

  public void setSaltEquivalents(Double saltEquivalents) {
    this.saltEquivalents = saltEquivalents;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CompositionData compositionData = (CompositionData) o;
    return Objects.equals(this.id, compositionData.id) &&
        Objects.equals(this.name, compositionData.name) &&
        Objects.equals(this.refuse, compositionData.refuse) &&
        Objects.equals(this.energy, compositionData.energy) &&
        Objects.equals(this.protein, compositionData.protein) &&
        Objects.equals(this.lipid, compositionData.lipid) &&
        Objects.equals(this.carbohydrate, compositionData.carbohydrate) &&
        Objects.equals(this.calcium, compositionData.calcium) &&
        Objects.equals(this.iron, compositionData.iron) &&
        Objects.equals(this.cholesterol, compositionData.cholesterol) &&
        Objects.equals(this.dietaryFibers, compositionData.dietaryFibers) &&
        Objects.equals(this.saltEquivalents, compositionData.saltEquivalents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, refuse, energy, protein, lipid, carbohydrate, calcium, iron, cholesterol, dietaryFibers, saltEquivalents);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CompositionData {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    refuse: ").append(toIndentedString(refuse)).append("\n");
    sb.append("    energy: ").append(toIndentedString(energy)).append("\n");
    sb.append("    protein: ").append(toIndentedString(protein)).append("\n");
    sb.append("    lipid: ").append(toIndentedString(lipid)).append("\n");
    sb.append("    carbohydrate: ").append(toIndentedString(carbohydrate)).append("\n");
    sb.append("    calcium: ").append(toIndentedString(calcium)).append("\n");
    sb.append("    iron: ").append(toIndentedString(iron)).append("\n");
    sb.append("    cholesterol: ").append(toIndentedString(cholesterol)).append("\n");
    sb.append("    dietaryFibers: ").append(toIndentedString(dietaryFibers)).append("\n");
    sb.append("    saltEquivalents: ").append(toIndentedString(saltEquivalents)).append("\n");
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

