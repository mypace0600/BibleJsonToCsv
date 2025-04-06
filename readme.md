# BibleJsonToCsv


### 성경 JSON 데이터 출처 : https://github.com/yuhwan/Bible-krv
### JSON ➡️ CSV 각각의 형식

- JSON 예시
```js
{
  "book":"요한계시록",
  "chapters":[
    {
      "chapter":1,
      "verses":[
        {
          "verse":1,
          "text":"예수 그리스도의 계시라 ..."
        },
        {
          "verse":2,
          "text":"요한은 하나님의 말씀과 ..."
        }
      ]
    }
  ]
}
```

- CSV 예시
```js
계 1:1, 예수 그리스도의 계시라 ...
계 1:2, 요한은 하나님의 말씀과 ...
```


### 제작 이유
Anki 를 통해서 학습하기 위해 csv 파일이 필요했고 마침 성경 json 데이터를 찾게 되어서 이왕에 직접 만들어보자 해서 만들었습니다. 다른 분들도 사용하실 수 있도록 공유 드립니다.


### 화면 및 사용 방법
<img width="500" alt="스크린샷 2025-04-06 오후 6 16 56" src="https://github.com/user-attachments/assets/26c66e5f-5541-4ab0-ae48-cb1cba74c4b0" />
<img width="500" alt="스크린샷 2025-04-06 오후 6 20 09" src="https://github.com/user-attachments/assets/7f543352-6aba-4410-b533-3b1788aee877" />

- CSV 파일로 변환할 JSON 파일의 경로를 입력하고 변환 버튼을 누릅니다.
- 우측 상단에 변환된 CSV 파일을 다운로드 받습니다.
