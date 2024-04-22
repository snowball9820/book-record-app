<div><h3>📚 기술 스택&라이브러리&사용 툴</h3></div>  

#### 기술 스택&라이브러리:  
- AndroidStudio
- Android SDK 33
- Minimum SDK: 26
- Target SDK: 33
- Kotlin: Version 1.8.0
- Java: 17.0.8
- ViewModel
- Navigation
- Room DB
- Coil
- Composable Graphs
- Jetpack Compose
- Google ML Kit `Text Recognition V2` `language identification` `Translation` `Digital Ink Recognition`
- AndroidX Libraries: AndroidX core and lifecycle, Compose UI , Material3 

#### 사용 툴:  
- Git
- Notion
- Figma  

## ⏰프로젝트 기간  
####  분류: 개인프로젝트  
#### 2023년 8월 16일~2023년 8월 22일 

### ✨한 줄 소개:    
다양한 기능(텍스트 인식, 번역, 복사, TTS, 타이머, 다이어리)이 들어있는 독서 기록 용 앱  

### 📑프로젝트 설명  
#### 프로젝트 스크린샷
![image](https://github.com/snowball9820/book-record-app/assets/124758100/a90602c4-a709-40a9-bdbb-7d2da36f6108)  
--------------------
![image](https://github.com/snowball9820/book-record-app/assets/124758100/9841c3ec-5e89-4878-aa16-79df55b2fa2d)  
--------------------
![image](https://github.com/snowball9820/book-record-app/assets/124758100/b9fe7d11-8bba-413f-b778-d403b5e43282)  
----------------------  
#### 프로젝트 시연영상  
<img src="https://github.com/snowball9820/book-record-app/assets/124758100/22ce07ed-e780-425d-a336-e93c80eeb40f" width="250">


### ❗기능구현&도전한 것&깨달은 점(배운 점):  
#### 기능구현  
- 이미지 텍스트 인식 기능 구현
- 텍스트 번역(영어,일본어,중국어)기능 구현
- Room db에 저장, 불러오기, 삭제 기능 구현
- TTS 기능 구현
- 스톱워치 기능 구현
- Animation기능 구현
- 그래프 구현

#### 도전한 것  
→ Room, Navigation, View Model, Jet pack Compose, ML Kit, TTS, Animation

- Room: Room에 데이터를 저장하고 불러오고 삭제하는 과정에서 다양한 쿼리 문을 사용하며 데이터를 이용함
- Navigation: 기존에는 Activity간의 화면 이동을 사용하였는데 이번 프로젝트에서 Compose Navigation을 사용함
- View Model: stopwatch, TTS에 View Model을 사용함
- Jet pack Compose: Navigation, View Model, LaunchedEffect, Coroutine을 사용함
- google ML Kit: Text recognition, Translation, Digital Ink recognition을 이용함
- TTS: TTS(Text To Speech)를 이용하여 번역 문장을 음성으로 출력함
- Animation: Compose Animation 기능 중 slideInHorizontally 사용함

### 깨달은 점&보완할 점  
→ 개인 프로젝트를 통하여 사용해본 적 없는 다양한 기술과 ML Kit, Jet pack Compose 라이브러리를 적절하게 활용하는 능력을 키울 수 있었고 여러가지의 다른 기능들을 합쳐서 하나의 애플리케이션으로 통합하여 연결 시키고 조율할 수 있는 경험을 얻음. 프로젝트를 하면서 다양한 오류와 Logcat 에러를 만나면서 해결하는 과정을 통하여 문제해결 능력도 키울 수 있었음. 또한 애플리케이션을 직접 만들어보고 사용해보는 과정에서 사용자의 입장에서 만드는 것이 중요하다는 생각을 하게 되었음. 추후에는 사용자의 편의성을 고려한 기능을 추가적으로 구현하거나 보완할 예정임.

