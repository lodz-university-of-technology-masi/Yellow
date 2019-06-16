// let urlWikiPL = 'https://pl.wikipedia.org/w/index.php?search=';
let urlWikiEN = 'https://en.wikipedia.org/w/index.php?search=';
// let urlSynonimsPL = 'https://synonim.net/synonim/';
let urlSynonimsEN = 'https://www.wordreference.com/synonyms/';

function checkKey(e) {
  if (e.code == 'KeyQ') {
    // console.log(getSelectionText());
    let text = getSelectionText();
    text.replace(/\s/g,'+');
    window.open(urlWikiEN + text);
  } else if (e.code == 'KeyS') {
    // console.log(getSelectionText());
    let text = getSelectionText();
    window.open(urlSynonimsEN + text);
  }
}

function getSelectionText() {
    let text = "";
    let activeEl = document.activeElement;
    let activeElTagName = activeEl ? activeEl.tagName.toLowerCase() : null;
    if (
      (activeElTagName == "textarea") || (activeElTagName == "input" &&
      /^(?:text|search|password|tel|url)$/i.test(activeEl.type)) &&
      (typeof activeEl.selectionStart == "number")
    ) {
        text = activeEl.value.slice(activeEl.selectionStart, activeEl.selectionEnd);
    } else if (window.getSelection) {
        text = window.getSelection().toString();
    }
    return text;
}

document.addEventListener('keypress', checkKey);
