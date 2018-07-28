# 競技プログラミング(Java)

IntelliJ IDEAのCHelperプラグインを利用した環境

## 使い方

### IntelliJ IDEAインストール
学生版では無料でUltimateを利用出来る
(Javaのみの利用であるため、Communityでも問題ない)
https://www.jetbrains.com/idea/
https://www.jetbrains.com/shop/eform/students

#### (日本語化)
Pleiadesのプラグインで簡単に日本語化出来る
http://mergedoc.osdn.jp/

### CHelperインストール
起動画面の[構成]>[プラグイン]>[リポジトリーの参照]を開き、「CHelper」と検索する

AtCoderやGCJ等で利用する場合、[CHelper extension](https://chrome.google.com/webstore/detail/chelper-extension/eicjndbmlajfjdhephbcjdeegmmoadip)をChromeに入れるとより便利

### CHelper設定
このリポジトリをクローンして、IDEAで開く

or

新規Javaプロジェクトを作成する(名前以外はほぼデフォルトでたぶん大丈夫)  
歯車マークをクリックすると、[Project settings]が開く  
特に設定しなくても問題ないけど、Use smart testingはOFFにした方が良いと思う(3ケース中1つ目がWAした時にそれ以降実行されなかったりする)

### コーディング
1. ⊕ボタンから名前とContest nameを入力し、[Edit tests]で入力と出力を追加する  
   AtCoderやGCJの場合、問題ページ(AtCoderはbetaでない方)を開いた状態でChromeの右上(拡張機能覧)の[Parse task]をクリックすると、自動的に問題をパースする
1. solve()で引数でScannerとPrintWriterが与えられるから、それを利用してコードを書く
1. 実行ボタンを押すと、テストケースに対する可否が表示される
1. 実行後、[Copy Source]ボタンを押すと提出用のコードをコピーする
