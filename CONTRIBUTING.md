# Contribution Guide
コントリビューションのガイドライン

# コントリビューション手順

1. Issueを作成する
2. Issueで対応方法を検討する
3. 対応方法が決まったら、リポジトリをクローンする(git clone https://github.com/neet-no1/menu-deliver-api.git)
4. 修正するブランチに切り替える(git checkout {ブランチ名})
5. ブランチを最新状態とする(git pull)
6. 修正用ブランチを作成する(git checkout -b feature/{Issue番号}-{任意の名前})
7. 修正を行う
8. 修正箇所を追加する(git add {修正ファイル})
9. コミットする(git commit -m "コメント")
10. プッシュする(git push origin HEAD)
11. githubをブラウザで開くと、プルリクエストを作成するボタンがあるので、作成する
12. プルリクエストには修正内容を細かく記入する(レビューがしやすいようにするため)
