# SunMoon Memory

> 연인과의 대화 기록을 다시 돌아보며 추억을 떠올리는 Spring MVC 기반 메모리 웹 프로젝트

## 프로젝트 소개

SunMoon Memory는 정적 웹으로 시작한 대화 리마인드 UI를
Spring Boot + Spring MVC + Neon PostgreSQL 구조로 이식한 프로젝트입니다.

현재 버전은 단순 API 서버를 넘어서,
실제 MVC 템플릿 화면과 REST API가 함께 동작하는 첫 번째 릴리스 상태입니다.

## 현재 버전

### `v1.0`

Spring MVC 기반 첫 릴리스 버전입니다.

이번 버전에서 포함된 내용:

- Neon PostgreSQL 연결
- Cloudtype 배포 완료
- `GET /db/ping` DB 연결 확인 endpoint 유지
- `GET /api/conversations` 대화 목록 조회 API 구현
- `GET /api/conversations/{id}` 대화 상세 조회 API 구현
- 정적 웹 UI를 Spring MVC 템플릿 구조로 이식
- 정적 JSON 대신 현재 REST API를 사용하는 화면 구성
- 사용자 시점 전환, 랜덤 추억 보기, 목록/상세 렌더링 동작 유지

## 현재 구현된 기능

- 메인 진입 경로 `/` 에서 MVC 템플릿 화면 제공
- 대화 목록 조회
- 목록 클릭 시 상세 대화 조회
- 현재 선택된 항목 강조
- 랜덤 추억 보기
- 사용자 시점 전환
- 시점에 따른 전체 테마 변경
- 프로필 이미지 fallback 처리

## 주요 엔드포인트

- `GET /`
- `GET /db/ping`
- `GET /api/conversations`
- `GET /api/conversations/{id}`

## 프로젝트 구조

```text
SunMoon/
├─ README.md
├─ build.gradle
├─ gradlew
├─ gradlew.bat
├─ scripts/
│  ├─ use-project-jdk17.ps1
│  ├─ local-db.env.example.ps1
│  ├─ local-db.env.ps1
│  └─ run-local-db.ps1
└─ src/
   └─ main/
      ├─ java/
      │  └─ io/cloudtype/sunmoon/
      │     ├─ SunMoonApplication.java
      │     ├─ config/
      │     │  └─ DbConfig.java
      │     ├─ controller/
      │     │  ├─ ConversationApiController.java
      │     │  ├─ DbPingController.java
      │     │  └─ ViewController.java
      │     ├─ dto/
      │     │  └─ conversation/
      │     ├─ repository/
      │     │  └─ ConversationQueryRepository.java
      │     └─ service/
      │        ├─ ConversationQueryService.java
      │        └─ DbHealthCheckService.java
      └─ resources/
         ├─ application.properties
         ├─ templates/
         │  └─ index.html
         └─ static/
            └─ assets/
               ├─ css/
               ├─ images/
               └─ js/
```

## 실행 방법

### 1. 로컬 DB 프로필 실행

```powershell
powershell -ExecutionPolicy Bypass -File .\scripts\run-local-db.ps1
```

실행 후 브라우저에서 아래 주소로 접속합니다.

```text
http://localhost:8080/
```

### 2. 단계별 실행

```powershell
Set-ExecutionPolicy -Scope Process Bypass
. .\scripts\use-project-jdk17.ps1
. .\scripts\local-db.env.ps1
$env:SPRING_PROFILES_ACTIVE = 'db'
.\gradlew.bat bootRun
```

## 배포 환경 변수

- `SPRING_PROFILES_ACTIVE=db`
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`

## 검증 방법

```powershell
powershell -ExecutionPolicy Bypass -Command "& { . .\scripts\use-project-jdk17.ps1; $env:GRADLE_USER_HOME='C:\ssafy\SunMoon\.gradle-user'; .\gradlew.bat test }"
```

## 다음 단계

- URL 상태 동기화
- 브라우저 뒤로가기/앞으로가기 대응
- 검색 / 정렬 / 필터 기능 확장
- 배포 환경 최종 점검 및 UI 다듬기

## 데이터 및 공개 범위 안내

이 프로젝트는 개인적인 대화 기록을 다루는 성격이 있으므로,
실제 데이터가 포함된 상태에서는 비공개 저장소 또는 안전한 환경에서 관리하는 것을 권장합니다.

공개용으로 확장할 경우에는 실제 대화 데이터와 샘플 데이터를 분리해서 관리하는 편이 안전합니다.
