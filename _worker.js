export default {
  async fetch(request, env, ctx) {
    const url = new URL(request.url);

    // リライトルール: `.js`で終わらないリクエストを`index.html`にリダイレクト
    if (!url.pathname.match(/\.js$/)) {
      url.pathname = "/index.html";
      return fetch(url.toString(), request);
    }

    // `.js`ファイルのリクエストはそのまま返す
    return fetch(request);
  },
};
