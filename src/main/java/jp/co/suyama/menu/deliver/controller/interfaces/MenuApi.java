/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.19-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package jp.co.suyama.menu.deliver.controller.interfaces;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jp.co.suyama.menu.deliver.model.auto.BasicResponse;
import jp.co.suyama.menu.deliver.model.auto.CompositionsResponse;
import jp.co.suyama.menu.deliver.model.auto.DeleteMenuParam;
import jp.co.suyama.menu.deliver.model.auto.FavoriteMenuParam;
import jp.co.suyama.menu.deliver.model.auto.MenuCategoriesResponse;
import jp.co.suyama.menu.deliver.model.auto.MenuDataResponse;
import jp.co.suyama.menu.deliver.model.auto.MenusResponse;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-30T01:21:02.180+09:00")

@Validated
@Api(value = "menu", description = "the menu API")
@RequestMapping(value = "")
public interface MenuApi {

    @ApiOperation(value = "献立削除", nickname = "deleteMenu", notes = "献立を削除 認証必須 ", response = BasicResponse.class, tags = {
            "Menu", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "通信成功時の返却値", response = BasicResponse.class),
            @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/menu/delete", produces = { "application/json" }, method = RequestMethod.POST)
    ResponseEntity<BasicResponse> deleteMenu(
            @ApiParam(value = "", required = true) @Valid @RequestBody DeleteMenuParam deleteArticleParam);

    @ApiOperation(value = "献立お気に入り追加・解除", nickname = "favoriteMenu", notes = "献立のお気に入りの追加と解除を行う 認証必須 ", response = BasicResponse.class, tags = {
            "Menu", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "通信成功時の返却値", response = BasicResponse.class),
            @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/menu/favorite", produces = { "application/json" }, method = RequestMethod.POST)
    ResponseEntity<BasicResponse> favoriteMenu(
            @ApiParam(value = "", required = true) @Valid @RequestBody FavoriteMenuParam favoriteMenuParam);

    @ApiOperation(value = "食品成分表情報取得", nickname = "getCompositions", notes = "食品成分表のデータ一覧を取得する 認証不要 ", response = CompositionsResponse.class, tags = {
            "Menu", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "通信成功時の返却値", response = CompositionsResponse.class),
            @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/menu/compositions", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<CompositionsResponse> getCompositions();

    @ApiOperation(value = "献立内容取得", nickname = "getMenu", notes = "献立内容を取得する S3のパスを取得し、内容はS3から取得する 投稿済み：認証不要 編集中：認証必須 ", response = MenuDataResponse.class, tags = {
            "Menu", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "通信成功時の返却値", response = MenuDataResponse.class),
            @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/menu", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<MenuDataResponse> getMenu(
            @NotNull @ApiParam(value = "献立ID", required = true) @Valid @RequestParam(value = "id", required = true) Integer id);

    @ApiOperation(value = "献立カテゴリ取得", nickname = "getMenuCategories", notes = "献立のカテゴリ一覧を取得する 認証不要 ", response = MenuCategoriesResponse.class, tags = {
            "Menu", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "通信成功時の返却値", response = MenuCategoriesResponse.class),
            @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/menu/categories", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<MenuCategoriesResponse> getMenuCategories();

    @ApiOperation(value = "新着献立取得", nickname = "getMenuNewArrival", notes = "最近の投稿順(更新日時)で献立情報を取得 認証不要 ", response = MenusResponse.class, tags = {
            "Menu", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "通信成功時の返却値", response = MenusResponse.class),
            @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/menu/newarrival", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<MenusResponse> getMenuNewArrival();

    @ApiOperation(value = "人気献立取得", nickname = "getMenuPopular", notes = "直近の閲覧数が多い献立を取得 認証不要 ", response = MenusResponse.class, tags = {
            "Menu", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "通信成功時の返却値", response = MenusResponse.class),
            @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/menu/popular", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<MenusResponse> getMenuPopular();

    @ApiOperation(value = "献立投稿", nickname = "postMenu", notes = "献立を投稿する S3へアップロードする 認証必須 ", response = BasicResponse.class, tags = {
            "Menu", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "通信成功時の返却値", response = BasicResponse.class),
            @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/menu", produces = { "application/json" }, consumes = {
            "multipart/form-data" }, method = RequestMethod.POST)
    ResponseEntity<BasicResponse> postMenu(
            @ApiParam(value = "献立ID　存在しない場合は0", required = true) @RequestParam(value = "id", required = true) Integer id,
            @ApiParam(value = "献立のタイトル", required = true) @RequestParam(value = "title", required = true) String title,
            @ApiParam(value = "カテゴリID", required = true) @RequestParam(value = "category", required = true) Integer category,
            @ApiParam(value = "内容", required = true) @RequestParam(value = "contents", required = true) String contents,
            @ApiParam(value = "投稿/保存のフラグ", required = true) @RequestParam(value = "opened", required = true) Boolean opened,
            @ApiParam(value = "献立のサブタイトル") @RequestParam(value = "subTitle", required = false) String subTitle,
            @ApiParam(value = "献立のサムネイル画像") @Valid @RequestPart(value = "thumb", required = false) MultipartFile thumb,
            @ApiParam(value = "作り方") @RequestParam(value = "cookery", required = false) String cookery,
            @ApiParam(value = "ファイルリスト") @RequestParam(value = "files", required = false) List<MultipartFile> files,
            @ApiParam(value = "ファイルの説明") @RequestParam(value = "filesDescription", required = false) List<String> filesDescription);

}
