# Compose for Web でブラウザのアドレスバーと Navigation Compose の route を同期させてみるサンプル

`NavController.bindBrowserUrl()` で NavController を監視し、
[History API](https://developer.mozilla.org/ja/docs/Web/API/History_API) を使って
ブラウザのアドレスバーに表示されるURLのパスと Navigation Compose の route を一致させてみている。

肝となるコードは以下のコミットに入っている。

- [2847e31](https://github.com/TBSten/cmp-history-api-sample/commit/2847e319771cd76445cf7afeca288d7b13bbafe6)
- [391ed61](https://github.com/TBSten/cmp-history-api-sample/commit/391ed61c5bea8b6eeb3a94a46797bc0baf8bbf36)
