# BCSD-beginner-example
## 목차
- [Kotlin and Android](#1-about-kotlin-and-android)
- [View : Wiget and Layout](#2-view-widget-and-layout)
- [Rescoure : Style and Theme etc...](#3-style과-theme-resource)
- [Activity and View 상호작용](#4-activity와-view-상호작용)
- [Fragment and Dialog](#5-fragment-와-dialog)
- [ListView and RecyclerView](#6-listview-and-recyclerview)
- [BottomNavigation, ViewPager2 and TabLayout](#7-bottomnavigation-and-viewpager2-tablayout)
___
## 1. About Kotlin and Android
[Kotlin  |  Kotlin Docs](https://kotlinlang.org/docs/home.html)

Kotlin 에 대해서 소개하고 다양한 문법과 언어의 특징을 소개한다. 

[Android 스튜디오 다운로드 및 설치  |  Android Developers](https://developer.android.com/codelabs/basic-android-kotlin-compose-install-android-studio?hl=ko#0)

[Android 버전  |  Android Developers](https://developer.android.com/about/versions?hl=ko)

Android 에서 사용되는 IDE 인 Android Studio의 설치와 Android 의 다양한 버전과 특징에 대해서 알아볼 수 있다.
## 2. View, Widget and Layout
[View  |  Android Developers](https://developer.android.com/reference/android/view/View)

유저가 상호작용하는 화면을 직접그려볼 수 있다. View와 Widget, Layout 의 개념에 대해서 알아볼 수 있고, 다양한 Widget과 Layout 을 실습할 수 있다.

레이아웃의 계층 구조를 이해하며 XML 구조를 설계하는 것에 있어서 중첩된 레이아웃 계층 구조를 피할 수 있다.

### 실습 : Layout
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

## 3. Style과 Theme, Resource
[앱 리소스 개요  |  Android 개발자  |  Android Developers](https://developer.android.com/guide/topics/resources/providing-resources?hl=ko)

리소스 파일을 가져오기 위해서 R클래스의 정적 변수들을 가져오는 것을 배울 수 있다. 또한, 속성으로 Theme와 Style을 지정하여 좀 더 편리하고 일관적인 디자인을 부여할 수 있다.

### 실습 : 세로-가로 모드, 글꼴 크기, 디스플레이 크기, 언어 대응
<img src="./참조 파일/chapter3_practice1.gif" width="175" height="350">
<img src="./참조 파일/chapter3_practice2.gif" width="175" height="350">

## 4. Activity와 View 상호작용
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

### 실습 : Activity 전환과 데이터 주고받기, View onClick 사용으로 이벤트 발생하기
<img src="./참조 파일/chapter4_practice.gif" width="175" height="350">

## 5. Fragment 와 Dialog
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

### 실습 : Activity 에서 Fragment 로 전환하기

<img src="./참조 파일/chapter5_practice.gif" width="175" height="350">

## 6. ListView and RecyclerView
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

### 실습 : 입력된 검색으로 버튼 클릭 시 목록 만들기

<img src="./참조 파일/chapter6_practice.gif" width="175" height="350">

## 7. BottomNavigation and ViewPager2, TabLayout
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

### 실습 : 

<img src="./참조 파일/chapter7_practice.gif" width="175" height="350">
