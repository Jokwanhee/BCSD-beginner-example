#  BCSD-beginner-example 😎
## 👀 목차
[🚀 1. Kotlin and Android](#-1-about-kotlin-and-android)  
[🚀 2. View : Wiget and Layout](#-2-view-widget-and-layout)   
[🚀 3. Rescoure : Style and Theme etc...](#-3-style과-theme-resource)   
[🚀 4. Activity and View 상호작용](#-4-activity와-view-상호작용)   
[🚀 5. Fragment and Dialog](#-5-fragment-와-dialog)   
[🚀 6. ListView and RecyclerView](#-6-listview-and-recyclerview)   
[🚀 7. BottomNavigation, ViewPager2 and TabLayout](#-7-bottomnavigation-and-viewpager2-tablayout)   
[🚀 8. Notification](#-8-notification)   
[🚀 9. ContentProvider and MediaStore](#-9-파일관리-contentprovider-mediastore)   
[🚀 10. Service, BroadcastReceiver and MediaPlayer](#-10-foregroundservice-broadcastreceiver-mediaplayer)   
[🚀 11. Thread, Coroutine](#-11-thread-coroutine)   
[🚀 12. Design Pattern, Jetpack Component](#-12-design-pattern-databinding-room-viewmodel-livedata)   
[]()   
[]()
___
## 🚀 1. About Kotlin and Android
[Kotlin  |  Kotlin Docs](https://kotlinlang.org/docs/home.html)

Kotlin 에 대해서 소개하고 다양한 문법과 언어의 특징을 소개한다. 

[Android 스튜디오 다운로드 및 설치  |  Android Developers](https://developer.android.com/codelabs/basic-android-kotlin-compose-install-android-studio?hl=ko#0)

[Android 버전  |  Android Developers](https://developer.android.com/about/versions?hl=ko)

Android 에서 사용되는 IDE 인 Android Studio의 설치와 Android 의 다양한 버전과 특징에 대해서 알아볼 수 있다.
## 🚀 2. View, Widget and Layout
[View  |  Android Developers](https://developer.android.com/reference/android/view/View)

유저가 상호작용하는 화면을 직접그려볼 수 있다. View와 Widget, Layout 의 개념에 대해서 알아볼 수 있고, 다양한 Widget과 Layout 을 실습할 수 있다.

레이아웃의 계층 구조를 이해하며 XML 구조를 설계하는 것에 있어서 중첩된 레이아웃 계층 구조를 피할 수 있다.

### 🐼 실습 : Layout
- LinearLayout

[LinearLayout  |  Android Developers](https://developer.android.com/guide/topics/ui/layout/linear?hl=ko)

<img src="./참조 파일/linear.png" width="350" height="300">

- RelativeLayout

[RelativeLayout  |  Android Developers](https://developer.android.com/guide/topics/ui/layout/relative?hl=ko)

<img src="./참조 파일/relative.png" width="350" height="300">

- FrameLayout

[FrameLayout  |  Android Developers](https://developer.android.com/reference/android/widget/FrameLayout)

<img src="./참조 파일/frame.png" width="350" height="300">

- ConstraintLayout 

[ConstraintLayout  |  Android Developers](https://developer.android.com/training/constraint-layout?hl=ko)


<img src="./참조 파일/constraint.png" width="350" height="300">

## 🚀 3. Style과 Theme, Resource
[앱 리소스 개요  |  Android 개발자  |  Android Developers](https://developer.android.com/guide/topics/resources/providing-resources?hl=ko)

리소스 파일을 가져오기 위해서 R클래스의 정적 변수들을 가져오는 것을 배울 수 있다. 또한, 속성으로 Theme와 Style을 지정하여 좀 더 편리하고 일관적인 디자인을 부여할 수 있다.

### 🐼 실습 : 세로-가로 모드, 글꼴 크기, 디스플레이 크기, 언어 대응
<img src="./참조 파일/chapter03_practice1.gif" width="175" height="350">
<img src="./참조 파일/chapter03_practice2.gif" width="175" height="350">

## 🚀 4. Activity와 View 상호작용
이벤트 리스너를 통해 View의 작동을 확인하고, 이벤트 핸들러를 통해 원하는 동작을 할 수 있다. onClick 과 TextWatcher 를 배우고, Programmatically 하게 명령형 UI로 동작할 수 있다. View의 접근하기 위해서 findByViewId 메서드를 사용방법을 익힐 수 있다. 

Android 4대 컴포넌트 중 Activity 에 대해서 배운다. 생명 주기, Activity Result API를 배울 수 있으며, 그 과정에서 데이터를 전달하기 위해 Intent를 배운다. 구성 변경사항(화면 회전)과 같은 동작이 일어날 대, 데이터를 보존할 수 있는 임시 UI 상태 저장 및 복원(onSaveInstanceState) 를 배울 수 있다.

### Kotlin
- `findByViewId()`
- `TextWatcher`   
[TextWatcher  |  Android Developers](https://developer.android.com/reference/android/text/TextWatcher)
- `setOnClickListener()`
### Android
- Activity   
[Activity  |  Android 개발자  |  Android Developers](https://developer.android.com/guide/components/activities/intro-activities?hl=ko)
    - Lifecycle   
    [Activity Lifecycle | Android Developers](https://developer.android.com/guide/components/activities/activity-lifecycle)
    - Activity Result API   
    [활동에서 결과 가져오기  |  Android 개발자  |  Android Developers](https://developer.android.com/training/basics/intents/result?hl=ko)
    - onSaveInstanceState()  
    [UI 상태 가져오기 | Android Developers](https://developer.android.com/guide/components/activities/activity-lifecycle#saras)
- Intent   
    [다른 앱으로 사용자 보내기  |  Android 개발자  |  Android Developers](https://developer.android.com/training/basics/intents/sending?hl=ko)
- Toast   
[Toast Message 개요  |  Android 개발자  |  Android Developers](https://developer.android.com/guide/topics/ui/notifiers/toasts?hl=ko) 
- SnackBar  
[SncakBar Message 추가  |  Android 개발자  |  Android Developers](https://developer.android.com/training/snackbar/action?hl=ko)

### 🐼 실습 : Activity 전환과 데이터 주고받기, View onClick 사용으로 이벤트 발생하기
<img src="./참조 파일/chapter04_practice.gif" width="175" height="350">

## 🚀 5. Fragment 와 Dialog
Fragment 에 대해서 배우며 Fragment의 생명주기, FragmentManager, FragmentTransaction 활용, Fragment 생성자 이슈를 배울 수 있다.

Dialog가 무엇인지 배울 수 있다. Dialog 중 AlertDialog 와 DialogFragment 를 배울 수 있다.

### Kotlin
- `supportFragmentManager`
- `Dialog.Builder()`

### Android
- Fragment   
[Fragment  |  Android Developers](https://developer.android.com/guide/fragments?hl=ko)
- Dialog   
[Dialog  |  Android Developers](https://developer.android.com/guide/topics/ui/dialogs?hl=ko)

- DialogFragment   
[DialogFragment  |  Android Developers](https://developer.android.com/reference/androidx/fragment/app/DialogFragment)

### 🐼 실습 : Activity 에서 Fragment 로 전환하기

<img src="./참조 파일/chapter5_practice.gif" width="175" height="350">

## 🚀 6. ListView and RecyclerView
Adapter Pattern 으로 이루어지는 동작을 배우고, 목록을 표시하는 ListView와 RecyclerView에 대해서 배울 수 있다. 또한, ListView와 RecyclerView의 차이점에 대해서 배울 수 있다.

RecyclerView는 ViewHolder 객체를 만들어 데이터를 바인딩하여 유저에게 보여준다. ViewHolder 방법도 배울 수 있다.

### Design Pattern
- Adapter Pattern   
[Design Patterns - Adapter Pattern](https://www.tutorialspoint.com/design_pattern/adapter_pattern.htm)
### Kotlin
- ListView
    - `BaseAdapter()`
- RecycelrView
    - `RecycelrView.Adapter()`
    - `RecyclerView.ViewHolder()`
    - `notifyDataSetChanged()`
### Android
- ListView   
[ListView  |  Android Developers](https://developer.android.com/reference/android/widget/ListView)

- RecyclerView   
[RecyclerView로 동적 목록 만들기 |  Android Developers](https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=ko)

### 🐼 실습 : 입력된 검색으로 버튼 클릭 시 목록 만들기

<img src="./참조 파일/chapter06_practice.gif" width="175" height="350">

## 🚀 7. BottomNavigation and ViewPager2, TabLayout
BottomNavigation 에 대해서 배우며, ViewPager2를 배움과 동시에 TabLayout과 연동하여 스와이프 탭 구현 방법도 배울 수 있다. 

BottomNavigation 과 Navigaition Drawer 를 Material 디자인에서 비교해볼 수 있다.

### Kotlin
- BottomNavigation
    - `setOnItemSelectedListener`
- BottomNavigation + ViewPager
    - `registerOnPageChangeCallback`
- ViewPager2 Adapter
    - `RecyclerViewAdapter`
    - `FragmentStateAdapter`
- TabLayout
    - `addOnTabSelectedListener`
- ViewPager + TabLayout
    - `TabLayoutMediator`


### Android
- BottomNavigation   
[BottomNavigationView  |  Android Developers](https://developer.android.com/reference/com/google/android/material/bottomnavigation/BottomNavigationView)

- ViewPager2   
[ViewPager2로 프래그먼트 간 슬라이드  | Android Developers](https://developer.android.com/training/animation/screen-slide-2?hl=ko)

- TabLayout   
[TabLayout | Android Developers](https://developer.android.com/reference/com/google/android/material/tabs/TabLayout)

- ViewPager2 Nested(중첩) 방법   
[ViewPager에서 ViewPager2로 이전  |  Android Developers](https://developer.android.com/training/animation/vp2-migration?hl=ko#nested-scrollables)

### Material
- BottomNavigation   
    [BottomNavigation  |  Material Design](https://m2.material.io/components/bottom-navigation)

- Navigation Drawer   
[Material Design](https://m2.material.io/components/navigation-drawer/android)

### 🐼 실습 : BottomNavigation + Viewpager2  실습

<img src="./참조 파일/chapter07_practice.gif" width="175" height="350">

## 🚀 8. Notification
핸드폰을 사용하면 주로 알림이 활성화되어서 사용자에게 소식을 전달해준다. Notification 그 역할을 해준다. Notification 의 기능과 특징을 살펴볼 수 있다. 버전 별로 Notification 의 구현 방법이 다른 점을 배울 수 있다.

Android 13부터 추가된 알림 런타인 권한으로 인하여 권한 워크플로를 이해하고 권한 요청에 대해서 알아본다.

### Android 8.0(Oreo, API 26) 과 그 이전 버전의 Notification
- **Notification 채널**

    Android 8.0부터는 모든 알림을 채널에 할당해야 한다.

    [Notifiaction 채널 만들기 및 관리  |  Android Developers](https://developer.android.com/training/notify-user/channels?hl=ko)


- **Notification 중요도**

    [Notifiaction 중요도 수준 설정  |  Android Developers](https://developer.android.com/training/notify-user/channels?hl=ko#importance)
    |수준|중요도(Android 8.0 이상)|우선순위(Android 7.1이하)|
    |-|-|-|
    |긴급 (Urgent)|IMPORTANCE_HIGH|PRIORITY_HIGH 또는 PRIORITY_MAX|
    |높음 (High)|IMPORTANCE_DEFAULT|PRIORITY_DEFAULT|
    |긴급 (Medium)|IMPORTANCE_LOW|PRIORITY_LOW|
    |긴급 (Low)|IMPORTANCE_MIN|PRIORITY_MIN|

### Android 13(TIRAMISU, API 33)부터 Runtime Permission 추가
[Notification runtime permission  |  Android Developers](https://developer.android.com/develop/ui/views/notifications/notification-permission)
```Kotlin
<uses-permission android:name="android.permission.POST_NOTIFICATIONS"/>
```

### Kotlin
- `Notification.Builder`
- Create Notification Channel
    - `NotificationChannel()`
- PendingIntent
    - `setContentIntent()`
- Permission
    - 시스템이 권한 요청 코드를 관리하도록 허용
    - `ActivityCompat.requestPermissions()` + `onRequestPermissionsResult()`
    - 권한 요청 코드 직접 관리
    - `registerForActivityResult()` + `launch()`

### Android
- Notification

    [Notification 개요  |  Android Developers](https://developer.android.com/guide/topics/ui/notifiers/notifications?hl=ko)   
    [Notification 만들기  |  Android Developers](https://developer.android.com/training/notify-user/build-notification?hl=ko)
- PendingIntent

    [Notification 에서 Activity 호출  |  Android Developers](https://developer.android.com/training/notify-user/navigation?hl=ko)

- Permission in Android

    [Android 권한  |  Android Developers](https://developer.android.com/guide/topics/permissions/overview?hl=ko)   
    [Android 권한 요청  |  Android Developers](https://developer.android.com/training/permissions/requesting?hl=ko#request-permission)


### 🐼 실습 : Notification 띄우기
<img src="./참조 파일/chapter08_practice.gif" width="175" height="350">

## 🚀 9. 파일관리, ContentProvider, MediaStore
핸드폰 폴더에 파일을 관리할 수 있어야 한다. ContentProvider를 통해서 파일 시스템, SQLite DB, 웹상이나 앱이 액세스할 수 있도록 다른 모든 영구 저장위치에 저장 가능한 앱 데이터의 공유형 집합을 관리할 수 있다. 예를 들면, 연락처 정보나 갤러리 이미지, 비디오 등등



MediaStore 에 접근하여 ContentResolver의 쿼리형태로 음악파일을 불러오는 것을 배운다. 또한, 저장소 모양이 Android 10(Q, API 29) 이전과 이후로 Legacy Storage와 Scoped Storage 의 형태로 바뀜을 이해해야 한다.

### Media
[공유 저장소의 미디어 파일 액세스  |  Android Developers](https://developer.android.com/training/data-storage/shared/media?hl=ko)


### Android 10(Q, API 29) 저장소 모양 이슈
[Update Storage  |  Android Developers](https://developer.android.com/about/versions/11/privacy/storage?hl=ko)

Android 10(Q, API 29) 이전과 이후로 저장소 모양이 Legacy Storage 에서 Scoped Storage로 변경되었다.

### Android 13(TIRAMISU, API 33) 이상 타겟 시 세분화된 Meida Permission
[Media Permission in Android 13  |  Android Developers](https://developer.android.com/about/versions/13/behavior-changes-13?hl=ko#granular-media-permissions)

- 이미지 및 사진 : `READ_MEDIA_IMAGES`
- 동영상 : `READ_MEDIA_VIDEO`
- 오디오 파일 : `READ_MEDIA_AUDIO`

### Glide
[Glide  |  Github](https://github.com/bumptech/glide)

안드로이드 이미지 로딩 라이브러리이다. 이는 쉽게 네트워크를 통해 이미지를 받아올 수 있도록 도와주는 라이브러리이며, 이미지 캐싱 처리도 가능하기 때문에 매우 유용하다.


### Kotlin
- RecyclerView
    - `RecyclerView.Adapter`
- Permission
    - `READ_EXTERNAL_STORAGE`
    - `READ_MEDIA_AUDIO`
- MediaStore
    - `MediaStore.Audio.Media.EXTERNAL_CONTENT_URI`
    - `contentResolver.query()`
### Android
- Android Storage Best Practices

    [Android 저장소 사용 사례 및 권장사항  |  Android Developers](https://developer.android.com/training/data-storage/use-cases?hl=ko)
- ContentProvider

    [ContentProvider  |  Android Developers](https://developer.android.com/guide/topics/providers/content-providers?hl=ko)





###  🐼 실습 : MediaStore 를 활용한 음악 리스트 목록 표현하기
<img src="./참조 파일/chapter09_practice.gif" width="175" height="350">

## 🚀 10. ForegroundService, BroadcastReceiver, MediaPlayer
안드로이드 4대 요소 중 2가지 service, broadcastReceiver 를 배운다. service는 Foreground service를 배워볼 수 있다. 또한, MediaPlayer 를 사용하여 음악을 재생하고 종료할 수 있다.

### Foreground Service
[Foreground Service  |  Android Developers](https://developer.android.com/guide/components/foreground-services)

포그라운드 서비스는 사용자에게 잘 보이는 작업이다. 반드시 알림을 표시해야 하며, 사용자가 앱과 상호작용하지 않을 때도 계속 실행되어야 한다. 예시로 음악 재생이 있다.

### Android 8.0(Oreo, API 26) 부터 BackgroundService 실행 제한
[백그라운드 서비스 실행 제한 | Android Developers](https://developer.android.com/about/versions/oreo/background?hl=ko)


무분별한 Background Service 사용은 앱의 과부하를 일으켜 메모리 부족으로 앱이 죽는 일이 발생할 수 있다. 그렇기 때문에 Google 에서는 이러한 일들을 방지하고자 Android 8.0 부터 Background service 실행 제한을 두었고 자세한 내용을 위에 링크를 참조하자.

### Android 12부터 ForegroundService 실행 제한
[포그라운드 서비스 실행 제한 | Android Developers](https://developer.android.com/about/versions/12/foreground-services?hl=ko)

Android 12를 타겟팅하는 앱은 몇 가지 특수한 사례를 제외하고 백그라운드에서 실행되는 동안 더 이상 포그라운 서비스를 시작할 수 없다. 자세한 내용은 위에 링크를 참조하자.


### MediaPlayer
[MediaPlayer 개요 |  Android Developers](https://developer.android.com/guide/topics/media/mediaplayer?hl=ko#basics)

애플리케이션 리소스(원시 리소스)에 저장된 미디어 파일, 파일 시스템의 독립형 파일 또는 네트워크 연결을 통해 들어오는 데이터 스트림에서 모두 MediaPlayer API를 사용하여 오디오 또는 동영상을 재생할 수 있습니다.

### SAF(Storage Access Framework)
[SAF  |  Android Developers](https://developer.android.com/guide/topics/providers/document-provider?hl=ko)

SAF는 사용자가 선호하는 문서 저장소 제공자 전체에서 문서, 이미지 및 각종 다른 파일을 탐색하고 여는 작업을 간편하게 해준다. 일관된 방식으로 selector UI 를 사용하여 사용자에게 쉬운 UI 제공한다.

### Photo Picker 사용하기
[Photo Picker  |  Android Developers](https://developer.android.com/training/data-storage/shared/photopicker?hl=ko)

Android 13(TIRAMISU, API 33) 부터 사용이 가능하다. Photo Picker 의 이점은 SAF와 달리 새로운 UI를 제공하며, 해당 버전보다 아래라면 자동으로 SAF를 진행한다.

### Kotlin
- Permission   
    - `READ_EXTERNAL_STORAGE`
    - `READ_MEDIA_AUDIO`
    - `POST_NOTIFICATIONS`   
- Notification
    - `Notification.MediaStyle()`
    - `Builder().addAction`
- service
    - `startService()`
    - `startForeground()`
    - `stopSelf()`
- MediaPlayer
    - `MediaPlayer.create()`
- BroadcastReceiver
    - `registerReceiver()`

### Android
- Service

    [서비스 개요 |  Android Developers](https://developer.android.com/guide/components/services?hl=ko)

- BroadcastReceiver

    [브로드캐스트 개요 |  Android Developers](https://developer.android.com/guide/components/broadcasts?hl=ko)
### 🐼 실습 : 뮤직 플레이어 만들기
실습 9의 연장선으로 노래 플레이 알림을 띄우고 배터리가 줄었을 때, 경고 알림을 사용자에게 알려준다.

<img src="./참조 파일/chapter10_practice1.gif" width="175" height="350">
<img src="./참조 파일/chapter10_practice2.gif" width="175" height="350">

## 🚀 11. Thread, Coroutine 
Thread와 Coroutine을 사용함으로써 동시성 프로그램을 이해하고, 비동기적으로 실행되는 것을 배울 수 있다. Coroutine 을 android 에서 사용하면서 Thread 의 Blocking의 단점을 보완하고 효율적인 비동기 실행을 할 수 있다.


### Thread
[Thread |  Android Developers](https://developer.android.com/guide/components/processes-and-threads?hl=ko)

Android 에서는 Thread 를 사용하는 데 있어서 중요한 규칙들이 존재한다.
- UI 스레드를 차단하지 마세요.
    - 앱이 일정시간 동안 반응이 없을 경우 ANR이 발생할 수 있다.
- UI 스레드 외부에서 Android UI 도구 키트에 액세스하지 마세요.

### 워크 스레드에서 UI 스레드 접근하는 방법

- [Activity.runOnUiThread(Runnable)](https://developer.android.com/reference/android/app/Activity#runOnUiThread(java.lang.Runnable))
- [View.post(Runnable)](https://developer.android.com/reference/android/app/Activity#runOnUiThread(java.lang.Runnable))
- [View.postDelayed(Runnable, long)](https://developer.android.com/reference/android/view/View?hl=ko#postDelayed(java.lang.Runnable,%20long))
- [Handler](https://developer.android.com/reference/android/os/Handler)

### Coroutine
[Coroutine |  Android Developers](https://developer.android.com/kotlin/coroutines?hl=ko)   
[Coroutine |  Kotlin Documents](https://kotlinlang.org/docs/coroutines-guide.html)

Coroutine은 비동기적으로 실행되는 코드를 간소화하기 위해 Android에서 사용할 수 있는 동시 실행 설계 패턴이다. Coroutine은 Kotlin 버전 1.3에 추가되었으며 다른 언어에서 확립된 개념을 기반으로 한다.

### ListAdapter & DiffUtil
[ListAdapter |  Android Developers](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter)   
[DiffUtil |  Android Developers](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil)

RecyclerView 기반으로 만들어진 ListAdapter는 데이터 변경 사항 처리를 좀 더 효율적으로 처리할 수 있다. DiffUtil 을 사용하여 삽입, 삭제 등 메인 Thread에서 무엇이 처리되는 지 확인 가능하다.

DiffUtil 은 두 데이터셋을 받아 그 차이를 계산해주는 클래스이다. 그렇기 때문에 ListAdpater를 RecyclerView.Adapter 대신 사용하여 데이터 비교로 변한 부분을 효율적으로 찾아낼 수 있다.


### Kotlin
- ListAdapter & DiffUtil
    - `DiffUtil.ItemCallback`
    - `submitList()`
- 타이머
    - `timer()`
    - `fixedRateTimer()`
- LinearLayoutManager
    - `reverseLayout `
    - `stackFromEnd `
- Thread
    - `Thread{}.start()`
- Coroutine
    - `CoroutineScope.lanch`
    - `delay()`
- System
    - `currentTimeMillis()`
- SystemClock
    - `uptimeMillis`
    - `elapsedRealtime`
### Android
- Timer   
    [Timer |  Android Developers](https://developer.android.com/reference/java/util/Timer)

- SystemClock   
    [SystemClock |  Android Developers](https://developer.android.com/reference/android/os/SystemClock)



### 🐼 실습 : 스톱워치 만들기

<img src="./참조 파일/chapter11_practice.gif" width="175" height="350">


## 🚀 12. Design Pattern, DataBinding, Room, ViewModel, LiveData
안드로이드에서 주로 사용하는 디자인 패턴에 대해서 살펴보고, Android Jetpack Component 몇 가지를 살펴볼 것이다. 추가적으로 데이터를 관리하기 위해 ViewModel과 LiveData를 살펴봄으로써 데이터를 어떤 식으로 보존하는 지 배울 수 있다.


### Design Pattern
- [MVC(Model-View-Controller)](https://atom-feet-3b2.notion.site/MVC-3aaaf18de4db4481b258f2aacb43eadb?pvs=4)   
    : 안드로이드 초기 구조로 자연스럽게 적용되었다. 또한, 안드로이드와 관계 없이 널리 사용되는 구조이다. 규모가 작고 앱을 빠르게 진행하는 프로젝트는 MVC 패턴이 적합한 것 같다. 하지만 유지보수가 여럽고 결합도가 높아 많이 사용되지는 않는다.
- [MVP(Model-View-Presenter)](https://atom-feet-3b2.notion.site/MVP-f0b483b106cc4892b9c04b8ce9a752a2?pvs=4)  
    : 단위 테스트가 어려웠던 MVC 패턴의 문제점을 해결하고자 나타난 패턴이다. 이는 관심사를 분리하는 역할을 해주어 코드 가독성을 높이는 데 큰 역할을 하였다. 비즈니스 로직이 Presenter에 모이는 경향이 있어서 코드가 비대해질 수 있다.
- [MVVM(Model-View-ViewModel)](https://atom-feet-3b2.notion.site/MVVM-708ba816031b4af8adab413ebc9eb43a?pvs=4)   
    : MVP 패턴에서 View와 Presenter는 1대1 매칭이라 강하게 결합되어있는데, 이를 보완하기 위해서 MVVM 패턴을 알아야 한다. 
- [MVI(Model-View-Intent)](https://atom-feet-3b2.notion.site/MVI-c9022cec947842bcae29c4a45af07990?pvs=4)   
    : MVVM 패턴의 상태 문제와 부수효과라는 문제점이 있다. 이러한 문제점을 보완하기 위해서 JavaScript의 Redux 기반으로 MVI 패턴이 등장하였다.
### DataBinding, Jetpack Component
[DataBinding  | Android Developers](https://developer.android.com/topic/libraries/data-binding?hl=ko)

데이터 결합 라이브러리는 프로그래매틱 방식이 아니라 선언적 형식으로 레이아웃의 UI 구성요소를 앱의 데이터 소스와 결합할 수 있는 지원 라이브러리이다.

추가적으로 [ViewBinding](https://developer.android.com/topic/libraries/view-binding?hl=ko) 에 대해서도 알아볼 필요가 있다.

### Room, Jetpack Component
[Room을 사용하여 로컬 데이터베이스에 데이터 저장  |  Android Developers](https://developer.android.com/training/data-storage/room?hl=ko)

상당한 양의 구조화된 데이터를 처리하는 앱은 데이터를 로컬에 유지하여 매우 큰 이익을 얻을 수 있다. 즉, 로컬 데이터베이스(DB)이다.

### ViewModel, Jetpack Component
[ViewModel  | Android Developers](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=ko)

ViewModel 클래스는 수명주기를 고려하여 UI 관련 데이터를 저장하고 관리하도록 설계되었다. ViewModel 클래스를 사용하면 화면 회전과 같이 구성을 변경할 때도 데이터를 유지할 수 있다.


### LiveData, Jetpack Component
[LiveData  | Android Developers](https://developer.android.com/topic/libraries/architecture/livedata?hl=ko)

관찰 가능한 일반 클래스와 달리 LiveData는 수명 주기를 인식합니다. 즉, 액티비티, 프래그먼트, 서비스 등 다른 앱 구성요소의 수명 주기를 고려합니다. 수명 주기 인식을 통해 LiveData는 액티비티 수명 주기 상태에 있는 앱 구성요소 관찰자만 업데이트합니다. 

요즘에는 Jetpack Compose가 대세이기 때문에 LiveData 보다는 Flow를 많이 사용한다. [Flow](https://developer.android.com/kotlin/flow?hl=ko) 에 대해서 공부를 추가적을 해보는 것도 중요하다.

### Coil
[Coil](https://coil-kt.github.io/coil/)

Coil은 Coroutine Image Loader의 약자이다. 즉, 코루틴으로 만들어진 가벼운 이미지 로딩 라이브러리이다. Glide과 비교될 수 있다. 각각의 장단점을 살펴보고 유용한 라이브러리를 적용해서 사용하는 것 또한 개발의 재미...

### Parcelize
[Parcelable 구현 생성기  |  Kotlin  |  Android Developers](https://developer.android.com/kotlin/parcelize?hl=ko)

Parcelize 는 Parcelable 클래스를 직렬화 시 Container 역활을 하는 클래스가 된다. 직렬화하면 Serializable 이 떠오른다. Serializalbe 과 Parcelable의 장단점을 살펴보고 Parcelize 플러그인을 사용하여 안드로이드 개발에 적용해보자.

### Android
- Pattern
    - Repository Pattern
- DataBinding
    - `@BindingAdapter()`
- ViewModel
    - `ViewModelProvider.Factory`
    - `by viewModels()`

### 🐼 실습 : 단어장 만들기

<img src="./참조 파일/chapter12_practice1.gif" width="175" height="350">
<img src="./참조 파일/chapter12_practice2.gif" width="175" height="350">
