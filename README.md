# Text_Editor(文字編輯器)
- 利用16個Pattern與物件導向的概念實做出的文字編輯器，撰寫語言為Java。
- Pattern參考了GoF的Design Pattern。
![img](https://github.com/changjam/IMG/blob/master/Text_Editor_1.png)
## 功能
- 使用者可以輸入文字，且可以更換文字的顏色、背景顏色、字形以及字體大小。
- 可以更換文字的排版，置中、左、右。
- 使用者可以自訂喜歡的字體樣式並保存起來，可以供其他文字使用，此外也可以使用系統提供的樣式。
- 系統會判斷使用者是否有打字，若閒置一段時間會進入休眠，保護使用者暫時離開也不會被其他人偷看到內容。
- 系統會對英文首字進行大寫。

## Design Pattens Implemented
1. Singleton pattern 用來確保我們使用者在使用文字編輯器時只會有同一時間只能有一個編輯器。
2. Bridge pattern 會在系統開啟時，判斷當前運行環境是Windows還是Mac，根據運行環境改變外觀和執行。
3. Facade pattern 管理整個系統，分擔了Bridge pattern的工作，使Bridge pattern不會太過龐大。
4. Mediator pattern 管理編輯器介面結構，例如Menu、Scroll、Menu Item等。
5. State pattern 讓系統可以擁有不同狀態來切換，分別為起始狀態、編輯狀態、睡眠狀態，當系統一開啟時會是起始狀態，使用者開始打字會進入編輯狀態，當使用者不操作系統達30秒時會變成睡眠狀態，該狀態會反白編輯區域讓其他人剛好經過也看不到上面打的內容，隨便按一個字即可回到編輯狀態。
6. Composite pattern 用來定義編輯器中各元件的階層，由於我們會使用Html來幫助排版，因此這邊會有一些DOM元素的概念，例如其中會有Body、Span、P的class。
7. Decorator pattern 跟 Composite pattern配合使用，Composite pattern定義組合，而Decorator幫元件加上樣式的操作，例如粗體、斜體、顏色、大小等。
8. Strategy pattern 用來設置不同的字體。
9. Flyweight pattern 用來完成字體樣式，讓使用者可以使用系統預設的樣式，這些樣式由不同字體、對齊方式、字體顏色、粗體、斜體或底線組成。
10. Template pattern 配合Flyweight pattern使用，使樣式可以讓使用者自行定義並儲存。
11. Iterator pattern 用來讓程式能更輕鬆地訪問Composite pattern的組合元件，並配合其他Pattern達成不同效果，例如可以配合Visitor pattern計算字數與行數。
12. Builder pattern 用來建構系統的段落、行距等樣式，我們使用CSS操作各種功能，並將他們拆分成方法，讓我們可以使用相同的建構流程來創建不同的模式。
13. Command pattern 用於呼叫系統的各式功能，例如複製、剪取、貼上，當系統做各種操作時都會先經過Command pattern。
14. Memento pattern 會記錄Command pattern所使用的各種功能，並將狀態儲存起來，當使用者需要還原操作時，會使用該Pattern還原成前一步狀態。
15. Visitor pattern 會拜訪Composite pattern並配合Iterator和Interpreter pattern走訪和解析各元件。
16. Interpreter pattern 配合Visitor pattern對各種傳進來的文字做解析，其中功能有首字大寫(英文)和文字拆分成Token。

## Technology Stack
Java, Swing
