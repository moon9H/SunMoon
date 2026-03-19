# Sun & Moon

> 💌 연인과의 대화 기록을 다시 돌아보며 추억을 떠올리는 Spring MVC 메모리 웹 프로젝트

## ✨ 프로젝트 소개

Sun & Moon은 정적 웹으로 시작한 대화 리마인드 UI를  
Spring Boot + Spring MVC + Neon PostgreSQL 구조로 확장한 프로젝트입니다.

현재는 Cloudtype 배포까지 완료된 상태이며,  
대화 목록과 상세 조회, 감성 UI, 모바일 대응까지 포함한 `v1.5` 릴리스 기준으로 정리되어 있습니다.

## 🚀 Release `v1.5`

이번 버전에서 반영된 핵심 변경사항:

- Neon PostgreSQL 연동 및 `/db/ping` 구성
- `GET /api/conversations` 대화 목록 조회 API 구현
- `GET /api/conversations/{id}` 대화 상세 조회 API 구현
- 정적 웹 UI를 Spring MVC 템플릿 구조로 이식
- `/` 진입 화면을 MVC 기반으로 전환
- 정적 JSON 대신 현재 REST API를 사용하는 화면 구성
- 대화 목록 정렬을 시간 흐름에 맞게 보정
- favicon 및 UI 아이콘 정리
- 모바일 반응형 레이아웃 개선
- 모바일에서 상세 대화 영역 내부 스크롤 개선
- 서비스 표시명을 `Sun & Moon`으로 정리
- DB 구조 확인용 `db/schema.sql` 포함

## 🫶 주요 기능

- 대화 목록 조회
- 목록 클릭 시 상세 대화 조회
- 현재 선택된 항목 강조
- 랜덤 추억 보기
- 사용자 시점 전환
- 시점에 따른 전체 테마 변경
- 모바일 화면 대응

## 🔗 주요 엔드포인트

- `GET /`
- `GET /db/ping`
- `GET /api/conversations`
- `GET /api/conversations/{id}`

## 🗂️ 프로젝트 구조

```text
SunMoon/
├─ README.md
├─ build.gradle
├─ db/
│  └─ schema.sql
├─ gradlew
├─ gradlew.bat
└─ src/
   └─ main/
      ├─ java/
      │  └─ io/cloudtype/sunmoon/
      │     ├─ SunMoonApplication.java
      │     ├─ config/
      │     ├─ controller/
      │     ├─ dto/
      │     ├─ repository/
      │     └─ service/
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
