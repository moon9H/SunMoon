# SunMoon Memory

> 💌 연인과의 대화 기록을 다시 돌아보며 추억을 떠올리는 Spring MVC 메모리 웹 프로젝트

## ✨ 프로젝트 소개

SunMoon Memory는 정적 웹으로 시작한 대화 리마인드 UI를  
Spring Boot + Spring MVC + Neon PostgreSQL 구조로 이식한 프로젝트입니다.

현재는 Cloudtype 배포까지 완료된 상태이며,  
MVC 템플릿 화면과 REST API가 함께 동작하는 `v1.0` 릴리스 버전입니다.

## 🚀 Release `v1.0`

이번 버전에서 포함된 내용:

- Spring MVC 기반 메인 화면 `/` 구성
- Neon PostgreSQL 연결 완료
- Cloudtype 배포 완료
- `GET /db/ping` DB 연결 확인 endpoint 유지
- `GET /api/conversations` 대화 목록 조회 API 구현
- `GET /api/conversations/{id}` 대화 상세 조회 API 구현
- 정적 웹 UI를 Spring MVC 템플릿 구조로 이식
- 정적 JSON 대신 현재 REST API를 사용하는 화면 구성

## 🫶 주요 기능

- 대화 목록 조회
- 목록 클릭 시 상세 대화 조회
- 현재 선택된 항목 강조
- 랜덤 추억 보기
- 사용자 시점 전환
- 시점에 따른 전체 테마 변경
- 프로필 이미지 fallback 처리

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
