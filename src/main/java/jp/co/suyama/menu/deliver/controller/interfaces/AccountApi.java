/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.19-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package jp.co.suyama.menu.deliver.controller.interfaces;

import jp.co.suyama.menu.deliver.model.auto.AccountAuthResponse;
import jp.co.suyama.menu.deliver.model.auto.AccountResponse;
import jp.co.suyama.menu.deliver.model.auto.ArticlesResponse;
import jp.co.suyama.menu.deliver.model.auto.BasicResponse;
import jp.co.suyama.menu.deliver.model.auto.MenusAndArticlesResponse;
import jp.co.suyama.menu.deliver.model.auto.MenusResponse;
import jp.co.suyama.menu.deliver.model.auto.RegistAccountParam;
import org.springframework.core.io.Resource;
import jp.co.suyama.menu.deliver.model.auto.UpdatePasswordParam;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-03-24T14:39:40.139+09:00")

@Validated
@Api(value = "account", description = "the account API")
@RequestMapping(value = "")
public interface AccountApi {

    @ApiOperation(value = "メールアドレス有効性確認", nickname = "emailConfirm", notes = "リクエストのワンタイムパスワードがあっているか確認する 認証不要 ", response = BasicResponse.class, tags={ "Account", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = BasicResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/account/email/confirm",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<BasicResponse> emailConfirm(@NotNull @ApiParam(value = "メールアドレスの有効性確認パスワード", required = true) @Valid @RequestParam(value = "oneTimePassword", required = true) String oneTimePassword);


    @ApiOperation(value = "ログイン状態取得", nickname = "getAccountAuth", notes = "ログイン状態を取得する ログインしていればユーザのアイコンを取得する 認証不要 ", response = AccountAuthResponse.class, tags={ "Account", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = AccountAuthResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/account/auth",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<AccountAuthResponse> getAccountAuth();


    @ApiOperation(value = "アカウント情報取得", nickname = "getAccountInfo", notes = "マイページに表示する情報を取得 認証必須 ", response = AccountResponse.class, tags={ "Account", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = AccountResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/account/info",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<AccountResponse> getAccountInfo();


    @ApiOperation(value = "お気に入り一覧を取得", nickname = "getFavoriteItems", notes = "お気に入りの記事と献立の一覧を取得する 認証必須 ", response = MenusAndArticlesResponse.class, tags={ "Favorite", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = MenusAndArticlesResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/account/favorites",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<MenusAndArticlesResponse> getFavoriteItems(@ApiParam(value = "献立取得ページ番号(1~)", defaultValue = "1") @Valid @RequestParam(value = "menuPage", required = false, defaultValue="1") Integer menuPage,@ApiParam(value = "記事取得ページ番号(1~)", defaultValue = "1") @Valid @RequestParam(value = "articlePage", required = false, defaultValue="1") Integer articlePage);


    @ApiOperation(value = "投稿記事一覧取得", nickname = "getPostedArticles", notes = "自身が投稿した記事の一覧を取得 認証必須 ", response = ArticlesResponse.class, tags={ "Article", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = ArticlesResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/account/posted/articles",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<ArticlesResponse> getPostedArticles(@ApiParam(value = "取得ページ番号(1~)", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue="1") Integer page);


    @ApiOperation(value = "投稿献立一覧取得", nickname = "getPostedMenus", notes = "自身が投稿した献立の一覧を取得 認証必須 ", response = MenusResponse.class, tags={ "Menu", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = MenusResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/account/posted/menus",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<MenusResponse> getPostedMenus(@ApiParam(value = "取得ページ番号(1~)", defaultValue = "1") @Valid @RequestParam(value = "page", required = false, defaultValue="1") Integer page);


    @ApiOperation(value = "アカウント登録", nickname = "registAccount", notes = "アカウント登録処理を行う メールアドレスにワンタイムパスワードを付与して、メールアドレスが有効か確認する 認証不要 ", response = BasicResponse.class, tags={ "Account", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = BasicResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/account/regist",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<BasicResponse> registAccount(@ApiParam(value = "" ,required=true )  @Valid @RequestBody RegistAccountParam registAccountParam);


    @ApiOperation(value = "アカウント情報更新", nickname = "updateAccountInfo", notes = "マイページに表示する情報を更新 認証必須 ", response = BasicResponse.class, tags={ "Account", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = BasicResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/account/info/update",
        produces = { "application/json" }, 
        consumes = { "multipart/form-data" },
        method = RequestMethod.POST)
    ResponseEntity<BasicResponse> updateAccountInfo(@ApiParam(value = "ニックネーム", required=true) @RequestParam(value="name", required=true)  String name,@ApiParam(value = "メールアドレス", required=true) @RequestParam(value="email", required=true)  String email,@ApiParam(value = "ユーザアイコン画像ファイル") @Valid @RequestPart(value="icon", required=true) MultipartFile icon);


    @ApiOperation(value = "パスワード更新", nickname = "updatePassword", notes = "ログインパスワードを変更する 認証必須 ", response = BasicResponse.class, tags={ "Account", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "通信成功時の返却値", response = BasicResponse.class),
        @ApiResponse(code = 400, message = "200以外の時のは通信失敗をクライアントに通達") })
    @RequestMapping(value = "/account/password/update",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<BasicResponse> updatePassword(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UpdatePasswordParam updatePasswordParam);

}
